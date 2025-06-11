package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YonghuView;
import com.entity.YonghuEntity; // 加这一行

/**
 * 用户 Dao 接口
 *
 * @author
 */
public interface YonghuDao extends BaseMapper<YonghuEntity> {

   List<YonghuView> selectListView(Page<YonghuEntity> page, @Param("params") Map<String,Object> params);

}