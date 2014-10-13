package com.sitech.basd.sxcloud.cloud.web.loadBalancing.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.FarmObj;
import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.L4PolicyObj;
import com.sitech.basd.sxcloud.cloud.service.loadBalancing.FarmsService;
import com.sitech.basd.sxcloud.cloud.service.loadBalancing.L4_policyService;
import com.sitech.basd.sxcloud.cloud.web.loadBalancing.form.L4PolicyForm;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class L4PolicyAction extends CRUDBaseAction {
	private L4PolicyForm theForm;

	public L4PolicyForm getTheForm() {
		return theForm;
	}

	public void setTheForm(L4PolicyForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有l4Policy信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public String l4PolicyList() throws BaseException {
		if (theForm == null) {
			theForm = new L4PolicyForm();
		}
		L4PolicyObj obj = new L4PolicyObj();
		if (null != theForm.getVirtualIP()
				&& !"".equals(theForm.getVirtualIP())) {
			obj.setVirtualIP(theForm.getVirtualIP().trim());
		}
		if (null != theForm.getL4PolicyName()
				&& !"".equals(theForm.getL4PolicyName())) {
			obj.setFarmName(theForm.getL4PolicyName().trim());
		}

		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List list = l4policyService.queryForListByObj(obj);
		theForm.setResultList(list);
		return LIST;
	}

	/**
	 * @Title:新增l4Policy信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public String addl4Policy() throws BaseException {
		if (theForm == null) {
			theForm = new L4PolicyForm();
		}
		FarmObj farmObj = new FarmObj();
		List farmsList = farmsService.queryForListByObj(farmObj);
		theForm.setFarmList(farmsList);
		theForm.setSourceIPFrom("0.0.0.0");
		theForm.setSourceIPTo("0.0.0.0");
		theForm.setVirtualIP("0.0.0.0");
		return ADD;
	}

	/**
	 * @Title:保存l4Policy信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public String savel4Policy() throws BaseException {
		if (theForm == null) {
			theForm = new L4PolicyForm();
		}
		L4PolicyObj l4PolicyObj = new L4PolicyObj();
		try {
			BeanUtils.copyProperties(l4PolicyObj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (0 == theForm.getId() || null == theForm.getId()) {
			l4policyService.insertByObj(l4PolicyObj);
		}
		// theForm.reset(mapping, request);
		return "add_save";
	}

	/**
	 * @Title:删除l4Policy信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public String dell4Policy() throws BaseException {
		if (theForm == null) {
			theForm = new L4PolicyForm();
		}
		L4PolicyObj obj = new L4PolicyObj();
		obj.setId(theForm.getId());
		l4policyService.deleteByObj(obj);

		return "del";
	}

	/**
	 * @Title:修改l4Policy信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public String modl4Policy() throws BaseException {
		if (theForm == null) {
			theForm = new L4PolicyForm();
		}

		FarmObj farmObj = new FarmObj();
		List farmsList = farmsService.queryForListByObj(farmObj);
		L4PolicyObj obj = new L4PolicyObj();
		obj.setId(theForm.getId());
		obj = l4policyService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, obj);
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
	 * @Title:修改l4Policy信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public String sureModl4Policy() throws BaseException {
		if (theForm == null) {
			theForm = new L4PolicyForm();
		}
		L4PolicyObj obj = new L4PolicyObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		l4policyService.updateByObj(obj);
		// theForm.reset(mapping, request);
		return "mod_save";
	}

	L4_policyService l4policyService;

	FarmsService farmsService;

	public void setL4policyService(L4_policyService service) {
		l4policyService = service;
	}

	public void setFarmsService(FarmsService farmsService) {
		this.farmsService = farmsService;
	}

}
