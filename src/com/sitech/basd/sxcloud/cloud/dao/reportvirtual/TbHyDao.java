package com.sitech.basd.sxcloud.cloud.dao.reportvirtual;

import java.util.List;
import java.util.Map;

import com.sitech.basd.cloud3.domain.charts.DataPoint;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.sxcloud.cloud.domain.reporthost.TbHostObj;
import com.sitech.basd.sxcloud.cloud.domain.reportvirtual.TbHyObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;

public interface TbHyDao {

	/**
	 * @Title:查询当天的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForToday(TbHyObj obj);

	/**
	 * @Title:查询本周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForWeek(TbHyObj obj);

	/**
	 * @Title:跨表查询本周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryUnionTbHyForWeek(TbHyObj obj);

	/**
	 * @Title:查询本周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForLastWeek(TbHyObj obj);

	/**
	 * @Title:跨表查询上周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryUnionTbHyForLastWeek(TbHyObj obj);

	/**
	 * @Title:查询当月的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForMonth(TbHyObj obj);

	/**
	 * @Title:查询上月的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForLastMonth(TbHyObj obj);

	/**
	 * @Title: 查询已经监控的虚拟机的个数
	 * @Copyright: Copyright (c) 20120406
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int queryMonitorVirtualCount(TbCloud2VirtualInfoObj obj);

	/**
	 * @Title: 查询已经监控的虚拟机的个数
	 * @Copyright: Copyright (c) 20120406
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int queryAllVirtualCount(TbCloud2VirtualInfoObj obj);

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
	public List queryVirtualMonitorData(Map map);

	/**
	 * 
	 * @Title: queryHyData
	 * @Description:查询虚拟机数据，highCharts展示
	 * @author duangh
	 * @date Jul 10, 2013 11:08:41 AM
	 * @param map
	 * @return
	 */
	public List<DataPoint> queryHyData(Map map);

	/**
	 * 采集一周的性能信息
	 * 
	 * @param map
	 * @return
	 */
	public List queryVmMonitorDataByWeek(Map map);

	/**
	 * 采集一个月的性能信息
	 * 
	 * @param map
	 * @return
	 */
	public List queryVmMonitorDataByMonth(Map map);

	public List queryPowerDayTimeLabelAndDataByCurrentMonth(String id,
			String kpi);

	public List queryPowerDayTimeLabelAndDataByLastMonth(String id, String kpi);

	public List queryPowerDayTimeLabelAndData(String id, String kpi,
			String time_inter);

	public List queryPowerDayTimeLabelAndDataBetween(String id, String kpi,
			String year, String month, String day, String startTime,
			String endTime,String startUsage,String endUsage);

	public List queryPowerDayTimeLabelAndDataBetween(String id, String kpi,
			String year, String month, String startTime, String endTime,String startUsage,String endUsage);

	public List queryPowerDayTimeLabelAndDataBetweenByCurrentDay(String id,
			String kpi, String startTime, String endTime,String startUsage,String endUsage);

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
			String startTime, String endTime, String flag,String startUsage,String endUsage);
	
	/**
	 * 
	 * @Title: queryFileSystemDayTimeLabelAndData
	 * @Description: 查询文件系统
	 * @param
	 * @return List
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-12-25 下午7:35:24
	 */
	public List queryFileSystemDayTimeLabelAndData(Map<String,String> param) ;

	/**
	 * <p>查询虚拟机某一时间段的磁盘和网络的平均读写速度和最大读写速度</p>
	 * 
	 * @param hyId
	 * @param string
	 * @param interval
	 * @return
	 */
	public Data queryHostDiskNetMonitor(String hyId, String string, String interval);
	
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:查询最新的告警数据
	 *  @作者 
	 *	@时间 2014-9-4 上午9:45:55
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param tbHostObj
	 *  @return
	 */
	public TbHyObj queryTheNewestData(TbHyObj tbHyObj);

}
