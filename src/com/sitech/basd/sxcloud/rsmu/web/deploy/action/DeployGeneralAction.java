package com.sitech.basd.sxcloud.rsmu.web.deploy.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployGeneralObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployGeneralService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.deploy.form.DeployGeneralForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class DeployGeneralAction extends CRUDBaseAction {

	/**
	 * @Title:得到个性化配置信息列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String listDeployGeneral() throws BaseException {
		TbBusiDeployGeneralObj obj = new TbBusiDeployGeneralObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		if (theForm.getStart_time() != null
				&& !"".equals(theForm.getStart_time())) {
			obj.setStart_time(theForm.getStart_time());
		}
		if (theForm.getEnd_time() != null && !"".equals(theForm.getEnd_time())) {
			obj.setEnd_time(theForm.getEnd_time());
		}
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List resultList = this.deployGeneralService.queryForListByObj(obj);
		theForm.setResultList(resultList);
		return LIST;
	}

	/**
	 * @Title:添加部署个性化信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String addDeployGeneral() throws BaseException {

		TbBusiHostObj busiHostobj = new TbBusiHostObj();
		List<TbBusiDeployGeneralObj> listhost = new ArrayList<TbBusiDeployGeneralObj>();
		List busiHostList = busiHostService.queryForListByObj(busiHostobj);
		for (int i = 0; i < busiHostList.size(); i++) {
			TbBusiDeployGeneralObj configObj = new TbBusiDeployGeneralObj();
			busiHostobj = (TbBusiHostObj) busiHostList.get(i);
			configObj.setHOSTID(busiHostobj.getID());
			configObj.setHOSTNAME(busiHostobj.getHOSTNAME());
			listhost.add(configObj);
		}

		TbSysAppObj appobj = new TbSysAppObj();
		List<TbBusiDeployGeneralObj> listapp = new ArrayList<TbBusiDeployGeneralObj>();
		List appList = appService.queryForListByObj(appobj);
		for (int i = 0; i < appList.size(); i++) {
			TbBusiDeployGeneralObj configObj = new TbBusiDeployGeneralObj();
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
	public String saveDeployGeneral() throws BaseException {
		TbBusiDeployGeneralObj obj = new TbBusiDeployGeneralObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		try {
			BeanUtils.copyProperties(obj, theForm);
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
			ret = deployGeneralService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增部署个性化配置信息");
		} else {
			ret = deployGeneralService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改部署个性化配置信息");
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
	 * @Title:删除部署个性化配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String delDeployGeneral() throws BaseException {
		TbBusiDeployGeneralObj obj = new TbBusiDeployGeneralObj();
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
		int ret = deployGeneralService.deleteByObj(obj);
		if (ret > 0) {
			result = 1;
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除部署个性化配置信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "del";
	}

	/**
	 * @Title:修改部署个性化配置管理
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String modDeployGeneral() throws BaseException {
		TbBusiDeployGeneralObj obj = new TbBusiDeployGeneralObj();
		obj.setID(theForm.getID());
		TbBusiDeployGeneralObj tempObj = deployGeneralService.queryByObj(obj);

		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TbBusiHostObj busiHostobj = new TbBusiHostObj();
		List<TbBusiDeployGeneralObj> listhost = new ArrayList<TbBusiDeployGeneralObj>();
		List busiHostList = busiHostService.queryForListByObj(busiHostobj);
		for (int i = 0; i < busiHostList.size(); i++) {
			TbBusiDeployGeneralObj configObj = new TbBusiDeployGeneralObj();
			busiHostobj = (TbBusiHostObj) busiHostList.get(i);
			configObj.setHOSTID(busiHostobj.getID());
			configObj.setHOSTNAME(busiHostobj.getHOSTNAME());
			listhost.add(configObj);
		}

		TbSysAppObj appobj = new TbSysAppObj();
		List<TbBusiDeployGeneralObj> listapp = new ArrayList<TbBusiDeployGeneralObj>();
		List appList = appService.queryForListByObj(appobj);
		for (int i = 0; i < appList.size(); i++) {
			TbBusiDeployGeneralObj configObj = new TbBusiDeployGeneralObj();
			appobj = (TbSysAppObj) appList.get(i);
			configObj.setAPPID(appobj.getID());
			configObj.setAPPNAME(appobj.getAPPNAME());
			listapp.add(configObj);
		}
		theForm.setHostList(listhost);
		theForm.setAppList(listapp);
		return MODIFY;
	}

	DeployGeneralService deployGeneralService;
	AppService appService;
	BusiHostService busiHostService;
	private DeployGeneralForm theForm;

	public DeployGeneralForm getTheForm() {
		return theForm;
	}

	public void setTheForm(DeployGeneralForm theForm) {
		this.theForm = theForm;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}

	public void setDeployGeneralService(
			DeployGeneralService deployGeneralService) {
		this.deployGeneralService = deployGeneralService;
	}

}
