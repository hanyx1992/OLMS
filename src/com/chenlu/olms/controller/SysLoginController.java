package com.chenlu.olms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SysLoginController {

	@RequestMapping(value = "/login.do")
	public String login(HttpServletRequest request) {
		return "login";
	}
	
	@RequestMapping(value = "/verify.do")
	public String verify(HttpServletRequest request) {
		return "main";
	}
}
