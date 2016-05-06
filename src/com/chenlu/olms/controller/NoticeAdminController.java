package com.chenlu.olms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chenlu.olms.bean.Notice;
import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;
import com.chenlu.olms.service.notice.INoticeSvc;
import com.chenlu.olms.util.SysUtils;

@Controller
@RequestMapping("/notice")
public class NoticeAdminController {
	
	@Autowired
	private INoticeSvc noticeSvc;

	@RequestMapping(value = "/index.do")
	public String homenotice(HttpServletRequest request) {
		return "/notice/notice";
	}
	
	/**
	 * 查询所有实验室信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/find.do")
	public void findAll(HttpServletRequest request, HttpServletResponse response) {
		PageBean page = SysUtils.getPageInfo(request);
		
		PageRetInfo<Notice> retInfo = noticeSvc.findByCondition(page, null);
		SysUtils.returnJson(response, retInfo);
	}
}
