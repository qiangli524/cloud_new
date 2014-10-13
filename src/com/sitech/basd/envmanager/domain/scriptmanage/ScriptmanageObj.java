package com.sitech.basd.envmanager.domain.scriptmanage;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class ScriptmanageObj extends BaseObj{
	
	private String s_id;
	
	private String host_id;
	
	private String SCRIPT_CONTENT;//脚本内容
	
	private String CREATE_TIME;//创建时间
	
	private String SCRIPT_USE;//用途
	
	private String id;
	
	private String EQ_IP;
	
	private String HOSTUSERNAME;
	
	private String HOSRPWD;
	
	
	private String SCRIPT_IP;
	
	private String SCRIPT_USERNAME;
	
	private String SCRIPT_PASSWORD;
	
	
	private String config_id;
	
	private String EQ_ID;
	
	private int type;
	
	

	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getEQ_ID() {
		return EQ_ID;
	}

	public void setEQ_ID(String eq_id) {
		EQ_ID = eq_id;
	}

	public String getConfig_id() {
		return config_id;
	}

	public void setConfig_id(String config_id) {
		this.config_id = config_id;
	}

	public String getEQ_IP() {
		return EQ_IP;
	}

	public void setEQ_IP(String eq_ip) {
		EQ_IP = eq_ip;
	}

	public String getHOSTUSERNAME() {
		return HOSTUSERNAME;
	}

	public void setHOSTUSERNAME(String hostusername) {
		HOSTUSERNAME = hostusername;
	}

	public String getHOSRPWD() {
		return HOSRPWD;
	}

	public void setHOSRPWD(String hosrpwd) {
		HOSRPWD = hosrpwd;
	}

	public String getS_id() {
		return s_id;
	}

	public void setS_id(String s_id) {
		this.s_id = s_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHost_id() {
		return host_id;
	}

	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}

	public String getSCRIPT_CONTENT() {
		return SCRIPT_CONTENT;
	}

	public void setSCRIPT_CONTENT(String script_content) {
		SCRIPT_CONTENT = script_content;
	}

	public String getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(String create_time) {
		CREATE_TIME = create_time;
	}

	public String getSCRIPT_USE() {
		return SCRIPT_USE;
	}

	public void setSCRIPT_USE(String script_use) {
		SCRIPT_USE = script_use;
	}

	public String getSCRIPT_IP() {
		return SCRIPT_IP;
	}

	public void setSCRIPT_IP(String script_ip) {
		SCRIPT_IP = script_ip;
	}

	public String getSCRIPT_USERNAME() {
		return SCRIPT_USERNAME;
	}

	public void setSCRIPT_USERNAME(String script_username) {
		SCRIPT_USERNAME = script_username;
	}

	public String getSCRIPT_PASSWORD() {
		return SCRIPT_PASSWORD;
	}

	public void setSCRIPT_PASSWORD(String script_password) {
		SCRIPT_PASSWORD = script_password;
	}
	
	
	

}
