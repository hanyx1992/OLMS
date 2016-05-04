package com.chenlu.olms.controller;

import java.util.HashMap;
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
import com.chenlu.olms.bean.Occupy;
import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;
import com.chenlu.olms.bean.Schedule;
import com.chenlu.olms.bean.User;
import com.chenlu.olms.service.laboratory.ILaboratorySvc;
import com.chenlu.olms.service.occupy.IOccupySvc;
import com.chenlu.olms.service.schedule.IScheduleSvc;
import com.chenlu.olms.util.DateUtils;
import com.chenlu.olms.util.GlobalConstraints;
import com.chenlu.olms.util.SysUtils;

/**
 * 处理实验室相关请求
 */
@Controller
@RequestMapping("/laboratory")
public class LaboratoryController {

	private static Log log = LogFactory.getLog(LaboratoryController.class);
	
	@Autowired
	private ILaboratorySvc laboratorySvc;
	@Autowired
	private IScheduleSvc scheduleSvc;
	@Autowired
	private IOccupySvc occupySvc;
	
	/**
	 * 实验室列表页面跳转
	 * @return
	 */
	@RequestMapping(value = "/index.do")
	public String index() {
		return "laboratory/laboratory";
	}
	
	/**
	 * 实验室详情页面跳转
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
	 * 获取课程表
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
			retMap.put("errMsg", "对不起,发生异常,请稍后重试!");
		}
		SysUtils.returnJson(response, retMap);
	}
	
	/**
	 * 获取实验室一周的预占信息
	 * @return
	 */
	@RequestMapping(value = "/getOccupyInfo.do")
	public void getOccupyInfo(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		log.info(no);
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
			retMap.put("errMsg", "对不起,发生异常,请稍后重试!");
		}
		
		SysUtils.returnJson(response, retMap);
	}
	
	/**
	 * 获取实验室一周的串课信息
	 * @return
	 */
	@RequestMapping(value = "/getChangeClzInfo.do")
	public void getChangeClzInfo(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String startDate = request.getParameter("startDate");
		log.info(no);
		log.info(startDate);
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("success", true);
		SysUtils.returnJson(response, retMap);
	}
	
	
	/**
	 * 查询所有实验室信息
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
		String no = request.getParameter("no");
		String day = request.getParameter("day");
		String num = request.getParameter("num");
		String startDate = request.getParameter("startDate");
		String onum = request.getParameter("onum");
		String desc = request.getParameter("desc");
		
		Map<String, Object> retMap = new HashMap<String, Object>();

		try {
			User user = SysUtils.getLoginedUser(request);
			
			Occupy occupy = new Occupy();
			occupy.setLoginName(user.getLoginName());
			occupy.setNo(no);
			occupy.setNum(Integer.parseInt(onum));
			occupy.setDate(DateUtils.getDateAfterDayNumByDateSting(startDate, Integer.parseInt(day)));
			occupy.setSubject(Integer.parseInt(num));
			occupy.setDesc(desc);
			
			occupySvc.save(occupy);
			
			retMap.put("success", true);
			
		} catch (Exception e) {
			retMap.put("success", true);
			retMap.put("errMsg", "信息有误,请重试!");
		}
		
		SysUtils.returnJson(response, retMap);
	}
}
