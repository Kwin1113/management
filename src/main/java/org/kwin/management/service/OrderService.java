package org.kwin.management.service;

import org.kwin.management.dto.CartDTO;
import org.kwin.management.dto.OrderDTO;
import org.kwin.management.entity.OrderDetail;
import org.kwin.management.entity.OrderMaster;

import java.util.List;

public interface OrderService {

    List<OrderMaster> selectAll();

    List<OrderDetail> ListAll();

    OrderDTO selectOne(String orderId);

    OrderDetail selectOneDetail(String detailId);

    OrderDTO add(OrderDTO orderDTO);

    OrderDetail addDetail(OrderDetail orderDetail);

    OrderDTO update(OrderDTO orderDTO);

    void updateDetail(List<CartDTO> cartDTOList);

    void cancel(String orderId);

    void calculateAmount(String orderId);
}
