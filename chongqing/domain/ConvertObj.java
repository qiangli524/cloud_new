package domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class ConvertObj extends BaseObj {
	private String id;
	private String convertType;
	private String destiIp;
	private String sourceIp;
	private String state;
	private String insertTime;
	private String usedTime;
	private String storeAll;
	private String storeFree;
	
	public String getStoreAll() {
		return storeAll;
	}
	public void setStoreAll(String storeAll) {
		this.storeAll = storeAll;
	}
	public String getStoreFree() {
		return storeFree;
	}
	public void setStoreFree(String storeFree) {
		this.storeFree = storeFree;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getConvertType() {
		return convertType;
	}
	public void setConvertType(String convertType) {
		this.convertType = convertType;
	}
	public String getDestiIp() {
		return destiIp;
	}
	public void setDestiIp(String destiIp) {
		this.destiIp = destiIp;
	}
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public String getUsedTime() {
		return usedTime;
	}
	public void setUsedTime(String usedTime) {
		this.usedTime = usedTime;
	}
	
}
