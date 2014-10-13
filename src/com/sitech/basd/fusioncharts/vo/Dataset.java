package com.sitech.basd.fusioncharts.vo;

import java.util.List;

public class Dataset {
	//名称
	private String seriesname;
	//构建的图形
	private String renderas;
	//点的边框
	private String showplotborder;
	//线的厚度
	private String linethickness;
	//半径
	private String anchorRadius;
	//颜色
	private String color;
	//数据
	private List<Data> data;
	
	private String showvalues;//是否显示值
	
	public String getAnchorRadius() {
		return anchorRadius;
	}
	public void setAnchorRadius(String anchorRadius) {
		this.anchorRadius = anchorRadius;
	}
	public String getLinethickness() {
		return linethickness;
	}
	public void setLinethickness(String linethickness) {
		this.linethickness = linethickness;
	}
	public String getSeriesname() {
		return seriesname;
	}
	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}
	public String getRenderas() {
		return renderas;
	}
	public void setRenderas(String renderas) {
		this.renderas = renderas;
	}
	public String getShowplotborder() {
		return showplotborder;
	}
	public void setShowplotborder(String showplotborder) {
		this.showplotborder = showplotborder;
	}
	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> data) {
		this.data = data;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	public String getShowvalues() {
		return showvalues;
	}
	public void setShowvalues(String showvalues) {
		this.showvalues = showvalues;
	} 
	
}
