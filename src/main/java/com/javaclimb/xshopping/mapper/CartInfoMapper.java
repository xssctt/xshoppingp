package com.javaclimb.xshopping.mapper;

import com.javaclimb.xshopping.entity.CartInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CartInfoMapper extends Mapper<CartInfo> {

    /**
     * 根据用户id 获取购物车列表
     * @param userId
     * @return
     */
    List<CartInfo> findCartByUserId(Long userId);


    /**
     *
     * @param userId
     * @param goodsId
     * @return
     */
    @Delete("delete from cart_info where userId=#{userId} and goodsId=#{goodsId}")
    int deleteGoods(@Param("userId")Long userId,@Param("goodsId")Long goodsId);

    @Delete("delete from cart_info where userId=#{userId}")
    int deleteByUserId(@Param("userId") Long userId);
}