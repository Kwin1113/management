package org.kwin.management.entity;

import lombok.Data;

import java.util.Date;

@Data
public class OrderMaster {
    private String orderId;

    private String buyerAddress;

    private String buyerName;

    private String buyerPhone;

    private Integer installerId;

    private Date installTime;

    private Integer orderDeposit;

    private Integer orderAmount;

    private Integer orderStatus;

    private Date createTime;

    private Date updateTime;
}