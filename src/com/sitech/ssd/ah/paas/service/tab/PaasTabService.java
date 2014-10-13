package com.sitech.ssd.ah.paas.service.tab;

import java.util.List;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.ssd.ah.paas.domain.entity.PaasEntityObj;
import com.sitech.ssd.ah.paas.domain.tab.MapChartSet;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;

/**
 * 
 * <p>
 * Title: PaasTabService
 * </p>
 * <p>
 * Description: Daas Tab页相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-6-16 上午11:06:50
 * 
 */
public interface PaasTabService {
	/**
	 * 
	 * @Title: showHealthLimitChart
	 * @Description: 展示健康度Chart
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-16 上午11:08:57
	 */
	public FusionCharts showHealthLimitChart(PaasTreeObj obj, String node_type);

	/**
	 * 
	 * @Title: queryNodeList
	 * @Description: 查询节点集合
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-18 下午8:20:29
	 */
	public List<PaasEntityObj> queryNodeList(PaasEntityObj obj);

	/**
	 * 
	 * @Title: queryInstanceNodeList
	 * @Description: 查询实例节点集合
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-18 下午8:20:29
	 */
	public List<PaasEntityObj> queryInstanceNodeList(PaasEntityObj obj);

	/**
	 * 
	 * @Title: queryInstanceListForBusiNode
	 * @Description: 点击业务节点时查询实例集合
	 * @param
	 * @return List<PaasEntityObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-18 下午8:20:29
	 */
	public List<PaasEntityObj> queryInstanceListForBusiNode(PaasEntityObj obj);

	/**
	 * 
	 * @Title: queryChartData
	 * @Description: 查看数据
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-24 上午9:56:45
	 */
	public FusionCharts queryChartData(MapChartSet mapChartSet, String unit);

	/**
	 * 
	 * @Title: queryBusHealthyLimitForInstanceId
	 * @Description: 查询业务健康度通过实例ID
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-20 上午11:11:12
	 */
	public double queryBusHealthyLimitForInstanceId(PaasTreeObj obj, String node_type);

	/**
	 * 
	 * @Title: queryBusiTopN
	 * @Description: 业务TopN
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-24 上午10:35:13
	 */
	public FusionCharts queryBusiTopN(PaasEntityObj obj, String node_type);

	/**
	 * 
	 * @Title: queryLimitAverageChart
	 * @Description: 查询健康度
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-24 上午10:35:26
	 */
	public FusionCharts queryLimitAverageChart(MapChartSet obj);

	/**
	 * 
	 * @Title: queryBusiListByEntityId
	 * @Description: 查询业务列表通过Entity_id
	 * @param
	 * @return List<PaasEntityObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-24 上午10:35:46
	 */
	public List<PaasEntityObj> queryBusiListByEntityId(PaasEntityObj obj);

	/**
	 * 
	 * @Title: queryParentEntityByEntityId
	 * @Description: 通过服务entityId查询该服务属于哪个实例或通过实例entityId查询该实例属于哪个数据库
	 * @param
	 * @return PaasEntityObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-24 上午10:36:27
	 */
	public PaasEntityObj queryParentEntityByEntityId(PaasEntityObj obj);

	/**
	 * 
	 * @Title: limitSort
	 * @Description: 健康度排序
	 * @param List包括lable
	 *            ,value,link(调用页面js方法),size是展示topN的长度,desc是如何排序topN
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-17 上午11:28:02
	 */
	public List<Data> limitSort(List<Data> data, int size);

	/**
	 * 
	 * @Title: queryTimeLabelAndData
	 * @Description: 自定义时间查询{1小时(天表),5小时(天表),本月(月表),上月(月表),自定义(不支持跨年)}
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-25 下午3:52:15
	 */
	public List<Data> queryTimeLabelAndData(MapChartSet setObj);

	/**
	 * 
	 * @Title: queryTableNameByCycle_time
	 * @Description: 查询表名通过cycle_time
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-28 上午11:01:09
	 */
	public String queryTableNameByCycle_time(String coll_time, String cycle_time);
}
