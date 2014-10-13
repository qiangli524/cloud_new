package com.sitech.basd.sxcloud.rsmu.domain.deploy;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbBusiVideoExampleObj extends BaseObj implements Serializable, Cloneable{
	private int ID;				//编号
	private String VIDEONAME;	//录像名称
	private String CREATEUSER;	//录像人
	private String CREATETIME;	//录像创建时间
	private String REMARK;		//备注
	private String HOSTIP;		//主机IP
	private String HOSTUSERNAME;//主机用户名

//属性的get,set方法start-----
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getVIDEONAME() {
		return VIDEONAME;
	}
	public void setVIDEONAME(String vIDEONAME) {
		VIDEONAME = vIDEONAME;
	}
	public String getCREATEUSER() {
		return CREATEUSER;
	}
	public void setCREATEUSER(String cREATEUSER) {
		CREATEUSER = cREATEUSER;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	
	public String getHOSTIP() {
		return HOSTIP;
	}
	public void setHOSTIP(String hOSTIP) {
		HOSTIP = hOSTIP;
	}
	public String getHOSTUSERNAME() {
		return HOSTUSERNAME;
	}
	public void setHOSTUSERNAME(String hOSTUSERNAME) {
		HOSTUSERNAME = hOSTUSERNAME;
	}
	public String getCREATETIME() {
		return CREATETIME;
	}
	public void setCREATETIME(String cREATETIME) {
		CREATETIME = cREATETIME;
	}
	
//属性的get,set方法end-----

}
