package com.w77996.top.entity;

import com.w77996.top.entity.base.TopBaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author w77996
 * @description
 * @date 2020/1/19 14:11
 */
@Data
@Document(collection = "zhihu_hot")
public class ZhihuHotEntity extends TopBaseEntity {

    private String desc;

    private String hot;
}
