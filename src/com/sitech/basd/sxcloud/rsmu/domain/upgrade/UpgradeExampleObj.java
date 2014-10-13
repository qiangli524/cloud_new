package com.sitech.basd.sxcloud.rsmu.domain.upgrade;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class UpgradeExampleObj extends BaseObj implements Serializable,
		Cloneable {

	private int ID; // 编号
	private String LONGID;
	private int HOSTID; // 主机编号
	private String UPGRADETIME; // 升级时间
	private int STRATEGYID; // 策略编号
	private int UPGRADE_FLAG; // 0：未升级1：已升级
	private int START_STOP_FLAG; // 0：停止 1：启动
	private String UPDATETIME; // 启停时间
	private int APPID; // 应用id

	private String UPGRADE_FLAG_NAME;
	private String UPGRADE_FLAG_AN;
	private String START_STOP_FLAG_NAME;
	private String HOSTNAME;
	private String STRATEGYNAME;
	private String APPNAME; // 应用名称

	private int APPPORT; // 应用端口号
	private String KEYNAME; // 中间键名称

	private int UPGRADE_PERCENT; // 升级完成百分比
	private int START_STOP_PERCENT;// 启动或停止完成百分比

	public String getHOSTNAME() {
		return HOSTNAME;
	}

	public void setHOSTNAME(String hostname) {
		HOSTNAME = hostname;
	}

	public void STRATEGYNAME(String hostname) {
		HOSTNAME = hostname;
	}

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

	public String getLONGID() {
		return LONGID;
	}

	public void setLONGID(String longid) {
		LONGID = longid;
	}

	public int getHOSTID() {
		return HOSTID;
	}

	public void setHOSTID(int hostid) {
		HOSTID = hostid;
	}

	public int getSTRATEGYID() {
		return STRATEGYID;
	}

	public void setSTRATEGYID(int strategyid) {
		STRATEGYID = strategyid;
	}

	public int getUPGRADE_FLAG() {
		return UPGRADE_FLAG;
	}

	public void setUPGRADE_FLAG(int upgrade_flag) {
		UPGRADE_FLAG = upgrade_flag;
	}

	public int getSTART_STOP_FLAG() {
		return START_STOP_FLAG;
	}

	public void setSTART_STOP_FLAG(int start_stop_flag) {
		START_STOP_FLAG = start_stop_flag;
	}

	public String getUPGRADETIME() {
		return UPGRADETIME;
	}

	public void setUPGRADETIME(String upgradetime) {
		UPGRADETIME = upgradetime;
	}

	public String getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(String updatetime) {
		UPDATETIME = updatetime;
	}

	public int getAPPID() {
		return APPID;
	}

	public void setAPPID(int appid) {
		APPID = appid;
	}

	public String getAPPNAME() {
		return APPNAME;
	}

	public void setAPPNAME(String appname) {
		APPNAME = appname;
	}

	public String getUPGRADE_FLAG_AN() {
		return UPGRADE_FLAG_AN;
	}

	public void setUPGRADE_FLAG_AN(String upgrade_flag_an) {
		UPGRADE_FLAG_AN = upgrade_flag_an;
	}

	public String getUPGRADE_FLAG_NAME() {
		return UPGRADE_FLAG_NAME;
	}

	public void setUPGRADE_FLAG_NAME(String upgrade_flag_name) {
		UPGRADE_FLAG_NAME = upgrade_flag_name;
	}

	public String getSTART_STOP_FLAG_NAME() {
		return START_STOP_FLAG_NAME;
	}

	public void setSTART_STOP_FLAG_NAME(String start_stop_flag_name) {
		START_STOP_FLAG_NAME = start_stop_flag_name;
	}

	public int getAPPPORT() {
		return APPPORT;
	}

	public void setAPPPORT(int aPPPORT) {
		APPPORT = aPPPORT;
	}

	public String getKEYNAME() {
		return KEYNAME;
	}

	public void setKEYNAME(String kEYNAME) {
		KEYNAME = kEYNAME;
	}

	public int getUPGRADE_PERCENT() {
		return UPGRADE_PERCENT;
	}

	public void setUPGRADE_PERCENT(int uPGRADEPERCENT) {
		UPGRADE_PERCENT = uPGRADEPERCENT;
	}

	public int getSTART_STOP_PERCENT() {
		return START_STOP_PERCENT;
	}

	public void setSTART_STOP_PERCENT(int sTARTSTOPPERCENT) {
		START_STOP_PERCENT = sTARTSTOPPERCENT;
	}

}
