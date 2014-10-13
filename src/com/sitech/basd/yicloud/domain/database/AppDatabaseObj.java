package com.sitech.basd.yicloud.domain.database;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * Title: AppDatabaseObj Description: 数据库类 Copyright: Copyright (c)2012
 * Company: SI-TECH
 * 
 * @author taoxue
 * @version 1.0
 */
public class AppDatabaseObj extends BaseObj {
	private String id;// 唯一标识
	private String usrname;//用户名
	private String password;//密码
	private String tabale_space;//命名空间
	private String ipaddr;//IP地址
	private String port;//端口
	private String service_name;//服务名称，orcl,KF01等

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsrname() {
		return usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTabale_space() {
		return tabale_space;
	}

	public void setTabale_space(String tabale_space) {
		this.tabale_space = tabale_space;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

}
