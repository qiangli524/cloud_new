package com.sitech.ssd.test.fusioncharts;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.FusionCharts;

/**
 * 
 * <p>
 * Title: FusionChartsTestCase
 * </p>
 * <p>
 * Description: 报表测试
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
 * @createtime 2013-9-13 下午10:31:02
 * 
 */
public class FusionChartsTestCase {
	/**
	 * 
	 * @Title: testSimpleCharts
	 * @Description: 测试简单数据
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-13 下午10:31:23
	 */
	@Test
	public void testSimpleCharts() {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setCaption("Weekly Sales Summary");
		chart.setXaxisname("Week");
		chart.setYaxisname("Sales");
		chart.setNumberprefix("$");
		List<Data> dataList = new ArrayList<Data>();
		Data data0 = new Data();
		data0.setLabel("Week 1");
		data0.setValue("14400");
		dataList.add(data0);
		fusionCharts.setChart(chart);
		fusionCharts.setData(dataList);
		String result = JacksonUtil.toJson(fusionCharts);
		FusionCharts rs = JacksonUtil.fromJSON(result,
				new JacksonUtil.TypeReference<FusionCharts>() {
				});
	}
}
