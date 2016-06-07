package com.chenlu.olms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chenlu.olms.bean.Occupy;
import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;
import com.chenlu.olms.service.occupy.IOccupySvc;
import com.chenlu.olms.util.GlobalConstraints;
import com.chenlu.olms.util.SysUtils;

/**
 * 租用审核相关控制器
 */
@Controller
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private IOccupySvc occupySvc;

	/**
	 * 页面跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index.do")
	public String index(HttpServletRequest request) {
		//将过期的申请转换状态.
		occupySvc.doTimeOut();
		return "/review/review";
	}
	
	/**
	 * 查询所有带审核的订单
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/findReview.do")
	public void findReview(HttpServletRequest request, HttpServletResponse response) {
		PageBean page = SysUtils.getPageInfo(request);
		Occupy condition = new Occupy();
		condition.setReviewState(GlobalConstraints.Data_ENUM.OCCUPY_REVIEW_WAIT);
		PageRetInfo<Occupy> retInfo = occupySvc.findByCondition(page, condition);
		SysUtils.returnJson(response, retInfo);
	}
	
	/**
	 * 查询所有订单
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/findOccupy.do")
	public void findOccupy(HttpServletRequest request, HttpServletResponse response) {
		PageBean page = SysUtils.getPageInfo(request);
		
		PageRetInfo<Occupy> retInfo = occupySvc.findByCondition(page, null);
		SysUtils.returnJson(response, retInfo);
	}
	
	/**
	 * 审核通过
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/ok.do")
	public void ok(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String, Object> retMap;
		try {
			occupySvc.doById(id, true, "");
			retMap = SysUtils.getDefaultSuccessMap();
		} catch (Exception e) {
			retMap = SysUtils.getDefaultErrorMap();
		}
		SysUtils.returnJson(response, retMap);
	}
	
	/**
	 * 审核不通过
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/no.do")
	public void no(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String desc = request.getParameter("desc");
		Map<String, Object> retMap;
		try {
			occupySvc.doById(id, false, desc);
			retMap = SysUtils.getDefaultSuccessMap();
		} catch (Exception e) {
			retMap = SysUtils.getDefaultErrorMap();
		}
		SysUtils.returnJson(response, retMap);
	}
}
