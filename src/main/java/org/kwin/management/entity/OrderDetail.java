package org.kwin.management.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDetail {
    private String detailId;

    private String orderId;

    private String productId;

    private String productType;

    private String productSize;

    private Integer productDirection;

    private Integer productPrice;

    private Integer productQuantity;

    private Date createTime;

    private Date updateTime;
}