package com.chenlu.olms.dao;

import com.chenlu.olms.bean.Schedule;

public class ScheduleDao extends CommonDao<Schedule> {

	@Override
	protected Class<Schedule> getEntityClass() {
		return Schedule.class;
	}

}
