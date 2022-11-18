package com.javaclimb.xshopping.confiig;

import com.javaclimb.xshopping.common.Common;
import com.javaclimb.xshopping.entity.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局拦截器
 * 没有登陆 重定向
 */
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 所有后台请求拦截
     *
     * 返回true 继续执行请求 false 中断请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserInfo userInfo=(UserInfo) request.getSession().getAttribute(Common.USER_INFO);
        if (userInfo==null){
            //重定向到登录页面
            response.sendRedirect("/end/page/login.html");
            return false;
        }

       return true;
    }
}












