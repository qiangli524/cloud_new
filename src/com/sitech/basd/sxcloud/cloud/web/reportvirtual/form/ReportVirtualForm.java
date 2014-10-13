package com.sitech.basd.sxcloud.cloud.web.reportvirtual.form;

import java.util.List;

@SuppressWarnings("serial")
public class ReportVirtualForm {
	private String cpuXML;// 虚拟机编号
	private String memoryXML;// 虚拟机名称
	private String storageXML;// 内存
	private String netXML;// CPU
	private List resultList;//

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
