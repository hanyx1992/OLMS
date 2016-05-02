package com.chenlu.olms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chenlu.olms.bean.Laboratory;
import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;
import com.chenlu.olms.bean.Schedule;
import com.chenlu.olms.service.laboratory.ILaboratorySvc;
import com.chenlu.olms.service.schedule.IScheduleSvc;
import com.chenlu.olms.util.GlobalConstraints;
import com.chenlu.olms.util.SysUtils;

/**
 * ����ʵ�����������
 */
@Controller
@RequestMapping("/laboratory")
public class LaboratoryController {

	@Autowired
	ILaboratorySvc laboratorySvc;
	@Autowired
	IScheduleSvc scheduleSvc;
	
	/**
	 * ʵ�����б�ҳ����ת
	 * @return
	 */
	@RequestMapping(value = "/index.do")
	public String index() {
		return "laboratory/laboratory";
	}
	
	/**
	 * ʵ��������ҳ����ת
	 * @return
	 */
	@RequestMapping(value = "/desc.do")
	public String desc(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		Laboratory lab = laboratorySvc.findById(no);
		request.setAttribute("lab", lab);
		request.setAttribute("num", GlobalConstraints.SYS_PARAM.MAX_SCHEDULE);
		return "laboratory/laboratory-desc";
	}
	
	/**
	 * ��ȡ�γ̱�
	 * @return
	 */
	@RequestMapping(value = "/schedule.do")
	public void schedule(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		Schedule sche = scheduleSvc.findById(no);
		SysUtils.returnJson(response, sche);
	}
	
	
	/**
	 * ��ѯ����ʵ������Ϣ
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/find.do")
	public void findAll(HttpServletRequest request, HttpServletResponse response) {
		PageBean page = SysUtils.getPageInfo(request);
		
		PageRetInfo<Laboratory> retInfo = laboratorySvc.findByCondition(page, null);
		SysUtils.returnJson(response, retInfo);
	}

}
