package com.javaclimb.xshopping.controller;


import com.github.pagehelper.PageInfo;
import com.javaclimb.xshopping.common.Result;
import com.javaclimb.xshopping.entity.GoodsInfo;
import com.javaclimb.xshopping.service.GoodsInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 商品增删改查
 */

@RestController
@RequestMapping(value = "/goodsInfo")
public class GoodsInfoController {

    @Resource
    private GoodsInfoService goodsInfoService;

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
    public Result<PageInfo<GoodsInfo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                            @PathVariable String name){
        return Result.success(goodsInfoService.findPage(pageNum,pageSize,name));
    }

    /**
     * 新增商品
     */
    @PostMapping()
    public Result<GoodsInfo> add(@RequestBody GoodsInfo goodsInfo){
        goodsInfoService.add(goodsInfo);
        return Result.success(goodsInfo);
    }

    /**
     * 修改商品
     */
    @PutMapping()
    public Result<GoodsInfo> update(@RequestBody GoodsInfo goodsInfo){
        goodsInfoService.update(goodsInfo);
        return Result.success(goodsInfo);
    }

    /**
     * 删除商品
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id){
        goodsInfoService.delete(id);
        return Result.success();
    }
    /**
     * 根据id查询商品
     */
    @GetMapping("/{id}")
    public Result detail(@PathVariable long id){
        return Result.success(goodsInfoService.findById(id));
    }

    /**
     *查询是否推荐商品
     * @param pageNum
     * @param pageSize
     * @return
     */

    @GetMapping("/findRecommendGoods")
    public Result<PageInfo<GoodsInfo>> findRecommendGoods(@RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "100") Integer pageSize){
        return Result.success(goodsInfoService.findRecommendGoods(pageNum,pageSize));
    }


    /**
     * 获取寻热卖商品
     */
    @GetMapping("/findHotSalesGoods")
    public Result<PageInfo<GoodsInfo>> findHotSalesGoods(@RequestParam(defaultValue = "1") Integer pageNum,
                                                          @RequestParam(defaultValue = "10") Integer pageSize){
        return Result.success(goodsInfoService.findHotSalesGoods(pageNum,pageSize));
    }


}












