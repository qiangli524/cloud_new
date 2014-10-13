package com.sitech.basd.yicloud.domain.switches;

public class SwitchObj {
	private int ID;
	private int DEVICE_ID;// 所属设备
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

	public int getDEVICE_ID() {
		return DEVICE_ID;
	}

	public void setDEVICE_ID(int device_id) {
		DEVICE_ID = device_id;
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

}
