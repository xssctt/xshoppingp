package com.javaclimb.xshopping.mapper;

import com.javaclimb.xshopping.entity.OrderGoodsRel;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface OrderGoodsRelMapper extends Mapper<OrderGoodsRel> {
    /**
     * 根据订单Id获取商品列表
     */
    List<OrderGoodsRel> findByOrderid(Long orderId);

}