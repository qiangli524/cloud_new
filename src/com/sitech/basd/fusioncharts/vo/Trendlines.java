package com.sitech.basd.fusioncharts.vo;

import java.util.List;

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
public class Trendlines {
	// 趋势线
	private List<Trendline> line;

	public List<Trendline> getLine() {
		return line;
	}

	public void setLine(List<Trendline> line) {
		this.line = line;
	}

}
