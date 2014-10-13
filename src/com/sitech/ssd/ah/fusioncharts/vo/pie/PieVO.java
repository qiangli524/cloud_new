package com.sitech.ssd.ah.fusioncharts.vo.pie;

import java.util.List;

import com.sitech.ssd.ah.fusioncharts.vo.basechart.Chart;

/**
 * <p>
 * Title: Pie2DVo
 * </p>
 * <p>
 * Description: FusionChart饼图 2D 属性封装类 有需要的属性自己添加
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
 * @createtime 2013-11-26 上午11:58:23
 * 
 */
public class PieVO {

	private Chart chart;// 头文件等 对应json的chart

	private List<PieData> data;// 数据源，对应json的data数组

	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

	public List<PieData> getData() {
		return data;
	}

	public void setData(List<PieData> data) {
		this.data = data;
	}
}
