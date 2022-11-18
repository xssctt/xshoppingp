package com.javaclimb.xshopping.controller;


import com.github.pagehelper.PageInfo;
import com.javaclimb.xshopping.common.Result;
import com.javaclimb.xshopping.entity.TypeInfo;
import com.javaclimb.xshopping.service.TypeInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 类别增删改查
 */

@RestController
@RequestMapping(value = "/typeInfo")
public class TypeInfoController {

    @Resource
    private TypeInfoService typeInfoService;

    /**
     *
     * *类别分页查询列表
     * *pagenum  第几页 pagesize 每页多少数据
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     *
     * /page/{name}    @PathVariable String name  name传递值
     */

    @GetMapping("/page/{name}")
    public Result<PageInfo<TypeInfo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @PathVariable String name){
        return Result.success(typeInfoService.findPage(pageNum,pageSize,name));
    }

    /**
     * 新增类别
     */
    @PostMapping()
    public Result<TypeInfo> add(@RequestBody TypeInfo typeInfo){
        typeInfoService.add(typeInfo);
        return Result.success(typeInfo);
    }

    /**
     * 修改类别
     */
    @PutMapping()
    public Result<TypeInfo> update(@RequestBody TypeInfo typeInfo){
        typeInfoService.update(typeInfo);
        return Result.success(typeInfo);
    }

    /**
     * 删除类别
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id){
        typeInfoService.delete(id);
        return Result.success();
    }
    /**
     * 根据类别id查询类别
     */
    @GetMapping("/{id}")
    public Result detail(@PathVariable long id){
        return Result.success(typeInfoService.findById(id));
    }
}












