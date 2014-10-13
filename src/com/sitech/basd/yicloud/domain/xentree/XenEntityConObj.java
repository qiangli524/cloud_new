package com.sitech.basd.yicloud.domain.xentree;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class XenEntityConObj extends BaseObj implements Serializable, Cloneable {

	private int dataCenterId = -1;// 数据中心Id
	private int clusterId = -1;// 集群Id
	private String name;// 实体名称
	private int type = -1;// 实体类型
	private String entityId;// 实体Id
	private int temp = -1;// 用来存储type的临时值
	private int temp_type = -1;// 用来存储type的临时值
	private String status;// 虚拟机，主机等状态
	private String ifOpen;// 主机虚拟机等状况，是否打开电源
	private String cpu;// cpu
	private String mem;// 内存
	private String storage;// 置备的存储(未共享的存储 + 额外的存储)
	private String usedStor;// 已使用存储
	private String host;// 虚拟机所在主机
	private String clientMem; // 客户机内存
	private String userId;
	
	private static final long serialVersionUID = -1714469510588445079L;

	public int getTemp_type() {
		return temp_type;
	}

	public void setTemp_type(int temp_type) {
		this.temp_type = temp_type;
	}

	public int getDataCenterId() {
		return dataCenterId;
	}

	public void setDataCenterId(int dataCenterId) {
		this.dataCenterId = dataCenterId;
	}

	public int getClusterId() {
		return clusterId;
	}

	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getMem() {
		return mem;
	}

	public void setMem(String mem) {
		this.mem = mem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIfOpen() {
		return ifOpen;
	}

	public void setIfOpen(String ifOpen) {
		this.ifOpen = ifOpen;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getUsedStor() {
		return usedStor;
	}

	public void setUsedStor(String usedStor) {
		this.usedStor = usedStor;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getClientMem() {
		return clientMem;
	}

	public void setClientMem(String clientMem) {
		this.clientMem = clientMem;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
