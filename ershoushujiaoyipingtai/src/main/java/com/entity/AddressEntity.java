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
 * 收货地址
 *
 * @author
 * @email
 */
@TableName("address")
public class AddressEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public AddressEntity() {}

    public AddressEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "yonghu_id")
    private Integer yonghuId;

    @TableField(value = "address_name")
    private String addressName;

    @TableField(value = "address_phone")
    private String addressPhone;

    @TableField(value = "address_dizhi")
    private String addressDizhi;

    @TableField(value = "isdefault_types")
    private Integer isdefaultTypes;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)
    private Date insertTime;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private Date updateTime;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getYonghuId() { return yonghuId; }
    public void setYonghuId(Integer yonghuId) { this.yonghuId = yonghuId; }

    public String getAddressName() { return addressName; }
    public void setAddressName(String addressName) { this.addressName = addressName; }

    public String getAddressPhone() { return addressPhone; }
    public void setAddressPhone(String addressPhone) { this.addressPhone = addressPhone; }

    public String getAddressDizhi() { return addressDizhi; }
    public void setAddressDizhi(String addressDizhi) { this.addressDizhi = addressDizhi; }

    public Integer getIsdefaultTypes() { return isdefaultTypes; }
    public void setIsdefaultTypes(Integer isdefaultTypes) { this.isdefaultTypes = isdefaultTypes; }

    public Date getInsertTime() { return insertTime; }
    public void setInsertTime(Date insertTime) { this.insertTime = insertTime; }

    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", yonghuId=" + yonghuId +
                ", addressName=" + addressName +
                ", addressPhone=" + addressPhone +
                ", addressDizhi=" + addressDizhi +
                ", isdefaultTypes=" + isdefaultTypes +
                ", insertTime=" + insertTime +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                "}";
    }
}