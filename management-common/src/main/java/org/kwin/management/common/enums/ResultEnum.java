package org.kwin.management.common.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),
    ERROR(1, "失败"),
    PRODUCT_NOT_EXIST(2, "商品不存在！"),
    ORDER_STATUS_ERROR(3, "订单状态错误！"),
    PRODUCT_EXIST(4, "商品已存在！"),
    STOCK_ERROR(5, "库存错误！"),
    FORM_ERROR(6, "表单验证错误"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
