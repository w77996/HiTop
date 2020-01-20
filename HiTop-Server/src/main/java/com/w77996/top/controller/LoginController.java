package com.w77996.top.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.w77996.top.core.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: top
 * @description: 登陆相关
 * @author: w77996
 * @create: 2020-01-19 17:08
 */
@Api(value = "登陆相关接口")
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {


    @GetMapping("/auto")
    public Result<String> autoLogin(){
        Snowflake snowflake =  IdUtil.createSnowflake(1, 1);
        long snowflakeId = snowflake.nextId();
        return Result.success(snowflakeId+"");
    }

}
