package com.sitech.basd.sxcloud.rsmu.domain.upgrade;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class UpgradeStrategyObj extends BaseObj implements Serializable,
		Cloneable {

	private int ID;
	private String STRATEGYNAME;
	private int TYPE;
	private int STRATEGY;
	private String UPDATETIME;
	private String FREQUENCY;
	private String HOSTNAME;
	private String BASEPATH; // 为基准部署时 选择基准路径

	public String getHOSTNAME() {
		return HOSTNAME;
	}

	public void setHOSTNAME(String hostname) {
		HOSTNAME = hostname;
	}

	public String getSTRATEGYNAME() {
		return STRATEGYNAME;
	}

	public void setSTRATEGYNAME(String strategyname) {
		STRATEGYNAME = strategyname;
	}

	public String getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(String updatetime) {
		UPDATETIME = updatetime;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int type) {
		TYPE = type;
	}

	public int getSTRATEGY() {
		return STRATEGY;
	}

	public void setSTRATEGY(int strategy) {
		STRATEGY = strategy;
	}

	public String getFREQUENCY() {
		return FREQUENCY;
	}

	public void setFREQUENCY(String frequency) {
		FREQUENCY = frequency;
	}

	public String getBASEPATH() {
		return BASEPATH;
	}

	public void setBASEPATH(String basepath) {
		BASEPATH = basepath;
	}

}
