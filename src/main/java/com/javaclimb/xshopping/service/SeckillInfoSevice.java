package com.javaclimb.xshopping.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaclimb.xshopping.entity.SeckillInfo;
import com.javaclimb.xshopping.mapper.GoodsInfoMapper;
import com.javaclimb.xshopping.mapper.SeckillInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SeckillInfoSevice {
    @Resource
    SeckillInfoMapper seckillInfoMapper;

    @Resource
    GoodsInfoService goodsInfoService;

    @Resource
    GoodsInfoMapper goodsInfoMapper;


    /**
     *分页查询商品列表
     *pagenum  第几页 pagesize 每页多少数据
     */
    public PageInfo<SeckillInfo> findPage(Integer pageNum, Integer pageSize, String name){
        //封装
        PageHelper.startPage(pageNum,pageSize);
        List<SeckillInfo> list=seckillInfoMapper.findByName(name,null);
        return PageInfo.of(list);
    }

    /**
     * 新增商品
     */

    public SeckillInfo  add(SeckillInfo seckillInfo){
         Long goodsid = Long.valueOf(seckillInfo.getName());
       List goodslist=seckillInfoMapper.findlistByid();
        if (goodslist.contains(goodsid)){
            return seckillInfo;
        }
        seckillInfo.setGoodsid(goodsid);
        seckillInfoMapper.insertSelective(seckillInfo);
        return seckillInfo;
    }
    /**
     *
     */
    /**
     *  根据id删除商品信息
     */
    public void delete(long id){
        seckillInfoMapper.deleteByPrimaryKey(id);
    }

}













