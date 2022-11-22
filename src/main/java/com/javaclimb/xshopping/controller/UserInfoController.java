package com.javaclimb.xshopping.controller;


import com.github.pagehelper.PageInfo;
import com.javaclimb.xshopping.common.Result;
import com.javaclimb.xshopping.entity.UserInfo;
import com.javaclimb.xshopping.service.UserInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户增删改查
 */

@RestController
@RequestMapping(value = "/userInfo")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    /**
     *
     * *分页查询用户列表
     * *pagenum  第几页 pagesize 每页多少数据
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     *
     * /page/{name}    @PathVariable String name  name传递值
     */

    @GetMapping("/page/{name}")
    public Result<PageInfo<UserInfo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @PathVariable String name){
        return Result.success(userInfoService.findPage(pageNum,pageSize,name));
    }

    /**
     * 新增用户
     */
    @PostMapping()
    public Result<UserInfo> add(@RequestBody UserInfo userInfo){
        userInfoService.add(userInfo);
        return Result.success(userInfo);
    }

    /**
     * gengxin
     */
    @PutMapping()
    public Result<UserInfo> update(@RequestBody UserInfo userInfo){
        userInfoService.update(userInfo);
        return Result.success(userInfo);
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id){
        userInfoService.delete(id);
        return Result.success();
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/price/{id}")
    public Result<UserInfo> detail(@PathVariable Long id){
        UserInfo userInfo=userInfoService.findById(id);
        return Result.success(userInfo);
    }


}












