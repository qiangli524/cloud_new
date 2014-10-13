package com.sitech.basd.sxcloud.rsmu.web.deploy.action;

import java.util.List;



import com.sitech.basd.sxcloud.rsmu.web.deploy.form.DeployAlarmForm;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployAlarmObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployAlarmService; 
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
@SuppressWarnings("serial")
public class DeployAlarmAction  extends CRUDBaseAction {

	
	/**
	 * @Title:得到异常告警日志列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listDeployAlarm() {
		if (theForm == null) {
			theForm = new DeployAlarmForm();
		}
		TbBusiDeployAlarmObj obj = new TbBusiDeployAlarmObj();
		if (theForm.getStart_time() != null
				&& !"".equals(theForm.getStart_time())) {
			obj.setStart_time(theForm.getStart_time());
		}
		if (theForm.getEnd_time() != null
				&& !"".equals(theForm.getEnd_time())) {
			obj.setEnd_time(theForm.getEnd_time());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List resultList = this.deployAlarmService.queryForListByObj(obj);
		theForm.setResultList(resultList);
		return LIST;
	}

	private DeployAlarmForm theForm;
	
	
	public DeployAlarmForm getTheForm() {
		return theForm;
	}
	public void setTheForm(DeployAlarmForm theForm) {
		this.theForm = theForm;
	}

	DeployAlarmService deployAlarmService;
	public void setDeployAlarmService(DeployAlarmService deployAlarmService) {
		this.deployAlarmService = deployAlarmService;
	}
	
}
