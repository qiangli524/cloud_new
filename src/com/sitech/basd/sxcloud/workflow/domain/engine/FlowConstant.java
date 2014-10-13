/**
 * @Title:公用常量
 * @Copyright: Copyright (c) 201006
 * @Company: si-tech
 * @author zengls
 * @version 1.0
 */
package com.sitech.basd.sxcloud.workflow.domain.engine;

public class FlowConstant {
	// ************************************流程操作权限**********************************************/
	public static final int FLOW_OPERATE_NEW = 0; // 新建工单操作权限
	public static final int FLOW_OPERATE_APPROVAL = 1; // 审批操作权限
	public static final int FLOW_OPERATE_HUNG = 2; // 挂起操作权限
	public static final int FLOW_OPERATE_WITHDRAW = 3; // 撤回操作权限
	public static final int FLOW_OPERATE_PLAYEDBACK = 4; // 打回操作权限
	public static final int FLOW_OPERATE_CANCALHUNG = 5; // 取消挂起操作权限
	public static final int FLOW_OPERATE_DELETENEW = 6; // 删除新建工单操作权限
	public static final int FLOW_OPERATE_SAVENEW = 7; // 保存新建工单操作权限
	public static final int FLOW_OPERATE_SENT = 8; // 派单工单操作权限
	public static final int FLOW_OPERATE_COMMITNEW = 9; // 保存新建工单操作权限
	public static final int FLOW_OPERATE_CLOSE = 10; // 流程结束操作权限

	// *************************************节点类型*********************************************/

	public static final int FLOW_NODE_START = 1;// 开始节点
	public static final int FLOW_NODE_GENERAL = 2;// 普通节点
	public static final int FLOW_NODE_ROB = 3;// 抢单节点
	public static final int FLOW_NODE_COOPERATESTART = 4;// 协同开始节点
	public static final int FLOW_NODE_COOPERATEEND_GENERAL = 5;// 协同结束节点&普通节点
	public static final int FLOW_NODE_COOPERATEEND_ROB = 6;// 协同结束节点&抢单节点
	public static final int FLOW_NODE_COOPERATEEND_COOPERATESTART = 7;// 协同结束节点&协同开始节点
	public static final int FLOW_NODE_SENT = 8;// 派单节点
	public static final int FLOW_NODE_END = 100;// 结束【4、5成对出现】

	// **************************************任务状态********************************************/

	public static final int FLOW_TASK_UNDISPOSE = 1;// 任务状态 等待处理
	public static final int FLOW_TASK_DISPOSE = 2; // 任务状态 已处理

	// /
	public static final String FLOW_TYPE_DRAFT = "FLOW_TYPE_DRAFT"; // 草稿列表
	public static final String FLOW_TYPE_TODOLIST = "FLOW_TYPE_TODOLIST"; // 待办列表
	public static final String FLOW_TYPE_ALREADYDO = "FLOW_TYPE_ALREADYDO";// 已办列表
	public static final String FLOW_TYPE_PLAYEDBACK = "FLOW_TYPE_PLAYEDBACK";// 被打回列表
	public static final String FLOW_TYPE_HUNGLIST = "FLOW_TYPE_HUNGLIST"; // 挂起列表
}