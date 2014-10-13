package com.sitech.basd.sxcloud.rsmu.domain.deploy.configfile;

import java.io.Serializable;
import java.sql.Date;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class DeployConfigFile extends BaseObj implements Serializable, Cloneable {
	private int ID;
	private String IP;
	private String PATH;
	private Date MODIFYTIME;

	public Date getMODIFYTIME() {
		return MODIFYTIME;
	}
	public void setMODIFYTIME(Date mODIFYTIME) {
		MODIFYTIME = mODIFYTIME;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getPATH() {
		return PATH;
	}
	public void setPATH(String pATH) {
		PATH = pATH;
	}
	
	

}
