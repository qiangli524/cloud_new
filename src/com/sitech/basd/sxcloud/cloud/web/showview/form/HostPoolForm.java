package com.sitech.basd.sxcloud.cloud.web.showview.form;

import java.util.List;

public class HostPoolForm {

	private String ID;// 主机池主键id

	private String NAME; // 主机池名称

	private String INS_DATE; // 创建时间

	private String POOL_DESC; // 主机池描述

	private String POOL_TYPE; // 主机池类型

	private int shu;

	private List LIST_POOL;

	private List LIST_NAME;

	private int flag;

	private String HOST_POOL_ID;

	private String VLANIP;// 服务IP

	public String getVLANIP() {
		return VLANIP;
	}

	public void setVLANIP(String vlanip) {
		VLANIP = vlanip;
	}

	public List getLIST_NAME() {
		return LIST_NAME;
	}

	public void setLIST_NAME(List list_name) {
		LIST_NAME = list_name;
	}

	public String getHOST_POOL_ID() {
		return HOST_POOL_ID;
	}

	public void setHOST_POOL_ID(String host_pool_id) {
		HOST_POOL_ID = host_pool_id;
	}

	public int getShu() {
		return shu;
	}

	public void setShu(int shu) {
		this.shu = shu;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
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

	public List getLIST_POOL() {
		return LIST_POOL;
	}

	public void setLIST_POOL(List list_pool) {
		LIST_POOL = list_pool;
	}

	public String getPOOL_TYPE() {
		return POOL_TYPE;
	}

	public void setPOOL_TYPE(String pool_type) {
		POOL_TYPE = pool_type;
	}

}
