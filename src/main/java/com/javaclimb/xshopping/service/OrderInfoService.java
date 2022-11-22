package com.javaclimb.xshopping.service;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaclimb.xshopping.common.Common;
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
import javax.servlet.http.HttpServletRequest;
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
        // 用户查到的数据放到订单信息表
        UserInfo userInfo=userInfoService.findById(userId);
        //地址
        orderInfo.setLinkaddress(userInfo.getAddress());
        //昵称
        orderInfo.setLinkman(userInfo.getNickname());
        //电话
        orderInfo.setLinkphone(userInfo.getPhone());
        //2 保存订单表//订单创建时间
        orderInfo.setCreatetime(DateUtil.formatDateTime(new Date()));
        //保存
        orderInfoMapper.insertSelective(orderInfo);

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
            //order 购买
            Integer orderCount=orderGoodsVO.getCount() == null ? 0 : orderGoodsVO.getCount();
            // 库存
            Integer goodsCount=goodsDetail.getCount() == null ? 0 :goodsDetail.getCount();

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
     *
     * 包装 把 用户信息user info  商品信息 goods info 查询到并放入orderinfo
     *
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
     * @param id
     * @param state
     */
    public void changeState(Long id,String state){
        OrderInfo order=orderInfoMapper.finById(id);
        Long userId=order.getUserid();
        UserInfo user=userInfoService.findById(userId);

        if (state.equals("待发货")){
            //校验余额
            Double account=user.getAccount();
            Double totalPrice=order.getTotalprice();

            if ((account < totalPrice)){
                throw new CustomException("-1","账户余额不足");
            }
            user.setAccount(user.getAccount() - order.getTotalprice());
            //修改用户余额
            userInfoService.update(user);

        }

        if (state.equals("已退货")){
            //校验余额
            Double account=user.getAccount();
            Double totalPrice=order.getTotalprice();
            user.setAccount(user.getAccount() + order.getTotalprice());
            //修改用户余额
            userInfoService.update(user);
        }
        //更新订单的状态
        orderInfoMapper.updateState(id,state);
    }

    /**
     *后台 查看订单列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param request
     * @return
     */
    public PageInfo<OrderInfo> findPage(Long userId, Integer pageNum, Integer pageSize, HttpServletRequest request){
        //
        UserInfo user=(UserInfo) request.getSession().getAttribute(Common.USER_INFO);
        if (user == null){
            throw new CustomException("1001","session已失效，请重新登录");

        }
        //
        Integer level=user.getLevel();
        PageHelper.startPage(pageNum,pageSize);
        List<OrderInfo> orderInfos;
        //
        if (1 == level){
            orderInfos=orderInfoMapper.selectAll();
        }else if(userId!=null){
            orderInfos=orderInfoMapper.findByEndUserId(userId,null);
        }else {
            orderInfos=new ArrayList<>();
        }
        for (OrderInfo orderInfo: orderInfos){
            packOrder(orderInfo);
        }
        return PageInfo.of(orderInfos);
    }





    /**
     * 删除订单
     * @param id
     * Transactional
     */
    @Transactional
    public void delete(Long id) {
        orderInfoMapper.deleteById(id);
        orderGoodsRelMapper.deleteByOrderId(id);
    }



    /**
     * 根据id查询订单信息
     *
     */
    public OrderInfo findById(Long id) {
      OrderInfo orderInfo= orderInfoMapper.selectByPrimaryKey(id);
      packOrder(orderInfo);
      return orderInfo;
    }
}



























