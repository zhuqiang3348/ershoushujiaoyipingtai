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
 * 图书
 *
 * @author
 * @email
 */
@TableName("tushu")
public class TushuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public TushuEntity() {}

    public TushuEntity(T t) {
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

    @TableField(value = "tushu_name")
    private String tushuName;

    @TableField(value = "tushu_photo")
    private String tushuPhoto;

    @TableField(value = "tushu_zuozhe")
    private String tushuZuozhe;

    @TableField(value = "tushu_chubanshe")
    private String tushuChubanshe;

    @TableField(value = "tushu_types")
    private Integer tushuTypes;

    @TableField(value = "tushu_kucun_number")
    private Integer tushuKucunNumber;

    @TableField(value = "tushu_old_money")
    private Double tushuOldMoney;

    @TableField(value = "tushu_new_money")
    private Double tushuNewMoney;

    @TableField(value = "tushu_clicknum")
    private Integer tushuClicknum;

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;

    @TableField(value = "tushu_delete")
    private Integer tushuDelete;

    @TableField(value = "tushu_content")
    private String tushuContent;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    // ====== getter/setter ======

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

    public String getTushuName() {
        return tushuName;
    }
    public void setTushuName(String tushuName) {
        this.tushuName = tushuName;
    }

    public String getTushuPhoto() {
        return tushuPhoto;
    }
    public void setTushuPhoto(String tushuPhoto) {
        this.tushuPhoto = tushuPhoto;
    }

    public String getTushuZuozhe() {
        return tushuZuozhe;
    }
    public void setTushuZuozhe(String tushuZuozhe) {
        this.tushuZuozhe = tushuZuozhe;
    }

    public String getTushuChubanshe() {
        return tushuChubanshe;
    }
    public void setTushuChubanshe(String tushuChubanshe) {
        this.tushuChubanshe = tushuChubanshe;
    }

    public Integer getTushuTypes() {
        return tushuTypes;
    }
    public void setTushuTypes(Integer tushuTypes) {
        this.tushuTypes = tushuTypes;
    }

    public Integer getTushuKucunNumber() {
        return tushuKucunNumber;
    }
    public void setTushuKucunNumber(Integer tushuKucunNumber) {
        this.tushuKucunNumber = tushuKucunNumber;
    }

    public Double getTushuOldMoney() {
        return tushuOldMoney;
    }
    public void setTushuOldMoney(Double tushuOldMoney) {
        this.tushuOldMoney = tushuOldMoney;
    }

    public Double getTushuNewMoney() {
        return tushuNewMoney;
    }
    public void setTushuNewMoney(Double tushuNewMoney) {
        this.tushuNewMoney = tushuNewMoney;
    }

    public Integer getTushuClicknum() {
        return tushuClicknum;
    }
    public void setTushuClicknum(Integer tushuClicknum) {
        this.tushuClicknum = tushuClicknum;
    }

    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }

    public Integer getTushuDelete() {
        return tushuDelete;
    }
    public void setTushuDelete(Integer tushuDelete) {
        this.tushuDelete = tushuDelete;
    }

    public String getTushuContent() {
        return tushuContent;
    }
    public void setTushuContent(String tushuContent) {
        this.tushuContent = tushuContent;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TushuEntity{" +
                "id=" + id +
                ", yonghuId=" + yonghuId +
                ", tushuName='" + tushuName + '\'' +
                ", tushuPhoto='" + tushuPhoto + '\'' +
                ", tushuZuozhe='" + tushuZuozhe + '\'' +
                ", tushuChubanshe='" + tushuChubanshe + '\'' +
                ", tushuTypes=" + tushuTypes +
                ", tushuKucunNumber=" + tushuKucunNumber +
                ", tushuOldMoney=" + tushuOldMoney +
                ", tushuNewMoney=" + tushuNewMoney +
                ", tushuClicknum=" + tushuClicknum +
                ", shangxiaTypes=" + shangxiaTypes +
                ", tushuDelete=" + tushuDelete +
                ", tushuContent='" + tushuContent + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}