package com.w77996.top;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.w77996.top.mapper")
public class HiTopApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiTopApplication.class, args);
    }

}
