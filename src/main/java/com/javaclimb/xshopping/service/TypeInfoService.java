package com.javaclimb.xshopping.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaclimb.xshopping.common.ResultCode;
import com.javaclimb.xshopping.entity.TypeInfo;
import com.javaclimb.xshopping.exception.CustomException;
import com.javaclimb.xshopping.mapper.TypeInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeInfoService {

    /**
     * 牛皮 直接调用 mapper
     */
    @Resource
    private TypeInfoMapper typeInfoMapper;




    /**
     *分页查询类别列表
     *pagenum  第几页 pagesize 每页多少数据
     */
    public PageInfo<TypeInfo> findPage(Integer pageNum,Integer pageSize,String name){
        //封装
        PageHelper.startPage(pageNum,pageSize);
        List<TypeInfo> list=typeInfoMapper.findByName(name);
        return PageInfo.of(list);
    }

    /**
     * 新增类别
     */

    public TypeInfo add(TypeInfo typeInfo){

        //设置  新增为买家
        typeInfoMapper.insertSelective(typeInfo);
        return typeInfo;
    }

    /**
     *
     * 修改类别信息
     */
    public void  update(TypeInfo typeInfo){
        typeInfoMapper.updateByPrimaryKeySelective(typeInfo);
    }

    /**
     *  根据id删除类别信息
     */
    public void delete(long id){
        typeInfoMapper.deleteByPrimaryKey(id);
    }
    /**
     *根据id查询类别信息
     */
    public TypeInfo findById(long id){
        return typeInfoMapper.selectByPrimaryKey(id);
    }
}



















