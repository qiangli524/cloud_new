package com.sitech.basd.sxcloud.cloud.domain.resource;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class TbCloud2HostPoolObj extends BaseObj {

	private String ID;// 主机池主键id

	private int h_uuid;// hostpool UUID

	private String NAME; // 主机池名称

	private String INS_DATE; // 创建时间

	private String POOL_DESC; // 主机池描述

	private String POOL_TYPE; // 主机池类型

	private int shu;

	private String poolId;

	// =====================================
	private String EQ_ID; // 服务器编号
	private String EQ_NAME; // 服务器名称
	private String EQ_TYPE;// 服务器类型,刀片机、IBM小型机，根据实际需求灵活
	private String CQ_ID;// 服务器所在机柜编号
	private String EQ_IP;// 服务器IP地址
	private String EQ_HOSTNAME; // 服务器主机名称
	private String EQ_TEMPERATURE;// 服务器温度
	// private String INS_DATE_T;
	private String HASVERTUAL;// 0,不可以虚拟化，1可以虚拟化，3为xen虚拟化，4为vmware虚拟化
	private String POSITION; // 所在机柜位置
	private String HOST_POOL_ID;

	private String VLANIP;// 服务IP

	public String getVLANIP() {
		return VLANIP;
	}

	public void setVLANIP(String vlanip) {
		VLANIP = vlanip;
	}

	public String getHOST_POOL_ID() {
		return HOST_POOL_ID;
	}

	public void setHOST_POOL_ID(String host_pool_id) {
		HOST_POOL_ID = host_pool_id;
	}

	public String getEQ_ID() {
		return EQ_ID;
	}

	public void setEQ_ID(String eq_id) {
		EQ_ID = eq_id;
	}

	public String getEQ_NAME() {
		return EQ_NAME;
	}

	public void setEQ_NAME(String eq_name) {
		EQ_NAME = eq_name;
	}

	public String getEQ_TYPE() {
		return EQ_TYPE;
	}

	public void setEQ_TYPE(String eq_type) {
		EQ_TYPE = eq_type;
	}

	public String getCQ_ID() {
		return CQ_ID;
	}

	public void setCQ_ID(String cq_id) {
		CQ_ID = cq_id;
	}

	public String getEQ_IP() {
		return EQ_IP;
	}

	public void setEQ_IP(String eq_ip) {
		EQ_IP = eq_ip;
	}

	public String getEQ_HOSTNAME() {
		return EQ_HOSTNAME;
	}

	public void setEQ_HOSTNAME(String eq_hostname) {
		EQ_HOSTNAME = eq_hostname;
	}

	public String getEQ_TEMPERATURE() {
		return EQ_TEMPERATURE;
	}

	public void setEQ_TEMPERATURE(String eq_temperature) {
		EQ_TEMPERATURE = eq_temperature;
	}

	public String getHASVERTUAL() {
		return HASVERTUAL;
	}

	public void setHASVERTUAL(String hasvertual) {
		HASVERTUAL = hasvertual;
	}

	public String getPOSITION() {
		return POSITION;
	}

	public void setPOSITION(String position) {
		POSITION = position;
	}

	public String getPoolId() {
		return poolId;
	}

	public void setPoolId(String poolId) {
		this.poolId = poolId;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public int getH_uuid() {
		return h_uuid;
	}

	public void setH_uuid(int h_uuid) {
		this.h_uuid = h_uuid;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getINS_DATE() {
		return INS_DATE;
	}

	public void setINS_DATE(String ins_date) {
		INS_DATE = ins_date;
	}

	public String getPOOL_DESC() {
		return POOL_DESC;
	}

	public void setPOOL_DESC(String pool_desc) {
		POOL_DESC = pool_desc;
	}

	public int getShu() {
		return shu;
	}

	public void setShu(int shu) {
		this.shu = shu;
	}

	public String getPOOL_TYPE() {
		return POOL_TYPE;
	}

	public void setPOOL_TYPE(String pool_type) {
		POOL_TYPE = pool_type;
	}

}
