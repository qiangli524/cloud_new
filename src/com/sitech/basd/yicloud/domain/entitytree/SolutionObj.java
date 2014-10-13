package com.sitech.basd.yicloud.domain.entitytree;

public class SolutionObj {
	private String TEM_ID;
	private String TEM_NAME;
	private String TEM_DESC;
	private String CPU;
	private String MEM;
	private String STORAGE;
	private String TYPE;
	private String CLONE_NUM;
	private int VIDEOID;

	public int getVIDEOID() {
		return VIDEOID;
	}

	public void setVIDEOID(int videoid) {
		VIDEOID = videoid;
	}

	public String getCLONE_NUM() {
		return CLONE_NUM;
	}

	public void setCLONE_NUM(String clone_num) {
		CLONE_NUM = clone_num;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
	}

	public String getTEM_ID() {
		return TEM_ID;
	}

	public void setTEM_ID(String tem_id) {
		TEM_ID = tem_id;
	}

	public String getTEM_NAME() {
		return TEM_NAME;
	}

	public void setTEM_NAME(String tem_name) {
		TEM_NAME = tem_name;
	}

	public String getTEM_DESC() {
		return TEM_DESC;
	}

	public void setTEM_DESC(String tem_desc) {
		TEM_DESC = tem_desc;
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
