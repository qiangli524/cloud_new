package com.sitech.basd.fusioncharts.vo;

/**
 * 
 * <p>
 * Title: Trendlines
 * </p>
 * <p>
 * Description: 趋势线实体类，一般用于报表中显示是否数据超越该数值
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
 * @createtime 2013-9-14 下午5:34:31
 * 
 */
public class Trendline {
	private Integer startValue;
	private Integer endValue;
	private String displayValue;
	private String color;
	// Boolean 0/1
	private Integer sTrendZone;
	// In Pixels
	private Integer thickness;
	// 0-100
	private Integer alpha;
	// Boolean 0/1
	private Integer dashed;
	// In Pixels
	private Integer dashLen;
	// In Pixels
	private Integer dashGap;
	// Boolean 0/1
	private Integer valueOnRight;
	private String toolText;

	public Integer getStartValue() {
		return startValue;
	}

	public void setStartValue(Integer startValue) {
		this.startValue = startValue;
	}

	public Integer getEndValue() {
		return endValue;
	}

	public void setEndValue(Integer endValue) {
		this.endValue = endValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getsTrendZone() {
		return sTrendZone;
	}

	public void setsTrendZone(Integer sTrendZone) {
		this.sTrendZone = sTrendZone;
	}

	public Integer getThickness() {
		return thickness;
	}

	public void setThickness(Integer thickness) {
		this.thickness = thickness;
	}

	public Integer getAlpha() {
		return alpha;
	}

	public void setAlpha(Integer alpha) {
		this.alpha = alpha;
	}

	public Integer getDashed() {
		return dashed;
	}

	public void setDashed(Integer dashed) {
		this.dashed = dashed;
	}

	public Integer getDashLen() {
		return dashLen;
	}

	public void setDashLen(Integer dashLen) {
		this.dashLen = dashLen;
	}

	public Integer getDashGap() {
		return dashGap;
	}

	public void setDashGap(Integer dashGap) {
		this.dashGap = dashGap;
	}

	public Integer getValueOnRight() {
		return valueOnRight;
	}

	public void setValueOnRight(Integer valueOnRight) {
		this.valueOnRight = valueOnRight;
	}

	public String getToolText() {
		return toolText;
	}

	public void setToolText(String toolText) {
		this.toolText = toolText;
	}

}
