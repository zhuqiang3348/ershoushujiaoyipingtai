package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.dao.NewsDao;
import com.entity.NewsEntity;
import com.service.NewsService;
import com.entity.view.NewsView;

/**
 * 公告信息 服务实现类
 */
@Service("newsService")
@Transactional
public class NewsServiceImpl extends ServiceImpl<NewsDao, NewsEntity> implements NewsService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        // 1. 必须用 NewsEntity 分页对象
        Page<NewsEntity> entityPage = new Query<NewsEntity>(params).getPage();
        // 2. 查出 NewsView 列表
        List<NewsView> viewList = baseMapper.selectListView(entityPage, params);
        // 3. 组装成 Page<NewsView>
        Page<NewsView> viewPage = new Page<>();
        viewPage.setRecords(viewList);
        viewPage.setTotal(entityPage.getTotal());
        viewPage.setSize(entityPage.getSize());
        viewPage.setCurrent(entityPage.getCurrent());
        viewPage.setPages(entityPage.getPages());
        // 4. 返回 PageUtils
        return new PageUtils(viewPage);
    }
}