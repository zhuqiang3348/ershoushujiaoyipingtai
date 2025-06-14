package com.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.UsersEntity;
import com.utils.PageUtils;

/**
 * 系统用户
 * @author yangliyuan
 * @date 2025年04月10日 上午9:18:20
 */
public interface UsersService extends IService<UsersEntity> {
	PageUtils queryPage(Map<String, Object> params);

	List<UsersEntity> selectListView(Wrapper<UsersEntity> wrapper);

	PageUtils queryPage(Map<String, Object> params, Wrapper<UsersEntity> wrapper);
}