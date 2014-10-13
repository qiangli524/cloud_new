package com.sitech.basd.sxcloud.workflow.web.appworkflow.action;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import com.sitech.basd.sxcloud.cloud.domain.templet.TempletObj;
import com.sitech.basd.sxcloud.cloud.service.templet.TempletService;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployExampleService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.service.system.UserInfoService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.sxcloud.util.exception.BaseException;
import com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.AppNeedObj;
import com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.OrderInfoObj;
import com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.TaskInfoObj;
import com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.WorkFlowConstant;
import com.sitech.basd.sxcloud.workflow.domain.engine.DisposeManObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowNodeDetailsObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowTaskObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.ProcessInfoObj;
import com.sitech.basd.sxcloud.workflow.service.appworkflow.AppWorkFlowService;
import com.sitech.basd.sxcloud.workflow.service.engine.WorkflowEngineService;
import com.sitech.basd.sxcloud.workflow.web.appworkflow.form.AppDeployWorkFlowForm;

public class AppDeployWorkFlowAction extends CRUDBaseAction {
	private AppDeployWorkFlowForm theForm;

	public AppDeployWorkFlowForm getTheForm() {
		return theForm;
	}

	public void setTheForm(AppDeployWorkFlowForm theForm) {
		this.theForm = theForm;
	}

	String FLOW_TYPE;

	public String getFLOW_TYPE() {
		return FLOW_TYPE;
	}

	public void setFLOW_TYPE(String flow_type) {
		FLOW_TYPE = flow_type;
	}

