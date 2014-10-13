package com.sitech.basd.yicloud.domain.busisystem;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>Title: BusiSystemObj</p>
 * <p>Description: 业务系统实体</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-9-24 下午5:11:55
 *
 */
public class BusiSystemObj extends BaseObj{

	private String ID;
	private String PARENT_ID;
	private String NUMBER;
	private String DESC;
	private String CONNECT_ID;
	private String NAME;
	private Integer TYPE;
	private String ENTITY_ID;
	private String INSERTDATE;
	private String USER_ID;
	private String OEM;
	
	private int vmCount;
	private double storeSize;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPARENT_ID() {
		return PARENT_ID;
	}
	public void setPARENT_ID(String pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}
	public String getNUMBER() {
		return NUMBER;
	}
	public void setNUMBER(String nUMBER) {
		NUMBER = nUMBER;
	}
	public String getDESC() {
		return DESC;
	}
	public void setDESC(String dESC) {
		DESC = dESC;
	}
	public String getCONNECT_ID() {
		return CONNECT_ID;
	}
	public void setCONNECT_ID(String cONNECT_ID) {
		CONNECT_ID = cONNECT_ID;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public Integer getTYPE() {
		return TYPE;
	}
	public void setTYPE(Integer tYPE) {
		TYPE = tYPE;
	}
	public String getENTITY_ID() {
		return ENTITY_ID;
	}
	public void setENTITY_ID(String eNTITY_ID) {
		ENTITY_ID = eNTITY_ID;
	}
	public String getINSERTDATE() {
		return INSERTDATE;
	}
	public void setINSERTDATE(String iNSERTDATE) {
		INSERTDATE = iNSERTDATE;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getOEM() {
		return OEM;
	}
	public void setOEM(String oEM) {
		OEM = oEM;
	}
	public int getVmCount() {
		return vmCount;
	}
	public void setVmCount(int vmCount) {
		this.vmCount = vmCount;
	}
	public double getStoreSize() {
		return storeSize;
	}
	public void setStoreSize(double storeSize) {
		this.storeSize = storeSize;
	}
}
