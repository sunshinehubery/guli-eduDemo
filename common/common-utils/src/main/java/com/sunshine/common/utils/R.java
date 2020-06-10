package com.sunshine.common.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 返回的结果类
 * @author: sunshine
 * @date: 2020/5/7 21:51
 * @Version: 1.0
 **/
@Data
public class R<T> {
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private T data;

    private R(ResultCode resultCode, T data){
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
        this.data = data;
    }
    private R(int code, String msg, T data){
        this.code = code;
        this.message = msg;
        this.data = data;
    }
    private R(ResultCode resultCode){
        this(resultCode,null);
    }
    private R(int code, String msg){
        this.code = code;
        this.message = msg;
    }
    public static R ok(){return new R(ResultCode.SUCCESS);}
    public static R ok(ResultCode code){return new R(code);}
    public static <T> R<T> ok(T data){
        return new R<>(ResultCode.SUCCESS,data);
    }
    public static <T> R<T> ok(int code, String msg, T data){
        return new R<>(code, msg, data);
    }
    public static R error(ResultCode code){
        return new R(code);
    }
    public static R error(String msg){
        return new R(ResultCode.ERROR.getCode(),msg);
    }
    public static <T> R<T> error(ResultCode code, T data){
        return new R<>(code, data);
    }
}
