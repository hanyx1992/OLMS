package com.chenlu.olms.service.notice;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Query;

import com.chenlu.olms.bean.Notice;
import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;
import com.chenlu.olms.dao.NoticeDao;
import com.chenlu.olms.util.DateUtils;

public class NoticeSvcImpl implements INoticeSvc{
	@Autowired
	private NoticeDao noticeDao;
	
	private static Log log = LogFactory.getLog(NoticeSvcImpl.class);
	
	@Override
	public List<Notice> findAll() {
		return noticeDao.queryAllUsedListByCondition(null);
	}
	
	@Override
	public Notice findById(String no) {
		log.debug(no);
		return noticeDao.queryById(no);
	}
	
	@Override
	public PageRetInfo<Notice> findByCondition(PageBean page, Notice condition) {
		Query query = new Query();
		//--Condition 略 想到的时候在写吧
		query.with(new Sort(Direction.DESC,"createDate"));
		PageRetInfo<Notice> retInfo = new PageRetInfo<Notice>();
		retInfo.setTotal(noticeDao.getPageUsedCount(query));
		retInfo.setRows(noticeDao.getPage(query, page));
		log.debug(retInfo);
		return retInfo;
	}

	@Override
	public void addNotice(String title, String content, Date expDate) {
		Notice n = new Notice();
		n.setTitle(title);
		n.setContent(content);
		n.setCreateDate(new Date());
		n.setExpDate(expDate);
		noticeDao.save(n);
	}

	@Override
	public List<Notice> findAllNoticeForDisplay() {
		Query query = new Query();
		query.with(new Sort(Direction.DESC,"createDate"));
		List<Notice> list =  noticeDao.queryAllUsedListByCondition(query);
		
		if (list != null && list.size() > 0) {
			for (int i=0; i< list.size(); i++) {
				Notice n = list.get(i);
				if(!DateUtils.isNowBetweenTwoDate(n.getCreateDate(), n.getExpDate())) {
					list.remove(i--);
				}
			}
		}
		return list;
	}

	@Override
	public void deleteById(String id) {
		noticeDao.logicDeleteById(id);
	}
	
	
}
