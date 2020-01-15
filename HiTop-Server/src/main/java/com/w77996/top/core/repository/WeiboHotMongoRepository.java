package com.w77996.top.core.repository;

import com.w77996.top.entity.WeiboHotEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author w77996
 * @description
 * @date 2020/1/15 16:29
 */
@Repository
public interface WeiboHotMongoRepository extends ReactiveMongoRepository<WeiboHotEntity, Long> {
}
