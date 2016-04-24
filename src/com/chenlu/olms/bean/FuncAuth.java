package com.chenlu.olms.bean;

import org.springframework.data.annotation.Id;

/**
 * ����Ȩ��
 */
public class FuncAuth {
	
	/** ҳ��id */
	@Id
	private short funcId;
	/** ҳ��һ��·�� �� "/page" */
	private String pageUrl;
	/** ����״̬ 0 - Del ; 1 - Used */
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
