package com.sitech.ssd.ah.paas.busiResource.dao.host;

import java.util.List;
import java.util.Map;

import com.sitech.basd.fusioncharts.vo.Data;

/**
 * <p>
 * Title: PaasHostMonitorDao
 * </p>
 * <p>
 * Description: paas主机监控DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-7-29 下午4:40:22
 * 
 */
public interface PaasHostMonitorDao {
	/**
	 * 
	 * @Title: queryPowerDayTimeLabelAndData
	 * @Description: 跟据cpu和内存查询时间轴x
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:29:58
	 */
	public List queryPowerDayTimeLabelAndData(String id, String kpi, String time_inter,String eq_type);

	/**
	 * 
	 * @Title: queryHostDiskNetMonitor
	 * @Description: 查询主机某一时间段的磁盘和网络的平均读写速度和最大读写速度
	 * @param
	 * @return Data
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:30:28
	 */
	public Data queryHostDiskNetMonitor(String id, String kpi, String interval,String eq_type);

	/**
	 * 
	 * @Title: queryPowerDayTimeLabelAndDataByCurrentMonth
	 * @Description: 本月数据
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:31:14
	 */
	public List queryPowerDayTimeLabelAndDataByCurrentMonth(String id, String kpi,String eq_type);

	/**
	 * 
	 * @Title: queryPowerDayTimeLabelAndDataByLastMonth
	 * @Description: 上月数据
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:31:29
	 */
	public List queryPowerDayTimeLabelAndDataByLastMonth(String id, String kpi,String eq_type);

	/**
	 * 
	 * @Title: queryPowerDayTimeLabelAndDataBetweenByCurrentDay
	 * @Description: 自定义查询(在一个月之内 查询当天)
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:32:40
	 */
	public List queryPowerDayTimeLabelAndDataBetweenByCurrentDay(String id, String kpi,
			String startTime, String endTime,String eq_type);

	/**
	 * 
	 * @Title: queryPowerDayTimeLabelAndDataBetween
	 * @Description: 自定义查询(在一个月之内 查询天表)
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:33:20
	 */
	public List queryPowerDayTimeLabelAndDataBetween(String id, String kpi, String year,
			String month, String day, String startTime, String endTime,String eq_type);

	/**
	 * 
	 * @Title: queryPowerDayTimeLabelAndDataBetween
	 * @Description: 自定义查询横坐标
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:34:02
	 */
	public List queryPowerDayTimeLabelAndDataBetween(String id, String kpi, String year,
			String month, String startTime, String endTime,String eq_type);

	/**
	 * 
	 * @Title: queryFileSystemDayTimeLabelAndDataByDate
	 * @Description: 文件系统天表
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-30 下午8:20:17
	 */
	public List queryFileSystemDayTimeLabelAndDataByDate(Map<String, String> param);

	/**
	 * 
	 * @Title: queryFileSystemDayTimeLabelAndDataByMonth
	 * @Description: 文件系统本月和上月
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-30 下午8:20:17
	 */
	public List queryFileSystemDayTimeLabelAndDataByMonth(Map<String, String> param);

	/**
	 * 
	 * @Title: queryFileSystemDayTimeLabelAndData
	 * @Description: 查询文件系统指标
	 * @param
	 * @return List
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-12-25 下午7:34:39
	 */
	public List queryFileSystemDayTimeLabelAndData(Map<String, String> param);

	/**
	 * 
	 * @Title: convertMap
	 * @Description: 参数转换成Map(flag=day,查询天,表名不用拼接),(flag=currentMonth,查询本月,表名需要拼接 ),
	 *               (flag=lastMonth;查询上月 ,表名需要拼接), (flag=userDefined,自定义查询,表名需要拼接
	 *               ),(flag=userDefinedMonth,自定义查询,表名需要拼接 )
	 * @param
	 * @return Map<String,String>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-21 上午9:43:30
	 */
	public Map<String, String> convertMap(String id, String kpi, String time_inter, String year,
			String month, String day, String startTime, String endTime, String flag,String eq_type);

	/**
	 * 
	 * @Title: queryFileSystemUrl
	 * @Description: 查询文件系统路径
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午11:39:40
	 */
	public List queryFileSystemUrl(Map<String, String> param);

}
