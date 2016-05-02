package com.chenlu.olms.service.schedule;

import java.util.List;

import com.chenlu.olms.bean.Schedule;

public interface IScheduleSvc {
	
	List<Schedule> findAll();


	Schedule findById(String no);
}
