package com.tensquare.base.exception;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description: 统一异常处理类
 * @author:
 * @create: 2019-02-13 22:03
 **/
@RestControllerAdvice
public class BaseExceptionHandler {

   /* @ExceptionHandler(value = ArithmeticException.class)
    public Result exception(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }*/

    @ExceptionHandler(value = Exception.class)
    public Result exception1(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR+1,e.getMessage());
    }

}