package com.sitech.basd.bol.domain.cluster;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: BolClusterVO
 * </p>
 * <p>
 * Description: BM3.5云集群对象
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
 * @createtime 2014-2-24 下午3:45:21
 * 
 */
public class BolClusterVO extends BaseObj implements Serializable, Cloneable {
	// 集群标示
	private int id;
	// 集群名称
	private String name;
	// 集群描述
	private String descrip;
	// 集群状态 0 异常 1 正常
	private int status = -1;
	// 状态日期 20140303112300
	private String lastUpdate;

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
}
