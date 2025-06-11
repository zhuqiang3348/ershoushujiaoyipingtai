package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.PageUtils;
import com.entity.YonghuEntity; // 加上这一行

import java.util.Map;

/**
 * 用户 服务类
 */
public interface YonghuService extends IService<YonghuEntity> {

    /**
     * @param params 查询参数
     * @return 带分页的查询出来的数据
     */
    PageUtils queryPage(Map<String, Object> params);
}