package com.sitech.basd.sxcloud.rsmu.web.deploy.form;

import java.util.List;

@SuppressWarnings("serial")
public class DeployTimeConfigForm {
	private int ID; // '编号',
	private String NAME; // 名称
	private String UPDATETIME; // 更新时间
	private String EXPRESSION; // cron表达式
	private int STATUS; // 0：失效，1生效
	private String EXPRESSION_BF;
	private int STATUS_BF;
	private String CLASS_NAME;
	private List resultList;

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

	public String getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(String uPDATETIME) {
		UPDATETIME = uPDATETIME;
	}

	public String getEXPRESSION() {
		return EXPRESSION;
	}

	public void setEXPRESSION(String eXPRESSION) {
		EXPRESSION = eXPRESSION;
	}

	@SuppressWarnings("unchecked")
	public List getResultList() {
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}

	public String getEXPRESSION_BF() {
		return EXPRESSION_BF;
	}

	public void setEXPRESSION_BF(String eXPRESSIONBF) {
		EXPRESSION_BF = eXPRESSIONBF;
	}

	public int getSTATUS_BF() {
		return STATUS_BF;
	}

	public void setSTATUS_BF(int sTATUSBF) {
		STATUS_BF = sTATUSBF;
	}

	/*
	 * 清空ActionForm
	 */
	public void reset() {
		this.ID = 0;
		this.NAME = null;
		this.EXPRESSION = null;
		this.UPDATETIME = null;
		this.resultList = null;
	}
}
