package com.chenlu.olms.service.occupy;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.chenlu.olms.bean.Occupy;
import com.chenlu.olms.dao.OccupyDao;
import com.chenlu.olms.util.GlobalConstraints;

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

	@Override
	public List<Occupy> findAllSuccessAfterToday() {
		Query query = new Query();
		query.addCriteria(Criteria.where("reviewState").is(GlobalConstraints.Data_ENUM.OCCUPY_REVIEW_SUCCESSS));
		query.addCriteria(Criteria.where("date").gte(new Date()));
		return occupyDao.queryAllUsedListByCondition(query);
	}
}
