package com.service.impl;

import com.entity.YonghuEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.dao.YonghuDao;
import com.service.YonghuService;
import com.entity.view.YonghuView;

/**
 * 用户 服务实现类
 */
@Service("yonghuService")
@Transactional
public class YonghuServiceImpl extends ServiceImpl<YonghuDao, YonghuEntity> implements YonghuService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        // 必须用 Entity 泛型分页对象
        Page<YonghuEntity> entityPage = new Query<YonghuEntity>(params).getPage();
        // 用 entityPage 查 View 列表
        List<YonghuView> viewList = baseMapper.selectListView(entityPage, params);
        // 组装 View 泛型分页对象
        Page<YonghuView> viewPage = new Page<>();
        viewPage.setRecords(viewList);
        viewPage.setTotal(entityPage.getTotal());
        viewPage.setSize(entityPage.getSize());
        viewPage.setCurrent(entityPage.getCurrent());
        viewPage.setPages(entityPage.getPages());
        return new PageUtils(viewPage);
    }
}