package com.chenlu.olms.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.chenlu.olms.bean.Occupy;
import com.chenlu.olms.dao.OccupyDao;

/**
 * @desc:向实验室表插入初始化数据
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"file:WebContent/WEB-INF/spring-servlet.xml"})
public class OccupyInit{

	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private OccupyDao occupyDao;
	
	@Test
	public void Test() {
//		this.init();
		System.out.println(occupyDao.queryAllUsedList().get(0).getLoginName());
		System.out.println(occupyDao.queryAllUsedList().get(0).getId());
		System.out.println(occupyDao.queryAllUsedList().get(0).getDesc());
		System.out.println(occupyDao.queryAllUsedList().get(0).getNo());
	}
	
	public void init() {
		//清空集合
		mongoTemplate.dropCollection(Occupy.class);
		
	}
	
}
