package com.javaclimb.xshopping.mapper;

import com.javaclimb.xshopping.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


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
     * 根据id更新订单状态
     * @param id
     * @param state
     */
    @Update("update order_info set state =#{state} where id=#{id}")
    void updateState(@Param("id")Long id,@Param("state")String state);

    /**
     *删除订单
     * @param id
     */
    void deleteById(Long id);

    /**
     *总交易额
     */
    @Select("select sum(totalPrice) from order_info where state='完成' ")
    Double count();


    /**
     * 分类总销售
     */
    @Select("select SUM(a.count*b.price) as `price`,c.`name`\n" +
            "from order_goods_rel as a\n" +
            "left join goods_info as b on a.goodsId=b.id\n" +
            "left join type_info as c on b.typeId=c.id\n" +
            "GROUP BY b.typeId")
    List<Map<String,Object>> getTypePrice();

    /**
     * 分类总
     */
    @Select("select a.count as `count`,c.`name`\n" +
            "from order_goods_rel as a\n" +
            "left join goods_info as b on a.goodsId=b.id\n" +
            "left join type_info as c on b.typeId=c.id\n" +
            "GROUP BY b.typeId")
    List<Map<String,Object>> getTypeCount();
}