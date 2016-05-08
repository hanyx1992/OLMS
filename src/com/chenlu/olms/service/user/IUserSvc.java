package com.chenlu.olms.service.user;

import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;
import com.chenlu.olms.bean.User;

public interface IUserSvc {
	
	User doLoginCheck(String username, String password);
	
	PageRetInfo<User> findByCondition(PageBean page, User condition);
	
	User findById(String loginName);
	
	void save(User user);

	void deleteById(String loginName);

	void resetPwdById(String id);
}
