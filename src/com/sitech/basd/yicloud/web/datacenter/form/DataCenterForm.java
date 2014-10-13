package com.sitech.basd.yicloud.web.datacenter.form;

public class DataCenterForm {
	private int ID;
	private String NAME;
	private int TYPE;
	private int PARENT_ID;
	private String ENTITY_ID;
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String name) {
		NAME = name;
	}
	public int getTYPE() {
		return TYPE;
	}
	public void setTYPE(int type) {
		TYPE = type;
	}
	public int getPARENT_ID() {
		return PARENT_ID;
	}
	public void setPARENT_ID(int parent_id) {
		PARENT_ID = parent_id;
	}
	public String getENTITY_ID() {
		return ENTITY_ID;
	}
	public void setENTITY_ID(String entity_id) {
		ENTITY_ID = entity_id;
	}
	
}
