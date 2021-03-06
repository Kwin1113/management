package org.kwin.management.service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kwin.management.common.common.Const;
import org.kwin.management.common.enums.OrderStatusEnum;
import org.kwin.management.common.enums.ResultEnum;
import org.kwin.management.common.exception.SysException;
import org.kwin.management.common.utils.KeyUtil;
import org.kwin.management.dao.mapper.OrderDetailMapper;
import org.kwin.management.dao.mapper.OrderMasterMapper;
import org.kwin.management.dto.CartDTO;
import org.kwin.management.dto.OrderDTO;
import org.kwin.management.entity.OrderDetail;
import org.kwin.management.entity.OrderMaster;
import org.kwin.management.entity.Product;
import org.kwin.management.form.OrderForm;
import org.kwin.management.form.ProductAddForm;
import org.kwin.management.service.service.OrderService;
import org.kwin.management.service.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMasterMapper orderMasterMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    ProductService productService;

    @Override
    public List<OrderMaster> selectAll() {
        List<OrderMaster> orderMasters = orderMasterMapper.selectAll(Integer.parseInt(Const.getCurrentUser()));
        return orderMasters;
    }

    @Override
    public OrderDTO selectOne(String orderId) {
        OrderDTO orderDTO = new OrderDTO();

        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderId,Integer.parseInt(Const.getCurrentUser()));
        List<OrderDetail> orderDetails = orderDetailMapper.selectByOrderId(orderId,Integer.parseInt(Const.getCurrentUser()));
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetails);

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO add(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderForm, orderDTO);

        Date date = orderForm.getInstallerTime();
        orderDTO.setInstallTime(date);

        List<ProductAddForm> productAddFormList = orderForm.getProductAddFormList();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (ProductAddForm productAddForm : productAddFormList) {
            OrderDetail orderDetail = new OrderDetail();
            Product product = productService.selectByTypeAndSizeAndDirection(productAddForm);
            BeanUtils.copyProperties(product, orderDetail);
            orderDetail.setProductQuantity(productAddForm.getProductQuantity());
            orderDetailList.add(orderDetail);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        //添加orderMaster
        String orderId = KeyUtil.getUniqueKey();
        Integer orderAmount = 0;

        //添加orderDetail
        List<OrderDetail> orderDetailList1 = orderDTO.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList1) {
            //订单详情入库
            Product product = productService.selectOne(orderDetail.getProductId());
            if (product == null) {
                throw new SysException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            BeanUtils.copyProperties(product, orderDetail);
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetailMapper.insert(orderDetail,Integer.parseInt(Const.getCurrentUser()));
            orderAmount = orderAmount + orderDetail.getProductQuantity() * orderDetail.getProductPrice();
        }

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMasterMapper.insert(orderMaster,Integer.parseInt(Const.getCurrentUser()));

        //扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.descStock(cartDTOList);

        return orderDTO;
    }


    /**
     * 变更订单主体信息
     *
     * @param orderDTO
     * @return
     */
    @Override
    public OrderDTO update(OrderDTO orderDTO) {

        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderDTO.getOrderId(),Integer.parseInt(Const.getCurrentUser()));
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterMapper.updateByPrimaryKeySelective(orderMaster,Integer.parseInt(Const.getCurrentUser()));

        return orderDTO;
    }

    @Override
    @Transactional
    public void cancel(String orderId) {
        //1.判断订单状态，只有NEW状态才能被取消
        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderId,Integer.parseInt(Const.getCurrentUser()));
        if (orderMaster.getOrderStatus() != OrderStatusEnum.NEW.getCode()) {
            throw new SysException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
        orderMasterMapper.updateByPrimaryKeySelective(orderMaster,Integer.parseInt(Const.getCurrentUser()));

        //3.修改库存
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(orderId,Integer.parseInt(Const.getCurrentUser()));
        List<CartDTO> cartDTOList = orderDetailList.stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.incrStock(cartDTOList);

    }

}
