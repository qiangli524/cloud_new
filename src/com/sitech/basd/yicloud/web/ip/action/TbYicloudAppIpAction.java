package com.sitech.basd.yicloud.web.ip.action;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.business.TbYicloudBusinessAppObj;
import com.sitech.basd.yicloud.domain.device.DeviceObj;
import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;
import com.sitech.basd.yicloud.domain.ip.TbYicloudAppIpObj;
import com.sitech.basd.yicloud.service.business.TbYicloudBusinessAppService;
import com.sitech.basd.yicloud.service.device.DeviceService;
import com.sitech.basd.yicloud.service.dictionary.YiDataDictionaryService;
import com.sitech.basd.yicloud.service.ip.TbYicloudAppIpService;
import com.sitech.basd.yicloud.web.ip.form.TbYicloudAppIpForm;

public class TbYicloudAppIpAction extends CRUDBaseAction {

	private TbYicloudAppIpForm theForm;

	public TbYicloudAppIpForm getTheForm() {
		return theForm;
	}

	public void setTheForm(TbYicloudAppIpForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: 获取IP列表
	 * @Copyright: Copyright (c) 2012-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String listIp() {
		if (theForm == null) {
			theForm = new TbYicloudAppIpForm();
		}
		TbYicloudAppIpObj obj = new TbYicloudAppIpObj();
		DeviceObj dObj = new DeviceObj();
		TbYicloudBusinessAppObj tObj = new TbYicloudBusinessAppObj();
		DictionaryObj yObj = new DictionaryObj();

		// 获取业务应用ID列表
		List appIdList = tbYicloudBusinessAppService.queryAppIdList(tObj);
		// 获取绑定主机设备ID列表
		List bindDeviceList = deviceService.queryForListByObj(dObj);
		// 获取运营商列表
		yObj.setGROUP_NAME("AppIp_telecomOperator");
		List operatorList = yiDataDictionaryService.queryBusinessType(yObj);
		// 获取是否使用列表
		yObj.setGROUP_NAME("AppIp_isUse");
		List isUseList = yiDataDictionaryService.queryBusinessType(yObj);
		// 获取端口类型列表
		yObj.setGROUP_NAME("AppIp_portType");
		List portTypeList = yiDataDictionaryService.queryBusinessType(yObj);
		// 获取公网IP类型
		yObj.setGROUP_NAME("AppIp_ipType");
		List ipTypeList = yiDataDictionaryService.queryBusinessType(yObj);
		// 获取公网是否开放列表
		yObj.setGROUP_NAME("AppIp_isIpOpen");
		List isOpenList = yiDataDictionaryService.queryBusinessType(yObj);
		// 获取是否负载均衡列表
		yObj.setGROUP_NAME("AppIp_isLoadBalance");
		List isBalanceList = yiDataDictionaryService.queryBusinessType(yObj);
		theForm.setAppIdList(appIdList);
		theForm.setBindDeviceList(bindDeviceList);
		theForm.setOperatorList(operatorList);
		theForm.setIpTypeList(ipTypeList);
		theForm.setIsOpenList(isOpenList);
		theForm.setIsUseList(isUseList);
		theForm.setPortTypeList(portTypeList);
		theForm.setIsBalanceList(isBalanceList);
		if (theForm.getSELECT_APP_ID() != 0) {
			obj.setAPP_ID(theForm.getSELECT_APP_ID());
		}
		if (theForm.getSELECT_BIND_DEVICE() != 0) {
			obj.setBIND_DEVICE(theForm.getSELECT_BIND_DEVICE());
		}
		if (theForm.getSELECT_ISUSE() != null
				&& !"".equals(theForm.getSELECT_ISUSE())) {
			obj.setISUSE(theForm.getSELECT_ISUSE());
		}
		if (theForm.getMAX_BANDWIDTH() != null
				& !"".equals(theForm.getMAX_BANDWIDTH())) {
			obj.setMAX_BANDWIDTH(theForm.getMAX_BANDWIDTH());
		}
		if (theForm.getSELECT_TELECOM_OPERATOR() != null
				&& !"".equals(theForm.getSELECT_TELECOM_OPERATOR())) {
			obj.setTELECOM_OPERATOR(theForm.getSELECT_TELECOM_OPERATOR());
		}
		if (theForm.getMIDDLEWARE() != null
				&& !"".equals(theForm.getMIDDLEWARE())) {
			obj.setMIDDLEWARE(theForm.getMIDDLEWARE());
		}
		if (theForm.getSELECT_PORT_TYPE() != null
				&& !"".equals(theForm.getSELECT_PORT_TYPE())) {
			obj.setPORT_TYPE(theForm.getSELECT_PORT_TYPE());
		}
		if (theForm.getSELECT_IP_TYPE() != null
				&& !"".equals(theForm.getSELECT_IP_TYPE())) {
			obj.setIP_TYPE(theForm.getSELECT_IP_TYPE());
		}
		if (theForm.getSELECT_ISIPOPEN() != null
				&& !"".equals(theForm.getSELECT_ISIPOPEN())) {
			obj.setISIPOPEN(theForm.getSELECT_ISIPOPEN());
		}
		if (theForm.getPORT_MAPPING() != null
				&& !"".equals(theForm.getPORT_MAPPING())) {
			obj.setPORT_MAPPING(theForm.getPORT_MAPPING());
		}
		if (theForm.getLOAD_VIRTUAL_IP() != null
				&& !"".equals(theForm.getLOAD_VIRTUAL_IP())) {
			obj.setLOAD_VIRTUAL_IP(theForm.getLOAD_VIRTUAL_IP());
		}
		if (theForm.getSELECT_ISLOADBALANCE() != null
				& !"".equals(theForm.getSELECT_ISLOADBALANCE())) {
			obj.setISLOADBALANCE(theForm.getSELECT_ISLOADBALANCE());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List ipList = tbYicloudAppIpService.queryForListByObj(obj);
		theForm.setResultList(ipList);
		return null;
	}

	/**
	 * 
	 * @Title: 添加IP信息
	 * @Copyright: Copyright (c) 2012-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String addIp() {
		if (theForm == null) {
			theForm = new TbYicloudAppIpForm();
		}
		TbYicloudAppIpObj obj = new TbYicloudAppIpObj();
		DeviceObj dObj = new DeviceObj();
		TbYicloudBusinessAppObj tObj = new TbYicloudBusinessAppObj();
		DictionaryObj yObj = new DictionaryObj();

		// 获取业务应用ID列表
		List appIdList = tbYicloudBusinessAppService.queryAppIdList(tObj);
		// 获取绑定主机设备ID列表
		// List bindDeviceList = deviceService.queryForBindDeviceList(dObj);
		// 获取运营商列表
		yObj.setGROUP_NAME("AppIp_telecomOperator");
		List operatorList = yiDataDictionaryService.queryBusinessType(yObj);
		// 获取是否使用列表
		yObj.setGROUP_NAME("AppIp_isUse");
		List isUseList = yiDataDictionaryService.queryBusinessType(yObj);
		// 获取端口类型列表
		yObj.setGROUP_NAME("AppIp_portType");
		List portTypeList = yiDataDictionaryService.queryBusinessType(yObj);
		// 获取公网IP类型
		yObj.setGROUP_NAME("AppIp_ipType");
		List ipTypeList = yiDataDictionaryService.queryBusinessType(yObj);
		// 获取公网是否开放列表
		yObj.setGROUP_NAME("AppIp_isIpOpen");
		List isOpenList = yiDataDictionaryService.queryBusinessType(yObj);
		// 获取是否负载均衡列表
		yObj.setGROUP_NAME("AppIp_isLoadBalance");
		List isBalanceList = yiDataDictionaryService.queryBusinessType(yObj);
		theForm.setAppIdList(appIdList);
		// theForm.setBindDeviceList(bindDeviceList);
		theForm.setOperatorList(operatorList);
		theForm.setIpTypeList(ipTypeList);
		theForm.setIsOpenList(isOpenList);
		theForm.setIsUseList(isUseList);
		theForm.setPortTypeList(portTypeList);
		theForm.setIsBalanceList(isBalanceList);
		return ADD;
	}

	/**
	 * 
	 * @Title: 保存IP信息
	 * @Copyright: Copyright (c) 2012-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String saveIp() {
		if (theForm == null) {
			theForm = new TbYicloudAppIpForm();
		}
		TbYicloudAppIpObj obj = new TbYicloudAppIpObj();
		obj.setID(theForm.getID());
		obj.setAPP_ID(theForm.getSELECT_APP_ID());
		obj.setBIND_DEVICE(theForm.getSELECT_BIND_DEVICE());
		obj.setIP_TYPE(theForm.getSELECT_IP_TYPE());
		obj.setISIPOPEN(theForm.getSELECT_ISIPOPEN());
		obj.setISLOADBALANCE(theForm.getSELECT_ISLOADBALANCE());
		obj.setLOAD_VIRTUAL_IP(theForm.getLOAD_VIRTUAL_IP());
		obj.setISUSE(theForm.getSELECT_ISUSE());
		obj.setMAX_BANDWIDTH(theForm.getMAX_BANDWIDTH());
		obj.setMIDDLEWARE(theForm.getMIDDLEWARE());
		obj.setPORT_MAPPING(theForm.getPORT_MAPPING());
		obj.setPORT_TYPE(theForm.getSELECT_PORT_TYPE());
		obj.setTELECOM_OPERATOR(theForm.getSELECT_TELECOM_OPERATOR());
		TbSysOperationLogObj operObj = this
				.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");
		int ret = 0;
		int result = 0;
		if (theForm.getFlag() == 0) {
			ret = tbYicloudAppIpService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增功能信息");
		} else {
			ret = tbYicloudAppIpService.updateByObj(obj);
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
	 * @Title: 修改IP信息
	 * @Copyright: Copyright (c) 2012-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String modIp() {
		if (theForm == null) {
			theForm = new TbYicloudAppIpForm();
		}
		TbYicloudAppIpObj obj = new TbYicloudAppIpObj();
		obj.setID(theForm.getID());
		TbYicloudAppIpObj tObj = tbYicloudAppIpService.queryByObj(obj);
		DeviceObj dObj = new DeviceObj();
		TbYicloudBusinessAppObj tbObj = new TbYicloudBusinessAppObj();
		DictionaryObj yObj = new DictionaryObj();

		// 获取业务应用ID列表
		List appIdList = tbYicloudBusinessAppService.queryAppIdList(tbObj);
		// 获取绑定主机设备ID列表
		// List bindDeviceList = deviceService.queryForBindDeviceList(dObj);
		// 获取运营商列表
		yObj.setGROUP_NAME("AppIp_telecomOperator");
		List operatorList = yiDataDictionaryService.queryBusinessType(yObj);
		// 获取是否使用列表
		yObj.setGROUP_NAME("AppIp_isUse");
		List isUseList = yiDataDictionaryService.queryBusinessType(yObj);
		// 获取端口类型列表
		yObj.setGROUP_NAME("AppIp_portType");
		List portTypeList = yiDataDictionaryService.queryBusinessType(yObj);
		// 获取公网IP类型
		yObj.setGROUP_NAME("AppIp_ipType");
		List ipTypeList = yiDataDictionaryService.queryBusinessType(yObj);
		// 获取公网是否开放列表
		yObj.setGROUP_NAME("AppIp_isIpOpen");
		List isOpenList = yiDataDictionaryService.queryBusinessType(yObj);
		// 获取是否负载均衡列表
		yObj.setGROUP_NAME("AppIp_isLoadBalance");
		List isBalanceList = yiDataDictionaryService.queryBusinessType(yObj);
		theForm.setAppIdList(appIdList);
		// theForm.setBindDeviceList(bindDeviceList);
		theForm.setOperatorList(operatorList);
		theForm.setIpTypeList(ipTypeList);
		theForm.setIsOpenList(isOpenList);
		theForm.setIsUseList(isUseList);
		theForm.setPortTypeList(portTypeList);
		theForm.setIsBalanceList(isBalanceList);
		theForm.setSELECT_APP_ID(tObj.getAPP_ID());
		theForm.setSELECT_BIND_DEVICE(tObj.getBIND_DEVICE());
		theForm.setSELECT_IP_TYPE(tObj.getIP_TYPE());
		theForm.setSELECT_ISIPOPEN(tObj.getISIPOPEN());
		theForm.setSELECT_ISLOADBALANCE(tObj.getISLOADBALANCE());
		theForm.setSELECT_ISUSE(tObj.getISUSE());
		theForm.setLOAD_VIRTUAL_IP(tObj.getLOAD_VIRTUAL_IP());
		theForm.setMAX_BANDWIDTH(tObj.getMAX_BANDWIDTH());
		theForm.setMIDDLEWARE(tObj.getMIDDLEWARE());
		theForm.setPORT_MAPPING(tObj.getPORT_MAPPING());
		theForm.setSELECT_PORT_TYPE(tObj.getPORT_TYPE());
		theForm.setSELECT_TELECOM_OPERATOR(tObj.getTELECOM_OPERATOR());
		return MODIFY;
	}

	/**
	 * 
	 * @Title: 删除IP信息
	 * @Copyright: Copyright (c) 2012-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String delIp() {
		if (theForm == null) {
			theForm = new TbYicloudAppIpForm();
		}
		TbYicloudAppIpObj obj = new TbYicloudAppIpObj();
		obj.setID(theForm.getID());
		int ret = tbYicloudAppIpService.deleteByObj(obj);
		return REDIRECT;
	}

	TbYicloudAppIpService tbYicloudAppIpService;
	TbYicloudBusinessAppService tbYicloudBusinessAppService;
	DeviceService deviceService;
	YiDataDictionaryService yiDataDictionaryService;

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public void setTbYicloudBusinessAppService(
			TbYicloudBusinessAppService tbYicloudBusinessAppService) {
		this.tbYicloudBusinessAppService = tbYicloudBusinessAppService;
	}

	public void setTbYicloudAppIpService(
			TbYicloudAppIpService tbYicloudAppIpService) {
		this.tbYicloudAppIpService = tbYicloudAppIpService;
	}

	public void setYiDataDictionaryService(
			YiDataDictionaryService yiDataDictionaryService) {
		this.yiDataDictionaryService = yiDataDictionaryService;
	}

}
