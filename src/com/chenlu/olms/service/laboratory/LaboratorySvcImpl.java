package com.chenlu.olms.service.laboratory;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.chenlu.olms.bean.Laboratory;
import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;
import com.chenlu.olms.dao.LaboratoryDao;

public class LaboratorySvcImpl implements ILaboratorySvc{
	@Autowired
	private LaboratoryDao laboratoryDao;
	
	private static Log log = LogFactory.getLog(LaboratorySvcImpl.class);
	
	@Override
	public List<Laboratory> findAll() {
		return laboratoryDao.queryAllUsedListByCondition(null);
	}

	@Override
	public PageRetInfo<Laboratory> findByCondition(PageBean page, Laboratory condition) {
		Query query = new Query();
		if (condition.getNo()!=null) {
			query.addCriteria(Criteria.where("no").regex(condition.getNo()));
		}
		if (condition.getName()!=null) {
			query.addCriteria(Criteria.where("name").regex(condition.getName()));
		}
		if (condition.getLocation()!=null) {
			query.addCriteria(Criteria.where("location").regex(condition.getLocation()));
		}
				
		query.with(new Sort(Direction.ASC,"no"));
		PageRetInfo<Laboratory> retInfo = new PageRetInfo<Laboratory>();
		retInfo.setTotal(laboratoryDao.getPageUsedCount(query));
		retInfo.setRows(laboratoryDao.getPage(query, page));
		log.debug(retInfo);
		return retInfo;
	}
	
	@Override
	public Laboratory findById(String no) {
		return laboratoryDao.queryById(no);
	}

	@Override
	public void save(Laboratory laboratory) {
		laboratoryDao.save(laboratory);
	}

	@Override
	public void deleteById(String id) {
		laboratoryDao.logicDeleteById(id, "no");	
	}

	@Override
	public void changeState(String id) {
		Laboratory lab = laboratoryDao.queryById(id);
		lab.setState((short)(lab.getState()==0?-1:0));
		laboratoryDao.save(lab);
	}
}
