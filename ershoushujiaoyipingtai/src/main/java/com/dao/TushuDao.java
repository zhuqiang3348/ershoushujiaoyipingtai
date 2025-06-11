package com.dao;

import com.entity.TushuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TushuView;

/**
 * 图书 Dao 接口
 *
 * @author
 */
public interface TushuDao extends BaseMapper<TushuEntity> {

   List<TushuView> selectListView(Page<TushuEntity> page, @Param("params") Map<String,Object> params);

}