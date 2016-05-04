package com.chenlu.olms.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.chenlu.olms.bean.User;
import com.chenlu.olms.util.GlobalConstraints;
import com.chenlu.olms.util.SysUtils;

/**
 * @desc:权限校验
 */
public class SysAdminAuthFilter extends OncePerRequestFilter{

	private static Log LOGGER = LogFactory.getLog(SysAdminAuthFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		boolean isTologin = isToLogin(request);
		if (!isTologin && !SysUtils.isLogined(request)) {
			LOGGER.info("未登录，跳转至登录页面...");
			response.sendRedirect(request.getContextPath() + GlobalConstraints.REQUEST_URL.TOLOGIN);
		} else if(!isTologin && !hasAuth(request)) {
			LOGGER.info("用户没有这个请求的权限...");
			response.sendRedirect(request.getContextPath() + GlobalConstraints.REQUEST_URL.AUTH);
		} else {
			filterChain.doFilter(request, response);
		}
	}

	/**
	 * @desc: 校验是否是跳转登录页面或者是登陆请求
	 * @param request
	 * @return
	 */
	private boolean isToLogin(HttpServletRequest request) {
		return (request.getContextPath() + GlobalConstraints.REQUEST_URL.TOLOGIN).equals(request.getRequestURI())
				|| (request.getContextPath() + GlobalConstraints.REQUEST_URL.LOGIN).equals(request.getRequestURI())
				|| (request.getContextPath() + GlobalConstraints.REQUEST_URL.AUTH).equals(request.getRequestURI());
	}
	
	/**
	 * 校验当前登录的用户是否有访问这个请求的权限
	 * @param request
	 * @return
	 */
	private boolean hasAuth(HttpServletRequest request) {
		String url = request.getRequestURI().replaceFirst(request.getContextPath(), "").substring(1);
		int i = url.indexOf("/",1);
		if (i > 1) {
			url = url.substring(0, i);
		}
		
		User user = SysUtils.getLoginedUser(request);
		String[] auths = user.getAuths();
		
		if (auths != null && auths.length > 0) {
			for (String path : auths) {
				if (path.equalsIgnoreCase(url)) {
					return true;
				}
			}
		}
		
		return false;
	}

}
