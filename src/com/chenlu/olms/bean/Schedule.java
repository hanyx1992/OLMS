package com.chenlu.olms.bean;

import org.springframework.data.annotation.Id;

import com.chenlu.olms.util.GlobalConstraints;

/**
 * �γ̱�
 */
public class Schedule {
	@Id
	/** ��Ӧʵ���ұ�� */
	private String no;
	/** ��Ӧ�����n�ڿ��Ƿ��п� */
	private boolean[][] hasClz = new boolean[7][GlobalConstraints.SYS_PARAM.MAX_SCHEDULE];
	/** ��Ӧ����n�ڿ��Ͽΰ༶�� */
	private String[][] clzName = new String[7][GlobalConstraints.SYS_PARAM.MAX_SCHEDULE];
	/** ��ע */
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
