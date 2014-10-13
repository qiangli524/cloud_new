package com.sitech.basd.sxcloud.rsmu.web.upgrade.form;

import java.util.List;

@SuppressWarnings("serial")
public class UpgradeStrategyForm {

	private int ID;
	private String STRATEGYNAME;
	private int TYPE;
	private int STRATEGY;
	private String UPDATETIME;
	private String BASEPATH; // 为基准部署时 选择基准路径
	private String FREQUENCY;
	private String HOSTNAME;
	private List resultList;
	private List hostList;

	public String getSTRATEGYNAME() {
		return STRATEGYNAME;
	}

	public void setSTRATEGYNAME(String strategyname) {
		STRATEGYNAME = strategyname;
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

	public String getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(String updatetime) {
		UPDATETIME = updatetime;
	}

	public String getFREQUENCY() {
		return FREQUENCY;
	}

	public void setFREQUENCY(String frequency) {
		FREQUENCY = frequency;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getBASEPATH() {
		return BASEPATH;
	}

	public void setBASEPATH(String basepath) {
		BASEPATH = basepath;
	}

	/*
	 * 清空ActionForm
	 */

	public String getHOSTNAME() {
		return HOSTNAME;
	}

	public void setHOSTNAME(String hostname) {
		HOSTNAME = hostname;
	}

	public List getHostList() {
		return hostList;
	}

	public void setHostList(List hostList) {
		this.hostList = hostList;
	}
}
