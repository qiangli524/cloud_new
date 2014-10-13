package com.sitech.basd.sxcloud.rsmu.domain.softmanage;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbBusiSoftwareFileInfoObj extends BaseObj implements Serializable, Cloneable {
	private Integer FILEID;
	private String APPID;
	private String VERSIONID;
	private String FILEPATH;
	private String FILESIZE;
	private String FILEUPDATETIME;
	private String SOFTWAREID;
	private String FILEMD5;

	public Integer getFILEID() {
		return FILEID;
	}

	public void setFILEID(Integer fILEID) {
		FILEID = fILEID;
	}

	public String getAPPID() {
		return APPID;
	}

	public void setAPPID(String aPPID) {
		APPID = aPPID;
	}

	public String getVERSIONID() {
		return VERSIONID;
	}

	public void setVERSIONID(String vERSIONID) {
		VERSIONID = vERSIONID;
	}

	public String getFILEPATH() {
		return FILEPATH;
	}

	public void setFILEPATH(String fILEPATH) {
		FILEPATH = fILEPATH;
	}

	public String getFILESIZE() {
		return FILESIZE;
	}

	public void setFILESIZE(String fILESIZE) {
		FILESIZE = fILESIZE;
	}

	public String getFILEUPDATETIME() {
		return FILEUPDATETIME;
	}

	public void setFILEUPDATETIME(String fILEUPDATETIME) {
		FILEUPDATETIME = fILEUPDATETIME;
	}

	public String getSOFTWAREID() {
		return SOFTWAREID;
	}

	public void setSOFTWAREID(String sOFTWAREID) {
		SOFTWAREID = sOFTWAREID;
	}

	public String getFILEMD5() {
		return FILEMD5;
	}

	public void setFILEMD5(String fILEMD5) {
		FILEMD5 = fILEMD5;
	}

}
