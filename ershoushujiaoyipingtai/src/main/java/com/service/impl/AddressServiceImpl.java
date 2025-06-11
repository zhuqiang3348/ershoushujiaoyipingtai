package com.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import java.util.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.dao.AddressDao;
import com.entity.AddressEntity;
import com.service.AddressService;
import com.entity.view.AddressView;

/**
 * 收货地址 服务实现类
 */
@Service("addressService")
@Transactional
public class AddressServiceImpl extends ServiceImpl<AddressDao, AddressEntity> implements AddressService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        // 必须用 AddressEntity 分页对象
        Page<AddressEntity> entityPage = new Query<AddressEntity>(params).getPage();
        List<AddressView> viewList = baseMapper.selectListView(entityPage, params);

        // 组装成新的 Page<AddressView>
        Page<AddressView> viewPage = new Page<>();
        viewPage.setRecords(viewList);
        viewPage.setTotal(entityPage.getTotal());
        viewPage.setSize(entityPage.getSize());
        viewPage.setCurrent(entityPage.getCurrent());
        viewPage.setPages(entityPage.getPages());

        return new PageUtils(viewPage);
    }
}