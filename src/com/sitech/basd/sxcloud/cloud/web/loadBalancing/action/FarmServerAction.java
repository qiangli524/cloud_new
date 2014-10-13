package com.sitech.basd.sxcloud.cloud.web.loadBalancing.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.FarmObj;
import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.FarmServerObj;
import com.sitech.basd.sxcloud.cloud.service.loadBalancing.FarmServerService;
import com.sitech.basd.sxcloud.cloud.service.loadBalancing.FarmsService;
import com.sitech.basd.sxcloud.cloud.web.loadBalancing.form.FarmServerForm;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class FarmServerAction extends CRUDBaseAction {
	private FarmServerForm theForm;

	public FarmServerForm getTheForm() {
		return theForm;
	}

	public void setTheForm(FarmServerForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询farmServer信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public String farmServerList() throws BaseException {
		if (theForm == null) {
			theForm = new FarmServerForm();
		}
		FarmServerObj obj = new FarmServerObj();
		if (theForm.getId() != null && !"".equals(theForm.getId())) {
			obj.setId(Integer.parseInt(theForm.getId()));
		}
		if (theForm.getServerName() != null
				&& !"".equals(theForm.getServerName())) {
			obj.setServerName(theForm.getServerName().trim());
		}

		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List list = farmServerService.queryForListByObj(obj);
		theForm.setResultList(list);
		return LIST;
	}

	/**
	 * @Title:新增farmServer信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public String addServerfarms() throws BaseException {
		if (theForm == null) {
			theForm = new FarmServerForm();
		}
		FarmObj farmObj = new FarmObj();
		List farmsList = farmsService.queryForListByObj(farmObj);
		theForm.setFarmList(farmsList);
		theForm.setServerAddress("0.0.0.0");
		theForm.setWeight(1);
		theForm.setResponseThreshold(0);
		theForm.setConnectionLimit(0);
		theForm.setClientNATAddressRange("0.0.0.0");
		theForm.setBackupServerAddress("0.0.0.0");
		theForm.setClientNAT("2");
		return ADD;
	}

	/**
	 * @Title:保存farmServer信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public String savefarmServer() throws BaseException {
		if (theForm == null) {
			theForm = new FarmServerForm();
		}
		FarmServerObj farmServerObj = new FarmServerObj();
		try {
			BeanUtils.copyProperties(farmServerObj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ("".equals(theForm.getId()) || null == theForm.getId()) {
			farmServerService.insertByObj(farmServerObj);
		}
		return "add_save";
	}

	/**
	 * @Title:删除farmServer信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String delfarmServer() throws BaseException {
		if (theForm == null) {
			theForm = new FarmServerForm();
		}
		FarmServerObj obj = new FarmServerObj();
		obj.setId(Integer.parseInt(theForm.getId()));
		farmServerService.deleteByObj(obj);

		// return farmServerList(mapping, form, request, response);
		return "del";
	}

	/**
	 * @Title:修改farmServer信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public String modfarmServer() throws BaseException {

		if (theForm == null) {
			theForm = new FarmServerForm();
		}
		FarmObj farmObj = new FarmObj();
		List farmsList = farmsService.queryForListByObj(farmObj);
		FarmServerObj farmServerObj = new FarmServerObj();
		farmServerObj.setId(Integer.parseInt(theForm.getId()));
		farmServerObj = farmServerService.queryByObj(farmServerObj);
		try {
			BeanUtils.copyProperties(theForm, farmServerObj);
			theForm.setFarmList(farmsList);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return MODIFY;
	}

	/**
	 * @Title:修改保存farmServer信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public String sureModfarmServer() throws BaseException {
		if (theForm == null) {
			theForm = new FarmServerForm();
		}
		FarmServerObj obj = new FarmServerObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		farmServerService.updateByObj(obj);
		// theForm.reset(mapping, request);
		return "mod_save";
	}

	FarmServerService farmServerService;

	FarmsService farmsService;

	public void setFarmServerService(FarmServerService farmServerService) {
		this.farmServerService = farmServerService;
	}

	public void setFarmsService(FarmsService farmsService) {
		this.farmsService = farmsService;
	}

}
