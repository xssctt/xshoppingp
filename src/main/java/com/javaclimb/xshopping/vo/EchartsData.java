package com.javaclimb.xshopping.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * EchartsData图形
 *
 * Serializable
 */
public class EchartsData implements Serializable {
    private Map title;
    private Map tooltip;
    private Map legend;
    private Map xAxis;
    private Map yAxis;

    private List<Series> series;

    public static class Series {
        private String name;
        private String type;
        private String radius;

        private  List<Object> data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRadius() {
            return radius;
        }

        public void setRadius(String radius) {
            this.radius = radius;
        }

        public List<Object> getData() {
            return data;
        }

        public void setData(List<Object> data) {
            this.data = data;
        }

    }

    public Map getTitle() {
        return title;
    }

    public void setTitle(Map title) {
        this.title = title;
    }

    public Map getTooltip() {
        return tooltip;
    }

    public void setTooltip(Map tooltip) {
        this.tooltip = tooltip;
    }

    public Map getLegend() {
        return legend;
    }

    public void setLegend(Map legend) {
        this.legend = legend;
    }

    public Map getxAxis() {
        return xAxis;
    }

    public void setxAxis(Map xAxis) {
        this.xAxis = xAxis;
    }

    public Map getyAxis() {
        return yAxis;
    }

    public void setyAxis(Map yAxis) {
        this.yAxis = yAxis;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }
}


/**
 * option = {
 *   title: {
 *     text: 'Referer of a Website',
 *     subtext: 'Fake Data',
 *     left: 'center'
 *   },
 *   tooltip: {
 *     trigger: 'item'
 *   },
 *   legend: {
 *     orient: 'vertical',
 *     left: 'left'
 *   },
 *   series: [
 *     {
 *       name: 'Access From',
 *       type: 'pie',
 *       radius: '50%',
 *       data: [
 *         { value: 1048, name: 'Search Engine' },
 *         { value: 735, name: 'Direct' },
 *         { value: 580, name: 'Email' },
 *         { value: 484, name: 'Union Ads' },
 *         { value: 300, name: 'Video Ads' }
 *       ],
 *       emphasis: {
 *         itemStyle: {
 *           shadowBlur: 10,
 *           shadowOffsetX: 0,
 *           shadowColor: 'rgba(0, 0, 0, 0.5)'
 *         }
 *       }
 *     }
 *   ]
 * };
 *
 *
 * option = {
 *   xAxis: {
 *     type: 'category',
 *     data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
 *   },
 *   yAxis: {
 *     type: 'value'
 *   },
 *   series: [
 *     {
 *       data: [120, 200, 150, 80, 70, 110, 130],
 *       type: 'bar',
 *       showBackground: true,
 *       backgroundStyle: {
 *         color: 'rgba(180, 180, 180, 0.2)'
 *       }
 *     }
 *   ]
 * };
 **/