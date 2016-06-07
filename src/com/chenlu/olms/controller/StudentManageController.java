package com.chenlu.olms.controller;

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
import com.chenlu.olms.util.JBcrypt;
import com.chenlu.olms.util.SysUtils;

/**
 * ѧ������
 */
@Controller
@RequestMapping("/stu-mag")
public class StudentManageController {
	@Autowired
	private IUserSvc userSvc;
	
	/**
	 * ����ҳ��
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index.do")
	public String index(HttpServletRequest request) {
		return "stu-mag/stu-mag";
	}
	
	/**
	 * ��ѯ����ѧ��
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/find.do")
	public void find(HttpServletRequest request, HttpServletResponse response) {
		PageBean page = SysUtils.getPageInfo(request);
		User condition = new User();
		condition.setRole(GlobalConstraints.Data_ENUM.USER_ROLE_STUDENT);
		PageRetInfo<User> retInfo = userSvc.findByCondition(page, condition);
		SysUtils.returnJson(response, retInfo);
	}
	
	/**
	 * ����ѧ��
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/add.do")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String loginName = request.getParameter("loginName");
		String realName = request.getParameter("realName");
		String clzName = request.getParameter("clzName");
		Map<String, Object> retMap;
		try {
			retMap = SysUtils.getDefaultSuccessMap();
			//У����Ϣ�Ƿ�Ϸ�,����Ϊ��,���ߵ�¼���ظ�
			if (checkInfo(id, loginName, realName, clzName, retMap)) {
				retMap.put("s", true);
				User user;
				if (!StringUtils.isEmpty(id)) {
					//��ʾ���޸�һ��ѧ��������
					user = userSvc.findById(id);
				} else {
					//��ʾ����һ��ѧ��������
					user = new User();
					user.setRole(GlobalConstraints.Data_ENUM.USER_ROLE_STUDENT);
					user.setLoginPwd(JBcrypt.hashpw("123456", JBcrypt.gensalt()));
					user.setLoginName(loginName);
				}
				user.setRealName(realName);
				user.setClzName(clzName);
				userSvc.save(user);
			}
		} catch (Exception e) {
			retMap = SysUtils.getDefaultErrorMap();
		}
		SysUtils.returnJson(response, retMap);
	}

	private boolean checkInfo(String id, String loginName,
			String realName, String clzName, Map<String, Object> retMap) {
		if (StringUtils.isEmpty(loginName)) {
			retMap.put("s", false);
			retMap.put("e", "��¼������Ϊ��!");
			return false;
		}
		if (StringUtils.isEmpty(realName)) {
			retMap.put("s", false);
			retMap.put("e", "��������Ϊ��!");
			return false;
		}
		if (StringUtils.isEmpty(clzName)) {
			retMap.put("s", false);
			retMap.put("e", "�༶����Ϊ��!");
			return false;
		}
		if (StringUtils.isEmpty(id) && userSvc.findById(loginName) != null) {
			retMap.put("s", false);
			retMap.put("e", "��¼�������ظ�!");
			return false;
		}
		if (!StringUtils.isEmpty(id) && !id.equals(loginName)) {
			retMap.put("s", false);
			retMap.put("e", "��¼�������޸�!");
			return false;
		}
		return true;
	}
	
	/**
	 * ɾ��ѧ��
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/del.do")
	public void del(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String, Object> retMap;
		try {
			userSvc.deleteById(id);
			retMap = SysUtils.getDefaultSuccessMap();
		} catch (Exception e) {
			retMap = SysUtils.getDefaultErrorMap();
		}
		SysUtils.returnJson(response, retMap);
	}
	
	/**
	 * ��������
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/reset.do")
	public void resetPwd(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String, Object> retMap;
		try {
			userSvc.resetPwdById(id);
			retMap = SysUtils.getDefaultSuccessMap();
		} catch (Exception e) {
			retMap = SysUtils.getDefaultErrorMap();
		}
		SysUtils.returnJson(response, retMap);
	}
}
