package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.utils.PageUtils;
import com.entity.NewsEntity;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 公告信息 服务类
 */
public interface NewsService extends IService<NewsEntity> {

    /**
     * @param params 查询参数
     * @return 带分页的查询出来的数据
     */
    PageUtils queryPage(Map<String, Object> params);
}