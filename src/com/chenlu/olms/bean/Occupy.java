package com.chenlu.olms.bean;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.chenlu.olms.util.GlobalConstraints;

/**
 * 
 * 实验室的预占信息
 * 
 * @author chenlu
 */
public class Occupy {
	@Id
	private String id;
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

	/** 申请时间 */
	private Date createDate;
	/** 审核状态 */
	private short reviewState;
	/** 审核人 */
	private String reviewUser;
	/** 审核时间 */
	private Date reviewDate;
	/** 审核描述 */
	private String reviewDesc;

	/** 数据状态 0 - Del ; 1 - Used */
	private short isUsed = GlobalConstraints.Data_ENUM.IS_USED;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

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

	public short getReviewState() {
		return reviewState;
	}

	public void setReviewState(short reviewState) {
		this.reviewState = reviewState;
	}

	public String getReviewUser() {
		return reviewUser;
	}

	public void setReviewUser(String reviewUser) {
		this.reviewUser = reviewUser;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewDesc() {
		return reviewDesc;
	}

	public void setReviewDesc(String reviewDesc) {
		this.reviewDesc = reviewDesc;
	}

}
