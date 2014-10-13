package com.sitech.basd.yicloud.domain.vmman;

public class TbYicloudOsTypeObj {
	private int ID;
	private String TYPE_NAME;
	private String CPU;
	private String MEM;
	private String STORAGE;

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getTYPE_NAME() {
		return TYPE_NAME;
	}

	public void setTYPE_NAME(String type_name) {
		TYPE_NAME = type_name;
	}

	public String getCPU() {
		return CPU;
	}

	public void setCPU(String cpu) {
		CPU = cpu;
	}

	public String getMEM() {
		return MEM;
	}

	public void setMEM(String mem) {
		MEM = mem;
	}

	public String getSTORAGE() {
		return STORAGE;
	}

	public void setSTORAGE(String storage) {
		STORAGE = storage;
	}

}
