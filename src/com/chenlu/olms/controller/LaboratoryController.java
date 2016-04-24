package com.chenlu.olms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chenlu.olms.bean.Laboratory;
import com.chenlu.olms.service.laboratory.ILaboratorySvc;
import com.chenlu.olms.util.SysUtils;

/**
 * ����ʵ�����������
 */
@Controller
@RequestMapping("/laboratory")
public class LaboratoryController {

	@Autowired
	ILaboratorySvc laboratorySvc;
	
	/**
	 * ��ѯ����ʵ������Ϣ
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/find.do")
	public void findAll(HttpServletRequest request, HttpServletResponse response) {
		List<Laboratory> list = laboratorySvc.findAll();
		
		SysUtils.returnJsonArray(response, list);
	}
}
