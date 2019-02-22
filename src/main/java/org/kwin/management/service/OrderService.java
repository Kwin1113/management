package org.kwin.management.service;

import org.kwin.management.dto.OrderDTO;
import org.kwin.management.entity.OrderMaster;
import org.kwin.management.form.OrderForm;

import java.util.List;

public interface OrderService {

    List<OrderMaster> selectAll();

    OrderDTO selectOne(String orderId);

    OrderDTO add(OrderForm orderForm);

    OrderDTO update(OrderDTO orderDTO);

    void cancel(String orderId);
}
