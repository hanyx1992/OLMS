package com.chenlu.olms.bean;


/**
 * 
 * 实验室的预占信息
 * 
 * @author chenlu
 */
public class MergeOccupy {

	/** 预占人数 */
	private int size;
	/** 预占周几的索引 */
	private int day;
	/** 第几节课的索引 */
	private int num;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	
}
