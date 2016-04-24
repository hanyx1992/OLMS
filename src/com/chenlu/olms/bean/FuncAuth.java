package com.chenlu.olms.bean;

import org.springframework.data.annotation.Id;

/**
 * 功能权限
 */
public class FuncAuth {
	
	/** 页面id */
	@Id
	private short funcId;
	/** 页面一级路径 如 "/page" */
	private String pageUrl;
	/** 数据状态 0 - Del ; 1 - Used */
	private short isUsed;
	
	public short getFuncId() {
		return funcId;
	}
	public void setFuncId(short funcId) {
		this.funcId = funcId;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public short getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(short isUsed) {
		this.isUsed = isUsed;
	}
	
}
