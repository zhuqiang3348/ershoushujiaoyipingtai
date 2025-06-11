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
 * 用户
 *
 * @author
 * @email
 */
@TableName("yonghu")
public class YonghuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public YonghuEntity() {}

    public YonghuEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "yonghu_name")
    private String yonghuName;

    @TableField(value = "yonghu_phone")
    private String yonghuPhone;

    @TableField(value = "yonghu_id_number")
    private String yonghuIdNumber;

    @TableField(value = "yonghu_photo")
    private String yonghuPhoto;

    @TableField(value = "sex_types")
    private Integer sexTypes;

    @TableField(value = "yonghu_email")
    private String yonghuEmail;

    @TableField(value = "new_money")
    private Double newMoney;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getYonghuName() { return yonghuName; }
    public void setYonghuName(String yonghuName) { this.yonghuName = yonghuName; }

    public String getYonghuPhone() { return yonghuPhone; }
    public void setYonghuPhone(String yonghuPhone) { this.yonghuPhone = yonghuPhone; }

    public String getYonghuIdNumber() { return yonghuIdNumber; }
    public void setYonghuIdNumber(String yonghuIdNumber) { this.yonghuIdNumber = yonghuIdNumber; }

    public String getYonghuPhoto() { return yonghuPhoto; }
    public void setYonghuPhoto(String yonghuPhoto) { this.yonghuPhoto = yonghuPhoto; }

    public Integer getSexTypes() { return sexTypes; }
    public void setSexTypes(Integer sexTypes) { this.sexTypes = sexTypes; }

    public String getYonghuEmail() { return yonghuEmail; }
    public void setYonghuEmail(String yonghuEmail) { this.yonghuEmail = yonghuEmail; }

    public Double getNewMoney() { return newMoney; }
    public void setNewMoney(Double newMoney) { this.newMoney = newMoney; }

    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    @Override
    public String toString() {
        return "Yonghu{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", yonghuName=" + yonghuName +
                ", yonghuPhone=" + yonghuPhone +
                ", yonghuIdNumber=" + yonghuIdNumber +
                ", yonghuPhoto=" + yonghuPhoto +
                ", sexTypes=" + sexTypes +
                ", yonghuEmail=" + yonghuEmail +
                ", newMoney=" + newMoney +
                ", createTime=" + createTime +
                "}";
    }
}