package com.chenlu.olms.service.occupy;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chenlu.olms.bean.Occupy;
import com.chenlu.olms.dao.OccupyDao;

public class OccupySvcImpl implements IOccupySvc{
	@Autowired
	private OccupyDao occupyDao;
	
	private static Log log = LogFactory.getLog(OccupySvcImpl.class);
	
	@Override
	public List<Occupy> findAll() {
		return occupyDao.queryAllUsedListByCondition(null);
	}
	
	@Override
	public Occupy findById(String no) {
		log.debug(no);
		return occupyDao.queryById(no);
	}

	@Override
	public void save(Occupy occupy) {
		occupyDao.save(occupy);
	}
}
