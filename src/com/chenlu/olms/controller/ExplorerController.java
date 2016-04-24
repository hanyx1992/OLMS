package com.chenlu.olms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class ExplorerController {

	@RequestMapping(value = "/main.do")
	public String homepage(HttpServletRequest request) {
		return "/common/index";
	}
}
