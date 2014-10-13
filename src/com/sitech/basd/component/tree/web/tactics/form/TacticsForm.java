package com.sitech.basd.component.tree.web.tactics.form;

import java.util.List;

import com.sitech.basd.component.tree.domain.tactics.TacticsObj;


public class TacticsForm {
	
	private String ID;
	private String TASKID;//任务ID
	private Short EXECUTEMETHOD;
	private Short TIMEMODE;
	private String EXECUTETIME;
	private Short ISCOPY;
	private Short ISNEEDCHECK;
	private Short ISRESTART;
	private Short flag;
	private List<TacticsObj> tacList;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
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
	public Short getFlag() {
		return flag;
	}
	public void setFlag(Short flag) {
		this.flag = flag;
	}
	public List<TacticsObj> getTacList() {
		return tacList;
	}
	public void setTacList(List<TacticsObj> tacList) {
		this.tacList = tacList;
	}
	public String getTASKID() {
		return TASKID;
	}
	public void setTASKID(String tASKID) {
		TASKID = tASKID;
	}
	public Short getISRESTART() {
		return ISRESTART;
	}
	public void setISRESTART(Short iSRESTART) {
		ISRESTART = iSRESTART;
	}
	
}
