package service.tab;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.fusioncharts.vo.Application;
import com.sitech.basd.fusioncharts.vo.Categories;
import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Dataset;
import com.sitech.basd.fusioncharts.vo.Definition;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.fusioncharts.vo.Styles;

import dao.monitor.HadoopMonitorDao;
import domain.kpi.HadoopKpiObj;
import domain.monitor.HadoopMonitorObj;
import domain.tab.ChartType;
import domain.tab.MapDataSet;

@Service("hadoopHostServersShijc")
public class HadoopHostServersImpl implements HadoopHostServers {
	
	@Autowired
	private HadoopMonitorDao hadoopMonitorDao;
	
	/* 
	 * <p>Title: queryChartDayData</p>
	 * <p>Description: </p>
	 * @return
	 * @see domain.service.HadoopHostServers#queryChartDayData()
	 */
	public FusionCharts queryChartDayData(MapDataSet mds,String type,String unit,boolean isShowPot){
		FusionCharts charts = new FusionCharts();
		Chart chart = new Chart();
		chart.setCaption(mds.getCaption());
		chart.setNumvisibleplot("288");
		chart.setLabelstep("30");
		chart.setLabelDisplay("WRAP");
		chart.setXaxisname("");
		chart.setYaxisname(unit);
		chart.setPlotGradientColor("");
		chart.setShowvalues("0");
		chart.setCanvasPadding("0");
		charts.setChart(chart);
		/**
		 * 遍历所有的kpiId，并对每一个查询数据库
		 */
		Map<String, HadoopKpiObj> kpiIds=mds.getKpiIds();
		Set<String> keys=kpiIds.keySet();
		List<MapDataSet> lists=new ArrayList<MapDataSet>();
		Map<String,MapDataSet> tempMaps=new HashMap<String,MapDataSet>();
		for(String key:keys){
			if (!"mem_usage".equals(key)) {
				MapDataSet temp = new MapDataSet();
				temp.setKpiId(key);
				temp.setHostName(mds.getHostName());
				temp.setClusterName(mds.getClusterName());
				temp.setPotCount(mds.getPotCount());
				int interval = mds.getInterval();
				List<Data> dataf = new ArrayList<Data>();
				if (interval == -1) {
					/**
					 * 查询点数
					 */
					dataf = hadoopMonitorDao.queryHostMonitorDataByPot(temp);
				} else {
					/**
					 * 查询一个小时的监控数据
					 */
					dataf = hadoopMonitorDao
							.queryHostMonitorDataByInterval(temp);
				}
				temp.setDatas(dataf);
				temp.setDatasSize(dataf.size());
				tempMaps.put(key, temp);
				lists.add(temp);
			}
		}
		if ("mem".equals(type)) {
			// 计算内存使用率@yanggl
			MapDataSet memTotalSet = tempMaps.get("mem_total");
			MapDataSet memFreeSet = tempMaps.get("mem_free");
			List<Data> memData = new ArrayList<Data>();
			MapDataSet memTemp = new MapDataSet();
			for (int i = 0; i < memTotalSet.getDatasSize(); i++) {
				Data data = new Data();
				Double memTotal = Double.parseDouble(memTotalSet.getDatas()
						.get(i).getValue());
				Double memFree = Double.parseDouble(memFreeSet.getDatas()
						.get(i).getValue());
				Double memUsed = memTotal - memFree;
				DecimalFormat df = new DecimalFormat("0.00");
				String memUsedAge = df.format(memUsed * 100 / memTotal);
				data.setValue(memUsedAge);
				memData.add(data);
			}
			memTemp.setDatas(memData);
			tempMaps.put("mem_usage", memTemp);
		}
		/**
		 * 获取最小的Data，使用label最为时间轴
		 */
		List<Data> datafMin =new ArrayList<Data>();
		datafMin=lists.get(0).getDatas();
		for(int i=1;i<lists.size();i++){
			if(lists.get(i).getDatas().size()<datafMin.size()){
				datafMin=lists.get(i).getDatas();
			}
		}
		// 横坐标
		List<Categories> categoriesLst = new ArrayList<Categories>();
		Categories categories = new Categories();
		List<Category> lst = new ArrayList<Category>();
		for(Data d:datafMin){
			Category category=new Category();
			category.setLabel(d.getLabel());
			lst.add(category);
		}
		
		/**
		 * 如果数据太多会导致页面卡顿，只截取后200个点
		 */
//		if(datafMin.size()>200){
//			
//		}
		categories.setCategory(lst);
		categoriesLst.add(categories);
		charts.setCategories(categoriesLst);
		List<Dataset> dataset = new ArrayList<Dataset>();
		for(String key:keys){
			if (!"mem_total".equals(key) && !"mem_free".equals(key)) {
				Dataset datasetf = new Dataset();
				datasetf.setSeriesname(kpiIds.get(key).getDescription());// 1分钟的负载
				HadoopKpiObj kpiId=mds.getKpiIds().get(key);
				List<Data> tempData=tempMaps.get(key).getDatas();
				if ("dfs".equals(type)) {
					/**
					 * HDFS由于主备关系，导致获取数据的时候获取了重复的数据，所有数值都应该除以2
					 */
					if("dfs.FSNamesystem.CapacityTotalGB".equalsIgnoreCase(key)||"dfs.FSNamesystem.CapacityRemainingGB".equalsIgnoreCase(key)){
						for(Data d:tempData){
							Double value=Double.parseDouble(d.getValue());
							value = value / 2;// 去掉主备的重复
							DecimalFormat df = new DecimalFormat("0.00");
							d.setValue(df.format(value));
						}
					}
				}
				if(ChartType.Area==kpiId.getType()){
					datasetf.setRenderas("Area");
					datasetf.setShowplotborder("0");
					/**
					 * area没有lable，去掉
					 */
					datasetf.setData(tempData);
					dataset.add(datasetf);
				}else if(ChartType.Line==kpiId.getType()){
					datasetf.setRenderas("Line");
					if(isShowPot){
						datasetf.setAnchorRadius("2");
					}else{
						datasetf.setAnchorRadius("1");
					}
					datasetf.setLinethickness("1");
					/**
					 * line使用上面查询的最小值的lable
					 */
					datasetf.setData(tempData);
					dataset.add(datasetf);
				}else{
					// 暂时没有其他类型，先放着，以后开发吧
				}
			}
		}
		charts.setDataset(dataset);
		Styles style = new Styles();
		List<Definition> defLst = new ArrayList<Definition>();
		Definition def = new Definition();
		def.setName("CanvasAnim");
		def.setType("animation");
		def.setParam("_xScale");
		def.setStart("0");
		def.setDuration("1");
		defLst.add(def);
		Definition def1 = new Definition();
		def1.setName("CanvasAnim1");
		def1.setType("animation");
		def1.setParam("_yScale");
		def1.setStart("0");
		def1.setDuration("1");
		defLst.add(def1);
		style.setDefinition(defLst);
		List<Application> appLst = new ArrayList<Application>();
		Application app = new Application();
		app.setToobject("BACKGROUND");
		app.setStyles("CanvasAnim,CanvasAnim1");
		appLst.add(app);
		style.setApplication(appLst);
		charts.setStyles(style);
		return charts;
	}

