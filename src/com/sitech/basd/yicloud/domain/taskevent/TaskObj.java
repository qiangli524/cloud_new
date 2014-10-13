package com.sitech.basd.yicloud.domain.taskevent;

public class TaskObj {
	private int id;
	private String name;// 名称
	private String type;// 类型
	private String task_uuid;
	private String task_name;
	private String task_state;
	private String start_time;
	private String end_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTask_uuid() {
		return task_uuid;
	}

	public void setTask_uuid(String task_uuid) {
		this.task_uuid = task_uuid;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public String getTask_state() {
		return task_state;
	}

	public void setTask_state(String task_state) {
		this.task_state = task_state;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

}
