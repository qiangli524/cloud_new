package com.sitech.basd.yicloud.web.snapshot.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.snapshot.Snapshot;
import com.sitech.basd.yicloud.service.snapshot.SnapshotService;
import com.sitech.basd.yicloud.web.snapshot.form.SnapshotForm;

public class SnapshotAction extends CRUDBaseAction {
	private SnapshotService snapshotService;
	private SnapshotForm theForm;

	public SnapshotForm getTheForm() {
		return theForm;
	}

	public void setTheForm(SnapshotForm theForm) {
		this.theForm = theForm;
	}

	public void setSnapshotService(SnapshotService snapshotService) {
		this.snapshotService = snapshotService;
	}

	/**
	 * @Title:查询快照信息列表
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String snapshotManager() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String name = request.getParameter("name");// 得到节点名称，即虚拟机名称
		String entityId = request.getParameter("entityId");// 实体Id，即code
		// name = "huojl111";
		List<Snapshot> resultList = snapshotService.querySnapshotList(entityId);
		if (theForm == null) {
			theForm = new SnapshotForm();
		}
		theForm.setResultList(resultList);
		theForm.setVmName(name);
		request.setAttribute("entityId", entityId);
		return "snapshotManager";
	}

	/**
	 * @Title:进入创建快照的页面
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 */
	public String executeSnapshot() throws UnsupportedEncodingException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String name = request.getParameter("name");// 得到节点名称，即虚拟机名称
		String entityId = request.getParameter("entityId");// 节点Id，即code
		// name = "huojl111";
		name = URLDecoder.decode(name, "utf-8");
		request.setAttribute("name", name);
		request.setAttribute("entityId", entityId);
		return "executeSnapshot";
	}

	/**
	 * @Title:创建快照
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 * @throws Exception
	 */
	public String createSnapShot() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String vmName = request.getParameter("vmName");
		String name = request.getParameter("name");
		name = URLDecoder.decode(name, "utf-8");
		String desc = request.getParameter("desc");
		String code = request.getParameter("code");
		boolean memSnapshot = Boolean.parseBoolean(request
				.getParameter("memSnapshot"));
		boolean isDefault = Boolean.parseBoolean(request
				.getParameter("isDefault"));
		Snapshot snapshot = new Snapshot();
		snapshot.setVmName(vmName);// 进行快照的虚拟机名称
		snapshot.setName(name);
		snapshot.setDescription(desc);
		snapshot.setSnap_uuid(code);
		String result = snapshotService.createSnapshot(snapshot, memSnapshot,
				isDefault);
		PrintWriter p = Struts2Utils.getResponse().getWriter();
		p.print(result);
		p.close();
		return null;
	}

	/**
	 * @Title:恢复快照
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 * @throws Exception
	 */
	public String revertSnapshot() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String vmName = request.getParameter("vmName");
		String name = request.getParameter("name");// 快照名称
		String code = request.getParameter("code");
		Snapshot snapshot = new Snapshot();
		snapshot.setVmName(vmName);
		snapshot.setName(name);
		snapshot.setSnap_uuid(code);
		String result = snapshotService.revertSnapshot(snapshot);
		PrintWriter p = Struts2Utils.getResponse().getWriter();
		p.print(result);
		p.close();
		return null;
	}

	/**
	 * @Title:删除快照信息
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 * @throws Exception
	 */
	public String deleteSnapshot() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String vmName = request.getParameter("vmName");
		String name = request.getParameter("name");// 快照名称
		String code = request.getParameter("code");
		Snapshot snapshot = new Snapshot();
		snapshot.setVmName(vmName);
		snapshot.setName(name);
		snapshot.setSnap_uuid(code);
		String result = snapshotService.deleteSnapshot(snapshot);
		PrintWriter p = Struts2Utils.getResponse().getWriter();
		p.print(result);
		p.close();
		return null;
	}

	/**
	 * @Title:删除全部快照信息
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 * @throws Exception
	 */
	public String deleteAllSnapshot() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String vmName = request.getParameter("vmName");
		String result = snapshotService.deleteAllSnapshot(vmName);
		PrintWriter p = Struts2Utils.getResponse().getWriter();
		p.print(result);
		p.close();
		return null;
	}
}
