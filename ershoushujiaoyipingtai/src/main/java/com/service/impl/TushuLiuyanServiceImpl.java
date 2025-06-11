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
import com.dao.TushuLiuyanDao;
import com.entity.TushuLiuyanEntity;
import com.service.TushuLiuyanService;
import com.entity.view.TushuLiuyanView;

/**
 * 图书留言 服务实现类
 */
@Service("tushuLiuyanService")
@Transactional
public class TushuLiuyanServiceImpl extends ServiceImpl<TushuLiuyanDao, TushuLiuyanEntity> implements TushuLiuyanService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        // 必须用 Entity 泛型分页对象
        Page<TushuLiuyanEntity> entityPage = new Query<TushuLiuyanEntity>(params).getPage();
        // 用 entityPage 查 view 数据
        List<TushuLiuyanView> viewList = baseMapper.selectListView(entityPage, params);
        // 组装 view 分页对象
        Page<TushuLiuyanView> viewPage = new Page<>();
        viewPage.setRecords(viewList);
        viewPage.setTotal(entityPage.getTotal());
        viewPage.setSize(entityPage.getSize());
        viewPage.setCurrent(entityPage.getCurrent());
        viewPage.setPages(entityPage.getPages());
        return new PageUtils(viewPage);
    }
}