package com.sitech.basd.report.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: ReportService</p>
 * <p>Description: 报表数据处理service
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author chenyu
 * @version 1.0
 * @createtime 2014-6-14 上午9:45:45
 *
 */
public interface ReportService {
	
	/**
	 * @Title: getResourceOccupancyRate
	 * @Description: 查询资源占用率
	 * @param
	 * @return List<Map<String,String>>
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @throws SQLException 
	 * @createtime 2014-6-14 上午9:47:18
	 */
	public List<Map<String, String>> getResourceOccupancyRate(String userDomain) throws SQLException;

	/**
	 * @Title: getResourceTrend
	 * @Description:  统计资源占用趋势，默认查询一年，时间粒度为月
	 * @param
	 * @return List<Map<String,String>>
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @throws ParseException 
	 * @createtime 2014-6-14 上午10:15:57
	 */
	public List<Map<String, String>> getResourceTrend(String userDomain,
			String type, String startDateStr, String endDateStr)
			throws SQLException, ParseException;
	
	/**
	 * @Title: getResourceAlarmCount
	 * @Description: 查询当前告警统计
	 * @param
	 * @return List<Map<String,Long>>
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @createtime 2014-6-16 下午2:53:03
	 */
	public Map<String, String> getResourceAlarmCount()	throws SQLException;

}
