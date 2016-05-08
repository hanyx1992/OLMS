package com.chenlu.olms.service.occupy;

import java.util.List;

import com.chenlu.olms.bean.Occupy;
import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;

public interface IOccupySvc {
	
	List<Occupy> findAll();

	PageRetInfo<Occupy> findByUserLoginName(PageBean page, String loginName);
	
	Occupy findById(String no);
	
	void save(Occupy occupy);
	
	void cancelOccupy(String id) throws Exception;
	
	/**
	 * 查询当前时间之后所有预占成功的
	 * @return
	 */
	List<Occupy> findAllSuccessAfterToday();
}
