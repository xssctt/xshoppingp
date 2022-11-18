package com.javaclimb.xshopping.service;


import com.javaclimb.xshopping.entity.NxSystemFileInfo;

import com.javaclimb.xshopping.mapper.NxSystemFileInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class NsSystemFileInfoService {

    /**
     * 牛皮 直接调用 mapper
     */
    @Resource
    private NxSystemFileInfoMapper nxSystemFileInfoMapper;



    /**
     * 新增文件
     */

    public NxSystemFileInfo add(NxSystemFileInfo nxSystemFileInfo){

        //设置
        nxSystemFileInfoMapper.insertSelective(nxSystemFileInfo);
        return nxSystemFileInfo;
    }



    /**
     *  根据id删除文件信息
     */
    public void delete(long id){
        nxSystemFileInfoMapper.deleteByPrimaryKey(id);
    }


    /**
     *文件id查询
     */
    public NxSystemFileInfo findById(long id){

        return nxSystemFileInfoMapper.selectByPrimaryKey(id);
    }
}



















