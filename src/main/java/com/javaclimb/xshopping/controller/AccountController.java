package com.javaclimb.xshopping.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
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
        //
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



    /**
     * 修改密码
     */

    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody UserInfo userInfo, HttpServletRequest request){
        Object user1=request.getSession().getAttribute(Common.USER_INFO);
        if (user1 ==null){
            return  Result.error("401","未登录");
        }
        UserInfo user=(UserInfo)user1;
        //数据库
        String oldPassword= SecureUtil.md5(userInfo.getPassword());
        //前端缓存
        String password=user.getPassword();
        if (!oldPassword.equals(password)){
            return Result.error(ResultCode.USER_ACCOUNT_ERROR.code,ResultCode.USER_ACCOUNT_ERROR.msg);
        }
        user.setPassword(SecureUtil.md5(userInfo.getNewPassword()));
        userInfoService.update(user);
        //清空session
        request.getSession().setAttribute(Common.USER_INFO,null);
        return Result.success(user);
    }

}























