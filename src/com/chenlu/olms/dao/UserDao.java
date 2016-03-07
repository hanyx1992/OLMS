package com.chenlu.olms.dao;

import com.chenlu.olms.bean.User;

public class UserDao extends CommonDao<User> {

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

}
