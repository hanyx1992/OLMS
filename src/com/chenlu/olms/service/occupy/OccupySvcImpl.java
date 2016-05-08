package com.chenlu.olms.service.occupy;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.chenlu.olms.bean.Occupy;
import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;
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
	public Occupy findById(String id) {
		log.debug(id);
		return occupyDao.queryById(id);
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

	@Override
	public PageRetInfo<Occupy> findByUserLoginName(PageBean page, String loginName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("loginName").is(loginName));
		query.with(new Sort(Direction.DESC,"createDate"));
		PageRetInfo<Occupy> retInfo = new PageRetInfo<Occupy>();
		retInfo.setTotal(occupyDao.getPageUsedCount(query));
		retInfo.setRows(occupyDao.getPage(query, page));
		return retInfo;
	}

	@Override
	public void cancelOccupy(String id) throws Exception {
		Occupy o = findById(id);
		if (o.getReviewState() == GlobalConstraints.Data_ENUM.OCCUPY_REVIEW_WAIT) {
			o.setReviewState(GlobalConstraints.Data_ENUM.OCCUPY_REVIEW_CANCEL);
			occupyDao.save(o);
			return ;
		}
		throw new Exception("当前状态不允许撤销");
	}
}
