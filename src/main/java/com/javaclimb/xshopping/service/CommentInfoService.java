package com.javaclimb.xshopping.service;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaclimb.xshopping.entity.CommentInfo;
import com.javaclimb.xshopping.mapper.CommentInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 商品评价服务类
 */
@Service
public class CommentInfoService {
    @Resource
    CommentInfoMapper commentInfoMapper;

    /**
     * add comment
     */
    public CommentInfo add(CommentInfo commentInfo) {
        commentInfo.setCreatetime(DateUtil.formatDateTime(new Date()));
        commentInfoMapper.insertSelective(commentInfo);
        return commentInfo;
    }

    /**
     * 根据内容模糊查询
     */
    public PageInfo<CommentInfo> findByConten(Integer pageNum,
                                              Integer pageSize,
                                              String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommentInfo> list = commentInfoMapper.findByConent(name);
        return PageInfo.of(list);

    }

    /**
     * 删除评论
     */
    public void delete(Long id) {
        commentInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据商品id查询商品评论
     */
    public List<CommentInfo> findByGoodsid(Long goodsid) {
        return commentInfoMapper.findByGoodsid(goodsid);
    }


    public Integer count(){
        return commentInfoMapper.count();
}

}

