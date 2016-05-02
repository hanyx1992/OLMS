package com.chenlu.olms.test;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.chenlu.olms.bean.Laboratory;
import com.chenlu.olms.bean.Schedule;
import com.chenlu.olms.dao.LaboratoryDao;
import com.chenlu.olms.dao.ScheduleDao;
import com.chenlu.olms.util.GlobalConstraints;

/**
 * @desc:向实验室表插入初始化数据
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"file:WebContent/WEB-INF/spring-servlet.xml"})
public class ScheduleInit{

	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private ScheduleDao scheduleDao;
	@Autowired
	private LaboratoryDao laboratoryDao;
	
	@Test
	public void Test() {
		this.init();
	}
	
	private static final String[] clzName = {"机械", "电气", "经管", "计算机", "自动化", "数学",
		"英语", "软件", "土木", "医学院", "法学院"}; 
	
	public void init() {
		//清空集合
		mongoTemplate.dropCollection(Schedule.class);
		
		List<Laboratory> list = laboratoryDao.queryAllUsedListByCondition(null);
		
		if (list != null && list.size() > 0) {
			Random r = new Random(47);
			for (Laboratory bean : list) {
				String no = bean.getNo();
				Schedule each = new Schedule();
				each.setNo(no);
				
				int n = r.nextInt(7);
				
				for (int i = 0 ; i < n ; i++) {
					int day = r.nextInt(7);
					int num = r.nextInt(GlobalConstraints.SYS_PARAM.MAX_SCHEDULE);
					
					each.getHasClz()[day][num] = true;
					each.getClzDesc()[day][num] = "一节课而已,没什么可描述的~";
					each.getClzName()[day][num] = clzName[r.nextInt(clzName.length)] + (r.nextInt(4)+12) + "-" + r.nextInt(15)+"班";
				}
				
				scheduleDao.save(each);
			}
		}
		
	}
	
}
