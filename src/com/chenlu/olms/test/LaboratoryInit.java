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
 * @desc:��ʵ���ұ�����ʼ������
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
		//��ռ���
		mongoTemplate.dropCollection(Laboratory.class);
		
		//��ʼ������
		Laboratory a = new Laboratory();
		a.setNo("0001");
		a.setName("��һ������ʵ����");
		a.setDesc("���ʵ���ҲŲ�Ҫ���ø�����~");
		a.setLocation("���صĽ���");
		a.setState((short)0);
		a.setIsUsed(GlobalConstraints.Data_ENUM.IS_USED);
		
		laboratoryDao.save(a);
	}
	
}
