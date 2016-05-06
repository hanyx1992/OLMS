package com.chenlu.olms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chenlu.olms.bean.Notice;
import com.chenlu.olms.service.notice.INoticeSvc;
import com.chenlu.olms.util.SysUtils;

@Controller
@RequestMapping("/main")
public class ExplorerController {
	
	@Autowired
	private INoticeSvc noticeSvc;

	@RequestMapping(value = "/main.do")
	public String homepage(HttpServletRequest request) {
		request.setAttribute("auths", SysUtils.getLoginedUser(request).getAuths());
		return "/common/index";
	}
	
	@RequestMapping(value = "/index.do")
	public String homenotice(HttpServletRequest request) {
		List<Notice> list = noticeSvc.findAllNoticeForDisplay();
		request.setAttribute("allNotice", list);
		return "/common/homenotice";
	}
}
