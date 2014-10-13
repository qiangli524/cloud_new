package com.sitech.basd.sxcloud.rsmu.web.cloudschedu.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.rsmu.domain.cloudschedu.RealServerObj;
import com.sitech.basd.sxcloud.rsmu.domain.cloudschedu.VirtualServerObj;
import com.sitech.basd.sxcloud.rsmu.lbsms.LbsClient;
import com.sitech.basd.sxcloud.rsmu.lbsms.LbsResult;
import com.sitech.basd.sxcloud.rsmu.lbsms.RealServerBean;
import com.sitech.basd.sxcloud.rsmu.service.cloudschedu.RealServerService;
import com.sitech.basd.sxcloud.rsmu.service.cloudschedu.VirtualServerService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.cloudschedu.form.RealServerForm;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.sxcloud.util.exception.BaseException;

public class RealServerAction extends BaseAction {
	private final String username = "u1";
	private final String password = "";
	private final String url = "";
	private RealServerForm theForm;

	public RealServerForm getTheForm() {
		return theForm;
	}

	public void setTheForm(RealServerForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有应用信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String realServerList() throws BaseException {

		if (theForm == null) {
			theForm = new RealServerForm();
		}
		RealServerObj obj = new RealServerObj();
		if (theForm.getName() != null && !"".equals(theForm.getName())) {
			obj.setName(theForm.getName().trim());
		}
		if (theForm.getRealAddress() != null && !"".equals(theForm.getRealAddress())) {
			obj.setRealAddress(theForm.getRealAddress().trim());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List list = realServerService.queryForListByObj(obj);
		theForm.setResultList(list);
		return "realServerList";
	}

	/**
	 * @Title:新增真实服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String addRealServer() throws BaseException {
		if (theForm == null) {
			theForm = new RealServerForm();
		}
		VirtualServerObj obj = new VirtualServerObj();
		List virtualServerlist = virtualServerService.queryForListByObj(obj);
		theForm.setVirtualServerlist(virtualServerlist);
		theForm.setWeight("1");
		return "addRealServer";
	}

	/**
	 * @Title:保存真实服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String sureAddRealServer() throws BaseException {
		if (theForm == null) {
			theForm = new RealServerForm();
		}
		RealServerObj obj = new RealServerObj();
		// TbSysUserinfoObj tempObj = (TbSysUserinfoObj)
		// Struts2Utils.getRequest().getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		obj.setOptr_id("cloud");
		obj.setVirtualAddress(theForm.getVirtualAddress());
		obj.setName(theForm.getName());
		obj.setRealAddress(theForm.getRealAddress());
		obj.setWeight(theForm.getWeight());
		obj.setReceive(theForm.getReceive());
		obj.setRequest(theForm.getRequest());
		obj.setRealAddress(theForm.getRealAddress());

		// 添加虚拟服务器成功后,主机调用调度中心接口

		LbsClient client = new LbsClient(username, password, url);
		RealServerBean rsBean = new RealServerBean();
		try {
			BeanUtils.copyProperties(rsBean, theForm);
			rsBean.setLoginNo(username);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		// 添加成功,info值为服务器的数据库id项
		LbsResult result = client.AddRealServer(rsBean);
		if (result.getCode() == 0) {
			obj.setINFO(result.getInfo());
			realServerService.insertByObj(obj);
		}

		// theForm.reset(mapping, request);
		return realServerList();
	}

	/**
	 * @Title:删除真实服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String delRealServer() throws BaseException {
		if (theForm == null) {
			theForm = new RealServerForm();
		}
		RealServerObj obj = new RealServerObj();
		obj.setINFO(new Integer(theForm.getId()).toString());
		obj = realServerService.queryByObj(obj);

		// 删除真实服务器成功后,主机调用调度中心接口
		LbsClient client = new LbsClient(username, password, url);
		RealServerBean rsBean = new RealServerBean();
		rsBean.setId(new Integer(theForm.getId()).toString());
		rsBean.setVirtualAddress(obj.getVirtualAddress());
		rsBean.setName(obj.getName());
		rsBean.setRealAddress(obj.getRealAddress());
		rsBean.setWeight(obj.getWeight());
		rsBean.setRequest(obj.getRequest());
		rsBean.setReceive(obj.getReceive());

		rsBean.setLoginNo(username);
		LbsResult result = client.DeleteRealServer(rsBean);
		if (result.getCode() == 0) {
			realServerService.deleteByObj(obj);
		}

		return realServerList();
	}

	/**
	 * @Title:修改真实服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String modRealServer() throws BaseException {
		if (theForm == null) {
			theForm = new RealServerForm();
		}
		RealServerObj obj = new RealServerObj();
		obj.setINFO(new Integer(theForm.getId()).toString());
		RealServerObj realServerObj = realServerService.queryByObj(obj);
		VirtualServerObj objTemp = new VirtualServerObj();
		List virtualServerlist = virtualServerService.queryForListByObj(objTemp);
		theForm.setVirtualServerlist(virtualServerlist);
		try {
			BeanUtils.copyProperties(theForm, realServerObj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		theForm.setWeight(realServerObj.getWeight());
		return "modRealServer";
	}

	/**
	 * @Title:保存修改真实服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String sureModRealServer() throws BaseException {
		if (theForm == null) {
			theForm = new RealServerForm();
		}
		RealServerObj obj = new RealServerObj();
		// TbSysUserinfoObj tempObj = (TbSysUserinfoObj)
		// Struts2Utils.getRequest().getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		obj.setINFO(theForm.getINFO());
		obj.setOptr_id(session.get("account").toString());
		obj.setVirtualAddress(theForm.getVirtualAddress());
		obj.setName(theForm.getName());
		obj.setRealAddress(theForm.getRealAddress());
		obj.setWeight(theForm.getWeight());
		obj.setReceive(theForm.getReceive());
		obj.setRequest(theForm.getRequest());
		realServerService.updateByObj(obj);

		LbsClient client = new LbsClient(username, password, url);
		RealServerBean rsBean = new RealServerBean();
		try {
			BeanUtils.copyProperties(rsBean, theForm);
			rsBean.setId(theForm.getINFO());
			rsBean.setLoginNo(username);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LbsResult result = client.UpdateRealServer("", "", "", rsBean);
		// theForm.reset(mapping, request);
		return realServerList();
	}

	RealServerService realServerService;

	public void setRealServerService(RealServerService realServerService) {
		this.realServerService = realServerService;
	}

	VirtualServerService virtualServerService;

	public void setVirtualServerService(VirtualServerService virtualServerService) {
		this.virtualServerService = virtualServerService;
	}

}
