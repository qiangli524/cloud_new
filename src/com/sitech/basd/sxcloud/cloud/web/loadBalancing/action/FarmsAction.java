package com.sitech.basd.sxcloud.cloud.web.loadBalancing.action;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.FarmObj;
import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.FarmServerObj;
import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.L4PolicyObj;
import com.sitech.basd.sxcloud.cloud.service.loadBalancing.FarmServerService;
import com.sitech.basd.sxcloud.cloud.service.loadBalancing.FarmsService;
import com.sitech.basd.sxcloud.cloud.service.loadBalancing.L4_policyService;
import com.sitech.basd.sxcloud.cloud.web.loadBalancing.form.FarmForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class FarmsAction extends CRUDBaseAction {
	private FarmForm theForm;

	public FarmForm getTheForm() {
		return theForm;
	}

	public void setTheForm(FarmForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有farms信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public String farmsList() throws BaseException {
		if (theForm == null) {
			theForm = new FarmForm();
		}
		FarmObj obj = new FarmObj();
		if (theForm.getFarmName() != null && !"".equals(theForm.getFarmName())) {
			obj.setFarmName(theForm.getFarmName().trim());
		}
		if (theForm.getId() != null && !"".equals(theForm.getId())) {
			obj.setId(Integer.parseInt(theForm.getId()));
		}

		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List list = farmsService.queryForListByObj(obj);
		theForm.setResultList(list);
		return LIST;
	}

	/**
	 * @Title:新增真实服务器信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public String addfarms() throws BaseException {
		if (theForm == null) {
			theForm = new FarmForm();
		}
		theForm.setAgingTime("60");
		theForm.setConnectivityCheckPort(80);
		theForm.setConnectivityCheckInterval(10);
		theForm.setConnectivityCheckRetries(5);
		theForm.setExtendedCheckFrequency(10);
		theForm.setBandwidthLimit(0);

		return ADD;
	}

	/**
	 * @Title:保存虚拟服务器信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 * @throws ServiceException
	 */
	public String savefarms() throws BaseException {
		if (theForm == null) {
			theForm = new FarmForm();
		}
		FarmObj farmObj = new FarmObj();
		try {
			BeanUtils.copyProperties(farmObj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ("".equals(theForm.getId()) || null == theForm.getId()) {
			farmsService.insertByObj(farmObj);
		}
		// theForm.reset(mapping, request);

		return "save";
	}

	/**
	 * @Title:删除真实服务器信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public String delfarms() throws BaseException {
		if (theForm == null) {
			theForm = new FarmForm();
		}
		FarmObj obj = new FarmObj();
		obj.setId(Integer.parseInt(theForm.getId()));
		FarmObj objall = farmsService.queryByObj(obj);
		String farmName = objall.getFarmName();

		FarmServerObj farmServerObj = new FarmServerObj();
		farmServerObj.setFarmName(farmName);
		List list = farmServerService.queryForListByObj(farmServerObj);
		for (Object object : list) {
			FarmServerObj fsObj = (FarmServerObj) object;
			farmServerService.deleteByObj(fsObj);
		}
		farmsService.deleteByObj(obj);
		theForm.setFarmName(null);
		return "del";

	}

	/**
	 * 
	 * @Title: queryAjx_del
	 * @Description: 验证farm是否有l4policy在用，是否可以删除
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime Mar 27, 2012 6:03:56 PM
	 */
	public String queryAjx_del() throws BaseException {
		FarmObj obj = new FarmObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		obj.setId(Integer.valueOf(request.getParameter("id")));
		String farmName = farmsService.queryByObj(obj).getFarmName();
		L4PolicyObj l4PolicyObj = new L4PolicyObj();
		l4PolicyObj.setFarmName(farmName);
		int size = l4policyService.queryForListByObj(l4PolicyObj).size();
		try {
			request.setCharacterEncoding("gb2312");// 字符串转换
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			if (size > 0) {
				out.print("no");
			} else {
				out.print("ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title:修改farms信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public String modfarms() throws BaseException {
		if (theForm == null) {
			theForm = new FarmForm();
		}
		FarmObj farmObj = new FarmObj();
		farmObj.setId(Integer.parseInt(theForm.getId()));
		farmObj = farmsService.queryByObj(farmObj);
		try {
			BeanUtils.copyProperties(theForm, farmObj);
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
	 * @Title:修改保存farms信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public String sureModfarms() throws BaseException {
		if (theForm == null) {
			theForm = new FarmForm();
		}
		FarmObj obj = new FarmObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		farmsService.updateByObj(obj);
		// theForm.reset(mapping, request);
		return "";
	}

	FarmsService farmsService;

	FarmServerService farmServerService;

	L4_policyService l4policyService;

	public void setFarmServerService(FarmServerService farmServerService) {
		this.farmServerService = farmServerService;
	}

	public void setL4policyService(L4_policyService service) {
		l4policyService = service;
	}

	public void setFarmsService(FarmsService farmsService) {
		this.farmsService = farmsService;
	}

}
