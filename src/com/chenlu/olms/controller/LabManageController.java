package com.chenlu.olms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chenlu.olms.bean.Laboratory;
import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;
import com.chenlu.olms.service.laboratory.ILaboratorySvc;
import com.chenlu.olms.util.SysUtils;

@Controller
@RequestMapping("/lab-mag")
public class LabManageController {
	@Autowired
	private ILaboratorySvc laboratorySvc;
	
	private static Log log = LogFactory.getLog(LabManageController.class);
	
	@RequestMapping(value = "/index.do")
	public String index(HttpServletRequest request) {
		return "lab-mag/lab-mag";
	}
	
	@RequestMapping(value = "/find.do")
	public void find(HttpServletRequest request, HttpServletResponse response) {
		PageBean page = SysUtils.getPageInfo(request);
		Laboratory condition = new Laboratory();
		PageRetInfo<Laboratory> retInfo = laboratorySvc.findByCondition(page, condition);
		SysUtils.returnJson(response, retInfo);
	}
	@RequestMapping(value = "/add.do")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String location = request.getParameter("location");
		String desc = request.getParameter("desc");
		Map<String, Object> retMap;
		try {
			int size = Integer.parseInt(request.getParameter("size"));
			
			retMap = SysUtils.getDefaultSuccessMap();
			//У����Ϣ�Ƿ�Ϸ�,����Ϊ��,���ߵ�¼���ظ�
			if (checkInfo(id, no, name, location, desc, size, retMap)) {
				retMap.put("s", true);
				Laboratory laboratory;
				if (!StringUtils.isEmpty(id)) {
					//��ʾ���޸�
					laboratory = laboratorySvc.findById(id);
				} else {
					//��ʾ����
					laboratory = new Laboratory();
					laboratory.setNo(no);
				}
				laboratory.setSize(size);
				laboratory.setName(name);
				laboratory.setLocation(location);
				laboratory.setDesc(desc);
				laboratorySvc.save(laboratory);
			}
		} catch (Exception e) {
			retMap = SysUtils.getDefaultErrorMap();
			log.error(e);
		}
		SysUtils.returnJson(response, retMap);
	}

	private boolean checkInfo(String id, String no, String name,
			String location, String desc, int size, Map<String, Object> retMap) {
		if (StringUtils.isEmpty(no)) {
			retMap.put("s", false);
			retMap.put("e", "ʵ���ұ�Ų���Ϊ��!");
			return false;
		}
		if (StringUtils.isEmpty(name)) {
			retMap.put("s", false);
			retMap.put("e", "ʵ�������Ʋ���Ϊ��!");
			return false;
		}
		if (StringUtils.isEmpty(location)) {
			retMap.put("s", false);
			retMap.put("e", "ʵ����λ�ò���Ϊ��!");
			return false;
		}
		if (size < 1) {
			retMap.put("s", false);
			retMap.put("e", "ʵ����������������ȷ!");
			return false;
		}
		if (StringUtils.isEmpty(id) && laboratorySvc.findById(no) != null) {
			retMap.put("s", false);
			retMap.put("e", "ʵ���ұ�Ų����ظ�!");
			return false;
		}
		if (!StringUtils.isEmpty(id) && !id.equals(no)) {
			retMap.put("s", false);
			retMap.put("e", "ʵ���ұ�Ų����޸�!");
			return false;
		}
		return true;
	}
	
	@RequestMapping(value = "/del.do")
	public void del(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String, Object> retMap;
		try {
			laboratorySvc.deleteById(id);
			retMap = SysUtils.getDefaultSuccessMap();
		} catch (Exception e) {
			retMap = SysUtils.getDefaultErrorMap();
			log.error(e);
		}
		SysUtils.returnJson(response, retMap);
	}
	
	@RequestMapping(value = "/reset.do")
	public void resetPwd(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String, Object> retMap;
		try {
			laboratorySvc.changeState(id);
			retMap = SysUtils.getDefaultSuccessMap();
		} catch (Exception e) {
			retMap = SysUtils.getDefaultErrorMap();
			log.error(e);
		}
		SysUtils.returnJson(response, retMap);
	}
}
