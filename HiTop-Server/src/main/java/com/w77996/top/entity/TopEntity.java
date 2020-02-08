package com.w77996.top.entity;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Map;

/**
 * @author w77996
 * @description
 * @date 2020/1/19 13:38
 */
@Data
public class TopEntity {

    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 跳转链接
     */
    private String url;
    /**
     * 类型 1.微博热门 2.知乎热榜 3.github趋势
     */
    private int type;
    /**
     * url md5
     */
    @JsonIgnore
    private String urlKey;
    /**
     * 其他参数
     */
    private String feature;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新世家
     */
    private String updateTime;

}
