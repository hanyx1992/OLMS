package com.chenlu.olms.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;
import com.chenlu.olms.bean.User;
import com.chenlu.olms.service.user.IUserSvc;
import com.chenlu.olms.util.GlobalConstraints;
import com.chenlu.olms.util.SysUtils;

@Controller
@RequestMapping("/auth-mag")
public class AuthManageController {
	@Autowired
	private IUserSvc userSvc;

	@RequestMapping(value = "/index.do")
	public String index(HttpServletRequest request) {
		return "/auth-mag/auth-mag";
	}
	
	@RequestMapping(value = "/find.do")
	public void find(HttpServletRequest request, HttpServletResponse response) {
		PageBean page = SysUtils.getPageInfo(request);
		PageRetInfo<User> retInfo = userSvc.findByCondition(page, null);
		SysUtils.returnJson(response, retInfo);
	}
	
	@RequestMapping(value = "/getAuth.do")
	public void getAuth(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> retMap;
		try {
			retMap = SysUtils.getDefaultSuccessMap();
			String id = request.getParameter("id");
			String[] auths = userSvc.findById(id).getAuths();
			Map<String, Object> authdata = new HashMap<String, Object>();
			for (int i = 0; i < GlobalConstraints.Data_ENUM.USER_AUTH_KEY.length; i++) {
				String key = GlobalConstraints.Data_ENUM.USER_AUTH_KEY[i];
				String value = GlobalConstraints.Data_ENUM.USER_AUTH_STR[i];
				authdata.put(key, value);
			}
			retMap.put("auths", auths);
			retMap.put("authdata", authdata);
		} catch (Exception e) {
			retMap = SysUtils.getDefaultErrorMap();
		}
		SysUtils.returnJson(response, retMap);
	}
	
	@RequestMapping(value = "/setAuth.do")
	public void setAuth(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> retMap;
		try {
			retMap = SysUtils.getDefaultSuccessMap();
			String id = request.getParameter("id");
			String auths = request.getParameter("auths");
			
			User user = userSvc.findById(id);
			
			List<String> authList = new ArrayList<String>();
			authList.add(GlobalConstraints.Data_ENUM.USER_AUTHS_ALL[0]);
			authList.add(GlobalConstraints.Data_ENUM.USER_AUTHS_ALL[1]);

			if (!StringUtils.isEmpty(auths)) {
				auths = auths.substring(0,auths.length()-1);//去掉最后的分隔符
				String[] eachAuth = auths.split(","); 
				authList.addAll(Arrays.asList(eachAuth));
			}
			authList.add(GlobalConstraints.Data_ENUM.USER_AUTHS_ALL[GlobalConstraints.Data_ENUM.USER_AUTHS_ALL.length-1]);
			
			String[] newAuth = new String[authList.size()];
			for (int i = 0; i < authList.size(); i++) {
				newAuth[i] = authList.get(i);
			}
			user.setAuths(newAuth);
			
			userSvc.save(user);
			
		} catch (Exception e) {
			retMap = SysUtils.getDefaultErrorMap();
		}
		SysUtils.returnJson(response, retMap);
	}
	
	@RequestMapping(value = "/blk.do")
	public void blk(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> retMap;
		try {
			retMap = SysUtils.getDefaultSuccessMap();
			String id = request.getParameter("id");
			User user = userSvc.findById(id);
			String[] auths = user.getAuths();
			List<String> authList = new ArrayList<String>(Arrays.asList(auths));
			authList.remove(GlobalConstraints.Data_ENUM.USER_AUTH_LAB);
			String[] newAuth = new String[authList.size()];
			user.setAuths(authList.toArray(newAuth));
			userSvc.save(user);
		} catch (Exception e) {
			retMap = SysUtils.getDefaultErrorMap();
		}
		SysUtils.returnJson(response, retMap);
	}
	
	@RequestMapping(value = "/cancelblk.do")
	public void cancelblk(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> retMap;
		try {
			retMap = SysUtils.getDefaultSuccessMap();
			String id = request.getParameter("id");
			User user = userSvc.findById(id);
			String[] auths = user.getAuths();
			List<String> authList = new ArrayList<String>(Arrays.asList(auths));
			authList.add(1, GlobalConstraints.Data_ENUM.USER_AUTH_LAB);
			String[] newAuth = new String[authList.size()];
			user.setAuths(authList.toArray(newAuth));
			userSvc.save(user);
		} catch (Exception e) {
			retMap = SysUtils.getDefaultErrorMap();
		}
		SysUtils.returnJson(response, retMap);
	}
}
