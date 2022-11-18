package com.javaclimb.xshopping.mapper;

import com.javaclimb.xshopping.entity.UserInfo;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 用户相关mapper
 */
@Repository
public interface UserInfoMapper extends Mapper<UserInfo> {
    /**
     * 用户名查询用户信息
     * @param name
     * @return
     */
    List<UserInfo> findByName(@Param("name") String name);

    /**
     * 用户唯一判定
     */
    int checkRepeat(@Param("column")String column,@Param("value")String value);

}





























