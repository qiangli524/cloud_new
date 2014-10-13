package com.sitech.basd.sxcloud.cloud.web.net.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import service.TbDirectoryCodeService;

import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.service.net.IpService;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.cloud.web.net.form.NetForm;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

@SuppressWarnings("all")
public class NetAction extends CRUDBaseAction {
	private NetForm theForm;
	@Autowired
	private TbDirectoryCodeService tbDirectoryCodeService;

	public NetForm getTheForm() {
		return theForm;
	}

	public void setTheForm(NetForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title:查询系统网络信息
	 * @Copyright: Copyright (c) 2011-12-13
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	public String listNetInfo() throws BaseException {
		if (theForm == null) {
			theForm = new NetForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String oper = request.getParameter("oper");
		TbCloud2NetInfoObj obj = new TbCloud2NetInfoObj();
		if (theForm.getQUERY_NAME() != null && !"".equals(theForm.getQUERY_NAME())) {
			obj.setNAME(theForm.getQUERY_NAME().trim());
		}
		if (theForm.getQUERY_NET_TYPE() != null && !"".equals(theForm.getQUERY_NET_TYPE())) {
			obj.setNET_TYPE(theForm.getQUERY_NET_TYPE());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List netList = netService.queryForListByObj(obj);
		theForm.setResultList(netList);
		if (oper != null) {
			request.setAttribute("oper", oper);
		} else {
			request.setAttribute("oper", "0");
		}
		return LIST;

	}

	/**
	 * @Title:增加网络管理模块信息
	 * @Copyright: Copyright (c) 20111210
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String addNetInfo() throws BaseException {
		if (theForm == null) {
			theForm = new NetForm();
		}
		try {
			theForm.setDomainList(tbDirectoryCodeService
					.queryForListByDiType("net.security.domian"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ADD;
	}

	/**
	 * @Title:保存网络信息请求
	 * @Copyright: Copyright (c) 20111210
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String saveNetInfo() throws BaseException {
		if (theForm == null) {
			theForm = new NetForm();
		}
		TbCloud2NetInfoObj obj = new TbCloud2NetInfoObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");

		int ret = 0;
		int result = 0;
		if (theForm.getNET_ID().equals("")) {
			ret = netService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增功能信息");
		} else {
			ret = netService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改功能信息");
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "redirect";
	}

	/**
	 * @Title:修改网络管理模块信息
	 * @Copyright: Copyright (c) 20111210
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String modNetInfo() throws BaseException {
		if (theForm == null) {
			theForm = new NetForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2NetInfoObj obj = new TbCloud2NetInfoObj();
		String netId = request.getParameter("netId");
		if (netId != null && !"".equals(netId)) {
			request.setAttribute("net_id", netId);
			theForm.setNET_ID(netId);
			obj.setNET_ID(netId);
		} else {
			request.setAttribute("net_id", theForm.getNET_ID());
			theForm.setNET_ID(theForm.getNET_ID());
			obj.setNET_ID(theForm.getNET_ID());
		}
		// obj.setNET_ID(theForm.getNET_ID());
		obj.setDESCRIPTION(theForm.getDESCRIPTION());
		TbCloud2NetInfoObj tempObj = netService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		try {
			theForm.setDomainList(tbDirectoryCodeService
					.queryForListByDiType("net.security.domian"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ADD;
	}

	/**
	 * @Title:删除网络管理模块请求
	 * @Copyright: Copyright (c) 20111210
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String delNetInfo() throws BaseException {
		if (theForm == null) {
			theForm = new NetForm();
		}
		TbCloud2NetInfoObj obj = new TbCloud2NetInfoObj();
		int result = 0;
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		obj.setNET_ID(theForm.getNET_ID());
		int ret = netService.deleteByObj(obj);
		// 删除网络内ip
		TbCloud2IpInfoObj ipInfoObj = new TbCloud2IpInfoObj();
		ipInfoObj.setNET_ID(theForm.getNET_ID());
		int ret1 = ipService.deleteByObj(ipInfoObj);
		if (ret > 0) {
			result = 1;
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除功能信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "redirect";
	}

	/**
	 * 
	 * @Title:树中单击时显示的网络信息
	 * @Copyright: Copyright (c) 2011-12-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	public String listNetNode() throws BaseException {
		if (theForm == null) {
			theForm = new NetForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		TbCloud2NetInfoObj obj = new TbCloud2NetInfoObj();
		obj.setNET_ID(id);
		obj.setNET_TYPE("2");
		obj = netService.queryByObj(obj);
		theForm.setNAME(obj.getNAME());
		theForm.setDESCRIPTION(obj.getDESCRIPTION());
		theForm.setFREECOUNT(obj.getFREECOUNT());
		theForm.setUSEDCOUNT(obj.getUSEDCOUNT());
		theForm.setISDEFAULT(obj.getISDEFAULT());
		return "listNetNode";

	}

	/**
	 * 
	 * 
	 * @Title: adjustNetUseful
	 * @Description: 判断网络是否可以删除
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 20, 2013 5:02:58 PM
	 */
	public String adjustNetUseful() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String netId = request.getParameter("NET_ID");
		TbCloud2IpInfoObj ipInfo = new TbCloud2IpInfoObj();
		ipInfo.setNET_ID(netId);
		List ipLst = ipService.queryForListByIPObj(ipInfo);
		String useing = "{\"useing\":\"" + "no" + "\"}";
		for (Object object : ipLst) {
			TbCloud2IpInfoObj ip = (TbCloud2IpInfoObj) object;
			if (ip.getISUSED().equals("1")) {
				useing = "{\"useing\":\"" + "yes" + "\"}";
				break;
			}
		}
		JSONObject j0 = JSONObject.fromObject(useing);
		try {
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			out.println(j0);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private NetService netService;
	private IpService ipService;

	public void setIpService(IpService ipService) {
		this.ipService = ipService;
	}

	public void setNetService(NetService netService) {
		this.netService = netService;
	}

}
