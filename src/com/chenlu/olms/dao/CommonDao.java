package com.chenlu.olms.dao;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.util.GlobalConstraints;

/**
 * @desc:MongoDB 钩子
 * @param <T>
 */
public abstract class CommonDao<T> {

	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(T t) {
		this.mongoTemplate.save(t);
	}

	public T queryById(Object id) {
		return this.mongoTemplate.findById(id, this.getEntityClass());
	}

	public List<T> queryList(Query query) {
		return this.mongoTemplate.find(query, this.getEntityClass());
	}
	
	public List<T> queryAllUsedList() {
		Query query = new Query();
		return this.mongoTemplate.find(query.addCriteria(Criteria.where(GlobalConstraints.Data_ENUM.IS_USED_KEY).is(GlobalConstraints.Data_ENUM.IS_USED)), this.getEntityClass());
	}
	
	public List<T> queryAllUsedListByCondition(Query query) {
		if (query == null) {
			query = new Query();
		}
		return this.mongoTemplate.find(query.addCriteria(Criteria.where(GlobalConstraints.Data_ENUM.IS_USED_KEY).is(GlobalConstraints.Data_ENUM.IS_USED)), this.getEntityClass());
	}
	
	public List<T> queryAllUsedListByPage(Query query, PageBean page) {
		if (query == null) {
			query = new Query();
		}
		query.addCriteria(Criteria.where(GlobalConstraints.Data_ENUM.IS_USED_KEY).is(GlobalConstraints.Data_ENUM.IS_USED))
			.skip(page.getStartIndex()).limit(page.getSize());
		return this.mongoTemplate.find(query, this.getEntityClass());
	}

	public T queryOne(Query query) {
		return this.mongoTemplate.findOne(query, this.getEntityClass());
	}

	public List<T> getPage(Query query, PageBean page) {
		query.skip(page.getStartIndex());
		query.limit(page.getSize());
		List<T> lists = this.mongoTemplate.find(query, this.getEntityClass());
		return lists;
	}

	public Long getPageUsedCount(Query query) {
		if (query == null) {
			query = new Query();
		}
		query.addCriteria(Criteria.where(GlobalConstraints.Data_ENUM.IS_USED_KEY).is(GlobalConstraints.Data_ENUM.IS_USED));		
		return this.mongoTemplate.count(query, this.getEntityClass());
	}

	public void delete(Query query) {
		this.mongoTemplate.remove(query);
	}

	public void delete(T t) {
		this.mongoTemplate.remove(t);
	}
	
	public void logicDeleteById(Object id) {
		Query query = new Query();
		query.addCriteria(Criteria.where(GlobalConstraints.Data_ENUM.IS_USED_KEY).is(GlobalConstraints.Data_ENUM.IS_USED));
		query.addCriteria(Criteria.where("id").is(id));
		
		Update update = new Update();
		update.set(GlobalConstraints.Data_ENUM.IS_USED_KEY, GlobalConstraints.Data_ENUM.IS_NOT_USED);
		
		this.mongoTemplate.updateFirst(query, update, this.getEntityClass());
	}
	
	/**
	 * 逻辑删除数据,如果这个数据的主键不为默认的_id,比如loginName,
	 * 那么逻辑删掉了hanyx,实际上创建hanyx是会提示失败的.因为主键冲突了
	 * 所以我们这里完全删掉旧数据,插入一条新数据,不设置id字段即可
	 * @param id
	 * @param key
	 */
	public void logicDeleteById(Object id, String key) {
		
		T t = this.mongoTemplate.findById(id, this.getEntityClass());
		
		try {
			Field idKey = t.getClass().getDeclaredField(key);
			idKey.setAccessible(true);
			idKey.set(t, null);
			
			Field isUsedKey = t.getClass().getDeclaredField(GlobalConstraints.Data_ENUM.IS_USED_KEY);
			isUsedKey.setAccessible(true);
			isUsedKey.set(t, GlobalConstraints.Data_ENUM.IS_NOT_USED);
			
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		this.mongoTemplate.save(t);
		this.mongoTemplate.remove(new Query(Criteria.where(key).is(id)), this.getEntityClass());
	}

	public void updateFirst(Query query, Update update) {
		this.mongoTemplate.updateFirst(query, update, this.getEntityClass());
	}

	public void updateMulti(Query query, Update update) {
		this.mongoTemplate.updateMulti(query, update, this.getEntityClass());
	}

	public void updateInser(Query query, Update update) {
		this.mongoTemplate.upsert(query, update, this.getEntityClass());
	}

	protected abstract Class<T> getEntityClass();
}
