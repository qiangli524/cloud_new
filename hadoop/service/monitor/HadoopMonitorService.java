package service.monitor;

import java.util.List;
import java.util.Map;

import domain.monitor.HadoopMonitorObj;

/**
 * <p>
 * Title: HadoopMonitorService
 * </p>
 * <p>
 * Description: hadoop监控逻辑层接口
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
public interface HadoopMonitorService {

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
	public List<HadoopMonitorObj> queryForLatestKpi(Map<String, Object> paramMap);

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
	public String buildFusionChart(HadoopMonitorObj hadoopMonitorObj);

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
	public List<HadoopMonitorObj> queryHostKpiListByServiceNode(HadoopMonitorObj hadoopMonitorObj);

	/**
	 * @Title: buildTopNFusionChart
	 * @Description: 创建topN柱状图
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @param caption
	 * @createtime 2014-1-7 上午11:03:58
	 */
	public String buildTopNFusionChart(List<HadoopMonitorObj> resultList, String caption);

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
	public List<HadoopMonitorObj> queryLastestValueForKpi(HadoopMonitorObj hadoopMonitorObj);

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
	public List<HadoopMonitorObj> queryLatestValueByKpi(HadoopMonitorObj hadoopMonitorObj);

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
	public List<HadoopMonitorObj> queryKpiValueByHostList(HadoopMonitorObj hadoopMonitorObj);
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
	public List<HadoopMonitorObj> queryMonitorDataForHost(HadoopMonitorObj hadoopMonitorObj);

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
	public List<HadoopMonitorObj> queryForListOrderByValueDesc(
			HadoopMonitorObj hadoopMonitorObj);
}
