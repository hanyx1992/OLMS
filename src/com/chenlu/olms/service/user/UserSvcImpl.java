package com.chenlu.olms.service.user;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;
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
				user.setLastLoginDate(user.getLoginDate());
				user.setLoginDate(new Date());
				userDao.save(user);
			}
		}
		return user;
	}

	@Override
	public PageRetInfo<User> findByCondition(PageBean page, User condition) {
		Query query = new Query();
		
		if (condition != null && condition.getRole() != null) {
			query.addCriteria(Criteria.where("role").is(condition.getRole()));
		}
		
		query.with(new Sort(Direction.DESC,"createDate"));
		PageRetInfo<User> retInfo = new PageRetInfo<User>();
		retInfo.setTotal(userDao.getPageUsedCount(query));
		retInfo.setRows(userDao.getPage(query, page));
		log.debug(retInfo);
		return retInfo;
	}

	@Override
	public User findById(String loginName) {
		return userDao.queryById(loginName);
	}
	
	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public void deleteById(String loginName) {
		userDao.logicDeleteById(loginName, "loginName");
	}

	@Override
	public void resetPwdById(String id) {
		User user = userDao.queryById(id);
		user.setLoginPwd(JBcrypt.hashpw("123456", JBcrypt.gensalt()));
		userDao.save(user);
	}

}
