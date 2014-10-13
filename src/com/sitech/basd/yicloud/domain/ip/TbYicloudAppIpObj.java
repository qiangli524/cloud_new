package com.sitech.basd.yicloud.domain.ip;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;


/**
 * 
 * Title: TbYicloudBusinessAppObj Description: IP类 
 * Company: SI-TECH
 * Copyright: Copyright (c)2012
 * @author taoxue
 * @version 1.0
 */
public class TbYicloudAppIpObj extends BaseObj {
	private int ID;
	private int APP_ID;// 业务应用ID
	private int BIND_DEVICE;// 绑定设备主机ID
	private int BIND_ID;// 绑定设备的ID（从别表查出）
	private String NAME_ZH;// 获取设备主机ID
	private String ISUSE;// 是否使用
	private String MAX_BANDWIDTH;// 分配最大带宽
	private String TELECOM_OPERATOR;// 运营商(联通/电信)
	private String MIDDLEWARE;// 中间件(tomcat/weblogic)
	private String PORT;// 端口
	private String PORT_TYPE;// 端口类型(启/停/监听/测试)
	private String BANDWIDTH_MONITER;// 带宽监控
	private String IP;
	private String IP_TYPE;// 公网IP类型(共享/独享)
	private String ISIPOPEN;// 公网是否开放 [是/否]
	private String PORT_MAPPING;// 公网映射端口
	private String LOAD_VIRTUAL_IP;// 负载虚拟IP
	private String ISLOADBALANCE;// 是否负载均衡

	public int getBIND_ID() {
		return BIND_ID;
	}

	public void setBIND_ID(int bind_id) {
		BIND_ID = bind_id;
	}

	public String getNAME_ZH() {
		return NAME_ZH;
	}

	public void setNAME_ZH(String name_zh) {
		NAME_ZH = name_zh;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public int getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(int app_id) {
		APP_ID = app_id;
	}

	public int getBIND_DEVICE() {
		return BIND_DEVICE;
	}

	public void setBIND_DEVICE(int bind_device) {
		BIND_DEVICE = bind_device;
	}

	public String getISUSE() {
		return ISUSE;
	}

	public void setISUSE(String isuse) {
		ISUSE = isuse;
	}

	public String getMAX_BANDWIDTH() {
		return MAX_BANDWIDTH;
	}

	public void setMAX_BANDWIDTH(String max_bandwidth) {
		MAX_BANDWIDTH = max_bandwidth;
	}

	public String getTELECOM_OPERATOR() {
		return TELECOM_OPERATOR;
	}

	public void setTELECOM_OPERATOR(String telecom_operator) {
		TELECOM_OPERATOR = telecom_operator;
	}

	public String getMIDDLEWARE() {
		return MIDDLEWARE;
	}

	public void setMIDDLEWARE(String middleware) {
		MIDDLEWARE = middleware;
	}

	public String getPORT() {
		return PORT;
	}

	public void setPORT(String port) {
		PORT = port;
	}

	public String getPORT_TYPE() {
		return PORT_TYPE;
	}

	public void setPORT_TYPE(String port_type) {
		PORT_TYPE = port_type;
	}

	public String getBANDWIDTH_MONITER() {
		return BANDWIDTH_MONITER;
	}

	public void setBANDWIDTH_MONITER(String bandwidth_moniter) {
		BANDWIDTH_MONITER = bandwidth_moniter;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String ip) {
		IP = ip;
	}

	public String getIP_TYPE() {
		return IP_TYPE;
	}

	public void setIP_TYPE(String ip_type) {
		IP_TYPE = ip_type;
	}

	public String getISIPOPEN() {
		return ISIPOPEN;
	}

	public void setISIPOPEN(String isipopen) {
		ISIPOPEN = isipopen;
	}

	public String getPORT_MAPPING() {
		return PORT_MAPPING;
	}

	public void setPORT_MAPPING(String port_mapping) {
		PORT_MAPPING = port_mapping;
	}

	public String getLOAD_VIRTUAL_IP() {
		return LOAD_VIRTUAL_IP;
	}

	public void setLOAD_VIRTUAL_IP(String load_virtual_ip) {
		LOAD_VIRTUAL_IP = load_virtual_ip;
	}

	public String getISLOADBALANCE() {
		return ISLOADBALANCE;
	}

	public void setISLOADBALANCE(String isloadbalance) {
		ISLOADBALANCE = isloadbalance;
	}

}
