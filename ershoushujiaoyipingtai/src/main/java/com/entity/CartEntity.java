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
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 购物车
 *
 * @author
 * @email
 */
@TableName("cart")
public class CartEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public CartEntity() {
    }

    public CartEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
    private Integer id;

    /**
     * 所属用户
     */
    @TableField(value = "yonghu_id")
    private Integer yonghuId;

    /**
     * 图书
     */
    @TableField(value = "tushu_id")
    private Integer tushuId;

    /**
     * 购买数量
     */
    @TableField(value = "buy_number")
    private Integer buyNumber;

    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(value = "insert_time", fill = FieldFill.INSERT)
    private Date insertTime;

    // Getter and setter methods...

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYonghuId() {
        return yonghuId;
    }
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }

    public Integer getTushuId() {
        return tushuId;
    }
    public void setTushuId(Integer tushuId) {
        this.tushuId = tushuId;
    }

    public Integer getBuyNumber() {
        return buyNumber;
    }
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getInsertTime() {
        return insertTime;
    }
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", yonghuId=" + yonghuId +
                ", tushuId=" + tushuId +
                ", buyNumber=" + buyNumber +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", insertTime=" + insertTime +
                "}";
    }
}