package com.sitech.basd.sxcloud.cloud.dao.reporthost;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.sxcloud.cloud.domain.reporthost.TbHostObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.date.TimeformatCommon;

public class TbHostDaoImpl extends BaseDao implements TbHostDao {

	/**
	 * @Title:查询当天的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHostForToday(TbHostObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHost.queryTbHostForToday", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHost.queryTbHostForToday:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询本周的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHostForWeek(TbHostObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHost.queryTbHostForWeek", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHost.queryTbHostForWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:跨表查询本周的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryUnionTbHostForWeek(TbHostObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHost.queryUnionTbHostForWeek",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHost.queryUnionTbHostForWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询上周的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHostForLastWeek(TbHostObj obj) {
		List lst = null;
		try {
			lst = getSqlMap()
					.queryForList("TbHost.queryTbHostForLastWeek", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHost.queryTbHostForLastWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:跨表查询上周的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryUnionTbHostForLastWeek(TbHostObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList(
					"TbHost.queryUnionTbHostForLastWeek", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHost.queryUnionTbHostForLastWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询当月的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHostForMonth(TbHostObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHost.queryTbHostForMonth", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHost.queryTbHostForMonth:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询上月的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHostForLastMonth(TbHostObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHost.queryTbHostForLastMonth",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHost.queryTbHostForLastMonth:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title: 查询已经监控的主机
	 * @Copyright: Copyright (c) 20120405
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@Override
	public int queryMonitorHostCount(TbCloud2HostInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().queryForObject(
					"TbHost.queryMonitorHostCount", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbHost.queryMonitorHostCount:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: 查询所有能监控的主机
	 * @Copyright: Copyright (c) 20120405
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@Override
	public int queryAllHostCount(TbCloud2HostInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().queryForObject("TbHost.queryAllHostCount",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbHost.queryAllHostCount:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryHostMonitorData
	 * @Description: 查询主机监控数据
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 25, 2013 1:41:39 PM
	 */
	@Override
	public List queryHostMonitorData(Map map) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHost.queryHostMonitorData", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHost.queryHostMonitorData:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	// 查询1周 的监控数据从月表中查询
	@Override
	public List queryHostMonitorDataByWeek(Map map) {
		String temp = TimeformatCommon.GetCurrentSysTime("yyyyMM");
		String tableName = "TB_CLOUD2_HOST_COLL_" + temp;
		map.put("tableName", tableName);
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHost.queryHostMonitorDataByWeek",
					map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHost.queryHostMonitorDataByWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	// 查询一个月的监控数据
	@Override
	public List queryHostMonitorDataByMonth(Map map) {
		List lst = null;
		String temp = TimeformatCommon.GetCurrentSysTime("yyyyMM");
		String tableName = "TB_CLOUD2_HOST_COLL_" + temp;
		map.put("tableName", tableName);
		try {
			lst = getSqlMap().queryForList(
					"TbHost.queryHostMonitorDataByMonth", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHost.queryHostMonitorDataByMonth:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryPowerDayTimeLabel
	 * @Description: 查询天表的横坐标
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-29 上午9:57:20
	 */
	public List queryPowerDayTimeLabel(TbHostObj obj) {
		List lst = null;
		try {
			lst = getSqlMap()
					.queryForList("TbHost.queryPowerDayTimeLabel", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHost.queryPowerDayTimeLabel:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryPowerDayTimeLabel
	 * @Description: 查询主机天的数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-29 上午9:57:20
	 */
	@Override
	public List queryPowerDayData(TbHostObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHost.queryPowerDayData", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHost.queryPowerDayData:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

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
	public List queryPowerDayTimeLabelAndData(String id, String kpi,
			String time_inter) {
		List lst = new ArrayList();
		Map<String, String> map = convertMap(id, kpi, time_inter, null, null,
				null, null, null, "day");
		try {
			lst = getSqlMap().queryForList(
					"TbHost.queryPowerDayTimeLabelAndData", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHost.queryPowerDayTimeLabelAndData:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	// 本月的横坐标
	@Override
	public List queryPowerDayTimeLabelAndDataByCurrentMonth(String id,
			String kpi) {
		List lst = new ArrayList();
		Map<String, String> map = convertMap(id, kpi, null, null, null, null,
				null, null, "currentMonth");
		try {
			lst = getSqlMap().queryForList(
					"TbHost.queryPowerDayTimeLabelAndDataByMonth", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHost.queryPowerDayTimeLabelAndDataByMonth:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	// 上月横坐标
	@Override
	public List queryPowerDayTimeLabelAndDataByLastMonth(String id, String kpi) {
		List lst = new ArrayList();
		Map<String, String> map = convertMap(id, kpi, null, null, null, null,
				null, null, "lastMonth");
		try {
			lst = getSqlMap().queryForList(
					"TbHost.queryPowerDayTimeLabelAndDataByMonth", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHost.queryPowerDayTimeLabelAndDataByMonth:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	// 自定义查询(在一个月之内 查询天表)
	@Override
	public List queryPowerDayTimeLabelAndDataBetween(String id, String kpi,
			String year, String month, String day, String startTime,
			String endTime) {
		List lst = new ArrayList();
		Map<String, String> map = convertMap(id, kpi, null, year, month,day,
				startTime, endTime, "userDefinedMonth");
		try {

			lst = getSqlMap().queryForList(
					"TbHost.queryPowerDayTimeLabelAndDataBetween", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHost.queryPowerDayTimeLabelAndDataBetween:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	// 自定义查询(在一个月之内 查询当天)
	@Override
	public List queryPowerDayTimeLabelAndDataBetweenByCurrentDay(String id,
			String kpi, String startTime, String endTime) {
		List lst = new ArrayList();
		Map<String, String> map = convertMap(id, kpi, null, null, null,
				null,startTime, endTime, "day");
		try {
			lst = getSqlMap().queryForList(
					"TbHost.queryPowerDayTimeLabelAndDataBetween", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHost.queryPowerDayTimeLabelAndDataBetween:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	// 自定义查询横坐标
	@Override
	public List queryPowerDayTimeLabelAndDataBetween(String id, String kpi,
			String year, String month, String startTime, String endTime) {
		List lst = new ArrayList();
		Map<String, String> map = convertMap(id, kpi, null, year, month, null,
				startTime, endTime, "userDefined");
		try {
			lst = getSqlMap().queryForList(
					"TbHost.queryPowerDayTimeLabelAndDataBetween", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHost.queryPowerDayTimeLabelAndDataBetween:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryPowerDayTrendData
	 * @Description: 查询主机天表使用率数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 下午2:38:28
	 */
	@Override
	public List queryPowerDayTrendData(TbHostObj obj) {
		List lst = null;
		try {
			lst = getSqlMap()
					.queryForList("TbHost.queryPowerDayTrendData", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHost.queryPowerDayTrendData:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: convertMap
	 * @Description: 
	 *               参数转换成Map(flag=day,查询天,表名不用拼接),(flag=currentMonth,查询本月,表名需要拼接
	 *               ), (flag=lastMonth;查询上月 ,表名需要拼接),
	 *               (flag=userDefined,自定义查询,表名需要拼接
	 *               ),(flag=userDefinedMonth,自定义查询,表名需要拼接 )
	 * @param
	 * @return Map<String,String>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-21 上午9:43:30
	 */
	@Override
	public java.util.Map<String, String> convertMap(String id, String kpi,
			String time_inter, String year, String month, String day,
			String startTime, String endTime, String flag) {
		Map<String, String> map = new HashMap<String, String>();
		String tableName = null;
		if (flag.equals("day")) {
			tableName = "TB_CLOUD2_HOST_COLL";// 天
		} else if (flag.equals("currentMonth")) {
			String temp = TimeformatCommon.GetCurrentSysTime("yyyyMM");
			tableName = "TB_CLOUD2_HOST_COLL_" + temp;// 本月
		} else if (flag.equals("lastMonth")) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			String temp = TimeformatCommon.GetCurrentSysTime(c.getTime(),
					"yyyyMM");
			tableName = "TB_CLOUD2_HOST_COLL_" + temp;// 上月
		} else if (flag.equals("userDefined")) {
			String temp = year + month;
			tableName = "TB_CLOUD2_HOST_COLL_" + temp;// 自定义查询
		} else if (flag.equals("userDefinedMonth")) {
			String temp = year + month + day;
			tableName = "TB_CLOUD2_HOST_COLL_" + temp;// 自定义查询 (查询一个月之内)
		}
		map.put("KPI_ID", kpi);
		map.put("EQ_ID", id);
		map.put("START_TIME", startTime);
		map.put("END_TIME", endTime);
		map.put("TIME_INTER", time_inter);
		map.put("tableName", tableName);
		return map;
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
	public List queryFileSystemDayTimeLabelAndData(Map<String,String> param) {
		List lst = new ArrayList(0);
		try {
			lst = getSqlMap().queryForList(
					"TbHost.queryFileSystemDayTimeLabelAndData", param);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHost.queryFileSystemDayTimeLabelAndData:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 *
	 * @see com.sitech.basd.sxcloud.cloud.dao.reporthost.TbHostDao#queryHostDiskNetMonitor(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Data queryHostDiskNetMonitor(String id, String kpi, String interval) {
		Data data = null;
		try {
			Map<String, String> map = convertMap(id, kpi, interval, null, null,
					null, null, null, "day");
			data = (Data) getSqlMap().queryForObject(
					"TbHost.queryMaxAndAvg", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHost.queryMaxAndAvg:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return data;
	}
	
	public TbHostObj queryTheNewestData(TbHostObj tbHostObj) {
		TbHostObj obj = null;
		try {
			obj = (TbHostObj) getSqlMap().queryForObject(
					"TbHost.queryTheNewestData", tbHostObj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHost.queryTheNewestData:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TbHostObj> queryTheNewestDatas(Map<String,Object> map) {
		List<TbHostObj> list = null;
		try {
			list = getSqlMap().queryForList(
					"TbHost.queryTheNewestDatas", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHost.queryTheNewestDatas:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return list;
	}
	
	public List<TbHostObj> queryTodayData(TbHostObj tbHostObj) {
		List<TbHostObj> list = null;
		try {
			list = getSqlMap().queryForList(
					"TbHost.queryTodayData", tbHostObj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHost.queryTodayData:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return list;
	}
	
}
