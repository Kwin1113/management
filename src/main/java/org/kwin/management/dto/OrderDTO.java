package org.kwin.management.dto;

import lombok.Data;
import org.kwin.management.entity.OrderDetail;

import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private String orderId;

    private String buyerAddress;

    private String buyerName;

    private String buyerPhone;

    private Integer installerId;

    private Date installTime;

    private Integer orderDeposit;

    private Integer orderAmount;

    private Integer orderStatus;

    private List<OrderDetail> orderDetailList;
}
