package com.chenlu.olms.service.laboratory;

import java.util.List;

import com.chenlu.olms.bean.Laboratory;
import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;

public interface ILaboratorySvc {
	
	List<Laboratory> findAll();

	PageRetInfo<Laboratory> findByCondition(PageBean page, Laboratory condition);

	Laboratory findById(String no);

	void save(Laboratory laboratory);

	void deleteById(String id);

	void changeState(String id);
}
