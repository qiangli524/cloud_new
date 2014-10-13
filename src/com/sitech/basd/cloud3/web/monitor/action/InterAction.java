package com.sitech.basd.cloud3.web.monitor.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.cloud3.domain.monitor.InterfaceDetailObj;
import com.sitech.basd.cloud3.domain.monitor.InterfaceInfoObj;
import com.sitech.basd.cloud3.service.monitor.InterService;
import com.sitech.basd.cloud3.web.monitor.form.InterForm;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class InterAction {
	private InterService interService;
	private InterForm theForm;

	public InterForm getTheForm() {
		return theForm;
	}

	public void setTheForm(InterForm theForm) {
		this.theForm = theForm;
	}

	public void setInterService(InterService interService) {
		this.interService = interService;
	}

	/**
	 * 
	 * @Title: getDetail
	 * @Description: ��ѯ�ӿڵ��õ���ϸ���
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jan 9, 2013 4:13:46 PM
	 */
	public String getDetail() {
		if (theForm == null) {
			theForm = new InterForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String inter_id = request.getParameter("inter_id");
		InterfaceDetailObj obj = new InterfaceDetailObj();
		obj.setInter_id(inter_id);
		List list = interService.queryForListByObj(obj);
		theForm.setResultList(list);
		return "detail";

	}

	/**
	 * 
	 * @Title: listInterInfo
	 * @Description: ��ʾ���ֽӿ�
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jan 9, 2013 6:58:43 PM
	 */
	public String listInterInfo() {
		if (theForm == null) {
			theForm = new InterForm();
		}
		InterfaceInfoObj obj = new InterfaceInfoObj();
		List list = interService.queryForList(obj);
		theForm.setResultList(list);
		return "list";
	}
}
