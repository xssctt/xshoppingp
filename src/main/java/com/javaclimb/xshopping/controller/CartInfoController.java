package com.javaclimb.xshopping.controller;

import com.javaclimb.xshopping.common.Result;
import com.javaclimb.xshopping.entity.CartInfo;
import com.javaclimb.xshopping.entity.GoodsInfo;
import com.javaclimb.xshopping.service.CartInfoSevice;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @DeleteMapping("/goods/{userId}/{goodsId}")
    public Result deleteGoods(@PathVariable Long userId,@PathVariable Long goodsId){
        cartInfoSevice.deleteGoods(userId,goodsId);
        return Result.success();
    }

}
