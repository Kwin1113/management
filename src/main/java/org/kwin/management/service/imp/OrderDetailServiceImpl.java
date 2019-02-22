package org.kwin.management.service.imp;

import org.kwin.management.common.Const;
import org.kwin.management.dao.OrderDetailMapper;
import org.kwin.management.dto.CartDTO;
import org.kwin.management.dto.OrderDTO;
import org.kwin.management.entity.OrderDetail;
import org.kwin.management.entity.Product;
import org.kwin.management.form.OrderDetailForm;
import org.kwin.management.form.ProductAddForm;
import org.kwin.management.service.OrderDetailService;
import org.kwin.management.service.OrderService;
import org.kwin.management.service.ProductService;
import org.kwin.management.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    ProductService productService;

    @Override
    public List<OrderDetail> ListAll() {
        List<OrderDetail> orderDetailList = orderDetailMapper.selectAll(Integer.parseInt(Const.getCurrentUser()));
        return orderDetailList;
    }

    @Override
    @Transactional
    public void updateDetail(OrderDetailForm orderDetailForm) {

        OrderDetail orderDetail = new OrderDetail();
        BeanUtils.copyProperties(orderDetailForm, orderDetail);

        OrderDetail orderDetail1 = orderDetailMapper.selectByPrimaryKey(orderDetail.getDetailId(),Integer.parseInt(Const.getCurrentUser()));
        Integer quantity = orderDetail.getProductQuantity() - orderDetail1.getProductQuantity();
        orderDetail1.setProductQuantity(orderDetail.getProductQuantity());
        List<CartDTO> cartDTOList = new ArrayList<>();
        CartDTO cartDTO = new CartDTO(orderDetail1.getProductId(), Math.abs(quantity));
        cartDTOList.add(cartDTO);
        if (quantity > 0) {
            //增加商品库存
            productService.descStock(cartDTOList);
        } else {
            //减少商品库存
            productService.incrStock(cartDTOList);
        }
        orderDetailMapper.updateByPrimaryKeySelective(orderDetail,Integer.parseInt(Const.getCurrentUser()));

        //查询所有该订单的订单详情
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(orderDetail.getOrderId(),Integer.parseInt(Const.getCurrentUser()));
        //重新计算订单总价
        Integer orderAmount = 0;
        for (OrderDetail detail : orderDetailList) {
            orderAmount = orderAmount + detail.getProductPrice() * detail.getProductQuantity();
        }
        //更新订单
        OrderDTO orderDTO = orderService.selectOne(orderDetail.getOrderId());
        orderDTO.setOrderAmount(orderAmount);
        orderService.update(orderDTO);
    }

    @Override
    @Transactional
    public OrderDetail addDetail(String orderId, ProductAddForm productAddForm) {
        OrderDetail orderDetail = new OrderDetail();
        BeanUtils.copyProperties(productAddForm, orderDetail);
        orderDetail.setOrderId(orderId);
        Product product = productService.selectByTypeAndSizeAndDirection(productAddForm);
        orderDetail.setProductId(product.getProductId());
        orderDetail.setDetailId(KeyUtil.getUniqueKey());
        orderDetail.setProductPrice(product.getProductPrice());

        //标记是否有相同的商品
        boolean flag = false;

        //查询所有该订单的订单详情
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(orderDetail.getOrderId(),Integer.parseInt(Const.getCurrentUser()));

        //重新计算Amount
        Integer orderAmount = 0;
        for (OrderDetail detail : orderDetailList) {
            //如果该订单详情里有相同的商品，则直接加上商品数量，并标记flag=true
            if (detail.getProductId().equals(orderDetail.getProductId())) {
                detail.setProductQuantity(detail.getProductQuantity() + orderDetail.getProductQuantity());
                orderDetailMapper.updateByPrimaryKeySelective(detail,Integer.parseInt(Const.getCurrentUser()));
                flag = true;
            }
            orderAmount = orderAmount + detail.getProductPrice() * detail.getProductQuantity();
        }

        //如果flag=true，则该详情已经增加，不用再insert
        if (!flag) {
            orderDetailMapper.insert(orderDetail,Integer.parseInt(Const.getCurrentUser()));
            orderAmount = orderAmount + orderDetail.getProductQuantity() * orderDetail.getProductPrice();
        }

        //更新订单
        OrderDTO orderDTO = orderService.selectOne(orderDetail.getOrderId());
        orderDTO.setOrderAmount(orderAmount);
        orderService.update(orderDTO);
        CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
        List<CartDTO> cartDTOList = new ArrayList<>();
        cartDTOList.add(cartDTO);
        productService.descStock(cartDTOList);

        return orderDetail;
    }

    @Override
    public OrderDetail selectOneDetail(String detailId) {
        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(detailId,Integer.parseInt(Const.getCurrentUser()));
        return orderDetail;
    }

    @Override
    @Transactional
    public void delete(String detailId) {
        //返还库存
        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(detailId,Integer.parseInt(Const.getCurrentUser()));
        CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
        List<CartDTO> cartDTOList = new ArrayList<>();
        cartDTOList.add(cartDTO);
        productService.incrStock(cartDTOList);
        //删除信息
        orderDetailMapper.deleteByPrimaryKey(detailId,Integer.parseInt(Const.getCurrentUser()));

        //查询所有该订单的订单详情
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(orderDetail.getOrderId(),Integer.parseInt(Const.getCurrentUser()));
        //重新计算订单总价
        Integer orderAmount = 0;
        for (OrderDetail detail : orderDetailList) {
            orderAmount = orderAmount + detail.getProductPrice() * detail.getProductQuantity();
        }
        //更新订单
        OrderDTO orderDTO = orderService.selectOne(orderDetail.getOrderId());
        orderDTO.setOrderAmount(orderAmount);
        orderService.update(orderDTO);
    }
}
