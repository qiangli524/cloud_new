package service.monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.Application;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Definition;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.fusioncharts.vo.Styles;

import dao.monitor.HadoopMonitorDao;
import domain.monitor.HadoopMonitorObj;

/**
 * <p>
 * Title: HadoopMonitorService
 * </p>
 * <p>
 * Description: hadoop监控逻辑层实现
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-1-6 上午10:42:22
 * 
 */
@Service("hadoopMonitorService")
public class HadoopMonitorServiceImpl implements HadoopMonitorService {

	@Autowired
	private HadoopMonitorDao hadoopMonitorDao;

	/**
	 * @Title: queryForLatestKpi
	 * @Description: 查询监控指标最新监控值
	 * @param
	 * @return List<HadoopMonitorObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-6 上午10:45:17
	 */
	@Override
	public List<HadoopMonitorObj> queryForLatestKpi(Map<String, Object> paramMap) {
		return hadoopMonitorDao.queryForLatestKpi(paramMap);
	}

	/**
	 * @Title: buildFusionChart
	 * @Description: 构建fusionchart线图
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-6 下午4:11:44
	 */
	@Override
	public String buildFusionChart(HadoopMonitorObj hadoopMonitorObj) {
		List<HadoopMonitorObj> list = hadoopMonitorDao
				.queryForListByObjInMonthTable(hadoopMonitorObj);
		if (list != null && list.size() > 0) {
			hadoopMonitorObj = list.get(0);
		}
		String subCaption = hadoopMonitorObj.getDescription();
		String yname = hadoopMonitorObj.getUnit();
		
		//若全部显示，则会因为数据过多导致页面卡死，这里控制显示100个点
		List<HadoopMonitorObj> newList = new ArrayList<HadoopMonitorObj>();
		if (list.size() <= 100) {
			newList.addAll(list);
		} else {
			int x = list.size()/100;
			for (int i = 0; i < 99; i++) {
				newList.add(list.get(i*x));
			}
			newList.add(list.get(list.size()-1));
		}
		list.clear();//释放内存
		
		String chartXml = JacksonUtil.toJson(this.ConstructFusionChart(subCaption, newList, yname));
		return chartXml;
	}

	/**
	 * @Title: ConstructFusionChart
	 * @Description: 创建fusionChart对象
	 * @param
	 * @return Object
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-6 下午4:24:22
	 */
	private FusionCharts ConstructFusionChart(String subCaption, List<HadoopMonitorObj> list,
			String yname) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setAnchorborderthickness("2");
		chart.setAnchorbordercolor("FFFFFF");
		chart.setAnchorbgcolor("BBDA00");
		chart.setAnchorRadius("4");
		chart.setAlternatehgridalpha("5");
		chart.setBasefontcolor("FFFFFF");
		chart.setBgColor("406181, 6DA5DB");
		chart.setBgalpha("100");
		chart.setCanvasbgalpha("0");
		chart.setCanvasbordercolor("FFFFFF");
		chart.setCaption("监控指标历史值");
		chart.setDivlinecolor("FFFFFF");
		chart.setDivlinealpha("100");
		chart.setDivintervalhints("100");
		chart.setLinecolor("BBDA00");
		chart.setLabelDisplay("auto");
		chart.setNumvdivlines("10");
		chart.setNumvisibleplot("100");
		chart.setShowvalues("0");
		chart.setShowalternatevgridcolor("1");
		chart.setSubcaption(subCaption);
		chart.setTooltipbordercolor("406181");
		chart.setTooltipbgcolor("406181");
		chart.setVdivlineisdashed("1");
		chart.setYaxisname(yname);

		List<Data> data = new ArrayList<Data>();
		for (HadoopMonitorObj hadoopMonitorObj : list) {
			Data d = new Data();
			d.setLabel(hadoopMonitorObj.getColl_time());
			d.setValue(hadoopMonitorObj.getKpi_value());
			data.add(d);
		}

		Styles styles = new Styles();

		List<Application> application = new ArrayList<Application>();
		Application lication = new Application();
		lication.setStyles("LineShadow");
		lication.setToobject("DATAPLOT");
		application.add(lication);

