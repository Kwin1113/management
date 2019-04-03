package org.kwin.management.common.VO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.kwin.management.common.enums.ResultEnum;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultResponse<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    private ResultResponse(int code) {
        this.code = code;
    }

    private ResultResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResultResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    private ResultResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.code == ResultEnum.SUCCESS.getCode();
    }

    public static <T> ResultResponse<T> createBySuccess() {
        return new ResultResponse<T>(ResultEnum.SUCCESS.getCode());
    }

    public static <T> ResultResponse<T> createBySuccessMessage(String msg) {
        return new ResultResponse<T>(ResultEnum.SUCCESS.getCode(), msg);
    }

    public static <T> ResultResponse<T> createBySuccess(T data) {
        return new ResultResponse<T>(ResultEnum.SUCCESS.getCode(), data);
    }

    public static <T> ResultResponse<T> createBySuccess(String msg, T data) {
        return new ResultResponse<T>(ResultEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> ResultResponse<T> createByError() {
        return new ResultResponse<T>(ResultEnum.ERROR.getCode());
    }

    public static <T> ResultResponse<T> createByErrorMessage(String msg) {
        return new ResultResponse<T>(ResultEnum.ERROR.getCode(), msg);
    }
}
