package org.kwin.management.form;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class OrderDetailForm {

    private String detailId;

    private String orderId;

    @Min(value = 1,message = "请至少添加一个商品")
    private Integer productQuantity;
}
