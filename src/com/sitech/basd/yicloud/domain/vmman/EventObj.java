package com.sitech.basd.yicloud.domain.vmman;

/**
 * 
 * <p>
 * Title: EventObj
 * </p>
 * <p>
 * Description: 事件接口信息类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Sep 4, 2012 2:33:30 PM
 * 
 */
public class EventObj {
	private String DESC;// 事件描述
	private String TYPE;// 类型
	private String DATE;// 日期时间
	private String TASk;// 任务
	private String TARGET;// 目标
	private String USER;// 用户

	public String getDESC() {
		return DESC;
	}

	public void setDESC(String desc) {
		DESC = desc;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
	}

	public String getDATE() {
		return DATE;
	}

	public void setDATE(String date) {
		DATE = date;
	}

	public String getTASk() {
		return TASk;
	}

	public void setTASk(String sk) {
		TASk = sk;
	}

	public String getTARGET() {
		return TARGET;
	}

	public void setTARGET(String target) {
		TARGET = target;
	}

	public String getUSER() {
		return USER;
	}

	public void setUSER(String user) {
		USER = user;
	}

}
