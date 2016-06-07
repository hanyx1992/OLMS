package com.chenlu.olms.controller;

import java.util.Date;
import java.util.Map;

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

/**
 * 公告相关控制器
 */
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
	 * 查询所有公告信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/find.do")
	public void findAll(HttpServletRequest request, HttpServletResponse response) {
		PageBean page = SysUtils.getPageInfo(request);
		
		PageRetInfo<Notice> retInfo = noticeSvc.findByCondition(page, null);
		SysUtils.returnJson(response, retInfo);
	}
	
	/**
	 * 添加公告
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/addNotice.do")
	public void addNotice(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String exp = request.getParameter("exp");
		String userName = SysUtils.getLoginedUser(request).getLoginName();
		
		Map<String, Object> retMap;
		try {
			noticeSvc.addNotice(title, content, userName,new Date(Long.parseLong(exp)));
			retMap = SysUtils.getDefaultSuccessMap();
		} catch (NumberFormatException e) {
			retMap = SysUtils.getDefaultErrorMap();
			retMap.put("errMsg", "失效时间不可以为空!");
		} catch (Exception e) {
			retMap = SysUtils.getDefaultErrorMap();
			retMap.put("errMsg", e.getMessage());
		}
		SysUtils.returnJson(response, retMap);
	}
	
	/**
	 * 删除公告
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/delNotice.do")
	public void delNotice(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String, Object> retMap;
		try {
			noticeSvc.deleteById(id);
			retMap = SysUtils.getDefaultSuccessMap();
		} catch (Exception e) {
			retMap = SysUtils.getDefaultErrorMap();
		}
		SysUtils.returnJson(response, retMap);
	}
	
}
