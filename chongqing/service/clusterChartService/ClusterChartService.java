package service.clusterChartService;


import domain.clusterKpi.ClusterKpiObj;

import com.sitech.basd.fusioncharts.vo.FusionCharts;


public interface ClusterChartService {

	public FusionCharts queryClusterKpi(ClusterKpiObj clusterKpiObj)
			throws Exception;
}


