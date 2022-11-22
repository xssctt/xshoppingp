package com.javaclimb.xshopping.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaclimb.xshopping.common.Common;
import com.javaclimb.xshopping.entity.CartInfo;
import com.javaclimb.xshopping.entity.GoodsInfo;
import com.javaclimb.xshopping.entity.UserInfo;
import com.javaclimb.xshopping.exception.CustomException;
import com.javaclimb.xshopping.mapper.CartInfoMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 购物车 方法1
 */
@Service
public class CartInfoSevice {


    @Resource
    private CartInfoMapper cartInfoMapper;

    @Resource
    private GoodsInfoService goodsInfoService;


    /**
     * add
     */
    public CartInfo add(CartInfo detailInfo){
        Long userid=detailInfo.getUserid();
        Long goodsid=detailInfo.getGoodsid();

        //查询购物车有没有数据 有更新 没有添加
        //查询流
        //select * from cart_info where( ( userId = userId and goodsId = goodsId ) )
        Example example=new Example(CartInfo.class);
        example.createCriteria()
                .andEqualTo("userid",userid)
                .andEqualTo("goodsid",goodsid);
        List<CartInfo> infos=cartInfoMapper.selectByExample(example);

        //CollectionUtil 封装的工具类
        if (CollectionUtil.isEmpty(infos)){
            //没有添加
            detailInfo.setCreatetime(DateUtil.formatDateTime(new Date()));
            cartInfoMapper.insertSelective(detailInfo);
        }else {
            //有更新
            CartInfo cartInfo=infos.get(0);
            cartInfo.setCount(cartInfo.getCount()+detailInfo.getCount());
            cartInfoMapper.updateByPrimaryKeySelective(cartInfo);
        }
        return detailInfo;
    }

    /**
     * 根据用户id 获取购物车列表 获得购物车商品的数量
     * @param userId
     * @return
     */

    public List<GoodsInfo> findAll(Long userId){

        List<CartInfo> cartInfoList=cartInfoMapper.findCartByUserId(userId);
        List<GoodsInfo> goodsList=new ArrayList<>();

        for (CartInfo cartInfo:cartInfoList){

            long goodsId=cartInfo.getGoodsid();
            GoodsInfo goodsInfo=goodsInfoService.findById(goodsId);

            if (goodsInfo !=null){
                //注意 这里是用户加入购物车的数量
                goodsInfo.setCount(cartInfo.getCount());
                //这里的id是购物车的商品id
                goodsInfo.setId(cartInfo.getGoodsid());

                goodsList.add(goodsInfo);
            }
        }
        return goodsList;
    }

    /**
     * 删除 购物车对应商品
     * @param userId
     * @param goodsId
     */

    public void deleteGoods(Long userId,Long goodsId){
        cartInfoMapper.deleteGoods(userId,goodsId);
    }

    /**
     *
     * @param userId
     */
    public void empty(Long userId){
        cartInfoMapper.deleteByUserId(userId);
    }



    /**
     *
     *翻页查询购物车列表
     */
    public PageInfo<CartInfo> findPageDetails(Integer pageNum, Integer pageSize, HttpServletRequest request){
        UserInfo user=(UserInfo) request.getSession().getAttribute(Common.USER_INFO);
        if (user == null){
            throw new CustomException("1001","session已失效请重新登录");
        }
        Integer level=user.getLevel();
        PageHelper.startPage(pageNum,pageSize);
        List<CartInfo> list;
        if (level == 1){
            list=cartInfoMapper.findAll();
        }else {
            list=cartInfoMapper.findCartByUserId(user.getId());
        }
        return PageInfo.of(list);
    }


    /**
     * 根据id删除购物车
     * @param id
     * @return
     */
    public void delete(Long id) {
        cartInfoMapper.deleteByPrimaryKey(id);
    }
}













