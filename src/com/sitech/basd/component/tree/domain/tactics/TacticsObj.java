package com.sitech.basd.component.tree.domain.tactics;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class TacticsObj extends BaseObj{

	private String ID;
	private String TASKID;
	private Short EXECUTEMETHOD;
	private Short TIMEMODE;
	private String EXECUTETIME;
	private Short ISCOPY;
	private Short ISNEEDCHECK;
	private Short ISRESTART;
	private String isAddTactics;//是否添加策略
	
	public String getIsAddTactics() {
		return isAddTactics;
	}
	public void setIsAddTactics(String isAddTactics) {
		this.isAddTactics = isAddTactics;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getTASKID() {
		return TASKID;
	}
	public void setTASKID(String tASKID) {
		TASKID = tASKID;
	}
	public Short getEXECUTEMETHOD() {
		return EXECUTEMETHOD;
	}
	public void setEXECUTEMETHOD(Short eXECUTEMETHOD) {
		EXECUTEMETHOD = eXECUTEMETHOD;
	}
	public Short getTIMEMODE() {
		return TIMEMODE;
	}
	public void setTIMEMODE(Short tIMEMODE) {
		TIMEMODE = tIMEMODE;
	}
	public String getEXECUTETIME() {
		return EXECUTETIME;
	}
	public void setEXECUTETIME(String eXECUTETIME) {
		EXECUTETIME = eXECUTETIME;
	}
	public Short getISCOPY() {
		return ISCOPY;
	}
	public void setISCOPY(Short iSCOPY) {
		ISCOPY = iSCOPY;
	}
	public Short getISNEEDCHECK() {
		return ISNEEDCHECK;
	}
	public void setISNEEDCHECK(Short iSNEEDCHECK) {
		ISNEEDCHECK = iSNEEDCHECK;
	}
	public Short getISRESTART() {
		return ISRESTART;
	}
	public void setISRESTART(Short iSRESTART) {
		ISRESTART = iSRESTART;
	}
	
}
