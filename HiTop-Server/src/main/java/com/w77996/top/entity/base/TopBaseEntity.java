package com.w77996.top.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author w77996
 * @description
 * @date 2020/1/19 13:38
 */
@Data
public class TopBaseEntity {

    @Id
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 跳转链接
     */
    private String url;

    @JsonIgnore
    private String key;
    /**
     * 创建时间
     */
    private String createTime;

}
