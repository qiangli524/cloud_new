package com.sitech.basd.yicloud.web.device.form;

import java.util.List;

public class DeviceForm {
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
	private int control; // 云平台能否管控 0 不管控、1 管控
	private String PROTOCOL; // 远程连接主机协议(Telnet、Ssh等)
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
