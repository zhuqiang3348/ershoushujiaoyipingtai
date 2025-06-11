package com.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 字典
 *
 * @author
 * @email
 */
@TableName("dictionary")
public class DictionaryEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public DictionaryEntity() {}
    public DictionaryEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
    private Long id;

    @TableField(value = "dic_code")
    private String dicCode;

    @TableField(value = "dic_name")
    private String dicName;

    @TableField(value = "code_index")
    private Integer codeIndex;

    @TableField(value = "index_name")
    private String indexName;

    @TableField(value = "super_id")
    private Integer superId;

    @TableField(value = "beizhu")
    private String beizhu;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    // ========== Getter and Setter ==========

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDicCode() {
        return dicCode;
    }
    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    public String getDicName() {
        return dicName;
    }
    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public Integer getCodeIndex() {
        return codeIndex;
    }
    public void setCodeIndex(Integer codeIndex) {
        this.codeIndex = codeIndex;
    }

    public String getIndexName() {
        return indexName;
    }
    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public Integer getSuperId() {
        return superId;
    }
    public void setSuperId(Integer superId) {
        this.superId = superId;
    }

    public String getBeizhu() {
        return beizhu;
    }
    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DictionaryEntity{" +
                "id=" + id +
                ", dicCode='" + dicCode + '\'' +
                ", dicName='" + dicName + '\'' +
                ", codeIndex=" + codeIndex +
                ", indexName='" + indexName + '\'' +
                ", superId=" + superId +
                ", beizhu='" + beizhu + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}