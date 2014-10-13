package com.sitech.basd.tasklist.domain;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysGrpmemberObj;

public class TaskListObj extends BaseObj {

	private String id;// 物理主键
	private Integer task_type;// 任务类型：需求0、工单1、维护2、项目3、其他4
	private String task_content;// 任务内容
	private String responsible_persion;// 责任人，为录入字段
	private String cooperate_persion;// 配合人
	private String plan_complete_date;// 计划完成时间
	private Integer task_status;// 任务状态:完成1，未完成0
	private Integer complete_rate;// 完成比例
	private String complete_date;// 实际完成时间，当选择完成时，必须填写实际完成时间
	private String note;// 备注
	private String submit_date;// 提交时间
	private String user_id;
	private String userName;
	private List<TbSysGrpmemberObj> responPersonList;//指定责任人
	
	public List<TbSysGrpmemberObj> getResponPersonList() {
		return responPersonList;
	}

	public void setResponPersonList(List<TbSysGrpmemberObj> responPersonList) {
		this.responPersonList = responPersonList;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPlan_complete_date() {
		return plan_complete_date;
	}

	public void setPlan_complete_date(String plan_complete_date) {
		this.plan_complete_date = plan_complete_date;
	}

	public String getSubmit_date() {
		return submit_date;
	}

	public void setSubmit_date(String submit_date) {
		this.submit_date = submit_date;
	}

	public String getComplete_date() {
		return complete_date;
	}

	public void setComplete_date(String complete_date) {
		this.complete_date = complete_date;
	}

	private String submit_persion;// 提交人

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getTask_type() {
		return task_type;
	}

	public void setTask_type(Integer task_type) {
		this.task_type = task_type;
	}

	public String getTask_content() {
		return task_content;
	}

	public void setTask_content(String task_content) {
		this.task_content = task_content;
	}

	public String getResponsible_persion() {
		return responsible_persion;
	}

	public void setResponsible_persion(String responsible_persion) {
		this.responsible_persion = responsible_persion;
	}

	public String getCooperate_persion() {
		return cooperate_persion;
	}

	public void setCooperate_persion(String cooperate_persion) {
		this.cooperate_persion = cooperate_persion;
	}

	public Integer getTask_status() {
		return task_status;
	}

	public void setTask_status(Integer task_status) {
		this.task_status = task_status;
	}


	public Integer getComplete_rate() {
		return complete_rate;
	}

	public void setComplete_rate(Integer complete_rate) {
		this.complete_rate = complete_rate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSubmit_persion() {
		return submit_persion;
	}

	public void setSubmit_persion(String submit_persion) {
		this.submit_persion = submit_persion;
	}

}
