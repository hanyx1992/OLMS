package com.chenlu.olms.service.user;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.chenlu.olms.bean.User;
import com.chenlu.olms.dao.UserDao;
import com.chenlu.olms.util.GlobalConstraints;
import com.chenlu.olms.util.JBcrypt;

public class UserSvcImpl implements IUserSvc{
	@Autowired
	private UserDao userDao;
	
	private static Log log = LogFactory.getLog(UserSvcImpl.class);

	@Override
	public User doLoginCheck(String username, String password) {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return null;
		}
		User user = userDao.queryById(username);
		if (user != null) {
			if (!JBcrypt.checkpw(password, user.getLoginPwd()) ||
					user.getIsUsed() != GlobalConstraints.Data_ENUM.IS_USED) {
				log.info("ÃÜÂë´íÎó!");
				user = null;
			} else {
				log.info("µÇÂ¼³É¹¦");
				user.setLastLoginDate(new Date());
				userDao.save(user);
			}
		}
		return user;
	}
}
