package com.w77996.top.controller;

import cn.hutool.core.date.DateUtil;
import com.w77996.top.core.annotation.IgnoreToken;
import com.w77996.top.core.result.Result;
import com.w77996.top.entity.WeiboHotEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
                        .gt(DateUtil.offsetHour(DateUtil.date(),-1)));
        return Result.success(mongoTemplate.find(query,WeiboHotEntity.class));
    }

    @IgnoreToken
    @GetMapping("/weibo/list")
    public Result<List<WeiboHotEntity>> getWeiboHotById(){
        return Result.success(mongoTemplate.findAll(WeiboHotEntity.class));
    }

    @IgnoreToken
    @GetMapping("/weibo/insert")
    public Result<WeiboHotEntity> insertWeiboHotById(){
        WeiboHotEntity weiboHotEntity =  WeiboHotEntity.builder().hot("1").url("test_url").createTime(DateUtil.date()).title("test").build();
        return Result.success(mongoTemplate.insert(weiboHotEntity));
    }


    public static void main(String[] args) {
        System.out.println(DateUtil.offsetHour(DateUtil.date(),-1));
    }
}
