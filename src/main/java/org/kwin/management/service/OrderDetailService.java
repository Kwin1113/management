package org.kwin.management.service;

import org.kwin.management.entity.OrderDetail;
import org.kwin.management.form.OrderDetailForm;
import org.kwin.management.form.ProductAddForm;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> ListAll();

    void updateDetail(OrderDetailForm orderDetailForm);

    OrderDetail addDetail(String orderId, ProductAddForm productAddForm);

    OrderDetail selectOneDetail(String detailId);

    void delete(String detailId);
}
