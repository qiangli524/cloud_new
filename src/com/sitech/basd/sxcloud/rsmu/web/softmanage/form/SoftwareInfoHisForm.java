package com.sitech.basd.sxcloud.rsmu.web.softmanage.form;

import java.util.List;


public class SoftwareInfoHisForm  {

	private int  ID     = 0 ;              
	private String   SOFTWARE_SIZE   = null;          
	private String   MANUFACTURERS   = null;    
	private String   SYSTEM_REQUEST    = null;  
	private String   PROVIDERS    = null;       
	private String   INTRODUCE    = null;       
	private String   REMARK       = null;       
	private String   NAME       = null;         
	private String   VERSION      = null;       
	private int   APPID      = 0 ;              
	private int   SOFTWAREID     = 0 ;          
	private String   UPDATEUSER      = null;    
	private String   INSERTTIME      = null;    
	private String   UPDATETYPE      = null ;
	private List resultList ;
	
	public List getResultList() {
		return resultList;
	}
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	public int getAPPID() {
		return APPID;
	}
	public void setAPPID(int appid) {
		APPID = appid;
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public String getINSERTTIME() {
		return INSERTTIME;
	}
	public void setINSERTTIME(String inserttime) {
		INSERTTIME = inserttime;
	}
	public String getINTRODUCE() {
		return INTRODUCE;
	}
	public void setINTRODUCE(String introduce) {
		INTRODUCE = introduce;
	}
	public String getMANUFACTURERS() {
		return MANUFACTURERS;
	}
	public void setMANUFACTURERS(String manufacturers) {
		MANUFACTURERS = manufacturers;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String name) {
		NAME = name;
	}
	public String getPROVIDERS() {
		return PROVIDERS;
	}
	public void setPROVIDERS(String providers) {
		PROVIDERS = providers;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String remark) {
		REMARK = remark;
	}
	
	public String getSOFTWARE_SIZE() {
		return SOFTWARE_SIZE;
	}
	public void setSOFTWARE_SIZE(String sOFTWARESIZE) {
		SOFTWARE_SIZE = sOFTWARESIZE;
	}
	public int getSOFTWAREID() {
		return SOFTWAREID;
	}
	public void setSOFTWAREID(int softwareid) {
		SOFTWAREID = softwareid;
	}
	public String getSYSTEM_REQUEST() {
		return SYSTEM_REQUEST;
	}
	public void setSYSTEM_REQUEST(String system_request) {
		SYSTEM_REQUEST = system_request;
	}
	public String getUPDATETYPE() {
		return UPDATETYPE;
	}
	public void setUPDATETYPE(String updatetype) {
		UPDATETYPE = updatetype;
	}
	public String getUPDATEUSER() {
		return UPDATEUSER;
	}
	public void setUPDATEUSER(String updateuser) {
		UPDATEUSER = updateuser;
	}
	public String getVERSION() {
		return VERSION;
	}
	public void setVERSION(String version) {
		VERSION = version;
	}
	
	
}
