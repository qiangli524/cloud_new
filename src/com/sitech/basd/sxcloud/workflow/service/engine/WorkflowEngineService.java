package com.sitech.basd.sxcloud.workflow.service.engine;

import java.util.List;

import com.sitech.basd.sxcloud.workflow.domain.engine.DisposeManObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowNodeDetailsObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowTaskObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.NextDisposeManObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.ProcessInfoObj;

public interface WorkflowEngineService {

	/**
	 * @Title: 判断当前流程是否可以撤回
	 * @prama: taskId:当前工单任务号
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return true：可以撤回 ,false:不能撤回
	 */

	public boolean canWithdraw(int taskId);

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
	public boolean canCreateNewApplication(int applicationType, String userId);

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
			String currUserId, String listType);
	
	/**
	 * 获取所有的任务流信息
	  
	* getALLFlowList(这里用一句话描述这个方法的作用)    
	  
	* @param   name   
	  
	* @param  @return    设定文件   
	  
	* @return String    DOM对象   
	  
	* @Exception 异常对象   
	  
	* @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<ProcessInfoObj> getALLFlowList(int applicationType,
			String currUserId,int node_id);

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
			int applicationType, String currUserId);

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
			int applicationType, String currUserId);

	/**
	 * @Title: 获取当前任务详情,包括：任务编号、任务轨迹、当前任务信息【节点名称、节点备注、节点处理权限、节点下一处理人】
	 * @prama: taskId:流程任务号
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 * @return FlowNodeDetailsObj 任务详情
	 */
	public FlowNodeDetailsObj getFlowNodeDetails(int taskId);

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
			String idea, List<NextDisposeManObj> nextDisposeMan);

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
