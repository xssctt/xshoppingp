package com.javaclimb.xshopping.mapper;

import com.javaclimb.xshopping.entity.TypeInfo;
import com.javaclimb.xshopping.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 类别信息相关mapper
 */

@Repository
public interface TypeInfoMapper extends Mapper<TypeInfo> {

    /**
     * 用户名查询类别信息
     * @param name
     * @return
     */
    List<TypeInfo> findByName(@Param("name") String name);



}