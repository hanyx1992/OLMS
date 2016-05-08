package com.chenlu.olms.test;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.chenlu.olms.bean.User;
import com.chenlu.olms.dao.UserDao;
import com.chenlu.olms.util.GlobalConstraints;
import com.chenlu.olms.util.JBcrypt;

/**
 * @desc:向实验室表插入初始化数据
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"file:WebContent/WEB-INF/spring-servlet.xml"})
public class UserInit{

	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private UserDao userDao;
	
	@Test
	public void Test() {
		this.init();
		System.out.println(userDao.queryById("hanyx").getRealName());
	}
	
	public void init() {
		//清空集合
		mongoTemplate.dropCollection(User.class);
		
		//初始化数据
		User a = new User();
		
		a.setLoginName("hanyx");
		a.setLoginPwd(JBcrypt.hashpw("88998899", JBcrypt.gensalt()));
		a.setRealName("韩元旭");
		a.setClzName("电气10-03班");
		a.setRole(GlobalConstraints.Data_ENUM.USER_ROLE_STUDENT);
		a.setCreateDate(new Date());

		userDao.save(a);
		
		//初始化数据
		User b = new User();
		
		b.setLoginName("admin");
		b.setLoginPwd(JBcrypt.hashpw("123456", JBcrypt.gensalt()));
		b.setRealName("管理员");
		b.setClzName("管理学院");
		b.setRole(GlobalConstraints.Data_ENUM.USER_ROLE_ADMINISTRATOR);
		b.setCreateDate(new Date());
		b.setAuths(GlobalConstraints.Data_ENUM.USER_AUTHS_ALL);
			
		userDao.save(b);
	}
	
}
