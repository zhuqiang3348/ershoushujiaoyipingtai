package com.dao;

import com.entity.ChatEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ChatView;

/**
 * 用户反馈 Dao 接口
 */
public interface ChatDao extends BaseMapper<ChatEntity> {
   List<ChatView> selectListView(Page<ChatEntity> page, @Param("params") Map<String,Object> params);
}