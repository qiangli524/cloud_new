package com.sitech.console.domain;

import java.util.Date;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: TbVncPortVO
 * </p>
 * <p>
 * Description: 映射tb_vnc_port表
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-7-30 下午4:19:55
 * 
 */
public class TbVncPortVO extends BaseObj {
	// 主键
	private String id;
	// 宿主机code
	private String host_code;
	// 宿主机ip
	private String host_ip;
	// 资源池id
	private String connect_id;
	// 端口
	private String port;
	// 若为占用，则关联虚拟机code
	private String vmcode;
	// 是否已使用，0：未使用，1：已使用
	private String ifused;
	// 开始端口-用于界面添加
	private String startport;
	// 结束端口-用于界面添加
	private String endport;
	// 数据更新时间
	private Date updatetime;

	public String getStartport() {
		return startport;
	}

	public void setStartport(String startport) {
		this.startport = startport;
	}

	public String getEndport() {
		return endport;
	}

	public void setEndport(String endport) {
		this.endport = endport;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHost_code() {
		return host_code;
	}

	public void setHost_code(String host_code) {
		this.host_code = host_code;
	}

	public String getHost_ip() {
		return host_ip;
	}

	public void setHost_ip(String host_ip) {
		this.host_ip = host_ip;
	}

	public String getConnect_id() {
		return connect_id;
	}

	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getVmcode() {
		return vmcode;
	}

	public void setVmcode(String vmcode) {
		this.vmcode = vmcode;
	}

	public String getIfused() {
		return ifused;
	}

	public void setIfused(String ifused) {
		this.ifused = ifused;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
}
