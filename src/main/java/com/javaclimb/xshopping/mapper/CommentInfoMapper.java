package com.javaclimb.xshopping.mapper;

import com.javaclimb.xshopping.entity.CommentInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CommentInfoMapper extends Mapper<CommentInfo> {

    /**
     * 根据内容模糊查询
     */
    List<CommentInfo> findByConent(@Param("name") String name);

//    void delete(Long id);
    /**
     * 根据商品id查询商品评论
     *
     */

    List<CommentInfo> findByGoodsid(@Param("goodsid") Long goodsid);


    @Select("select count(*) from comment_info")
    Integer count();

}