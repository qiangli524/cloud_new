package com.sitech.basd.envmanager.web.condevice.form;

import java.util.List;

public class CondeviceForm {
	
	private int DEVICE_ID;// 设备ID
	private String DEVICE_NAME;// 设备名称
	private String DEVICE_NAME_EN;// 设备英文名称
	private String DEVICE_CODE;// 设备编码
	private String DEVICE_TYPE;// 设备类型
	private String DEVICE_DESC;// 设备描述
	private String STATUSE;// 设备状态
	private List resultList;// 结果列表
	private int flag;
	private String TYPE_NAME;// 设备类型名
	// 主机相关字段
	private String eq_name = null;// 服务器名称
	private String eq_type = null;// 服务器类型
	private String eq_ip = null;// 服务器IP
	private String eq_hostname = null;// 服务器主机名称
	private String hasvertual = null;// 是否支持虚拟化
	private String cq_id;// 机柜ID
	private String c_addr;// 机柜位置
	private String eq_id = null;// 服务器ID
	private int control=0; // 云平台能否管控 0 不管控、1 管控
	private String PROTOCOL = "ssh"; // 远程连接主机协议(Telnet、Ssh等)
	private List cabinetList;// 机柜列表
	// 交换机或路由器相关字段
	private int ID;// 交换机编号
	private String NAME;// 交换机名称
	private String TYPE;// 交换机型号
	private String LOCATION;// 物理位置
	private String PORT_100M_NUMS;// 百兆端口总数
	private String PORT_100M_USABLE;// 百兆端口可用数
	private String PORT_RJ45_NUMS;// 千兆端口总数
	private String PORT_RJ45_USABLE;// 千兆端口可用数
	private String PORT_FIBER_NUMS;// 光纤以太端口总数
	private String PORT_FIBER_USABLE;// 光纤端口可用数
	private String PORT_ATM_NUMS;// ATM端口总数
	private String PORT_ATM_USABLE;// ATM端口可用数
	private String PORT_EI_NUMS;// EI端口总数
	private String PORT_EI_USABLE;// EI端口可用数
	private String IP_ADDR;// 管理IP地址
	
	
	
	
	private String LD_ID ;//INTEGER ,--id
	private String MECH_ROOM  ;//VARCHAR2(30) ,--机房
	private String CAPITAL_TYPE  ;//VARCHAR2(30) ,--主机类型
	private String MECH_ID  ;//VARCHAR2(100) ,--内存空闲
	private String CAPITAL_ID  ;//VARCHAR2(100) ,--设备ID
	private String MECH_TYPE  ;//VARCHAR2(100) ,--类号
	private String MECH_CONF   ;//VARCHAR2(300),--配制
	private String SYS_SYSTEM ;// VARCHAR2(100),--操作系统
	private String SYS_HOSTNAME ;//  VARCHAR2(100),--主机名
	private String SYS_VM  ;// VARCHAR2(100) ,--VM分区
	private String IP_PHYSICS ;// VARCHAR2(100), --物理IP地址
	private String IP_VIRTUAL ;// VARCHAR2(100), --虚拟机IP地址
	private String IP_ILO ;//  VARCHAR2(100) ,--管理地址
	private String PWD_SYSTEM  ;//VARCHAR2(100), --系统口令
	private String PWD_CONSOLE ;// VARCHAR2(100), --console 用户/口令
	private String STORE ;//  VARCHAR2(100), --对应存储
	private String USE_DOMAN ;// VARCHAR2(100), --归属域
	private String USE_RES ;// VARCHAR2(100), --资源池
	private String USE_DEPART ;//  VARCHAR2(100),--使用部门
	private String USE_DESCRIP ;//  VARCHAR2(100),-- 用户说明
	private String MANAG_PERSON  ;// VARCHAR2(100), --负责人
	private String MANAG_RECORD ;//  VARCHAR2(100), --借用记录
	private String MANAG_REPAIR ;//  VARCHAR2(100), --维修记录
	private String MANAG_USE ;//  VARCHAR2(100), --是否在保
	private String MANAG_USABLE ;//  VARCHAR2(100), --是否需要报废
	private String MANAG_DESCRIP  ;// VARCHAR2(100), --管理说明
	
	
	private int env_id ;//INTEGER ,--序号
	private String DOMAIN  ;//VARCHAR2(30) ,--系统域
	private String SOURCE  ;//VARCHAR2(30) ,--资源域
	private String CLASS  ;//VARCHAR2(30) ,--分类
	private String HOSTTYPE ;// VARCHAR2(30) ,--主机类型
	private String HOSTNUM ;// VARCHAR2(30) ,--型号
	private String DESCRIPTION ;//  VARCHAR2(200),--配置
	private String SYSTEM ;// VARCHAR2(30),--操作系统
	private String HOSTNAME ;//  VARCHAR2(30),--主机名
	private String IP ;//  VARCHAR2(30) ,--IP地址
	private String PRODUCT ;// VARCHAR2(30), --使用产品线
	private String DEVEPROD ;// VARCHAR2(30), --研发产品
	private String SID ;//  VARCHAR2(30) ,--实例
	private String TABLESPACE ;// NUMBER, --表空间
	private String FILESYSNAM ;// VARCHAR2(30), --文件系统
	private String FILEAPPNUM ;//  NUMBER, --文件系统大小
	private String CPUUSED ;// VARCHAR2(10), --CPU
	private String MEM ;// NUMBER, --内存
	private String FILEUSERD ;// NUMBER,--文件系统
	private String FILEUSEPER ;//  VARCHAR2(30),-- 文件系统使用率
	private String SID1 ;//  VARCHAR2(30), --实例
	private String TABSPAUSED ;//  NUMBER, --表空间
	private String TABSPAUSEPER ;//   VARCHAR2(30), --表空间使率
	private String SGA ;//   VARCHAR2(30), --内存
	private String CPULEFT ;//   VARCHAR2(30), --CPU
	private String MEMLEFT ;//   VARCHAR2(30), --内存
	private String STORAGE ;//  VARCHAR2(30),--存储
	private String E_DATE ;//   VARCHAR2(10)--更新时间

