package com.chenlu.olms.bean;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.chenlu.olms.util.GlobalConstraints;

/**
 * 
 * 实验室的预占信息
 * @author chenlu
 */
public class Occupy {
	@Id
	/** 实验室id和实验室id相匹配 */
	private String no;
	/** 预占人的名字 */
	private String loginName;
	/** 预占人数 */
	private int num;
	/** 预占时间 */
	private Date date;
	/** 第几节课 */
	private int subject;
	/** 描述信息 */
	private String desc;
	/** 数据状态 0 - Del ; 1 - Used */
	private short isUsed = GlobalConstraints.Data_ENUM.IS_USED;
	
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
