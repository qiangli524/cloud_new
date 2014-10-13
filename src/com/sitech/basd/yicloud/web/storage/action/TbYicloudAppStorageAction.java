package com.sitech.basd.yicloud.web.storage.action;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.business.TbYicloudBusinessAppObj;
import com.sitech.basd.yicloud.domain.device.DeviceObj;
import com.sitech.basd.yicloud.domain.storage.TbYicloudAppStorageObj;
import com.sitech.basd.yicloud.service.business.TbYicloudBusinessAppService;
import com.sitech.basd.yicloud.service.device.DeviceService;
import com.sitech.basd.yicloud.service.storage.TbYicloudAppStorageService;
import com.sitech.basd.yicloud.web.storage.form.TbYicloudAppStorageForm;

public class TbYicloudAppStorageAction extends CRUDBaseAction {

	private TbYicloudAppStorageForm theForm;

	public TbYicloudAppStorageForm getTheForm() {
		return theForm;
	}

	public void setTheForm(TbYicloudAppStorageForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: 获取存储列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String listStorage() {
		if (theForm == null) {
			theForm = new TbYicloudAppStorageForm();
		}
		TbYicloudAppStorageObj obj = new TbYicloudAppStorageObj();
		TbYicloudBusinessAppObj tObj = new TbYicloudBusinessAppObj();
		DeviceObj dObj = new DeviceObj();
		if (theForm.getSELECT_APP_ID() != 0) {
			obj.setAPP_ID(theForm.getSELECT_APP_ID());
		}
		if (theForm.getSELECT_BIND_DEVICE() != 0) {
			obj.setAPP_ID(theForm.getSELECT_BIND_DEVICE());
		}
		if (theForm.getUSER_NAME() != null
				&& !"".equals(theForm.getUSER_NAME())) {
			obj.setUSER_NAME(theForm.getUSER_NAME());
		}
		if (theForm.getUSER_ID() != null && !"".equals(theForm.getUSER_ID())) {
			obj.setUSER_ID(theForm.getUSER_ID());
		}
		if (theForm.getGROUP_ID() != null && !"".equals(theForm.getGROUP_ID())) {
			obj.setGROUP_ID(theForm.getGROUP_ID());
		}
		if (theForm.getFILE_PATH() != null
				&& !"".equals(theForm.getFILE_PATH())) {
			obj.setFILE_PATH(theForm.getFILE_PATH());
		}
		if (theForm.getSTORAGE_SIZE() != null
				&& !"".equals(theForm.getSTORAGE_SIZE())) {
			obj.setSTORAGE_SIZE(theForm.getSTORAGE_SIZE());
		}
		if (theForm.getAVAILABLE() != null
				&& !"".equals(theForm.getAVAILABLE())) {
			obj.setAVAILABLE(theForm.getAVAILABLE());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List appIdList = tbYicloudBusinessAppService.queryAppIdList(tObj);
		// List bindDeviceList = deviceService.queryForBindDeviceList(dObj);
		List resultList = tbYicloudAppStorageService.queryForListByObj(obj);
		theForm.setAppIdList(appIdList);
		// theForm.setBindDeviceList(bindDeviceList);
		theForm.setResultList(resultList);
		return null;
	}

	/**
	 * 
	 * @Title: 添加存储
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String addStorage() {
		if (theForm == null) {
			theForm = new TbYicloudAppStorageForm();
		}
		TbYicloudAppStorageObj obj = new TbYicloudAppStorageObj();
		TbYicloudBusinessAppObj tObj = new TbYicloudBusinessAppObj();
		DeviceObj dObj = new DeviceObj();
		List appIdList = tbYicloudBusinessAppService.queryAppIdList(tObj);
		// List bindDeviceList = deviceService.queryForBindDeviceList(dObj);
		theForm.setAppIdList(appIdList);
		// theForm.setBindDeviceList(bindDeviceList);
		return ADD;
	}

	/**
	 * 
	 * @Title: 修改存储
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String modStorage() {
		if (theForm == null) {
			theForm = new TbYicloudAppStorageForm();
		}
		TbYicloudAppStorageObj obj = new TbYicloudAppStorageObj();
		TbYicloudBusinessAppObj tObj = new TbYicloudBusinessAppObj();
		DeviceObj dObj = new DeviceObj();
		obj.setID(theForm.getID());
		List appIdList = tbYicloudBusinessAppService.queryAppIdList(tObj);
		// List bindDeviceList = deviceService.queryForBindDeviceList(dObj);
		theForm.setAppIdList(appIdList);
		// theForm.setBindDeviceList(bindDeviceList);
		TbYicloudAppStorageObj tbObj = tbYicloudAppStorageService
				.queryByObj(obj);
		theForm.setSELECT_APP_ID(tbObj.getAPP_ID());
		theForm.setSELECT_BIND_DEVICE(tbObj.getBIND_DEVICE());
		theForm.setUSER_NAME(tbObj.getUSER_NAME());
		theForm.setGROUP_ID(tbObj.getGROUP_ID());
		theForm.setUSER_ID(tbObj.getUSER_ID());
		theForm.setFILE_PATH(tbObj.getFILE_PATH());
		theForm.setSTORAGE_SIZE(tbObj.getSTORAGE_SIZE());
		theForm.setAVAILABLE(tbObj.getAVAILABLE());
		return MODIFY;
	}

	/**
	 * 
	 * @Title:保存信息
	 * @Copyright: Copyright (c) 2012-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String saveStorage() {
		if (theForm == null) {
			theForm = new TbYicloudAppStorageForm();
		}
		TbYicloudAppStorageObj obj = new TbYicloudAppStorageObj();
		obj.setID(theForm.getID());
		obj.setAPP_ID(theForm.getSELECT_APP_ID());
		obj.setBIND_DEVICE(theForm.getSELECT_BIND_DEVICE());
		obj.setUSER_NAME(theForm.getUSER_NAME());
		obj.setUSER_ID(theForm.getUSER_ID());
		obj.setGROUP_ID(theForm.getGROUP_ID());
		obj.setFILE_PATH(theForm.getFILE_PATH());
		obj.setAVAILABLE(theForm.getAVAILABLE());
		obj.setSTORAGE_SIZE(theForm.getSTORAGE_SIZE());
		TbSysOperationLogObj operObj = this
				.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");
		int ret = 0;
		int result = 0;
		if (theForm.getFlag() == 0) {
			ret = tbYicloudAppStorageService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增功能信息");
		} else {
			ret = tbYicloudAppStorageService.updateByObj(obj);
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
	 * @Title:删除存储
	 * @Copyright: Copyright (c) 2012-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String delStorage() {
		if (theForm == null) {
			theForm = new TbYicloudAppStorageForm();
		}
		TbYicloudAppStorageObj obj = new TbYicloudAppStorageObj();
		obj.setID(theForm.getID());
		int ret = tbYicloudAppStorageService.deleteByObj(obj);
		return REDIRECT;
	}

	TbYicloudBusinessAppService tbYicloudBusinessAppService;
	DeviceService deviceService;
	TbYicloudAppStorageService tbYicloudAppStorageService;

	public void setTbYicloudBusinessAppService(
			TbYicloudBusinessAppService tbYicloudBusinessAppService) {
		this.tbYicloudBusinessAppService = tbYicloudBusinessAppService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public void setTbYicloudAppStorageService(
			TbYicloudAppStorageService tbYicloudAppStorageService) {
		this.tbYicloudAppStorageService = tbYicloudAppStorageService;
	}

}
