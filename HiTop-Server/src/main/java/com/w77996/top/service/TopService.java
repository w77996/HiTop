package com.w77996.top.service;

import com.github.pagehelper.PageInfo;
import com.w77996.top.entity.TopEntity;

/**
 * @author w77996
 * @description
 * @date 2020/2/7 20:25
 */
public interface TopService {

    PageInfo<TopEntity> selectAllWeiboHot();
}
