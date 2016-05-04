package com.chenlu.olms.bean;

import org.springframework.data.annotation.Id;

import com.chenlu.olms.util.GlobalConstraints;

/**
 * ʵ����
 */
public class Laboratory {
	@Id
	/** ʵ���ұ�� */
	private String no;
	/** ʵ�������� */
	private String name;
	/** λ�� */
	private String location;
	/** �������� */
	private int size;
	/** ���� */
	private String desc;
	/** ״̬ -1 δ���� */
	private short state;
	/** ����״̬ 0 - Del ; 1 - Used */
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
		return state == (short)-1? "�ݲ�����":"��ԤԼ";
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
}
