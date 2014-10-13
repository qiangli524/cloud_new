package com.sitech.basd.report.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.report.domain.ResourceStatisticsHisObj;

/**
 * 统计报表Dao
 * 
 * @author jiangdi
 * 
 */
public interface ReportDao {

	/**
	 * 查询资源占用率
	 * 
	 * @param userDomain
	 *            用户所属域
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Long>> getResourceOccupancyRate(String userDomain) throws SQLException;

	/**
	 * @Title: getResourceTrend
	 * @Description: 查询资源趋势
	 * @param
	 * @return List<Map<String,Long>>
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @createtime 2014-6-14 上午11:14:05
	 */
	public List<Map<String, Long>> getResourceTrend(String userDomain, String type,
			String startDateStr, String endDateStr) throws SQLException;

	/**
	 * @Title: getResourceAlarmCount
	 * @Description: 查询当前告警统计
	 * @param
	 * @return List<Map<String,Long>>
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @createtime 2014-6-16 下午2:39:51
	 */
	public List<Map<String, Long>> getResourceAlarmCount() throws SQLException;

	/**
	 * 
	 * @Title: queryStatisticsHistory
	 * @Description:查询统计历史数据
	 * @param
	 * @return List<ResourceStatisticsHisObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-17 下午10:27:23
	 */
	public List<ResourceStatisticsHisObj> queryStatisticsHistory(ResourceStatisticsHisObj obj)
			throws SQLException;
	
	public List<Map> getAlarmHistoryCount() throws SQLException;

	/**
	 * @Title:getVmHostPfmcSummarySumInfo
	 * @Description:查询虚拟机性能情况top数据的方法
	 * @param paramHashMap
	 * @return List
	 * @throws SQLException
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Aug 7, 2014 10:22:22 AM
	 */
	public List getVmHostPfmcSummarySumInfo(HashMap<String, String> paramHashMap) throws SQLException;

	/**
	 * @Title:getVmHostPfmcSummaryInfo
	 * @Description:查询虚拟机性能情况信息
	 * @param paramHashMap
	 * @return List
	 * @throws SQLException
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Aug 7, 2014 10:23:20 AM
	 */
	public List getVmHostPfmcSummaryInfo(HashMap<String, String> paramHashMap) throws SQLException;

	/**
	 * @Title:getVmHostDetailInfo
	 * @Description:查询虚拟机详细信息页面中的进程占用top信息的方法
	 * @param paramHashMap
	 * @return List
	 * @throws SQLException
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Aug 7, 2014 10:23:59 AM
	 */
	public List getVmHostDetailInfo(HashMap<String, String> paramHashMap) throws SQLException;

	/**
	 * @Title:queryVmHostDetailInfo
	 * @Description:查询虚拟机详细信息的方法
	 * @param hashMap
	 * @return List
	 * @throws SQLException
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Aug 7, 2014 10:26:22 AM
	 */
	public List queryVmHostDetailInfo(HashMap<String, String> hashMap) throws SQLException;

	/**
	 * @Title:queryLANPortRateReport
	 * @Description:AN（半双工）端口利用率查询方法
	 * @param paraHashMap
	 * @return List<Data>
	 * @throws SQLException
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Aug 18, 2014 12:25:46 PM
	 */
	public List<Data> queryLANPortRateReport(HashMap<String, String> paraHashMap)throws SQLException;

	/**
	 * @Title:queryWANPortInRateReport
	 * @Description:WAN（全双工）端口流入利用率查询方法
	 * @param paraHashMap
	 * @return List<Data>
	 * @throws SQLException
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Aug 18, 2014 12:26:10 PM
	 */
	public List<Data> queryWANPortInRateReport(HashMap<String, String> paraHashMap)throws SQLException;

	/**
	 * @Title:queryWANPortOutRateReport
	 * @Description:WAN（全双工）端口流出利用率查询方法
	 * @param paraHashMap
	 * @return List<Data>
	 * @throws SQLException
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Aug 18, 2014 12:26:21 PM
	 */
	public List<Data> queryWANPortOutRateReport(HashMap<String, String> paraHashMap)throws SQLException;

	/**
	 * @Title:queryPortErrorRateReport
	 * @Description:端口误码率查询方法
	 * @param paraHashMap
	 * @return List<Data>
	 * @throws SQLException
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Aug 18, 2014 12:26:29 PM
	 */
	public List<Data> queryPortErrorRateReport(HashMap<String, String> paraHashMap)throws SQLException;

	/**
	 * @Title:queryPortLostRateReport
	 * @Description:端口丢包率查询方法
	 * @param paraHashMap
	 * @return List<Data>
	 * @throws SQLException
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Aug 18, 2014 12:26:39 PM
	 */
	public List<Data> queryPortLostRateReport(HashMap<String, String> paraHashMap)throws SQLException;

	/**
	 * @Title:queryCPUUsedRateReport
	 * @Description:设备CPU利用率查询方法
	 * @param paraHashMap
	 * @return List<Data>
	 * @throws SQLException
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Aug 18, 2014 12:26:50 PM
	 */
	public List<Data> queryCPUUsedRateReport(HashMap<String, String> paraHashMap)throws SQLException;

	/**
	 * @Title:queryMemUsedRateReport
	 * @Description:设备内存利用率查询方法
	 * @param paraHashMap
	 * @return List<Data>
	 * @throws SQLException
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Aug 18, 2014 12:27:00 PM
	 */
	public List<Data> queryMemUsedRateReport(HashMap<String, String> paraHashMap)throws SQLException;
}
