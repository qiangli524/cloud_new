package com.sitech.basd.sxcloud.cloud.web.virtual.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.project.TbCloud2ProjectInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualPoolObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.Tbcloud2VirtualStorageInfoObj;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.cloud.service.project.TbProjectService;
import com.sitech.basd.sxcloud.cloud.service.virtual.VirtualPoolService;
import com.sitech.basd.sxcloud.cloud.service.virtual.VirtualService;
import com.sitech.basd.sxcloud.cloud.web.virtual.form.VirtualForm;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

public class VirtualAction extends CRUDBaseAction {
	private VirtualForm theForm;

	public VirtualForm getTheForm() {
		return theForm;
	}

	public void setTheForm(VirtualForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有虚拟机管理模块信息
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listVirtualInfo() throws BaseException {
		if (theForm == null) {
			theForm = new VirtualForm();
		}
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		if (theForm.getVH_NAME() != null && !"".equals(theForm.getVH_NAME())) {
			obj.setVH_NAME(theForm.getVH_NAME().trim());
		}
		if (theForm.getVH_STAT() != null && !"".equals(theForm.getVH_STAT())) {
			obj.setVH_STAT(theForm.getVH_STAT().trim());
		}
		obj.setVH_TYPE("2");
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List vsList = virtualService.queryForListByObj(obj);
		theForm.setResultList(vsList);
		return "list";

	}

	/**
	 * @Title:查询出所有虚拟机管理模块信息
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listVirtualInfoForReport() throws BaseException {
		if (theForm == null) {
			theForm = new VirtualForm();
		}
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		if (theForm.getVH_NAME() != null && !"".equals(theForm.getVH_NAME())) {
			obj.setVH_NAME(theForm.getVH_NAME().trim());
		}
		if (theForm.getVH_STAT() != null && !"".equals(theForm.getVH_STAT())) {
			obj.setVH_STAT(theForm.getVH_STAT().trim());
		}
		// obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List vsList = virtualService.queryForListByObj(obj);
		theForm.setResultList(vsList);
		return "listForReport";

	}

	/**
	 * @Title:增加虚拟机管理模块信息
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String addVirtualInfo() throws BaseException {
		if (theForm == null) {
			theForm = new VirtualForm();
		}
		return "add";
	}

	/**
	 * @Title:保存虚拟机管理模块请求
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String saveVirtualInfo() throws BaseException {
		if (theForm == null) {
			theForm = new VirtualForm();
		}
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		/*
		 * try { BeanUtils.copyProperties(obj,theForm); } catch
		 * (IllegalAccessException e) { e.printStackTrace(); } catch
		 * (InvocationTargetException e) { e.printStackTrace(); }
		 */
		obj.setVH_DESC(theForm.getVH_DESC());
		obj.setVH_ID_IBM(theForm.getVH_ID_IBM());
		obj.setVH_NAME(theForm.getVH_NAME());
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");

		int ret = 0;
		int result = 0;
		if (theForm.getFlag() == 0) {
			ret = virtualService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增功能信息");
		} else {
			ret = virtualService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改功能信息");
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "save";

	}

	/**
	 * @Title:修改虚拟机管理模块信息
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String modVirtualInfo() throws BaseException {
		if (theForm == null) {
			theForm = new VirtualForm();
		}
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		obj.setVH_ID_IBM(theForm.getVH_ID_IBM());
		TbCloud2VirtualInfoObj tempObj = virtualService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return "mod";
	}

	/**
	 * @Title:删除虚拟机管理模块请求
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String delVirtualInfo() throws BaseException {
		if (theForm == null) {
			theForm = new VirtualForm();
		}
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		int result = 0;
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		obj.setVH_ID_IBM(theForm.getVH_ID_IBM());
		int ret = virtualService.deleteByObj(obj);
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
		return "del";
	}

	/**
	 * @Title:调整大小模块
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public String adjustVirtualInfo() throws BaseException {
		if (theForm == null) {
			theForm = new VirtualForm();
		}
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		obj.setVH_ID_IBM(theForm.getVH_ID_IBM());
		TbCloud2VirtualInfoObj tempObj = virtualService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return "adjust";
	}

	/**
	 * @Title:保存调整大小到接口
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String saveAdjustVirtualInfo() throws BaseException {
		if (theForm == null) {
			theForm = new VirtualForm();
		}
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
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
		obj.setVH_ID_IBM(theForm.getVH_ID_IBM());
		ret = virtualService.adjustVirtualInfo(obj); // 预留接口
		operObj.setOPERTYPE(3);
		operObj.setMESSAGE("调整大小");
		/*
		 * if(ret>0){ result=1; } operObj.setRESULT(result);
		 * 
		 * @SuppressWarnings("unused") int retOper =
		 * operationService.insertByObj(operObj);// 写操作日志
		 */

		// 调接口
		return "saveAdjust";
	}

