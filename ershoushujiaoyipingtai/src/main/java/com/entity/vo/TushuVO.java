package com.entity.vo;

import com.entity.TushuEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 图书
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("tushu")
public class TushuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 图书名称
     */

    @TableField(value = "tushu_name")
    private String tushuName;


    /**
     * 图书图片
     */

    @TableField(value = "tushu_photo")
    private String tushuPhoto;


    /**
     * 作者
     */

    @TableField(value = "tushu_zuozhe")
    private String tushuZuozhe;


    /**
     * 出版社
     */

    @TableField(value = "tushu_chubanshe")
    private String tushuChubanshe;


    /**
     * 图书类型
     */

    @TableField(value = "tushu_types")
    private Integer tushuTypes;


    /**
     * 图书库存
     */

    @TableField(value = "tushu_kucun_number")
    private Integer tushuKucunNumber;


    /**
     * 图书原价
     */

    @TableField(value = "tushu_old_money")
    private Double tushuOldMoney;


    /**
     * 现价
     */

    @TableField(value = "tushu_new_money")
    private Double tushuNewMoney;


    /**
     * 点击次数
     */

    @TableField(value = "tushu_clicknum")
    private Integer tushuClicknum;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */

    @TableField(value = "tushu_delete")
    private Integer tushuDelete;


    /**
     * 图书简介
     */

    @TableField(value = "tushu_content")
    private String tushuContent;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
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
	 * 设置：图书名称
	 */
    public String getTushuName() {
        return tushuName;
    }


    /**
	 * 获取：图书名称
	 */

    public void setTushuName(String tushuName) {
        this.tushuName = tushuName;
    }
    /**
	 * 设置：图书图片
	 */
    public String getTushuPhoto() {
        return tushuPhoto;
    }


    /**
	 * 获取：图书图片
	 */

    public void setTushuPhoto(String tushuPhoto) {
        this.tushuPhoto = tushuPhoto;
    }
    /**
	 * 设置：作者
	 */
    public String getTushuZuozhe() {
        return tushuZuozhe;
    }


    /**
	 * 获取：作者
	 */

    public void setTushuZuozhe(String tushuZuozhe) {
        this.tushuZuozhe = tushuZuozhe;
    }
    /**
	 * 设置：出版社
	 */
    public String getTushuChubanshe() {
        return tushuChubanshe;
    }


    /**
	 * 获取：出版社
	 */

    public void setTushuChubanshe(String tushuChubanshe) {
        this.tushuChubanshe = tushuChubanshe;
    }
    /**
	 * 设置：图书类型
	 */
    public Integer getTushuTypes() {
        return tushuTypes;
    }


    /**
	 * 获取：图书类型
	 */

    public void setTushuTypes(Integer tushuTypes) {
        this.tushuTypes = tushuTypes;
    }
    /**
	 * 设置：图书库存
	 */
    public Integer getTushuKucunNumber() {
        return tushuKucunNumber;
    }


    /**
	 * 获取：图书库存
	 */

    public void setTushuKucunNumber(Integer tushuKucunNumber) {
        this.tushuKucunNumber = tushuKucunNumber;
    }
    /**
	 * 设置：图书原价
	 */
    public Double getTushuOldMoney() {
        return tushuOldMoney;
    }


    /**
	 * 获取：图书原价
	 */

    public void setTushuOldMoney(Double tushuOldMoney) {
        this.tushuOldMoney = tushuOldMoney;
    }
    /**
	 * 设置：现价
	 */
    public Double getTushuNewMoney() {
        return tushuNewMoney;
    }


    /**
	 * 获取：现价
	 */

    public void setTushuNewMoney(Double tushuNewMoney) {
        this.tushuNewMoney = tushuNewMoney;
    }
    /**
	 * 设置：点击次数
	 */
    public Integer getTushuClicknum() {
        return tushuClicknum;
    }


    /**
	 * 获取：点击次数
	 */

    public void setTushuClicknum(Integer tushuClicknum) {
        this.tushuClicknum = tushuClicknum;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getTushuDelete() {
        return tushuDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setTushuDelete(Integer tushuDelete) {
        this.tushuDelete = tushuDelete;
    }
    /**
	 * 设置：图书简介
	 */
    public String getTushuContent() {
        return tushuContent;
    }


    /**
	 * 获取：图书简介
	 */

    public void setTushuContent(String tushuContent) {
        this.tushuContent = tushuContent;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
