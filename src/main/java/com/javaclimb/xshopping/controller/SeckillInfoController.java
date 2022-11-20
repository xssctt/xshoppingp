package com.javaclimb.xshopping.controller;


import com.github.pagehelper.PageInfo;
import com.javaclimb.xshopping.common.Result;
import com.javaclimb.xshopping.entity.SeckillInfo;
import com.javaclimb.xshopping.service.SeckillInfoSevice;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/seckillInfo")
public class SeckillInfoController {
    @Resource
    SeckillInfoSevice seckillInfoSevice;


    @GetMapping("/page/{name}")
    public Result<PageInfo<SeckillInfo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                              @RequestParam(defaultValue = "10") Integer pageSize,
                                              @PathVariable String name){
        return Result.success(seckillInfoSevice.findPage(pageNum,pageSize,name));
    }


    /**
     * 新增商品
     */
    @PostMapping()
    public Result<SeckillInfo> add(@RequestBody SeckillInfo seckillInfo){
        seckillInfoSevice.add(seckillInfo);
        return Result.success(seckillInfo);
    }

    /**
     * 删除商品
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id){
        seckillInfoSevice.delete(id);
        return Result.success();
    }

}
