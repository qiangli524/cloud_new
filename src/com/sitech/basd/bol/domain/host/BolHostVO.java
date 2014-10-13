package com.sitech.basd.bol.domain.host;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: BolHostVO
 * </p>
 * <p>
 * Description: BM3.5云主机对象
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
 * @createtime 2014-2-24 下午3:48:03
 * 
 */
public class BolHostVO extends BaseObj implements Serializable, Cloneable {
	// 主机标示
	private int id;
	// 主机名称
	private String name;
	// 集群描述
	private String descrip;
	// 主机IP地址
	private String ipaddress;
	//主机标识
	private String hostCode;
	// 集群状态
	private int status = -1;
	// 状态日期
	private String lastUpdate;
	//参数服务器
	private int paramServer;
	//激活标志
	private int isActive = -1;
	//主机类型
	private int hostType = -1;
	//主机能力
	private int capability;

	public String getHostCode() {
		return hostCode;
	}

	public void setHostCode(String hostCode) {
		this.hostCode = hostCode;
	}

	public int getCapability() {
		return capability;
	}

	public void setCapability(int capability) {
		this.capability = capability;
	}

	public int getHostType() {
		return hostType;
	}

	public void setHostType(int hostType) {
		this.hostType = hostType;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getParamServer() {
		return paramServer;
	}

	public void setParamServer(int paramServer) {
		this.paramServer = paramServer;
	}

}