		List<Definition> definition = new ArrayList<Definition>();
		Definition nition = new Definition();
		nition.setName("LineShadow");
		nition.setColor("333333");
		nition.setType("shadow");
		nition.setDistance("6");
		definition.add(nition);

		styles.setApplication(application);
		styles.setDefinition(definition);

		fusionCharts.setChart(chart);
		fusionCharts.setData(data);
		fusionCharts.setStyles(styles);
		return fusionCharts;

	}

	/**
	 * @Title: queryHostKpiListByServiceNode
	 * @Description: 查询主机的kpi集合
	 * @param
	 * @return List<HadoopMonitorObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-7 上午10:12:44
	 */
	@Override
	public List<HadoopMonitorObj> queryHostKpiListByServiceNode(HadoopMonitorObj hadoopMonitorObj) {
		return hadoopMonitorDao.queryHostKpiListByServiceNode(hadoopMonitorObj);
	}

	/**
	 * @Title: buildTopNFusionChart
	 * @Description: 创建topN柱状图
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-7 上午11:03:58
	 */
	@Override
	public String buildTopNFusionChart(List<HadoopMonitorObj> resultList, String caption) {
		HadoopMonitorObj hadoopMonitorObj = new HadoopMonitorObj();
		if (resultList != null && resultList.size() > 0) {
			hadoopMonitorObj = resultList.get(0);
		}
		caption = hadoopMonitorObj.getDescription() + caption;

		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setShowborder("1");
		chart.setCaption(caption);
		chart.setShowvalues("0");
		chart.setYaxisname(hadoopMonitorObj.getUnit());

		List<Data> data = new ArrayList<Data>();
		for (HadoopMonitorObj monitorObj : resultList) {
			Data d = new Data();
			d.setLabel(monitorObj.getLabelname());
			d.setValue(monitorObj.getKpi_value());
			data.add(d);
		}

		fusionCharts.setChart(chart);
		fusionCharts.setData(data);
		return JacksonUtil.toJson(fusionCharts);
	}

	/**
	 * @Title: queryLastestValueForKpi
	 * @Description: 查询每个监控指标的最新监控值
	 * @param
	 * @return List<HadoopMonitorObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-7 下午6:11:05
	 */
	@Override
	public List<HadoopMonitorObj> queryLastestValueForKpi(HadoopMonitorObj hadoopMonitorObj) {
		return hadoopMonitorDao.queryLastestValueForKpi(hadoopMonitorObj);
	}

	/**
	 * @Title: queryLatestValueByKpi
	 * @Description: 查询指标的最新监控值
	 * @param
	 * @return List<HadoopMonitorObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-9 下午9:44:29
	 */
	@Override
	public List<HadoopMonitorObj> queryLatestValueByKpi(HadoopMonitorObj hadoopMonitorObj) {
		return hadoopMonitorDao.queryLatestValueByKpi(hadoopMonitorObj);
	}
	/**
	 * @Title: queryKpiValueByHostList
	 * @Description: 通过主机查询kpi值
	 * @param
	 * @return List<HadoopMonitorObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-11 上午10:25:38
	 */
	@Override
	public List<HadoopMonitorObj> queryKpiValueByHostList(HadoopMonitorObj hadoopMonitorObj) {
		return hadoopMonitorDao.queryKpiValueByHostList(hadoopMonitorObj);
	}
	/**
	 * 
	 * @Title: queryMonitorDataForHost
	 * @Description:查询主机对应的相关指标信息
	 * @param
	 * @return List<HadoopMonitorObj>
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-11 上午11:41:28
	 */
	public List<HadoopMonitorObj> queryMonitorDataForHost(HadoopMonitorObj hadoopMonitorObj) {
		return hadoopMonitorDao.queryMonitorDataForHost(hadoopMonitorObj);
	}

	/**
	 * @Title: queryForListOrderByValueDesc
	 * @Description: 根据值降序
	 * @param
	 * @return List<HadoopMonitorObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-26 下午4:36:30
	 */
	@Override
	public List<HadoopMonitorObj> queryForListOrderByValueDesc(
			HadoopMonitorObj hadoopMonitorObj) {
		return hadoopMonitorDao.queryForListOrderByValueDesc(hadoopMonitorObj);
	}

}
