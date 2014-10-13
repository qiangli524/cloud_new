package com.sitech.basd.component.domain.dhcp;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;


/**
 * <p>Title: DhcpObj</p>
 * <p>Description: dhcp实体类</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-8-16 下午3:42:57
 *
 */
public class DhcpObj extends BaseObj{

	private String ID;//主键
	private String IP;//ip地址
	private String MACADDRESS;//machine地址
	private Short IPSTATUS;//ip状态 0未使用 1已使用
	private String DESCR;//描述
	private String INSERTDATE;//插入时间
	private String UPDATEDATE;//更新时间
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getMACADDRESS() {
		return MACADDRESS;
	}
	public void setMACADDRESS(String mACADDRESS) {
		MACADDRESS = mACADDRESS;
	}
	public Short getIPSTATUS() {
		return IPSTATUS;
	}
	public void setIPSTATUS(Short iPSTATUS) {
		IPSTATUS = iPSTATUS;
	}
	public String getDESCR() {
		return DESCR;
	}
	public void setDESCR(String dESCR) {
		DESCR = dESCR;
	}
	public String getINSERTDATE() {
		return INSERTDATE;
	}
	public void setINSERTDATE(String iNSERTDATE) {
		INSERTDATE = iNSERTDATE;
	}
	public String getUPDATEDATE() {
		return UPDATEDATE;
	}
	public void setUPDATEDATE(String uPDATEDATE) {
		UPDATEDATE = uPDATEDATE;
	}
	
}
