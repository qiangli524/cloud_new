package com.sitech.basd.fusioncharts.vo;

import java.util.Map;

/**
 * 
 * <p>Title: Category</p>
 * <p>Description: 类别
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author hehui
 * @version 1.0
 * @createtime 2013-10-23 下午5:08:53
 *
 */
public class Category {
	private String label;

	public Category(String label) {
		super();
		this.label = label;
	}

	public Category() {
		super();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
