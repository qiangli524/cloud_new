package com.sitech.ssd.ah.fusioncharts.vo.pie;

/**
 * <p>
 * Title: Data
 * </p>
 * <p>
 * Description: 饼图数据源
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipp
 * @version 1.0
 * @createtime 2013-11-26 下午2:10:17
 * 
 */
public class PieData {

	private String label;// 标签，分类，扇区名称

	private String value;// 值，扇区大小

	private String color;// 扇区颜色

	public String getLabel() {
		return label;
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

}
