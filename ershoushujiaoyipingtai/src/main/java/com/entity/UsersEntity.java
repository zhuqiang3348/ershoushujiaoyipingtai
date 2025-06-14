package com.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

/**
 * 用户
 */
@TableName("users")
public class UsersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 * 用户账号
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 用户类型
	 */
	private String role;

	private Date addtime;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}