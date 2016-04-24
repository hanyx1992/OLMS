package com.chenlu.olms.bean;

import org.springframework.data.annotation.Id;

/**
 * 实验室
 */
public class Laboratory {
	@Id
	/** 实验室编号 */
	private String no;
	/** 实验室名称 */
	private String name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
