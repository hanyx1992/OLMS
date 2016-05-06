package com.chenlu.olms.dao;

import com.chenlu.olms.bean.Notice;

public class NoticeDao extends CommonDao<Notice> {

	@Override
	protected Class<Notice> getEntityClass() {
		return Notice.class;
	}

}
