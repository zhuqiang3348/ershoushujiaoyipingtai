package com.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

/** 
 * token表
 */
@TableName("token")
public class TokenEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@TableId(type = IdType.AUTO)
	private Integer id;
	
	/**
	 * 用户id
	 */
	private Integer userid;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 表名
	 */
	private String tablename;
	
	/**
	 * 角色
	 */
	private String role;
	
	/**
	 * token
	 */
	private String token;
	
	/**
	 * 过期时间
	 */
	private Date expiratedtime;
	
	/**
	 * 新增时间
	 */
	private Date addtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiratedtime() {
		return expiratedtime;
	}

	public void setExpiratedtime(Date expiratedtime) {
		this.expiratedtime = expiratedtime;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public TokenEntity(Integer userid, String username, String tablename,String role, String token, Date expiratedtime) {
		super();
		this.userid = userid;
		this.username = username;
		this.tablename = tablename;
		this.role = role;
		this.token = token;
		this.expiratedtime = expiratedtime;
	}
	
	public TokenEntity() {
	}
	
}
