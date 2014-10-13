package com.sitech.basd.sxcloud.cloud.web.appmessage.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.appmessage.AppMessageObj;
import com.sitech.basd.sxcloud.cloud.domain.appmessage.BizsysObj;
import com.sitech.basd.sxcloud.cloud.service.appmessage.AppMessageService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.cloud.web.appmessage.form.AppMessageForm;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

@SuppressWarnings("all")
public class AppMessageAction extends BaseAction {
	private AppMessageForm theForm;

	public AppMessageForm getTheForm() {
		return theForm;
	}

	public void setTheForm(AppMessageForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listAppMessage() throws BaseException {
		if (theForm == null) {
			theForm = new AppMessageForm();
		}
		AppMessageObj obj = new AppMessageObj();
		if (theForm.getAPP_ID() != null && !"".equals(theForm.getAPP_ID())) {
			obj.setAPP_ID(theForm.getAPP_ID().trim());
		}
		if (theForm.getAPP_NAME() != null && !"".equals(theForm.getAPP_NAME())) {
			obj.setAPP_NAME(theForm.getAPP_NAME().trim());
		}

		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List AppMessageList = appMessageService.queryForListByObj(obj);
		theForm.setResultList(AppMessageList);
		return "listAppMess";

	}

	/**
	 * @Title:增加应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	public String addAppMessage() throws BaseException {
		if (theForm == null) {
			theForm = new AppMessageForm();
		}
		// VMHostObj vmHostObj = new VMHostObj();
		BizsysObj bizsysObj = new BizsysObj();
		// List vmList = vmHostService.queryForListByVMHostObj(vmHostObj);
		List bzList = appMessageService.queryForListByBizsysObj(bizsysObj);
		theForm.setSysList(bzList);
		// theForm.setVirtualList(vmList);

		return "addAppMess";
	}

	/**
	 * @Title:保存应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public String saveAppMessage() throws BaseException {
		if (theForm == null) {
			theForm = new AppMessageForm();
		}
		AppMessageObj obj = new AppMessageObj();
		theForm.setFlag(theForm.getFlag());
		try {
			BeanUtils.copyProperties(obj, theForm);
			obj.setVH_ID(theForm.getVH_ID());
			obj.setSYS_ID(theForm.getSYS_ID());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		TbSysOperationLogObj operObj = this
				.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");

		int ret = 0;
		int result = 0;
		if (theForm.getFlag() == 0) { // flag是来自JS中的隐藏变量，判断是新增操作或修改操作
			ret = appMessageService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增应用信息");
		} else {
			obj.setAPP_ID(theForm.getAPP_ID());
			ret = appMessageService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改应用信息");
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "saveAppMess";
	}

	/**
	 * @Title:修改应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	public String modAppMessage() throws BaseException {
		if (theForm == null) {
			theForm = new AppMessageForm();
		}
		AppMessageObj obj = new AppMessageObj();
		obj.setAPP_ID(theForm.getAPP_ID());
		AppMessageObj tempObj = appMessageService.queryByObj(obj);
		// VMHostObj vmHostObj = new VMHostObj();
		BizsysObj bizsysObj = new BizsysObj();

		// List vmList = vmHostService.queryForListByVMHostObj(vmHostObj);
		List bzList = appMessageService.queryForListByBizsysObj(bizsysObj);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
			Struts2Utils.getRequest().setAttribute("vhId",tempObj.getVH_ID());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		theForm.setSysList(bzList);
		// theForm.setVirtualList(vmList);

		return "modAppMess";
	}

	/**
	 * @Title:删除应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public String delAppMessage() throws BaseException {
		if (theForm == null) {
			theForm = new AppMessageForm();
		}
		AppMessageObj obj = new AppMessageObj();
		int result = 0;
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int ret = appMessageService.deleteByObj(obj);
		if (ret > 0) {
			result = 1;
		}
		TbSysOperationLogObj operObj = this
				.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除应用信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "delAppMess";
	}

	private VMHostService vmHostService;
	private AppMessageService appMessageService;

	public void setAppMessageService(AppMessageService appMessageService) {
		this.appMessageService = appMessageService;
	}

	public void setVmHostService(VMHostService vmHostService) {
		this.vmHostService = vmHostService;
	}

}
