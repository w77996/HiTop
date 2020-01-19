package com.w77996.top.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.w77996.top.core.annotation.IgnoreToken;
import com.w77996.top.core.result.Result;
import com.w77996.top.entity.GithubTrendingEntity;
import com.w77996.top.entity.WeiboHotEntity;
import com.w77996.top.entity.ZhihuHotEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author w77996
 * @description
 * @date 2020/1/1516:32
 */
@Api(value = "热门数据获取接口")
@Slf4j
@RestController
@RequestMapping("/top")
public class TopController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @ApiOperation(value = "获取微博热门")
    @IgnoreToken
    @GetMapping("/weibo")
    public Result<List<WeiboHotEntity>> getNowWeiboHotList(){
        Query query = new Query()
                .addCriteria(Criteria.where("create_time")
                        .gt(DateUtil.format(DateUtil.offsetHour(DateUtil.date(),-1), DatePattern.NORM_DATETIME_PATTERN)));
        log.info(DateUtil.format(DateUtil.offsetHour(DateUtil.date(),-1), DatePattern.NORM_DATETIME_PATTERN));
        return Result.success(mongoTemplate.find(query,WeiboHotEntity.class));
    }

    @IgnoreToken
    @GetMapping("/weibo/list")
    public Result<List<WeiboHotEntity>> getWeiboHotAll(){
        return Result.success(mongoTemplate.findAll(WeiboHotEntity.class));
    }

    @IgnoreToken
    @GetMapping("/weibo/{id}")
    public Result<WeiboHotEntity> getWeiboHotById(@PathVariable("id") String id){
        return Result.success(mongoTemplate.findById(id,WeiboHotEntity.class));
    }


    @IgnoreToken
    @GetMapping("/github")
    public Result<List<GithubTrendingEntity>> getGithubTrendingList(){
        Query query = new Query()
                .addCriteria(Criteria.where("create_time")
                        .gt(DateUtil.format(DateUtil.offsetHour(DateUtil.date(),-1), DatePattern.NORM_DATETIME_PATTERN)));
        
        return Result.success(mongoTemplate.find(query,GithubTrendingEntity.class));
    }

    @IgnoreToken
    @GetMapping("/zhihu")
    public Result<List<ZhihuHotEntity>> getZhihuHotList(){
        Query query = new Query()
                .addCriteria(Criteria.where("create_time")
                        .gt(DateUtil.format(DateUtil.offsetHour(DateUtil.date(),-1), DatePattern.NORM_DATETIME_PATTERN)));
        return Result.success(mongoTemplate.find(query,ZhihuHotEntity.class));
    }


    public static void main(String[] args) {
        System.out.println(DateUtil.offsetHour(DateUtil.date(),-1));
    }
}
