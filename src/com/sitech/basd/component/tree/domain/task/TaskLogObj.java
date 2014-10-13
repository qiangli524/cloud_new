package com.sitech.basd.component.tree.domain.task;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class TaskLogObj extends BaseObj implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private String id; // log id
	private int example_id; // 实例id
	private String example_log;// log
	private String task_id; // 任务编号
	private String order_id;
	private int isSuccess; // 是否成功
	private String insertTime;
	private int isComplete;// 是否完成
	private String IP;// IP地址
	private String exampleName;// 实例名称
	private String exampleID;// 实例ID
	private String is_success;// 是否成功
	private String is_complete;// 是否完成
	private String successCount;// 成功个数
	private String failCount;// 失败个数
	private String failReason;// 失败原因

	public String getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(String successCount) {
		this.successCount = successCount;
	}

	public String getFailCount() {
		return failCount;
	}

	public void setFailCount(String failCount) {
		this.failCount = failCount;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public String getIs_success() {
		return is_success;
	}

	public void setIs_success(String is_success) {
		this.is_success = is_success;
	}

	public String getIs_complete() {
		return is_complete;
	}

	public void setIs_complete(String is_complete) {
		this.is_complete = is_complete;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getExampleID() {
		return exampleID;
	}

	public void setExampleID(String exampleID) {
		this.exampleID = exampleID;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getExampleName() {
		return exampleName;
	}

	public void setExampleName(String exampleName) {
		this.exampleName = exampleName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getExample_id() {
		return example_id;
	}

	public void setExample_id(int example_id) {
		this.example_id = example_id;
	}

	public String getExample_log() {
		return example_log;
	}

	public void setExample_log(String example_log) {
		this.example_log = example_log;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public int getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public int getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(int isComplete) {
		this.isComplete = isComplete;
	}

}
