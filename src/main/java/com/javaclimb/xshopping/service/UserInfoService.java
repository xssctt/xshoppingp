package com.javaclimb.xshopping.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaclimb.xshopping.common.ResultCode;
import com.javaclimb.xshopping.entity.UserInfo;
import com.javaclimb.xshopping.exception.CustomException;
import com.javaclimb.xshopping.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 关于用户的service
 */
@Service
public class UserInfoService {

    /**
     * 牛皮 直接调用 mapper
     */
    @Resource
    private UserInfoMapper userInfoMapper;

    public UserInfo login(String name,String password){
        //从数据库查询用户
        List<UserInfo> list=userInfoMapper.findByName(name);
        //判断存在   USER_NOT_EXIST_ERROR("2003","用户未找到");   存在 不存在
        if (CollectionUtil.isEmpty(list)){
            throw new CustomException(ResultCode.USER_NOT_EXIST_ERROR);
        }
        //判断，密码  CustomException(ResultCode.USER_ACCOUNT_ERROR);
        //SecureUtil.md5()  加密
        if( !SecureUtil.md5(password).equals(list.get(0).getPassword())){
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }

        return list.get(0);
    }



    /**
     * 重置密码
     */
    public UserInfo resetPassword(String name){

        List<UserInfo> list=userInfoMapper.findByName(name);
        //判断存在   USER_NOT_EXIST_ERROR("2003","用户未找到");   存在 不存在
        if (CollectionUtil.isEmpty(list)){
            throw new CustomException(ResultCode.USER_NOT_EXIST_ERROR);
        }

        list.get(0).setPassword(SecureUtil.md5("123456"));
        //更新数据库
        userInfoMapper.updateByPrimaryKeySelective(list.get(0));
        return list.get(0);
    }


    /**
     *分页查询用户列表
     *pagenum  第几页 pagesize 每页多少数据
     */
    public PageInfo<UserInfo> findPage(Integer pageNum,Integer pageSize,String name){
        //封装
        PageHelper.startPage(pageNum,pageSize);
        List<UserInfo> list=userInfoMapper.findByName(name);
        return PageInfo.of(list);
    }

    /**
     * 根据Id中查找用户
     */
    public UserInfo findById(Long id){
        return userInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增用户
     */

    public UserInfo add(UserInfo userInfo){
        //判断用户存在 存在用户返回
        List<UserInfo> list=userInfoMapper.findByName(userInfo.getName());
        if (CollectionUtil.isNotEmpty(list)){
            return list.get(0);
        }

        if(StrUtil.isBlank(userInfo.getPassword())){
            userInfo.setPassword(SecureUtil.md5("123456"));
        }else {
            userInfo.setPassword(SecureUtil.md5(userInfo.getPassword()));
        }
        //设置  新增为买家
        userInfo.setLevel(3);
        userInfoMapper.insertSelective(userInfo);
        return userInfo;
    }

    /**
     *
     * 修改信息
     */
    public void  update(UserInfo userInfo){
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    /**
     *  根据id删除信息
     */
    public void delete(long id){
        userInfoMapper.deleteByPrimaryKey(id);
    }
}



















