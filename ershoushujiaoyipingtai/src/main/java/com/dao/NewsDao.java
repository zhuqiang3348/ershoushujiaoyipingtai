package com.dao;

import com.entity.NewsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.view.NewsView;

/**
 * 公告信息 Dao 接口
 *
 * @author
 */
public interface NewsDao extends BaseMapper<NewsEntity> {

   List<NewsView> selectListView(Page<NewsEntity> page, @Param("params") Map<String,Object> params);

}