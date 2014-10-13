package com.sitech.basd.fusioncharts.vo;

import java.util.List;

/**
 * 
 * <p>
 * Title: FusionCharts
 * </p>
 * <p>
 * Description: 报表整体类
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
 * @createtime 2013-9-13 下午7:59:54
 * 
 */
public class FusionCharts {
	// 报表基本属性类
	private Chart chart;
	// 报表数据
	private List<Data> data;
	// 趋势线
	private Trendlines trendlines;
	//横坐标
	private List<Categories> categories;
	//数据组
	private List<Dataset> dataset;
	
	private Styles styles;

	private ColorRange colorrange;

	private Dials dials;

	private TrendPoints trendpoints;

	private Annotations annotations;

	public List<Dataset> getDataset() {
		return dataset;
	}

	public void setDataset(List<Dataset> dataset) {
		this.dataset = dataset;
	}

	public List<Categories> getCategories() {
		return categories;
	}

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}

	public Trendlines getTrendlines() {
		return trendlines;
	}

	public void setTrendlines(Trendlines trendlines) {
		this.trendlines = trendlines;
	}

	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public Styles getStyles() {
		return styles;
	}

	public void setStyles(Styles styles) {
		this.styles = styles;
	}

	public ColorRange getColorrange() {
		return colorrange;
	}

	public void setColorrange(ColorRange colorrange) {
		this.colorrange = colorrange;
	}

	public Dials getDials() {
		return dials;
	}

	public void setDials(Dials dials) {
		this.dials = dials;
	}

	public TrendPoints getTrendpoints() {
		return trendpoints;
	}

	public void setTrendpoints(TrendPoints trendpoints) {
		this.trendpoints = trendpoints;
	}

	public Annotations getAnnotations() {
		return annotations;
	}

	public void setAnnotations(Annotations annotations) {
		this.annotations = annotations;
	}

}
