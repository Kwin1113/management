package org.kwin.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.kwin.management.enums.OrderStatusEnum;
import org.kwin.management.utils.EnumUtil;

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

    @JsonIgnore
    public OrderStatusEnum getOrderStatusByCode() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }
}