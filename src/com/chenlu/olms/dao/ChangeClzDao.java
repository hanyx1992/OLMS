package com.chenlu.olms.dao;

import com.chenlu.olms.bean.ChangeClz;

public class ChangeClzDao extends CommonDao<ChangeClz> {

	@Override
	protected Class<ChangeClz> getEntityClass() {
		return ChangeClz.class;
	}

}
