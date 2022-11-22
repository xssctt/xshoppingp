package com.javaclimb.xshopping.controller;

import com.github.pagehelper.PageInfo;
import com.javaclimb.xshopping.common.Result;
import com.javaclimb.xshopping.entity.CartInfo;
import com.javaclimb.xshopping.entity.GoodsInfo;
import com.javaclimb.xshopping.service.CartInfoSevice;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 购物车控制
 */
@RestController
@RequestMapping(value = "/cartInfo")
public class CartInfoController {


    @Resource
    CartInfoSevice cartInfoSevice;

    /**
     * 添加购物车
     */
    @PostMapping
    public Result<CartInfo> add(@RequestBody CartInfo cartInfo){
        return Result.success(cartInfoSevice.add(cartInfo));
    }

    /**
     * 查询某用户购物车（不分页）
     */
    @GetMapping
    public Result<List<GoodsInfo>> findAll(@RequestParam Long userId){
        return Result.success(cartInfoSevice.findAll(userId));
    }

    /**
     *
     * @param userId
     * @param goodsId
     * @return
     */

    @DeleteMapping("/goods/{userId}/{goodsId}")
    public Result deleteGoods(@PathVariable Long userId,@PathVariable Long goodsId){
        cartInfoSevice.deleteGoods(userId,goodsId);
        return Result.success();
    }

    /**
     * 查询购物车  分页
     * required = false 不是必须传值
     */
    @GetMapping("/page")
    public Result<PageInfo<CartInfo>> findPage(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                                HttpServletRequest request){
        return Result.success(cartInfoSevice.findPageDetails(pageNum,pageSize,request));
    }

    /**
     * 根据id删除购物车
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        cartInfoSevice.delete(id);
        return Result.success();
    }

}
