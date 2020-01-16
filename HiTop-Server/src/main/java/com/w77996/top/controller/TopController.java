package com.w77996.top.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.w77996.top.core.repository.WeiboHotMongoRepository;
import com.w77996.top.entity.WeiboHotEntity;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.ServerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @ApiOperation(value = "获取微博热门")
    @GetMapping("/weibo")
    public ResponseEntity<Flux<WeiboHotEntity>> getNowWeiboHotList(){
        Query query = new Query().addCriteria(Criteria.where("create_time").gt(DateUtil.offsetHour(DateUtil.date(),-1)));
//        Example example = new E

        return ResponseEntity.ok(weiboHotMongoRepository.findAll());
    }

    @GetMapping("/weibo/{id}")
    public Mono<ResponseEntity<WeiboHotEntity>> getWeiboHotById(@PathVariable("id") String id){
        return weiboHotMongoRepository.findById(NumberUtil.binaryToLong(id))
                .map(weiboHotEntity -> ResponseEntity.ok(weiboHotEntity))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    public static void main(String[] args) {
        System.out.println(DateUtil.offsetHour(DateUtil.date(),-1));
    }
}