	private String MAC;
	
	
	private int ip_id; // NUMBER,--ID
	private String VIRTUAL_IP ; //VARCHAR2(256),--虚拟IP地址
	private String DEPART ; //VARCHAR2(256),--使用部门
	private List ipList;
	
	
	
	
	public List getIpList() {
		return ipList;
	}

	public void setIpList(List ipList) {
		this.ipList = ipList;
	}

	public int getIp_id() {
		return ip_id;
	}

	public void setIp_id(int ip_id) {
		this.ip_id = ip_id;
	}

	public String getVIRTUAL_IP() {
		return VIRTUAL_IP;
	}

	public void setVIRTUAL_IP(String virtual_ip) {
		VIRTUAL_IP = virtual_ip;
	}

	public String getDEPART() {
		return DEPART;
	}

	public void setDEPART(String depart) {
		DEPART = depart;
	}

	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mac) {
		MAC = mac;
	}

	public int getEnv_id() {
		return env_id;
	}

	public void setEnv_id(int env_id) {
		this.env_id = env_id;
	}

	public String getDOMAIN() {
		return DOMAIN;
	}

	public void setDOMAIN(String domain) {
		DOMAIN = domain;
	}

	public String getSOURCE() {
		return SOURCE;
	}

	public void setSOURCE(String source) {
		SOURCE = source;
	}

	public String getCLASS() {
		return CLASS;
	}

	public void setCLASS(String class1) {
		CLASS = class1;
	}

	public String getHOSTTYPE() {
		return HOSTTYPE;
	}

	public void setHOSTTYPE(String hosttype) {
		HOSTTYPE = hosttype;
	}

	public String getHOSTNUM() {
		return HOSTNUM;
	}

	public void setHOSTNUM(String hostnum) {
		HOSTNUM = hostnum;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String description) {
		DESCRIPTION = description;
	}

	public String getSYSTEM() {
		return SYSTEM;
	}

	public void setSYSTEM(String system) {
		SYSTEM = system;
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

	public String getPRODUCT() {
		return PRODUCT;
	}

	public void setPRODUCT(String product) {
		PRODUCT = product;
	}

	public String getDEVEPROD() {
		return DEVEPROD;
	}

	public void setDEVEPROD(String deveprod) {
		DEVEPROD = deveprod;
	}

	public String getSID() {
		return SID;
	}

	public void setSID(String sid) {
		SID = sid;
	}

	public String getTABLESPACE() {
		return TABLESPACE;
	}

	public void setTABLESPACE(String tablespace) {
		TABLESPACE = tablespace;
	}

	public String getFILESYSNAM() {
		return FILESYSNAM;
	}

	public void setFILESYSNAM(String filesysnam) {
		FILESYSNAM = filesysnam;
	}

	public String getFILEAPPNUM() {
		return FILEAPPNUM;
	}

	public void setFILEAPPNUM(String fileappnum) {
		FILEAPPNUM = fileappnum;
	}

	public String getCPUUSED() {
		return CPUUSED;
	}

	public void setCPUUSED(String cpuused) {
		CPUUSED = cpuused;
	}

	public String getMEM() {
		return MEM;
	}

	public void setMEM(String mem) {
		MEM = mem;
	}

	public String getFILEUSERD() {
		return FILEUSERD;
	}

	public void setFILEUSERD(String fileuserd) {
		FILEUSERD = fileuserd;
	}

	public String getFILEUSEPER() {
		return FILEUSEPER;
	}

	public void setFILEUSEPER(String fileuseper) {
		FILEUSEPER = fileuseper;
	}

	public String getSID1() {
		return SID1;
	}

	public void setSID1(String sid1) {
		SID1 = sid1;
	}

	public String getTABSPAUSED() {
		return TABSPAUSED;
	}

	public void setTABSPAUSED(String tabspaused) {
		TABSPAUSED = tabspaused;
	}

	public String getTABSPAUSEPER() {
		return TABSPAUSEPER;
	}

	public void setTABSPAUSEPER(String tabspauseper) {
		TABSPAUSEPER = tabspauseper;
	}

	public String getSGA() {
		return SGA;
	}

	public void setSGA(String sga) {
		SGA = sga;
	}

	public String getCPULEFT() {
		return CPULEFT;
	}

	public void setCPULEFT(String cpuleft) {
		CPULEFT = cpuleft;
	}

	public String getMEMLEFT() {
		return MEMLEFT;
	}

	public void setMEMLEFT(String memleft) {
		MEMLEFT = memleft;
	}

	public String getSTORAGE() {
		return STORAGE;
	}

	public void setSTORAGE(String storage) {
		STORAGE = storage;
	}

	public String getE_DATE() {
		return E_DATE;
	}

	public void setE_DATE(String e_date) {
		E_DATE = e_date;
	}

	public String getLD_ID() {
		return LD_ID;
	}

	public void setLD_ID(String ld_id) {
		LD_ID = ld_id;
	}

	public String getMECH_ROOM() {
		return MECH_ROOM;
	}

	public void setMECH_ROOM(String mech_room) {
		MECH_ROOM = mech_room;
	}

	public String getCAPITAL_TYPE() {
		return CAPITAL_TYPE;
	}

	public void setCAPITAL_TYPE(String capital_type) {
		CAPITAL_TYPE = capital_type;
	}

	public String getMECH_ID() {
		return MECH_ID;
	}

	public void setMECH_ID(String mech_id) {
		MECH_ID = mech_id;
	}

	public String getCAPITAL_ID() {
		return CAPITAL_ID;
	}

	public void setCAPITAL_ID(String capital_id) {
		CAPITAL_ID = capital_id;
	}

	public String getMECH_TYPE() {
		return MECH_TYPE;
	}

	public void setMECH_TYPE(String mech_type) {
		MECH_TYPE = mech_type;
	}

	public String getMECH_CONF() {
		return MECH_CONF;
	}

	public void setMECH_CONF(String mech_conf) {
		MECH_CONF = mech_conf;
	}

	public String getSYS_SYSTEM() {
		return SYS_SYSTEM;
	}

	public void setSYS_SYSTEM(String sys_system) {
		SYS_SYSTEM = sys_system;
	}

	public String getSYS_HOSTNAME() {
		return SYS_HOSTNAME;
	}

	public void setSYS_HOSTNAME(String sys_hostname) {
		SYS_HOSTNAME = sys_hostname;
	}

	public String getSYS_VM() {
		return SYS_VM;
	}

	public void setSYS_VM(String sys_vm) {
		SYS_VM = sys_vm;
	}

	public String getIP_PHYSICS() {
		return IP_PHYSICS;
	}

	public void setIP_PHYSICS(String ip_physics) {
		IP_PHYSICS = ip_physics;
	}

	public String getIP_VIRTUAL() {
		return IP_VIRTUAL;
	}

	public void setIP_VIRTUAL(String ip_virtual) {
		IP_VIRTUAL = ip_virtual;
	}

	public String getIP_ILO() {
		return IP_ILO;
	}

	public void setIP_ILO(String ip_ilo) {
		IP_ILO = ip_ilo;
	}

	public String getPWD_SYSTEM() {
		return PWD_SYSTEM;
	}

	public void setPWD_SYSTEM(String pwd_system) {
		PWD_SYSTEM = pwd_system;
	}

	public String getPWD_CONSOLE() {
		return PWD_CONSOLE;
	}

	public void setPWD_CONSOLE(String pwd_console) {
		PWD_CONSOLE = pwd_console;
	}

	public String getSTORE() {
		return STORE;
	}

	public void setSTORE(String store) {
		STORE = store;
	}

	public String getUSE_DOMAN() {
		return USE_DOMAN;
	}

	public void setUSE_DOMAN(String use_doman) {
		USE_DOMAN = use_doman;
	}

	public String getUSE_RES() {
		return USE_RES;
	}

	public void setUSE_RES(String use_res) {
		USE_RES = use_res;
	}

	public String getUSE_DEPART() {
		return USE_DEPART;
	}

	public void setUSE_DEPART(String use_depart) {
		USE_DEPART = use_depart;
	}

	public String getUSE_DESCRIP() {
		return USE_DESCRIP;
	}

	public void setUSE_DESCRIP(String use_descrip) {
		USE_DESCRIP = use_descrip;
	}

	public String getMANAG_PERSON() {
		return MANAG_PERSON;
	}

	public void setMANAG_PERSON(String manag_person) {
		MANAG_PERSON = manag_person;
	}

	public String getMANAG_RECORD() {
		return MANAG_RECORD;
	}

	public void setMANAG_RECORD(String manag_record) {
		MANAG_RECORD = manag_record;
	}

	public String getMANAG_REPAIR() {
		return MANAG_REPAIR;
	}

	public void setMANAG_REPAIR(String manag_repair) {
		MANAG_REPAIR = manag_repair;
	}

	public String getMANAG_USE() {
		return MANAG_USE;
	}

	public void setMANAG_USE(String manag_use) {
		MANAG_USE = manag_use;
	}

	public String getMANAG_USABLE() {
		return MANAG_USABLE;
	}

	public void setMANAG_USABLE(String manag_usable) {
		MANAG_USABLE = manag_usable;
	}

	public String getMANAG_DESCRIP() {
		return MANAG_DESCRIP;
	}

	public void setMANAG_DESCRIP(String manag_descrip) {
		MANAG_DESCRIP = manag_descrip;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
	}

	public String getLOCATION() {
		return LOCATION;
	}

	public void setLOCATION(String location) {
		LOCATION = location;
	}

	public String getPORT_100M_NUMS() {
		return PORT_100M_NUMS;
	}

	public void setPORT_100M_NUMS(String port_100m_nums) {
		PORT_100M_NUMS = port_100m_nums;
	}

	public String getPORT_100M_USABLE() {
		return PORT_100M_USABLE;
	}

	public void setPORT_100M_USABLE(String port_100m_usable) {
		PORT_100M_USABLE = port_100m_usable;
	}

	public String getPORT_RJ45_NUMS() {
		return PORT_RJ45_NUMS;
	}

	public void setPORT_RJ45_NUMS(String port_rj45_nums) {
		PORT_RJ45_NUMS = port_rj45_nums;
	}

	public String getPORT_RJ45_USABLE() {
		return PORT_RJ45_USABLE;
	}

	public void setPORT_RJ45_USABLE(String port_rj45_usable) {
		PORT_RJ45_USABLE = port_rj45_usable;
	}

	public String getPORT_FIBER_NUMS() {
		return PORT_FIBER_NUMS;
	}

	public void setPORT_FIBER_NUMS(String port_fiber_nums) {
		PORT_FIBER_NUMS = port_fiber_nums;
	}

	public String getPORT_FIBER_USABLE() {
		return PORT_FIBER_USABLE;
	}

	public void setPORT_FIBER_USABLE(String port_fiber_usable) {
		PORT_FIBER_USABLE = port_fiber_usable;
	}

	public String getPORT_ATM_NUMS() {
		return PORT_ATM_NUMS;
	}

	public void setPORT_ATM_NUMS(String port_atm_nums) {
		PORT_ATM_NUMS = port_atm_nums;
	}

	public String getPORT_ATM_USABLE() {
		return PORT_ATM_USABLE;
	}

	public void setPORT_ATM_USABLE(String port_atm_usable) {
		PORT_ATM_USABLE = port_atm_usable;
	}

	public String getPORT_EI_NUMS() {
		return PORT_EI_NUMS;
	}

	public void setPORT_EI_NUMS(String port_ei_nums) {
		PORT_EI_NUMS = port_ei_nums;
	}

	public String getPORT_EI_USABLE() {
		return PORT_EI_USABLE;
	}

	public void setPORT_EI_USABLE(String port_ei_usable) {
		PORT_EI_USABLE = port_ei_usable;
	}

	public String getIP_ADDR() {
		return IP_ADDR;
	}

	public void setIP_ADDR(String ip_addr) {
		IP_ADDR = ip_addr;
	}

	public List getCabinetList() {
		return cabinetList;
	}

	public void setCabinetList(List cabinetList) {
		this.cabinetList = cabinetList;
	}

	public String getEq_name() {
		return eq_name;
	}

	public void setEq_name(String eq_name) {
		this.eq_name = eq_name;
	}

	public String getEq_type() {
		return eq_type;
	}

	public void setEq_type(String eq_type) {
		this.eq_type = eq_type;
	}

	public String getEq_ip() {
		return eq_ip;
	}

	public void setEq_ip(String eq_ip) {
		this.eq_ip = eq_ip;
	}

	public String getEq_hostname() {
		return eq_hostname;
	}

	public void setEq_hostname(String eq_hostname) {
		this.eq_hostname = eq_hostname;
	}

	public String getHasvertual() {
		return hasvertual;
	}

	public void setHasvertual(String hasvertual) {
		this.hasvertual = hasvertual;
	}

	public String getCq_id() {
		return cq_id;
	}

	public void setCq_id(String cq_id) {
		this.cq_id = cq_id;
	}

	public String getC_addr() {
		return c_addr;
	}

	public void setC_addr(String c_addr) {
		this.c_addr = c_addr;
	}

	public String getEq_id() {
		return eq_id;
	}

	public void setEq_id(String eq_id) {
		this.eq_id = eq_id;
	}

	public int getControl() {
		return control;
	}

	public void setControl(int control) {
		this.control = control;
	}

	public String getPROTOCOL() {
		return PROTOCOL;
	}

	public void setPROTOCOL(String protocol) {
		PROTOCOL = protocol;
	}

	public String getTYPE_NAME() {
		return TYPE_NAME;
	}

	public void setTYPE_NAME(String type_name) {
		TYPE_NAME = type_name;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public int getDEVICE_ID() {
		return DEVICE_ID;
	}

	public void setDEVICE_ID(int device_id) {
		DEVICE_ID = device_id;
	}

	public String getDEVICE_NAME() {
		return DEVICE_NAME;
	}

	public void setDEVICE_NAME(String device_name) {
		DEVICE_NAME = device_name;
	}

	public String getDEVICE_NAME_EN() {
		return DEVICE_NAME_EN;
	}

	public void setDEVICE_NAME_EN(String device_name_en) {
		DEVICE_NAME_EN = device_name_en;
	}

	public String getDEVICE_CODE() {
		return DEVICE_CODE;
	}

	public void setDEVICE_CODE(String device_code) {
		DEVICE_CODE = device_code;
	}

	public String getDEVICE_TYPE() {
		return DEVICE_TYPE;
	}

	public void setDEVICE_TYPE(String device_type) {
		DEVICE_TYPE = device_type;
	}

	public String getDEVICE_DESC() {
		return DEVICE_DESC;
	}

	public void setDEVICE_DESC(String device_desc) {
		DEVICE_DESC = device_desc;
	}

	public String getSTATUSE() {
		return STATUSE;
	}

	public void setSTATUSE(String statuse) {
		STATUSE = statuse;
	}


}
