package org.kwin.management.exception;

import lombok.Getter;
import org.kwin.management.enums.ResultEnum;

@Getter
public class SysException extends RuntimeException {
    private Integer code;
    private String msg;

    public SysException(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }
}
