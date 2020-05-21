package com.yibu.web.config;

import com.wang.exception.WErrorException;
import com.yibu.web.dto.HttpResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wzq
 * @date 2020/5/21
 * @DESC 统一异常处理
 */
@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(WErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResult handlerWErrorException(WErrorException e){
        return HttpResult.errorMsg(e.getKey(),e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResult handlerWErrorException(Exception e){
        if (e instanceof WErrorException){
            return HttpResult.errorMsg(e.getLocalizedMessage(),e.getMessage());
        }
        return HttpResult.errorMsg(e.getLocalizedMessage(),e.getMessage());
    }

}
