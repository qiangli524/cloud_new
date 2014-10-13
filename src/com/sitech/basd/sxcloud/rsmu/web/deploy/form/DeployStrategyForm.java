package com.sitech.basd.sxcloud.rsmu.web.deploy.form;

import java.util.List;

@SuppressWarnings("serial")
public class DeployStrategyForm {

	private int ID; // '编号',
	private String STRATEGYNAME; // '部署策略名称',
	private int TYPE; // 1：录像部署 2：基准部署
	private int STRATEGY; // 当录像部署时，方案为录像编号。 当基准部署时，基准主机编号
	private String FREQUENCY; // 以0 0/1 * * * ?形式表示
	private String UPDATETIME; // '更新时间',
	private String BASEPATH; // 为基准部署时 选择基准路径
	private List resultList = null;

	public String getBASEPATH() {
		return BASEPATH;
	}

	public void setBASEPATH(String basepath) {
		BASEPATH = basepath;
	}

	public String getFREQUENCY() {
		return FREQUENCY;
	}

	public void setFREQUENCY(String frequency) {
		FREQUENCY = frequency;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public int getSTRATEGY() {
		return STRATEGY;
	}

	public void setSTRATEGY(int strategy) {
		STRATEGY = strategy;
	}

	public String getSTRATEGYNAME() {
		return STRATEGYNAME;
	}

	public void setSTRATEGYNAME(String strategyname) {
		STRATEGYNAME = strategyname;
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
