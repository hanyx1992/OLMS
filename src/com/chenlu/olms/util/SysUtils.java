package com.chenlu.olms.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chenlu.olms.bean.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @desc:��Ҫ���ܵĹ�����
 */
public final class SysUtils {

	private SysUtils() {};
	
	/**
	 * ��ȡҳ���ҳ��Ϣ,���û�����ṩһ��Ĭ�ϵ���Ϣ.
	 * @param request
	 * @return
	 */
	public static PageBean getPageInfo(HttpServletRequest request) {
		//��ҳ��Ϣ
		String page = request.getParameter("page");
		String rowsSize = request.getParameter("rows");
		int pageNo = 0;
		int pageSize = 10;
		try {
			pageSize = Integer.parseInt(rowsSize);
			pageNo = Integer.parseInt(page);
		} catch (NumberFormatException e) {
			//�����ʾû���� ����
		}
		
		PageBean info = new PageBean(pageNo, pageSize);
		return info;
	}
	
	/**
	 * @desc: ����Ա�Ƿ��¼
	 * @param request
	 * @return
	 */
	public static boolean isLogined (HttpServletRequest request) {
		//��У��
		if (request == null || request.getSession() == null) {
			return false;
		}
		return request.getSession().getAttribute(GlobalConstraints.SESSION_KEY.USER)!=null;
	}
	
	/**
	 * ����json
	 * @param response
	 * @param responseObject
	 */
	public static void returnJson(HttpServletResponse response,
			Object responseObject) {
		//��ʵ�����ת��ΪJSON Objectת��
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
	 * ����json
	 * @param response
	 * @param responseObject
	 */
	public static void returnJsonArray(HttpServletResponse response,
			Object responseObject) {
		//��ʵ�����ת��ΪJSON Objectת��
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
