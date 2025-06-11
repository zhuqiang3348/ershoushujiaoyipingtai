package com.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper; // 修改点1
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dao.ConfigDao;
import com.entity.ConfigEntity;
import com.service.ConfigService;
import com.utils.PageUtils;
import com.utils.Query;

/**
 * 系统用户
 * @author yangliyuan
 * @date 2025年4月10日 上午9:17:59
 */
@Service("configService")
public class ConfigServiceImpl extends ServiceImpl<ConfigDao, ConfigEntity> implements ConfigService {
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<ConfigEntity> page = this.page( // 修改点2
				new Query<ConfigEntity>(params).getPage(),
				new QueryWrapper<ConfigEntity>() // 修改点3
		);
		return new PageUtils(page);
	}
}