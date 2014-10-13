package com.sitech.basd.sxcloud.cloud.web.reporthost.form;

import java.util.List;

public class ReportHostForm {
	private String cpuXML;
	private String memoryXML;
	private String storageXML;
	private String netXML;
	private List resultList;

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getCpuXML() {
		return cpuXML;
	}

	public void setCpuXML(String cpuXML) {
		this.cpuXML = cpuXML;
	}

	public String getMemoryXML() {
		return memoryXML;
	}

	public void setMemoryXML(String memoryXML) {
		this.memoryXML = memoryXML;
	}

	public String getNetXML() {
		return netXML;
	}

	public void setNetXML(String netXML) {
		this.netXML = netXML;
	}

	public String getStorageXML() {
		return storageXML;
	}

	public void setStorageXML(String storageXML) {
		this.storageXML = storageXML;
	}

}
