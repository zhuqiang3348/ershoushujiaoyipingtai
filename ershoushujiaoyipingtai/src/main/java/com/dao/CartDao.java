package com.dao;

import com.entity.CartEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CartView;

/**
 * 购物车 Dao 接口
 */
public interface CartDao extends BaseMapper<CartEntity> {
   List<CartView> selectListView(Page<CartEntity> page, @Param("params") Map<String,Object> params);
}