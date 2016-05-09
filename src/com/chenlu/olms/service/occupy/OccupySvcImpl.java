package com.chenlu.olms.service.occupy;

import java.util.ArrayList;
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
import com.chenlu.olms.util.DateUtils;
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

	@Override
	public PageRetInfo<Occupy> findByCondition(PageBean page, Occupy occupy) {
		Query query = new Query();
		if (occupy != null && occupy.getReviewState() != -1) {
			//不是查询全部
			query.addCriteria(Criteria.where("reviewState").is(occupy.getReviewState()));
		}
		query.with(new Sort(Direction.DESC,"createDate"));
		PageRetInfo<Occupy> retInfo = new PageRetInfo<Occupy>();
		retInfo.setTotal(occupyDao.getPageUsedCount(query));
		retInfo.setRows(occupyDao.getPage(query, page));
		log.debug(retInfo);
		return retInfo;
	}

	@Override
	public void doTimeOut() {
		log.info("准备清理过期的申请");
		Query query = new Query();
		query.addCriteria(Criteria.where("reviewState").is(GlobalConstraints.Data_ENUM.OCCUPY_REVIEW_WAIT));
		List<Occupy> list = occupyDao.queryAllUsedListByCondition(query);
		if (list != null && list.size() > 0) {
			for (Occupy o : list) {
				if (o.getDate().getTime() <= new Date().getTime()) {
					o.setReviewState(GlobalConstraints.Data_ENUM.OCCUPY_REVIEW_TIMEOUT);
					occupyDao.save(o);
					log.info("处理一条:" + o);
				}
			}
		}
		log.info("清理完毕...");
	}

	@Override
	public void doById(String id, boolean b, String string) throws Exception {
		Occupy o = occupyDao.queryById(id);
		if (o.getReviewState() != GlobalConstraints.Data_ENUM.OCCUPY_REVIEW_WAIT) {
			throw new Exception();
		}
		o.setReviewState(b?GlobalConstraints.Data_ENUM.OCCUPY_REVIEW_SUCCESSS:GlobalConstraints.Data_ENUM.OCCUPY_REVIEW_FAILED);
		o.setReviewDesc(string);
		occupyDao.save(o);
	}

	@Override
	public List<Occupy> getOccupyByWeekFirstDate(Date weekFirstDate) {
		Query query = new Query();
		query.addCriteria(Criteria.where("reviewState").is(GlobalConstraints.Data_ENUM.OCCUPY_REVIEW_SUCCESSS));
		List<Occupy> list = occupyDao.queryAllUsedListByCondition(query);
		if (list != null && list.size() > 0) {
			for (Occupy o : list) {
				//TODO
				Date date = o.getDate();
				DateUtils.isBetweenTwoDate(date, weekFirstDate, null);
			}
		}
		return list;
	}

	@Override
	public List<Occupy> getOccupyByStrDateRange(String no, String startDate, String endDate) {
		Query query = new Query();
		startDate += " 00:00:00";
		endDate += " 23:59:59";
		
		Date s = DateUtils.parseDateWithString(startDate, DateUtils.ILikeYMDHMS);
		Date e = DateUtils.parseDateWithString(endDate, DateUtils.ILikeYMDHMS);
		
		query.addCriteria(Criteria.where("no").is(no));
		query.addCriteria(Criteria.where("reviewState").is(GlobalConstraints.Data_ENUM.OCCUPY_REVIEW_SUCCESSS));
		List<Occupy> list = occupyDao.queryAllUsedListByCondition(query);
		List<Occupy> ret = new ArrayList<Occupy>();
		
		if (list != null && list.size() > 0) {
			for (Occupy o : list) {
				if (DateUtils.isBetweenTwoDate(o.getDate(), s, e)) {
					ret.add(o);
				}
			}
		}
		return ret;
	}
}
