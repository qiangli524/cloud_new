package com.sitech.basd.sxcloud.rsmu.web.deploy.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.rsmu.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleHisObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployExampleHisService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.deploy.form.DeployExampleHisForm;
import com.sitech.basd.sxcloud.util.Struts2Utils;


public class DeployExampleHisAction extends CRUDBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8182273931965583704L;

	/**
	 * @Title:得到部署实例管理历史信息列表
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String listDeployExampleHis() {
		if (theForm == null) {
			theForm = new DeployExampleHisForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbBusiDeployExampleHisObj obj = new TbBusiDeployExampleHisObj();
		if (theForm.getAPPNAME() != null
				&& !"".equals(theForm.getAPPNAME())) {
			obj.setAPPNAME(theForm.getAPPNAME());
		}
		if (theForm.getIP() != null
				&& !"".equals(theForm.getIP())) {
			obj.setIP(theForm.getIP());
		}
		TbSysAppObj appObj = new TbSysAppObj();
		List appList = appService.queryForListByObj(appObj);

		obj.setPagination(this.getPaginater().initPagination(request));// 分页
//		TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj) request.getSession()
//				.getAttribute(Constant.USER_SESSION_KEY);
		obj.setDATAAUTHORITY((String)session.get("datau"));
		List resultList = this.deployExampleHisService.queryForListByObj(obj);
		theForm.setResultList(resultList);
		theForm.setAppList(appList); // 应用集合
		return LIST;
	}

	/**
	 * @Title:删除部署实例管理历史信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String delDeployExampleHis()  {
		if (theForm == null) {
			theForm = new DeployExampleHisForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbBusiDeployExampleHisObj obj = new TbBusiDeployExampleHisObj();
		int result = 0;
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obj.getID();
		int ret = deployExampleHisService.deleteByObj(obj);
		if (ret > 0) {
			result = 1;
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除部署实例管理信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return DEL;
	}

	AppService appService;

	DeployExampleHisService deployExampleHisService;
	
	private DeployExampleHisForm theForm;

	public DeployExampleHisForm getTheForm() {
		return theForm;
	}

	public void setTheForm(DeployExampleHisForm theForm) {
		this.theForm = theForm;
	}

	public void setDeployExampleHisService(
			DeployExampleHisService deployExampleHisService) {
		this.deployExampleHisService = deployExampleHisService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

}
