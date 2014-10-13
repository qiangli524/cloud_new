package com.sitech.basd.sxcloud.workflow.dao.engine;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.workflow.domain.engine.DisposeManObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowNodeDescObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowRuleObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowTaskObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.ProcessInfoObj;


public class WorkflowEngineDaoImpl extends BaseDao implements WorkflowEngineDao {
	/**
	 * @Title:新建工作流任务
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 -1：推动失败,正数：推动成功
	 */
	public int insertFlowTask(FlowTaskObj flowTaskObj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("workflowEngine.insertFlowTask",
					flowTaskObj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}

	/**
	 * @Title: 更新工作流任务状态
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 -1：更新任务失败,正数：更新成功
	 */
	public int updateFlowTask(FlowTaskObj flowTaskObj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("workflowEngine.updateFlowTask",
					flowTaskObj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			return -1;
		}
		return ret;
	}

	/**
	 * @Title: 挂起工作流任务
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 -1：挂起任务失败,正数：挂起成功
	 */
	public int updateFlowTaskHungStatus(int taskId, int oprare) {
		int ret = 0;
		try {
			FlowTaskObj obj = new FlowTaskObj();
			obj.setTASK_ID(taskId);
			obj.setTASK_OPERATE(oprare);
			Object o = getSqlMap().update(
					"workflowEngine.updateFlowTaskHungStatus", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			return -1;
		}
		return ret;
	}

	/**
	 * @Title: 获取某流程环节处理人集合
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<DisposeManObj> getProcessNodeDispose(DisposeManObj disposeManObj) {
		List list = null;
		try {
			list = getSqlMap().queryForList(
					"workflowEngine.getProcessNodeDispose", disposeManObj);
		} catch (Exception e) {
			LogHelper.error("workflowEngine.getProcessNodeDispose:"
					+ e.getMessage() + getClass().getName());
			return null;
		}
		return list;
	}

	/**
	 * @Title:获取工单草稿列表
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<ProcessInfoObj> getDraftList(ProcessInfoObj processInfoObj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("workflowEngine.getDraftList",
					processInfoObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("workflowEngine.getDraftList:" + e.getMessage()
					+ getClass().getName());
			return null;
		}
		return list;
	}

	/**
	 * @Title:获取工单待办列表
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<ProcessInfoObj> getToDoList(ProcessInfoObj processInfoObj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("workflowEngine.getToDoList",
					processInfoObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("workflowEngine.getToDoList:" + e.getMessage()
					+ getClass().getName());
			return null;
		}
		return list;
	}

	/**
	 * @Title:获取工单已办列表
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<ProcessInfoObj> getAlreadyDo(ProcessInfoObj processInfoObj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("workflowEngine.getAlreadyDo",
					processInfoObj);
		} catch (Exception e) {
			LogHelper.error("workflowEngine.getAlreadyDo:" + e.getMessage()
					+ getClass().getName());
			return null;
		}
		return list;
	}

	/**
	 * @Title:获取工单打回列表
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<ProcessInfoObj> getPlayedBack(ProcessInfoObj processInfoObj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("workflowEngine.getPlayedBack",
					processInfoObj);
		} catch (Exception e) {
			LogHelper.error("workflowEngine.getPlayedBack:" + e.getMessage()
					+ getClass().getName());
			return null;
		}
		return list;
	}

	/**
	 * @Title:获取工单挂起列表
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<ProcessInfoObj> getHungList(ProcessInfoObj processInfoObj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("workflowEngine.getHungList",
					processInfoObj);
		} catch (Exception e) {
			LogHelper.error("workflowEngine.getHungList:" + e.getMessage()
					+ getClass().getName());
			return null;
		}
		return list;
	}

	/**
	 * @Title:获取工单起始节点编号
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int getProcessStartNodeId(int applicationType) {
		int ret = 0;
		try {
			ret = (Integer) getSqlMap().queryForObject(
					"workflowEngine.getProcessStartNodeId", applicationType);
		} catch (Exception e) {
			LogHelper.error("workflowEngine.getProcessStartNodeId:"
					+ e.getMessage() + getClass().getName());
			return -1;
		}
		return ret;
	}

	/**
	 * @Title:删除未发起的新建工单任务
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteNewFlowTask(String applicationId, int processNode,
			int applicationType) {
		int ret = 0;
		try {
			FlowTaskObj flowTaskObj = new FlowTaskObj();
			flowTaskObj.setNODE_ID(processNode);
			flowTaskObj.setPROCESS_SERIALNUMBER(applicationId);
			flowTaskObj.setPROCESS_ID(applicationType);
			ret = (Integer) getSqlMap().delete(
					"workflowEngine.deleteNewFlowTask", flowTaskObj);
		} catch (Exception e) {
			LogHelper.error("workflowEngine.deleteNewFlowTask:"
					+ e.getMessage() + getClass().getName());
			return -1;
		}
		return ret;
	}

	/**
	 * @Title:获取草稿工单任务号
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int getCacheFlowTaskId(FlowTaskObj flowTaskObj) {
		int ret = 0;
		try {
			String o = (String) getSqlMap().queryForObject(
					"workflowEngine.getCacheFlowTaskId", flowTaskObj);
			if (o == null || o.equals("")) {
				return -1;
			}
			ret = Integer.parseInt(o);
		} catch (Exception e) {
			LogHelper.error("workflowEngine.getCacheFlowTaskId:"
					+ e.getMessage() + getClass().getName());
			return -1;
		}
		return ret;
	}

	/**
	 * @Title:获取工单任务号
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public FlowTaskObj queryFlowTask(FlowTaskObj flowTaskObj) {
		FlowTaskObj ret = null;
		try {
			ret = (FlowTaskObj) getSqlMap().queryForObject(
					"workflowEngine.queryFlowTask", flowTaskObj);
		} catch (Exception e) {
			LogHelper.error("workflowEngine.queryFlowTask:" + e.getMessage()
					+ getClass().getName());
			return null;
		}
		return ret;
	}

	/**
	 * @Title:获取工单上下流转规则
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<FlowRuleObj> getProcessFlowRule(FlowRuleObj flowRuleObj) {
		List ret = null;
		try {
			ret = getSqlMap().queryForList("workflowEngine.getProcessFlowRule",
					flowRuleObj);
		} catch (Exception e) {
			LogHelper.error("workflowEngine.getProcessFlowRule:"
					+ e.getMessage() + getClass().getName());
			return null;
		}
		return ret;
	}

	/**
	 * @Title:获取下一节点已处理任务单
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List getNextNodeAlreadyDoTask(int taskId) {
		List list = null;
		FlowTaskObj flowTaskObj = new FlowTaskObj();
		flowTaskObj.setPER_TASK_ID(taskId);
		try {
			list = getSqlMap().queryForList(
					"workflowEngine.getNextNodeAlreadyDoTask", flowTaskObj);
		} catch (Exception e) {
			LogHelper.error("workflowEngine.getNextNodeAlreadyDoTask:"
					+ e.getMessage() + getClass().getName());
			return null;
		}
		return list;
	}

	/**
	 * @Title:删除下一节点未处理任务
	 * @param:taskId 当前任务号
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteNextNodeFlowTask(int taskId) {
		int ret = 0;
		try {
			FlowTaskObj flowTaskObj = new FlowTaskObj();
			flowTaskObj.setPER_TASK_ID(taskId);
			ret = (Integer) getSqlMap().delete(
					"workflowEngine.deleteNextNodeFlowTask", flowTaskObj);
		} catch (Exception e) {
			LogHelper.error("workflowEngine.deleteNextNodeFlowTask:"
					+ e.getMessage() + getClass().getName());
			return -1;
		}
		return ret;
	}

	/**
	 * @Title:获取工单任务处理轨迹
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<FlowTaskObj> getProcessTaskTrack(FlowTaskObj flowTaskObj) {
		List list = null;
		try {
			list = getSqlMap().queryForList(
					"workflowEngine.getProcessTaskTrack", flowTaskObj);
		} catch (Exception e) {
			LogHelper.error("workflowEngine.getProcessTaskTrack:"
					+ e.getMessage() + getClass().getName());
			return null;
		}
		return list;
	}

	/**
	 * @Title:获取工单节点历史处理人
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<DisposeManObj> getProcessNodeHisDisposeMan(
			String process_serialnumber, int process_id, int node_id) {
		FlowTaskObj flowTaskObj = new FlowTaskObj();
		flowTaskObj.setPROCESS_ID(process_id);
		flowTaskObj.setNODE_ID(node_id);
		flowTaskObj.setPROCESS_SERIALNUMBER(process_serialnumber);
		List list = null;
		try {
			list = getSqlMap().queryForList(
					"workflowEngine.getProcessNodeHisDisposeMan", flowTaskObj);
		} catch (Exception e) {
			LogHelper.error("workflowEngine.getProcessNodeHisDisposeMan:"
					+ e.getMessage() + getClass().getName());
			return null;
		}
		return list;
	}

	/**
	 * @Title:获取流程发起人
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<DisposeManObj> getStartProcessDisposeMan(int process_id,
			String userId) {
		DisposeManObj disposeManObj = new DisposeManObj();
		disposeManObj.setApplicationType(process_id);
		disposeManObj.setUserId(userId);
		List list = null;
		try {
			list = getSqlMap().queryForList(
					"workflowEngine.getStartProcessDisposeMan", disposeManObj);
		} catch (Exception e) {
			LogHelper.error("workflowEngine.getStartProcessDisposeMan:"
					+ e.getMessage() + getClass().getName());
			return null;
		}
		return list;
	}

	/**
	 * @Title:获取工单节点描述
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @param: processId 流程类型
	 * @param: nodeId 流程节点编号
	 */
	public FlowNodeDescObj getFlowNodeDesc(int processId, int nodeId) {
		FlowNodeDescObj ret = null;
		FlowNodeDescObj flowNodeDescObj = new FlowNodeDescObj();
		flowNodeDescObj.setNODE_ID(nodeId);
		flowNodeDescObj.setPROCESS_ID(processId);
		try {
			ret = (FlowNodeDescObj) getSqlMap().queryForObject(
					"workflowEngine.getFlowNodeDesc", flowNodeDescObj);
		} catch (Exception e) {
			LogHelper.error("workflowEngine.queryFlowTask:" + e.getMessage()
					+ getClass().getName());
			return null;
		}
		return ret;
	}

	/**
	 * @Title:获取流程当前处理人,流程当前待办人
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<FlowTaskObj> queryProcessToDoDisposeMan(FlowTaskObj flowTaskObj) {
		List ret = null;
		try {
			ret = getSqlMap().queryForList(
					"workflowEngine.queryProcessToDoDisposeMan", flowTaskObj);
		} catch (Exception e) {
			LogHelper.error("workflowEngine.queryProcessToDoDisposeMan:"
					+ e.getMessage() + getClass().getName());
			return null;
		}
		return ret;
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

	public FlowTaskObj queryWorkflowInfo(FlowTaskObj obj) {
		FlowTaskObj fObj = new FlowTaskObj();
		try {
			fObj = (FlowTaskObj) getSqlMap().queryForObject(
					"workflowEngine.queryWorkflowInfo", obj);
		} catch (Exception e) {
			LogHelper.error("workflowEngine.queryWorkflowInfo:"
					+ e.getMessage() + getClass().getName());
			return null;
		}
		return fObj;

	}

	@Override
	public List<ProcessInfoObj> getAllList(ProcessInfoObj processInfoObj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("workflowEngine.getAllList",
					processInfoObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("workflowEngine.getAllList:" + e.getMessage()
					+ getClass().getName());
			return null;
		}
		return list;
	}
}
