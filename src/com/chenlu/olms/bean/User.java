package com.chenlu.olms.bean;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.chenlu.olms.util.DateUtils;
import com.chenlu.olms.util.GlobalConstraints;

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
	/** 班级信息 */
	private String clzName;
	/** 权限集合 */
	private String[] auths = {"main", "laboratory", "info"};
	/** 创建日期 */
	private Date createDate;
	/** 最后登录日期 */
	private Date lastLoginDate;
	/** 本次登录日志 */
	private Date loginDate;
	/** 数据状态 0 - Del ; 1 - Used */
	private short isUsed = GlobalConstraints.Data_ENUM.IS_USED;
	
	
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getClzName() {
		return clzName;
	}
	public void setClzName(String clzName) {
		this.clzName = clzName;
	}
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
	public String[] getAuths() {
		return auths;
	}
	public void setAuths(String[] auths) {
		this.auths = auths;
	}
	public String getLastLoginDateStr() {
		if (this.lastLoginDate == null) {
			return "第一次登录";
		} else {
			return DateUtils.formatDateToILikeymdhmsStr(this.lastLoginDate);
		}
	}
}
