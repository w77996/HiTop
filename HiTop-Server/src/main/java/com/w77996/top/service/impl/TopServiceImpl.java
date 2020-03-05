package com.w77996.top.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.w77996.top.entity.TopEntity;
import com.w77996.top.mapper.TopMapper;
import com.w77996.top.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


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
        PageHelper.startPage(1, 50);
        return new PageInfo<>(topMapper.selectAllWeiboHot());
    }

    @Override
    public  PageInfo<TopEntity> getWeiboHotList(){
        PageHelper.startPage(1, 50);
        List<TopEntity> webHotList = topMapper.selectAllWeiboHot();
        if(CollectionUtil.isNotEmpty(webHotList)){
            CollectionUtil.sort(webHotList, (o1, o2) -> {
                if(o2.getFeature().getIntValue("hot") == 0 ||o1.getFeature().getIntValue("hot") ==0){
                    return o1.getFeature().getIntValue("hot") - o2.getFeature().getIntValue("hot");
                }
                return o2.getFeature().getIntValue("hot") - o1.getFeature().getIntValue("hot");
            });
        }
        return new PageInfo<>(webHotList);
    }

    @Override
    public PageInfo<TopEntity> getZhihuHotList() {
        PageHelper.startPage(1, 50);
        List<TopEntity> zhihuHotList = topMapper.getZhihuHotList();
        if(CollectionUtil.isNotEmpty(zhihuHotList)){
            CollectionUtil.sort(zhihuHotList, (o1, o2) -> NumberUtil.parseInt(o2.getFeature().getString("hot").trim()) - NumberUtil.parseInt(o1.getFeature().getString("hot").trim()));
        }
        return new PageInfo<>(zhihuHotList);
    }

    @Override
    public PageInfo<TopEntity> getGithubTrendList() {
        PageHelper.startPage(1, 50);
        return new PageInfo<>(topMapper.getGithubTrendList());
    }
}
