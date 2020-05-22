package com.sunshine.baseService.exception;

import com.sunshine.common.utils.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@ApiModel(value = "自定义异常对象", description = "自定义异常信息类")
@Data
@AllArgsConstructor  //有参构造
@NoArgsConstructor   //无参构造
public class GuliException extends RuntimeException {
    @ApiModelProperty(value = "异常码")
    private Integer code;
    @ApiModelProperty(value = "异常信息")
    private String msg;

    public GuliException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    public GuliException(ResultCode code){
        super(code.getMsg());
        this.code = code.getCode();
        this.msg = code.getMsg();
    }
    @Override
    public String toString() {
        return "GuliException{" +
                "message=" + this.getMsg() +
                ", code=" + code +
                '}';
    }
}
