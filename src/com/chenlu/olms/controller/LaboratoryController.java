package com.chenlu.olms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.chenlu.olms.bean.MergeOccupy;
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
			if (sche == null) {
				sche = new Schedule();
				sche.setNo(no);
				scheduleSvc.save(sche);
			}
			retMap = SysUtils.getDefaultSuccessMap();
			retMap.put("sche",  sche);
		} catch (Exception e) {
			retMap = SysUtils.getDefaultErrorMap();
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
		String endDate;
		String isLast = request.getParameter("isLast");
		Map<String, Object> retMap = SysUtils.getDefaultSuccessMap();
		retMap.put("lockFlag", false);
		retMap.put("lockIndex", -1);
		try {
			if (StringUtils.isEmpty(startDate)) {
				startDate = DateUtils.getFirstDayOfThisWeek();
				endDate = DateUtils.getLastDayOfThisWeek();
				retMap.put("startDate", startDate);
				retMap.put("endDate", endDate);
				retMap.put("lockIndex", DateUtils.getWeekNumByDate(new Date()));
			} else {
				
				startDate = "true".equals(isLast) ? 
						DateUtils.getLastWeekStrByILike(startDate) :
							DateUtils.getNextWeekStrByILike(startDate);
				startDate = DateUtils.getFirstDayOfTheWeek(startDate);
				endDate = DateUtils.getLastDayOfTheWeek(startDate);
				
				if (DateUtils.isDayBeforeAWeekFormThatDay(DateUtils.parseDateWithILikeString(endDate), new Date())) {
					//如果当期选择的周末为上周末那么不返回结果.直接return;
					retMap.put("lockFlag", true);
					SysUtils.returnJson(response, retMap);
					return ;
				}
				
				retMap.put("startDate", startDate);
				retMap.put("endDate",  endDate);
				
				if (DateUtils.isBetweenTwoDate(new Date(), DateUtils.parseDateWithILikeString(startDate), DateUtils.parseDateWithILikeString(endDate))) {
					retMap.put("lockIndex", DateUtils.getWeekNumByDate(new Date()));
				}
			}
			
			List<Occupy> list = occupySvc.getOccupyByStrDateRange(no, startDate, endDate);
			List<MergeOccupy> occData = handleOccupyListToMergeData(list);
			retMap.put("list", occData);
		} catch (Exception e) {
			retMap = SysUtils.getDefaultErrorMap();
			log.error(e);
		}
		
		SysUtils.returnJson(response, retMap);
	}
	
	/**
	 * 将预占单信息合并,以便前台处理数据
	 * @param list
	 * @return
	 */
	private List<MergeOccupy> handleOccupyListToMergeData(List<Occupy> list) {
		//分析
		int[][] sizes = new int[7][GlobalConstraints.SYS_PARAM.MAX_SCHEDULE];
		if (list != null && list.size() > 0) {
			for (Occupy o : list) {
				//求索引,减一
				int day = DateUtils.getWeekNumByDate(o.getDate()) - 1;
				int num = o.getSubject();
				int size = o.getNum();
				sizes[day][num] = size;
			}
		}
		//处理
		List<MergeOccupy> retList = new ArrayList<MergeOccupy>();
		for (int d = 0; d < 7; d++) {
			for (int n = 0; n < GlobalConstraints.SYS_PARAM.MAX_SCHEDULE; n++) {
				int size = sizes[d][n];
				if (size > 0) {
					MergeOccupy o = new MergeOccupy();
					o.setDay(d);
					o.setNum(n);
					o.setSize(size);
					retList.add(o);
				}
			}
		}
		return retList;
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
		SysUtils.returnJson(response, SysUtils.getDefaultSuccessMap());
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
			//方便以后显示
			occupy.setLabName(laboratorySvc.findById(no).getName());
			occupy.setNum(Integer.parseInt(onum));
			occupy.setDate(DateUtils.getDateAfterDayNumByDateSting(startDate, Integer.parseInt(day)));
			occupy.setSubject(Integer.parseInt(num));
			occupy.setDesc(desc);
			occupy.setCreateDate(new Date());
			
			occupySvc.save(occupy);
			
			retMap = SysUtils.getDefaultSuccessMap();
		} catch (Exception e) {
			retMap = SysUtils.getDefaultErrorMap();
		}
		
		SysUtils.returnJson(response, retMap);
	}
}
