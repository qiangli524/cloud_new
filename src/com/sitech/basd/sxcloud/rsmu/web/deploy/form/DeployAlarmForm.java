package com.sitech.basd.sxcloud.rsmu.web.deploy.form;

import java.sql.Timestamp;
import java.util.List;


public class DeployAlarmForm {

	/*
	 * 异常告警 
	 */
	private int ID      ;         //'编码',
	private int APPID   ;         //'应用编号',
	private int HOSTID  ;         //'主机编号',
	private Timestamp INSERTTIME; //'告警时间',
	private String COMMAND;       //'操作命令',
	private String RESULT ;       //'操作结果',
	private int LEVEL;            //'1：严重告警2：主要告警3：次要告警4：警告告警5：不确定告警
	private String start_time;//告警时间 开始
    private String end_time;  //告警时间 结束
    @SuppressWarnings("unchecked")
	private List resultList  = null ;
    
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
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
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
	public int getLEVEL() {
		return LEVEL;
	}
	public void setLEVEL(int level) {
		LEVEL = level;
	}
	public String getRESULT() {
		return RESULT;
	}
	public void setRESULT(String result) {
		RESULT = result;
	}
	@SuppressWarnings("unchecked")
	public List getResultList() {
		return resultList;
	}
	@SuppressWarnings("unchecked")
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
    
}
