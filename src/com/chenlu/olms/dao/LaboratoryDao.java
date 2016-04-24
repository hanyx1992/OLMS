package com.chenlu.olms.dao;

import com.chenlu.olms.bean.Laboratory;

public class LaboratoryDao extends CommonDao<Laboratory> {

	@Override
	protected Class<Laboratory> getEntityClass() {
		return Laboratory.class;
	}

}
