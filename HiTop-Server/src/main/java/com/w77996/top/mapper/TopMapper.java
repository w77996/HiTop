package com.w77996.top.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.w77996.top.entity.TopEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author w77996
 * @description
 * @date 2020/2/7 20:03
 */
@Repository
public interface TopMapper extends BaseMapper<TopEntity> {


    List<TopEntity> selectAllWeiboHot();
}
