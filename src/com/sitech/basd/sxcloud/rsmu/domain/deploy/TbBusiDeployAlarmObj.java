package com.sitech.basd.sxcloud.rsmu.domain.deploy;

import java.io.Serializable;
import java.sql.Timestamp;
import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbBusiDeployAlarmObj  extends BaseObj implements Serializable, Cloneable{
    
	/*
	 * 异常告警 
	 */
	private int ID      ;         //'编码',
	private int APPID   ;         //'应用编号',
	private int HOSTID  ;         //'主机编号',
	private Timestamp INSERTTIME; //'告警时间',
	private String COMMAND;       //'操作命令',
	private String RESULT ;       //'操作结果',
	private int ALARM_LEVEL;            //'1：严重告警2：主要告警3：次要告警4：警告告警5：不确定告警
	private String start_time;//告警时间 开始
    private String end_time;  //告警时间 结束 
    private String APPIDS; //同一业务中心下业务系统id的集合
    
	public String getAPPIDS() {
		return APPIDS;
	}
	public void setAPPIDS(String appids) {
		APPIDS = appids;
	}
	public int getAPPID() {
		return APPID;
	}
	public void setAPPID(int appid) {
		APPID = appid;
	}
	public String getCOMMAND() {
		return COMMAND;
	}
	public void setCOMMAND(String command) {
		COMMAND = command;
	}
	public int getHOSTID() {
		return HOSTID;
	}
	public void setHOSTID(int hostid) {
		HOSTID = hostid;
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public Timestamp getINSERTTIME() {
		return INSERTTIME;
	}
	public void setINSERTTIME(Timestamp inserttime) {
		INSERTTIME = inserttime;
	}
	
	public int getALARM_LEVEL() {
		return ALARM_LEVEL;
	}
	public void setALARM_LEVEL(int alarm_level) {
		ALARM_LEVEL = alarm_level;
	}
	public String getRESULT() {
		return RESULT;
	}
	public void setRESULT(String result) {
		RESULT = result;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

}
