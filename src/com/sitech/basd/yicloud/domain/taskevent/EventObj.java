package com.sitech.basd.yicloud.domain.taskevent;

public class EventObj {
	private int id;
	private String name;
	private String type;
	private String event_uuid;
	private String event_time;
	private String event_user;
	private String event_desc;

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

	public String getEvent_uuid() {
		return event_uuid;
	}

	public void setEvent_uuid(String event_uuid) {
		this.event_uuid = event_uuid;
	}

	public String getEvent_time() {
		return event_time;
	}

	public void setEvent_time(String event_time) {
		this.event_time = event_time;
	}

	public String getEvent_user() {
		return event_user;
	}

	public void setEvent_user(String event_user) {
		this.event_user = event_user;
	}

	public String getEvent_desc() {
		return event_desc;
	}

	public void setEvent_desc(String event_desc) {
		this.event_desc = event_desc;
	}

}
