package com.sitech.basd.yicloud.domain.device;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class SnmpParamObj extends BaseObj {
	private String id;
	private String version;
	private String ip;
	private String port;
	private String time_out;
	private String retry_nums;
	private String smm1_ip;
	private String smm2_ip;
	private String community;
	private String security_mode;
	private String security_name;
	private String indentity_metic;
	private String indentity_password;
	private String encrypt_metic;
	private String encrypt_password;
	private String entity_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getTime_out() {
		return time_out;
	}

	public void setTime_out(String time_out) {
		this.time_out = time_out;
	}

	public String getRetry_nums() {
		return retry_nums;
	}

	public void setRetry_nums(String retry_nums) {
		this.retry_nums = retry_nums;
	}

	public String getSmm1_ip() {
		return smm1_ip;
	}

	public void setSmm1_ip(String smm1_ip) {
		this.smm1_ip = smm1_ip;
	}

	public String getSmm2_ip() {
		return smm2_ip;
	}

	public void setSmm2_ip(String smm2_ip) {
		this.smm2_ip = smm2_ip;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getSecurity_mode() {
		return security_mode;
	}

	public void setSecurity_mode(String security_mode) {
		this.security_mode = security_mode;
	}

	public String getSecurity_name() {
		return security_name;
	}

	public void setSecurity_name(String security_name) {
		this.security_name = security_name;
	}

	public String getIndentity_metic() {
		return indentity_metic;
	}

	public void setIndentity_metic(String indentity_metic) {
		this.indentity_metic = indentity_metic;
	}

	public String getIndentity_password() {
		return indentity_password;
	}

	public void setIndentity_password(String indentity_password) {
		this.indentity_password = indentity_password;
	}

	public String getEncrypt_metic() {
		return encrypt_metic;
	}

	public void setEncrypt_metic(String encrypt_metic) {
		this.encrypt_metic = encrypt_metic;
	}

	public String getEncrypt_password() {
		return encrypt_password;
	}

	public void setEncrypt_password(String encrypt_password) {
		this.encrypt_password = encrypt_password;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

}
