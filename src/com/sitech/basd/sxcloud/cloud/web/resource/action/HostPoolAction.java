package com.sitech.basd.sxcloud.cloud.web.resource.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostPoolObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostPoolService;
import com.sitech.basd.sxcloud.cloud.web.resource.form.HostManageForm;
import com.sitech.basd.sxcloud.cloud.web.resource.form.HostPoolForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.sxcloud.util.UUIDGenerator;

public class HostPoolAction extends BaseAction {

	private HostPoolForm theForm;

	private HostPoolService hostPoolService;

	private String theForm_NAME;

	private String hostID;

	private HostInfoService hostInfoService;

	private HostManageForm hostForm;

	public HostManageForm getHostForm() {
		return hostForm;
	}

	public void setHostForm(HostManageForm hostForm) {
		this.hostForm = hostForm;
	}

	/**
	 * 
	 * @Title: 查询主机池信息
	 * @Copyright: Copyright (c) 2013-02-2b ,7
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public String allHostPoolList() {
		if (theForm == null) {
			theForm = new HostPoolForm();
		}
		TbCloud2HostPoolObj obj = new TbCloud2HostPoolObj();
		if (theForm_NAME != null && !"".equals(theForm_NAME)) {
			obj.setNAME(theForm_NAME.trim());
		}
		if (theForm.getPOOL_TYPE() != null && !"".equals(theForm.getPOOL_TYPE())) {
			obj.setPOOL_TYPE(theForm.getPOOL_TYPE().trim());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List hostPoolList = hostPoolService.queryHostForPool(obj);// 查询主机池列表
		theForm.setLIST_POOL(hostPoolList);
		return "allPool";
	}

	/**
	 * 
	 * @Title: 删除主机名称信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public String deleteHostPool() {

		if (theForm == null) {
			theForm = new HostPoolForm();
		}

		TbCloud2HostPoolObj obj = new TbCloud2HostPoolObj();
		int result = 0;
		obj.setID(theForm.getID());
		// 删除主机池和主机的关联关系
		result = hostPoolService.deleteHostRelation(obj);
		if (result == -1) {
			showErrorMsg(1);
			return null;
		}
		// 删除主机池信息
		result = hostPoolService.deleteHostForPool(obj);
		if (result == -1) {
			showErrorMsg(2);
			return null;
		}
		showErrorMsg(0);
		return null;
	}

	/**
	 * 
	 * @Title: 进入修改主机池信息修改页面
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public String modHostPool() {
		if (theForm == null) {
			theForm = new HostPoolForm();
		}
		TbCloud2HostPoolObj obj = new TbCloud2HostPoolObj();
		obj.setID(theForm.getID());
		TbCloud2HostPoolObj tObj = hostPoolService.queryHostObj(obj);
		theForm.setID(tObj.getID());
		theForm.setNAME(tObj.getNAME());
		theForm.setPOOL_TYPE(tObj.getPOOL_TYPE());
		theForm.setPOOL_DESC(tObj.getPOOL_DESC());
		theForm.setFlag(1);
		return "modify";
	}

	/**
	 * 
	 * @Title: 增加与修改主机池信息页面
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public String saveHostPool() {
		if (theForm == null) {
			theForm = new HostPoolForm();
		}
		int flag = theForm.getFlag();
		TbCloud2HostPoolObj obj = new TbCloud2HostPoolObj();
		UUIDGenerator uuid = new UUIDGenerator();
		if (flag == 1) {
			obj.setID(theForm.getID());
		} else {
			obj.setID(uuid.getUUID());

		}
		obj.setNAME(theForm.getNAME());
		obj.setPOOL_DESC(theForm.getPOOL_DESC());
		obj.setPOOL_TYPE(theForm.getPOOL_TYPE());
		if (flag == 1) {
			hostPoolService.updateHostForPool(obj);
		} else {
			hostPoolService.insertHostForPool(obj);
		}

		return "save";
	}

	/**
	 * 
	 * @Title: host2pool
	 * @Description: 主机池中进入增加主机的界面
	 * @author duangh
	 * @date May 21, 2013 5:14:23 PM
	 * @return
	 */
	public String host2pool() {
		if (hostForm == null) {
			hostForm = new HostManageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		// 主机池ID
		String id = request.getParameter("id");
		// 增、删标识
		String flag = request.getParameter("flag");
		// 主机池类型
		String pooltype = request.getParameter("pool_type");
		if (id == null || id.equals("")) {
			id = hostForm.getHOST_POOL_ID();
			flag = hostForm.getFlag();
			pooltype = hostForm.getPOOL_TYPE();
		}

		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		obj.setHOST_POOL_ID(id);
		obj.setPOOL_TYPE(pooltype);

		// 获取查询条件
		if (hostForm != null) {
			String eq_name = hostForm.getEq_name();
			if (eq_name != null && !eq_name.equals("")) {
				obj.setEq_name(eq_name);
			}
			String eq_ip = hostForm.getEq_ip();
			if (eq_ip != null && !eq_ip.equals("")) {
				obj.setEq_ip(eq_ip);
			}
			String hasvertual = hostForm.getHasvertual();
			if (hasvertual != null && !hasvertual.equals("-1")) {
				obj.setHasvertual(hasvertual);
			}
			String eq_type = hostForm.getEq_type();
			if (eq_type != null && !eq_type.equals("-1")) {
				obj.setEq_type(eq_type);
			}
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		if (flag.equals("1")) {
			// 删除主机时，列出已选择的主机
			List checkedList = hostInfoService.queryAllHost(obj);// 查询已经在主机池中的主机列表
			request.setAttribute("checkedList", checkedList);
			request.setAttribute("flag", 1);
			request.setAttribute("pool_type", pooltype);
		} else {
			// 新增主机时列出未被选择的主机
			List resultList = hostInfoService.hostNotInPool(obj);// 查询没有在主机池中的主机
			request.setAttribute("resultList", resultList);
			request.setAttribute("flag", 0);
			request.setAttribute("pool_type", pooltype);
		}
		request.setAttribute("pool_id", id);

		return "host2pool";
	}

	/**
	 * 
	 * @Title: updatePoolId
	 * @Description: 保存主机添加到主机池中，即更新主机的主机池ID
	 * @author duangh
	 * @date May 21, 2013 5:14:47 PM
	 * @return
	 */
	public String updatePoolId() {
		HttpServletRequest request = Struts2Utils.getRequest();
		// 获取所选新添加主机的IDS
		String host_ids = request.getParameter("host_ids");
		// 获取主机池的ID
		String pool_id = request.getParameter("pool_id");
		// 获取是新增主机、删除主机的标识
		String flag = request.getParameter("flag");

		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		obj.setHOST_POOL_ID(pool_id);
		if (flag.equals("1")) {
			// 删除主机
			host_ids = host_ids.substring(0, host_ids.length() - 3);
			obj.setHOST_IDS(host_ids);
		} else {
			// 添加主机
			obj.setHOST_IDS(host_ids);
		}

		obj.setFlag(flag);
		int result = hostInfoService.updatePoolId(obj);
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			String json = "{\"result\":\"" + result + "\"}";
			p.print(json);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: 进入增加主机池信息页面
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public String addHostPool() {
		if (theForm == null) {
			theForm = new HostPoolForm();
		}
		theForm.setFlag(0);
		return "add";
	}

	public String getTheForm_NAME() {
		return theForm_NAME;
	}

	public void setTheForm_NAME(String theForm_NAME) {
		this.theForm_NAME = theForm_NAME;
	}

	public HostPoolForm getTheForm() {
		return theForm;
	}

	public void setTheForm(HostPoolForm theForm) {
		this.theForm = theForm;
	}

	public HostPoolService getHostPoolService() {
		return hostPoolService;
	}

	public void setHostPoolService(HostPoolService hostPoolService) {
		this.hostPoolService = hostPoolService;
	}

	public String getHostID() {
		return hostID;
	}

	public void setHostID(String hostID) {
		this.hostID = hostID;
	}

	public HostInfoService getHostInfoService() {
		return hostInfoService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

}
