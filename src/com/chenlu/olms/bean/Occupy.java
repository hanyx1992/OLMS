package com.chenlu.olms.bean;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.chenlu.olms.util.DateUtils;
import com.chenlu.olms.util.GlobalConstraints;
import com.chenlu.olms.util.SysUtils;

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
	/** 实验室名称方便页面显示 */
	private String labName;
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

	
	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

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
	
	public String getDateStr() {
		if (createDate == date) {
			return "-";
		}
		return DateUtils.formatDateToILikeymdStr(date);
	}
	
	public String getSubjectStr() {
		String num = "一";
		switch(subject) {
		case 1:
			num = "二";
			break;
		case 2:
			num = "三";
			break;
		case 3:
			num = "四";
			break;
		case 4:
			num = "五";
			break;
		case 5:
			num = "六";
			break;
		case 6:
			num = "七";
			break;
		}
		return "第"+num+"大节";
	}
	
	public String getStateStr() {
		return GlobalConstraints.Data_ENUM.OCCUPY_REVIEW_STRING[this.reviewState];
	}
	
	public String getCreateDateStr() {
		if (createDate == null) {
			return "-";
		}
		return DateUtils.formatDateToILikeymdStr(createDate);
	}
	
	public String getReviewDateStr() {
		if (reviewDate == null) {
			return "-";
		}
		return DateUtils.formatDateToILikeymdStr(reviewDate);
	}

	public String getCancelBtn() {
		if (this.reviewState==GlobalConstraints.Data_ENUM.OCCUPY_REVIEW_WAIT) {
			return "<a href='javascript:;' class='dlt-btn easyui-linkbutton'>撤销</a>";
		} else {
			return "-";
		}
	}

	@Override
	public String toString() {
		return "Occupy [id=" + id + ", no=" + no + ", labName=" + labName
				+ ", loginName=" + loginName + ", num=" + num + ", date="
				+ date + ", subject=" + subject + ", desc=" + desc
				+ ", createDate=" + createDate + ", reviewState=" + reviewState
				+ ", reviewUser=" + reviewUser + ", reviewDate=" + reviewDate
				+ ", reviewDesc=" + reviewDesc + ", isUsed=" + isUsed + "]";
	}
	
	public String getRealName() {
		return SysUtils.getUserNameByLoginName(this.loginName);
	}
	
	
}
