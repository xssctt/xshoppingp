package com.javaclimb.xshopping.controller;

import com.github.pagehelper.PageInfo;
import com.javaclimb.xshopping.common.Result;
import com.javaclimb.xshopping.common.ResultCode;
import com.javaclimb.xshopping.entity.GoodsInfo;
import com.javaclimb.xshopping.entity.OrderInfo;
import com.javaclimb.xshopping.exception.CustomException;
import com.javaclimb.xshopping.service.OrderInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单控制器
 */
@RestController
@RequestMapping(value = "/orderInfo")
public class OrderInfoController {

    @Resource
    OrderInfoService orderInfoService;

    /**
     * 下单
     */
    @PostMapping
    public Result<OrderInfo> add(@RequestBody OrderInfo orderInfo){

        Long userId=orderInfo.getUserid();
        List<GoodsInfo> goodsList=orderInfo.getGoodsList();
        if (userId == null || goodsList == null || goodsList.size() == 0){
            throw new CustomException(ResultCode.PARAM_ERROR);
        }

        orderInfo.setState("待付款");
        return Result.success(orderInfoService.add(orderInfo));

    }

    /**
     * 查询所有信息  分页
     * required = false 不是必须传值
     */
    @GetMapping("/page/front")
    public Result<PageInfo<OrderInfo>> findFrontPage(@RequestParam(required = false) Long userId,
                                                     @RequestParam(required = false) String state,
                                                     @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                                     @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        return Result.success(orderInfoService.findFrontPages(userId,state,pageNum,pageSize));
    }
}


















