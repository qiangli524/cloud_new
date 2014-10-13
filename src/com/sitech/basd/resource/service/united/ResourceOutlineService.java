package com.sitech.basd.resource.service.united;

import java.util.List;
import java.util.Map;

import com.sitech.basd.cloud3.domain.departproject.DepartInfoObj;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.resource.domain.top.TopTargetObj;
import com.sitech.basd.resource.domain.united.ResourceOutlineObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.yicloud.domain.busisystem.BusiSystemObj;

/**
 * 
 * <p>
 * Title: ResourceOutlineService
 * </p>
 * <p>
 * Description: 首页展示相关操作
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
 * @createtime 2014-1-22 下午2:47:00
 * 
 */
public interface ResourceOutlineService {

	public List<DepartInfoObj> queryForDepartList(DepartInfoObj departInfoObj);

	// public FusionCharts queryChartDayData(List<DepartInfoObj>
	// departInfos,String type);

	public String buildXmlData(String resourceType, List<DepartInfoObj> departList,
			String startTime, String endTime);

	/**
	 * 
	 * @Title: queryResource
	 * @Description: 查询资源
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-22 下午7:28:59
	 */
	public FusionCharts queryResource();

	/**
	 * @Title: buildNetChart
	 * @Description: 构造网络fusionChart
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-22 下午3:38:56
	 */
	public String buildNetChart();

	/**
	 * 
	 * @Title: queryHostVmsData
	 * @Description: 构造主机及虚拟机数据
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-22 下午7:53:32
	 */
	public FusionCharts queryHostVmsData();

	/**
	 * @Title: buildTopNChartXml
	 * @Description: topN
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-23 下午7:24:56
	 */
	public String buildTopNChartXml(Map<String, Object> map) throws Exception ;
	/**
	 * 
	 * @Title: queryStore
	 * @Description: 查询存储总量和使用量
	 * @param
	 * @return ResourceOutlineObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-23 下午8:09:24
	 */
	public ResourceOutlineObj queryStore(ResourceOutlineObj obj);
	
	/**
	 * 查询所有的计算资源
	 * @Title: queryChartDayDataForPie
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-23 下午7:51:07
	 */
	public FusionCharts queryChartDayDataForPie(String type);

	/**
	 * @Title: buildBusiTopNChartJSON
	 * @Description: 创建业务系统资源占用topN
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 上午9:30:10
	 */
	public String buildBusiTopNChartJSON(Map<String, Object> paramMap);

	/**
	 * @Title: queryBusiSystemTopList
	 * @Description: 查询虚拟机占用资源topN列表
	 * @param
	 * @return List<BusiSystemObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 下午2:00:06
	 */
	public List<BusiSystemObj> queryBusiSystemTopList(Map<String, Object> paramMap);

	/**
	 * @Title: queryHostTopList
	 * @Description: 查询主机topN列表
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 下午5:57:53
	 */
	public List<TopTargetObj> queryHostTopList(String resourceType, TopTargetObj topTargetObj);

	/**
	 * @Title: queryValidStore
	 * @Description: 查询有效存储
	 * @param
	 * @return ResourceOutlineObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午12:54:13
	 */
	public ResourceOutlineObj queryValidStore(ResourceOutlineObj resourceOutlineObj);

	/**
	 * @Title: queryVmTopList
	 * @Description: 查询虚拟机topN列表
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午2:14:15
	 */
	public List<TopTargetObj> queryVmTopList(String resourceType, TopTargetObj topTargetObj);

	/**
	 * @Title: queryVmHostConfigInfo
	 * @Description: 查询虚拟机配置信息
	 * @param
	 * @return VMHostObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午6:44:49
	 */
	public VMHostObj queryVmHostConfigInfo(TopTargetObj topTargetObj);
}
