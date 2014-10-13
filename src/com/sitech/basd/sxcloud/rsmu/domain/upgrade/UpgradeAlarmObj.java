package com.sitech.basd.sxcloud.rsmu.domain.upgrade;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class UpgradeAlarmObj extends BaseObj implements Serializable, Cloneable {

	private int ID; // '编码',
	private int APPID; // '应用编号',
	private int HOSTID; // '主机编号',
	private String INSERTTIME; // '告警时间',
	private String COMMAND; // '操作命令',
	private String RESULT; // '操作结果',
	private int ALARM_LEVEL; // '1：严重告警2：主要告警3：次要告警4：警告告警5：不确定告警
	private String start_time;// 告警时间 开始
	private String end_time; // 告警时间 结束

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public int getAPPID() {
		return APPID;
	}

	public void setAPPID(int appid) {
		APPID = appid;
	}

	public int getHOSTID() {
		return HOSTID;
	}

	public void setHOSTID(int hostid) {
		HOSTID = hostid;
	}

	public String getINSERTTIME() {
		return INSERTTIME;
	}

	public void setINSERTTIME(String inserttime) {
		INSERTTIME = inserttime;
	}

	public String getCOMMAND() {
		return COMMAND;
	}

	public void setCOMMAND(String command) {
		COMMAND = command;
	}

	public String getRESULT() {
		return RESULT;
	}

	public void setRESULT(String result) {
		RESULT = result;
	}

	public int getALARM_LEVEL() {
		return ALARM_LEVEL;
	}

	public void setALARM_LEVEL(int alarm_level) {
		ALARM_LEVEL = alarm_level;
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
