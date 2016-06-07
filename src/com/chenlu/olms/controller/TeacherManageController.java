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
import com.chenlu.olms.service.laboratory.ILaboratorySvc;
import com.chenlu.olms.service.user.IUserSvc;
import com.chenlu.olms.util.GlobalConstraints;
import com.chenlu.olms.util.JBcrypt;
import com.chenlu.olms.util.SysUtils;

/**
 * 教师管理
 */
@Controller
@RequestMapping("/tea-mag")
public class TeacherManageController {
	@Autowired
	private IUserSvc userSvc;
	@Autowired
	private ILaboratorySvc laboratorySvc;
	
	/**
	 * 页面跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index.do")
	public String index(HttpServletRequest request) {
		return "tea-mag/tea-mag";
	}
	
	/**
	 * 查询所有教师
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/find.do")
	public void find(HttpServletRequest request, HttpServletResponse response) {
		PageBean page = SysUtils.getPageInfo(request);
		User condition = new User();
		condition.setRole(GlobalConstraints.Data_ENUM.USER_ROLE_TEACHER);
		PageRetInfo<User> retInfo = userSvc.findByCondition(page, condition);
		SysUtils.returnJson(response, retInfo);
	}
	
	/**
	 * 增加教师
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
			//校验信息是否合法,比如为空,或者登录名重复
			if (checkInfo(id, loginName, realName, clzName, retMap)) {
				retMap.put("s", true);
				User user;
				if (!StringUtils.isEmpty(id)) {
					//表示是修改
					user = userSvc.findById(id);
				} else {
					//表示新增
					user = new User();
					user.setRole(GlobalConstraints.Data_ENUM.USER_ROLE_TEACHER);
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
			retMap.put("e", "登录名不能为空!");
			return false;
		}
		if (StringUtils.isEmpty(realName)) {
			retMap.put("s", false);
			retMap.put("e", "姓名不能为空!");
			return false;
		}
		if (!StringUtils.isEmpty(clzName) && laboratorySvc.findById(clzName) == null) {
			retMap.put("s", false);
			retMap.put("e", "没有找到编号为:"+clzName+"的实验室!</br>提示:教师可以不是任课教师.");
			return false;
		}
		if (StringUtils.isEmpty(id) && userSvc.findById(loginName) != null) {
			retMap.put("s", false);
			retMap.put("e", "登录名不能重复!");
			return false;
		}
		if (!StringUtils.isEmpty(id) && !id.equals(loginName)) {
			retMap.put("s", false);
			retMap.put("e", "登录名不能修改!");
			return false;
		}
		return true;
	}
	
	/**
	 * 删除教师
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
	 * 重置密码
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
