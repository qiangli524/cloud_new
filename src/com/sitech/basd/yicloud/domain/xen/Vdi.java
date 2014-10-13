package com.sitech.basd.yicloud.domain.xen;

public class Vdi extends Sr {
	private String vdiUuid;
	private String vdiName;
	private String vdiDesc;
	private long vdiSize;
    private long vdiPhysicalUtilisation;
    private boolean readOnly;
    private String vdiType;
    private String srHostName;
    private String srHostUuid;
    
	
	public String getVdiType() {
		return vdiType;
	}
	public void setVdiType(String vdiType) {
		this.vdiType = vdiType;
	}
	public String getSrHostName() {
		return srHostName;
	}
	public void setSrHostName(String srHostName) {
		this.srHostName = srHostName;
	}
	public String getSrHostUuid() {
		return srHostUuid;
	}
	public void setSrHostUuid(String srHostUuid) {
		this.srHostUuid = srHostUuid;
	}
	public String getVdiUuid() {
		return vdiUuid;
	}
	public void setVdiUuid(String vdiUuid) {
		this.vdiUuid = vdiUuid;
	}
	public long getVdiPhysicalUtilisation() {
		return vdiPhysicalUtilisation;
	}
	public void setVdiPhysicalUtilisation(long vdiPhysicalUtilisation) {
		this.vdiPhysicalUtilisation = vdiPhysicalUtilisation;
	}
	public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	public String getVdiName() {
		return vdiName;
	}
	public void setVdiName(String vdiName) {
		this.vdiName = vdiName;
	}
	public String getVdiDesc() {
		return vdiDesc;
	}
	public void setVdiDesc(String vdiDesc) {
		this.vdiDesc = vdiDesc;
	}

	public long getVdiSize() {
		return vdiSize;
	}
	public void setVdiSize(long vdiSize) {
		this.vdiSize = vdiSize;
	}
	
	public String toString(){
		
		return "vdiName:"+vdiName+"  vdiDesc:"+vdiDesc+"  vdiSize:"+vdiSize;
	}

}