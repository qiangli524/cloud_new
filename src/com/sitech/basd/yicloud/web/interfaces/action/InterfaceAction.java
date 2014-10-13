package com.sitech.basd.yicloud.web.interfaces.action;


import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.yicloud.domain.device.DeviceObj;
import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;
import com.sitech.basd.yicloud.domain.interfaces.InterfaceObj;
import com.sitech.basd.yicloud.service.interfaces.InterfaceService;
import com.sitech.basd.yicloud.web.interfaces.form.InterfaceForm;

import java.lang.reflect.InvocationTargetException;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

import java.util.List;

public class InterfaceAction extends CRUDBaseAction {
	
	private InterfaceForm theForm;
	public InterfaceForm getTheForm() {
		return theForm;
	}

	public void setTheForm(InterfaceForm theForm) {
		this.theForm = theForm;
	}

	private InterfaceService interfaceService;

	/**
	 * @Title:查询外围接口数据结果集
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public String listInterface() throws BaseException {
		if (theForm == null) {
			theForm = new InterfaceForm();
		}
		
		InterfaceObj obj = new InterfaceObj();
		if (theForm.getDEVICE_ID() != 0 && !"".equals(theForm.getDEVICE_ID())) {
			obj.setDEVICE_ID(theForm.getDEVICE_ID());
		}
		if (theForm.getCODE() != null && !"".equals(theForm.getCODE())) {
			obj.setCODE(theForm.getCODE());
		}
		if (!"0".equals(theForm.getSTATUS()) && !"".equals(theForm.getSTATUS())) {
			obj.setSTATUS(theForm.getSTATUS());
		}
		if (!"0".equals(theForm.getTYPE()) && !"".equals(theForm.getTYPE())) {
			obj.setTYPE(theForm.getTYPE());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		DeviceObj deviceObj = new DeviceObj();
		DictionaryObj dictionaryObj = new DictionaryObj();
		List deviceList = interfaceService.queryForListByDeviceObj(deviceObj);
		List statusList = interfaceService
				.queryForListByStatusObj(dictionaryObj);
		List typeList = interfaceService.queryForListByTypeObj(dictionaryObj);
		theForm.setDeviceList(deviceList);
		theForm.setStatusList(statusList);
		theForm.setTypeList(typeList);
		List interList = interfaceService.queryForListByObj(obj);
		theForm.setResultList(interList);
		return SUCCESS;
	}

	/**
	 * @Title:添加外围接口信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public String addInterface() throws BaseException {
		if (theForm == null) {
			theForm = new InterfaceForm();
		}
		InterfaceObj interfaceObj = new InterfaceObj();
		DeviceObj deviceObj = new DeviceObj();
		DictionaryObj dictionaryObj = new DictionaryObj();
		List deviceList = interfaceService.queryForListByDeviceObj(deviceObj);
		List statusList = interfaceService
				.queryForListByStatusObj(dictionaryObj);
		List typeList = interfaceService.queryForListByTypeObj(dictionaryObj);
		theForm.setDeviceList(deviceList);
		theForm.setStatusList(statusList);
		theForm.setTypeList(typeList);
		return ADD;
	}

	/**
	 * @Title:保存外围接口信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public String saveInterface() throws BaseException {
		if (theForm == null) {
			theForm = new InterfaceForm();
		}
		InterfaceObj interfaceObj = new InterfaceObj();
		try {
			BeanUtils.copyProperties(interfaceObj, theForm);
			TbSysOperationLogObj operObj = this
			.getTbSysOperationLogObj(Struts2Utils.getRequest());
			operObj.setREMARK("");
			int ret = 0;
			int result = 0;
			if (theForm.getFlag() == 0) {
				ret = interfaceService.insertByObj(interfaceObj);
				operObj.setOPERTYPE(1);
				operObj.setMESSAGE("新增功能信息");
			} else {
				ret = interfaceService.updateByObj(interfaceObj);
				operObj.setOPERTYPE(3);
				operObj.setMESSAGE("修改功能信息");
			}
			if (ret > 0) {
				result = 1;
			}
			operObj.setRESULT(result);
			@SuppressWarnings("unused")
			int retOper = operationService.insertByObj(operObj);// 写操作日志
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return REDIRECT;
	}

	public String modInterface() {
		if (theForm == null) {
			theForm = new InterfaceForm();
		}
		InterfaceObj interfaceObj = new InterfaceObj();
		interfaceObj.setID(theForm.getID());
		DeviceObj deviceObj = new DeviceObj();
		DictionaryObj dictionaryObj = new DictionaryObj();
		List deviceList = interfaceService.queryForListByDeviceObj(deviceObj);
		List statusList = interfaceService
				.queryForListByStatusObj(dictionaryObj);
		List typeList = interfaceService.queryForListByTypeObj(dictionaryObj);
		theForm.setDeviceList(deviceList);
		theForm.setStatusList(statusList);
		theForm.setTypeList(typeList);
		InterfaceObj obj = interfaceService.queryByObj(interfaceObj);
		try {
			BeanUtils.copyProperties(theForm, obj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return MODIFY;
	}

	/**
	 * 
	 * @Title:删除外围接口信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public String delInterface() {
		if (theForm == null) {
			theForm = new InterfaceForm();
		}
		InterfaceObj obj = new InterfaceObj();
		obj.setID(theForm.getID());
		int ret = interfaceService.deleteByObj(obj);
		return REDIRECT;
	}

	public void setInterfaceService(InterfaceService interfaceService) {
		this.interfaceService = interfaceService;
	}

}
