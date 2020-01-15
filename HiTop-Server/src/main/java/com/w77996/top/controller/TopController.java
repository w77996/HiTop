package com.w77996.top.controller;

import cn.hutool.core.date.DateUtil;
import com.w77996.top.core.repository.WeiboHotMongoRepository;
import com.w77996.top.entity.WeiboHotEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author w77996
 * @description
 * @date 2020/1/1516:32
 */
@RestController
@RequestMapping("/top")
public class TopController {

    @Autowired
    private WeiboHotMongoRepository weiboHotMongoRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/weibo")
    public Flux<WeiboHotEntity> getNowWeiboHotList(){
//        Query query = new Query();
//        Criteria.where("").lt().andOperator(Criteria.where().gt(DateUtil.))
//        query.addCriteria(C)
        return weiboHotMongoRepository.findAll().m
    }


}
