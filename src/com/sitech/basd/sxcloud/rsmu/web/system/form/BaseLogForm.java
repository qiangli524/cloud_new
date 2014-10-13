package com.sitech.basd.sxcloud.rsmu.web.system.form;

import java.util.List;

public class BaseLogForm {
	private String ID;
	private String IP;
	private String HOSTNAME;
	private String ACCOUNT;
	private String start_time;// 时间 开始
	private String end_time; // 时间 结束
	private List resultList;
	private String resoursePoolObject;
	private String NAME; //用户名称 

	public String getResoursePoolObject() {
		return resoursePoolObject;
	}

	public void setResoursePoolObject(String resoursePoolObject) {
		this.resoursePoolObject = resoursePoolObject;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getACCOUNT() {
		return ACCOUNT;
	}

	public void setACCOUNT(String account) {
		ACCOUNT = account;
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

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getHOSTNAME() {
		return HOSTNAME;
	}

	public void setHOSTNAME(String hOSTNAME) {
		HOSTNAME = hOSTNAME;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}
	
	
}
