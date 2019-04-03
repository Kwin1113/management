package org.kwin.management.common.exception;

import lombok.Getter;
import org.kwin.management.common.enums.ResultEnum;

@Getter
public class SysException extends RuntimeException {
    private Integer code;
    private String msg;

    public SysException(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }
}
