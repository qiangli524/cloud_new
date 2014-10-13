package com.sitech.basd.sxcloud.workflow.web.resworkflow.action;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sitech.basd.sxcloud.cloud.domain.templet.TempletObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;
import com.sitech.basd.sxcloud.cloud.service.templet.TempletService;
import com.sitech.basd.sxcloud.cloud.service.virtual.VirtualService;
import com.sitech.basd.sxcloud.rsmu.config.Constant;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.OrderInfoObj;
import com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.TaskInfoObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.DisposeManObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowNodeDetailsObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowTaskObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.ProcessInfoObj;
import com.sitech.basd.sxcloud.workflow.domain.resourceorder.ResourceOrderInfoObj;
import com.sitech.basd.sxcloud.workflow.domain.resourceorder.VMHost;
import com.sitech.basd.sxcloud.workflow.domain.resourceorder.WorkFlowConstant;
import com.sitech.basd.sxcloud.workflow.domain.templet.TempletInfoObj;
import com.sitech.basd.sxcloud.workflow.service.engine.WorkflowEngineService;
import com.sitech.basd.sxcloud.workflow.service.resworkflow.ResourceWorkflowService;
import com.sitech.basd.sxcloud.workflow.service.templet.TempletInfoService;
import com.sitech.basd.sxcloud.workflow.web.resworkflow.form.ResourceWorkflowForm;
import com.sitech.utils.servlet.PrintWriterOut;

public class ResourceWorkflowAction extends CRUDBaseAction {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private VMHost vmHost;

	public VMHost getVmHost() {
		return vmHost;
	}

	public void setVmHost(VMHost vmHost) {
		this.vmHost = vmHost;
	}

