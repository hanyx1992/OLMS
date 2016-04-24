package com.chenlu.olms.service.laboratory;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chenlu.olms.bean.Laboratory;
import com.chenlu.olms.dao.LaboratoryDao;

public class LaboratorySvcImpl implements ILaboratorySvc{
	@Autowired
	private LaboratoryDao laboratoryDao;
	
	private static Log log = LogFactory.getLog(LaboratorySvcImpl.class);
	
	@Override
	public List<Laboratory> findAll() {
		return laboratoryDao.queryAllUsedList(null);
	}
	
}
