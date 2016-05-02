package com.chenlu.olms.bean;

import org.springframework.data.annotation.Id;

import com.chenlu.olms.util.GlobalConstraints;

/**
 * 课程表
 */
public class Schedule {
	@Id
	/** 对应实验室编号 */
	private String no;
	/** 对应七天的n节课是否有课 */
	private boolean[][] hasClz = new boolean[7][GlobalConstraints.SYS_PARAM.MAX_SCHEDULE];
	/** 对应七天n节课上课班级名 */
	private String[][] clzName = new String[7][GlobalConstraints.SYS_PARAM.MAX_SCHEDULE];
	/** 备注 */
	private String[][] clzDesc = new String[7][GlobalConstraints.SYS_PARAM.MAX_SCHEDULE];
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public boolean[][] getHasClz() {
		return hasClz;
	}

	public void setHasClz(boolean[][] hasClz) {
		this.hasClz = hasClz;
	}

	public String[][] getClzName() {
		return clzName;
	}

	public void setClzName(String[][] clzName) {
		this.clzName = clzName;
	}

	public String[][] getClzDesc() {
		return clzDesc;
	}

	public void setClzDesc(String[][] clzDesc) {
		this.clzDesc = clzDesc;
	}
	
	
}
