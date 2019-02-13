package org.kwin.management.service;

import org.kwin.management.dto.OrderDTO;
import org.kwin.management.entity.OrderMaster;

import java.util.List;

public interface OrderService {

    List<OrderMaster> selectAll();

    OrderDTO selectOne(String orderId);

    OrderDTO add(OrderDTO orderDTO);

    OrderDTO update(OrderDTO orderDTO);

    void cancel(String orderId);
}
