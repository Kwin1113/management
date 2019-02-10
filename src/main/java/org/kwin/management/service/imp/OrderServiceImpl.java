package org.kwin.management.service.imp;

import lombok.extern.slf4j.Slf4j;
import org.kwin.management.dao.OrderDetailMapper;
import org.kwin.management.dao.OrderMasterMapper;
import org.kwin.management.dto.CartDTO;
import org.kwin.management.dto.OrderDTO;
import org.kwin.management.entity.OrderDetail;
import org.kwin.management.entity.OrderMaster;
import org.kwin.management.entity.Product;
import org.kwin.management.enums.OrderStatusEnum;
import org.kwin.management.enums.ResultEnum;
import org.kwin.management.exception.SysException;
import org.kwin.management.service.OrderService;
import org.kwin.management.service.ProductService;
import org.kwin.management.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<OrderMaster> orderMasters = orderMasterMapper.selectAll();
        return orderMasters;
    }

    @Override
    public List<OrderDetail> ListAll() {
        List<OrderDetail> orderDetailList = orderDetailMapper.selectAll();
        return orderDetailList;
    }

    @Override
    public OrderDTO selectOne(String orderId) {
        OrderDTO orderDTO = new OrderDTO();

        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderId);
        List<OrderDetail> orderDetails = orderDetailMapper.selectByOrderId(orderId);
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetails);

        return orderDTO;
    }

    @Override
    public OrderDetail selectOneDetail(String detailId) {
        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(detailId);
        return orderDetail;
    }

    @Override
    @Transactional
    public OrderDTO add(OrderDTO orderDTO) {
        //添加orderMaster
        String orderId = KeyUtil.getUniqueKey();
        Integer orderAmount = 0;

        //添加orderDetail
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            //订单详情入库
            Product product = productService.selectOne(orderDetail.getProductId());
            if (product == null) {
                throw new SysException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            BeanUtils.copyProperties(product, orderDetail);
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetailMapper.insert(orderDetail);
            orderAmount = orderAmount + orderDetail.getProductQuantity() * orderDetail.getProductPrice();
        }

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMasterMapper.insert(orderMaster);

        //扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.descStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDetail addDetail(OrderDetail orderDetail) {
        orderDetailMapper.insert(orderDetail);
        //查询所有该订单的订单详情
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(orderDetail.getOrderId());
        //重新计算Amount
        Integer orderAmount = 0;
        for (OrderDetail detail : orderDetailList) {
            orderAmount = orderAmount + detail.getProductPrice() * detail.getProductQuantity();
        }
        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderDetail.getOrderId());
        orderMaster.setOrderAmount(orderAmount);
        orderMasterMapper.updateByPrimaryKeySelective(orderMaster);
        return orderDetail;
    }

    /**
     * 变更订单主体信息
     *
     * @param orderDTO
     * @return
     */
    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderDTO.getOrderId());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterMapper.updateByPrimaryKeySelective(orderMaster);

        return orderDTO;
    }

    /**
     * 变更订单详情信息
     *
     * @param cartDTOList
     */
    @Override
    public void updateDetail(List<CartDTO> cartDTOList) {
        //TODO
//        List<OrderDetail> orderDetailList = new ArrayList<>();
//        for (CartDTO cartDTO : cartDTOList) {
//
//        }
    }

    @Override
    @Transactional
    public void cancel(String orderId) {
        //1.判断订单状态，只有NEW状态才能被取消
        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderId);
        if (orderMaster.getOrderStatus() != OrderStatusEnum.NEW.getCode()) {
            throw new SysException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
        orderMasterMapper.updateByPrimaryKeySelective(orderMaster);

        //3.修改库存
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(orderId);
        List<CartDTO> cartDTOList = orderDetailList.stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.incrStock(cartDTOList);

    }

    /**
     * 重新计算订单总价
     * @param orderId
     */
    @Override
    public void calculateAmount(String orderId) {
        //查询所有该订单的订单详情
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(orderId);
        //重新计算Amount
        Integer orderAmount = 0;
        for (OrderDetail orderDetail : orderDetailList) {
            orderAmount = orderAmount + orderDetail.getProductPrice() * orderDetail.getProductQuantity();
        }
        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMasterMapper.updateByPrimaryKeySelective(orderMaster);
    }
}
