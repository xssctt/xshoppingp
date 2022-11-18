package com.javaclimb.xshopping.exception;

import com.javaclimb.xshopping.common.Result;
import com.javaclimb.xshopping.common.ResultCode;

/**
 * 前端自定义异常返回
 */

public class CustomException extends RuntimeException{
private String code;
private String msg;

    public CustomException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CustomException(ResultCode resultCode) {
        this.code = resultCode.code;
        this.msg = resultCode.msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

















