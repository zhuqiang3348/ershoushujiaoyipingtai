package com.service.impl;

import com.utils.StringUtil;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import com.dao.TushuDao;
import com.entity.TushuEntity;
import com.service.TushuService;
import com.entity.view.TushuView;

/**
 * 图书 服务实现类
 */
@Service("tushuService")
@Transactional
public class TushuServiceImpl extends ServiceImpl<TushuDao, TushuEntity> implements TushuService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        // 必须用 Entity 泛型分页对象
        Page<TushuEntity> entityPage = new Query<TushuEntity>(params).getPage();
        // 用 entityPage 查 view 数据
        List<TushuView> viewList = baseMapper.selectListView(entityPage, params);
        // 组装 view 分页对象
        Page<TushuView> viewPage = new Page<>();
        viewPage.setRecords(viewList);
        viewPage.setTotal(entityPage.getTotal());
        viewPage.setSize(entityPage.getSize());
        viewPage.setCurrent(entityPage.getCurrent());
        viewPage.setPages(entityPage.getPages());
        return new PageUtils(viewPage);
    }
}