package com.sitech.basd.component.tree.domain.task;

public class DeployExampleObj {
	
	private String ID;//数表的id
	
	private String PARENT_ID;//树表的父节点id
	
	private String busiID;//树表的业务id
	
	private int exampleId;//实例id
	
	private String username;//实例用户名
	
	private String password;//实例密码
	
	private String hostip;//实例ip

	public String getPARENT_ID() {
		return PARENT_ID;
	}

	public void setPARENT_ID(String pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public int getExampleId() {
		return exampleId;
	}

	public void setExampleId(int exampleId) {
		this.exampleId = exampleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBusiID() {
		return busiID;
	}

	public void setBusiID(String busiID) {
		this.busiID = busiID;
	}

	public String getHostip() {
		return hostip;
	}

	public void setHostip(String hostip) {
		this.hostip = hostip;
	}
}
