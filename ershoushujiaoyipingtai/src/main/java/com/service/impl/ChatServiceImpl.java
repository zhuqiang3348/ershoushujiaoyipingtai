package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import com.dao.ChatDao;
import com.entity.ChatEntity;
import com.service.ChatService;
import com.entity.view.ChatView;

/**
 * 用户反馈 服务实现类
 */
@Service("chatService")
@Transactional
public class ChatServiceImpl extends ServiceImpl<ChatDao, ChatEntity> implements ChatService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        // 1. 必须用 ChatEntity 泛型分页对象
        Page<ChatEntity> entityPage = new Query<ChatEntity>(params).getPage();
        // 2. 用 entityPage 作为分页参数查 View
        List<ChatView> viewList = baseMapper.selectListView(entityPage, params);
        // 3. 组装成 Page<ChatView>
        Page<ChatView> viewPage = new Page<>();
        viewPage.setRecords(viewList);
        viewPage.setTotal(entityPage.getTotal());
        viewPage.setSize(entityPage.getSize());
        viewPage.setCurrent(entityPage.getCurrent());
        viewPage.setPages(entityPage.getPages());
        // 4. 返回 PageUtils
        return new PageUtils(viewPage);
    }
}