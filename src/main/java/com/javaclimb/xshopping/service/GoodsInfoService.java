package com.javaclimb.xshopping.service;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaclimb.xshopping.entity.GoodsInfo;
import com.javaclimb.xshopping.mapper.GoodsInfoMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsInfoService {

    /**
     * 牛皮 直接调用 mapper
     */
    @Resource
    private GoodsInfoMapper goodsInfoMapper;




    /**
     *分页查询商品列表
     *pagenum  第几页 pagesize 每页多少数据
     */
    public PageInfo<GoodsInfo> findPage(Integer pageNum, Integer pageSize, String name){
        //封装
        PageHelper.startPage(pageNum,pageSize);
        List<GoodsInfo> list=goodsInfoMapper.findByName(name,null);
        return PageInfo.of(list);
    }

    /**
     * 新增商品
     */

    public GoodsInfo add(GoodsInfo goodsInfo){
        convertFileListToFilelds(goodsInfo);
        goodsInfoMapper.insertSelective(goodsInfo);
        return goodsInfo;
    }

    /**
     *
     * @param goodsInfo
     */
    private void convertFileListToFilelds(GoodsInfo goodsInfo){
        List<Long> fileList=goodsInfo.getFileList();
        if (!CollectionUtil.isEmpty(fileList)){
            goodsInfo.setFields(fileList.toString());
        }
    }


    /**
     *
     * 修改商品信息
     */
    public void  update(GoodsInfo goodsInfo){
        convertFileListToFilelds(goodsInfo);
        goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
    }

    /**
     *  根据id删除商品信息
     */
    public void delete(long id){
        goodsInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     *根据id查询商品信息
     */
    public GoodsInfo findById(long id){
        List<GoodsInfo> list=goodsInfoMapper.findByName(null,id);
        if (list==null || list.size()==0){
            return null;
        }
        return list.get(0);
    }



    /**
     * 查询商品是否是推荐商品
     */
    public PageInfo<GoodsInfo> findRecommendGoods(Integer pageNum, Integer pageSize){
        //封装
        PageHelper.startPage(pageNum,pageSize);
        List<GoodsInfo> list=goodsInfoMapper.findRecommendGoods();
        return PageInfo.of(list);
    }

    /**
     * 获取寻热卖商品
     */
    public PageInfo<GoodsInfo> findHotSalesGoods(Integer pageNum, Integer pageSize){
        //封装
        PageHelper.startPage(pageNum,pageSize);
        List<GoodsInfo> list=goodsInfoMapper.findHotSalesGoods();
        return PageInfo.of(list);
    }

    /**
     * 根据类型查询
     */

    public List<GoodsInfo> findByType(@Param("typeId") Integer typeId){
        return goodsInfoMapper.findByType(typeId);
    }


}



















