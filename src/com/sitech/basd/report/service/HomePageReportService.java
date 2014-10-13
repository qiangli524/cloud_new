package com.sitech.basd.report.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.resource.domain.united.UnitedTree;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.utils.exception.HttpClientException;

/**
 * 
 * <p>
 * Title: HomePageReportService
 * </p>
 * <p>
 * Description: 首页报表Service
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-6-17 下午2:25:15
 * 
 */
public interface HomePageReportService {
	/**
	 * 
	 * @Title: initResourceStatisticsData
	 * @Description: 实例资源统计数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-17 下午4:43:26
	 */
	public String initResourceStatisticsData(String domain);

	/**
	 * 
	 * @Title: initResourceStatisticsTrendData
	 * @Description: 实例资源历史趋势数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-17 下午10:51:29
	 */
	public String initResourceStatisticsTrendData(String type);

	/**
	 * 
	 * @Title: initHistoryAlarmData
	 * @Description: 实例历史告警信息
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-18 下午2:54:50
	 */
	public String initHistoryAlarmData();

	/**
	 * @Title:initVmHostPfmcSummaryData
	 * @Description:虚拟主机性能情况数据
	 * @param type
	 * @return String
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @param domain 
	 * @createtime Aug 4, 2014 10:41:35 AM
	 */
	public String initVmHostPfmcSummaryData(String type, String domain);

	/**
	 * @Title:initVmHostDetailInfoData
	 * @Description:虚拟主机性能情况数据查询方法
	 * @param hyId
	 * @param type
	 * @return String
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @param domain 
	 * @createtime Aug 7, 2014 9:52:18 AM
	 */
	public String initVmHostDetailInfoData(String hyId, String type, String domain);

	/**
	 * @Title:queryVmHostDetailInfo
	 * @Description:虚拟机详细信息查询的方法
	 * @param hashMap
	 * @return List
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Aug 7, 2014 9:52:31 AM
	 */
	public List queryVmHostDetailInfo(HashMap<String, String> hashMap);
	
	/**
	 * @Title: queryLANPortRate
	 * @Description: LAN（半双工）端口利用率报表
	 * @param
	 * @return List
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-14 下午6:59:54
	 */
	public String queryLANPortRateReport(String domain);
	
	/**
	 * @Title: queryWANPortInRate
	 * @Description: WAN（全双工）端口流入利用率报表
	 * @param
	 * @return List
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-14 下午6:59:56
	 */
	public String queryWANPortInRateReport(String domain);
	
	/**
	 * @Title: queryWANPortOutRate
	 * @Description: WAN（全双工）端口流出利用率报表
	 * @param
	 * @return List
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-14 下午6:59:58
	 */
	public String queryWANPortOutRateReport(String domain);
	
	/**
	 * @Title: queryPortErrorRate
	 * @Description: 端口误码率报表
	 * @param
	 * @return List
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-14 下午7:00:01
	 */
	public String queryPortErrorRateReport(String domain);
	
	/**
	 * @Title: queryPortLostRate
	 * @Description: 端口丢包率报表
	 * @param
	 * @return List
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-14 下午7:00:03
	 */
	public String queryPortLostRateReport(String domain);
	
	/**
	 * @Title: queryCPUUsedRate
	 * @Description: 设备CPU利用率报表
	 * @param
	 * @return List
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-14 下午7:00:05
	 */
	public String queryCPUUsedRateReport(String domain);
	
	/**
	 * @Title: queryMemUsedRate
	 * @Description: 设备内存利用率报表
	 * @param
	 * @return List
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-14 下午7:00:07
	 */
	public String queryMemUsedRateReport(String domain);
	/**
	 * 
	 *  @方法名称:initDataCenterReportData
	 *  @方法描述:生成数据中心使用率数据
	 *  @作者 
	 *	@时间 2014-9-3 上午10:05:32
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param type
	 *  @return
	 *  @throws SQLException 
	 */
	public String initResourceReportData(HttpServletRequest request) throws SQLException;
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取父节点下所有子节点
	 *  @作者 
	 *	@时间 2014-9-3 下午4:40:11
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param parent_id
	 *  @return
	 *  @throws SQLException
	 */
	public List<UnitedTreeObj> getChildren(String parent_id) throws SQLException;
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取主机下所有虚拟机
	 *  @作者 
	 *	@时间 2014-9-3 下午4:26:41
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param parent_id
	 *  @return
	 *  @throws SQLException
	 */
	public List<UnitedTreeObj> getVirtualMachines(String parent_id) throws SQLException;
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取集群下所有主机
	 *  @作者 
	 *	@时间 2014-9-3 下午4:27:43
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param parent_id
	 *  @return
	 *  @throws SQLException
	 */
	public List<UnitedTreeObj> getHosts(String parent_id) throws SQLException;
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取集群下所有虚拟机
	 *  @作者 
	 *	@时间 2014-9-3 下午4:29:16
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param parent_id
	 *  @return
	 *  @throws SQLException
	 */
	public List<UnitedTreeObj> getHostVMs(String parent_id) throws SQLException;
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取数据中心下所有主机
	 *  @作者 
	 *	@时间 2014-9-3 下午4:37:41
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param parent_id
	 *  @return
	 *  @throws SQLException
	 */
	public List<UnitedTreeObj> getDCHosts(String parent_id) throws SQLException;
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取数据中心下所有虚拟机
	 *  @作者 
	 *	@时间 2014-9-3 下午4:43:47
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param parent_id
	 *  @return
	 *  @throws SQLException
	 */
	public List<UnitedTreeObj> getDCHostVMs(String parent_id) throws SQLException;
	
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取所有数据中心
	 *  @作者 
	 *	@时间 2014-9-3 下午4:46:32
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param parent_id
	 *  @return
	 *  @throws SQLException
	 */
	public List<UnitedTreeObj> getDCs() throws SQLException;
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取资源总池使用率数据
	 *  @作者 
	 *	@时间 2014-9-4 上午10:42:59
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param request
	 *  @return
	 */
	public String resourceMainReport(HttpServletRequest request) throws SQLException;
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取各数据中心使用率数据
	 *  @作者 
	 *	@时间 2014-9-4 下午2:53:58
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param request
	 *  @return
	 * @throws SQLException 
	 */
	public String resourceDCReport(HttpServletRequest request) throws SQLException;
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取各集群使用率数据
	 *  @作者 
	 *	@时间 2014-9-4 下午3:18:17
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param request
	 *  @return
	 * @throws SQLException 
	 */
	public String resourceGroupReport(HttpServletRequest request) throws SQLException;
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取某集群下所有主机使用率数据
	 *  @作者 
	 *	@时间 2014-9-4 下午3:43:18
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param request
	 *  @return
	 */
	
	public String resourceHostReport(HttpServletRequest request) throws SQLException;
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取IP下一级数据
	 *  @作者 
	 *	@时间 2014-9-5 下午2:57:13
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param request
	 *  @return
	 *  @throws SQLException
	 */
	public String getIPData(HttpServletRequest request) throws SQLException;

}
