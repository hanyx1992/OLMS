package com.chenlu.olms.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @desc:主要功能的工具类
 */
public final class SysUtils {

	private SysUtils() {};
	
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
}
