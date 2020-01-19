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

    private String title;

    private String url;

    @JsonIgnore
    private String key;

    private String createTime;

}
