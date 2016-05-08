package com.chenlu.olms.bean;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.chenlu.olms.util.DateUtils;
import com.chenlu.olms.util.GlobalConstraints;

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
	private Short role;
	/** �༶��Ϣ */
	private String clzName;
	/** Ȩ�޼��� */
	private String[] auths = {"main", "laboratory", "info"};
	/** �������� */
	private Date createDate;
	/** ����¼���� */
	private Date lastLoginDate;
	/** ���ε�¼��־ */
	private Date loginDate;
	/** ����״̬ 0 - Del ; 1 - Used */
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
	public String getUserid() {
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
	public Short getRole() {
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
			return "��һ�ε�¼";
		} else {
			return DateUtils.formatDateToILikeymdhmsStr(this.lastLoginDate);
		}
	}
	public String getRoleStr() {
		return GlobalConstraints.Data_ENUM.USER_ROLE_STRING[this.role];
	}
	public String getAuthStr() {
		StringBuffer auth = new StringBuffer();
		String fs = ", ";
		for (String str : this.auths) {
			for (int i = 0; i < GlobalConstraints.Data_ENUM.USER_AUTH_KEY.length; i++) {
				String a = GlobalConstraints.Data_ENUM.USER_AUTH_KEY[i];
				if (str.equals(a)) {
					auth.append(GlobalConstraints.Data_ENUM.USER_AUTH_STR[i]).append(fs);
					break;
				}
			}
		}
		int i;
		if ((i = auth.lastIndexOf(fs)) > 0) {
			return  auth.substring(0, i);
		} else {
			return "��";
		}
	}
	public String getBlkStateStr() {
		for (String str : this.auths) {
			if (str.equals(GlobalConstraints.Data_ENUM.USER_AUTH_LAB)){
				return "����";
			}
		}
		return "������";
	}
}
