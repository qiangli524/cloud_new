package com.sitech.basd.cloud3.web.message.form;

import java.util.List;

public class AlarmSubscribeForm {
	private String id;
	private String alarmuserid;
	private String sendstyle;
	private String subscribe;
	private String alarmlevel;
	private String objid;
	private String objname;
	private String sendmode;
	private String sendtime1;
	private String sendtime2;
	private String unsendtime1;
	private String unsendtime2;
	private List busiList;
	private List subscribeList;
	private List userList;

	public String getObjname() {
		return objname;
	}

	public void setObjname(String objname) {
		this.objname = objname;
	}

	public List getUserList() {
		return userList;
	}

	public void setUserList(List userList) {
		this.userList = userList;
	}

	public List getSubscribeList() {
		return subscribeList;
	}

	public void setSubscribeList(List subscribeList) {
		this.subscribeList = subscribeList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlarmuserid() {
		return alarmuserid;
	}

	public void setAlarmuserid(String alarmuserid) {
		this.alarmuserid = alarmuserid;
	}

	public String getSendstyle() {
		return sendstyle;
	}

	public void setSendstyle(String sendstyle) {
		this.sendstyle = sendstyle;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	public String getAlarmlevel() {
		return alarmlevel;
	}

	public void setAlarmlevel(String alarmlevel) {
		this.alarmlevel = alarmlevel;
	}

	public String getObjid() {
		return objid;
	}

	public void setObjid(String objid) {
		this.objid = objid;
	}

	public String getSendmode() {
		return sendmode;
	}

	public void setSendmode(String sendmode) {
		this.sendmode = sendmode;
	}

	public String getSendtime1() {
		return sendtime1;
	}

	public void setSendtime1(String sendtime1) {
		this.sendtime1 = sendtime1;
	}

	public String getSendtime2() {
		return sendtime2;
	}

	public void setSendtime2(String sendtime2) {
		this.sendtime2 = sendtime2;
	}

	public String getUnsendtime1() {
		return unsendtime1;
	}

	public void setUnsendtime1(String unsendtime1) {
		this.unsendtime1 = unsendtime1;
	}

	public String getUnsendtime2() {
		return unsendtime2;
	}

	public void setUnsendtime2(String unsendtime2) {
		this.unsendtime2 = unsendtime2;
	}

	public List getBusiList() {
		return busiList;
	}

	public void setBusiList(List busiList) {
		this.busiList = busiList;
	}

}
