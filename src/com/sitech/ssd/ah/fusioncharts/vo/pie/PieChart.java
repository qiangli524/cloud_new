package com.sitech.ssd.ah.fusioncharts.vo.pie;

import com.sitech.ssd.ah.fusioncharts.vo.basechart.Chart;

/**
 * <p>
 * Title: PieChart
 * </p>
 * <p>
 * Description: 饼图基本属性类
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
 * @createtime 2013-11-26 下午2:24:54
 * 
 */
public class PieChart extends Chart {

	private String showpercetvalues;// 是否显示百分比

	private String pieRadius;// 半径

	public String getShowpercetvalues() {
		return showpercetvalues;
	}

	public void setShowpercetvalues(String showpercetvalues) {
		this.showpercetvalues = showpercetvalues;
	}

	public String getPieRadius() {
		return pieRadius;
	}

	public void setPieRadius(String pieRadius) {
		this.pieRadius = pieRadius;
	}

}
