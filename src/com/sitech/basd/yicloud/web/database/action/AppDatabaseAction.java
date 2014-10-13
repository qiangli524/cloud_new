package com.sitech.basd.yicloud.web.database.action;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.appmessage.TbCloudAppInfoObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.database.AppDatabaseObj;
import com.sitech.basd.yicloud.service.database.AppDatabaseService;
import com.sitech.basd.yicloud.web.database.form.AppDatabaseForm;

public class AppDatabaseAction extends CRUDBaseAction {

	private AppDatabaseForm theForm;
	private AppDatabaseService appDatabaseService;

	public void setAppDatabaseService(AppDatabaseService appDatabaseService) {
		this.appDatabaseService = appDatabaseService;
	}

	public AppDatabaseForm getTheForm() {
		return theForm;
	}

	public void setTheForm(AppDatabaseForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: 获取数据库列表
	 * @Copyright: Copyright (c) 2012-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String listDataBase() {
		if (theForm == null) {
			theForm = new AppDatabaseForm();
		}
		String id = Struts2Utils.getParameter("id");
		TbCloudAppInfoObj obj = new TbCloudAppInfoObj();
		if (id != null && !"".equals(id)) {
			obj.setId(Integer.parseInt(id));
		}
		List<AppDatabaseObj> resultList = appDatabaseService
				.queryBizSysDataBase(obj);
		theForm.setResultList(resultList);
		return SUCCESS;
	}
}
