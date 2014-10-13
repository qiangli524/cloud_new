package com.sitech.basd.sxcloud.cloud.dao.reportvirtual;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitech.basd.cloud3.domain.charts.DataPoint;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.sxcloud.cloud.domain.reporthost.TbHostObj;
import com.sitech.basd.sxcloud.cloud.domain.reportvirtual.TbHyObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.date.TimeformatCommon;

public class TbHyDaoImpl extends BaseDao implements TbHyDao {

	/**
	 * @Title:查询当天的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHyForToday(TbHyObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHy.queryTbHyForToday", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHy.queryTbHyForToday:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询本周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHyForWeek(TbHyObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHy.queryTbHyForWeek", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHy.queryTbHyForWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:跨表查询本周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryUnionTbHyForWeek(TbHyObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHy.queryUnionTbHyForWeek", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHy.queryUnionTbHyForWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询上周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHyForLastWeek(TbHyObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHy.queryTbHyForLastWeek", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHy.queryTbHyForLastWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:跨表查询上周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryUnionTbHyForLastWeek(TbHyObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHy.queryUnionTbHyForLastWeek",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHy.queryUnionTbHyForLastWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询当月的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHyForMonth(TbHyObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHy.queryTbHyForMonth", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHy.queryTbHyForMonth:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询上月的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHyForLastMonth(TbHyObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHy.queryTbHyForLastMonth", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHy.queryTbHyForLastMonth:"
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
	public int queryMonitorVirtualCount(TbCloud2VirtualInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().queryForObject(
					"TbHy.queryMonitorVirtualCount", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbHy.queryMonitorVirtualCount:"
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
	public int queryAllVirtualCount(TbCloud2VirtualInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().queryForObject("TbHy.queryAllVirtualCount",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbHy.queryAllVirtualCount:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryVirtualData
	 * @Description: 查询虚拟机监控数据
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 25, 2013 1:41:39 PM
	 */
	@Override
	public List queryVirtualMonitorData(Map map) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbHy.queryVirtualMonitorData", map);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHy.queryVirtualData:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryHyData
	 * @Description:查询虚拟机数据，highCharts展示
	 * @author duangh
	 * @date Jul 10, 2013 11:08:41 AM
	 * @param map
	 * @return
	 */
	@Override
	public List<DataPoint> queryHyData(Map map) {
		List<DataPoint> list = null;
		try {
			list = getSqlMap().queryForList("TbHy.queryHyData", map);
		} catch (Exception sqlexception) {
			LogHelper.error("TbHy.queryHyData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	// 查询1周 的监控数据从月表中查询
	@Override
	public List queryVmMonitorDataByWeek(Map map) {
		String temp = TimeformatCommon.GetCurrentSysTime("yyyyMM");
		String tableName = "TB_CLOUD2_HY_COLL_" + temp;
		map.put("tableName", tableName);
		List lst = null;
		try {
			lst = getSqlMap()
					.queryForList("TbHy.queryVmMonitorDataByWeek", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHy.queryVmMonitorDataByWeek:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	// 查询一个月的监控数据
	@Override
	public List queryVmMonitorDataByMonth(Map map) {
		List lst = null;
		String temp = TimeformatCommon.GetCurrentSysTime("yyyyMM");
		String tableName = "TB_CLOUD2_HY_COLL_" + temp;
		map.put("tableName", tableName);
		try {
			lst = getSqlMap().queryForList("TbHy.queryVmMonitorDataByMonth",
					map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHy.queryVmMonitorDataByMonth:"
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
				null, null, null, "day",null,null);
		try {
			lst = getSqlMap().queryForList(
					"TbHy.queryPowerDayTimeLabelAndData", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHy.queryPowerDayTimeLabelAndData:"
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
				null, null, "currentMonth",null,null);
		try {
			lst = getSqlMap().queryForList(
					"TbHy.queryPowerDayTimeLabelAndDataByMonth", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHy.queryPowerDayTimeLabelAndDataByMonth:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	// 上月横坐标
	@Override
	public List queryPowerDayTimeLabelAndDataByLastMonth(String id, String kpi) {
		List lst = new ArrayList();
		Map<String, String> map = convertMap(id, kpi, null, null, null, null,
				null, null, "lastMonth",null,null);
		try {
			lst = getSqlMap().queryForList(
					"TbHy.queryPowerDayTimeLabelAndDataByMonth", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHy.queryPowerDayTimeLabelAndDataByMonth:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	// 自定义查询横坐标
	@Override
	public List queryPowerDayTimeLabelAndDataBetween(String id, String kpi,
			String year, String month, String startTime, String endTime,String startUsage,String endUsage) {
		List lst = new ArrayList();
		Map<String, String> map = convertMap(id, kpi, null, year, month, null,
				startTime, endTime, "userDefined",startUsage,endUsage);
		try {
			lst = getSqlMap().queryForList(
					"TbHy.queryPowerDayTimeLabelAndDataBetween", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHy.queryPowerDayTimeLabelAndDataBetween:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	// 自定义查询(在一个月之内 查询天表)
	@Override
	public List queryPowerDayTimeLabelAndDataBetween(String id, String kpi,
			String year, String month, String day, String startTime,
			String endTime,String startUsage,String endUsage) {
		List lst = new ArrayList();
		Map<String, String> map = convertMap(id, kpi, null, year, month, day,
				startTime, endTime, "userDefinedMonth",startUsage,endUsage);
		try {
			lst = getSqlMap().queryForList(
					"TbHy.queryPowerDayTimeLabelAndDataBetween", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHy.queryPowerDayTimeLabelAndDataBetween:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	// 自定义查询(在一个月之内 查询当天)
	@Override
	public List queryPowerDayTimeLabelAndDataBetweenByCurrentDay(String id,
			String kpi, String startTime, String endTime,String startUsage,String endUsage) {
		List lst = new ArrayList();
		Map<String, String> map = convertMap(id, kpi, null, null, null,
				null,startTime, endTime,"day",startUsage,endUsage);
		try {
			lst = getSqlMap().queryForList(
					"TbHy.queryPowerDayTimeLabelAndDataBetween", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHy.queryPowerDayTimeLabelAndDataBetween:"
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
			String startTime, String endTime, String flag,String startUsage,String endUsage) {
		Map<String, String> map = new HashMap<String, String>();
		String tableName = null;
		if (flag.equals("day")) {
			tableName = "TB_CLOUD2_HY_COLL";// 天
		} else if (flag.equals("currentMonth")) {
			String temp = TimeformatCommon.GetCurrentSysTime("yyyyMM");
			tableName = "TB_CLOUD2_HY_COLL_" + temp;// 本月
		} else if (flag.equals("lastMonth")) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			String temp = TimeformatCommon.GetCurrentSysTime(c.getTime(),
					"yyyyMM");
			tableName = "TB_CLOUD2_HY_COLL_" + temp;// 上月
		} else if (flag.equals("userDefined")) {
			String temp = year + month;
			tableName = "TB_CLOUD2_HY_COLL_" + temp;// 自定义查询
		} else if (flag.equals("userDefinedMonth")) {
			String temp = year + month + day;
			tableName = "TB_CLOUD2_HY_COLL_" + temp;// 自定义查询 (查询一个月之内)
		}
		map.put("KPI_ID", kpi);
		map.put("HY_ID", id);
		map.put("START_TIME", startTime);
		map.put("END_TIME", endTime);
		map.put("TIME_INTER", time_inter);
		map.put("tableName", tableName);
		map.put("startUsage", startUsage);
		map.put("endUsage", endUsage);
		
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
		List lst = new ArrayList();
		try {
			lst = getSqlMap().queryForList(
					"TbHy.queryFileSystemDayTimeLabelAndData", param);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHy.queryFileSystemDayTimeLabelAndData:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 *
	 * @see com.sitech.basd.sxcloud.cloud.dao.reportvirtual.TbHyDao#queryHostDiskNetMonitor(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Data queryHostDiskNetMonitor(String hyId, String kpi, String interval) {
		Data data = null;
		try {
			Map<String, String> map = convertMap(hyId, kpi, interval, null, null,
					null, null, null, "day",null,null);
			data = (Data) getSqlMap().queryForObject(
					"TbHy.queryMaxAndAvg", map);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHy.queryMaxAndAvg:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return data;
	}

	@Override
	public TbHyObj queryTheNewestData(TbHyObj tbHyObj) {
		TbHyObj obj = null;
		try {
			obj = (TbHyObj) getSqlMap().queryForObject(
					"TbHy.queryTheNewestData", tbHyObj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbHy.queryTheNewestData:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return obj;
	}
	
	
}
