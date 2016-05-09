package com.chenlu.olms.bean;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.chenlu.olms.util.DateUtils;
import com.chenlu.olms.util.GlobalConstraints;
import com.chenlu.olms.util.SysUtils;

/**
 * 
 * ʵ���ҵ�Ԥռ��Ϣ
 * 
 * @author chenlu
 */
public class Occupy {
	@Id
	private String id;
	/** ʵ����id��ʵ����id��ƥ�� */
	private String no;
	/** ʵ�������Ʒ���ҳ����ʾ */
	private String labName;
	/** Ԥռ�˵����� */
	private String loginName;
	/** Ԥռ���� */
	private int num;
	/** Ԥռʱ�� */
	private Date date;
	/** �ڼ��ڿ� */
	private int subject;
	/** ������Ϣ */
	private String desc;

	/** ����ʱ�� */
	private Date createDate;
	/** ���״̬ */
	private short reviewState;
	/** ����� */
	private String reviewUser;
	/** ���ʱ�� */
	private Date reviewDate;
	/** ������� */
	private String reviewDesc;

	/** ����״̬ 0 - Del ; 1 - Used */
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
		String num = "һ";
		switch(subject) {
		case 1:
			num = "��";
			break;
		case 2:
			num = "��";
			break;
		case 3:
			num = "��";
			break;
		case 4:
			num = "��";
			break;
		case 5:
			num = "��";
			break;
		case 6:
			num = "��";
			break;
		}
		return "��"+num+"���";
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
			return "<a href='javascript:;' class='dlt-btn easyui-linkbutton'>����</a>";
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
