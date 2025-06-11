package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.dao.CartDao;
import com.entity.CartEntity;
import com.service.CartService;
import com.entity.view.CartView;

/**
 * 购物车 服务实现类
 */
@Service("cartService")
@Transactional
public class CartServiceImpl extends ServiceImpl<CartDao, CartEntity> implements CartService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        // 1. 必须用 CartEntity 分页对象
        Page<CartEntity> entityPage = new Query<CartEntity>(params).getPage();
        // 2. 用 entityPage 作为分页参数查 View
        List<CartView> viewList = baseMapper.selectListView(entityPage, params);
        // 3. 组装成 Page<CartView>
        Page<CartView> viewPage = new Page<>();
        viewPage.setRecords(viewList);
        viewPage.setTotal(entityPage.getTotal());
        viewPage.setSize(entityPage.getSize());
        viewPage.setCurrent(entityPage.getCurrent());
        viewPage.setPages(entityPage.getPages());
        // 4. 返回 PageUtils
        return new PageUtils(viewPage);
    }
}