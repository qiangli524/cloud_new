package com.sitech.basd.sxcloud.rsmu.domain.hostmanage;
import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbBusiHostHisObj extends BaseObj implements Serializable, Cloneable {
	 private int  ID               ;    //编号
	 private String  IP               ;    //主机IP
	 private String  HOSTNAME         ;    //主机名称
	 private String  INSERTTIME       ;    //更新时间
	 private String  STATUS           ;    //0：不可用，1：可用，这个字段页面设置修改用
	 private String  FREQUENCY        ;    //主机主频
	 private String  MEMORY           ;    //内存
	 private String  OPERATE_SYSTEM   ;    //操作系统
	 private String  MANUFACTURERS    ;    //主机厂商      
	 private int HOSTID            ;    //主机编号       
	 private String UPDATEUSER        ;    //更新人员       
	 private String UPDATETYPE        ;    //1：更新 2：删除

	public String getFREQUENCY() {
		return FREQUENCY;
	}
	public void setFREQUENCY(String frequency) {
		FREQUENCY = frequency;
	}
	public String getHOSTNAME() {
		return HOSTNAME;
	}
	public void setHOSTNAME(String hostname) {
		HOSTNAME = hostname;
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String ip) {
		IP = ip;
	}
	public String getMANUFACTURERS() {
		return MANUFACTURERS;
	}
	public void setMANUFACTURERS(String manufacturers) {
		MANUFACTURERS = manufacturers;
	}
	public String getMEMORY() {
		return MEMORY;
	}
	public void setMEMORY(String memory) {
		MEMORY = memory;
	}
	public String getOPERATE_SYSTEM() {
		return OPERATE_SYSTEM;
	}
	public void setOPERATE_SYSTEM(String operate_system) {
		OPERATE_SYSTEM = operate_system;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String status) {
		STATUS = status;
	}
	public int getHOSTID() {
		return HOSTID;
	}
	public void setHOSTID(int hostid) {
		HOSTID = hostid;
	}
	public String getINSERTTIME() {
		return INSERTTIME;
	}
	public void setINSERTTIME(String inserttime) {
		INSERTTIME = inserttime;
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


	
}
