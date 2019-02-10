package org.kwin.management.enums;

import lombok.Getter;

@Getter
public enum ProductDirectionEnum implements CodeEnum {
    LEFT_INNER(1,"左内开"),
    LEFT_OUTER(2,"左外开"),
    RIGHT_INNER(3,"右内开"),
    RIGHT_OUTER(4,"右外开"),;
    private Integer code;

    private String message;

    ProductDirectionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
