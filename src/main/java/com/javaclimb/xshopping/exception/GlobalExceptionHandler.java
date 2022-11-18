package com.javaclimb.xshopping.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.javaclimb.xshopping.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *    controller 全局异常拦截
 */

@ControllerAdvice(basePackages = "com/javaclimb/xshopping/controller")
public class GlobalExceptionHandler {
    private static final Log log= LogFactory.get();


    @ExceptionHandler(Exception.class)
    @ResponseBody//返回json串
    public Result error(HttpServletRequest request,Exception e){
        log.error("异常信息",e);
        return Result.error();
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result customerror(HttpServletRequest request,CustomException e){
        log.error("异常信息",e.getMsg());
        return Result.error(e.getCode(),e.getMsg());
    }

}






































