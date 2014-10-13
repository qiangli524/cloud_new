package com.sitech.basd.sxcloud.cloud.domain.sxworkorder;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>
 * Title: WorkOrderObj
 * </p>
 * <p>
 * Description: bomc工单实体
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-8-22 下午1:11:30
 * 
 */
public class WorkOrderObj extends BaseObj {

	// 工单表
	private String uuid;// 工单编号
	private Integer type;// 工单类型：0申请，1调整，2回收
	private String userName;// 工单申请人：
	private String requestDoneTime;// 工单要求完成时间
	private String receiveTime;// 工单接收时间
	private String exetime;// 工单处理时间
	private String cameFrom;// 工单来源
	private String requestType;// 需求类型
	private String addReason;// 新增原因
	private String expansionReason;// 现网扩容原因
	private String currSituation;// 现网运行情况
	private String IsInPlan;// 是否计划内
	private String IsInvest;// 是否投资类
	private String state;// 工单是否正常0 正常工单 1异常工单
	private String dealState;// 工单处理状态 0未处理 1已处理

	// 工单任务表
	// private String ID;// 主键
	// private Integer CPU_NUM;// cpu数量
	// private Integer MEM_SIZE;// 内存大小
	// private Double SR_SIZE;// 存储大小
	// private String NETWORK_ID;// 网络域id
	// private String TEMPLATE_ID;// connect_id + "_" +　templatecode
	// private String TEMPLATE_TYPE;// 模板类型 HOST/VM
	// private String WORKORDER_ID;// 工单表uuid
	// private Integer STATUS;// 状态 0 待处理 1处理中 2处理成功 3处理失败
	// private Integer DEAL_COUNT;// 处理次数
	// private String ENTITY_ID;// 实体id，(虚拟机或主机id)
	// private String DEALTIME;// 处理时间
	// private String VM_NAME;// bomc虚拟机名称
	// private String IPADDRESS;// 手动执行时，任务制定的ip
	// private String MESSAGE;// 信息
	// private String HOST_NAME;// 主机名
	//
	// private String PROJECT_NAME;// 项目名称
	// private String NETWORK_NAME;// 网络名称
	private Integer task_count;// 任务总数
	private Integer task_deal_count;// 任务处理中个数
	private Integer task_complete_count;// 任务处理完成个数
	private Integer task_wait_count;// 待处理任务个数

	// private String TEMPLATENAME;// 模板名称
	// private String PROJECT_USER_NAME;// 项目责任人名称
	// private String SHOWNAME;// 申请人显示名称
	// private String BUSISYSTEMNAME;// 工单所属业务系统名称

	public String getRequestDoneTime() {
		return requestDoneTime;
	}

	public Integer getTask_count() {
		return task_count;
	}

	public void setTask_count(Integer task_count) {
		this.task_count = task_count;
	}

	public Integer getTask_deal_count() {
		return task_deal_count;
	}

	public void setTask_deal_count(Integer task_deal_count) {
		this.task_deal_count = task_deal_count;
	}

	public Integer getTask_complete_count() {
		return task_complete_count;
	}

	public void setTask_complete_count(Integer task_complete_count) {
		this.task_complete_count = task_complete_count;
	}

	public Integer getTask_wait_count() {
		return task_wait_count;
	}

	public void setTask_wait_count(Integer task_wait_count) {
		this.task_wait_count = task_wait_count;
	}

	public String getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(String cameFrom) {
		this.cameFrom = cameFrom;
	}

	public void setRequestDoneTime(String requestDoneTime) {
		this.requestDoneTime = requestDoneTime;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getExetime() {
		return exetime;
	}

	public void setExetime(String exetime) {
		this.exetime = exetime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDealState() {
		return dealState;
	}

	public void setDealState(String dealState) {
		this.dealState = dealState;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getAddReason() {
		return addReason;
	}

	public void setAddReason(String addReason) {
		this.addReason = addReason;
	}

	public String getExpansionReason() {
		return expansionReason;
	}

	public void setExpansionReason(String expansionReason) {
		this.expansionReason = expansionReason;
	}

	public String getCurrSituation() {
		return currSituation;
	}

	public void setCurrSituation(String currSituation) {
		this.currSituation = currSituation;
	}

	public String getIsInPlan() {
		return IsInPlan;
	}

	public void setIsInPlan(String isInPlan) {
		IsInPlan = isInPlan;
	}

	public String getIsInvest() {
		return IsInvest;
	}

	public void setIsInvest(String isInvest) {
		IsInvest = isInvest;
	}

}
