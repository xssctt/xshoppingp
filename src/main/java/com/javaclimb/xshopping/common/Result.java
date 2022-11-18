package com.javaclimb.xshopping.common;


/**
 * *  统一返回前端结果类
 * 泛型
 */

public class Result<T> {
//返回验证码信息
    private String code;
//返回页面中文信息
    private String msg;
//返回对象
    private T data;


    /**
     * 不带参数的返回  成功
     * @return
     */
    public static Result success(){
        Result result=new Result<>();
        result.setCode(ResultCode.SUCCESS.code);
        result.setMsg(ResultCode.SUCCESS.msg);
        return result;
    }

    /**
     * 带参数的返回 成功
     * @return
     */
    public static <T> Result <T> success(T data){
        Result<T> result=new Result<>(data);
        result.setCode(ResultCode.SUCCESS.code);
        result.setMsg(ResultCode.SUCCESS.msg);
        return result;
    }

    /**
     * 不带参数的返回  失败
     * @return
     */
    public static Result error(){
        Result result=new Result<>();
        result.setCode(ResultCode.ERROR.code);
        result.setMsg(ResultCode.ERROR.msg);
        return result;
    }


    /**
     * 带参数的返回  失败
     * @return
     */
    public static Result error(String code,String msg){
        Result result=new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }






    public Result(T data) {
        this.data = data;
    }

    public Result() {

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
