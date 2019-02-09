package org.kwin.management.exception;

import org.kwin.management.enums.ResultEnum;

public class SysException extends RuntimeException {
    private Integer code;
    private String msg;

    public SysException(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }
}
