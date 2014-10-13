package com.sitech.basd.scheduler.domain.task;

import java.io.Serializable;
import java.util.Date;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class ResourceTaskObj extends BaseObj implements Serializable {

	private static final long serialVersionUID = 1L;

	private String taskId;// 任务ID主键
	private String vhId;// ID
	private String vhName;// 主机名称
	private String vhUuid;// 虚拟机UUID
	private String vhIp;// 虚拟机IP
	private String executeState;// 任务执行状态
	private Date executeStartDate;// 任务执行开始行时间
	private Date executeEndDate;// 任务执行结束行时间
	private String executeCronExpression;// 任务执开始行时间
	private String executeAction;// 任务执行动作类型 0:创建 2:回收 3:开机 4:关机
	private String excludeDate;// 排除的时间
	private String everyType;// 轮询的类型
	private String vmType;// 设备类型
	private String boattr1;// 预留字段1
	private String boattr2;// 预留字段2
	private String boattr3;// 预留字段3
	private String boattr4;// 预留字段4
	private String boattr5;// 预留字段5
	private Date createdDate;// 记录创建时间
	private String createdBy;// 记录创建者
	private Date lastUpdatedDate;// 最后更新时间
	private String lastUpdatedBy;// 最后更新者

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getVhId() {
		return vhId;
	}

	public void setVhId(String vhId) {
		this.vhId = vhId;
	}

	public String getVhName() {
		return vhName;
	}

	public void setVhName(String vhName) {
		this.vhName = vhName;
	}

	public String getVhUuid() {
		return vhUuid;
	}

	public void setVhUuid(String vhUuid) {
		this.vhUuid = vhUuid;
	}

	public String getVhIp() {
		return vhIp;
	}

	public void setVhIp(String vhIp) {
		this.vhIp = vhIp;
	}

	public String getExecuteState() {
		return executeState;
	}

	public void setExecuteState(String executeState) {
		this.executeState = executeState;
	}

	public Date getExecuteStartDate() {
		return executeStartDate;
	}

	public void setExecuteStartDate(Date executeStartDate) {
		this.executeStartDate = executeStartDate;
	}

	public Date getExecuteEndDate() {
		return executeEndDate;
	}

	public void setExecuteEndDate(Date executeEndDate) {
		this.executeEndDate = executeEndDate;
	}

	public String getExecuteCronExpression() {
		return executeCronExpression;
	}

	public void setExecuteCronExpression(String executeCronExpression) {
		this.executeCronExpression = executeCronExpression;
	}

	public String getExecuteAction() {
		return executeAction;
	}

	public void setExecuteAction(String executeAction) {
		this.executeAction = executeAction;
	}

	public String getExcludeDate() {
		return excludeDate;
	}

	public void setExcludeDate(String excludeDate) {
		this.excludeDate = excludeDate;
	}

	public String getEveryType() {
		return everyType;
	}

	public void setEveryType(String everyType) {
		this.everyType = everyType;
	}

	public String getVmType() {
		return vmType;
	}

	public void setVmType(String vmType) {
		this.vmType = vmType;
	}

	public String getBoattr1() {
		return boattr1;
	}

	public void setBoattr1(String boattr1) {
		this.boattr1 = boattr1;
	}

	public String getBoattr2() {
		return boattr2;
	}

	public void setBoattr2(String boattr2) {
		this.boattr2 = boattr2;
	}

	public String getBoattr3() {
		return boattr3;
	}

	public void setBoattr3(String boattr3) {
		this.boattr3 = boattr3;
	}

	public String getBoattr4() {
		return boattr4;
	}

	public void setBoattr4(String boattr4) {
		this.boattr4 = boattr4;
	}

	public String getBoattr5() {
		return boattr5;
	}

	public void setBoattr5(String boattr5) {
		this.boattr5 = boattr5;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

}
