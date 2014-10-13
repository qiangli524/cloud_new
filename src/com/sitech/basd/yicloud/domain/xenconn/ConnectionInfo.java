package com.sitech.basd.yicloud.domain.xenconn;


public class ConnectionInfo {
	
	private String ADDRESS;
	private String USERNAME;
	private String PASSWORD;
	private String POOLUUID;
	
	public ConnectionInfo(String aDDRESS, String uSERNAME, String pASSWORD,
			String pOOLUUID) {
		super();
		ADDRESS = aDDRESS;
		USERNAME = uSERNAME;
		PASSWORD = pASSWORD;
		POOLUUID = pOOLUUID;
	}
	
	public ConnectionInfo() {
		super();
	}
	
	public ConnectionInfo(String pOOLUUID) {
		super();
		POOLUUID = pOOLUUID;
	}

	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getPOOLUUID() {
		return POOLUUID;
	}
	public void setPOOLUUID(String pOOLUUID) {
		POOLUUID = pOOLUUID;
	}

	
}
