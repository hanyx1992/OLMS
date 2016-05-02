package com.chenlu.olms.service.changeclz;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chenlu.olms.bean.ChangeClz;
import com.chenlu.olms.dao.ChangeClzDao;

public class ChangeClzSvcImpl implements IChangeClzSvc{
	@Autowired
	private ChangeClzDao changeClzDao;
	
	private static Log log = LogFactory.getLog(ChangeClzSvcImpl.class);
	
	@Override
	public List<ChangeClz> findAll() {
		return changeClzDao.queryAllUsedListByCondition(null);
	}
	
	@Override
	public ChangeClz findById(String no) {
		log.debug(no);
		return changeClzDao.queryById(no);
	}
}
