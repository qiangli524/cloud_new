package service.tab;

import java.util.List;
import java.util.Map;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.FusionCharts;

import domain.kpi.HadoopKpiObj;
import domain.tab.MapDataSet;

public interface HadoopHostServers {

	public FusionCharts queryChartDayData(MapDataSet mds,String type,String unit,boolean isShowPot);
	
	public List<Data> queryChartData(MapDataSet mds);
	
	public List<HadoopKpiObj> queryKpiIdDataByHost(HadoopKpiObj obj);
	
//	public List<Data> queryHostMonitorDataByPot(String kpiId,MapDataSet mds);
	
	/**
	 * 
	 * @Title: queryHostLastData
	 * @Description: 查询最一条数据
	 * @param
	 * @return Map
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-5-7 下午2:16:59
	 */
	public Map queryHostLastData(MapDataSet map);
}