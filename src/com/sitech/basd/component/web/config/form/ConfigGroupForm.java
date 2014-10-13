package com.sitech.basd.component.web.config.form;

import java.util.List;

public class ConfigGroupForm {
	private String name;
	private String description;
	private List resultList;
	private List selectedList;

	public List getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List selectedList) {
		this.selectedList = selectedList;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
