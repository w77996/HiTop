package com.w77996.top.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @description
 * @author w77996
 * @date 2020/1/15 16:19
 */
@Data
@Document(collection = "weibo")
public class WeiboHotEntity {

    @Id
    private String id;

    private String title;

    private String url;

    private String hot;

    private String urlMd5;

    private Date createTime = new Date();
}
