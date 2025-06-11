package com.dao;

import com.entity.TushuqiugouEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TushuqiugouView;

/**
 * 图书求购 Dao 接口
 *
 * @author
 */
public interface TushuqiugouDao extends BaseMapper<TushuqiugouEntity> {

   List<TushuqiugouView> selectListView(Page<TushuqiugouEntity> page, @Param("params") Map<String,Object> params);

}