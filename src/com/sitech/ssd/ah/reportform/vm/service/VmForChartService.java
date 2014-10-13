package com.sitech.ssd.ah.reportform.vm.service;

import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;

public interface VmForChartService {
	//添加的方法，用于将查询数据以JSON字符串返回
	public String buildBusiTopNChartJSON_cpu(VmReportForm vf);
	public String buildBusiTopNChartJSON_mem(VmReportForm vf);
}
