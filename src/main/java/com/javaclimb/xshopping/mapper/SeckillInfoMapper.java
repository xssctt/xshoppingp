package com.javaclimb.xshopping.mapper;

import com.javaclimb.xshopping.entity.SeckillInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author lenovo
 */
public interface SeckillInfoMapper extends Mapper<SeckillInfo> {


    List<SeckillInfo> findByName(@Param("name") String name, @Param("id") Long id);


    List<SeckillInfo> findlistByid();
}