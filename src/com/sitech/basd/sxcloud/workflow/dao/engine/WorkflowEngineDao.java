package com.sitech.basd.sxcloud.workflow.dao.engine;

import java.util.List;

import com.sitech.basd.sxcloud.workflow.domain.engine.DisposeManObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowNodeDescObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowRuleObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowTaskObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.ProcessInfoObj;

public interface WorkflowEngineDao {
	/**
	 * @Title:新建工作流任务
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 -1：推动失败,正数：推动成功
	 */
	public int insertFlowTask(FlowTaskObj flowTaskObj);

	/**
	 * @Title: 更新工作流任务状态
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 -1：更新任务失败,正数：更新成功
	 */
	public int updateFlowTask(FlowTaskObj flowTaskObj);

	/**
	 * @Title: 挂起工作流任务
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return 返回值 -1：挂起任务失败,正数：挂起成功
	 */
	public int updateFlowTaskHungStatus(int taskId, int oprare);

	/**
	 * @Title: 获取某流程环节处理人集合
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<DisposeManObj> getProcessNodeDispose(DisposeManObj disposeManObj);

	/**
	 * @Title:获取工单草稿列表
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<ProcessInfoObj> getDraftList(ProcessInfoObj processInfoObj);

	/**
	 * @Title:获取工单待办列表
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<ProcessInfoObj> getToDoList(ProcessInfoObj processInfoObj);
	
	/**
	 * 或去工单任务表的所有信息，，node_id=3
	  
	* getAllList(这里用一句话描述这个方法的作用)    
	  
	* @param   name   
	  
	* @param  @return    设定文件   
	  
	* @return String    DOM对象   
	  
	* @Exception 异常对象   
	  
	* @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<ProcessInfoObj> getAllList(ProcessInfoObj processInfoObj);

	/**
	 * @Title:获取工单已办列表
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<ProcessInfoObj> getAlreadyDo(ProcessInfoObj processInfoObj);

	/**
	 * @Title:获取工单打回列表
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<ProcessInfoObj> getPlayedBack(ProcessInfoObj processInfoObj);

	/**
	 * @Title:获取工单挂起列表
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<ProcessInfoObj> getHungList(ProcessInfoObj processInfoObj);

	/**
	 * @Title:获取工单起始节点编号
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int getProcessStartNodeId(int applicationType);

	/**
	 * @Title:删除未发起的新建工单任务
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteNewFlowTask(String applicationId, int currNodeId,
			int applicationType);

	/**
	 * @Title:获取草稿工单任务号
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int getCacheFlowTaskId(FlowTaskObj flowTaskObj);

	/**
	 * @Title:获取工单任务号
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public FlowTaskObj queryFlowTask(FlowTaskObj flowTaskObj);

	/**
	 * @Title:获取工单上下流转规则
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<FlowRuleObj> getProcessFlowRule(FlowRuleObj flowRuleObj);

	/**
	 * @Title:获取下一节点已处理任务单
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List getNextNodeAlreadyDoTask(int taskId);

	/**
	 * @Title:删除下一节点未处理任务
	 * @param:taskId 当前任务号
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteNextNodeFlowTask(int taskId);

	/**
	 * @Title:获取工单任务处理轨迹
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<FlowTaskObj> getProcessTaskTrack(FlowTaskObj flowTaskObj);

	/**
	 * @Title:获取工单节点历史处理人
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<DisposeManObj> getProcessNodeHisDisposeMan(
			String process_serialnumber, int process_id, int node_id);

	/**
	 * @Title:获取流程发起人
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<DisposeManObj> getStartProcessDisposeMan(int process_id,
			String userId);

	/**
	 * @Title:获取工单节点描述
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @param: processId 流程类型
	 * @param: nodeId 流程节点编号
	 */
	public FlowNodeDescObj getFlowNodeDesc(int processId, int nodeId);

	/**
	 * @Title:获取流程当前处理人,流程当前待办人
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List<FlowTaskObj> queryProcessToDoDisposeMan(FlowTaskObj flowTaskObj);

	/**
	 * @Title: 通过ProcessId以及NEED_NUMBERS获取TASK_ID，NODE_ID
	 * @prama: ProcessId以及NEED_NUMBERS
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 * @return
	 */

	public FlowTaskObj queryWorkflowInfo(FlowTaskObj obj);
}
