package com.sitech.basd.yicloud.web.ip.form;

import java.util.List;


/**
 * 
 * Title: TbYicloudBusinessAppObj Description: IP类 Copyright: Copyright (c)2012
 * Company: SI-TECH
 * 
 * @author taoxue
 * @version 1.0
 */
public class TbYicloudAppIpForm {
	private int ID;
	private int APP_ID;// 业务应用ID
	private int BIND_DEVICE;// 绑定设备主机ID
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
	private List resultList;// 结果列表
	private List appIdList;// 业务应用ID列表
	private List bindDeviceList;// 绑定主机ID列表
	private List operatorList;// 运营商列表
	private List isUseList;// 是否使用列表
	private List portTypeList;// 端口类型列表
	private List ipTypeList;// IP类型列表
	private List isOpenList;// 是否开放列表
	private List isBalanceList;// 是否负载均衡列表
	private int SELECT_APP_ID;// 所选用用ID
	private int SELECT_BIND_DEVICE;// 所选绑定主机ID
	private String SELECT_ISUSE;
	private String SELECT_TELECOM_OPERATOR;
	private String SELECT_PORT_TYPE;
	private String SELECT_IP_TYPE;
	private String SELECT_ISIPOPEN;
	private String SELECT_ISLOADBALANCE;
	private int flag;// 判断操作是插入还是更新
	private int BIND_ID;// 绑定设备的ID（从别表查出）

	public String getSELECT_ISUSE() {
		return SELECT_ISUSE;
	}

	public void setSELECT_ISUSE(String select_isuse) {
		SELECT_ISUSE = select_isuse;
	}

	public String getSELECT_TELECOM_OPERATOR() {
		return SELECT_TELECOM_OPERATOR;
	}

	public void setSELECT_TELECOM_OPERATOR(String select_telecom_operator) {
		SELECT_TELECOM_OPERATOR = select_telecom_operator;
	}

	public String getSELECT_PORT_TYPE() {
		return SELECT_PORT_TYPE;
	}

	public void setSELECT_PORT_TYPE(String select_port_type) {
		SELECT_PORT_TYPE = select_port_type;
	}

	public String getSELECT_IP_TYPE() {
		return SELECT_IP_TYPE;
	}

	public void setSELECT_IP_TYPE(String select_ip_type) {
		SELECT_IP_TYPE = select_ip_type;
	}

	public String getSELECT_ISIPOPEN() {
		return SELECT_ISIPOPEN;
	}

	public void setSELECT_ISIPOPEN(String select_isipopen) {
		SELECT_ISIPOPEN = select_isipopen;
	}

	public String getSELECT_ISLOADBALANCE() {
		return SELECT_ISLOADBALANCE;
	}

	public void setSELECT_ISLOADBALANCE(String select_isloadbalance) {
		SELECT_ISLOADBALANCE = select_isloadbalance;
	}

	public List getIsUseList() {
		return isUseList;
	}

	public void setIsUseList(List isUseList) {
		this.isUseList = isUseList;
	}

	public List getPortTypeList() {
		return portTypeList;
	}

	public void setPortTypeList(List portTypeList) {
		this.portTypeList = portTypeList;
	}

	public List getIpTypeList() {
		return ipTypeList;
	}

	public void setIpTypeList(List ipTypeList) {
		this.ipTypeList = ipTypeList;
	}

	public List getIsOpenList() {
		return isOpenList;
	}

	public void setIsOpenList(List isOpenList) {
		this.isOpenList = isOpenList;
	}

	public List getIsBalanceList() {
		return isBalanceList;
	}

	public void setIsBalanceList(List isBalanceList) {
		this.isBalanceList = isBalanceList;
	}

	public List getOperatorList() {
		return operatorList;
	}

	public void setOperatorList(List operatorList) {
		this.operatorList = operatorList;
	}

	public int getBIND_ID() {
		return BIND_ID;
	}

	public void setBIND_ID(int bind_id) {
		BIND_ID = bind_id;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public List getBindDeviceList() {
		return bindDeviceList;
	}

	public void setBindDeviceList(List bindDeviceList) {
		this.bindDeviceList = bindDeviceList;
	}

	public int getSELECT_APP_ID() {
		return SELECT_APP_ID;
	}

	public void setSELECT_APP_ID(int select_app_id) {
		SELECT_APP_ID = select_app_id;
	}

	public int getSELECT_BIND_DEVICE() {
		return SELECT_BIND_DEVICE;
	}

	public void setSELECT_BIND_DEVICE(int select_bind_device) {
		SELECT_BIND_DEVICE = select_bind_device;
	}

	public List getAppIdList() {
		return appIdList;
	}

	public void setAppIdList(List appIdList) {
		this.appIdList = appIdList;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
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
