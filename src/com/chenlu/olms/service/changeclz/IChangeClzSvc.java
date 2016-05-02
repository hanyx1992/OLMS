package com.chenlu.olms.service.changeclz;

import java.util.List;

import com.chenlu.olms.bean.ChangeClz;

public interface IChangeClzSvc {
	
	List<ChangeClz> findAll();


	ChangeClz findById(String no);
}
