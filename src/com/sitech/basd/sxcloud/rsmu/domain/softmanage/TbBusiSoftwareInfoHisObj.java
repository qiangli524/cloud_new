package com.sitech.basd.sxcloud.rsmu.domain.softmanage;
import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbBusiSoftwareInfoHisObj extends BaseObj implements Serializable, Cloneable {
	private Integer  ID ;              
	private String   SOFTWARE_SIZE;          
	private String   MANUFACTURERS;    
	private String   SYSTEM_REQUEST;  
	private String   PROVIDERS;       
	private String   INTRODUCE;       
	private String   REMARK;       
	private String   NAME;         
	private String   VERSION;       
	private Integer   APPID;              
	private Integer   SOFTWAREID;          
	private String   UPDATEUSER;    
	private String   INSERTTIME;    
	private String   UPDATETYPE;
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getSOFTWARE_SIZE() {
		return SOFTWARE_SIZE;
	}
	public void setSOFTWARE_SIZE(String sOFTWARE_SIZE) {
		SOFTWARE_SIZE = sOFTWARE_SIZE;
	}
	public String getMANUFACTURERS() {
		return MANUFACTURERS;
	}
	public void setMANUFACTURERS(String mANUFACTURERS) {
		MANUFACTURERS = mANUFACTURERS;
	}
	public String getSYSTEM_REQUEST() {
		return SYSTEM_REQUEST;
	}
	public void setSYSTEM_REQUEST(String sYSTEM_REQUEST) {
		SYSTEM_REQUEST = sYSTEM_REQUEST;
	}
	public String getPROVIDERS() {
		return PROVIDERS;
	}
	public void setPROVIDERS(String pROVIDERS) {
		PROVIDERS = pROVIDERS;
	}
	public String getINTRODUCE() {
		return INTRODUCE;
	}
	public void setINTRODUCE(String iNTRODUCE) {
		INTRODUCE = iNTRODUCE;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getVERSION() {
		return VERSION;
	}
	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}
	public Integer getAPPID() {
		return APPID;
	}
	public void setAPPID(Integer aPPID) {
		APPID = aPPID;
	}
	public Integer getSOFTWAREID() {
		return SOFTWAREID;
	}
	public void setSOFTWAREID(Integer sOFTWAREID) {
		SOFTWAREID = sOFTWAREID;
	}
	public String getUPDATEUSER() {
		return UPDATEUSER;
	}
	public void setUPDATEUSER(String uPDATEUSER) {
		UPDATEUSER = uPDATEUSER;
	}
	public String getINSERTTIME() {
		return INSERTTIME;
	}
	public void setINSERTTIME(String iNSERTTIME) {
		INSERTTIME = iNSERTTIME;
	}
	public String getUPDATETYPE() {
		return UPDATETYPE;
	}
	public void setUPDATETYPE(String uPDATETYPE) {
		UPDATETYPE = uPDATETYPE;
	}
	
}
