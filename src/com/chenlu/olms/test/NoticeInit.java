package com.chenlu.olms.test;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.chenlu.olms.bean.Notice;
import com.chenlu.olms.dao.NoticeDao;
import com.chenlu.olms.util.GlobalConstraints;

/**
 * @desc:��ʵ���ұ�����ʼ������
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"file:WebContent/WEB-INF/spring-servlet.xml"})
public class NoticeInit{

	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private NoticeDao noticeDao;
	
	@Test
	public void Test() {
		this.init();
	}
	
	public void init() {
		//��ռ���
		mongoTemplate.dropCollection(Notice.class);
		
		Notice a = new Notice();
		a.setTitle("���Թ���ϵͳ");
		a.setContent("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		a.setIsUsed(GlobalConstraints.Data_ENUM.IS_USED);
		a.setCreateDate(new Date());
		a.setExpDate(new Date(System.currentTimeMillis()+1000*60*60*24*7));
		noticeDao.save(a);
	}
	
}
