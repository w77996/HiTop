package com.w77996.top.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
import com.w77996.top.core.result.Result;
import com.w77996.top.entity.TopEntity;
import com.w77996.top.service.TopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author w77996
 * @description
 * @date 2020/1/1516:32
 */
@CrossOrigin
@Api(value = "热门数据获取接口")
@Slf4j
@RestController
@RequestMapping("/top")
public class TopController {

    @Autowired
    private TopService topService;

    @ApiOperation(value = "获取微博热门")
    @GetMapping("/weibo")
    public Result<PageInfo<TopEntity>> getNowWeiboHotList(){
        log.info(DateUtil.format(DateUtil.offsetHour(DateUtil.date(),-1), DatePattern.NORM_DATETIME_PATTERN));
        return Result.success(topService.selectAllWeiboHot());
    }



}
