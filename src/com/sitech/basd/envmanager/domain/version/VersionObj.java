package com.sitech.basd.envmanager.domain.version;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class VersionObj extends BaseObj{
	
	private int s_id ; //INTEGER ,--id
	
	private String SOFTNAME ; // VARCHAR2(200) ,--软件名称
	
	private String SOFTEDITION ; // VARCHAR2(200) ,--版本
	
	private String SUPLINUX ; // VARCHAR2(200) ,--支持LINUX
	
	private String SUPUNIX ; // VARCHAR2(200) ,--支持UNIX
	
	private String SUPSUNOS ; // VARCHAR2(200) ,--各版本都支持
	
	private String SYSTEMPATCH ; //  VARCHAR2(200),--操作系统补丁
	
	private String SYSTEMSERVICE ; //  VARCHAR2(200),--操作系统服务要求
	
	private String DEPENDPACK  ; // VARCHAR2(200),--依赖软件包
	
	private String COMPILER  ; //VARCHAR2(200),--编译器要求
	
	private String OTHERPACK  ; //VARCHAR2(200),--依赖第三方
	
	private String SOFTPACK ; // VARCHAR2(200),--软件补丁
	
	private String SOFTLICENSE ; // VARCHAR2(200),--是否需要license


	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public String getSOFTNAME() {
		return SOFTNAME;
	}

	public void setSOFTNAME(String softname) {
		SOFTNAME = softname;
	}

	public String getSOFTEDITION() {
		return SOFTEDITION;
	}

	public void setSOFTEDITION(String softedition) {
		SOFTEDITION = softedition;
	}

	public String getSUPLINUX() {
		return SUPLINUX;
	}

	public void setSUPLINUX(String suplinux) {
		SUPLINUX = suplinux;
	}

	public String getSUPUNIX() {
		return SUPUNIX;
	}

	public void setSUPUNIX(String supunix) {
		SUPUNIX = supunix;
	}

	public String getSUPSUNOS() {
		return SUPSUNOS;
	}

	public void setSUPSUNOS(String supsunos) {
		SUPSUNOS = supsunos;
	}

	public String getSYSTEMPATCH() {
		return SYSTEMPATCH;
	}

	public void setSYSTEMPATCH(String systempatch) {
		SYSTEMPATCH = systempatch;
	}

	public String getSYSTEMSERVICE() {
		return SYSTEMSERVICE;
	}

	public void setSYSTEMSERVICE(String systemservice) {
		SYSTEMSERVICE = systemservice;
	}

	public String getDEPENDPACK() {
		return DEPENDPACK;
	}

	public void setDEPENDPACK(String dependpack) {
		DEPENDPACK = dependpack;
	}

	public String getCOMPILER() {
		return COMPILER;
	}

	public void setCOMPILER(String compiler) {
		COMPILER = compiler;
	}

	public String getOTHERPACK() {
		return OTHERPACK;
	}

	public void setOTHERPACK(String otherpack) {
		OTHERPACK = otherpack;
	}

	public String getSOFTPACK() {
		return SOFTPACK;
	}

	public void setSOFTPACK(String softpack) {
		SOFTPACK = softpack;
	}

	public String getSOFTLICENSE() {
		return SOFTLICENSE;
	}

	public void setSOFTLICENSE(String softlicense) {
		SOFTLICENSE = softlicense;
	}
	
	
	
	

}
