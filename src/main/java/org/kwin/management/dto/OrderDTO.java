package org.kwin.management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.kwin.management.entity.OrderDetail;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private String orderId;

    private String buyerAddress;

    private String buyerName;

    private String buyerPhone;

    private Integer installerId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date installTime;

    private Integer orderDeposit;

    private Integer orderAmount;

    private Integer orderStatus;

    private List<OrderDetail> orderDetailList;
}
