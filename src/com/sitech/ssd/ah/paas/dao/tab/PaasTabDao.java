package com.sitech.ssd.ah.paas.dao.tab;

import java.util.List;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.ssd.ah.paas.domain.entity.PaasEntityObj;
import com.sitech.ssd.ah.paas.domain.tab.MapChartSet;

/**
 * 
 * <p>
 * Title: PaasTabDao
 * </p>
 * <p>
 * Description: tab上相关操作
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
 * @createtime 2014-6-18 下午8:11:57
 * 
 */
public interface PaasTabDao {
	/**
	 * 
	 * @Title: queryNodeList
	 * @Description: 查询节点集合
	 * @param
	 * @return List<PaasEntityObj>
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
	 * @return List<PaasEntityObj>
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
	 * @Title: queryBusHealthyLimitForInstanceId
	 * @Description: 查询业务健康度通过实例ID
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-20 上午11:11:12
	 */
	public double queryBusHealthyLimitForInstanceId(PaasEntityObj obj);

	/**
	 * 
	 * @Title: queryBusiListByEntityId
	 * @Description: 查询业务信息，通过entity_id
	 * @param
	 * @return List<PaasEntityObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-24 上午10:31:24
	 */
	public List<PaasEntityObj> queryBusiListByEntityId(PaasEntityObj obj);

	/**
	 * 
	 * @Title: queryParentEntityByEntityId
	 * @Description: 通过服务entityId查询该服务属于哪个实例或通过实例entityId查询该实例属于哪个数据库
	 * @param
	 * @return List<PaasEntityObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-24 上午10:31:45
	 */
	public List<PaasEntityObj> queryParentEntityByEntityId(PaasEntityObj obj);

	/**
	 * 
	 * @Title: queryChartDataByDate
	 * @Description: 查询天表
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-25 下午4:04:52
	 */
	public List<Data> queryChartDataByDate(MapChartSet chartSet);

	/**
	 * 
	 * @Title: queryChartDataByMonth
	 * @Description: 查询月表
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-25 下午4:05:35
	 */
	public List<Data> queryChartDataByMonth(MapChartSet chartSet);

	/**
	 * 
	 * @Title: queryChartDataByDateBetween
	 * @Description: 查询不是当天的数据
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-25 下午4:59:29
	 */
	public List<Data> queryChartDataByDateBetween(MapChartSet chartSet);

}
