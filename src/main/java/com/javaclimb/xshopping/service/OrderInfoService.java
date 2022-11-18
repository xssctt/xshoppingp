package com.javaclimb.xshopping.service;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaclimb.xshopping.common.ResultCode;
import com.javaclimb.xshopping.entity.GoodsInfo;
import com.javaclimb.xshopping.entity.OrderGoodsRel;
import com.javaclimb.xshopping.entity.OrderInfo;
import com.javaclimb.xshopping.entity.UserInfo;
import com.javaclimb.xshopping.exception.CustomException;
import com.javaclimb.xshopping.mapper.OrderGoodsRelMapper;
import com.javaclimb.xshopping.mapper.OrderInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderInfoService {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private GoodsInfoService goodsInfoService;

    @Resource
    private CartInfoSevice cartInfoSevice;

    @Resource
    private OrderGoodsRelMapper orderGoodsRelMapper;




    /**
     * 下单
     *
     * 前端把订单商品列表返回后台orderInfo
     * orderInfo 存在用户id  后台获取用户信息
     * 修饰订单id 保存
     */
    //spring管理sql事务 出错回滚数据
    @Transactional
    public OrderInfo add(OrderInfo orderInfo){
        //1 生成订单信息 用户信息 放到orderOInfo
        Long userId=orderInfo.getUserid();
        //d订单id 用户id+当前时间+流水号
        String orderId=userId+ DateUtil.format(new Date(),"yyyyMMddHHmm")+ RandomUtil.randomNumbers(4);
        orderInfo.setOrderid(orderId);
        //用户相关
        UserInfo userInfo=userInfoService.findById(userId);  //用户查到的数据放到订单信息表
        orderInfo.setLinkaddress(userInfo.getAddress());    //地址
        orderInfo.setLinkman(userInfo.getNickname());       //昵称
        orderInfo.setLinkphone(userInfo.getPhone());        //电话
        //2 保存订单表
        orderInfo.setCreatetime(DateUtil.formatDateTime(new Date()));  //订单创建时间
        orderInfoMapper.insertSelective(orderInfo);         //保存

        //
        List<OrderInfo> orderInfoList=orderInfoMapper.findByOrderId(orderId);



        /**
         * //3 查询订单商品列表 便利
         * goodsList
         * 从orderInfo获取商品列表
         * 获取各个商品id 在后台查询商品数量 商品库存 修改
         * 查询销量 修改销量 sale+count
         * 修改关联表
         */
        List<GoodsInfo> goodsList=orderInfo.getGoodsList();

        for (GoodsInfo orderGoodsVO : goodsList){

            Long goodsId=orderGoodsVO.getId();
            //goodsDetail   goodsInfoService  数据库
            GoodsInfo goodsDetail=goodsInfoService.findById(goodsId);

            if (goodsDetail == null){
                continue;
            }
            Integer orderCount=orderGoodsVO.getCount() == null ? 0 : orderGoodsVO.getCount(); //order 购买
            Integer goodsCount=goodsDetail.getCount() == null ? 0 :goodsDetail.getCount();   // 库存

        //4 修改库存
        if (orderCount>goodsCount){
            throw new CustomException(ResultCode.ORDER_PAY_ERROR);
        }
        goodsDetail.setCount(goodsCount - orderCount);
            //5 增加销量
            int sales=goodsDetail.getSales() == null ? 0 :goodsDetail.getSales();
            goodsDetail.setSales(sales+orderCount);
            goodsInfoService.update(goodsDetail);


            //6 商品订单关联表 将增加关系
            OrderGoodsRel orderGoodsRel=new OrderGoodsRel();
            orderGoodsRel.setOrderid(orderInfoList.get(0).getId());
            orderGoodsRel.setGoodsid(goodsId);
            orderGoodsRel.setCount(orderCount);
            orderGoodsRelMapper.insert(orderGoodsRel);
    }

        //7 清除购物车
        cartInfoSevice.empty(userId);

        return orderInfo;
    }

    /**
     * 根据终端用户获取 订单 状态
     *
     */
    public PageInfo<OrderInfo> findFrontPages(Long userId,String state,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<OrderInfo> orderInfos;
        //
        if (userId ==null){
            orderInfos=new ArrayList<>();
        }else {
            orderInfos=orderInfoMapper.findByEndUserId(userId,state);
        }
        for (OrderInfo orderInfo:orderInfos){
            packOrder(orderInfo);
        }



        return PageInfo.of(orderInfos);
    }

    /**
     *包装订单的用户和商品信息
     * order
     * userid  --> userinfo
     * id -----> order_goods_rel : orderid(order.id)  goodsid  count
     * goodsid --> goodsinfo
     * count
     */
    private void packOrder(OrderInfo orderInfo){

        //用户信息 userinfo  orderInfo.getUserid()不会空 在添加购物车已判断是否空
       orderInfo.setUserInfo(userInfoService.findById(orderInfo.getUserid())) ;

       //商品信息
        Long orderId=orderInfo.getId();
        //rel  id  goodsid count  用户买的什么商品id 买了多少件
        List<OrderGoodsRel> rels= orderGoodsRelMapper.findByOrderid(orderId);
        List<GoodsInfo> goodsInfoList=new ArrayList<>();

        for (OrderGoodsRel rel: rels){
            //获取 用户购买 商品的信息
            GoodsInfo goodsInfo=goodsInfoService.findById(rel.getGoodsid());
            if (goodsInfo != null){
                //rel.getCount()  用户买的什么商品id 买了多少件
                goodsInfo.setCount(rel.getCount());
                goodsInfoList.add(goodsInfo);
            }
        }
        orderInfo.setGoodsList(goodsInfoList);
        //orderInfo  userInfo +  goodsList
    }

    /**
     * 改变订单状态
     */
    public void changeState(Long id,String state){
        OrderInfo orderInfo=orderInfoMapper.finById(id);
    }


}



























