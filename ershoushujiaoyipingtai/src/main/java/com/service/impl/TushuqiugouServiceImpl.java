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
import com.dao.TushuqiugouDao;
import com.entity.TushuqiugouEntity;
import com.service.TushuqiugouService;
import com.entity.view.TushuqiugouView;

/**
 * 图书求购 服务实现类
 */
@Service("tushuqiugouService")
@Transactional
public class TushuqiugouServiceImpl extends ServiceImpl<TushuqiugouDao, TushuqiugouEntity> implements TushuqiugouService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        // 必须用 Entity 泛型分页对象
        Page<TushuqiugouEntity> entityPage = new Query<TushuqiugouEntity>(params).getPage();
        // 用 entityPage 查 view 数据
        List<TushuqiugouView> viewList = baseMapper.selectListView(entityPage, params);
        // 组装 view 分页对象
        Page<TushuqiugouView> viewPage = new Page<>();
        viewPage.setRecords(viewList);
        viewPage.setTotal(entityPage.getTotal());
        viewPage.setSize(entityPage.getSize());
        viewPage.setCurrent(entityPage.getCurrent());
        viewPage.setPages(entityPage.getPages());
        return new PageUtils(viewPage);
    }

}