package com.w77996.top.entity;

import com.w77996.top.entity.base.TopBaseEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author w77996
 * @description
 * @date 2020/1/19 13:35
 */
@Data
@Builder
@Document(collection = "github_trending")
public class GithubTrendingEntity extends TopBaseEntity {


}
