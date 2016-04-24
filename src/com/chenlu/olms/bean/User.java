package com.chenlu.olms.bean;

import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * 用户表
 */
public class User {
	/** 登录名 */
	@Id
	private String loginName;
	/** 登录密码 */
	private String loginPwd;
	/** 真实姓名 */
	private String realName;
	/** 角色 0 - Administrator ; 1 - Teacher ; 2 - Student */
	private short role;
	/** 权限集合 */
	private short[] auths = {0};
	/** 创建日期 */
	private Date createDate;
	/** 最后登录日期 */
	private Date lastLoginDate;
	/** 数据状态 0 - Del ; 1 - Used */
	private short isUsed;
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public short getRole() {
		return role;
	}
	public void setRole(short role) {
		this.role = role;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public short getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(short isUsed) {
		this.isUsed = isUsed;
	}
	public short[] getAuths() {
		return auths;
	}
	public void setAuths(short[] auths) {
		this.auths = auths;
	}
	
}
