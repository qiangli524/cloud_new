package com.sitech.basd.fusioncharts.vo;

import java.util.List;

public class Groups {
	private String id;
	private String showbelow;
	private String showshadow;
	private List<Items> items;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShowbelow() {
		return showbelow;
	}

	public void setShowbelow(String showbelow) {
		this.showbelow = showbelow;
	}

	public String getShowshadow() {
		return showshadow;
	}

	public void setShowshadow(String showshadow) {
		this.showshadow = showshadow;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

}
