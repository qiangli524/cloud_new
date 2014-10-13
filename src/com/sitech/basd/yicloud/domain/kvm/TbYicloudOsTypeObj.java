package com.sitech.basd.yicloud.domain.kvm;

public class TbYicloudOsTypeObj {
	private int ID;
	private String TYPE_NAME;
	private String CPU;
	private String MEM;
	private String STORAGE;
	private String OS_TYPE;// 1：Windows 2:Linux 3:其他
	private String TYPE_ID;// 类型标示
	private String VIR_TYPE;// 虚拟化类型

	public String getVIR_TYPE() {
		return VIR_TYPE;
	}

	public void setVIR_TYPE(String vir_type) {
		VIR_TYPE = vir_type;
	}

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

	public String getOS_TYPE() {
		return OS_TYPE;
	}

	public void setOS_TYPE(String os_type) {
		OS_TYPE = os_type;
	}

	public String getTYPE_ID() {
		return TYPE_ID;
	}

	public void setTYPE_ID(String type_id) {
		TYPE_ID = type_id;
	}

}
