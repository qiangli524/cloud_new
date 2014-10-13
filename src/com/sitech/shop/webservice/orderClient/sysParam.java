package com.sitech.shop.webservice.orderClient;

import java.io.Serializable;

public class sysParam implements Serializable {

	private static final long serialVersionUID = 1L;
	//服务地址
	public String method_mark;
	//密匙
	public String app_key;
	//请求时间
	public String timestamp;
	//版本号
	public String v;
	//通道ID
	public String channel_id;

	public String getMethod_mark() {
		return method_mark;
	}

	public void setMethod_mark(String method_mark) {
		this.method_mark = method_mark;
	}

	public String getApp_key() {
		return app_key;
	}

	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

}
