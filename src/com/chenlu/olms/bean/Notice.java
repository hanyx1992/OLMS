package com.chenlu.olms.bean;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.chenlu.olms.util.DateUtils;
import com.chenlu.olms.util.GlobalConstraints;

public class Notice {
	
	@Id
	private String id;
	/** ������� */
	private String title;
	/** �������� */
	private String content;
	/** �������� */
	private Date createDate;
	/** ʧЧ���� */
	private Date expDate;
	/** ����״̬ 0 - Del ; 1 - Used */
	private short isUsed = GlobalConstraints.Data_ENUM.IS_USED;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public short getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(short isUsed) {
		this.isUsed = isUsed;
	}
	public String getCreateDateStr() {
		return DateUtils.formatDateToILikeymdStr(createDate);
	}
	public String getExpDateStr() {
		return DateUtils.formatDateToILikeymdStr(expDate);
	}
}