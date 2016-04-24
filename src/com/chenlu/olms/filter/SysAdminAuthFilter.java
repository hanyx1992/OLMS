package com.chenlu.olms.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.chenlu.olms.util.GlobalConstraints;
import com.chenlu.olms.util.SysUtils;

/**
 * @desc:Ȩ��У��
 */
public class SysAdminAuthFilter extends OncePerRequestFilter{

	private static Log LOGGER = LogFactory.getLog(SysAdminAuthFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (isToLogin(request) || SysUtils.isLogined(request)) {
			filterChain.doFilter(request, response);
		} else {
			LOGGER.info("δ��¼����ת����¼ҳ��...");
			response.sendRedirect(request.getContextPath() + GlobalConstraints.REQUEST_URL.TOLOGIN);
		}
	}

	/**
	 * @desc: У���Ƿ�����ת��¼ҳ������ǵ�½����
	 * @param request
	 * @return
	 */
	private boolean isToLogin(HttpServletRequest request) {
		return (request.getContextPath() + GlobalConstraints.REQUEST_URL.TOLOGIN).equals(request.getRequestURI())
				|| (request.getContextPath() + GlobalConstraints.REQUEST_URL.LOGIN).equals(request.getRequestURI());
	}

}
