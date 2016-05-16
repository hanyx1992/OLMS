package com.chenlu.olms.service.notice;

import java.util.Date;
import java.util.List;

import com.chenlu.olms.bean.Notice;
import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;

public interface INoticeSvc {
	
	List<Notice> findAll();
	
	List<Notice> findAllNoticeForDisplay();

	Notice findById(String no);
	
	PageRetInfo<Notice> findByCondition(PageBean page, Notice condition);
	
	void addNotice(String title, String content, String userName, Date expDate);

	void deleteById(String id);
}
