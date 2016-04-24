package com.chenlu.olms.bean;

import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * 
 * 实验室的占用数据
 * @author chenlu
 */
public class Occupy {
	@Id
	/** 实验室编号(对应的) */
	private String no;
	/** 租用人登录名 */
	private String loginName;
	/** 租用日期 */
	private Date date;
	/** 租用第几节课 */
	private int subject;
	/** 描述 */
	private String desc;
	/** 数据状态 0 - Del ; 1 - Used */
	private short isUsed;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getSubject() {
		return subject;
	}
	public void setSubject(int subject) {
		this.subject = subject;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public short getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(short isUsed) {
		this.isUsed = isUsed;
	}
	
	
}
