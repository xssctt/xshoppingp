package com.javaclimb.xshopping.controller;


import cn.hutool.core.util.StrUtil;
import com.javaclimb.xshopping.common.Common;
import com.javaclimb.xshopping.common.Result;
import com.javaclimb.xshopping.common.ResultCode;
import com.javaclimb.xshopping.entity.UserInfo;
import com.javaclimb.xshopping.exception.CustomException;
import com.javaclimb.xshopping.service.UserInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@ResponseBody
public class AccountController {

    @Resource
    private UserInfoService userInfoService;
    /**
     *
     */
    @PostMapping("/login")
    public Result<UserInfo> login(@RequestBody UserInfo userInfo, HttpServletRequest request){
        //判空
        if (StrUtil.isBlank(userInfo.getName())|| StrUtil.isBlank(userInfo.getPassword())  ){
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        // 从数据库查询账户密码  并放入session

        UserInfo login= userInfoService.login(userInfo.getName(),userInfo.getPassword());
        //session 获得
        HttpSession session=request.getSession();
        session.setAttribute(Common.USER_INFO,login);
        //设置时效
        session.setMaxInactiveInterval(60*60*24);


        return Result.success(login);
//        return Result.success();
    }






    /**
     * 重置密码
     */

    @PostMapping("/resetPassword")
    public Result<UserInfo> resetPassword(@RequestBody UserInfo userInfo, HttpServletRequest request){
        return Result.success(userInfoService.resetPassword(userInfo.getName()));
    }
    /**
     * 登出
     */
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request){
        request.getSession().setAttribute(Common.USER_INFO,null);
        return Result.success();
    }

    /**
     * 小程序登录
     */
    @PostMapping("/register")
    public  Result<UserInfo> register(@RequestBody UserInfo userInfo,HttpServletRequest request){
        if (StrUtil.isBlank(userInfo.getName()) || StrUtil.isBlank(userInfo.getPassword())){
            throw new CustomException(ResultCode.PARAM_ERROR);
        }

        UserInfo register=userInfoService.add(userInfo);
        HttpSession session=request.getSession();
        session.setAttribute(Common.USER_INFO,register);
        //设置时效
         session.setMaxInactiveInterval(60*60*24);
         return Result.success(register);
    }

    /**
     *小程序用户是否登录
     */
    @GetMapping("/auth")
    public Result getAuth(HttpServletRequest request){

        Object user=request.getSession().getAttribute(Common.USER_INFO);
        if (user ==null){
            return  Result.error("401","未登录");
        }

        return Result.success(user);
    }

}























