package org.kwin.management.service;

import org.kwin.management.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> ListAll();

    void updateDetail(OrderDetail orderDetail);

    OrderDetail addDetail(OrderDetail orderDetail);

    OrderDetail selectOneDetail(String detailId);

    void delete(String detailId);
}
