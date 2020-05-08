package com.sunshine.baseService.handler;

import com.sunshine.baseService.exception.GuliException;
import com.sunshine.common.utils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        return R.error().message("进行了统一异常处理");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R errorArithmeticException(ArithmeticException e){
        return R.error().message("进行了特殊异常的处理");
    }
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R errorGuliException(GuliException e){
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}