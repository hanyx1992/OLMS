package com.chenlu.olms.service.notice;

import java.util.List;

import com.chenlu.olms.bean.Notice;
import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;

public interface INoticeSvc {
	
	List<Notice> findAll();

	Notice findById(String no);
	
	PageRetInfo<Notice> findByCondition(PageBean page, Notice condition);
}
