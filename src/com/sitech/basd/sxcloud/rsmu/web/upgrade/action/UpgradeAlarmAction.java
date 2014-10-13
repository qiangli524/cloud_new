package com.sitech.basd.sxcloud.rsmu.web.upgrade.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.sxcloud.rsmu.domain.upgrade.UpgradeAlarmObj;
import com.sitech.basd.sxcloud.rsmu.service.upgrade.UpgradeAlarmService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.upgrade.form.UpgradeAlarmForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class UpgradeAlarmAction extends CRUDBaseAction {

	/**
	 * @Title:查询出所有告警信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String upgradeAlarmList() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		UpgradeAlarmObj obj = new UpgradeAlarmObj();
		if(theForm==null){
			theForm = new UpgradeAlarmForm();
		}
		if (theForm.getStart_time() != null
				&& !"".equals(theForm.getStart_time())) {
			obj.setStart_time(theForm.getStart_time());
		}
		if (theForm.getEnd_time() != null && !"".equals(theForm.getEnd_time())) {
			obj.setEnd_time(theForm.getEnd_time());
		}
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List list = upgradeAlarmService.queryForListByObj(obj);
		theForm.setResultList(list);
		return LIST;
	}

	UpgradeAlarmService upgradeAlarmService;
	private UpgradeAlarmForm theForm;

	public UpgradeAlarmForm getTheForm() {
		return theForm;
	}

	public void setTheForm(UpgradeAlarmForm theForm) {
		this.theForm = theForm;
	}

	public void setUpgradeAlarmService(UpgradeAlarmService upgradeAlarmService) {
		this.upgradeAlarmService = upgradeAlarmService;
	}

}
