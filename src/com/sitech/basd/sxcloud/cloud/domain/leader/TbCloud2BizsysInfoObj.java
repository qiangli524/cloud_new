package com.sitech.basd.sxcloud.cloud.domain.leader;

/**
 * 业务系统表
 * 
 * @author zhangwj
 * @Date 2011.12.2
 */
public class TbCloud2BizsysInfoObj {

	private String SYS_ID = null; // 系统编码

	private String SYS_NAME = null; // 系统名称

	private String REGION_ID = null;// 所属域编码

	private String SYS_DESC = null; // 系统描述
	
	private String m_id = null;//机房
	
	private int STATU = 0;//状态
	
	/*
	 * 系统状态
	 * SYS_STATUS_NORMAL,SYS_STATUS_WARNING,SYS_STATUS_TERMINATION
	 */
	private int SYS_STATUS = 0; 

	public int getSYS_STATUS() {
		return SYS_STATUS;
	}

	public void setSYS_STATUS(int sys_status) {
		SYS_STATUS = sys_status;
	}

	public String getREGION_ID() {
		return REGION_ID;
	}

	public void setREGION_ID(String region_id) {
		REGION_ID = region_id;
	}

	public String getSYS_DESC() {
		return SYS_DESC;
	}

	public void setSYS_DESC(String sys_desc) {
		SYS_DESC = sys_desc;
	}

	public String getSYS_ID() {
		return SYS_ID;
	}

	public void setSYS_ID(String sys_id) {
		SYS_ID = sys_id;
	}

	public String getSYS_NAME() {
		return SYS_NAME;
	}

	public void setSYS_NAME(String sys_name) {
		SYS_NAME = sys_name;
	}

	public int getSTATU() {
		return STATU;
	}

	public void setSTATU(int statu) {
		STATU = statu;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

}
