package com.dao;

import com.entity.DictionaryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.entity.view.DictionaryView;

/**
 * 字典 Dao 接口
 *
 * @author
 */
public interface DictionaryDao extends BaseMapper<DictionaryEntity> {

   List<DictionaryView> selectListView(Page<DictionaryEntity> page, @Param("params") Map<String,Object> params);

}