package org.kwin.management.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    STOCK_ERROR(0, "库存错误！"),
    PRODUCT_NOT_EXIST(1, "商品不存在！"),
    ORDER_STATUS_ERROR(2, "订单状态错误！"),;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