	/**
	 * 通过服务和主机名称与集群名称，查询此主机上挂载的kpi指标，并展示出每一个kpi指标
	 */
	@Override
	public List<Data> queryChartData(MapDataSet mds) {
		if(mds.getInterval()==-1){
			/**
			 * 查询点数
			 */
			return hadoopMonitorDao.queryHostMonitorDataByPot(mds);
		}else{
			/**
			 * 查询一个小时的监控数据
			 */
			return hadoopMonitorDao.queryHostMonitorDataByInterval(mds);
		}
	}


	@Override
	public List<HadoopKpiObj> queryKpiIdDataByHost(HadoopKpiObj obj) {
		return hadoopMonitorDao.queryKpiIdDataByHost(obj);
	}

//	@Override
//	public List<Data> queryHostMonitorDataByPot(String kpiId,MapDataSet mds) {
//		Map<String,String> map=new HashMap<String,String>();
//		map.put("KPI_ID",kpiId);
//		map.put("CLUSTER_NAME",mds.getClusterName());
//		map.put("HOST_NAME",mds.getHostName());
//		map.put("potCount",mds.getPotCount()+"");
//		return hadoopMonitorDao.queryHostMonitorDataByPot(map);
//	}

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
	@Override
	public Map<String, String> queryHostLastData(MapDataSet mds) {
		HadoopMonitorObj obj = new HadoopMonitorObj();
		/**
		 * 遍历所有的kpiId，并对每一个查询数据库
		 */
		Map<String, HadoopKpiObj> kpiIds = mds.getKpiIds();
		Set<String> keys = kpiIds.keySet();
		Map<String, String> tempMaps = new HashMap<String, String>();
		for (String key : keys) {
			MapDataSet temp = new MapDataSet();
			temp.setKpiId(key);
			temp.setHostName(mds.getHostName());
			temp.setClusterName(mds.getClusterName());
			temp.setPotCount(mds.getPotCount());
			obj = hadoopMonitorDao.queryHostLastData(temp);
			if (obj == null) {
				tempMaps.put(key, "0");
			} else {
				tempMaps.put(obj.getKpi_id(), obj.getKpi_value());
			}
		}
		return tempMaps;
	}
}