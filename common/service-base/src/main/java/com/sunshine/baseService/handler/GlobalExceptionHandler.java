package com.sunshine.baseService.handler;

import com.sunshine.baseService.exception.GuliException;
import com.sunshine.common.utils.R;
import com.sunshine.common.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        return R.error(e.getMessage());
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R errorArithmeticException(ArithmeticException e){
        return R.error(e.getMessage());
    }
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R errorGuliException(ResultCode code){
        return R.error(code);
    }
}
