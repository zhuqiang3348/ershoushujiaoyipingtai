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
import com.dao.TushuOrderDao;
import com.entity.TushuOrderEntity;
import com.service.TushuOrderService;
import com.entity.view.TushuOrderView;

/**
 * 图书订单 服务实现类
 */
@Service("tushuOrderService")
@Transactional
public class TushuOrderServiceImpl extends ServiceImpl<TushuOrderDao, TushuOrderEntity> implements TushuOrderService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        // 1. 必须用 TushuOrderEntity 泛型分页对象
        Page<TushuOrderEntity> entityPage = new Query<TushuOrderEntity>(params).getPage();
        // 2. 查出 View 列表
        List<TushuOrderView> viewList = baseMapper.selectListView(entityPage, params);
        // 3. 组装成 Page<TushuOrderView>
        Page<TushuOrderView> viewPage = new Page<>();
        viewPage.setRecords(viewList);
        viewPage.setTotal(entityPage.getTotal());
        viewPage.setSize(entityPage.getSize());
        viewPage.setCurrent(entityPage.getCurrent());
        viewPage.setPages(entityPage.getPages());
        // 4. 返回 PageUtils
        return new PageUtils(viewPage);
    }
}