	/**
	 * @Title:新增应用部署申请
	 * @Copyright: Copyright (c) 2012-02-28
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String addAppDeployOrder() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		if (theForm == null) {
			theForm = new AppDeployWorkFlowForm();
		}
		// TbSysUserinfoObj tempObj = (TbSysUserinfoObj) request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		TbSysAppObj obj = new TbSysAppObj();
		obj.setDATAAUTHORITY((String) session.get("datau"));
		int processId = Constant.FLOW_APPORDER_PROCESS_ID;
		boolean premettion = workflowEngineService.canCreateNewApplication(processId,
				session.get("account").toString());
		// 设置待处理人到 form数据
		if (premettion) {
			List appList = appService.queryForListByObj(obj);
			theForm.setAppList(appList);
			return ADD;
		} else {
			// 跳转到出错页面
			// LogHelper.info("用户："+tempObj.getACCOUNT()+" 不具备工单："+processId+"
			// 申请权限");
			request.setAttribute("error", true);
			return "error";
		}
	}

	/**
	 * @Title:保存应用部署申请
	 * @Copyright: Copyright (c) 2012-03-01
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String saveAppDeployOrder() throws BaseException {
		if (theForm == null) {
			theForm = new AppDeployWorkFlowForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		AppNeedObj obj = new AppNeedObj();
		int processId = Constant.FLOW_APPORDER_PROCESS_ID;
		String requestNo = theForm.getNEED_NUMBERS();
		String COMMAND = theForm.getCOMMAND();
		// TbSysUserinfoObj tempObj = (TbSysUserinfoObj) request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		String account = session.get("account").toString();

		// 申请应用部署的工单信息

		obj.setNEED_NUMBERS(requestNo);
		obj.setNEED_SPONSOR(account);
		obj.setEND_DATE(theForm.getEND_DATE());
		obj.setNEED_CONT(theForm.getNEED_CONT());
		obj.setAPP_SIZE(theForm.getAPP_SIZE());
		String appId = theForm.getAPPID();
		obj.setAPPID(appId);
		TbSysAppObj appObj = new TbSysAppObj();
		appObj.setID(Integer.parseInt(appId));
		String appName = appService.queryByObj(appObj).getAPPNAME();
		obj.setAPPNAME(appName);

		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		int ret = 0;
		int result = 0;
		if (WorkFlowConstant.COMMAND_REPUBLISH.equals(COMMAND)
				|| WorkFlowConstant.COMMAND_BACKPUBLISH.equals(COMMAND)
				|| WorkFlowConstant.COMMAND_BACKSAVE.equals(COMMAND)) { // 修改后发布
			obj.setNEED_STATUS(WorkFlowConstant.NEEDSTATUS_DOING);
			ret = appWorkFlowService.alterAppNeedInfo(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改申请应用部署信息");
		} else if (WorkFlowConstant.COMMAND_RESAVE.equals(COMMAND)) { // 修改后保存
			obj.setNEED_STATUS(WorkFlowConstant.NEEDSTATUS_DRAFT);
			ret = appWorkFlowService.alterAppNeedInfo(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改申请应用部署信息");
		} else {
			obj.setNEED_STATUS(Integer.parseInt(COMMAND));
			ret = appWorkFlowService.addAppNeedInfo(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增申请应用部署信息");
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);

		try {
			List<DisposeManObj> returnList = null;
			// 页面跳转设置
			if (WorkFlowConstant.COMMAND_SAVE.equals(COMMAND)) { // 保存新建上线工单
				// 调用工作流引擎 新建保存应用部署工单
				returnList = workflowEngineService
						.triggerSaveNewFlow(requestNo, processId, account);
			} else if (WorkFlowConstant.COMMAND_PUBLISTH.equals(COMMAND)
					|| WorkFlowConstant.COMMAND_REPUBLISH.equals(COMMAND)) { // 新建发布上线工单
				// 或
				// 草稿箱发布上线工单
				returnList = workflowEngineService.triggerSubmitNewFlow(requestNo, processId,
						account);

			} else if (WorkFlowConstant.COMMAND_BACKPUBLISH.equals(COMMAND)) { // 被打会工单修改后发布

				// 当前环节编号、当前用户任务编号、审批意见
				String currTaskId = theForm.getTASKID(); // 获得当前用户任务编号
				// 调用工作流引擎发布被打回的工单
				returnList = workflowEngineService.triggerSubmitFlow(Integer.parseInt(currTaskId),
						FlowConstant.FLOW_OPERATE_APPROVAL, "", null);

			} else if (WorkFlowConstant.COMMAND_RESAVE.equals(COMMAND)) { // 草稿箱保存新建工单操作
				returnList = new ArrayList<DisposeManObj>();
			} else if (WorkFlowConstant.COMMAND_BACKSAVE.equals(COMMAND)) {
				returnList = new ArrayList<DisposeManObj>();
			}
			Struts2Utils.getResponse().setCharacterEncoding("UTF-8");
			if (null != returnList) {
				// 成功新建工单
				if (WorkFlowConstant.COMMAND_PUBLISTH.equals(COMMAND)
						|| WorkFlowConstant.COMMAND_REPUBLISH.equals(COMMAND)) {

					String nextDealMan = this.getNextDealManInfo(returnList);
					request.setAttribute("NextDealMan", nextDealMan);
					theForm.setFLOW_TYPE("FLOW_TYPE_HUNGLIST");// 向Form传值用来判断提交成功后显示下一个处理人界面时点确定后返回的页面
					return SUCCESS;
				}
			} else {
				// 失败新建工单
				Struts2Utils.getResponse().getWriter().print(WorkFlowConstant.FAILED);
				throw new Exception();
			}
		} catch (IOException e) {
			LogHelper
					.debug("AppDeployWorkFlowAction.saveAppDeployOrder() PrintWriterOut.printWirter();:"
							+ e.getMessage() + getClass().getName());
		} catch (Exception e) {
			deleteAppNeedInfo(requestNo); // 删除数据
			LogHelper.debug("AppDeployWorkFlowAction.saveAppDeployOrder():" + e.getMessage()
					+ getClass().getName());
			return "wrong";
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
	 * @Title:查询应用部署草稿
	 * @Copyright: Copyright (c) 2012-03-02
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listDraftAppDeploy() throws BaseException {
		if (theForm == null) {
			theForm = new AppDeployWorkFlowForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		AppNeedObj obj = new AppNeedObj();
		if (theForm.getNEED_NUMBERS() != null && !"".equals(theForm.getNEED_NUMBERS())) {
			obj.setNEED_NUMBERS(theForm.getNEED_NUMBERS());
		}
		// TbSysUserinfoObj tempObj = (TbSysUserinfoObj) request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		String account = session.get("account").toString();
		obj.setNEED_SPONSOR(account);
		obj.setNEED_NUMBERS(theForm.getNEED_NUMBERS());
		obj.setNEED_STATUS(WorkFlowConstant.NEEDSTATUS_DRAFT);
		obj.setSTART_DATE(theForm.getSTART_DATE());
		obj.setPagination(this.paginater.initPagination(request));
		List<AppNeedObj> resultList = appWorkFlowService.queryAppNeedList(obj);
		theForm.setResultList(resultList);
		return "listDraft";

	}

	/**
	 * @Title:修改保存在草稿箱中的应用部署申请
	 * @Copyright: Copyright (c) 2012-03-07
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String alterAppOrder() throws BaseException {
		if (theForm == null) {
			theForm = new AppDeployWorkFlowForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		// TbSysUserinfoObj userObj = (TbSysUserinfoObj) request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		TbSysAppObj appAbj = new TbSysAppObj();
		appAbj.setDATAAUTHORITY((String) session.get("datau"));
		// int processId = Constant.FLOW_APPORDER_PROCESS_ID;
		AppNeedObj obj = new AppNeedObj();
		obj.setNEED_NUMBERS(theForm.getNEED_NUMBERS());
		AppNeedObj tempOrderObj = appWorkFlowService.queryAppOrderInfo(obj);
		// TbSysUserinfoObj tempUserObj = (TbSysUserinfoObj)
		// request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		// String account = tempUserObj.getACCOUNT();
		/*
		 * 从被打回取得的变量
		 */
		String rejectOrderFlag = request.getParameter("rejectOrderFlag");
		String NEED_NUMBERS = request.getParameter("NEED_NUMBERS");
		String taskId = request.getParameter("taskId");

