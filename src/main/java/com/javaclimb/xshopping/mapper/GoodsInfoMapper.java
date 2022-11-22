package com.javaclimb.xshopping.mapper;

import com.javaclimb.xshopping.entity.GoodsInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 商品相关mapper
 */

@Repository
public interface GoodsInfoMapper extends Mapper<GoodsInfo> {

    /**
     *模糊查询
     * @param name
     * @return
     */
    List<GoodsInfo> findByName(@Param("name") String name,@Param("id") Long id);


    /**
     *查询是否推荐
     */
    @Select("select * from goods_info where recommend='是' ")
    List<GoodsInfo> findRecommendGoods();


    /**
     * 获取寻热卖商品
     */
    @Select("select * from goods_info order by sales desc")
    List<GoodsInfo> findHotSalesGoods();

    /**
     * 根据类型查询
     */
    @Select("select * from goods_info where typeId =#{typeId}")
    List<GoodsInfo> findByType(@Param("typeId") Integer typeId);



}












