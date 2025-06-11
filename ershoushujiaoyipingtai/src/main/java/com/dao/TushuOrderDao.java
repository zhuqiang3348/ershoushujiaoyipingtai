package com.dao;

import com.entity.TushuOrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TushuOrderView;

/**
 * 图书订单 Dao 接口
 *
 * @author
 */
public interface TushuOrderDao extends BaseMapper<TushuOrderEntity> {

   List<TushuOrderView> selectListView(Page<TushuOrderEntity> page, @Param("params") Map<String,Object> params);

}