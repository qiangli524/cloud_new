package com.sitech.basd.sxcloud.rsmu.web.deploy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3OnlineHistoryVO;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.version.OnlineHistoryService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.deploy.form.OnlineHistoryForm;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class OnlineHistoryAction extends CRUDBaseAction {

	AppService appService;

	OnlineHistoryService onlineHistoryService;

	private OnlineHistoryForm theForm;

	public OnlineHistoryForm getTheForm() {
		return theForm;
	}

	public void setTheForm(OnlineHistoryForm theForm) {
		this.theForm = theForm;
	}

	public void setOnlineHistoryService(
			OnlineHistoryService onlineHistoryService) {
		this.onlineHistoryService = onlineHistoryService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	/**
	 * @Title:得到部署实例管理历史信息列表
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String listOnlineHistory() {
		if (theForm == null) {
			theForm = new OnlineHistoryForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud3OnlineHistoryVO obj = new TbCloud3OnlineHistoryVO();
		// if (theForm.getAPPNAME() != null && !"".equals(theForm.getAPPNAME()))
		// {
		// obj.set(theForm.getAPPNAME());
		// }
		// if (theForm.getIP() != null && !"".equals(theForm.getIP())) {
		// obj.setIP(theForm.getIP());
		// }
		TbSysAppObj appObj = new TbSysAppObj();
		List appList = appService.queryForListByObj(appObj);

		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		// obj.setDATAAUTHORITY(userInfoObj.getDATAAUTHORITY());
		List onlineHisList = this.onlineHistoryService.queryForListByObj(obj);
		theForm.setOnlineHisList(onlineHisList);
		// theForm.setAppList(appList); // 应用集合
		return LIST;
	}

}
