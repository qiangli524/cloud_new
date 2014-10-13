package com.sitech.basd.yicloud.domain.vmman;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class EntityConObj extends BaseObj implements Serializable, Cloneable {

	private int dataCenterId = -1;//数据中心Id
	private int clusterId = -1;//集群Id
	private String name;//实体名称
	private int type = -1;// 实体类型
	private int entityId;//实体Id
	private int temp = -1;//用来存储type的临时值
	private String cpu;//cpu
	private String mem;//内存
	private String provisionStore;//置备的存储(未共享的存储 + 额外的存储)
	private String store;//已使用存储
	private String clientMem; //客户机内存

	private static final long serialVersionUID = -1714469510588445079L;

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

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
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

	public String getProvisionStore() {
		return provisionStore;
	}

	public void setProvisionStore(String provisionStore) {
		this.provisionStore = provisionStore;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
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
}
