package com.chenlu.olms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chenlu.olms.bean.Occupy;
import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;
import com.chenlu.olms.bean.User;
import com.chenlu.olms.service.laboratory.ILaboratorySvc;
import com.chenlu.olms.service.occupy.IOccupySvc;
import com.chenlu.olms.service.schedule.IScheduleSvc;
import com.chenlu.olms.service.user.IUserSvc;
import com.chenlu.olms.util.GlobalConstraints;
import com.chenlu.olms.util.SysUtils;

/**
 * 我的
 */
@Controller
@RequestMapping("/info")
public class ProfileController {
	@Autowired
	private IOccupySvc occupySvc;
	
	@Autowired
	private IScheduleSvc scheduleSvc;
	
	@Autowired
	private ILaboratorySvc laboratorySvc;
	
	@Autowired
	private IUserSvc userSvc;
	
	/**
	 * 载入页面要显示的信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index.do")
	public String index(HttpServletRequest request) {
		User user = SysUtils.getLoginedUser(request);
		switch (user.getRole()) {
		case GlobalConstraints.Data_ENUM.USER_ROLE_STUDENT:
			//将过期的申请转换状态.
			occupySvc.doTimeOut();
			return "/info/info_stu";
		case GlobalConstraints.Data_ENUM.USER_ROLE_TEACHER:
			//授课实验室
			String name = "无";
			if(!StringUtils.isEmpty(user.getClzName())){
				name =  " : " + laboratorySvc.findById(user.getClzName()).getName();
				request.setAttribute("hasClass", true);
				request.setAttribute("num", GlobalConstraints.SYS_PARAM.MAX_SCHEDULE);
			}
			request.setAttribute("clzLabName", name);
			return "/info/info_tea";
		case GlobalConstraints.Data_ENUM.USER_ROLE_ADMINISTRATOR:
			return "/info/info_adm";
		}
		return null;
	}
	
	@RequestMapping(value = "/pwd.do")
	public void changePwd(HttpServletRequest request,  HttpServletResponse response) {
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		User user = SysUtils.getLoginedUser(request);
		try {
			userSvc.changepwd(user, oldPwd, newPwd);
			SysUtils.returnJson(response, SysUtils.getDefaultSuccessMap());
		} catch (Exception e) {
			Map<String, Object> map = SysUtils.getDefaultErrorMap();
			map.put("errMsg", e.getMessage());
			SysUtils.returnJson(response, map);
		}
	}
	
	/**
	 * 订单查询
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/stuFind.do")
	public void findStudentOccupyRecords(HttpServletRequest request,  HttpServletResponse response) {
		PageBean page = SysUtils.getPageInfo(request);
		PageRetInfo<Occupy> retInfo = occupySvc.findByUserLoginName(page, SysUtils.getLoginedUser(request).getLoginName());
		SysUtils.returnJson(response, retInfo);
	}
	
	/**
	 * 取消预占
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/cancelOccupy.do")
	public void cancelOccupy(HttpServletRequest request,  HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			occupySvc.cancelOccupy(id);
			SysUtils.returnJson(response, SysUtils.getDefaultSuccessMap());
		} catch (Exception e) {
			SysUtils.returnJson(response, SysUtils.getDefaultErrorMap());
		}
		
	}
	
	/**
	 * 增加课程
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/addClz.do")
	public void add(HttpServletRequest request,  HttpServletResponse response) {
		try {
			String no = SysUtils.getLoginedUser(request).getClzName();
			int day = Integer.parseInt(request.getParameter("day"));
			int num = Integer.parseInt(request.getParameter("num"));
			String clzName = request.getParameter("clzName");
			String desc = request.getParameter("desc");
			
			scheduleSvc.addClz(no, day, num, clzName, desc);
			
			SysUtils.returnJson(response, SysUtils.getDefaultSuccessMap());
		} catch (Exception e) {
			SysUtils.returnJson(response, SysUtils.getDefaultErrorMap());
		}
	}
	
	/**
	 * 删除学生
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/delClz.do")
	public void del(HttpServletRequest request,  HttpServletResponse response) {
		try {
			String no = SysUtils.getLoginedUser(request).getClzName();
			int day = Integer.parseInt(request.getParameter("day"));
			int num = Integer.parseInt(request.getParameter("num"));
			
			scheduleSvc.delClz(no, day, num);
			
			SysUtils.returnJson(response, SysUtils.getDefaultSuccessMap());
		} catch (Exception e) {
			SysUtils.returnJson(response, SysUtils.getDefaultErrorMap());
		}
	}
}
