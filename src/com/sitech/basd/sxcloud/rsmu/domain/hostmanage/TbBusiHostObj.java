package com.sitech.basd.sxcloud.rsmu.domain.hostmanage;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbBusiHostObj extends BaseObj implements Serializable, Cloneable {
	private int ID; // 编号
	private String IP; // 主机IP
	private String HOSTNAME; // 主机名称
	private String UPDATETIME; // 更新时间
	private String STATUS; // 0：不可用，1：可用，这个字段页面设置修改用
	private String FREQUENCY; // 主机主频
	private String MEMORY; // 内存
	private String OPERATE_SYSTEM; // 操作系统
	private String MANUFACTURERS; // 主机厂商
	private String VLAN;
	private int HOSTPORT;
	private String VLANIP; // vlan ip
	private String APPNAMES;
	private String KBP_CLASS;
	private String DEVICE_ID;
	private String CPU_KPI; // CPU使用率
	private String MEMORY_KPI; // 物理内存使用率
	private String MAC;
	private String EQ_TYPE; // 服务器类型,刀片机、IBM小型机，根据实际需求灵活
	private int APPID; // 编号
	private Integer WORKSTATUS;// 运行状态
	private String HOSTIDS; // 同一业务系统下部署机的id集合
	private String HOSTUSERNAME;

	public String getHOSTUSERNAME() {
		return HOSTUSERNAME;
	}

	public void setHOSTUSERNAME(String hostusername) {
		HOSTUSERNAME = hostusername;
	}

	public Integer getWORKSTATUS() {
		return WORKSTATUS;
	}

	public void setWORKSTATUS(Integer workstatus) {
		WORKSTATUS = workstatus;
	}

	public String getHOSTIDS() {
		return HOSTIDS;
	}

	public void setHOSTIDS(String hostids) {
		HOSTIDS = hostids;
	}

	public int getAPPID() {
		return APPID;
	}

	public void setAPPID(int appid) {
		APPID = appid;
	}

	public String getEQ_TYPE() {
		return EQ_TYPE;
	}

	public void setEQ_TYPE(String eq_type) {
		EQ_TYPE = eq_type;
	}

	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mac) {
		MAC = mac;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String status) {
		STATUS = status;
	}

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

	public String getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(String updatetime) {
		UPDATETIME = updatetime;
	}

	public int getHOSTPORT() {
		return HOSTPORT;
	}

	public void setHOSTPORT(int hostport) {
		HOSTPORT = hostport;
	}

	public String getVLAN() {
		return VLAN;
	}

	public void setVLAN(String vlan) {
		VLAN = vlan;
	}

	public String getVLANIP() {
		return VLANIP;
	}

	public void setVLANIP(String vLANIP) {
		VLANIP = vLANIP;
	}

	public String getAPPNAMES() {
		return APPNAMES;
	}

	public void setAPPNAMES(String appnames) {
		APPNAMES = appnames;
	}

	public String getKBP_CLASS() {
		return KBP_CLASS;
	}

	public void setKBP_CLASS(String kBPCLASS) {
		KBP_CLASS = kBPCLASS;
	}

	public String getDEVICE_ID() {
		return DEVICE_ID;
	}

	public void setDEVICE_ID(String dEVICEID) {
		DEVICE_ID = dEVICEID;
	}

	public String getCPU_KPI() {
		return CPU_KPI;
	}

	public void setCPU_KPI(String cPUKPI) {
		CPU_KPI = cPUKPI;
	}

	public String getMEMORY_KPI() {
		return MEMORY_KPI;
	}

	public void setMEMORY_KPI(String mEMORYKPI) {
		MEMORY_KPI = mEMORYKPI;
	}

}
