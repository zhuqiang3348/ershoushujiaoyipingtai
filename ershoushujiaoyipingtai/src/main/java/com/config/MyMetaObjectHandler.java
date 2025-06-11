package com.config;

import java.util.Date;



import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

/**
 * 自定义填充处理器
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "ctime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 如果有更新时间字段可以加自动填充
        // this.strictUpdateFill(metaObject, "mtime", Date.class, new Date());
    }
}
