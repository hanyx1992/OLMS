package com.chenlu.olms.service.schedule;

import java.util.List;

import com.chenlu.olms.bean.Schedule;

public interface IScheduleSvc {
	
	List<Schedule> findAll();


	Schedule findById(String no);


	void addClz(String no, int day, int num, String clzName, String desc);


	void delClz(String no, int day, int num);
}
