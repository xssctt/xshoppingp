package com.javaclimb.xshopping.mapper;

import com.javaclimb.xshopping.entity.AdvertiserInfo;
import com.javaclimb.xshopping.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 *
 */
@Repository
public interface AdvertiserInfoMapper extends Mapper<AdvertiserInfo> {
    /**
     *公告标题的模糊查询
     * @param name
     * @return
     */
    List<AdvertiserInfo> findByName(@Param("name") String name);
}