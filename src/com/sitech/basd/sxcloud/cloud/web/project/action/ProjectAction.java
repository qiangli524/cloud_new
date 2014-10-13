package com.sitech.basd.sxcloud.cloud.web.project.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.project.TbCloud2ProjectInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vusermanage.VuserManageObj;
import com.sitech.basd.sxcloud.cloud.service.project.TbProjectService;
import com.sitech.basd.sxcloud.cloud.service.vusermanage.VuserManageService;
import com.sitech.basd.sxcloud.cloud.web.project.form.ProjectForm;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

public class ProjectAction extends CRUDBaseAction {
	private ProjectForm theForm;

	public ProjectForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ProjectForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询项目信息
	 * @Copyright: Copyright (c) 20120109
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listProject() throws BaseException {
		if (theForm == null) {
			theForm = new ProjectForm();
		}
		// theForm.setP_ID(0);
		TbCloud2ProjectInfoObj obj = new TbCloud2ProjectInfoObj();
		if (theForm.getPROJECT_ID() != null && !"".equals(theForm.getPROJECT_ID())) {
			obj.setPROJECT_ID(theForm.getPROJECT_ID().trim());
		}
		if (theForm.getNAME() != null && !"".equals(theForm.getNAME())) {
			obj.setNAME(theForm.getNAME().trim());
		}

		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List projectList = tbProjectService.queryForListByObj(obj);
		theForm.setResultList(projectList);
		return SUCCESS;

	}

	/**
	 * @Title:增加项目信息
	 * @Copyright: Copyright (c) 20120109
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public String addProject() throws BaseException {
		if (theForm == null) {
			theForm = new ProjectForm();
		}
		return "add";
	}

	/**
	 * @Title:保存项目信息
	 * @Copyright: Copyright (c) 20120109
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public String saveProject() throws BaseException {
		if (theForm == null) {
			theForm = new ProjectForm();
		}
		TbCloud2ProjectInfoObj obj = new TbCloud2ProjectInfoObj();
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
		if (theForm.getFlag() == 0) {
			if (theForm.getISSTAGING() == null && theForm.getISPUBLIC() == null) {
				obj.setISSTAGING("0");
				obj.setISPUBLIC("0");
				ret = tbProjectService.insertByObj(obj);
			} else {
				if (theForm.getISDEFAULT() == null && theForm.getISPUBLIC() == null) {
					obj.setISDEFAULT("0");
					obj.setISPUBLIC("0");
					ret = tbProjectService.insertByObj(obj);
				} else {
					if (theForm.getISDEFAULT() == null && theForm.getISSTAGING() == null) {
						obj.setISDEFAULT("0");
						obj.setISSTAGING("0");
						ret = tbProjectService.insertByObj(obj);
					}
				}
			}
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增项目信息");
		} else {
			if (theForm.getISSTAGING() == null && theForm.getISPUBLIC() == null) {
				obj.setISSTAGING("0");
				obj.setISPUBLIC("0");
				ret = tbProjectService.updateByObj(obj);
			} else {
				if (theForm.getISDEFAULT() == null && theForm.getISPUBLIC() == null) {
					obj.setISDEFAULT("0");
					obj.setISPUBLIC("0");
					ret = tbProjectService.updateByObj(obj);
				} else {
					if (theForm.getISDEFAULT() == null && theForm.getISSTAGING() == null) {
						obj.setISDEFAULT("0");
						obj.setISSTAGING("0");
						ret = tbProjectService.updateByObj(obj);
					}
				}
			}
			ret = tbProjectService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改项目信息");
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "savesuccess";
	}

	/**
	 * @Title:修改项目信息
	 * @Copyright: Copyright (c) 20120109
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public String modProject() throws BaseException {
		if (theForm == null) {
			theForm = new ProjectForm();
		}
		TbCloud2ProjectInfoObj obj = new TbCloud2ProjectInfoObj();
		obj.setPROJECT_ID(theForm.getPROJECT_ID());
		TbCloud2ProjectInfoObj tempObj = tbProjectService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return "add";
	}

	/**
	 * @Title:删除项目信息
	 * @Copyright: Copyright (c) 20120109
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public String delProject() throws BaseException {
		if (theForm == null) {
			theForm = new ProjectForm();
		}
		TbCloud2ProjectInfoObj obj = new TbCloud2ProjectInfoObj();
		int result = 0;
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int ret = tbProjectService.deleteByObj(obj);
		if (ret > 0) {
			result = 1;
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除项目信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "savesuccess";
	}

	/**
	 * @Title:编辑用户
	 * @Copyright: Copyright (c) 20120214
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listVuserInfoWindow() throws BaseException, Exception {
		if (theForm == null) {
			theForm = new ProjectForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String project_id = request.getParameter("PROJECT_ID");
		if (project_id != null && !"".equals(project_id)) {
			theForm.setPROJECT_ID(project_id);
		}
		String project_name = request.getParameter("NAME");
		if (project_name != null && !"".equals(project_name)) {
			theForm.setNAME(project_name);
		}
		String description = request.getParameter("DESCRIPTION");
		if (description != null && !"".equals(description)) {
			theForm.setDESCRIPTION(description);
		}
		VuserManageObj obj = new VuserManageObj();
		obj.setUSER_ID(theForm.getUSER_ID());
		obj.setUSER_NAME(theForm.getUSER_NAME());
		obj.setPROJECT_ID(theForm.getPROJECT_ID());
		obj.setISADMIN(theForm.getISADMIN());
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		// List vuserList = vuserManageService.queryForListByObj(obj);
		List vuserList = tbProjectService.queryUserListByObj(obj);
		theForm.setResultList(vuserList);
		return "listVuserInfo";
	}

	public String getVuserRole() throws BaseException, Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String projectId = request.getParameter("projectId");
		VuserManageObj obj = new VuserManageObj();
		obj.setPROJECT_ID(projectId);
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List vuserList = tbProjectService.queryUserListByObj(obj);
		/** 将vuserList装到JSON传到页面 */
		response.setContentType("text/html;charset=gb2312");
		JSONArray ja = new JSONArray();
		ja = JSONArray.fromObject(vuserList);
		// PrintWriter out = response.getWriter();
		// out.println(ja.toString());
		// out.close();
		PrintWriterOut.printWirter(response, ja.toString());
		return null;
	}

	/**
	 * @Title:保存项目中的用户信息
	 * @Copyright: Copyright (c) 20120214
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String saveProVuser() throws BaseException {
		if (theForm == null) {
			theForm = new ProjectForm();
		}
		TbCloud2ProjectInfoObj obj = new TbCloud2ProjectInfoObj();
		obj.setID(theForm.getID());
		obj.setPROJECT_ID(theForm.getPROJECT_ID());
		obj.setDESCRIPTION(theForm.getDESCRIPTION());
		obj.setNAME(theForm.getNAME());
		obj.setUSER_STAT_ID(theForm.getUSER_STAT_ID());
		int result = tbProjectService.insertVuserByObj(obj);
		/** 写操作日志 */
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");
		int ret = 0;
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "saveProVuser";
	}

	private TbProjectService tbProjectService;

	public void setTbProjectService(TbProjectService tbProjectService) {
		this.tbProjectService = tbProjectService;
	}

	private VuserManageService vuserManageService;

	public void setVuserManageService(VuserManageService vuserManageService) {
		this.vuserManageService = vuserManageService;
	}
}
