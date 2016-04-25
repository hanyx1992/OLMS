package com.chenlu.olms.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.chenlu.olms.bean.Laboratory;
import com.chenlu.olms.dao.LaboratoryDao;
import com.chenlu.olms.util.GlobalConstraints;

/**
 * @desc:向实验室表插入初始化数据
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"file:WebContent/WEB-INF/spring-servlet.xml"})
public class LaboratoryInit{

	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private LaboratoryDao laboratoryDao;
	
	@Test
	public void Test() {
		this.init();
		System.out.println(laboratoryDao.queryById("0001").getName());
	}
	
	public void init() {
		//清空集合
		mongoTemplate.dropCollection(Laboratory.class);
		
		//初始化数据
		Laboratory a = new Laboratory();
		a.setNo("0001");
		a.setName("第一个测试实验室");
		a.setDesc("这个实验室才不要租用给你呢~");
		a.setLocation("隐藏的角落");
		a.setState((short)0);
		a.setIsUsed(GlobalConstraints.Data_ENUM.IS_USED);
		
		laboratoryDao.save(a);
	}
	
}