	/**
	 * @Title:移至项目
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String moveVirtualInfo() throws BaseException {
		if (theForm == null) {
			theForm = new VirtualForm();
		}
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		obj.setVH_ID_IBM(theForm.getVH_ID_IBM());
		obj.setVH_DESC(theForm.getVH_DESC());
		obj.setVH_NAME(theForm.getVH_NAME());
		TbCloud2VirtualInfoObj tempObj = virtualService.queryByObj(obj);
		TbCloud2ProjectInfoObj tbCloud2ProjectInfoObj = new TbCloud2ProjectInfoObj();
		List pList = tbProjectService.queryForListByProjectObj(tbCloud2ProjectInfoObj);
		theForm.setProjectList(pList);

		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return "move";
	}

	/**
	 * @Title:保存虚拟机管理模块请求
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String saveProjectIdInfo() throws BaseException {
		if (theForm == null) {
			theForm = new VirtualForm();
		}
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		/*
		 * try { BeanUtils.copyProperties(obj,theForm); } catch
		 * (IllegalAccessException e) { e.printStackTrace(); } catch
		 * (InvocationTargetException e) { e.printStackTrace(); }
		 */
		obj.setPROJECT_ID(theForm.getPROJECT_ID());
		obj.setVH_ID_IBM(theForm.getVH_ID_IBM());
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");
		int ret = 0;
		int result = 0;
		if (theForm.getFlag() == 0) {
			ret = virtualService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增功能信息");
		} else {
			ret = virtualService.updateByProjectIdObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改功能信息");
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "saveProjectId";
	}

