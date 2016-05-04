package com.chenlu.olms.service.occupy;

import java.util.List;

import com.chenlu.olms.bean.Occupy;

public interface IOccupySvc {
	
	List<Occupy> findAll();


	Occupy findById(String no);
	
	void save(Occupy occupy);
}
