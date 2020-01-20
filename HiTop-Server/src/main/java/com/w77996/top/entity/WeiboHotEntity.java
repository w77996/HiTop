package com.w77996.top.entity;

import com.w77996.top.entity.base.TopBaseEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @description
 * @author w77996
 * @date 2020/1/15 16:19
 */
@Data
@Builder
@Document(collection = "weibo_hot")
public class WeiboHotEntity extends TopBaseEntity {

    private String hot;

}