		if (null != rejectOrderFlag && "RejectOrder".equals(rejectOrderFlag)) { // 判断是否是从被打回的工单
			theForm.setNEED_NUMBERS(NEED_NUMBERS);
			AppNeedObj tempAppObj = new AppNeedObj();
			tempAppObj.setNEED_NUMBERS(theForm.getNEED_NUMBERS());
			tempAppObj.setNEED_STATUS(WorkFlowConstant.NEEDSTATUS_DOING); // 被打回设置状态为正在处理
			/*
			 * 打回后查询相应的申请信息并显示在页面
			 */
			AppNeedObj tempObj2 = appWorkFlowService.queryAppOrderInfo(tempAppObj);
			theForm.setEND_DATE(tempObj2.getEND_DATE());
			theForm.setAPP_SIZE(tempObj2.getAPP_SIZE());
			theForm.setNEED_CONT(tempObj2.getNEED_CONT());
			List appList = appService.queryForListByObj(appAbj);
			theForm.setAppList(appList);
			theForm.setAPPNAME(tempObj2.getAPPNAME());
			theForm.setNEED_STATUS(WorkFlowConstant.NEEDSTATUS_DOING);
			theForm.setTASKID(taskId);
			theForm.setCOMMAND(WorkFlowConstant.COMMAND_BACKPUBLISH);
			// 获取当前任务详情
			FlowNodeDetailsObj flowNodeDetailsObj = workflowEngineService
					.getFlowNodeDetails(Integer.parseInt(taskId));
			// 被打回列表显示出 当前需求编号下的 所有审批信息
			List<FlowTaskObj> flowTaskList = flowNodeDetailsObj.getTASK_TRACK();
			if (null != flowTaskList && !flowTaskList.isEmpty()) {
				theForm.setFlowTaskList(flowTaskList); // 设置为当前需求编号下的所有审批信息
			}

		} else {
			theForm.setNEED_STATUS(WorkFlowConstant.NEEDSTATUS_DRAFT);
			theForm.setCOMMAND(WorkFlowConstant.COMMAND_REPUBLISH); // 设置为草稿箱提交命令
			theForm.setEND_DATE(tempOrderObj.getEND_DATE());
			theForm.setAPP_SIZE(tempOrderObj.getAPP_SIZE());
			List appList = appService.queryForListByObj(appAbj);
			theForm.setAppList(appList);
			theForm.setNEED_CONT(tempOrderObj.getNEED_CONT());
		}
		return "alter";

	}

	/**
	 * 
	 * @Title: 删除应用部署工单与信息
	 * @Copyright: Copyright (c) 2012-03-07
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String deleteAppOrder() throws BaseException {
		if (theForm == null) {
			theForm = new AppDeployWorkFlowForm();
		}
		AppNeedObj obj = new AppNeedObj();
		OrderInfoObj infoObj = new OrderInfoObj();
		String requestNo = theForm.getNEED_NUMBERS();
		obj.setNEED_NUMBERS(theForm.getNEED_NUMBERS());
		infoObj.setNEED_NUMBERS(theForm.getNEED_NUMBERS());
		OrderInfoObj tempObj = appWorkFlowService.queryAppInfo(infoObj);
		AppNeedObj tempOrderObj = appWorkFlowService.queryAppOrderInfo(obj);
		int result = 0;
		int ret = appWorkFlowService.deleteAppNeedInfo(requestNo);
		int ret2 = appWorkFlowService.deleteAppNeedOrder(requestNo);
		if (ret > 0 && ret2 > 0) {
			result = 1;
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除应用部署申请信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return null;
	}

	/**
	 * 
	 * @Title: 删除所有与 需求编号一致的信息
	 * @Copyright: Copyright (c) 2012-03-02
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	private void deleteAppNeedInfo(String requestNo) {
		appWorkFlowService.deleteAppNeedInfo(requestNo);
		appWorkFlowService.deleteAppNeedOrder(requestNo);
	}

	/**
	 * @Title:查询待处理应用部署工单信息
	 * @Copyright: Copyright (c) 2012-03-02
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listWaitAppDeploy() {
		if (theForm == null) {
			theForm = new AppDeployWorkFlowForm();
		}
		theForm.setFLOW_TYPE(FlowConstant.FLOW_TYPE_TODOLIST);
		FLOW_TYPE = FlowConstant.FLOW_TYPE_TODOLIST;

		return "listWait";
	}

	/**
	 * @Title:查询申请应用部署信息列表
	 * @Copyright: Copyright (c) 2012-03-05
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listDealOrder() {
		if (theForm == null) {
			theForm = new AppDeployWorkFlowForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		/** huojla修改 */
		if (theForm.getFLOW_TYPE() == null || "".equals(theForm.getFLOW_TYPE())) {
			String FLOW_TYPE = (String) request.getParameter("FLOW_TYPE");
			theForm.setFLOW_TYPE(FLOW_TYPE);
		}
		String FLOW_TYPE = theForm.getFLOW_TYPE();
		// String FLOW_TYPE = (String)request.getParameter("FLOW_TYPE");
		// theForm.setFLOW_TYPE(FLOW_TYPE);
		/** huojla修改 */
		// String FLOW_TYPE = theForm.getFLOW_TYPE();
		int processId = Constant.FLOW_APPORDER_PROCESS_ID;
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		List<ProcessInfoObj> processInfoObjList = null;
		if (FLOW_TYPE != null && !"".equals(FLOW_TYPE)) {
			processInfoObjList = workflowEngineService.getFlowList(processId, session
					.get("account").toString(), FLOW_TYPE);
		}
		// 根据需求编号查询对应的需求信息
		AppNeedObj tempObj = new AppNeedObj();
		List<AppNeedObj> allOnlorderList = new ArrayList();
		if (processInfoObjList != null) {
			for (int i = 0; i < processInfoObjList.size(); i++) {
				ProcessInfoObj processInfoObj = processInfoObjList.get(i);
				String needNum = processInfoObj.getApplicationId();
				tempObj.setNEED_NUMBERS(needNum);
				tempObj.setNEED_STATUS(-1);
				if (theForm.getNEED_NUMBERS() != null && !"".equals(theForm.getNEED_NUMBERS())) {
					if (!needNum.equals(theForm.getNEED_NUMBERS())) {
						continue;
					}
				}
				if (theForm.getNEED_SPONSOR() != null && !"".equals(theForm.getNEED_SPONSOR())) {
					tempObj.setNEED_SPONSOR(theForm.getNEED_SPONSOR());
				}
				List<AppNeedObj> resultList = appWorkFlowService.queryAppNeedList(tempObj);
				if (resultList != null && resultList.size() == 1) {
					AppNeedObj obj = resultList.get(0);
					obj.setTaskId(String.valueOf(processInfoObj.getTaskId()));
					obj.setProcessNode(processInfoObj.getProcessNode());
					obj.setDisposeMan(processInfoObj.getDisposeMan());
					allOnlorderList.add(obj);
				}
			}

			/** 分页功能 */
			AppNeedObj pageObj = new AppNeedObj();
			pageObj.setPagination(this.paginater.initPagination(request));
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
			List<AppNeedObj> rList = allOnlorderList.subList(pageObj.getFIRSTROWNUM(), pageSize);
			theForm.setResultList(rList);
		}
		// theForm.setResultList(allOnlorderList);
		return "listDealOrder";
	}

	/**
	 * 
	 * @Title:进入处理待办的申请工单信息页面
	 * @Copyright: Copyright (c) 2012-3-6
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String dealAppDeploy() throws BaseException {
		if (theForm == null) {
			theForm = new AppDeployWorkFlowForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String needNum = request.getParameter("needNum");
		String taskId = request.getParameter("taskId");
		int processNode = Integer.parseInt((String) request.getParameter("processNode"));
		AppNeedObj tempObj = new AppNeedObj();
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		tempObj.setNEED_NUMBERS(needNum);
		tempObj.setNEED_STATUS(-1);
		List<AppNeedObj> resultList = appWorkFlowService.queryAppNeedList(tempObj);
		if (resultList != null && resultList.size() == 1) {
			tempObj = resultList.get(0);
		}
		theForm.setNEED_NUMBERS(tempObj.getNEED_NUMBERS()); // 在页面显示的详细信息
		theForm.setNEED_SPONSOR(tempObj.getNEED_SPONSOR());
		theForm.setTaskId(taskId);
		theForm.setProcessNode(processNode);
		// theForm.setNODE_TYPE(node_type);
		theForm.setNEED_STATUS(tempObj.getNEED_STATUS());
		theForm.setSTART_DATE(tempObj.getSTART_DATE());
		theForm.setEND_DATE(tempObj.getEND_DATE());
		theForm.setAPP_SIZE(tempObj.getAPP_SIZE());
		theForm.setAPPNAME(tempObj.getAPPNAME());
		theForm.setDEFEND_FLAG(tempObj.getDefendflag());
		theForm.setNEED_CONT(tempObj.getNEED_CONT());
		if (tempObj.getNEED_SPONSOR().equals(session.get("account").toString())) {
			request.setAttribute("isSponsor", true);
		} else {
			request.setAttribute("isSponsor", false);
		}
		// 获取当前任务详情
		FlowNodeDetailsObj flowNodeDetailsObj = workflowEngineService.getFlowNodeDetails(Integer
				.parseInt(taskId));
		// 查询需求工单的任务轨迹
		List<FlowTaskObj> flowTaskInfoList = flowNodeDetailsObj.getTASK_TRACK();
		List<TaskInfoObj> taskInfoList = new ArrayList();
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

		theForm.setTaskInfoResultList(taskInfoList);

		if (theForm.getFLOW_TYPE().equals(FlowConstant.FLOW_TYPE_ALREADYDO)) {
			OrderInfoObj orObj = new OrderInfoObj();// 用来查询保存的应用部署的端口，编号等信息
			orObj.setNEED_NUMBERS(needNum);
			List<OrderInfoObj> list = appWorkFlowService.queryAppInfoList(orObj);
			if (list != null && list.size() == 1) {
				orObj = list.get(0);
				TempletObj temObj = new TempletObj();
				temObj.setTYPE(orObj.getTYPE()); // 得到模板的类型
				HashMap map = templetService.queryType(temObj); // 得到模板类型的map
				HashMap kvmap = appWorkFlowService.getKvMap(orObj);
				theForm = getForm(
						"com.sitech.basd.sxcloud.workflow.web.appworkflow.form.AppDeployWorkFlowForm",
						theForm, map, kvmap);
				theForm.setDEFEND_DIR(tempObj.getDEFEND_DIR());
				request.setAttribute("getip", tempObj.getAPP_IP());
				request.setAttribute("getvlan", tempObj.getVLANIP());
			}
		}
		theForm.setNEED_REMARK(flowNodeDetailsObj.getNODE_REMARK());
		return "dealAppDeploy";
	}

	/**
	 * @Title:动态设置Form中的显示
	 * @Copyright: Copyright (c) 20120314
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public AppDeployWorkFlowForm getForm(String classname, AppDeployWorkFlowForm theForm,
			HashMap map, HashMap kvmap) {
		Class c = null;
		try {
			c = Class.forName(classname);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Set<String> set = map.keySet();

		for (String key : set) {
			Object d1 = map.get(key);// 取得键值,注意是用来保存map中key对应的数值！
			Field f = null;
			try {
				f = c.getDeclaredField(key);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}// 返回一个 Field 对象，该对象反映此
				// Class

			// 对象所表示的类或接口的指定已声明字段。name 参数是一个
			// String，它指定所需字段的简称
			String str = key.charAt(0) + "";
			str = str.toUpperCase();
			String methodName = "set" + str + key.substring(1);// 先得到除了第一个字符以外的字符再加上就是方法名

			Method me = null;
			try {
				me = c.getMethod(methodName, f.getType());
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}

			// 获取字段的类型。此时的key是类型的方法名，f是字段对象用getType取道其字段的类型。就是此方法的传入参数类型
			f.setAccessible(true);
			try {
				me.invoke(theForm, d1);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}// 利用类的一个方法来操纵obj（的一个对象），而d1是用来保存键对应的值，即就是传参，实参
		}

		Set<String> kvset = kvmap.keySet();

		for (String kvkey : kvset) {
			Object d2 = kvmap.get(kvkey);// 取得键值,注意是用来保存map中key对应的数值！
			Field f = null;
			try {
				f = c.getDeclaredField(kvkey);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}// 返回一个 Field 对象，该对象反映此
				// Class
				// 对象所表示的类或接口的指定已声明字段。name 参数是一个
				// String，它指定所需字段的简称
			String str = kvkey.charAt(0) + "";
			str = str.toUpperCase();
			String methodName = "set" + str + kvkey.substring(1) + "VALUE";// 先得到除了第一个字符以外的字符再加上就是方法名

			Method me = null;
			try {
				me = c.getMethod(methodName, f.getType());
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}// 获取字段的类型。此时的key是类型的方法名，f是字段对象用getType取道其字段的类型。就是此方法的传入参数类型

			f.setAccessible(true);
			try {
				me.invoke(theForm, d2);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}// 利用类的一个方法来操纵obj（一个对象），而d1是用来保存键对应的值，即就是传参，实参
		}

		return theForm;

	}

	/**
	 * 
	 * @Title:同意提交应用部署工单
	 * @Copyright: Copyright (c) 2012-3-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String agreeWaitDealOrder() {
		HttpServletRequest request = Struts2Utils.getRequest();
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		if (theForm == null) {
			theForm = new AppDeployWorkFlowForm();
		}
		String needNum = theForm.getNEED_NUMBERS();
		String taskId = theForm.getTaskId();
		String remark = theForm.getINPUT_REMARK();
		// int processNode = Integer.parseInt((String) request
		// .getParameter("processNode"));
		List<DisposeManObj> returnList = workflowEngineService.triggerSubmitFlow(
				Integer.parseInt(taskId), FlowConstant.FLOW_OPERATE_APPROVAL, remark, null);
		// 更新需求单的状态
		AppNeedObj obj = new AppNeedObj();
		obj.setNEED_NUMBERS(needNum);
		obj.setDefendflag(theForm.getDEFEND_FLAG());
		obj.setDEFEND_DIR(theForm.getDEFEND_DIR());
		if (returnList != null && returnList.size() > 0) {
			obj.setNEED_STATUS(WorkFlowConstant.NEEDSTATUS_DOING);
			appWorkFlowService.updateByObj(obj);
			appWorkFlowService.updateDefend(obj); // 将防篡改等信息存入表中
		} else if (returnList != null && returnList.size() == 0) {
			obj.setNEED_STATUS(WorkFlowConstant.NEEDSTATUS_FINSHED);
			appWorkFlowService.updateByObj(obj);
		}
		String nextDealMan = this.getNextDealManInfo(returnList);
		request.setAttribute("NextDealMan", nextDealMan);
		return "agree";
	}

	/**
	 * 
	 * @Title:组装下一步执行人信息
	 * @Copyright: Copyright (c) 2012-03-02
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	private String getNextDealManInfo(List<DisposeManObj> returnList) {
		StringBuilder user = new StringBuilder();
		if (null != returnList) {
			user.append("该申请工单已经发送给： ");
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
	 * @Title: 编辑信息时加载模板和选择IP
	 * @Copyright: Copyright (c) 2012-03-09
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public String editAppOrder() {
		HttpServletRequest request = Struts2Utils.getRequest();
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		if (theForm == null) {
			theForm = new AppDeployWorkFlowForm();
		}
		TempletObj temObj = new TempletObj();
		List templetList = templetService.queryAppForListByObj(temObj);
		String need_numbers = (String) Struts2Utils.getRequest().getParameter("NEED_NUMBERS");
		theForm.setNEED_NUMBERS(need_numbers);
		theForm.setTempletList(templetList);

		return "edit";
	}

	/**
	 * @Title:保存应用和IP信息
	 * @Copyright: Copyright (c) 20120316
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String saveAppIp() {
		if (theForm == null) {
			theForm = new AppDeployWorkFlowForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		AppNeedObj obj = new AppNeedObj();
		OrderInfoObj orObj = new OrderInfoObj();
		String app_ip = (String) request.getParameter("subNum");// 得到IP
		if (app_ip != null && !"".equals(app_ip)) {
			obj.setAPP_IP(app_ip);
		}
		// obj.setAPP_IP(request.getParameter("subNum"));// 得到IP
		String vlanip = (String) request.getParameter("subvlan");// 得到服务IP
		if (vlanip != null && !"".equals(vlanip)) {
			obj.setVLANIP(vlanip);
		}
		// obj.setVLANIP(request.getParameter("subvlan"));// 得到服务IP
		obj.setAPP_IPMODEL(theForm.getAPP_IPMODEL());
		obj.setNEED_NUMBERS(theForm.getNEED_NUMBERS());
		appWorkFlowService.updateAppIpInfo(obj);// 更新IP信息
		/* 将修改的模板的信息保存 */
		TempletObj temobj = new TempletObj();
		temobj.setTEM_ID(theForm.getSELECT());
		HashMap map = templetService.queryType(temobj); // 查询模板的类型
		orObj = getObj(
				"com.sitech.basd.sxcloud.workflow.web.appworkflow.form.AppDeployWorkFlowForm",
				"com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.OrderInfoObj", orObj,
				theForm, map);
		orObj.setNEED_NUMBERS(theForm.getNEED_NUMBERS());
		List list = appWorkFlowService.queryAppInfoList(orObj);
		if (list != null && list.size() > 0) {
			appWorkFlowService.updateOrderInfo(orObj);
		} else {
			appWorkFlowService.insertAppInfo(orObj);
		}
		return null;

	}

	/**
	 * @Title:动态保存修改的模板信息
	 * @Copyright: Copyright (c) 20120316
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public OrderInfoObj getObj(String classname1, String classname2, OrderInfoObj obj,
			AppDeployWorkFlowForm theForm, HashMap map) {

		Class formClass = null;
		try {
			formClass = Class.forName(classname1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}// 传入Form
		Class beanClass = null;
		try {
			beanClass = Class.forName(classname2);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // 传入TempletObj

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
			Method me1 = null;
			try {
				me1 = formClass.getMethod(mName, new Class[] {});
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			for (String key : set) {
				if (str.equals(key + "VALUE")) {
					Object o = null;
					try {
						o = me1.invoke(theForm, new Object[] {});
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					if (o != null) {
						o.toString();
						Field kvField = null;
						try {
							kvField = formClass.getDeclaredField(key);
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (NoSuchFieldException e) {
							e.printStackTrace();
						}
						String kvMethod = "set" + "KV" + n;
						Method setkv = null;
						try {
							setkv = beanClass.getMethod(kvMethod, kvField.getType());
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} // 得到kv的方法
						try {
							setkv.invoke(obj, key + ":" + o);
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} // 将字符串拼接后设置到kv中
						n++;
					}
				}
			}
			obj.setKvnum(n - 1);
			obj.setTYPE(theForm.getTYPE());
		}
		return obj;
	}

	/**
	 * @Title:根据不同的模板加载模板信息
	 * @Copyright: Copyright (c) 2012-03-10
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String selectTempletRequest() throws BaseException {
		if (theForm == null) {
			theForm = new AppDeployWorkFlowForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		theForm.setAPP_PORTVALUE("");
		theForm.setAPP_NAMEVALUE("");
		TempletObj temObj = new TempletObj();
		String tem_id = request.getParameter("SELECT");
		temObj.setTEM_ID(tem_id);
		theForm = templetInfo(theForm, temObj); // 调用方法动态显示页面的模版信息
		List templetList = templetService.queryAppForListByObj(temObj); // 模板列表
		theForm.setTempletList(templetList);

		return "select";

	}

	/**
	 * @Title:查看模板信息
	 * @Copyright: Copyright (c) 20120111
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public AppDeployWorkFlowForm templetInfo(AppDeployWorkFlowForm theForm, TempletObj obj) {

		TempletObj tempObj = templetService.queryByObj(obj);

		theForm.setTEM_NAME(tempObj.getTEM_NAME());
		theForm.setTYPE(tempObj.getTYPE());
		// theForm.setTYPE_NAME(tempObj.getTYPE_NAME());
		// theForm.setTEM_DESC(tempObj.getTEM_DESC());
		HashMap map = templetService.queryType(tempObj);
		HashMap kvmap = templetService.getKvMap(tempObj);
		theForm = getForm(
				"com.sitech.basd.sxcloud.workflow.web.appworkflow.form.AppDeployWorkFlowForm",
				theForm, map, kvmap);

		return theForm;
	}

	/**
	 * @Title:查询可用的IP地址
	 * @Copyright: Copyright (c) 2012-03-16
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String checkAppIp() throws BaseException {
		if (theForm == null) {
			theForm = new AppDeployWorkFlowForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TempletObj temObj = new TempletObj();
		String tem_id = request.getParameter("SELECT");
		temObj.setTEM_ID(tem_id);
		List templetList = templetService.queryAppForListByObj(temObj); // 模板列表
		theForm.setTempletList(templetList);

		TbBusiDeployExampleObj deployObj = new TbBusiDeployExampleObj();
		deployObj.setAPPID(Integer.parseInt(theForm.getAPP_IDVALUE()));
		deployObj.setPagination(this.getPaginater().initPagination(request));// 分页
		List resultList = busiHostService.queryAppIp(deployObj);
		theForm.setResultList(resultList);
		return "check";

	}

	/**
	 * 
	 * @Title:打回提交应用部署申请工单
	 * @Copyright: Copyright (c) 2012-03-06
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 * 
	 */
	public String fightbackWaitDealApp() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		if (theForm == null) {
			theForm = new AppDeployWorkFlowForm();
		}
		OrderInfoObj orderObj = new OrderInfoObj();
		String needNum = theForm.getNEED_NUMBERS();
		String taskId = theForm.getTaskId();
		String remark = theForm.getINPUT_REMARK();
		// int processNode = Integer.parseInt((String) request
		// .getParameter("processNode"));
		List<DisposeManObj> returnList = workflowEngineService.triggerSubmitFlow(
				Integer.parseInt(taskId), FlowConstant.FLOW_OPERATE_PLAYEDBACK, remark, null);
		// 更新需求单的状态
		AppNeedObj obj = new AppNeedObj();
		obj.setNEED_NUMBERS(needNum);
		orderObj.setNEED_NUMBERS(needNum);
		List list = appWorkFlowService.queryAppInfoList(orderObj);
		if (list != null && list.size() == 1) {
			Class c = null;
			try {
				c = Class
						.forName("com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.OrderInfoObj");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Field[] field = c.getDeclaredFields();
			for (Field f : field) {
				PropertyDescriptor pd = null;
				try {
					pd = new PropertyDescriptor(f.getName(), c);
				} catch (IntrospectionException e) {
					e.printStackTrace();
				}
				Method wM = pd.getWriteMethod();// 获得写方法
				if (wM.toString().indexOf("KV") != -1) {
					try {
						wM.invoke(orderObj, "");
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} // 清空KV里的值
				}
			}
			appWorkFlowService.updateOrderInfo(orderObj); // 清空kv的值
		}
		obj.setNEED_STATUS(WorkFlowConstant.NEEDSTATUS_DOING);
		obj.setAPP_IP("");
		obj.setVLANIP("");
		appWorkFlowService.updateByObj(obj);
		appWorkFlowService.updateAppIpInfo(obj); // 打回时清空IP信息
		String nextDealMan = this.getNextDealManInfo(returnList);
		request.setAttribute("NextDealMan", nextDealMan);
		return "fightback";
	}

	/**
	 * @Title:查询已处理应用部署工单信息
	 * @Copyright: Copyright (c) 2012-03-02
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String alreadyDealApp() {
		if (theForm == null) {
			theForm = new AppDeployWorkFlowForm();
		}
		theForm.setFLOW_TYPE(FlowConstant.FLOW_TYPE_ALREADYDO);
		FLOW_TYPE = FlowConstant.FLOW_TYPE_ALREADYDO;
		return "already";
	}

	/**
	 * @Title:查询打回应用部署工单信息
	 * @Copyright: Copyright (c) 2012-03-02
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String rejectAppDeploy() {
		if (theForm == null) {
			theForm = new AppDeployWorkFlowForm();
		}
		theForm.setFLOW_TYPE(FlowConstant.FLOW_TYPE_PLAYEDBACK);
		FLOW_TYPE = FlowConstant.FLOW_TYPE_PLAYEDBACK;
		return "listReject";
	}

	/**
	 * 
	 * @Title: 新建应用部署工单唯一性判断
	 * @Copyright: Copyright (c) 2012-3-3
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String appOrderUniqueJudgement() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String NEED_NUMBERS = request.getParameter("NEED_NUMBERS");

		AppNeedObj obj = new AppNeedObj();

		obj.setNEED_NUMBERS(NEED_NUMBERS);
		obj.setNEED_STATUS(-1);// 去掉状态查询条件
		this.paginater.initPagination(request);
		List<?> list = appWorkFlowService.queryAppNeedList(obj);

		List<String> jsonArr = new ArrayList<String>();

		JSONArray json = new JSONArray();

		if (null != list && !list.isEmpty()) {
			jsonArr.add(WorkFlowConstant.NO);
		} else {
			jsonArr.add(WorkFlowConstant.YES);
		}
		try {
			json = JSONArray.fromObject(jsonArr);
			Struts2Utils.getResponse().getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
			LogHelper.debug("AppDeployWorkFlowAction.appOrderUniqueJudgement():" + e.getMessage()
					+ getClass().getName());
		}
		return null;
	}

	private DeployExampleService deployExampleService;
	private UserInfoService userInfoService;
	private AppWorkFlowService appWorkFlowService;
	private WorkflowEngineService workflowEngineService;
	private TempletService templetService;
	private AppService appService;
	private BusiHostService busiHostService;

	public void setTempletService(TempletService templetService) {
		this.templetService = templetService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public void setAppWorkFlowService(AppWorkFlowService appWorkFlowService) {
		this.appWorkFlowService = appWorkFlowService;
	}

	public void setWorkflowEngineService(WorkflowEngineService workflowEngineService) {
		this.workflowEngineService = workflowEngineService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}

	public void setDeployExampleService(DeployExampleService deployExampleService) {
		this.deployExampleService = deployExampleService;
	}

}
