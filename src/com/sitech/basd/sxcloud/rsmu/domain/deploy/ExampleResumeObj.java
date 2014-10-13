package com.sitech.basd.sxcloud.rsmu.domain.deploy;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;


@SuppressWarnings("serial")
public class ExampleResumeObj extends BaseObj implements Serializable, Cloneable {

	private int ID; // 编号
	private String LONGID;
	private String NAME; // 名称
	private int HOSTID; // 主机编号
	private String CREATETIME; // 创建时间
	private int RESUME_FLAG; // 0：未恢复1：正在恢复2:已恢复
	private int APPID; // 应用id

	private String RESUME_FLAG_NAME;
	private String RESUME_FLAG_AN;
	private String IP; // 主机名称
	private String APPNAME; // 应用名称
	private String UPGRADEID; // 升级部署实例Id
	private String DATAAUTHORITY; //
	private String RESUME_PERCENT; // 恢复完成百分比

	public String getDATAAUTHORITY() {
		return DATAAUTHORITY;
	}

	public void setDATAAUTHORITY(String dataauthority) {
		DATAAUTHORITY = dataauthority;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getLONGID() {
		return LONGID;
	}

	public void setLONGID(String lONGID) {
		LONGID = lONGID;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public int getHOSTID() {
		return HOSTID;
	}

	public void setHOSTID(int hOSTID) {
		HOSTID = hOSTID;
	}

	public String getCREATETIME() {
		return CREATETIME;
	}

	public void setCREATETIME(String cREATETIME) {
		CREATETIME = cREATETIME;
	}

	public int getRESUME_FLAG() {
		return RESUME_FLAG;
	}

	public void setRESUME_FLAG(int rESUMEFLAG) {
		RESUME_FLAG = rESUMEFLAG;
	}

	public int getAPPID() {
		return APPID;
	}

	public void setAPPID(int aPPID) {
		APPID = aPPID;
	}

	public String getRESUME_FLAG_NAME() {
		return RESUME_FLAG_NAME;
	}

	public void setRESUME_FLAG_NAME(String rESUMEFLAGNAME) {
		RESUME_FLAG_NAME = rESUMEFLAGNAME;
	}

	public String getRESUME_FLAG_AN() {
		return RESUME_FLAG_AN;
	}

	public void setRESUME_FLAG_AN(String rESUMEFLAGAN) {
		RESUME_FLAG_AN = rESUMEFLAGAN;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String ip) {
		IP = ip;
	}

	public String getAPPNAME() {
		return APPNAME;
	}

	public void setAPPNAME(String aPPNAME) {
		APPNAME = aPPNAME;
	}

	public String getRESUME_PERCENT() {
		return RESUME_PERCENT;
	}

	public void setRESUME_PERCENT(String rESUMEPERCENT) {
		RESUME_PERCENT = rESUMEPERCENT;
	}

	public String getUPGRADEID() {
		return UPGRADEID;
	}

	public void setUPGRADEID(String upgradeid) {
		UPGRADEID = upgradeid;
	}

}
