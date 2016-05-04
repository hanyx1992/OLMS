package com.chenlu.olms.service.user;

import com.chenlu.olms.bean.User;

public interface IUserSvc {
	User doLoginCheck(String username, String password);
}
