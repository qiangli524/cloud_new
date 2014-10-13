package com.sitech.basd.sxcloud.rsmu.web.deploy.form;

import java.util.List;

@SuppressWarnings("serial")
public class DeployVideorecordForm {

	private int ID; // '编号',
	private int VIDEOID; // '录像编号',
	private String CONTENT; // '记录',
	private int FLAG; // '1:输入命令 2：输出结果',
	private String INSERTTIME; // '执行时间',
	private List resultList = null;
	private String STARTTIME = null; // 部署回放开始时间
	private String ENDTIME = null; // 部署回放结束时间

	public String getCONTENT() {
		return CONTENT;
	}

	public void setCONTENT(String content) {
		CONTENT = content;
	}

	public int getFLAG() {
		return FLAG;
	}

	public void setFLAG(int flag) {
		FLAG = flag;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getINSERTTIME() {
		return INSERTTIME;
	}

	public void setINSERTTIME(String inserttime) {
		INSERTTIME = inserttime;
	}

	public int getVIDEOID() {
		return VIDEOID;
	}

	public void setVIDEOID(int videoid) {
		VIDEOID = videoid;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getSTARTTIME() {
		return STARTTIME;
	}

	public void setSTARTTIME(String sTARTTIME) {
		STARTTIME = sTARTTIME;
	}

	public String getENDTIME() {
		return ENDTIME;
	}

	public void setENDTIME(String eNDTIME) {
		ENDTIME = eNDTIME;
	}

}
