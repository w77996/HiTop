package com.w77996.top.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.w77996.top.entity.TopEntity;
import com.w77996.top.mapper.TopMapper;
import com.w77996.top.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author w77996
 * @description
 * @date 2020/2/7 20:25
 */
@Service
public class TopServiceImpl implements TopService {

    @Autowired
    private TopMapper topMapper;

    @Override
    public  PageInfo<TopEntity> selectAllWeiboHot(){
        PageHelper.startPage(2, 5);
        return new PageInfo<>(topMapper.selectAllWeiboHot());
    }

    @Override
    public  PageInfo<TopEntity> getWeiboHotList(){
        PageHelper.startPage(1, 50);
        return new PageInfo<>(topMapper.selectAllWeiboHot());
    }

    @Override
    public PageInfo<TopEntity> getZhihuHotList() {
        PageHelper.startPage(1, 50);
        return new PageInfo<>(topMapper.getZhihuHotList());
    }

    @Override
    public PageInfo<TopEntity> getGithubTrendList() {
        PageHelper.startPage(1, 50);
        return new PageInfo<>(topMapper.getGithubTrendList());
    }
}
