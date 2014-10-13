package service.clusterChartService;

import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sitech.basd.fusioncharts.service.HostSystemTopNService;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.FusionCharts;


import domain.clusterKpi.ClusterKpiObj;
import dao.clusterKpi.ClusterKpiDao;


@Service("clusterChartService")
public class ClusterChartServiceImpl  implements ClusterChartService  {
	
	@Autowired
	private ClusterKpiDao clusterKpiDao;

	
	public FusionCharts queryClusterKpi(ClusterKpiObj clusterKpiObj) throws SQLException{
		
		FusionCharts fusionCharts = new FusionCharts();
		// 设置报表基本参数
		Chart chart = new Chart();
		List<Data> dataList = new ArrayList<Data>();

		String kpiid = (String) clusterKpiObj.getKpiid();
		
		if(kpiid.equals("PM-00-23-001-07")) {
	     dataList =  clusterKpiDao.queryForList(clusterKpiObj);
		chart.setCaption("集群Cpu使用率");
		chart.setXaxisname("集群名称");
		chart.setYaxisname("Cpu使用量");
		chart.setNumberSuffix("%");  }
		else if(kpiid.equals("PM-00-23-001-10")){
			 dataList =  clusterKpiDao.queryForList(clusterKpiObj);
			chart.setCaption("集群内存使用率");
			chart.setXaxisname("集群名称");
			chart.setYaxisname("内存使用量");
			chart.setNumberSuffix("%");
			
		}else{
			 dataList =  clusterKpiDao.queryForList(clusterKpiObj);
			chart.setCaption("集群存储使用率");
			chart.setXaxisname("集群名称");
			chart.setYaxisname("存储使用量");
			chart.setNumberSuffix("%");
		} 
		
		
		chart.setPlotspacepercent("20");
		fusionCharts.setChart(chart);
		fusionCharts.setData(dataList);
		return fusionCharts;
		
		
		
	}

}
