package com.entity.view;

import com.entity.CartEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 购物车
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("cart")
public class CartView extends CartEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//级联表 tushu
	/**
	 * 图书 的 用户
	 */
	private Integer tushuYonghuId;
	/**
	 * 图书名称
	 */
	private String tushuName;
	/**
	 * 图书图片
	 */
	private String tushuPhoto;
	/**
	 * 作者
	 */
	private String tushuZuozhe;
	/**
	 * 出版社
	 */
	private String tushuChubanshe;
	/**
	 * 图书类型
	 */
	private Integer tushuTypes;
	/**
	 * 图书类型的值
	 */
	private String tushuValue;
	/**
	 * 图书库存
	 */
	private Integer tushuKucunNumber;
	/**
	 * 图书原价
	 */
	private Double tushuOldMoney;
	/**
	 * 现价
	 */
	private Double tushuNewMoney;
	/**
	 * 点击次数
	 */
	private Integer tushuClicknum;
	/**
	 * 是否上架
	 */
	private Integer shangxiaTypes;
	/**
	 * 是否上架的值
	 */
	private String shangxiaValue;
	/**
	 * 逻辑删除
	 */
	private Integer tushuDelete;
	/**
	 * 图书简介
	 */
	private String tushuContent;

	//级联表 yonghu
	/**
	 * 用户姓名
	 */
	private String yonghuName;
	/**
	 * 用户手机号
	 */
	private String yonghuPhone;
	/**
	 * 用户身份证号
	 */
	private String yonghuIdNumber;
	/**
	 * 用户头像
	 */
	private String yonghuPhoto;
	/**
	 * 电子邮箱
	 */
	private String yonghuEmail;

	public CartView() {

	}

	public CartView(CartEntity cartEntity) {
		try {
			BeanUtils.copyProperties(this, cartEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	//级联表的get和set tushu
	public Integer getTushuYonghuId() {
		return tushuYonghuId;
	}
	public void setTushuYonghuId(Integer tushuYonghuId) {
		this.tushuYonghuId = tushuYonghuId;
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

	public String getTushuValue() {
		return tushuValue;
	}
	public void setTushuValue(String tushuValue) {
		this.tushuValue = tushuValue;
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

	public String getShangxiaValue() {
		return shangxiaValue;
	}
	public void setShangxiaValue(String shangxiaValue) {
		this.shangxiaValue = shangxiaValue;
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

	//级联表的get和set yonghu
	public String getYonghuName() {
		return yonghuName;
	}
	public void setYonghuName(String yonghuName) {
		this.yonghuName = yonghuName;
	}

	public String getYonghuPhone() {
		return yonghuPhone;
	}
	public void setYonghuPhone(String yonghuPhone) {
		this.yonghuPhone = yonghuPhone;
	}

	public String getYonghuIdNumber() {
		return yonghuIdNumber;
	}
	public void setYonghuIdNumber(String yonghuIdNumber) {
		this.yonghuIdNumber = yonghuIdNumber;
	}

	public String getYonghuPhoto() {
		return yonghuPhoto;
	}
	public void setYonghuPhoto(String yonghuPhoto) {
		this.yonghuPhoto = yonghuPhoto;
	}

	public String getYonghuEmail() {
		return yonghuEmail;
	}
	public void setYonghuEmail(String yonghuEmail) {
		this.yonghuEmail = yonghuEmail;
	}
}