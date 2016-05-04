package com.chenlu.olms.bean;

import org.springframework.data.annotation.Id;

import com.chenlu.olms.util.GlobalConstraints;

/**
 * 实验室
 */
public class Laboratory {
	@Id
	/** 实验室编号 */
	private String no;
	/** 实验室名称 */
	private String name;
	/** 位置 */
	private String location;
	/** 容纳人数 */
	private int size;
	/** 描述 */
	private String desc;
	/** 状态 -1 未开放 */
	private short state;
	/** 数据状态 0 - Del ; 1 - Used */
	private short isUsed = GlobalConstraints.Data_ENUM.IS_USED;
	
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public short getState() {
		return state;
	}
	public void setState(short state) {
		this.state = state;
	}
	public String getStateStr() {
		return state == (short)-1? "暂不开放":"可预约";
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
}
