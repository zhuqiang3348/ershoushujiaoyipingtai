package com.model.enums;

import java.io.Serializable;

/**
 * 必须现在 IEnum 配置 该包扫描自动注入，查看文件 spring-mybatis.xml 参数 typeEnumsPackage
 */
public enum TypeEnum {
    DISABLED(0, "禁用"),
    NORMAL(1, "正常");

    private final int value;
    private final String desc;

    TypeEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return this.value;
    }

    // Jackson 注解为 JsonValue 返回中文 json 描述
    public String getDesc() {
        return this.desc;
    }
}