package com.sitech.basd.sxcloud.rsmu.web.system.form;

import java.util.List;

@SuppressWarnings("serial")
public class FunctionsForm {

	private int ID = 0;
	private String FUNCID = null;
	private String FUNNAME = null;
	private String STATUS;
	private String REMARK;
	private String UPDATETIME;
	private int TYPE;
	private String FUNCREQUEST;
	private int ISREFRESH;
	private List resultList = null;
	private String ICON;

	public String getFUNCID() {
		return FUNCID;
	}

	public void setFUNCID(String funcid) {
		FUNCID = funcid;
	}

	public String getFUNCREQUEST() {
		return FUNCREQUEST;
	}

	public void setFUNCREQUEST(String funcrequest) {
		FUNCREQUEST = funcrequest;
	}

	public String getFUNNAME() {
		return FUNNAME;
	}

	public void setFUNNAME(String funname) {
		FUNNAME = funname;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public int getISREFRESH() {
		return ISREFRESH;
	}

	public void setISREFRESH(int isrefresh) {
		ISREFRESH = isrefresh;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String remark) {
		REMARK = remark;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String status) {
		STATUS = status;
	}

	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int type) {
		TYPE = type;
	}

	public String getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(String updatetime) {
		UPDATETIME = updatetime;
	}

	public String getICON() {
		return ICON;
	}

	public void setICON(String icon) {
		ICON = icon;
	}
}
