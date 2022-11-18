package com.javaclimb.xshopping.mapper;

import com.javaclimb.xshopping.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import javax.persistence.Table;
import java.util.List;


public interface OrderInfoMapper extends Mapper<OrderInfo> {

    /**
     * 根据订单Id获取 订单数据
     * @param orderId
     * @return
     */
    @Select("select * from order_info where orderId = #{orderId}")
    List<OrderInfo> findByOrderId(@Param("orderId") String orderId);

    /**
     *根据用户Id  订单状态  查询订单列表
     */
    List<OrderInfo> findByEndUserId(@Param("userId") Long useId,@Param("state") String state);

    /**
     *
     * @param id
     * @return
     */
    @Select("select * from order_info where id = #{id}")
    OrderInfo finById(@Param("id")Long id);

    /**
     *
     */
}