package com.sitech.basd.envmanager.domain.condevice;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class CondeviceObj extends BaseObj{
	
	private int DEVICE_ID;// 设备ID
	private String DEVICE_NAME;// 设备名称
	private String DEVICE_NAME_EN;// 设备英文名称
	private String DEVICE_CODE;// 设备编码
	private String DEVICE_TYPE;// 设备类型
	private String DEVICE_DESC;// 设备描述
	private String STATUSE;// 设备状态
	private String TYPE_NAME;// 设备类型名
	
	
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

	public String getTYPE_NAME() {
		return TYPE_NAME;
	}

	public void setTYPE_NAME(String type_name) {
		TYPE_NAME = type_name;
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