	/**
	 * @Title:添加存储器
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public String addMemInfo() throws BaseException {
		// 加入ID和NAME的值
		if (theForm == null) {
			theForm = new VirtualForm();
		}
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		obj.setVH_ID_IBM(theForm.getVH_ID_IBM());
		TbCloud2VirtualInfoObj tempObj = virtualService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return "addMem";
	}

	/**
	 * @Title:保存添加的存储器
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public String saveMemInfo() throws BaseException {
		// 加入存储器名称和大小
		if (theForm == null) {
			theForm = new VirtualForm();
		}
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		obj.setVH_ID_IBM(theForm.getVH_ID_IBM());
		obj.setVH_STORAGE_NAME(theForm.getVH_STORAGE_NAME());
		obj.setVH_STORAGE_VALUE(theForm.getVH_STORAGE_VALUE());
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		int ret = 0;
		ret = virtualService.addMemInfo(obj); // 预留接口
		operObj.setOPERTYPE(3);
		operObj.setMESSAGE("添加存储");
		return "saveMem";
	}

	/**
	 * @Title:查看虚拟机详细信息
	 * @Copyright: Copyright (c) 20120215
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String checkVirtualInfo() throws BaseException {
		if (theForm == null) {
			theForm = new VirtualForm();
		}
		TbCloud2VirtualInfoObj obj1 = new TbCloud2VirtualInfoObj();
		String vhId = Struts2Utils.getRequest().getParameter("VH_ID");
		if (vhId != null && !"".equals(vhId)) {
			obj1.setVH_ID_IBM(vhId);
		}
		// form.reset(mapping, request);//清空ActionForm
		TbCloud2VirtualInfoObj obj = virtualService.queryVirtualServerByObj(obj1);// 调用netService的接口显示网络的下拉列表
		theForm.setVH_ID(obj.getVH_ID());
		theForm.setVH_CPU(obj.getVH_CPU());
		theForm.setVH_MAX_CPU(obj.getVH_MAX_CPU());
		theForm.setVH_MAX_MEM(obj.getVH_MAX_MEM());
		theForm.setVH_MAX_PROCESS_UNIT(obj.getVH_MAX_PROCESS_UNIT());
		theForm.setVH_MEM(obj.getVH_MEM());
		theForm.setVH_MIN_CPU(obj.getVH_MIN_CPU());
		theForm.setVH_MIN_MEM(obj.getVH_MIN_MEM());
		theForm.setVH_MIN_PROCESS_UNIT(obj.getVH_MIN_PROCESS_UNIT());
		theForm.setVH_DESC(obj.getVH_DESC());
		theForm.setVH_NAME(obj.getVH_NAME());
		theForm.setVH_PROCESS_UNIT(obj.getVH_PROCESS_UNIT());
		theForm.setVH_PROCESS_UNIT_MODE(obj.getVH_PROCESS_UNIT_MODE());
		theForm.setVH_CPU_MODE(obj.getVH_CPU_MODE());
		theForm.setVH_RUNTIME(obj.getVH_RUNTIME());
		theForm.setVH_COMPLETED(obj.getVH_COMPLETED());
		theForm.setVH_DEPLOYETIME(obj.getVH_DEPLOYETIME());
		theForm.setVH_STARTED(obj.getVH_STARTED());
		theForm.setVH_STAT(obj.getVH_STAT());
		theForm.setVH_LOG(obj.getVH_LOG());
		theForm.setVH_USER(obj.getVH_USER());
		theForm.setVH_PASS(obj.getVH_PASS());
		theForm.setVH_CPU_MODE(obj.getVH_CPU_MODE());
		theForm.setVH_PROCESS_UNIT_MODE(obj.getVH_PROCESS_UNIT_MODE());
		theForm.setVH_NETWORK(obj.getVH_NETWORK());
		theForm.setVH_IP(obj.getVH_IP());
		theForm.setVH_PROCESS_UNIT_Priority(obj.getVH_PROCESS_UNIT_Priority());
		String storage = obj.getVH_STORAGE();
		if (storage != null && !"".equals(storage)) {
			List<Tbcloud2VirtualStorageInfoObj> list = new ArrayList<Tbcloud2VirtualStorageInfoObj>();
			String nameValueStr = obj.getVH_STORAGE().substring(0,
					obj.getVH_STORAGE().lastIndexOf(","));
			String[] storageName_value = nameValueStr.split(",");
			for (int i = 0; i < storageName_value.length; i++) {
				String[] name_valueStr = storageName_value[i].split(":");
				String name = name_valueStr[0];
				String value = name_valueStr[1];
				Tbcloud2VirtualStorageInfoObj tObj = new Tbcloud2VirtualStorageInfoObj();
				tObj.setVH_STORAGE_NAME(name);
				tObj.setVH_STORAGE_VALUE(value);
				list.add(tObj);
			}
			theForm.setList(list);
		}
		return "check";
	}

	/**
	 * @Title:启动停止虚拟机
	 * @Copyright: Copyright (c) 20120215
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String StartAndStopVirtual() throws BaseException {
		if (theForm == null) {
			theForm = new VirtualForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("VH_ID_IBM");
		String param = request.getParameter("paran");
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		obj.setVH_ID_IBM(id);
		obj.setVH_STAT(param);
		int count = virtualService.StartAndStopByObj(obj);
		if (count != 0) {
			response.setContentType("text/html; charset=gb2312");
			// out = response.getWriter();
			// out.print(count);
			// out.close();
			PrintWriterOut.printWirter(response, count);
		}
		return "list";

		// return mapping.findForward("default");
	}

	/**
	 * @Title:定时获取虚拟机状态
	 * @Copyright: Copyright (c) 20120215
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String Virtual_Flag() throws BaseException, IOException {
		if (theForm == null) {
			theForm = new VirtualForm();
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = Struts2Utils.getRequest().getParameter("VH_ID_IBM");
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		obj.setVH_ID_IBM(id);
		ArrayList<TbCloud2VirtualInfoObj> startStopProcessList = (ArrayList<TbCloud2VirtualInfoObj>) virtualService
				.queryListIDByObj(obj);
		for (int i = 0; i < startStopProcessList.size(); i++) {
			TbCloud2VirtualInfoObj virtual = (TbCloud2VirtualInfoObj) startStopProcessList.get(i);
			String flag = virtual.getVH_STAT();
			String str = "";
			if (flag != null && !"".equals(flag)) {
				if (flag.equals("EXECUTING")) {
					virtual.setVH_STAT("<img src=\"./sxcloud/images/ajax-loader.gif\" width=\"15\" height=\"18\"/>正在部署");
				} else if (flag.equals("STOPPED")) {
					virtual.setVH_STAT("已停止");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"启动\" ";
					str += "onclick=\"StartAndStopVirtual(" + virtual.getVH_ID_IBM()
							+ ",'OK');return false;\" />";
					// obj.setSTART_STOP_FLAG_AN(str);
				} else if (flag.equals("DELETING")) {
					virtual.setVH_STAT("<img src=\"./sxcloud/images/ajax-loader.gif\" width=\"15\" height=\"18\"/>正在删除");
				} else if (flag.equals("OK")) {
					virtual.setVH_STAT("已启动");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"停止\" ";
					str += "onclick=\"StartAndStopVirtual(" + virtual.getVH_ID_IBM()
							+ ",'STOPPED');return false;\" />";
					// obj.setSTART_STOP_FLAG_AN(str);
				} else if (flag.equals("ATTEMPTED")) {
					virtual.setVH_STAT("已尝试");
				} else if (flag.equals("DRAFT")) {
					virtual.setVH_STAT("草稿");
				} else if (flag.equals("UNKNOWN")) {
					virtual.setVH_STAT("未知");
				} else if (flag.equals("FAILED")) {
					virtual.setVH_STAT("失败");
				} else if (flag.equals("ERROR")) {
					virtual.setVH_STAT("错误");
				} else if (flag.equals("CANCELED")) {
					virtual.setVH_STAT("已放弃");
				} else if (flag.equals("BANNED")) {
					virtual.setVH_STAT("禁止");
				}
			}
			virtual.setSTART_STOP_FLAG_NAME(str);
			startStopProcessList.set(i, virtual);
		}

		// 存入json
		response.setContentType("text/html; charset=utf-8");
		JSONArray ja = new JSONArray();
		ja = JSONArray.fromObject(startStopProcessList);
		// PrintWriter out = response.getWriter();
		// out.print(ja.toString());
		// out.close();
		PrintWriterOut.printWirter(response, ja.toString());
		return null;

	}

	/**
	 * @Title:同步虚拟机数据
	 * @Copyright: Copyright (c) 20120411
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String synchVirtualInfo() throws BaseException {
		if (theForm == null) {
			theForm = new VirtualForm();
		}
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		String vh_id_ibm = (String) Struts2Utils.getRequest().getParameter("id");
		if (vh_id_ibm != null && !"".equals(vh_id_ibm)) {
			obj.setVH_ID_IBM(vh_id_ibm);
			TbCloud2VirtualInfoObj tempObj = new TbCloud2VirtualInfoObj();
			tempObj = virtualService.queryByObj(obj);
			if (tempObj != null) {
				TbCloud2VirtualPoolObj poolObj = new TbCloud2VirtualPoolObj();
				poolObj.setEq_id(tempObj.getEQ_ID());
				poolObj.setVH_ID(tempObj.getVH_ID());
				poolObj.setVH_NAME(tempObj.getVH_NAME());
				poolObj.setVH_IP(tempObj.getVH_IP());
				poolObj.setVH_TYPE("0");
				virtualPoolService.insertByObjForSyn(poolObj);

			}
		}
		// return null;
		return "synch";
	}

	/**
	 * @Title:检查虚拟机数据是否同步
	 * @Copyright: Copyright (c) 20120411
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String checkVirtualIfSynch() throws BaseException {
		if (theForm == null) {
			theForm = new VirtualForm();
		}
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		String vh_id_ibm = (String) Struts2Utils.getRequest().getParameter("id");
		if (vh_id_ibm != null && !"".equals(vh_id_ibm)) {
			obj.setVH_ID_IBM(vh_id_ibm);
			TbCloud2VirtualInfoObj tempObj = new TbCloud2VirtualInfoObj();
			tempObj = virtualService.queryByObj(obj);
			if (tempObj != null) {
				TbCloud2VirtualPoolObj poolObj = new TbCloud2VirtualPoolObj();
				// poolObj.setVH_ID(tempObj.getVH_ID());
				poolObj.setVH_IP(tempObj.getVH_IP());
				List<String> jsonArr = new ArrayList<String>();
				JSONArray json = new JSONArray();
				List list = virtualPoolService.queryForListByObj(poolObj);
				if (list != null && list.size() > 0) {
					jsonArr.add("NO");
				} else {
					jsonArr.add("YES");
				}
				try {
					json = JSONArray.fromObject(jsonArr);
					Struts2Utils.getResponse().getWriter().print(json.toString());
				} catch (IOException e) {
					e.printStackTrace();
					LogHelper.debug("VirtualAction.checkVirtualIfSynch():" + e.getMessage()
							+ getClass().getName());
				}
			}
		}
		return null;
	}

	private TbProjectService tbProjectService;
	private VirtualService virtualService;
	private NetService netService;
	private VirtualPoolService virtualPoolService;

	public void setVirtualPoolService(VirtualPoolService virtualPoolService) {
		this.virtualPoolService = virtualPoolService;
	}

	public void setNetService(NetService netService) {
		this.netService = netService;
	}

	public void setVirtualService(VirtualService virtualService) {
		this.virtualService = virtualService;
	}

	public void setTbProjectService(TbProjectService tbProjectService) {
		this.tbProjectService = tbProjectService;
	}

}
