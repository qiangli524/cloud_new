package com.sitech.basd.sxcloud.rsmu.domain.deploy;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbBusiDeployTimeConfigObj extends BaseObj implements Serializable,
		Cloneable {
	// 部署基本配置

	private int ID; // '编号',
	private String NAME; // 名称
	private int APPID; // 所属应用ID
	private String EXPRESSION; // cron表达式
	private int EXECUTE_FLAG; // 0：未扫描 1：已扫描
	private String UPDATETIME; // 更新时间
	private int STATUS; // 0：失效，1生效
	private int EXECUTE_FLAG2; // 0：未扫描 1：已扫描 对于STATUS字段
	private String CLASS_NAME;

	public String getCLASS_NAME() {
		return CLASS_NAME;
	}

	public void setCLASS_NAME(String cLASSNAME) {
		CLASS_NAME = cLASSNAME;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public int getAPPID() {
		return APPID;
	}

	public void setAPPID(int aPPID) {
		APPID = aPPID;
	}

	public String getEXPRESSION() {
		return EXPRESSION;
	}

	public void setEXPRESSION(String eXPRESSION) {
		EXPRESSION = eXPRESSION;
	}

	public int getEXECUTE_FLAG() {
		return EXECUTE_FLAG;
	}

	public void setEXECUTE_FLAG(int eXECUTEFLAG) {
		EXECUTE_FLAG = eXECUTEFLAG;
	}

	public String getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(String uPDATETIME) {
		UPDATETIME = uPDATETIME;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}

	public int getEXECUTE_FLAG2() {
		return EXECUTE_FLAG2;
	}

	public void setEXECUTE_FLAG2(int eXECUTEFLAG2) {
		EXECUTE_FLAG2 = eXECUTEFLAG2;
	}
}
