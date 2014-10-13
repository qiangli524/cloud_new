package com.sitech.basd.fusioncharts.vo;

/**
 * 
 * <p>
 * Title: Data
 * </p>
 * <p>
 * Description: 数据属性类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-9-13 下午7:57:33
 * 
 */
public class Data implements Comparable<Data> {
	// 数据标签
	private String label;
	//
	private String label2;
	
	private String label3;
	
	private String label4;
	
	private String toolText;
	private String toolText1;
	
	// 数据值
	private String value;
	private String color;
	private String link;
	// Boolean 0/1
	private Integer showLabel;
	// Boolean 0/1
	private Integer showValue;
	// Number 0-100
	private Integer alpha;
	private String displayValue;

	public String getLabel() {
		return label;
	}

	public String getToolText1() {
		return toolText1;
	}

	public void setToolText1(String toolText1) {
		this.toolText1 = toolText1;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public String getToolText() {
		return toolText;
	}

	public void setToolText(String toolText) {
		this.toolText = toolText;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getShowLabel() {
		return showLabel;
	}

	public void setShowLabel(Integer showLabel) {
		this.showLabel = showLabel;
	}

	public Integer getShowValue() {
		return showValue;
	}

	public void setShowValue(Integer showValue) {
		this.showValue = showValue;
	}

	public Integer getAlpha() {
		return alpha;
	}

	public void setAlpha(Integer alpha) {
		this.alpha = alpha;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getLabel2() {
		return label2;
	}

	public void setLabel2(String label2) {
		this.label2 = label2;
	}

	public String getLabel3() {
		return label3;
	}

	public void setLabel3(String label3) {
		this.label3 = label3;
	}

	public String getLabel4() {
		return label4;
	}

	public void setLabel4(String label4) {
		this.label4 = label4;
	}

	@Override
	public int compareTo(Data o) {
		
		return (this.value.compareTo(o.value));
	}
}
