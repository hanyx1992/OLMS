package com.chenlu.olms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chenlu.olms.bean.Laboratory;
import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;
import com.chenlu.olms.bean.Schedule;
import com.chenlu.olms.service.laboratory.ILaboratorySvc;
import com.chenlu.olms.service.schedule.IScheduleSvc;
import com.chenlu.olms.util.DateUtils;
import com.chenlu.olms.util.GlobalConstraints;
import com.chenlu.olms.util.SysUtils;

/**
 * ����ʵ�����������
 */
@Controller
@RequestMapping("/laboratory")
public class LaboratoryController {

	@Autowired
	private ILaboratorySvc laboratorySvc;
	@Autowired
	private IScheduleSvc scheduleSvc;
	
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
		request.setAttribute("startDate", DateUtils.getFirstDayOfThisWeek());
		request.setAttribute("endDate", DateUtils.getLastDayOfThisWeek());
		return "laboratory/laboratory-desc";
	}
	
	/**
	 * ��ȡ�γ̱�
	 * @return
	 */
	@RequestMapping(value = "/schedule.do")
	public void schedule(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			Schedule sche = scheduleSvc.findById(no);
			retMap.put("success", true);
			retMap.put("sche",  sche);
		} catch (Exception e) {
			retMap.put("success", false);
			retMap.put("errMsg", "�Բ���,�����쳣,���Ժ�����!");
		}
		SysUtils.returnJson(response, retMap);
	}
	
	/**
	 * ��ȡʵ����һ�ܵ�Ԥռ��Ϣ
	 * @return
	 */
	@RequestMapping(value = "/getOccupyInfo.do")
	public void getOccupyInfo(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String startDate = request.getParameter("startDate");
		String isLast = request.getParameter("isLast");
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			if (StringUtils.isEmpty(startDate)) {
				retMap.put("startDate", DateUtils.getFirstDayOfThisWeek());
				retMap.put("endDate", DateUtils.getLastDayOfThisWeek());
			} else {
				startDate = "true".equals(isLast) ? 
						DateUtils.getLastWeekStrByILike(startDate) :
							DateUtils.getNextWeekStrByILike(startDate);
				retMap.put("startDate", DateUtils.getFirstDayOfTheWeek(startDate));
				retMap.put("endDate",  DateUtils.getLastDayOfTheWeek(startDate));
			}
			retMap.put("success", true);
		} catch (Exception e) {
			retMap.put("success", false);
			retMap.put("errMsg", "�Բ���,�����쳣,���Ժ�����!");
		}
		
		SysUtils.returnJson(response, retMap);
	}
	
	/**
	 * ��ȡʵ����һ�ܵĴ�����Ϣ
	 * @return
	 */
	@RequestMapping(value = "/getChangeClzInfo.do")
	public void getChangeClzInfo(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String startDate = request.getParameter("startDate");
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("success", true);
		SysUtils.returnJson(response, retMap);
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
	
	@RequestMapping(value = "/occupy.do")
	public void occupy(HttpServletRequest request, HttpServletResponse response) {
		String day = request.getParameter("day");
		String num = request.getParameter("num");
		
		
	}
}
