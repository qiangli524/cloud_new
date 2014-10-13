package com.sitech.basd.sxcloud.rsmu.domain.deploy;

import java.io.Serializable;
import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbBusiDeployConfigObj extends BaseObj implements Serializable, Cloneable{
    
     // 部署基本配置 
     
	private int ID           ;        // '编号',
	private int HOSTID       ;        // '主机编号',
	private int APPID        ;        // '应用编号',
	private int HOSTCONFIG   ;        // '主机配置编号',
	private String UPDATEUSER;        // '更新者',
	private String UPDATETIME;        // '更新时间',
    private String REMARK    ;        // '备注',
	private String start_time;//告警时间 开始
    private String end_time;  //告警时间 结束  
    private String APPNAME;   //应用名称
    private String HOSTNAME;  //主机名称

	public int getAPPID() {
		return APPID;
	}
	public void setAPPID(int appid) {
		APPID = appid;
	}
	public int getHOSTCONFIG() {
		return HOSTCONFIG;
	}
	public void setHOSTCONFIG(int hostconfig) {
		HOSTCONFIG = hostconfig;
	}
	public int getHOSTID() {
		return HOSTID;
	}
	public void setHOSTID(int hostid) {
		HOSTID = hostid;
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String remark) {
		REMARK = remark;
	}
	public String getUPDATETIME() {
		return UPDATETIME;
	}
	public void setUPDATETIME(String updatetime) {
		UPDATETIME = updatetime;
	}
	public String getUPDATEUSER() {
		return UPDATEUSER;
	}
	public void setUPDATEUSER(String updateuser) {
		UPDATEUSER = updateuser;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getAPPNAME() {
		return APPNAME;
	}
	public void setAPPNAME(String appname) {
		APPNAME = appname;
	}
	public String getHOSTNAME() {
		return HOSTNAME;
	}
	public void setHOSTNAME(String hostname) {
		HOSTNAME = hostname;
	}

	    
}
