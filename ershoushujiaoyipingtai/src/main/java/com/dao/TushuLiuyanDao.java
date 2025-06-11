package com.dao;

import com.entity.TushuLiuyanEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TushuLiuyanView;

/**
 * 图书留言 Dao 接口
 *
 * @author
 */
public interface TushuLiuyanDao extends BaseMapper<TushuLiuyanEntity> {

   List<TushuLiuyanView> selectListView(Page<TushuLiuyanEntity> page, @Param("params") Map<String,Object> params);

}