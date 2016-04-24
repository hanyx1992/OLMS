package com.chenlu.olms.bean;

import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * �û���
 */
public class User {
	/** ��¼�� */
	@Id
	private String loginName;
	/** ��¼���� */
	private String loginPwd;
	/** ��ʵ���� */
	private String realName;
	/** ��ɫ 0 - Administrator ; 1 - Teacher ; 2 - Student */
	private short role;
	/** Ȩ�޼��� */
	private short[] auths = {0};
	/** �������� */
	private Date createDate;
	/** ����¼���� */
	private Date lastLoginDate;
	/** ����״̬ 0 - Del ; 1 - Used */
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
