package com.sitech.basd.sxcloud.cloud.web.eventmanage.action;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.eventmanage.EventManageObj;
import com.sitech.basd.sxcloud.cloud.service.eventmanage.EventManageService;
import com.sitech.basd.sxcloud.cloud.web.eventmanage.form.EventManageForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class EventManageAction extends BaseAction {
	private EventManageForm theForm;

	public EventManageForm getTheForm() {
		return theForm;
	}

	public void setTheForm(EventManageForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listEventManage() throws BaseException {
		if (theForm == null) {
			theForm = new EventManageForm();
		}
		EventManageObj obj = new EventManageObj();
		if (theForm.getEVENT_ID() != null && !"".equals(theForm.getEVENT_ID())) {
			obj.setEVENT_ID(theForm.getEVENT_ID().trim());
		}
		if (theForm.getEVENT_INFO() != null
				&& !"".equals(theForm.getEVENT_INFO())) {
			obj.setEVENT_INFO(theForm.getEVENT_INFO().trim());
		}
		if (theForm.getSERIOUS() != null && !"".equals(theForm.getSERIOUS())) {
			obj.setSERIOUS(theForm.getSERIOUS().trim());
		}
		if (theForm.getStart_time() != null
				&& !theForm.getStart_time().equals("")) {
			obj.setStart_eventTime(theForm.getStart_time());
		}
		if (theForm.getEnd_time() != null && !theForm.getEnd_time().equals("")) {
			obj.setEnd_eventTime(theForm.getEnd_time());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List eventManageList = eventManageService.queryForListByObj(obj);
		theForm.setResultList(eventManageList);

		return SUCCESS;

	}

	private EventManageService eventManageService;

	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}
}
