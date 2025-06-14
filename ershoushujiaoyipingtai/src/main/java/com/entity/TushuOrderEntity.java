package com.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;

/**
 * 图书订单
 *
 * @author
 * @email
 */
@TableName("tushu_order")
public class TushuOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public TushuOrderEntity() {

    }

    public TushuOrderEntity(T t) {
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
     * 订单号
     */
    @TableField(value = "tushu_order_uuid_number")
    private String tushuOrderUuidNumber;

    /**
     * 送货地址
     */
    @TableField(value = "address_id")
    private Integer addressId;

    /**
     * 图书
     */
    @TableField(value = "tushu_id")
    private Integer tushuId;

    /**
     * 用户
     */
    @TableField(value = "yonghu_id")
    private Integer yonghuId;

    /**
     * 购买数量
     */
    @TableField(value = "buy_number")
    private Integer buyNumber;

    /**
     * 快递单号
     */
    @TableField(value = "tushu_order_courier_number")
    private String tushuOrderCourierNumber;

    /**
     * 快递公司
     */
    @TableField(value = "tushu_order_courier_name")
    private String tushuOrderCourierName;

    /**
     * 实付价格
     */
    @TableField(value = "tushu_order_true_price")
    private Double tushuOrderTruePrice;

    /**
     * 订单类型
     */
    @TableField(value = "tushu_order_types")
    private Integer tushuOrderTypes;

    /**
     * 支付类型
     */
    @TableField(value = "tushu_order_payment_types")
    private Integer tushuOrderPaymentTypes;

    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(value = "insert_time", fill = FieldFill.INSERT)
    private Date insertTime;

    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 设置：主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 获取：主键
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * 设置：订单号
     */
    public String getTushuOrderUuidNumber() {
        return tushuOrderUuidNumber;
    }

    /**
     * 获取：订单号
     */
    public void setTushuOrderUuidNumber(String tushuOrderUuidNumber) {
        this.tushuOrderUuidNumber = tushuOrderUuidNumber;
    }
    /**
     * 设置：送货地址
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * 获取：送货地址
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    /**
     * 设置：图书
     */
    public Integer getTushuId() {
        return tushuId;
    }

    /**
     * 获取：图书
     */
    public void setTushuId(Integer tushuId) {
        this.tushuId = tushuId;
    }
    /**
     * 设置：用户
     */
    public Integer getYonghuId() {
        return yonghuId;
    }

    /**
     * 获取：用户
     */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
     * 设置：购买数量
     */
    public Integer getBuyNumber() {
        return buyNumber;
    }

    /**
     * 获取：购买数量
     */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
     * 设置：快递单号
     */
    public String getTushuOrderCourierNumber() {
        return tushuOrderCourierNumber;
    }

    /**
     * 获取：快递单号
     */
    public void setTushuOrderCourierNumber(String tushuOrderCourierNumber) {
        this.tushuOrderCourierNumber = tushuOrderCourierNumber;
    }
    /**
     * 设置：快递公司
     */
    public String getTushuOrderCourierName() {
        return tushuOrderCourierName;
    }

    /**
     * 获取：快递公司
     */
    public void setTushuOrderCourierName(String tushuOrderCourierName) {
        this.tushuOrderCourierName = tushuOrderCourierName;
    }
    /**
     * 设置：实付价格
     */
    public Double getTushuOrderTruePrice() {
        return tushuOrderTruePrice;
    }

    /**
     * 获取：实付价格
     */
    public void setTushuOrderTruePrice(Double tushuOrderTruePrice) {
        this.tushuOrderTruePrice = tushuOrderTruePrice;
    }
    /**
     * 设置：订单类型
     */
    public Integer getTushuOrderTypes() {
        return tushuOrderTypes;
    }

    /**
     * 获取：订单类型
     */
    public void setTushuOrderTypes(Integer tushuOrderTypes) {
        this.tushuOrderTypes = tushuOrderTypes;
    }
    /**
     * 设置：支付类型
     */
    public Integer getTushuOrderPaymentTypes() {
        return tushuOrderPaymentTypes;
    }

    /**
     * 获取：支付类型
     */
    public void setTushuOrderPaymentTypes(Integer tushuOrderPaymentTypes) {
        this.tushuOrderPaymentTypes = tushuOrderPaymentTypes;
    }
    /**
     * 设置：订单创建时间
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * 获取：订单创建时间
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
     * 设置：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 获取：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TushuOrder{" +
                "id=" + id +
                ", tushuOrderUuidNumber=" + tushuOrderUuidNumber +
                ", addressId=" + addressId +
                ", tushuId=" + tushuId +
                ", yonghuId=" + yonghuId +
                ", buyNumber=" + buyNumber +
                ", tushuOrderCourierNumber=" + tushuOrderCourierNumber +
                ", tushuOrderCourierName=" + tushuOrderCourierName +
                ", tushuOrderTruePrice=" + tushuOrderTruePrice +
                ", tushuOrderTypes=" + tushuOrderTypes +
                ", tushuOrderPaymentTypes=" + tushuOrderPaymentTypes +
                ", insertTime=" + insertTime +
                ", createTime=" + createTime +
                "}";
    }
}