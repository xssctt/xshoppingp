package com.javaclimb.xshopping.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaclimb.xshopping.entity.AdvertiserInfo;
import com.javaclimb.xshopping.mapper.AdvertiserInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 关于公告的service
 */
@Service
public class AdvertiserInfoService {

    /**
     * 牛皮 直接调用 mapper
     */
    @Resource
    private AdvertiserInfoMapper advertiserInfoMapper;



    /**
     *分页查询列表
     *pagenum  第几页 pagesize 每页多少数据
     */
    public PageInfo<AdvertiserInfo> findPage(Integer pageNum,Integer pageSize,String name){
        //封装
        PageHelper.startPage(pageNum,pageSize);
        List<AdvertiserInfo> list=advertiserInfoMapper.findByName(name);
        return PageInfo.of(list);
    }

    /**
     * 新增公告
     */

    public AdvertiserInfo add(AdvertiserInfo advertiserInfo){
        /**
         *new Date() 获得当前时间  SimpleDateFormat("yyyy-MM-dd HH:mm:ss") 转化指定字符串
         */
        advertiserInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        advertiserInfoMapper.insertSelective(advertiserInfo);
        return advertiserInfo;
    }

    /**
     *
     * 修改公告信息
     */
    public void  update(AdvertiserInfo advertiserInfo){
        advertiserInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        advertiserInfoMapper.updateByPrimaryKeySelective(advertiserInfo);
    }

    /**
     *  根据id删除公告信息
     */
    public void delete(long id){
        advertiserInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     *根据公告id查询公告
     */
    public AdvertiserInfo findById(long id){
        return advertiserInfoMapper.selectByPrimaryKey(id);
    }
}



















