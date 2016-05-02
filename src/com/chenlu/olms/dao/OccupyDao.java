package com.chenlu.olms.dao;

import com.chenlu.olms.bean.Occupy;

public class OccupyDao extends CommonDao<Occupy> {

	@Override
	protected Class<Occupy> getEntityClass() {
		return Occupy.class;
	}

}
