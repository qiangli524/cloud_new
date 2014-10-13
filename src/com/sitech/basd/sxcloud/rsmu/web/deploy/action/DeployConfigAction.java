package com.sitech.basd.sxcloud.rsmu.web.deploy.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.rsmu.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployConfigService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostConfigService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.deploy.form.DeployConfigForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class DeployConfigAction extends CRUDBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Title:得到部署基本配置信息列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String listDeployConfig() throws BaseException {
		if (theForm == null) {
			theForm = new DeployConfigForm();
		}
		TbBusiDeployConfigObj obj = new TbBusiDeployConfigObj();
		if (theForm.getStart_time() != null
				&& !"".equals(theForm.getStart_time())) {
			obj.setStart_time(theForm.getStart_time());
		}
		if (theForm.getEnd_time() != null && !"".equals(theForm.getEnd_time())) {
			obj.setEnd_time(theForm.getEnd_time());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List resultList = this.deployConfigService.queryForListByObj(obj);
		theForm.setResultList(resultList);
		return LIST;
	}

	/**
	 * @Title:添加部署基本配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String addDeployConfig() throws BaseException {

		TbBusiHostObj busiHostobj = new TbBusiHostObj();
		List<TbBusiDeployConfigObj> listhost = new ArrayList<TbBusiDeployConfigObj>();
		List busiHostList = busiHostService.queryForListByObj(busiHostobj);
		for (int i = 0; i < busiHostList.size(); i++) {
			TbBusiDeployConfigObj configObj = new TbBusiDeployConfigObj();
			busiHostobj = (TbBusiHostObj) busiHostList.get(i);
			configObj.setHOSTID(busiHostobj.getID());
			configObj.setHOSTNAME(busiHostobj.getHOSTNAME());
			listhost.add(configObj);
		}
		// TbBusiHostConfigObj obj = new TbBusiHostConfigObj();
		// List busiHostConfigList =
		// busiHostConfigService.queryForListByObj(obj);

		TbSysAppObj appobj = new TbSysAppObj();
		List<TbBusiDeployConfigObj> listapp = new ArrayList<TbBusiDeployConfigObj>();
		List appList = appService.queryForListByObj(appobj);
		for (int i = 0; i < appList.size(); i++) {
			TbBusiDeployConfigObj configObj = new TbBusiDeployConfigObj();
			appobj = (TbSysAppObj) appList.get(i);
			configObj.setAPPID(appobj.getID());
			configObj.setAPPNAME(appobj.getAPPNAME());
			listapp.add(configObj);
		}

		theForm.setHostList(listhost);
		theForm.setAppList(listapp);

		return ADD;
	}

	/**
	 * @Title:保存部署基本配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String saveDeployConfig() throws BaseException {
		TbBusiDeployConfigObj obj = new TbBusiDeployConfigObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		try {
			BeanUtils.copyProperties(obj, theForm);
//			TbSysUserinfoObj tempObj = (TbSysUserinfoObj) request.getSession()
//					.getAttribute(Constant.USER_SESSION_KEY);
			obj.setUPDATEUSER(session.get("name").toString());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");

		int ret = 0;
		int result = 0;
		if (theForm.getID() == 0) {
			ret = deployConfigService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增部署基本信息");
		} else {
			ret = deployConfigService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改部署基本信息");
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "save";
	}

	/**
	 * @Title:删除部署基本配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String delDeployConfig() throws BaseException {
		TbBusiDeployConfigObj obj = new TbBusiDeployConfigObj();
		HttpServletRequest request = Struts2Utils.getRequest();
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
		int ret = deployConfigService.deleteByObj(obj);
		if (ret > 0) {
			result = 1;
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除部署基本配置信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "del";
	}

	DeployConfigService deployConfigService;
	AppService appService;
	BusiHostService busiHostService;
	BusiHostConfigService busiHostConfigService;
	private DeployConfigForm theForm;

	public DeployConfigForm getTheForm() {
		return theForm;
	}

	public void setTheForm(DeployConfigForm theForm) {
		this.theForm = theForm;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public void setDeployConfigService(DeployConfigService deployConfigService) {
		this.deployConfigService = deployConfigService;
	}

	public void setBusiHostConfigService(
			BusiHostConfigService busiHostConfigService) {
		this.busiHostConfigService = busiHostConfigService;
	}

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}

}
