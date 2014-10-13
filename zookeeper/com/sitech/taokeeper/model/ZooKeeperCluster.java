package com.sitech.taokeeper.model;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * Description: Model: ZooKeeperCluster
 * 
 * @author yinshi.nc
 * @Date 2011-10-26
 */
public class ZooKeeperCluster extends BaseObj {

	private Integer clusterId;
	private String clusterName;
	/** ip:prot */
	private List<String> serverList;
	private String serverListStr;
	private String description;
	private String ssh_username;
	private String ssh_passwd;
	private Integer ssh_port;

	public List<String> getServerList() {
		return serverList;
	}

	public void setServerList(List<String> serverList) {
		this.serverList = serverList;
	}

	public String getSsh_username() {
		return ssh_username;
	}

	public void setSsh_username(String ssh_username) {
		this.ssh_username = ssh_username;
	}

	public String getSsh_passwd() {
		return ssh_passwd;
	}

	public void setSsh_passwd(String ssh_passwd) {
		this.ssh_passwd = ssh_passwd;
	}

	public Integer getSsh_port() {
		return ssh_port;
	}

	public void setSsh_port(Integer ssh_port) {
		this.ssh_port = ssh_port;
	}

	public Integer getClusterId() {
		return clusterId;
	}

	public void setClusterId(Integer clusterId) {
		this.clusterId = clusterId;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "[clusterId: " + this.clusterId + ", clusterName: " + this.clusterName
				+ ", serverList: " + this.serverList;
	}

	public String getServerListStr() {
		return serverListStr;
	}

	public void setServerListStr(String serverListStr) {
		this.serverListStr = serverListStr;
	}

}
