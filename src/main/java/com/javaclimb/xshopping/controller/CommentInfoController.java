package com.javaclimb.xshopping.controller;

import com.github.pagehelper.PageInfo;
import com.javaclimb.xshopping.common.Result;
import com.javaclimb.xshopping.entity.CommentInfo;
import com.javaclimb.xshopping.service.CommentInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/commentInfo")
public class CommentInfoController {

    @Resource
    private CommentInfoService commentInfoService;

    /**
     * 新增
     * @param commentInfo
     * @return
     */
    @PostMapping
    public Result<CommentInfo> add(@RequestBody CommentInfo commentInfo){
        commentInfoService.add(commentInfo);
        return Result.success(commentInfo);
    }

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page/{name}")
    public Result<PageInfo<CommentInfo>> findpage(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                                  @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                                  @PathVariable String name){
        return Result.success(commentInfoService.findByConten(pageNum,pageSize,name));

    }

    /**
     * 跟据id删除评论
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        commentInfoService.delete(id);
        return Result.success();
    }

    /**
     * 根据商品id查询商品评论
     *
     */
    @GetMapping("/all/{goodsid}")
    public Result<List<CommentInfo>> findByGoodsid(@PathVariable Long goodsid){
        return Result.success(commentInfoService.findByGoodsid(goodsid));
    }


}
