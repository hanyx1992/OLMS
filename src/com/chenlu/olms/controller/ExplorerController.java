package com.chenlu.olms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chenlu.olms.util.SysUtils;

@Controller
@RequestMapping("/main")
public class ExplorerController {

	@RequestMapping(value = "/main.do")
	public String homepage(HttpServletRequest request) {
		request.setAttribute("auths", SysUtils.getLoginedUser(request).getAuths());
		return "/common/index";
	}
	
	@RequestMapping(value = "/index.do")
	public String homenotice(HttpServletRequest request) {
		return "/common/homenotice";
	}
}
