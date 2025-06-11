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
 * 用户反馈
 *
 * @author
 * @email
 */
@TableName("chat")
public class ChatEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public ChatEntity() {}

    public ChatEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /** 主键 */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
    private Integer id;

    /** 提问用户 */
    @TableField(value = "yonghu_id")
    private Integer yonghuId;

    /** 问题 */
    @TableField(value = "chat_issue")
    private String chatIssue;

    /** 问题时间 */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(value = "issue_time")
    private Date issueTime;

    /** 回复 */
    @TableField(value = "chat_reply")
    private String chatReply;

    /** 回复时间 */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(value = "reply_time")
    private Date replyTime;

    /** 状态 */
    @TableField(value = "zhuangtai_types")
    private Integer zhuangtaiTypes;

    /** 数据类型 */
    @TableField(value = "chat_types")
    private Integer chatTypes;

    /** 创建时间 */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)
    private Date insertTime;

    // -- getters and setters below --

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getYonghuId() { return yonghuId; }
    public void setYonghuId(Integer yonghuId) { this.yonghuId = yonghuId; }

    public String getChatIssue() { return chatIssue; }
    public void setChatIssue(String chatIssue) { this.chatIssue = chatIssue; }

    public Date getIssueTime() { return issueTime; }
    public void setIssueTime(Date issueTime) { this.issueTime = issueTime; }

    public String getChatReply() { return chatReply; }
    public void setChatReply(String chatReply) { this.chatReply = chatReply; }

    public Date getReplyTime() { return replyTime; }
    public void setReplyTime(Date replyTime) { this.replyTime = replyTime; }

    public Integer getZhuangtaiTypes() { return zhuangtaiTypes; }
    public void setZhuangtaiTypes(Integer zhuangtaiTypes) { this.zhuangtaiTypes = zhuangtaiTypes; }

    public Integer getChatTypes() { return chatTypes; }
    public void setChatTypes(Integer chatTypes) { this.chatTypes = chatTypes; }

    public Date getInsertTime() { return insertTime; }
    public void setInsertTime(Date insertTime) { this.insertTime = insertTime; }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", yonghuId=" + yonghuId +
                ", chatIssue=" + chatIssue +
                ", issueTime=" + issueTime +
                ", chatReply=" + chatReply +
                ", replyTime=" + replyTime +
                ", zhuangtaiTypes=" + zhuangtaiTypes +
                ", chatTypes=" + chatTypes +
                ", insertTime=" + insertTime +
                "}";
    }
}