package com.sitech.basd.sxcloud.rsmu.domain.system;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbSysFunctionsObj extends BaseObj implements Serializable,
		Cloneable {
	private int ID = 0;
	private String FUNCID = null;
	private String FUNNAME = null;
	private String STATUS;
	private String REMARK;
	private String UPDATETIME;
	private int TYPE;
	private String FUNCREQUEST;
	private int ISREFRESH;

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

}
