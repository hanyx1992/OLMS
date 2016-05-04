package com.chenlu.olms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chenlu.olms.bean.User;
import com.chenlu.olms.service.user.IUserSvc;
import com.chenlu.olms.util.SysUtils;

@Controller
public class SysLoginController {

	@Autowired
	private IUserSvc userSvc;
	
	@RequestMapping(value = "/noauth.do")
	public String noauth() {
		return "noAuth";
	}
	
	@RequestMapping(value = "/login.do")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/verify.do")
	public void verify(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		String checkcode = request.getParameter("message");
		String number = (String) request.getSession().getAttribute("number");
		if (StringUtils.isEmpty(checkcode) || !checkcode.equalsIgnoreCase(number)) {
			retMap.put("success", false);
			retMap.put("errMsg", "验证码错误!");
		} else {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			User user = userSvc.doLoginCheck(username, password);
			if (user == null) {
				retMap.put("success", false);
				retMap.put("errMsg", "用户名或者密码错误!");
			} else {
				request.getSession().invalidate();
				SysUtils.setLoginedUser(request, user);
				retMap.put("success", true);
			}
		}
		
		SysUtils.returnJson(response, retMap);
	}
}
