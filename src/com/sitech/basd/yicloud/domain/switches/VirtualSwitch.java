package com.sitech.basd.yicloud.domain.switches;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class VirtualSwitch extends BaseObj {
	public int id;
	public String name;
	public int numPorts;
	public int numPortsAvailable;
	public String type;//0:标准交换机，1：分布式交换机
	public String hostName;
	public List resultList;
	public String hostCode;
	private String vssuuid;
	private int numNic;
	private String connect_id;// /链接的唯一标示

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getConnect_id() {
		return connect_id;
	}

	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}

	public int getNumNic() {
		return numNic;
	}

	public void setNumNic(int numNic) {
		this.numNic = numNic;
	}

	public String getVssuuid() {
		return vssuuid;
	}

	public void setVssuuid(String vssuuid) {
		this.vssuuid = vssuuid;
	}

	public String getHostCode() {
		return hostCode;
	}

	public void setHostCode(String hostCode) {
		this.hostCode = hostCode;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumPorts() {
		return numPorts;
	}

	public void setNumPorts(int numPorts) {
		this.numPorts = numPorts;
	}

	public int getNumPortsAvailable() {
		return numPortsAvailable;
	}

	public void setNumPortsAvailable(int numPortsAvailable) {
		this.numPortsAvailable = numPortsAvailable;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
