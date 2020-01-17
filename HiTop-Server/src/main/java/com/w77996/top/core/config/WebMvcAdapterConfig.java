package com.w77996.top.core.config;

import com.w77996.top.core.filter.TokenFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @description: web拦截器
 * @author: straw
 **/
@Component
public class WebMvcAdapterConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenFilter());
    }
}
