package com.sitech.basd.sxcloud.cloud.dao.reporthost;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.sxcloud.cloud.domain.reporthost.TbHostObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public interface TbHostDao {

	/**
	 * @Title:查询当天的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHostForToday(TbHostObj obj);

	/**
	 * @Title:查询本周的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHostForWeek(TbHostObj obj);

	/**
	 * @Title:跨表查询本周的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryUnionTbHostForWeek(TbHostObj obj);

	/**
	 * @Title:查询本周的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHostForLastWeek(TbHostObj obj);

	/**
	 * @Title:跨表查询上周的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryUnionTbHostForLastWeek(TbHostObj obj);

	/**
	 * @Title:查询当月的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHostForMonth(TbHostObj obj);

	/**
	 * @Title:查询上月的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHostForLastMonth(TbHostObj obj);

	/**
	 * @Title: 查询已经监控的主机
	 * @Copyright: Copyright (c) 20120405
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int queryMonitorHostCount(TbCloud2HostInfoObj obj);

	/**
	 * @Title: 查询所有能监控的主机
	 * @Copyright: Copyright (c) 20120405
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int queryAllHostCount(TbCloud2HostInfoObj obj);

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
	public List queryHostMonitorData(Map map);

	/**
	 * 采集一周的性能信息
	 * 
	 * @param map
	 * @return
	 */
	public List queryHostMonitorDataByWeek(Map map);

	/**
	 * 采集一个月的性能信息
	 * 
	 * @param map
	 * @return
	 */
	public List queryHostMonitorDataByMonth(Map map);

	public List queryPowerDayTimeLabelAndData(String id, String kpi,
			String time_inter);

	public List queryPowerDayData(TbHostObj obj);

	public List queryPowerDayTrendData(TbHostObj obj);

	public List queryPowerDayTimeLabelAndDataByCurrentMonth(String id,
			String kpi);

	public List queryPowerDayTimeLabelAndDataByLastMonth(String id, String kpi);

	public List queryPowerDayTimeLabelAndDataBetween(String id, String kpi,
			String year, String month, String day, String startTime,
			String endTime);

	public List queryPowerDayTimeLabelAndDataBetween(String id, String kpi,
			String year, String month, String startTime, String endTime);

	public List queryPowerDayTimeLabelAndDataBetweenByCurrentDay(String id,
			String kpi, String startTime, String endTime);

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
	public Map<String, String> convertMap(String id, String kpi,
			String time_inter, String year, String month, String day,
			String startTime, String endTime, String flag);
	
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
	public List queryFileSystemDayTimeLabelAndData(Map<String,String> param) ;

	/**
	 * <p>查询主机某一时间段的磁盘和网络的平均读写速度和最大读写速度</p>
	 * 
	 * @param id
	 * @param string
	 * @param interval
	 * @return
	 */
	public Data queryHostDiskNetMonitor(String id, String kpi, String interval);
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:查询最新的指标数据
	 *  @作者 
	 *	@时间 2014-9-4 上午9:45:55
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param tbHostObj
	 *  @return
	 */
	public TbHostObj queryTheNewestData(TbHostObj tbHostObj);
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:批量查询最新的指标数据
	 *  @作者 
	 *	@时间 2014-9-4 下午2:10:16
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param map
	 *  @return
	 */
	public List<TbHostObj> queryTheNewestDatas(Map<String,Object> map);

	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:查询某一主机当天的所有指标数据
	 *  @作者 
	 *	@时间 2014-9-4 下午4:39:11
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param tbHostObj
	 *  @return
	 */
	public List<TbHostObj> queryTodayData(TbHostObj tbHostObj);
}
