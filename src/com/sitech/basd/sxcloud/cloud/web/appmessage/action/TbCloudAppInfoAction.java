package com.sitech.basd.sxcloud.cloud.web.appmessage.action;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.appmessage.TbCloudAppInfoObj;
import com.sitech.basd.sxcloud.cloud.service.appmessage.TbCloudAppInfoService;
import com.sitech.basd.sxcloud.cloud.web.appmessage.form.TbCloudAppInfoForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class TbCloudAppInfoAction extends BaseAction {
	private TbCloudAppInfoService tbCloudAppInfoService;
	private TbCloudAppInfoForm theForm;

	public TbCloudAppInfoForm getTheForm() {
		return theForm;
	}

	public void setTheForm(TbCloudAppInfoForm theForm) {
		this.theForm = theForm;
	}

	public void setTbCloudAppInfoService(
			TbCloudAppInfoService tbCloudAppInfoService) {
		this.tbCloudAppInfoService = tbCloudAppInfoService;
	}

	public String queryBizsysAppList() {
		String id = Struts2Utils.getParameter("id");//得到应用Id
		TbCloudAppInfoObj obj = new TbCloudAppInfoObj();
		obj.setSys_id(id);
		List resultList = tbCloudAppInfoService.queryBizsysAppList(obj);
		if (theForm == null) {
			theForm = new TbCloudAppInfoForm();
		}
		theForm.setResultList(resultList);
		return "queryBizsysAppList";
	}
}
