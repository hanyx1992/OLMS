package com.chenlu.olms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chenlu.olms.bean.Laboratory;
import com.chenlu.olms.bean.PageBean;
import com.chenlu.olms.bean.PageRetInfo;
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
		PageBean page = SysUtils.getPageInfo(request);
		
		PageRetInfo<Laboratory> retInfo = laboratorySvc.findByCondition(page, null);
		SysUtils.returnJson(response, retInfo);
	}

//	/**
//	 * ��dao��ѯ��������תΪҳ����ʾ��Ϣ
//	 * @param findAll
//	 * @return
//	 */
//	private List<Map<String, Object>> createLaboratoryBeanForView(List<Laboratory> findAll) {
//		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//		if (findAll !=null && findAll.size() > 0) {
//			for (Laboratory lab : findAll) {
//				Map<String, Object> each = new HashMap<String, Object>();
//				each.put("labId", lab.getNo());
//				each.put("labName", lab.getName());
//				each.put("labDesc", lab.getDesc());
//				each.put("labLocation", lab.getLocation());
//				each.put("labState", lab.getStateStr());
//				list.add(each);
//			}
//		}
//		return list;
//	}
}