	/**
	 * 
	 * @Title: 新建需求工单
	 * @Copyright: Copyright (c) 2012-2
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String addOrderInfo() {
		// 调用工作流引擎 获得处理人列表
		if (theForm == null) {
			theForm = new ResourceWorkflowForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		// TbSysUserinfoObj tempObj = (TbSysUserinfoObj) request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		int processId = Constant.FLOW_ONLORDER_PROCESS_ID;
		boolean premettion = workflowEngineService.canCreateNewApplication(processId,
				session.get("account").toString());
		// 设置待处理人到 form数据
		if (premettion) {
			List typeList = templetService.queryResourceType();
			theForm.setTypeList(typeList);
			List virtualList = virtualService.queryForAllListByObj(null);
			theForm.setVirtualList(virtualList);
			return ADD;
		} else {
			// 跳转到出错页面
			LogHelper.info("用户：" + session.get("account").toString() + " 不具备工单：" + processId
					+ "申请权限");
			request.setAttribute("error", true);
			return "error";
		}

	}

	/**
	 * 
	 * @Title: 保存发布新建工单
	 * @Copyright: Copyright (c) 2012-3
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String saveOrderInfo() {
		int processId = Constant.FLOW_ONLORDER_PROCESS_ID;
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		ResourceOrderInfoObj orderInfoObj = new ResourceOrderInfoObj();
		// TbSysUserinfoObj tempObj = (TbSysUserinfoObj) request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		String account = session.get("account").toString();
		// 需求编号
		String requestNo = theForm.getNEED_NUMBERS();
		String COMMAND = theForm.getCOMMAND();

		if (theForm.getVH_ID_IBM() != null && !"".equals(theForm.getVH_ID_IBM())) {
			orderInfoObj.setWORKLOAD_ID(theForm.getVH_ID_IBM());
		}
		try {
			// 工单基本信息
			orderInfoObj.setNEED_NUMBERS(requestNo);
			orderInfoObj.setNEED_SPONSOR(account);
			orderInfoObj.setNEED_STATUS(theForm.getNEED_STATUS());
			orderInfoObj.setNEED_END(theForm.getEnd_time());
			orderInfoObj.setContent(theForm.getContent());
			orderInfoObj.setTYPE(theForm.getSELECT());
			int ret = 0;
			if (WorkFlowConstant.COMMAND_REPUBLISH.equals(COMMAND)
					|| WorkFlowConstant.COMMAND_BACKPUBLISH.equals(COMMAND)
					|| WorkFlowConstant.COMMAND_BACKSAVE.equals(COMMAND)) { // 被打回修改后发布
				orderInfoObj.setNEED_STATUS(String.valueOf(WorkFlowConstant.NEEDSTATUS_DOING));
				ret = resourceWorkflowService.alterOrderInfo(orderInfoObj);
			} else if (WorkFlowConstant.COMMAND_RESAVE.equals(COMMAND)) { // 草稿箱修改保存
				orderInfoObj.setNEED_STATUS(String.valueOf(WorkFlowConstant.NEEDSTATUS_DRAFT));
				ret = resourceWorkflowService.alterOrderInfo(orderInfoObj);
			} else {
				orderInfoObj.setNEED_STATUS(COMMAND);
				ret = resourceWorkflowService.addOrderInfo(orderInfoObj);
			}
			if (ret == -1) {
				return "error";
			}
			// 将修改后的模板信息插入数据库
			// TempletInfoObj tObj = new TempletInfoObj();
			// tObj = insertByTempletObj(theForm);
			// int result = templetInfoService.saveTempletInfo(tObj);
			// 当前环节编号、当前用户任务编号、审批意见
			String currTaskId = theForm.getTASKID(); // 获得当前用户任务编号
			List<DisposeManObj> returnList = null;
			if (WorkFlowConstant.COMMAND_SAVE.equals(COMMAND)) { // 保存新建工单
				// 调用工作流引擎 新建保存上线工单
				returnList = workflowEngineService.triggerSaveNewFlow(requestNo, processId, session
						.get("account").toString());
			} else if (WorkFlowConstant.COMMAND_PUBLISTH.equals(COMMAND)
					|| WorkFlowConstant.COMMAND_REPUBLISH.equals(COMMAND)) { // 新建发布工单
				// 或
				// 草稿箱发布上线工单
				returnList = workflowEngineService.triggerSubmitNewFlow(requestNo, processId,
						session.get("account").toString());

			} else if (WorkFlowConstant.COMMAND_BACKPUBLISH.equals(COMMAND)) { // 被打回工单修改后发布
				returnList = workflowEngineService.triggerSubmitFlow(Integer.parseInt(currTaskId),
						FlowConstant.FLOW_OPERATE_APPROVAL, "", null);
			} else if (WorkFlowConstant.COMMAND_RESAVE.equals(COMMAND)
					|| WorkFlowConstant.COMMAND_BACKSAVE.equals(COMMAND)) { // 草稿箱保存新建工单操作
				returnList = new ArrayList<DisposeManObj>();
			}
			response.setCharacterEncoding("UTF-8");
			if (null != returnList) {
				// 成功新建工单
				if (WorkFlowConstant.COMMAND_PUBLISTH.equals(COMMAND)
						|| WorkFlowConstant.COMMAND_REPUBLISH.equals(COMMAND)) {
					String nextDealMan = this.getNextDealManInfo(returnList);
					request.setAttribute("NextDealMan", nextDealMan);
					theForm.setFLOW_TYPE("FLOW_TYPE_HUNGLIST");// 向Form传值用来判断提交成功后显示下一个处理人界面时点确定后返回的页面
					return "next";
				}

			} else {
				// 失败新建工单
				PrintWriterOut.printWirter(response, WorkFlowConstant.FAILED);
				throw new Exception();
			}
		} catch (IOException e) {
			LogHelper.debug("ResourceWorkflowAction.saveOrderInfo()"
					+ "PrintWriterOut.printWirter():" + e.getMessage() + getClass().getName());
		} catch (Exception e) {
			LogHelper.debug("ResourceWorkflowAction.saveOrderInfo():" + e.getMessage()
					+ getClass().getName());
			return "error";
		}
		theForm.setNEED_NUMBERS("");
		if (WorkFlowConstant.COMMAND_BACKPUBLISH.equals(COMMAND)
				|| WorkFlowConstant.COMMAND_BACKSAVE.equals(COMMAND)) {
			return "back";
		} else if (WorkFlowConstant.COMMAND_RESAVE.equals(COMMAND)
				|| WorkFlowConstant.COMMAND_REPUBLISH.equals(COMMAND)
				|| WorkFlowConstant.COMMAND_SAVE.equals(COMMAND)) {
			return "draft";
		}
		return null;
	}

	/**
	 * 
	 * @Title: 新建工单唯一性判断
	 * @Copyright: Copyright (c) 2011-7-11
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String OrderUniqueJudgement() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String NEED_NUMBERS = request.getParameter("NEED_NUMBERS");
		ResourceOrderInfoObj orderInfoObj = new ResourceOrderInfoObj();
		orderInfoObj.setNEED_NUMBERS(NEED_NUMBERS);
		orderInfoObj.setNEED_STATUS(String.valueOf(-1));// 排除状态影响
		this.paginater.initPagination(request);
		List<?> list = resourceWorkflowService.queryOrderList(orderInfoObj);
		List<String> jsonArr = new ArrayList<String>();
		JSONArray json = new JSONArray();
		if (null != list && !list.isEmpty()) {
			jsonArr.add(WorkFlowConstant.NO);
		} else {
			jsonArr.add(WorkFlowConstant.YES);
		}
		json = JSONArray.fromObject(jsonArr);
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}

	/**
	 * 
	 * @Title: 获得草稿箱工单信息
	 * @Copyright: Copyright (c) 2011-7-12
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String getOrderDraftInfo() {
		if (theForm == null) {
			theForm = new ResourceWorkflowForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		ResourceOrderInfoObj orderInfoObj = new ResourceOrderInfoObj();
		if (theForm.getNEED_NUMBERS() != null && !"".equals(theForm.getNEED_NUMBERS())) {
			orderInfoObj.setNEED_NUMBERS(theForm.getNEED_NUMBERS());
		}
		// TbSysUserinfoObj tempObj = (TbSysUserinfoObj) request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		String account = session.get("account").toString();
		orderInfoObj.setNEED_END(theForm.getEnd_time());
		orderInfoObj.setNEED_SPONSOR(account);
		orderInfoObj.setNEED_STATUS(String.valueOf(WorkFlowConstant.NEEDSTATUS_DRAFT));
		orderInfoObj.setPagination(this.paginater.initPagination(request));
		List<ResourceOrderInfoObj> resultList = resourceWorkflowService
				.queryOrderList(orderInfoObj);
		theForm.setResultList(resultList);
		return "draftInfo";
	}

	/**
	 * 
	 * @Title: 删除所有与需求编号一致的信息
	 * @Copyright: Copyright (c) 2012-3
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String deleteOrderInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String requestNo = request.getParameter("NEED_NUMBERS");
		ResourceOrderInfoObj orderInfoObj = new ResourceOrderInfoObj();
		try {
			BeanUtils.copyProperties(orderInfoObj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int ret = resourceWorkflowService.deleteOrderInfo(requestNo);
		if (ret == 0) {
			int result = templetInfoService.deleteTempletInfo(requestNo);
		}
		return "del";
	}

	/**
	 * 
	 * @Title: 修改保存在草稿箱的工单
	 * @Copyright: Copyright (c) 2011-7-8
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String alterOrderInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		// TbSysUserinfoObj tempObj = (TbSysUserinfoObj) request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		String account = session.get("account").toString();
		String NEED_NUMBER = request.getParameter("NEED_NUMBERS");
		/*
		 * 从被打回工单取得的变量
		 */
		theForm.setNEED_NUMBERS(NEED_NUMBER);
		TempletInfoObj tObj = new TempletInfoObj();
		ResourceOrderInfoObj obj = new ResourceOrderInfoObj();
		obj.setNEED_NUMBERS(theForm.getNEED_NUMBERS());
		tObj.setNEED_NUMBERS(theForm.getNEED_NUMBERS());
		ResourceOrderInfoObj rObj = resourceWorkflowService.queryOrderInfo(obj);
		// TbSysUserinfoObj tempUserObj = (TbSysUserinfoObj)
		// request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		String rejectOrderFlag = request.getParameter("rejectOrderFlag");
		String taskId = request.getParameter("taskId");
		theForm.setStart_time(rObj.getNEED_START());
		theForm.setEnd_time(rObj.getNEED_END());
		theForm.setContent(rObj.getContent());
		List list = templetService.queryResourceType();
		theForm.setTypeList(list);
		List virtualList = virtualService.queryForAllListByObj(null);
		theForm.setVirtualList(virtualList);
		theForm.setSELECT(rObj.getTYPE());
		String[] chk = theForm.getChk();
		String requestNo = null;
		List<ResourceOrderInfoObj> orderInfoObjList = null;
		if ((null != chk && chk.length == 1)
				|| (null != theForm.getNEED_NUMBERS() && !"".equals(theForm.getNEED_NUMBERS()))) {

			requestNo = (null != NEED_NUMBER && !"".equals(NEED_NUMBER)) ? NEED_NUMBER : chk[0];
			ResourceOrderInfoObj orderInfoObj = new ResourceOrderInfoObj();
			if (null != rejectOrderFlag && "RejectOrder".equals(rejectOrderFlag)) { // 判断是否是从被打回的工单
				orderInfoObj.setNEED_STATUS(String.valueOf(WorkFlowConstant.NEEDSTATUS_DOING)); // 被打回设置状态为正在处理
				theForm.setNEED_STATUS("" + WorkFlowConstant.NEEDSTATUS_DOING);
				theForm.setTASKID(taskId);
				theForm.setCOMMAND(WorkFlowConstant.COMMAND_BACKPUBLISH); //
				// 设置为被打回提交命令
				// 获取当前任务详情
				FlowNodeDetailsObj flowNodeDetailsObj = workflowEngineService
						.getFlowNodeDetails(Integer.parseInt(taskId));
				// 被打回列表显示出 当前需求编号下的 所有审批信息
				List<FlowTaskObj> flowTaskList = flowNodeDetailsObj.getTASK_TRACK();
				if (null != flowTaskList && !flowTaskList.isEmpty()) {
					theForm.setFlowTaskList(flowTaskList); // 设置为当前需求编号下的所有审批信息
				}

			} else {
				theForm.setNEED_STATUS("" + WorkFlowConstant.NEEDSTATUS_DRAFT);
				theForm.setCOMMAND(WorkFlowConstant.COMMAND_REPUBLISH); // 设置为草稿箱提交命令
			}
			orderInfoObj.setNEED_NUMBERS(requestNo);
			orderInfoObj.setNEED_SPONSOR(account);
			// 新建需求表单信息
			orderInfoObjList = resourceWorkflowService.queryOrderList(orderInfoObj);
		}
		/*
		 * 设置页面基本信息显示数据
		 */
		ResourceOrderInfoObj orderInfoObj = orderInfoObjList.get(0);
		theForm.setNEED_NUMBERS(requestNo);
		theForm.setStart_time(orderInfoObj.getNEED_START());
		return "alter_draft";
	}

	/**
	 * @Title:查询待处理工单列表
	 * @Copyright: Copyright (c) 2012-03-02
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String listWaitResourceOrderInfo() {
		if (theForm == null) {
			theForm = new ResourceWorkflowForm();
			theForm.setFLOW_TYPE(FlowConstant.FLOW_TYPE_TODOLIST);
			FLOW_TYPE = FlowConstant.FLOW_TYPE_TODOLIST;
		} else {
			FLOW_TYPE = theForm.getFLOW_TYPE();
		}
		return "wait_list";
	}

	/**
	 * @Title:查询已处理工单列表
	 * @Copyright: Copyright (c) 2012-03-02
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String alreadyResourceOrder() {
		if (theForm == null) {
			theForm = new ResourceWorkflowForm();
		}
		theForm.setFLOW_TYPE(FlowConstant.FLOW_TYPE_ALREADYDO);
		FLOW_TYPE = FlowConstant.FLOW_TYPE_ALREADYDO;
		return "already_list";
	}

	/**
	 * @Title:查询被打回工单列表信息
	 * @Copyright: Copyright (c) 2012-03-02
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String rejectResourceOrder() {
		if (theForm == null) {
			theForm = new ResourceWorkflowForm();
		}
		theForm.setFLOW_TYPE(FlowConstant.FLOW_TYPE_PLAYEDBACK);
		FLOW_TYPE = FlowConstant.FLOW_TYPE_PLAYEDBACK;
		return "back_list";
	}

	/**
	 * @Title:查询资源工单的详细信息
	 * @Copyright: Copyright (c) 2012-03-05
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listDealResourceWorkOrder() {
		if (theForm == null) {
			theForm = new ResourceWorkflowForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		// if (theForm.getFLOW_TYPE() == null ||
		// "".equals(theForm.getFLOW_TYPE())) {
		// String FLOW_TYPE = (String) request.getParameter("FLOW_TYPE");
		// theForm.setFLOW_TYPE(FLOW_TYPE);
		// }
		// String FLOW_TYPE = theForm.getFLOW_TYPE();

		int processId = Constant.FLOW_ONLORDER_PROCESS_ID;
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// request.getSession().getAttribute(Constant.USER_SESSION_KEY);
		List<ProcessInfoObj> processInfoObjList = null;
		// if (FLOW_TYPE != null && !"".equals(FLOW_TYPE)) {
		processInfoObjList = workflowEngineService.getALLFlowList(processId,
				Constant.INTERFACE_USER_NAME, 3);
		// }
		// 根据需求编号查询对应的需求信息
		ResourceOrderInfoObj tempObj = new ResourceOrderInfoObj();
		List<ResourceOrderInfoObj> allOnlorderList = new ArrayList();
		if (processInfoObjList != null) {
			for (int i = 0; i < processInfoObjList.size(); i++) {
				ProcessInfoObj processInfoObj = processInfoObjList.get(i);
				String needNum = processInfoObj.getApplicationId();
				tempObj.setNEED_NUMBERS(needNum);
				tempObj.setNEED_STATUS("-1");
				if (theForm.getNEED_NUMBERS() != null && !"".equals(theForm.getNEED_NUMBERS())) {
					if (!needNum.equals(theForm.getNEED_NUMBERS())) {
						continue;
					}
				}
				if (theForm.getAPPLY_Type() != null && !"".equals(theForm.getAPPLY_Type())) {
					String tempApplyType = theForm.getAPPLY_Type().trim();
					tempObj.setAPPLY_TYPE(tempApplyType);
				}
				theForm.setStart_time(processInfoObj.getReceiveTime());
				List<ResourceOrderInfoObj> resultList = resourceWorkflowService
						.queryWorkOrderList(tempObj);
				if (resultList != null && resultList.size() == 1) {
					ResourceOrderInfoObj obj = resultList.get(0);
					obj.setTaskId(String.valueOf(processInfoObj.getTaskId()));
					obj.setProcessNode(processInfoObj.getProcessNode());
					obj.setDisposeMan(processInfoObj.getDisposeMan());
					allOnlorderList.add(obj);
				}

			}
			/** 分页功能 */
			ResourceOrderInfoObj pageObj = new ResourceOrderInfoObj();
			pageObj.setPagination(this.getPaginater().initPagination(request));
			if (pageObj.getPagination() != null) {
				pageObj.setFIRSTROWNUM(pageObj.getPagination().getFirstRownum());
				pageObj.setPAGESIZE(pageObj.getPagination().getPageSize());
				pageObj.getPagination().setTotalCount(allOnlorderList.size());
			}
			int pageSize = 0;

			if ((pageObj.getPAGESIZE() + pageObj.getFIRSTROWNUM()) < allOnlorderList.size()) {
				pageSize = pageObj.getPAGESIZE() + pageObj.getFIRSTROWNUM();
			} else {
				pageSize = allOnlorderList.size();
			}
			List<ResourceOrderInfoObj> rList = allOnlorderList.subList(pageObj.getFIRSTROWNUM(),
					pageSize);
			theForm.setNeedResultList(rList);
		}

		return "list_workorder";
	}

	/**
	 * @Title:查询资源工单的详细信息
	 * @Copyright: Copyright (c) 2012-03-05
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listDealResourceOrder() {
		if (theForm == null) {
			theForm = new ResourceWorkflowForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		if (theForm.getFLOW_TYPE() == null || "".equals(theForm.getFLOW_TYPE())) {
			String FLOW_TYPE = (String) request.getParameter("FLOW_TYPE");
			theForm.setFLOW_TYPE(FLOW_TYPE);
		}
		String FLOW_TYPE = theForm.getFLOW_TYPE();

		int processId = Constant.FLOW_ONLORDER_PROCESS_ID;
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		List<ProcessInfoObj> processInfoObjList = null;
		if (FLOW_TYPE != null && !"".equals(FLOW_TYPE)) {
			processInfoObjList = workflowEngineService.getFlowList(processId, session
					.get("account").toString(), FLOW_TYPE);
		}
		// 根据需求编号查询对应的需求信息
		ResourceOrderInfoObj tempObj = new ResourceOrderInfoObj();
		List<ResourceOrderInfoObj> allOnlorderList = new ArrayList();
		if (processInfoObjList != null) {
			for (int i = 0; i < processInfoObjList.size(); i++) {
				ProcessInfoObj processInfoObj = processInfoObjList.get(i);
				String needNum = processInfoObj.getApplicationId();
				tempObj.setNEED_NUMBERS(needNum);
				tempObj.setNEED_STATUS("-1");
				if (theForm.getNEED_NUMBERS() != null && !"".equals(theForm.getNEED_NUMBERS())) {
					if (!needNum.equals(theForm.getNEED_NUMBERS())) {
						continue;
					}
				}
				if (theForm.getNEED_SPONSOR() != null && !"".equals(theForm.getNEED_SPONSOR())) {
					tempObj.setNEED_SPONSOR(theForm.getNEED_SPONSOR());
				}
				if (theForm.getEnd_time() != null && !"".equals(theForm.getEnd_time())) {
					tempObj.setNEED_END(theForm.getEnd_time());
				}

				List<ResourceOrderInfoObj> resultList = resourceWorkflowService
						.queryOrderList(tempObj);
				if (resultList != null && resultList.size() == 1) {
					ResourceOrderInfoObj obj = resultList.get(0);

					obj.setTaskId(String.valueOf(processInfoObj.getTaskId()));
					obj.setProcessNode(processInfoObj.getProcessNode());
					obj.setDisposeMan(processInfoObj.getDisposeMan());
					allOnlorderList.add(obj);
				}

			}
			/** 分页功能 */
			ResourceOrderInfoObj pageObj = new ResourceOrderInfoObj();
			pageObj.setPagination(this.getPaginater().initPagination(request));
			if (pageObj.getPagination() != null) {
				pageObj.setFIRSTROWNUM(pageObj.getPagination().getFirstRownum());
				pageObj.setPAGESIZE(pageObj.getPagination().getPageSize());
				pageObj.getPagination().setTotalCount(allOnlorderList.size());
			}
			int pageSize = 0;

			if ((pageObj.getPAGESIZE() + pageObj.getFIRSTROWNUM()) < allOnlorderList.size()) {
				pageSize = pageObj.getPAGESIZE() + pageObj.getFIRSTROWNUM();
			} else {
				pageSize = allOnlorderList.size();
			}
			List<ResourceOrderInfoObj> rList = allOnlorderList.subList(pageObj.getFIRSTROWNUM(),
					pageSize);
			theForm.setNeedResultList(rList);
		}

		return "order_info";
	}

	/**
	 * 
	 * @Title:处理需求工单
	 * @Copyright: Copyright (c) 2012-3-6
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 * @throws NoSuchFieldException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	public String dealResourceDeploy() throws SecurityException, IllegalArgumentException,
			ClassNotFoundException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String needNum = request.getParameter("needNum");
		String taskId = request.getParameter("taskId");
		int processNode = Integer.parseInt((String) request.getParameter("processNode"));
		ResourceOrderInfoObj tempObj = new ResourceOrderInfoObj();

		tempObj.setNEED_NUMBERS(needNum);
		tempObj.setNEED_STATUS("-1");
		List<ResourceOrderInfoObj> resultList = resourceWorkflowService.queryOrderList(tempObj);

		if (resultList != null && resultList.size() == 1) {
			tempObj = resultList.get(0);
			theForm.setNEED_NUMBERS(tempObj.getNEED_NUMBERS());
			theForm.setContent(tempObj.getContent());
			theForm.setEnd_time(tempObj.getNEED_END());
			theForm.setTYPE(tempObj.getTYPE());
			// 将存储在数据库中的信息读取出来（在已处理任务的详细信息页面中显示的）
			TempletInfoObj temObj = templetInfoService.queryResourceListInfo(theForm.getTYPE());
			TempletInfoObj paramTemObj = new TempletInfoObj();
			paramTemObj.setNEED_NUMBERS(theForm.getNEED_NUMBERS());
			paramTemObj.setTYPE(theForm.getTYPE());
			theForm = workloadFlowTempletInfo(theForm, paramTemObj);
			theForm.setTYPE_NAME(temObj.getTYPE_NAME());
		}
		// 获取当前任务详情
		FlowNodeDetailsObj flowNodeDetailsObj = workflowEngineService.getFlowNodeDetails(Integer
				.parseInt(taskId));
		// 查询需求工单的任务轨迹
		List<FlowTaskObj> flowTaskInfoList = flowNodeDetailsObj.getTASK_TRACK();
		List<TaskInfoObj> taskInfoList = new ArrayList<TaskInfoObj>();
		// 组装任务轨迹对象
		for (int i = 0; i < flowTaskInfoList.size(); i++) {
			TaskInfoObj taskInfoObj = new TaskInfoObj();
			taskInfoObj.setTASK_ID(flowTaskInfoList.get(i).getTASK_ID());
			taskInfoObj.setDISPOSE_MAN(flowTaskInfoList.get(i).getDISPOSE_MAN());
			taskInfoObj.setREMARK(flowTaskInfoList.get(i).getTASK_REMARK());
			taskInfoObj.setNODE_ID(flowTaskInfoList.get(i).getNODE_ID());
			taskInfoObj.setRECEIVE_TIME(flowTaskInfoList.get(i).getRECEIVE_TIME());
			taskInfoObj.setDISPOSE_TIME(flowTaskInfoList.get(i).getDISPOSE_TIME());
			taskInfoObj.setNODE_NAME(flowTaskInfoList.get(i).getNODE_NAME());
			taskInfoObj.setNODE_REMARK(flowTaskInfoList.get(i).getNODE_REMARK());
			taskInfoList.add(taskInfoObj);
		}
		theForm.setTASK_OPERATE(flowNodeDetailsObj.getTASK_OPERATE());
		theForm.setNEED_NUMBERS(tempObj.getNEED_NUMBERS());
		theForm.setNEED_SPONSOR(tempObj.getNEED_SPONSOR());
		theForm.setTaskId(taskId);
		theForm.setProcessNode(processNode);
		theForm.setNEED_STATUS(tempObj.getNEED_STATUS());
		theForm.setTaskInfoResultList(taskInfoList);
		theForm.setNEED_REMARK(flowNodeDetailsObj.getNODE_REMARK());
		theForm.setResultList(resultList);
		return "deal";
	}

	/**
	 * 处理用户名为Interface——user——name的工单信息
	 * 
	 * dealWorkOrderResourceDeploy(这里用一句话描述这个方法的作用)
	 * 
	 * @param name
	 * 
	 * @param @return 设定文件
	 * 
	 * @return String DOM对象
	 * 
	 * @Exception 异常对象
	 * 
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public String dealWorkOrderResourceDeploy() throws SecurityException, IllegalArgumentException,
			ClassNotFoundException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String needNum = request.getParameter("needNum");
		vmHost = new VMHost();
		if (needNum != null && !needNum.equals("")) {
			vmHost.setWorkOrderID(needNum);
			vmHost = this.resourceWorkflowService.queryOrderListByVMHost(vmHost);
		} else {
			logger.debug("dealWorkOrderResourceDeploy() 过程中， 工单需求编号为空！");
		}
		return "dealWorkOrder";
	}

	/**
	 * 
	 * @Title:同意资源申请工单
	 * @Copyright: Copyright (c) 2012-3-5
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String agreeWaitDealResourceOrder() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		String needNum = theForm.getNEED_NUMBERS();
		String taskId = theForm.getTaskId();
		String remark = theForm.getINPUT_REMARK();
		int processNode = Integer.parseInt((String) request.getParameter("processNode"));
		List<DisposeManObj> returnList = workflowEngineService.triggerSubmitFlow(
				Integer.parseInt(taskId), FlowConstant.FLOW_OPERATE_APPROVAL, remark, null);
		// // 更新需求单的状态
		ResourceOrderInfoObj obj = new ResourceOrderInfoObj();
		obj.setNEED_NUMBERS(needNum);
		if (returnList != null && returnList.size() > 0) {
			obj.setNEED_STATUS(String.valueOf(WorkFlowConstant.NEEDSTATUS_DOING));
			resourceWorkflowService.updateByObj(obj);
		} else if (returnList != null && returnList.size() == 0) {
			obj.setNEED_STATUS(String.valueOf(WorkFlowConstant.NEEDSTATUS_FINSHED));
			resourceWorkflowService.updateByObj(obj);
		}
		String nextDealMan = this.getNextDealManInfo(returnList);
		request.setAttribute("NextDealMan", nextDealMan);
		return "agree";
	}

	/**
	 * 
	 * @Title:打回资源申请工单
	 * @Copyright: Copyright (c) 2012-03-06
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String fightbackWaitDealResourceOrder() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		String needNum = theForm.getNEED_NUMBERS();
		String taskId = theForm.getTaskId();
		String remark = theForm.getINPUT_REMARK();
		int processNode = Integer.parseInt((String) request.getParameter("processNode"));
		List<DisposeManObj> returnList = workflowEngineService.triggerSubmitFlow(
				Integer.parseInt(taskId), FlowConstant.FLOW_OPERATE_PLAYEDBACK, remark, null);
		// // 更新需求单的状态
		ResourceOrderInfoObj obj = new ResourceOrderInfoObj();
		obj.setNEED_NUMBERS(needNum);
		obj.setNEED_STATUS(String.valueOf(WorkFlowConstant.NEEDSTATUS_DOING));
		resourceWorkflowService.updateByObj(obj);
		String nextDealMan = this.getNextDealManInfo(returnList);
		request.setAttribute("NextDealMan", nextDealMan);
		return "reject";
	}

	/**
	 * 
	 * @Title:组装下一步执行人信息
	 * @Copyright: Copyright (c) 2012-03-02
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	private String getNextDealManInfo(List<DisposeManObj> returnList) {
		StringBuilder user = new StringBuilder();
		if (null != returnList) {
			user.append("该工单已经发送给： ");
			int count = 0;
			for (DisposeManObj disposeManObj : returnList) {
				if (count++ == 0) {
					user.append(disposeManObj.getUserId());
				} else {
					user.append("," + disposeManObj.getUserId());
				}
			}
			user.append(" 处理！");
		}
		return user.toString();
	}

	/**
	 * 
	 * @Title:编辑信息
	 * @Copyright: Copyright (c) 2012-03-02
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 * @throws NoSuchFieldException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */

	public String editResourceOrder() throws SecurityException, IllegalArgumentException,
			ClassNotFoundException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		if (theForm == null) {
			theForm = new ResourceWorkflowForm();
		}
		String NEED_NUMBERS = request.getParameter("NEED_NUMBERS");
		theForm.setNEED_NUMBERS(NEED_NUMBERS);
		String taskId = request.getParameter("taskId");
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		String needNum = theForm.getNEED_NUMBERS();

		String remark = theForm.getINPUT_REMARK();
		String tem_id = request.getParameter("TEM_ID");
		TempletObj teObj = new TempletObj();
		teObj.setTEM_ID(tem_id);
		ResourceOrderInfoObj tObj = new ResourceOrderInfoObj();
		tObj.setNEED_NUMBERS(needNum);
		List<ResourceOrderInfoObj> resultList = resourceWorkflowService.queryOrderList(tObj);
		ResourceOrderInfoObj tempObj = new ResourceOrderInfoObj();
		if (resultList != null && resultList.size() == 1) {
			tempObj = resultList.get(0);
		}
		List templetList = templetService.queryResourceTypeTemplet(tempObj.getTYPE());
		theForm.setTempletList(templetList);
		int processNode = Integer.parseInt((String) request.getParameter("processNode"));
		List<DisposeManObj> returnList = workflowEngineService.triggerSubmitFlow(
				Integer.parseInt(taskId), FlowConstant.FLOW_OPERATE_APPROVAL, remark, null);
		// 更新需求单的状态
		ResourceOrderInfoObj obj = new ResourceOrderInfoObj();
		OrderInfoObj orderObj = new OrderInfoObj();
		orderObj.setNEED_NUMBERS(needNum);
		obj.setNEED_NUMBERS(needNum);
		return "select";
	}

	/**
	 * @Title:动态设置Form中的显示
	 * @Copyright: Copyright (c) 20120314
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public ResourceWorkflowForm getForm(String classname, ResourceWorkflowForm theForm,
			HashMap map, HashMap kvmap) throws java.lang.ClassNotFoundException,
			java.lang.InstantiationException, java.lang.IllegalAccessException, SecurityException,
			NoSuchMethodException, IllegalArgumentException, InvocationTargetException,
			NoSuchFieldException {
		Class c = Class.forName(classname);
		Set<String> set = map.keySet();
		for (String key : set) {
			Object d1 = map.get(key);// 取得键值,注意是用来保存map中key对应的数值！
			Field f = c.getDeclaredField(key);// 返回一个 Field 对象，该对象反映此 Class

			// 对象所表示的类或接口的指定已声明字段。name 参数是一个
			// String，它指定所需字段的简称
			String str = key.charAt(0) + "";
			str = str.toUpperCase();
			String methodName = "set" + str + key.substring(1);// 先得到除了第一个字符以外的字符再加上就是方法名

			Method me = c.getMethod(methodName, f.getType());

			// 获取字段的类型。此时的key是类型的方法名，f是字段对象用getType取道其字段的类型。就是此方法的传入参数类型
			f.setAccessible(true);
			me.invoke(theForm, d1);// 利用类的一个方法来操纵obj（的一个对象），而d1是用来保存键对应的值，即就是传参，实参
		}

		Set<String> kvset = kvmap.keySet();

		for (String kvkey : kvset) {
			Object d2 = kvmap.get(kvkey);// 取得键值,注意是用来保存map中key对应的数值！
			Field f = c.getDeclaredField(kvkey);// 返回一个 Field 对象，该对象反映此 Class
			// 对象所表示的类或接口的指定已声明字段。name 参数是一个
			// String，它指定所需字段的简称
			String str = kvkey.charAt(0) + "";
			str = str.toUpperCase();
			String methodName = "set" + str + kvkey.substring(1) + "VALUE";// 先得到除了第一个字符以外的字符再加上就是方法名

			Method me = c.getMethod(methodName, f.getType());// 获取字段的类型。此时的key是类型的方法名，f是字段对象用getType取道其字段的类型。就是此方法的传入参数类型

			f.setAccessible(true);
			me.invoke(theForm, d2);// 利用类的一个方法来操纵obj（一个对象），而d1是用来保存键对应的值，即就是传参，实参
		}

		return theForm;

	}

	/**
	 * @Title:将修改后的模板信息写入数据库
	 * @Copyright: Copyright (c) 20120314
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public TempletInfoObj getObj(String classname1, String classname2, TempletInfoObj obj,
			ResourceWorkflowForm theForm, HashMap map) throws java.lang.ClassNotFoundException,
			java.lang.InstantiationException, java.lang.IllegalAccessException, SecurityException,
			NoSuchMethodException, IllegalArgumentException, InvocationTargetException,
			NoSuchFieldException {

		Class formClass = Class.forName(classname1);// 传入Form
		Class beanClass = Class.forName(classname2); // 传入TempletObj

		Set<String> set = map.keySet();
		int n = 1;
		Field[] fields = formClass.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) { // 得到Form的所有getter方法

			fields[i].setAccessible(true);
			String fieldname = fields[i].getName();
			String up = fieldname.charAt(0) + "";
			up = up.toUpperCase(); // 将第一个字母转化成大写
			String mName = "get" + up + fieldname.substring(1);
			String str = up + fieldname.substring(1);
			Method me1 = formClass.getMethod(mName, new Class[] {});
			for (String key : set) {
				if (str.equals(key + "VALUE")) {
					Object o = me1.invoke(theForm, new Object[] {});
					if (o != null) {
						o.toString();
						Field kvField = formClass.getDeclaredField(key);
						String kvMethod = "set" + "KV" + n;
						Method setkv = beanClass.getMethod(kvMethod, kvField.getType()); // 得到kv的方法
						setkv.invoke(obj, key + ":" + o); // 将字符串拼接后设置到kv中
						n++;
					}
				}
			}

		}

		return obj;

	}

	/**
	 * @Title:查看模板信息
	 * @Copyright: Copyright (c) 20120111
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 * @throws NoSuchFieldException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	public ResourceWorkflowForm templetInfo(ResourceWorkflowForm theForm, TempletObj obj)
			throws SecurityException, IllegalArgumentException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, NoSuchMethodException,
			InvocationTargetException, NoSuchFieldException {
		TempletObj tempObj = templetService.queryByObj(obj);
		theForm.setTEM_NAME(tempObj.getTEM_NAME());
		theForm.setTYPE(tempObj.getTYPE());
		theForm.setTYPE_NAME(tempObj.getTYPE_NAME());
		theForm.setTEM_DESC(tempObj.getTEM_DESC());
		HashMap map = templetService.queryType(tempObj);
		HashMap kvmap = templetService.getKvMap(tempObj);
		theForm = getForm(
				"com.sitech.basd.sxcloud.workflow.web.resworkflow.form.ResourceWorkflowForm",
				theForm, map, kvmap);

		return theForm;
	}

	/**
	 * @Title:查看模板信息
	 * @Copyright: Copyright (c) 20120111
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 * @throws NoSuchFieldException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	public ResourceWorkflowForm workloadFlowTempletInfo(ResourceWorkflowForm theForm,
			TempletInfoObj obj) throws SecurityException, IllegalArgumentException,
			ClassNotFoundException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
		TempletInfoObj teObj = templetInfoService.queryByObj(obj);
		if (teObj != null) {
			theForm.setTEM_NAME(teObj.getTEM_NAME());
			theForm.setTYPE(teObj.getTYPE());
			theForm.setTYPE_NAME(teObj.getTYPE_NAME());
			theForm.setTEM_DESC(teObj.getTEM_DESC());
			TempletObj tempObj = new TempletObj();
			tempObj.setTYPE(teObj.getTYPE());
			HashMap map = templetService.queryType(tempObj);
			HashMap kvmap = templetInfoService.getKvMap(teObj);
			theForm = getForm(
					"com.sitech.basd.sxcloud.workflow.web.resworkflow.form.ResourceWorkflowForm",
					theForm, map, kvmap);
		}
		return theForm;
	}

	/**
	 * @Title:查看修改后模板信息
	 * @Copyright: Copyright (c) 20120111
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 * @throws NoSuchFieldException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	public ResourceWorkflowForm lookTempletInfo(ResourceWorkflowForm theForm, TempletInfoObj obj)
			throws SecurityException, IllegalArgumentException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, NoSuchMethodException,
			InvocationTargetException, NoSuchFieldException {
		TempletInfoObj tempObj = templetInfoService.queryByObj(obj);
		theForm.setTEM_NAME(tempObj.getTEM_NAME());
		theForm.setTYPE(tempObj.getTYPE());
		theForm.setTYPE_NAME(tempObj.getTYPE_NAME());
		theForm.setTEM_DESC(tempObj.getTEM_DESC());
		TempletObj tObj = new TempletObj();
		tObj.setTEM_NAME(tempObj.getTEM_NAME());
		tObj.setTYPE(tempObj.getTYPE());
		tObj.setTEM_ID(tempObj.getTEM_ID());
		HashMap map = templetService.queryType(tObj);
		HashMap kvmap = templetService.getKvMap(tObj);
		theForm = getForm("com.sitech.basd.workflow.web.resworkflow.form.ResourceWorkflowForm",
				theForm, map, kvmap);

		return theForm;
	}

	/**
	 * @Title:根据不同的模板加载模板信息
	 * @Copyright: Copyright (c) 2012-03-10
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 * @throws NoSuchFieldException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	@SuppressWarnings("unchecked")
	public String selectTemplet() throws BaseException, SecurityException,
			IllegalArgumentException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoSuchMethodException, InvocationTargetException,
			NoSuchFieldException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		TempletObj temObj = new TempletObj();
		String tem_id = request.getParameter("SELECT");
		temObj.setTEM_ID(tem_id);
		TempletObj tempObj = templetService.queryByObj(temObj);
		theForm.setTEM_ID(tempObj.getTEM_ID());
		theForm.setTEM_NAME(tempObj.getTEM_NAME());
		theForm.setTYPE(tempObj.getTYPE());
		theForm.setTYPE_NAME(tempObj.getTYPE_NAME());
		theForm.setTEM_DESC(tempObj.getTEM_DESC());
		List templetList = templetService.queryResourceTypeTemplet(tempObj.getTYPE());
		theForm.setTempletList(templetList);
		theForm = templetInfo(theForm, temObj); // 调用方法动态显示页面的模版信息
		return "templet";
	}

	/**
	 * @Title:保存子页面编辑后的信息
	 * @Copyright: Copyright (c) 20120314
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 * @throws NoSuchFieldException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	public String saveSubPageMess() throws SecurityException, IllegalArgumentException,
			ClassNotFoundException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
		TempletInfoObj tObj = new TempletInfoObj();
		tObj.setTEM_NAME(theForm.getTYPE_NAME());
		tObj.setTYPE_DESC(theForm.getTEM_DESC());
		tObj.setTYPE_NAME(theForm.getTYPE_NAME());
		tObj.setNEED_NUMBERS(theForm.getNEED_NUMBERS());
		tObj.setTYPE(theForm.getTYPE());
		TempletObj obj = new TempletObj();
		obj.setTEM_ID(theForm.getTEM_ID());
		TempletObj tempObj = templetService.queryByObj(obj);
		HashMap map = templetService.queryType(tempObj);
		HashMap kvmap = templetService.getKvMap(tempObj);
		tObj = getObj("com.sitech.basd.sxcloud.workflow.web.resworkflow.form.ResourceWorkflowForm",
				"com.sitech.basd.sxcloud.workflow.domain.templet.TempletInfoObj", tObj, theForm,
				map);
		TempletInfoObj teObj = templetInfoService.queryByObj(tObj);
		if (teObj != null && !"".equals(teObj)) {
			int res = templetInfoService.deleteTempletInfo(teObj.getNEED_NUMBERS());
			int ret = templetInfoService.saveTempletInfo(tObj);
		} else {
			int ret = templetInfoService.saveTempletInfo(tObj);
		}
		theForm.setTYPE_NAME(teObj.getTYPE_NAME());
		theForm.setTEM_ID(teObj.getTEM_ID());
		theForm.setTEM_NAME(teObj.getTEM_NAME());
		// theForm.setTYPE(teObj.getTYPE());
		theForm.setTEM_DESC(teObj.getTEM_DESC());

		return null;
	}

	/**
	 * @Title:查看我的资源
	 * @Copyright: Copyright (c) 2012-03-20
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String myResourceOrder() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		if (theForm == null) {
			theForm = new ResourceWorkflowForm();
		}
		// TbSysUserinfoObj tempObj = (TbSysUserinfoObj) request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		ResourceOrderInfoObj obj = new ResourceOrderInfoObj();
		obj.setTYPE(theForm.getTYPE());
		int processId = Constant.FLOW_APPORDER_PROCESS_ID;
		boolean premettion = workflowEngineService.canCreateNewApplication(processId,
				session.get("account").toString());
		if (premettion) {
			List<ResourceOrderInfoObj> list = resourceWorkflowService.queryVirtualId();
			List<TbCloud2VirtualInfoObj> lst = new ArrayList<TbCloud2VirtualInfoObj>();
			for (ResourceOrderInfoObj r : list) {
				TbCloud2VirtualInfoObj tObj = new TbCloud2VirtualInfoObj();
				tObj.setVH_ID_IBM(r.getWORKLOAD_ID());
				// tObj = virtualService.queryVirtualList(tObj.getVH_ID_IBM())
				// .get(0);
				List<TbCloud2VirtualInfoObj> tempList = virtualService.queryVirtualList(tObj
						.getVH_ID_IBM());
				if (tempList != null && tempList.size() == 1) {
					tObj = tempList.get(0);
				}
				tObj.setVH_ID_IBM(r.getWORKLOAD_ID());
				tObj.setNEED_NUMBERS(r.getNEED_NUMBERS());
				FlowTaskObj flObj = new FlowTaskObj();
				flObj.setPROCESS_SERIALNUMBER(tObj.getNEED_NUMBERS());
				flObj.setPROCESS_ID(1);
				FlowTaskObj fObj = workflowEngineService.queryWorkflowInfo(flObj);
				tObj.setPROCESS_NODE("4");
				tObj.setTASK_ID(String.valueOf(fObj.getTASK_ID()));
				if (tObj.getVH_NAME() != null && !"".equals(tObj.getVH_NAME())) {
					lst.add(tObj);
				}
			}
			TbCloud2VirtualInfoObj pageObj = new TbCloud2VirtualInfoObj();
			pageObj.setPagination(this.paginater.initPagination(request));
			if (pageObj.getPagination() != null) {
				pageObj.setFIRSTROWNUM(pageObj.getPagination().getFirstRownum());
				pageObj.setPAGESIZE(pageObj.getPagination().getPageSize());
				pageObj.getPagination().setTotalCount(lst.size());
			}
			int pageSize = 0;
			if ((pageObj.getPAGESIZE() + pageObj.getFIRSTROWNUM()) < lst.size()) {
				pageSize = pageObj.getPAGESIZE() + pageObj.getFIRSTROWNUM();
			} else {
				pageSize = lst.size();
			}
			List<TbCloud2VirtualInfoObj> rList = lst.subList(pageObj.getFIRSTROWNUM(), pageSize);
			theForm.setNeedResultList(rList);
			theForm.setTbVirtualList(rList);
			theForm.setFLOW_TYPE(FlowConstant.FLOW_TYPE_ALREADYDO);
			return "myresource";
		} else {
			// 跳转到出错页面
			request.setAttribute("error", true);
			return "error";
		}
	}

	WorkflowEngineService workflowEngineService;
	ResourceWorkflowService resourceWorkflowService;
	TempletService templetService;
	TempletInfoService templetInfoService;
	private VirtualService virtualService;
	private ResourceWorkflowForm theForm;
	String FLOW_TYPE;

	public String getFLOW_TYPE() {
		return FLOW_TYPE;
	}

	public void setFLOW_TYPE(String flow_type) {
		FLOW_TYPE = flow_type;
	}

	public ResourceWorkflowForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ResourceWorkflowForm theForm) {
		this.theForm = theForm;
	}

	public void setVirtualService(VirtualService virtualService) {
		this.virtualService = virtualService;
	}

	public void setTempletInfoService(TempletInfoService templetInfoService) {
		this.templetInfoService = templetInfoService;
	}

	public void setWorkflowEngineService(WorkflowEngineService workflowEngineService) {
		this.workflowEngineService = workflowEngineService;
	}

	public void setResourceWorkflowService(ResourceWorkflowService resourceWorkflowService) {
		this.resourceWorkflowService = resourceWorkflowService;
	}

	public void setTempletService(TempletService templetService) {
		this.templetService = templetService;
	}

}
