package com.sitech.ssd.ah.paas.busiResource.dao.host;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.date.TimeformatCommon;

/**
 * <p>
 * Title: PaasHostMonitorDaoImpl
 * </p>
 * <p>
 * Description: paas主机监控DAO实现类
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
 * @createtime 2014-7-29 下午4:41:17
 * 
 */
@Repository("paasHostMonitorDao")
public class PaasHostMonitorDaoImpl extends BaseDao implements PaasHostMonitorDao {

	/**
	 * 跟据cpu和内存查询时间轴x
	 * 
	 * @Title: queryPowerDayData1
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2013-11-13 下午7:22:44
	 */
	@Override
	public List queryPowerDayTimeLabelAndData(String id, String kpi, String time_inter,String eq_type) {
		List lst = new ArrayList();
		Map<String, String> map = convertMap(id, kpi, time_inter, null, null, null, null, null,
				"day",eq_type);
		try {
			lst = getSqlMap().queryForList("paasHostMonitor.queryPowerDayTimeLabelAndData", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("paasHostMonitor.queryPowerDayTimeLabelAndData:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

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
	@Override
	public Data queryHostDiskNetMonitor(String id, String kpi, String interval,String eq_type) {
		Data data = null;
		try {
			Map<String, String> map = convertMap(id, kpi, interval, null, null, null, null, null,
					"day",eq_type);
			data = (Data) getSqlMap().queryForObject("paasHostMonitor.queryMaxAndAvg", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("paasHostMonitor.queryMaxAndAvg:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return data;
	}

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
	@Override
	public List queryPowerDayTimeLabelAndDataByCurrentMonth(String id, String kpi,String eq_type) {
		List lst = new ArrayList();
		Map<String, String> map = convertMap(id, kpi, null, null, null, null, null, null,
				"currentMonth",eq_type);
		try {
			lst = getSqlMap().queryForList("paasHostMonitor.queryPowerDayTimeLabelAndDataByMonth",
					map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("paasHostMonitor.queryPowerDayTimeLabelAndDataByMonth:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

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
	@Override
	public List queryPowerDayTimeLabelAndDataByLastMonth(String id, String kpi,String eq_type) {
		List lst = new ArrayList();
		Map<String, String> map = convertMap(id, kpi, null, null, null, null, null, null,
				"lastMonth",eq_type);
		try {
			lst = getSqlMap().queryForList("paasHostMonitor.queryPowerDayTimeLabelAndDataByMonth",
					map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("paasHostMonitor.queryPowerDayTimeLabelAndDataByMonth:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

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
	@Override
	public List queryPowerDayTimeLabelAndDataBetweenByCurrentDay(String id, String kpi,
			String startTime, String endTime,String eq_type) {
		List lst = new ArrayList();
		Map<String, String> map = convertMap(id, kpi, null, null, null, null, startTime, endTime,
				"day",eq_type);
		try {
			lst = getSqlMap().queryForList("paasHostMonitor.queryPowerDayTimeLabelAndDataBetween",
					map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("paasHostMonitor.queryPowerDayTimeLabelAndDataBetween:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

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
	@Override
	public List queryPowerDayTimeLabelAndDataBetween(String id, String kpi, String year,
			String month, String day, String startTime, String endTime,String eq_type) {
		List lst = new ArrayList();
		Map<String, String> map = convertMap(id, kpi, null, year, month, day, startTime, endTime,
				"userDefinedMonth",eq_type);
		try {

			lst = getSqlMap().queryForList("paasHostMonitor.queryPowerDayTimeLabelAndDataBetween",
					map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("paasHostMonitor.queryPowerDayTimeLabelAndDataBetween:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

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
	@Override
	public List queryPowerDayTimeLabelAndDataBetween(String id, String kpi, String year,
			String month, String startTime, String endTime,String eq_type) {
		List lst = new ArrayList();
		Map<String, String> map = convertMap(id, kpi, null, year, month, null, startTime, endTime,
				"userDefined",eq_type);
		try {
			lst = getSqlMap().queryForList("paasHostMonitor.queryPowerDayTimeLabelAndDataBetween",
					map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("paasHostMonitor.queryPowerDayTimeLabelAndDataBetween:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

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
	@Override
	public List queryFileSystemDayTimeLabelAndDataByDate(Map<String, String> param) {
		List lst = new ArrayList(0);
		try {
			lst = getSqlMap().queryForList(
					"paasHostMonitor.queryFileSystemDayTimeLabelAndDataByDate", param);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("paasHostMonitor.queryFileSystemDayTimeLabelAndDataByDate:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

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
	@Override
	public List queryFileSystemDayTimeLabelAndDataByMonth(Map<String, String> param) {
		List lst = new ArrayList(0);
		try {
			lst = getSqlMap().queryForList(
					"paasHostMonitor.queryFileSystemDayTimeLabelAndDataByMonth", param);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("paasHostMonitor.queryFileSystemDayTimeLabelAndDataByMonth:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

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
	@Override
	public List queryFileSystemDayTimeLabelAndData(Map<String, String> param) {
		List lst = new ArrayList(0);
		try {
			lst = getSqlMap().queryForList("paasHostMonitor.queryFileSystemDayTimeLabelAndData",
					param);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("paasHostMonitor.queryFileSystemDayTimeLabelAndData:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

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
	@Override
	public java.util.Map<String, String> convertMap(String id, String kpi, String time_inter,
			String year, String month, String day, String startTime, String endTime, String flag,String eq_type) {
		Map<String, String> map = new HashMap<String, String>();
		String tableName = null;
		if (flag.equals("day")) {
			tableName = "TB_CLOUD2_AGENT_COLL";// 天
		} else if (flag.equals("currentMonth")) {
			String temp = TimeformatCommon.GetCurrentSysTime("yyyyMM");
			tableName = "TB_CLOUD2_AGENT_COLL_" + temp;// 本月
		} else if (flag.equals("lastMonth")) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			String temp = TimeformatCommon.GetCurrentSysTime(c.getTime(), "yyyyMM");
			tableName = "TB_CLOUD2_AGENT_COLL_" + temp;// 上月
		} else if (flag.equals("userDefined")) {
			if (!month.startsWith("0")) {
				month = "0" + month;
			}
			String temp = year + month;
			tableName = "TB_CLOUD2_AGENT_COLL_" + temp;// 自定义查询
		} else if (flag.equals("userDefinedMonth")) {
			String temp = year + month + day;
			tableName = "TB_CLOUD2_AGENT_COLL_" + temp;// 自定义查询 (查询一个月之内)
		}
		map.put("KPI_ID", kpi);
		map.put("EXT_EQ_ID", id);
		map.put("EQ_ID", id);
		map.put("START_TIME", startTime);
		map.put("END_TIME", endTime);
		map.put("TIME_INTER", time_inter);
		map.put("tableName", tableName);
		map.put("EQ_TYPE", eq_type);
		return map;
	}

	/**
	 * 
	 * @Title: queryFileSystemUrl
	 * @Description: 查询文件系统路径
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午11:39:40
	 */
	@Override
	public List queryFileSystemUrl(Map<String, String> param) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("paasHostMonitor.queryFileSystemUrl", param);
		} catch (Exception sqlexception) {
			LogHelper.error("paasHostMonitor.queryFileSystemUrl:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
}
