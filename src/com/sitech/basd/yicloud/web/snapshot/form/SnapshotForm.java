package com.sitech.basd.yicloud.web.snapshot.form;

import java.util.List;

public class SnapshotForm {
	private List resultList;
	private String vmName;//虚拟机名称
	private String name;//快照名称
	private String description;//快照描述

	public String getVmName() {
		return vmName;
	}

	public void setVmName(String vmName) {
		this.vmName = vmName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

}
