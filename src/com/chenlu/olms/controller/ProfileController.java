package com.chenlu.olms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chenlu.olms.bean.Occupy;
import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;
import com.chenlu.olms.bean.User;
import com.chenlu.olms.service.occupy.IOccupySvc;
import com.chenlu.olms.util.GlobalConstraints;
import com.chenlu.olms.util.SysUtils;

@Controller
@RequestMapping("/info")
public class ProfileController {
	@Autowired
	private IOccupySvc occupySvc;

	@RequestMapping(value = "/index.do")
	public String index(HttpServletRequest request) {
		User user = SysUtils.getLoginedUser(request);
		switch (user.getRole()) {
		case GlobalConstraints.Data_ENUM.USER_ROLE_STUDENT:
			//将过期的申请转换状态.
			occupySvc.doTimeOut();
			return "/info/info_stu";
		case GlobalConstraints.Data_ENUM.USER_ROLE_TEACHER:
			return "/info/info_tea";
		case GlobalConstraints.Data_ENUM.USER_ROLE_ADMINISTRATOR:
			return "/info/info_adm";
		}
		return null;
	}
	
	@RequestMapping(value = "/stuFind.do")
	public void findStudentOccupyRecords(HttpServletRequest request,  HttpServletResponse response) {
		PageBean page = SysUtils.getPageInfo(request);
		PageRetInfo<Occupy> retInfo = occupySvc.findByUserLoginName(page, SysUtils.getLoginedUser(request).getLoginName());
		SysUtils.returnJson(response, retInfo);
	}
	
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
}
