package com.chenlu.olms.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.User;
import com.chenlu.olms.service.user.IUserSvc;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @desc:主要功能的工具类
 */
public final class SysUtils {

	private SysUtils() {};

	/**
	 * 获取页面分页信息,如果没传会提供一个默认的信息.
	 * @param request
	 * @return
	 */
	public static PageBean getPageInfo(HttpServletRequest request) {
		//分页信息
		String page = request.getParameter("page");
		String rowsSize = request.getParameter("rows");
		int pageNo = 0;
		int pageSize = 10;
		try {
			pageSize = Integer.parseInt(rowsSize);
			pageNo = Integer.parseInt(page);
		} catch (NumberFormatException e) {
			//报错表示没传参 无视
		}
		
		PageBean info = new PageBean(pageNo, pageSize);
		return info;
	}
	
	/**
	 * @desc: 管理员是否登录
	 * @param request
	 * @return
	 */
	public static boolean isLogined (HttpServletRequest request) {
		//空校验
		if (request == null || request.getSession() == null) {
			return false;
		}
		return request.getSession().getAttribute(GlobalConstraints.SESSION_KEY.USER)!=null;
	}
	
	/**
	 * 设置Session中登录的用户实体
	 * @param request
	 * @param user
	 */
	public static void setLoginedUser(HttpServletRequest request, User user) {
		request.getSession(true).setAttribute(GlobalConstraints.SESSION_KEY.USER, user);
	}
	
	/**
	 * 从Session中获取登录的用户实体
	 * @param request
	 * @return
	 */
	public static User getLoginedUser(HttpServletRequest request) {
		return (User) request.getSession(true).getAttribute(GlobalConstraints.SESSION_KEY.USER);
	}
	
	/**
	 * 返回json
	 * @param response
	 * @param responseObject
	 */
	public static void returnJson(HttpServletResponse response,
			Object responseObject) {
		//将实体对象转换为JSON Object转换
		JSONObject responseJSONObject = JSONObject.fromObject(responseObject);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(responseJSONObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * 返回json
	 * @param response
	 * @param responseObject
	 */
	public static void returnJsonArray(HttpServletResponse response,
			Object responseObject) {
		//将实体对象转换为JSON Object转换
		JSONArray responseJSONObject = JSONArray.fromObject(responseObject);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(responseJSONObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	public static Map<String, Object> getDefaultErrorMap() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("success", false);
		retMap.put("errMsg", "对不起,发生错误了,请重试!");
		return retMap;
	}
	public static Map<String, Object> getDefaultSuccessMap() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("success", true);
		return retMap;
	}
	
	public static String getUserNameByLoginName(String loginName) {
		IUserSvc userSvc = (IUserSvc) SpringBeanUtil.getBean("userSvc");
		if (StringUtils.isEmpty(loginName)) {
			return "-";
		}
		User user = userSvc.findById(loginName);
		if (user != null) {
			return user.getRealName();
		} else {
			return "--";
		}
	}
}
