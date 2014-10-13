package com.sitech.basd.yicloud.web.business.action;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.business.TbYicloudBusinessAppObj;
import com.sitech.basd.yicloud.domain.device.DeviceObj;
import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;
import com.sitech.basd.yicloud.service.business.TbYicloudBusinessAppService;
import com.sitech.basd.yicloud.service.device.DeviceService;
import com.sitech.basd.yicloud.service.dictionary.YiDataDictionaryService;
import com.sitech.basd.yicloud.web.business.form.TbYicloudBusinessAppForm;

public class TbYicloudBusinessAppAction extends CRUDBaseAction {

	private TbYicloudBusinessAppForm theForm;

	public TbYicloudBusinessAppForm getTheForm() {
		return theForm;
	}

	public void setTheForm(TbYicloudBusinessAppForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: 获取应用服务列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0d
	 */
	public String listAppService() {
		if (theForm == null) {
			theForm = new TbYicloudBusinessAppForm();
		}
		TbYicloudBusinessAppObj obj = new TbYicloudBusinessAppObj();
		if (theForm.getNAME() != null && !"".equals(theForm.getNAME())) {
			obj.setNAME(theForm.getNAME());
		}
		if (theForm.getSELECT_TYPE() != null
				&& !"".equals(theForm.getSELECT_TYPE())) {
			obj.setTYPE(theForm.getSELECT_TYPE());
		}
		if (theForm.getSELECT_UPPER_ID() != 0) {
			obj.setUPPER_ID(theForm.getSELECT_UPPER_ID());
		}
		if (theForm.getSELECT_BIND_DEVICE() != 0) {
			obj.setBIND_DEVICE(theForm.getSELECT_BIND_DEVICE());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		DictionaryObj yObj = new DictionaryObj();
		TbYicloudBusinessAppObj tObj = new TbYicloudBusinessAppObj();
		DeviceObj dObj = new DeviceObj();
		yObj.setGROUP_NAME("App_type");
		// 获取业务类型列表
		List bus = yiDataDictionaryService.queryBusinessType(yObj);
		// 此处用于获取所属上级业务列表
		List upperList = tbYicloudBusinessAppService.queryAppIdList(tObj);
		// 此处用于获取绑定设备主机ID列表 List
		// List bindDevice = deviceService.queryForBindDeviceList(dObj);
		List resultList = tbYicloudBusinessAppService.queryForListByObj(obj);
		theForm.setBusinessTypeList(bus);
		theForm.setUpperList(upperList);
		// theForm.setBineDeviceList(bindDevice);
		theForm.setResultList(resultList);
		return null;
	}

	/**
	 * 
	 * @Title: 添加信息
	 * @Copyright: Copyright (c) 2012-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String addAppService() {
		if (theForm == null) {
			theForm = new TbYicloudBusinessAppForm();
		}
		TbYicloudBusinessAppObj obj = new TbYicloudBusinessAppObj();
		DictionaryObj yObj = new DictionaryObj();
		TbYicloudBusinessAppObj tObj = new TbYicloudBusinessAppObj();
		DeviceObj dObj = new DeviceObj();
		yObj.setGROUP_NAME("App_type");
		// 获取业务类型列表
		List bus = yiDataDictionaryService.queryBusinessType(yObj);
		// 此处用于获取所属上级业务列表
		List upperList = tbYicloudBusinessAppService.queryAppIdList(tObj);
		// 此处用于获取绑定设备主机ID列表 List
		// List bindDevice = deviceService.queryForBindDeviceList(dObj);
		theForm.setBusinessTypeList(bus);
		theForm.setUpperList(upperList);
		// theForm.setBineDeviceList(bindDevice);
		return ADD;
	}

	/**
	 * 
	 * @Title:保存信息
	 * @Copyright: Copyright (c) 2012-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String saveAppService() {
		if (theForm == null) {
			theForm = new TbYicloudBusinessAppForm();
		}
		TbYicloudBusinessAppObj obj = new TbYicloudBusinessAppObj();
		obj.setID(theForm.getID());
		obj.setNAME(theForm.getNAME());
		obj.setTYPE(theForm.getSELECT_TYPE());
		obj.setUPPER_ID(theForm.getSELECT_UPPER_ID());
		obj.setBIND_DEVICE(theForm.getSELECT_BIND_DEVICE());
		TbSysOperationLogObj operObj = this
				.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");
		int ret = 0;
		int result = 0;
		if (theForm.getFlag() == 0) {
			ret = tbYicloudBusinessAppService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增功能信息");
		} else {
			ret = tbYicloudBusinessAppService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改功能信息");
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return REDIRECT;
	}

	/**
	 * 
	 * @Title:修改信息
	 * @Copyright: Copyright (c) 2012-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String modAppService() {
		if (theForm == null) {
			theForm = new TbYicloudBusinessAppForm();
		}
		TbYicloudBusinessAppObj obj = new TbYicloudBusinessAppObj();
		obj.setID(theForm.getID());
		TbYicloudBusinessAppObj tObj = tbYicloudBusinessAppService
				.queryByObj(obj);
		theForm.setID(tObj.getID());
		theForm.setNAME(tObj.getNAME());
		theForm.setSELECT_BIND_DEVICE(tObj.getBIND_DEVICE());
		theForm.setSELECT_TYPE(tObj.getTYPE());
		theForm.setSELECT_UPPER_ID(tObj.getUPPER_ID());
		DictionaryObj yObj = new DictionaryObj();
		yObj.setGROUP_NAME("App_type");
		// 获取业务类型列表
		TbYicloudBusinessAppObj tbObj = new TbYicloudBusinessAppObj();
		DeviceObj dObj = new DeviceObj();
		// 获取业务类型列表
		List bus = yiDataDictionaryService.queryBusinessType(yObj);
		// 此处用于获取所属上级业务列表
		List upperList = tbYicloudBusinessAppService.queryAppIdList(tbObj);
		// 此处用于获取绑定设备主机ID列表 List
		// List bindDevice = deviceService.queryForBindDeviceList(dObj);
		theForm.setBusinessTypeList(bus);
		theForm.setUpperList(upperList);
		// theForm.setBineDeviceList(bindDevice);
		return MODIFY;
	}

	/**
	 * 
	 * @Title:删除信息
	 * @Copyright: Copyright (c) 2012-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String delAppService() {
		if (theForm == null) {
			theForm = new TbYicloudBusinessAppForm();
		}
		TbYicloudBusinessAppObj obj = new TbYicloudBusinessAppObj();
		obj.setID(theForm.getID());
		int ret = tbYicloudBusinessAppService.deleteByObj(obj);
		return REDIRECT;
	}

	TbYicloudBusinessAppService tbYicloudBusinessAppService;
	YiDataDictionaryService yiDataDictionaryService;
	DeviceService deviceService;

	public void setTbYicloudBusinessAppService(
			TbYicloudBusinessAppService tbYicloudBusinessAppService) {
		this.tbYicloudBusinessAppService = tbYicloudBusinessAppService;
	}

	public void setYiDataDictionaryService(
			YiDataDictionaryService yiDataDictionaryService) {
		this.yiDataDictionaryService = yiDataDictionaryService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

}
