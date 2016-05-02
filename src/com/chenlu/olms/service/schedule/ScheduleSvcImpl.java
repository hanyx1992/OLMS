package com.chenlu.olms.service.schedule;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chenlu.olms.bean.Schedule;
import com.chenlu.olms.dao.ScheduleDao;

public class ScheduleSvcImpl implements IScheduleSvc{
	@Autowired
	private ScheduleDao scheduleDao;
	
	private static Log log = LogFactory.getLog(ScheduleSvcImpl.class);
	
	@Override
	public List<Schedule> findAll() {
		return scheduleDao.queryAllUsedListByCondition(null);
	}
	
	@Override
	public Schedule findById(String no) {
		log.debug(no);
		return scheduleDao.queryById(no);
	}
}
