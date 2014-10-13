package com.sitech.basd.yicloud.web.database.form;

import java.util.List;

/**
 * 
 * Title: AppDatabaseForm Description: 数据库类 Copyright: Copyright (c)2012
 * Company: SI-TECH
 * 
 * @author taoxue
 * @version 1.0
 */
public class AppDatabaseForm {
	private int id;// 唯一标识
	private String usrname;//用户名
	private String password;//密码
	private String tabale_space;//命名空间
	private String ipaddr;//IP地址
	private String port;//端口
	private String service_name;//服务名称，orcl,KF01等
	private List resultList;

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
