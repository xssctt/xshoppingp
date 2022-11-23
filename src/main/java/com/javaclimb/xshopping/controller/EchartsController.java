package com.javaclimb.xshopping.controller;


import cn.hutool.json.JSONObject;
import com.javaclimb.xshopping.common.Result;
import com.javaclimb.xshopping.service.CommentInfoService;
import com.javaclimb.xshopping.service.OrderInfoService;
import com.javaclimb.xshopping.service.UserInfoService;
import com.javaclimb.xshopping.vo.EchartsData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author lenovo
 */
@RestController
@RequestMapping("/echarts")
public class EchartsController {


    @Resource
    private UserInfoService userInfoService;


    @Resource
    private CommentInfoService commentInfoService;

    @Resource
    private OrderInfoService orderInfoService;
    /**
     * 统计各种总数
     * @return
     */
    @GetMapping("/getTotal")
    public Result<Map<String,Object>> getTotal(){
        Map<String,Object> map=new HashMap<>();
        map.put("totalUser",userInfoService.count());
        map.put("totalComment",commentInfoService.count());
        map.put("totalOrder",orderInfoService.count());
        map.put("totalShopping",orderInfoService.totalShopping());
        return Result.success(map);
    }

    /**
     * 统计 分类总销售
     * @return
     */
    @GetMapping("/get/price")
    public Result<List<EchartsData>> getPriceEchartsData(){

        List<EchartsData> list=new ArrayList<>();
        List<Map<String,Object>> typePriceList= orderInfoService.getTypePrice();


        Map<String, Double> typemap=new HashMap<>();

        for (Map<String,Object> map : typePriceList){
            typemap.put((String) map.get("name"), (Double) map.get("price"));
        }
        getPieData("分类总销售",list,typemap);
        getBarData("分类总销售",list,typemap);

        return Result.success(list);
    }


    /**
     * 统计 分类
     * @return
     */
    @GetMapping("/get/shopping")
    public Result<List<EchartsData>> getShoppingEchartsData(){

        List<EchartsData> list=new ArrayList<>();
        List<Map<String,Object>> typePriceList= orderInfoService.getTypeCount();

        System.out.println(typePriceList);


        Map<String, Double> typemap=new HashMap<>();


        for (Map<String,Object> map : typePriceList){
            typemap.put((String) map.get("name"), ((Integer)map.get("count")).doubleValue());
        }
        getPieData("分类总销量",list,typemap);
        getBarData("分类总销量",list,typemap);

        return Result.success(list);
    }



    /**
     * 饼图数据
     * @param name  标题
     * @param pielist    封装后的数据list
     * @param datamap  数据
     */

    private void getPieData(String name,List pielist,Map<String,Double> datamap){
        EchartsData pieDta=new EchartsData();

        Map<String,String> titlemap=new HashMap<>();
        titlemap.put("text",name);
        titlemap.put("left","center");
        pieDta.setTitle(titlemap);

        Map<String,Object> tooltipmap=new HashMap<>();
        tooltipmap.put("show","true");
        pieDta.setTooltip(tooltipmap);

        Map<String,String> legendmap=new HashMap<>();
        legendmap.put("orient","vertical");
        legendmap.put("left","left");
        pieDta.setLegend(legendmap);

        EchartsData.Series series=new EchartsData.Series();
        series.setName(name);
        series.setRadius("50%");
        series.setType("pie");

        List<Object> objects=new ArrayList<>();
        for (String key : datamap.keySet()){
            //
            objects.add(new JSONObject().putOpt("name",key).putOpt("value",datamap.get(key)) );
        }
        series.setData(objects);

        pieDta.setSeries(Collections.singletonList(series));
        pielist.add(pieDta);
    }

    /**
     * zhu图数据
     * @param name  标题
     * @param pielist    封装后的数据list
     * @param datamap  数据
     */

    private void getBarData(String name,List pielist,Map<String,Double> datamap){
        EchartsData barDta=new EchartsData();

        Map<String,String> titlemap=new HashMap<>();
        titlemap.put("text",name);
        titlemap.put("left","center");
        barDta.setTitle(titlemap);

        Map<String,Object> tooltipmap=new HashMap<>();
        tooltipmap.put("show","true");
        barDta.setTooltip(tooltipmap);


        EchartsData.Series series=new EchartsData.Series();
        series.setName(name);
        series.setType("bar");

        List<Object> objects=new ArrayList<>();
        List<Object> xA=new ArrayList<>();
        for (String key : datamap.keySet()){
            //
            objects.add(datamap.get(key));
            xA.add(key);
        }
        series.setData(objects);

        Map<String,Object> xa=new HashMap<>();
        xa.put("data",xA);
        barDta.setxAxis(xa);

        barDta.setyAxis(new HashMap());

        barDta.setSeries(Collections.singletonList(series));
        pielist.add(barDta);
    }



}
