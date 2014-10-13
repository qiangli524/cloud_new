package com.sitech.basd.sxcloud.workflow.service.engine;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sitech.basd.sxcloud.workflow.dao.engine.WorkflowEngineDao;
import com.sitech.basd.sxcloud.workflow.domain.engine.DisposeManObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowNodeDescObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowNodeDetailsObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowTaskObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.NextDisposeManObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.ProcessInfoObj;

public class WorkflowEngineServiceImpl implements WorkflowEngineService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private WorkflowEngineDao workflowEngineDao;

	/**
	 * @Title: 保存新建申请工单成草稿时触发的流程动作
	 * @param: applicationId 申请单编号
	 * @param: applicationType 流程类型,如上线流程、下线流程
	 * @param: currUserId 当前工单操作人唯一标识
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 null：新建草稿工单保存失败,非null：新建草稿工单保存成功
	 */
	public List<DisposeManObj> triggerSaveNewFlow(String applicationId,
			int applicationType, String currUserId) {
		int currNodeId = workflowEngineDao
				.getProcessStartNodeId(applicationType);
		if (currNodeId <= 0 || applicationId == null
				|| applicationId.equals("") || currUserId == null
				|| currUserId.equals("")) {
			return null;
		}
		boolean bl = canCreateNewApplication(applicationType, currUserId);
		if (bl == false) {
			logger.debug("用户：" + currUserId + " 不具备发起工单权限");
			return null;
		}
		FlowTaskObj flowTaskObj = new FlowTaskObj();
		flowTaskObj.setPER_TASK_ID(0);
		flowTaskObj.setPROCESS_ID(applicationType);
		flowTaskObj.setPROCESS_SERIALNUMBER(applicationId);
		flowTaskObj.setTASK_STATUS(0);
		flowTaskObj.setDISPOSE_MAN(currUserId);
		flowTaskObj.setNODE_ID(currNodeId);
		flowTaskObj.setTASK_OPERATE(1);
		flowTaskObj.setTASK_REMARK("");
		int disposeStatus = workflowEngineDao.insertFlowTask(flowTaskObj);
		if (disposeStatus > 0) {
			return new ArrayList();
		}
		return null;
	}

	/**
	 * @Title: 提交新建申请工单时触发的流程动作
	 * @param: applicationId 申请单编号
	 * @param: applicationType 流程类型,如上线流程、下线流程
	 * @param: userId 当前工单操作人唯一标识
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 null：新建工单提交失败,非null：新建工单提交成功,返回值中存放工单下一处理人
	 */
	public List<DisposeManObj> triggerSubmitNewFlow(String applicationId,
			int applicationType, String currUserId) {
		int currNodeId = workflowEngineDao
				.getProcessStartNodeId(applicationType);
		if (currNodeId <= 0 || applicationId == null
				|| applicationId.equals("") || currUserId == null
				|| currUserId.equals("")) {
			return null;
		}
		boolean bl = canCreateNewApplication(applicationType, currUserId);
		if (bl == false) {
			logger.debug("用户：" + currUserId + " 不具备发起工单权限");
			return null;
		}
		return triggerFlow(applicationId, applicationType, currUserId,
				currNodeId, FlowConstant.FLOW_OPERATE_COMMITNEW, 2, "", "");
	}

	/**
	 * @Title: 删除未发起新建申请工单时触发的流程动作
	 * @param: applicationId 申请单编号
	 * @param: applicationType 流程类型,如上线流程、下线流程
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 null：草稿工单删除失败,非null：草稿工单提交成功
	 */
	public List<DisposeManObj> triggerDeleteNewFlow(String applicationId,
			int applicationType) {
		int currNodeId = workflowEngineDao
				.getProcessStartNodeId(applicationType);
		if (currNodeId <= 0 || applicationId == null
				|| applicationId.equals("")) {
			return null;
		}
		int disposeStatus = workflowEngineDao.deleteNewFlowTask(applicationId,
				currNodeId, applicationType);
		if (disposeStatus > 0) {
			return new ArrayList();
		}
		return null;

	}

	/**
	 * @Title: 撤回全然未处理的工单时触发的流程动作
	 * @param: applicationId 申请单编号
	 * @param: applicationType 流程类型,如上线流程、下线流程
	 * @param: currUserId 当前工单处理人唯一标识
	 * @param: currNodeId 当前环节编号，如发起环节、审批环节
	 * @param: currTaskId 当前用户任务编号
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 null：撤回工单失败,非null：撤回工单成功
	 */
	private List<DisposeManObj> triggerWithdrawFlow(String applicationId,
			int applicationType, String currUserId, int currNodeId,
			int currTaskId) {
		if (applicationId == null || applicationId.equals("")
				|| applicationType < 0 || currUserId == null
				|| currUserId.equals("") || currNodeId < 0 || currTaskId < 0) {
			return null;
		}
		boolean isCan = canWithdraw(currTaskId);
		List ret = null;
		FlowTaskObj flowTaskObj = new FlowTaskObj();
		if (isCan) {
			flowTaskObj.setTASK_ID(currTaskId);
			flowTaskObj.setTASK_STATUS(1);
			flowTaskObj.setTASK_REMARK("");
			flowTaskObj.setTASK_OPERATE(1);
			int disposeStatus = workflowEngineDao.updateFlowTask(flowTaskObj);
			if (disposeStatus > 0) {
				disposeStatus = workflowEngineDao
						.deleteNextNodeFlowTask(currTaskId);
				if (disposeStatus > 0) {
					ret = new ArrayList();
					return ret;
				}
			} else {
				flowTaskObj.setTASK_STATUS(2);
				flowTaskObj.setTASK_OPERATE(1);
				workflowEngineDao.updateFlowTask(flowTaskObj);
			}
		}
		return null;
	}

	/**
	 * @Title: 审批成功、被打回工单提交成功、提交工单时触发的流程动作
	 * @param: applicationId 申请单编号
	 * @param: applicationType 流程类型,如上线流程、下线流程
	 * @param: userId 当前工单处理人唯一标识
	 * @param: currNodeId 当前环节编号，如发起环节、审批环节
	 * @param: currTaskId 当前用户任务编号
	 * @param: idea 审批意见
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 null：工单提交失败,非null：工单提交成功,返回值中存放工单下一处理人
	 */
	private List<DisposeManObj> triggerSubmitFlow(String applicationId,
			int applicationType, int currNodeId, String currUserId,
			int currTaskId, String idea) {
		if (applicationId == null || applicationId.equals("")
				|| applicationType < 0 || currUserId == null
				|| currUserId.equals("") || currNodeId < 0 || currTaskId < 0) {
			return null;
		}
		return triggerFlow(applicationId, applicationType, currUserId,
				currNodeId, FlowConstant.FLOW_OPERATE_APPROVAL, currTaskId,
				idea, "");
	}

	/**
	 * @Title: 结束流程时触发的流程动作
	 * @param: applicationId 申请单编号
	 * @param: applicationType 流程类型,如上线流程、下线流程
	 * @param: userId 当前工单处理人唯一标识
	 * @param: currNodeId 当前环节编号，如发起环节、审批环节
	 * @param: currTaskId 当前用户任务编号
	 * @param: idea 审批意见
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 null：工单提交失败,非null：工单提交成功,返回值中存放工单下一处理人
	 */
	private List<DisposeManObj> triggerCloseFlow(String applicationId,
			int applicationType, int currNodeId, String currUserId,
			int currTaskId, String idea) {
		if (applicationId == null || applicationId.equals("")
				|| applicationType < 0 || currUserId == null
				|| currUserId.equals("") || currNodeId < 0 || currTaskId < 0) {
			return null;
		}
		FlowTaskObj flowTaskObj = new FlowTaskObj();
		flowTaskObj.setTASK_ID(currTaskId);
		flowTaskObj.setTASK_STATUS(2);
		flowTaskObj.setTASK_REMARK(idea);
		flowTaskObj.setTASK_OPERATE(1);
		int disposeStatus = workflowEngineDao.updateFlowTask(flowTaskObj);
		if (disposeStatus > 0) {
			return new ArrayList();
		}
		return null;
	}

	/**
	 * @Title: 派单审批提交工单时触发的流程动作
	 * @param: applicationId 申请单编号
	 * @param: applicationType 流程类型,如上线流程、下线流程
	 * @param: userId 当前工单处理人唯一标识
	 * @param: currNodeId 当前环节编号，如发起环节、审批环节
	 * @param: currTaskId 当前用户任务编号
	 * @param: idea 审批意见
	 * @param: nextUserId 下一环节处理人唯一标识
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 null：工单提交失败,非null：工单提交成功,返回值中存放工单下一处理人
	 */
	private List<DisposeManObj> triggerSubmitSentFlow(String applicationId,
			int applicationType, int currNodeId, String currUserId,
			int currTaskId, String idea, String nextUserId) {
		if (applicationId == null || applicationId.equals("")
				|| applicationType < 0 || currUserId == null
				|| currUserId.equals("") || currNodeId < 0 || currTaskId < 0
				|| nextUserId == null || nextUserId.equals("")) {
			return null;
		}
		return triggerFlow(applicationId, applicationType, currUserId,
				currNodeId, FlowConstant.FLOW_OPERATE_SENT, currTaskId, idea,
				nextUserId);
	}

	/**
	 * @Title: 审批不通过【打回】工单时触发的流程动作
	 * @param: applicationId 申请单编号
	 * @param: applicationType 流程类型,如上线流程、下线流程
	 * @param: currUserId 当前工单处理人唯一标识
	 * @param: currNodeId 当前环节编号，如发起环节、审批环节
	 * @param: currTaskId 当前任务编号
	 * @param: idea 审批意见
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 null：打回工单提交失败,非null：打回工单提交成功
	 */
	private List<DisposeManObj> triggerPlayedBackFlow(String applicationId,
			int applicationType, int currNodeId, String currUserId,
			int currTaskId, String idea) {
		if (applicationId == null || applicationId.equals("")
				|| applicationType < 0 || currUserId == null
				|| currUserId.equals("") || currNodeId < 0 || currTaskId < 0) {
			return null;
		}
		return triggerFlow(applicationId, applicationType, currUserId,
				currNodeId, FlowConstant.FLOW_OPERATE_PLAYEDBACK, currTaskId,
				idea, "");
	}

	/**
	 * @Title: 挂起工单时触发的流程动作
	 * @param: currTaskId 当前任务编号
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 null：挂起工单提交失败,非null：挂起工单提交成功
	 */
	private List<DisposeManObj> triggerHungFlow(int currTaskId) {
		if (currTaskId < 0) {
			return null;
		}
		int disposeStatus = workflowEngineDao.updateFlowTaskHungStatus(
				currTaskId, 2);
		if (disposeStatus > 0) {
			return new ArrayList();
		}
		return null;
	}

	/**
	 * @Title: 解除挂起工单时触发的流程动作
	 * @param: currTaskId 当前任务编号
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 null：解除挂起失败,非null：解除挂起成功
	 */
	private List<DisposeManObj> triggerRecoveryFlow(int currTaskId) {
		if (currTaskId < 0) {
			return null;
		}
		int disposeStatus = workflowEngineDao.updateFlowTaskHungStatus(
				currTaskId, 1);
		if (disposeStatus > 0) {
			return new ArrayList();
		}
		return null;
	}

	/**
	 * @Title: 提交工单时触发的流程动作,包括申请提交、审批提交、打回提交
	 * @param: applicationId 申请单编号
	 * @param: applicationType 流程类型,如上线流程、下线流程
	 * @param: currUserId 当前工单处理人唯一标识
	 * @param: currNodeId 当前环节编号，如发起环节、审批环节
	 * @param: currTaskId 当前任务编号
	 * @param: nextNodeId 下一节点处理人
	 * @param: currNodeOprate 当前节点操作
	 * @param: idea 审批意见
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 null：推动失败,非null：推动成功
	 */
	private List<DisposeManObj> triggerFlow(String applicationId,
			int applicationType, String currUserId, int currNodeId,
			int currNodeOprate, int currTaskId, String idea, String nextNodeId) {
		FlowTaskObj flowTaskObj = new FlowTaskObj();
		int disposeStatus = 0;
		int taskStatus = 0;
		int taskOprate = 1;
		int preTaskId = currTaskId;
		List<DisposeManObj> ret = new ArrayList<DisposeManObj>();
		if (currNodeOprate == FlowConstant.FLOW_OPERATE_COMMITNEW) {// 保存新建工单
			flowTaskObj.setPER_TASK_ID(0);
			flowTaskObj.setPROCESS_ID(applicationType);
			flowTaskObj.setPROCESS_SERIALNUMBER(applicationId);
			flowTaskObj.setTASK_STATUS(2);
			flowTaskObj.setDISPOSE_MAN(currUserId);
			flowTaskObj.setNODE_ID(currNodeId);
			int tempTaskId = workflowEngineDao.getCacheFlowTaskId(flowTaskObj);
			if (tempTaskId > 0) {// 改变草稿状态
				flowTaskObj.setTASK_ID(tempTaskId);
				flowTaskObj.setTASK_STATUS(2);
				flowTaskObj.setTASK_OPERATE(1);
				disposeStatus = workflowEngineDao.updateFlowTask(flowTaskObj);
			} else {// 申请直接提交
				flowTaskObj.setTASK_OPERATE(1);
				flowTaskObj.setTASK_REMARK("");
				disposeStatus = workflowEngineDao.insertFlowTask(flowTaskObj);
			}
			preTaskId = disposeStatus;
			taskStatus = 1;
			taskOprate = 1;
		} else if (currNodeOprate == FlowConstant.FLOW_OPERATE_APPROVAL) {// 审批操作
			flowTaskObj.setTASK_ID(currTaskId);
			flowTaskObj.setTASK_STATUS(2);
			flowTaskObj.setTASK_REMARK(idea);
			flowTaskObj.setTASK_OPERATE(1);
			disposeStatus = workflowEngineDao.updateFlowTask(flowTaskObj);
			taskStatus = 1;
			taskOprate = 1;
		} else if (currNodeOprate == FlowConstant.FLOW_OPERATE_PLAYEDBACK) {// 打回
			flowTaskObj.setTASK_ID(currTaskId);
			flowTaskObj.setTASK_STATUS(2);
			flowTaskObj.setTASK_REMARK(idea);
			flowTaskObj.setTASK_OPERATE(1);
			disposeStatus = workflowEngineDao.updateFlowTask(flowTaskObj);
			taskStatus = 1;
			taskOprate = 4;
		} else if (currNodeOprate == FlowConstant.FLOW_OPERATE_SENT) {// 派单工单
			flowTaskObj.setTASK_ID(currTaskId);
			flowTaskObj.setTASK_STATUS(2);
			flowTaskObj.setTASK_REMARK(idea);
			flowTaskObj.setTASK_OPERATE(1);
			disposeStatus = workflowEngineDao.updateFlowTask(flowTaskObj);
			taskStatus = 1;
			taskOprate = 1;
		}
		if (disposeStatus > 0) {
			flowTaskObj.setPER_TASK_ID(preTaskId);
			flowTaskObj.setPROCESS_ID(applicationType);
			flowTaskObj.setPROCESS_SERIALNUMBER(applicationId);
			flowTaskObj.setTASK_STATUS(taskStatus);
			flowTaskObj.setTASK_OPERATE(taskOprate);
			flowTaskObj.setTASK_REMARK("");

			DisposeManObj disposeManObj = new DisposeManObj();
			disposeManObj.setApplicationType(applicationType);
			disposeManObj.setCurrNodeId(currNodeId);
			disposeManObj.setOprate(taskOprate);
			List listNodeDispose = workflowEngineDao
					.getProcessNodeDispose(disposeManObj);
			if (taskOprate == 1) {// 审批通过
				if (listNodeDispose != null && listNodeDispose.size() > 0) {
					for (int i = 0; i < listNodeDispose.size(); i++) {
						DisposeManObj tempObj = (DisposeManObj) listNodeDispose
								.get(i);
						flowTaskObj.setNODE_ID(tempObj.getNextNodeId());
						flowTaskObj.setDISPOSE_MAN(tempObj.getUserId());
						if (currNodeOprate == FlowConstant.FLOW_OPERATE_SENT) {// 工单派发审批提交
							if (tempObj.getUserId() != null
									&& tempObj.getUserId().equals(nextNodeId)) {
								workflowEngineDao.insertFlowTask(flowTaskObj);
								ret.add(tempObj);
								return ret;
							}
						} else {
							workflowEngineDao.insertFlowTask(flowTaskObj);
							ret.add(tempObj);
						}
					}
				}
			} else if (taskOprate == 4) {// 打回
				if (listNodeDispose != null && listNodeDispose.size() > 0) {
					for (int i = 0; i < listNodeDispose.size(); i++) {
						DisposeManObj tempObj = (DisposeManObj) listNodeDispose
								.get(i);
						flowTaskObj.setNODE_ID(tempObj.getNextNodeId());
						listNodeDispose = workflowEngineDao
								.getProcessNodeHisDisposeMan(applicationId,
										applicationType, tempObj
												.getNextNodeId());
						if (listNodeDispose != null
								&& listNodeDispose.size() > 0) {
							for (int k = 0; k < listNodeDispose.size(); k++) {
								DisposeManObj obj = (DisposeManObj) listNodeDispose
										.get(k);
								flowTaskObj.setDISPOSE_MAN(obj.getUserId());
								workflowEngineDao.insertFlowTask(flowTaskObj);
								ret.add(obj);
								return ret;
							}
						}
					}
				}
			}
		} else {
			ret = null;
		}

		return ret;
	}

	/**
	 * @Title: 判断当前流程是否可以撤回
	 * @prama: taskId:当前工单任务号
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return true：可以撤回 ,false:不能撤回
	 */
	public boolean canWithdraw(int taskId) {
		List lst = workflowEngineDao.getNextNodeAlreadyDoTask(taskId);
		return (lst == null || lst.size() == 0) ? false : true;
	}

	/**
	 * @Title: 获取派单处理人列表
	 * @prama: applicationType:申请单类型 TB_ASLM_FLOW_PROCESS(PROCESS_ID)
	 * @prama: currNodeId: 当前工单环节编号,如发起环节、审批环节
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return List 待办工单编号集合
	 */
	private List<DisposeManObj> getSentDisposeMan(int applicationType,
			int currNodeId) {
		if (applicationType <= 0) {
			return null;
		}
		if (currNodeId <= 0) {
			return null;
		}
		DisposeManObj disposeManObj = new DisposeManObj();
		disposeManObj.setApplicationType(applicationType);
		disposeManObj.setCurrNodeId(currNodeId);
		disposeManObj.setOprate(0);
		return workflowEngineDao.getProcessNodeDispose(disposeManObj);
	}

	/**
	 * @Title: 获取某用户草稿工单编号集合
	 * @prama: applicationType:申请单类型,如上线、下线流程
	 * @prama: currUserId: 当前工单处理人唯一标识
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return List 待办工单编号集合
	 */
	private List<ProcessInfoObj> getDraftList(int applicationType,
			String currUserId) {
		if (applicationType <= 0) {
			return null;
		}
		if (currUserId == null || currUserId.equals("")) {
			return null;
		}
		ProcessInfoObj workflowEngineObj = new ProcessInfoObj();
		workflowEngineObj.setApplicationType(applicationType);
		workflowEngineObj.setUserId(currUserId);
		return workflowEngineDao.getDraftList(workflowEngineObj);
	}

	/**
	 * @Title: 获取某用户待办工单编号集合
	 * @prama: applicationType:申请单类型,如上线、下线流程
	 * @prama: currUserId: 当前工单处理人唯一标识
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return List 待办工单编号集合
	 */
	private List<ProcessInfoObj> getToDoList(int applicationType,
			String currUserId) {
		if (applicationType <= 0) {
			return null;
		}
		if (currUserId == null || currUserId.equals("")) {
			return null;
		}
		List<ProcessInfoObj> ret = null;
		ProcessInfoObj workflowEngineObj = new ProcessInfoObj();
		workflowEngineObj.setApplicationType(applicationType);
		workflowEngineObj.setUserId(currUserId);
		return workflowEngineDao.getToDoList(workflowEngineObj);
	}

	/**
	 * @Title: 获取某用户已办工单编号集合
	 * @prama: applicationType:申请单类型 如上线、下线流程
	 * @prama: currUserId: 当前工单处理人唯一标识
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return List 已办工单编号集合
	 */
	private List<ProcessInfoObj> getAlreadyDo(int applicationType,
			String currUserId) {
		if (applicationType <= 0) {
			return null;
		}
		if (currUserId == null || currUserId.equals("")) {
			return null;
		}
		ProcessInfoObj workflowEngineObj = new ProcessInfoObj();
		workflowEngineObj.setApplicationType(applicationType);
		workflowEngineObj.setUserId(currUserId);
		return workflowEngineDao.getAlreadyDo(workflowEngineObj);
	}

	/**
	 * @Title: 获取某用户被打回工单编号集合
	 * @prama: applicationType:申请单类型 如上线、下线流程
	 * @prama: currUserId: 当前工单处理人唯一标识
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return List 打回工单编号集合
	 */
	private List<ProcessInfoObj> getPlayedBack(int applicationType,
			String currUserId) {
		if (applicationType <= 0) {
			return null;
		}
		if (currUserId == null || currUserId.equals("")) {
			return null;
		}
		ProcessInfoObj workflowEngineObj = new ProcessInfoObj();
		workflowEngineObj.setApplicationType(applicationType);
		workflowEngineObj.setUserId(currUserId);
		return workflowEngineDao.getPlayedBack(workflowEngineObj);
	}

	/**
	 * @Title: 获取某用户工单编号集合
	 * @prama: applicationType:申请单类型 如上线、下线流程
	 * @prama: currUserId: 当前工单处理人唯一标识
	 * @prama: listType : 工单列表类型 取值
	 *         常量FlowConstant【FLOW_TYPE_DRAFT、FLOW_TYPE_TODOLIST、FLOW_TYPE_ALREADYDO、FLOW_TYPE_PLAYEDBACK、FLOW_TYPE_HUNGLIST】
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return List 挂起工单编号集合
	 */
	public List<ProcessInfoObj> getFlowList(int applicationType,
			String currUserId, String listType) {
		if (listType == null || listType.equals("")) {
			return null;
		}
		List<ProcessInfoObj> ret = null;
		if (listType.equals(FlowConstant.FLOW_TYPE_TODOLIST)) {
			ret = getToDoList(applicationType, currUserId);
		} else if (listType.equals(FlowConstant.FLOW_TYPE_ALREADYDO)) {
			ret = getAlreadyDo(applicationType, currUserId);
		} else if (listType.equals(FlowConstant.FLOW_TYPE_PLAYEDBACK)) {
			ret = getPlayedBack(applicationType, currUserId);
		} else if (listType.equals(FlowConstant.FLOW_TYPE_HUNGLIST)) {
			ret = getHungList(applicationType, currUserId);
		} else if (listType.equals(FlowConstant.FLOW_TYPE_DRAFT)) {
			ret = getDraftList(applicationType, currUserId);
		}
		FlowTaskObj flowTaskObj = new FlowTaskObj();
		String disposeMan = "";
		if (ret != null) {// 添加流程当前处理人,流程当前待办人
			for (int i = 0; i < ret.size(); i++) {
				disposeMan = "";
				ProcessInfoObj obj = (ProcessInfoObj) ret.get(i);
				flowTaskObj.setPROCESS_ID(obj.getApplicationType());
				flowTaskObj.setPROCESS_SERIALNUMBER(obj.getApplicationId());
				List<FlowTaskObj> lst = workflowEngineDao
						.queryProcessToDoDisposeMan(flowTaskObj);
				if (lst != null && lst.size() > 0) {
					for (int j = 0; j < lst.size(); j++) {
						if (disposeMan != null && disposeMan.length() > 0) {
							disposeMan = disposeMan + ",";
						}
						disposeMan = disposeMan + lst.get(j).getDISPOSE_MAN();
					}
				}
				obj.setDisposeMan(disposeMan);
			}
		}
		return ret;
	}

	/**
	 * @Title: 获取某用户挂起工单编号集合
	 * @prama: applicationType:申请单类型 如上线、下线流程
	 * @prama: currUserId: 当前工单处理人唯一标识
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return List 挂起工单编号集合
	 */
	public List<ProcessInfoObj> getHungList(int applicationType,
			String currUserId) {
		if (applicationType <= 0) {
			return null;
		}
		if (currUserId == null || currUserId.equals("")) {
			return null;
		}
		ProcessInfoObj workflowEngineObj = new ProcessInfoObj();
		workflowEngineObj.setApplicationType(applicationType);
		workflowEngineObj.setUserId(currUserId);
		return workflowEngineDao.getHungList(workflowEngineObj);
	}

	/**
	 * @Title: 获取工单任务轨迹
	 * @param: applicationId 申请单编号
	 * @param: applicationType 流程类型,如上线流程、下线流程
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return List 工单任务轨迹
	 */
	private List<FlowTaskObj> getProcessTaskTrack(int applicationType,
			String applicationId) {
		if (applicationType == 0 || applicationId == null
				|| applicationId.equals("")) {
			return null;
		}
		FlowTaskObj flowTaskObj = new FlowTaskObj();
		flowTaskObj.setPROCESS_ID(applicationType);
		flowTaskObj.setPROCESS_SERIALNUMBER(applicationId);
		return workflowEngineDao.getProcessTaskTrack(flowTaskObj);
	}

	/**
	 * @Title: 判断当前用户是否具备新建工单权限
	 * @prama: applicationType:申请单类型 如上线、下线流程
	 * @prama: userId: 当前用户唯一标识
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return true:具备当前工单新建权限 false:没有权限发起工单权限
	 */
	public boolean canCreateNewApplication(int applicationType, String userId) {
		List lst = workflowEngineDao.getStartProcessDisposeMan(applicationType,
				userId);
		return (lst == null || lst.size() == 0) ? false : true;
	}

	/**
	 * @Title: 获取当前任务详情,包括：任务编号、任务轨迹、当前任务信息【节点名称、节点备注、节点处理权限、节点下一处理人】
	 * @prama: taskId:流程任务号
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return FlowNodeDetailsObj 任务详情
	 */
	public FlowNodeDetailsObj getFlowNodeDetails(int taskId) {
		if (taskId == 0) {
			return null;
		}

		List task_operate = new ArrayList();
		List<NextDisposeManObj> node_next_disposeman = new ArrayList<NextDisposeManObj>();
		FlowNodeDetailsObj flowNodeDetailsObj = new FlowNodeDetailsObj();
		FlowTaskObj flowTaskObj = new FlowTaskObj();
		flowTaskObj.setTASK_ID(taskId);
		flowTaskObj = workflowEngineDao.queryFlowTask(flowTaskObj);
		List<FlowTaskObj> task_track = getProcessTaskTrack(flowTaskObj
				.getPROCESS_ID(), flowTaskObj.getPROCESS_SERIALNUMBER());
		FlowNodeDescObj flowNodeDescObj = getFlowNodeDesc(flowTaskObj
				.getPROCESS_ID(), flowTaskObj.getNODE_ID());

		if (flowTaskObj.getTASK_STATUS() == FlowConstant.FLOW_TASK_DISPOSE) {// 已办
			FlowTaskObj tempObj = new FlowTaskObj();
			tempObj.setTASK_ID(0);
			tempObj.setPER_TASK_ID(taskId);
			tempObj = workflowEngineDao.queryFlowTask(tempObj);
			if (tempObj != null) {
				NextDisposeManObj nextDisposeManObj = new NextDisposeManObj();
				nextDisposeManObj.setUserId(tempObj.getDISPOSE_MAN());
				nextDisposeManObj.setOprate(flowTaskObj.getTASK_OPERATE());
				node_next_disposeman.add(nextDisposeManObj);
			}
		}

		List<DisposeManObj> node_disposeman = getSentDisposeMan(flowTaskObj
				.getPROCESS_ID(), flowTaskObj.getNODE_ID());
		if (node_disposeman != null && node_disposeman.size() > 0) {
			NextDisposeManObj nextDisposeManObj = null;
			boolean breakFlag = false;
			for (int i = 0; i < node_disposeman.size(); i++) {
				breakFlag = false;
				int oprate = node_disposeman.get(i).getOprate();
				if (flowTaskObj.getTASK_STATUS() == FlowConstant.FLOW_TASK_UNDISPOSE) {// 待办
					if (oprate == FlowConstant.FLOW_OPERATE_APPROVAL
							&& flowNodeDescObj.getNODE_TYPE() == FlowConstant.FLOW_OPERATE_SENT) {// 派单节点
						oprate = FlowConstant.FLOW_OPERATE_SENT;
					} else if (oprate == FlowConstant.FLOW_OPERATE_HUNG
							&& flowTaskObj.getTASK_OPERATE() == FlowConstant.FLOW_OPERATE_HUNG) {// 取消挂起
						task_operate.clear();
						oprate = FlowConstant.FLOW_OPERATE_CANCALHUNG;
						breakFlag = true;
					}
					if (!task_operate.contains(oprate)) {
						task_operate.add(oprate);
					}
					String userId = node_disposeman.get(i).getUserId();
					nextDisposeManObj = new NextDisposeManObj();
					nextDisposeManObj.setUserId(userId);
					nextDisposeManObj.setOprate(oprate);
					node_next_disposeman.add(nextDisposeManObj);
				} else {// 已办
					if (oprate == FlowConstant.FLOW_OPERATE_WITHDRAW) {// 撤回
						boolean isWithdraw = canWithdraw(taskId);
						if (isWithdraw) {
							oprate = FlowConstant.FLOW_OPERATE_WITHDRAW;
							task_operate.add(oprate);
						} else {
							continue;
						}
					}
				}
				if (breakFlag) {
					break;
				}
			}
		}
		flowNodeDetailsObj.setNODE_NAME(flowNodeDescObj.getNODE_NAME());
		flowNodeDetailsObj.setNODE_NEXT_DISPOSEMAN(node_next_disposeman);
		flowNodeDetailsObj.setNODE_TYPE(flowNodeDescObj.getNODE_TYPE());
		flowNodeDetailsObj.setNODE_REMARK(flowNodeDescObj.getNODE_REMARK());
		flowNodeDetailsObj.setTASK_ID(taskId);
		flowNodeDetailsObj.setTASK_OPERATE(task_operate);
		flowNodeDetailsObj.setTASK_TRACK(task_track);
		return flowNodeDetailsObj;
	}

	/**
	 * @Title: 提交工单时触发的流程动作,包括FlowConstant类所有操作
	 * @param: taskId:流程任务号
	 * @param: oprate:操作类型，参照FlowConstant类常量说明
	 * @param: nextDisposeMan 下一环节处理人列表,只是派发环节需要指定处理人,其他环节为null,
	 *         派单是人员NextDisposeManObj【userId：用户编号、用户唯一标识，oprate：FlowConstant.FLOW_OPERATE_APPROVAL】
	 * @param: idea 审批意见
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 null：工单提交失败,非null：工单提交成功,返回值中存放工单下一处理人
	 */
	public List<DisposeManObj> triggerSubmitFlow(int taskId, int oprate,
			String idea, List<NextDisposeManObj> nextDisposeMan) {
		if (taskId == 0) {
			return null;
		}
		FlowTaskObj flowTaskObj = new FlowTaskObj();
		flowTaskObj.setTASK_ID(taskId);
		flowTaskObj = workflowEngineDao.queryFlowTask(flowTaskObj);
		String applicationId = flowTaskObj.getPROCESS_SERIALNUMBER();
		int applicationType = flowTaskObj.getPROCESS_ID();
		int currNodeId = flowTaskObj.getNODE_ID();
		String currUserId = flowTaskObj.getDISPOSE_MAN();
		List<DisposeManObj> ret = null;
		if (oprate == FlowConstant.FLOW_OPERATE_NEW) {

		} else if (oprate == FlowConstant.FLOW_OPERATE_APPROVAL) {
			ret = triggerSubmitFlow(applicationId, applicationType, currNodeId,
					currUserId, taskId, idea);
		} else if (oprate == FlowConstant.FLOW_OPERATE_HUNG) {
			ret = triggerHungFlow(taskId);
		} else if (oprate == FlowConstant.FLOW_OPERATE_WITHDRAW) {
			ret = triggerWithdrawFlow(applicationId, applicationType,
					currUserId, currNodeId, taskId);
		} else if (oprate == FlowConstant.FLOW_OPERATE_PLAYEDBACK) {
			ret = triggerPlayedBackFlow(applicationId, applicationType,
					currNodeId, currUserId, taskId, idea);
		} else if (oprate == FlowConstant.FLOW_OPERATE_CANCALHUNG) {
			ret = triggerRecoveryFlow(taskId);
		} else if (oprate == FlowConstant.FLOW_OPERATE_DELETENEW) {
			ret = triggerDeleteNewFlow(applicationId, applicationType);
		} else if (oprate == FlowConstant.FLOW_OPERATE_SENT) {
			if (nextDisposeMan != null && nextDisposeMan.size() > 0) {
				String nextUserId = nextDisposeMan.get(0).getUserId();
				ret = triggerSubmitSentFlow(applicationId, applicationType,
						currNodeId, currUserId, taskId, idea, nextUserId);
			}
		} else if (oprate == FlowConstant.FLOW_OPERATE_CLOSE) {
			ret = triggerCloseFlow(applicationId, applicationType, currNodeId,
					currUserId, taskId, idea);
		}
		flowTaskObj = new FlowTaskObj();
		String disposeMan = "";
		if (ret != null) {// 添加流程当前处理人,流程当前待办人
			for (int i = 0; i < ret.size(); i++) {
				disposeMan = "";
				DisposeManObj obj = (DisposeManObj) ret.get(i);
				flowTaskObj.setPROCESS_ID(applicationType);
				flowTaskObj.setPROCESS_SERIALNUMBER(applicationId);
				List<FlowTaskObj> lst = workflowEngineDao
						.queryProcessToDoDisposeMan(flowTaskObj);
				if (lst != null && lst.size() > 0) {
					for (int j = 0; j < lst.size(); j++) {
						if (disposeMan != null && disposeMan.length() > 0) {
							disposeMan = disposeMan + ",";
						}
						disposeMan = disposeMan + lst.get(j).getDISPOSE_MAN();
					}
				}
				obj.setUserId(disposeMan);
			}
		}
		return ret;
	}

	/**
	 * @Title: 获取节点描述
	 * @prama: applicationType:申请单类型 如上线、下线流程
	 * @prama: nodeId: 工单当前节点
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return FlowNodeDescObj 工单节点描述
	 */
	private FlowNodeDescObj getFlowNodeDesc(int applicationType, int nodeId) {
		return workflowEngineDao.getFlowNodeDesc(applicationType, nodeId);
	}

	/**
	 * @Title: 通过ProcessId以及NEED_NUMBERS获取TASK_ID，NODE_ID
	 * @prama: ProcessId以及NEED_NUMBERS
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 * @return
	 */

	public FlowTaskObj queryWorkflowInfo(FlowTaskObj obj)

	{
		return workflowEngineDao.queryWorkflowInfo(obj);
	}

	public WorkflowEngineDao getWorkflowEngineDao() {
		return workflowEngineDao;
	}

	public void setWorkflowEngineDao(WorkflowEngineDao workflowEngineDao) {
		this.workflowEngineDao = workflowEngineDao;
	}

	@Override
	public List<ProcessInfoObj> getALLFlowList(int applicationType,
			String currUserId,int node_id) {
		if (applicationType <= 0) {
			return null;
		}
		if (currUserId == null || currUserId.equals("")) {
			return null;
		}
		List<ProcessInfoObj> ret = null;
		ProcessInfoObj workflowEngineObj = new ProcessInfoObj();
		workflowEngineObj.setApplicationType(applicationType);
		workflowEngineObj.setUserId(currUserId);
		workflowEngineObj.setProcessNode(node_id);
		return workflowEngineDao.getAllList(workflowEngineObj);
	}
}
