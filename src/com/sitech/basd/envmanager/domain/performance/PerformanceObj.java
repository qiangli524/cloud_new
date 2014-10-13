package com.sitech.basd.envmanager.domain.performance;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class PerformanceObj  extends BaseObj{
	
	private String CF_ID; // INTEGER ,--id
	
	private String CF_IP ; // VARCHAR2(30); // --IP地址
	
	private String MEM_USED ; // VARCHAR2(30) ,--内存使用
	
	private String MEM_FREE ; // VARCHAR2(30) ,--内存空闲
	
	private String CPU_IDLE ; // VARCHAR2(30) ,--CPU空闲
	
	private String STOR_FREE ; // VARCHAR2(200) ,--存储剩余
	
	private String STOR_ALL  ; // VARCHAR2(30)--存储总量
	
	private String CHE_TIME  ; // VARCHAR2(30)--检查时间
	
	private String HOSTNAME  ; // VARCHAR2(30)--机器名称

	public String getCF_ID() {
		return CF_ID;
	}

	public void setCF_ID(String cf_id) {
		CF_ID = cf_id;
	}

	public String getCF_IP() {
		return CF_IP;
	}

	public void setCF_IP(String cf_ip) {
		CF_IP = cf_ip;
	}

	public String getMEM_USED() {
		return MEM_USED;
	}

	public void setMEM_USED(String mem_used) {
		MEM_USED = mem_used;
	}

	public String getMEM_FREE() {
		return MEM_FREE;
	}

	public void setMEM_FREE(String mem_free) {
		MEM_FREE = mem_free;
	}

	public String getCPU_IDLE() {
		return CPU_IDLE;
	}

	public void setCPU_IDLE(String cpu_idle) {
		CPU_IDLE = cpu_idle;
	}

	public String getSTOR_FREE() {
		return STOR_FREE;
	}

	public void setSTOR_FREE(String stor_free) {
		STOR_FREE = stor_free;
	}

	public String getSTOR_ALL() {
		return STOR_ALL;
	}

	public void setSTOR_ALL(String stor_all) {
		STOR_ALL = stor_all;
	}

	public String getCHE_TIME() {
		return CHE_TIME;
	}

	public void setCHE_TIME(String che_time) {
		CHE_TIME = che_time;
	}

	public String getHOSTNAME() {
		return HOSTNAME;
	}

	public void setHOSTNAME(String hostname) {
		HOSTNAME = hostname;
	}
	
	
	
	
	

}
