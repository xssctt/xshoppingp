package com.javaclimb.xshopping.controller;


import com.github.pagehelper.PageInfo;
import com.javaclimb.xshopping.common.Result;
import com.javaclimb.xshopping.entity.AdvertiserInfo;
import com.javaclimb.xshopping.service.AdvertiserInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 公告增删改查
 */

@RestController
@RequestMapping(value = "/advertiserInfo")
public class AdvertiserInfoController {

    @Resource
    private AdvertiserInfoService advertiserInfoService;

    /**
     *
     * *公告分页查询列表
     * *pagenum  第几页 pagesize 每页多少数据
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     *
     * /page/{name}    @PathVariable String name  name传递值
     */

    @GetMapping("/page/{name}")
    public Result<PageInfo<AdvertiserInfo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @PathVariable String name){
        return Result.success(advertiserInfoService.findPage(pageNum,pageSize,name));
    }

    /**
     * 新增公告
     */
    @PostMapping()
    public Result<AdvertiserInfo> add(@RequestBody AdvertiserInfo advertiserInfo){
        advertiserInfoService.add(advertiserInfo);
        return Result.success(advertiserInfo);
    }

    /**
     * 修改公告
     */
    @PutMapping()
    public Result<AdvertiserInfo> update(@RequestBody AdvertiserInfo advertiserInfo){
        advertiserInfoService.update(advertiserInfo);
        return Result.success(advertiserInfo);
    }

    /**
     * 删除公告
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id){
        advertiserInfoService.delete(id);
        return Result.success();
    }
    /**
     * 根据公告id查询公告
     */
    @GetMapping("/{id}")
    public Result detail(@PathVariable long id){
        return Result.success(advertiserInfoService.findById(id));
    }
}












