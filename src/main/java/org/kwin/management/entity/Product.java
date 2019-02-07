package org.kwin.management.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Product {
    private String productId;

    private String productType;

    private String productSize;

    private Integer productDirection;

    private Integer productPrice;

    private Integer productStock;

    private Date createTime;

    private Date updateTime;
}