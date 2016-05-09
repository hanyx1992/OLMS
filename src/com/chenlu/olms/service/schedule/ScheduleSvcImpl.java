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

	@Override
	public void addClz(String no, int day, int num, String clzName, String desc) {
		Schedule s = scheduleDao.queryById(no);
		String[][] sDesc = s.getClzDesc();
		String[][] sName = s.getClzName();
		boolean[][] sHas = s.getHasClz();
		
		sDesc[day][num] = desc;
		sName[day][num] = clzName;
		sHas[day][num] = true;
		
		s.setClzDesc(sDesc);
		s.setClzName(sName);
		s.setHasClz(sHas);
		
		scheduleDao.save(s);
	}

	@Override
	public void delClz(String no, int day, int num) {
		Schedule s = scheduleDao.queryById(no);
		String[][] sDesc = s.getClzDesc();
		String[][] sName = s.getClzName();
		boolean[][] sHas = s.getHasClz();
		
		sDesc[day][num] = "";
		sName[day][num] = "";
		sHas[day][num] =  false;
		
		s.setClzDesc(sDesc);
		s.setClzName(sName);
		s.setHasClz(sHas);
		
		scheduleDao.save(s);
	}
}
