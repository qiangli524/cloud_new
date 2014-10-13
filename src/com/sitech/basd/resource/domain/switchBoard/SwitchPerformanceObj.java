package com.sitech.basd.resource.domain.switchBoard;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class SwitchPerformanceObj extends BaseObj {
	private String id;
	private String interf_id; // 接口主键id
	private String useage; // 接口利用率
	private String recieve; // 接受利用率
	private String send; // 发送利用率
	private String error; // 出错率
	private String lost; // 丢包率

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInterf_id() {
		return interf_id;
	}

	public void setInterf_id(String interf_id) {
		this.interf_id = interf_id;
	}

	public String getUseage() {
		return useage;
	}

	public void setUseage(String useage) {
		this.useage = useage;
	}

	public String getRecieve() {
		return recieve;
	}

	public void setRecieve(String recieve) {
		this.recieve = recieve;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getLost() {
		return lost;
	}

	public void setLost(String lost) {
		this.lost = lost;
	}

}
