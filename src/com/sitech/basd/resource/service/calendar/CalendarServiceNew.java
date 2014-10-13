package com.sitech.basd.resource.service.calendar;

import java.util.Map;

import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.resource.domain.calendar.CalendarObj;

/**
 * 
 * <p>Title: CalendarService</p>
 * <p>Description: 资源使用情况统计</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH</p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-5-14 上午10:47:29
 * 
 */
public interface CalendarServiceNew {
	/**
	 * @Title:查询CPU资源使用情况
	 * @Company: si-tech
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-5-14
	 */
	public FusionCharts queryCpuList(CalendarObj calendarObj);

	/**
	 * @Title:查询内存资源使用情况
	 * @Company: si-tech
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-5-14
	 */
	public FusionCharts queryMemList(CalendarObj calendarObj);

	/**
	 * @Title:查询存储资源使用情况
	 * @Company: si-tech
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-5-14
	 */
	public FusionCharts queryStorageList(CalendarObj calendarObj);
	/**
	 * @Title:查询IP资源使用情况
	 * @Company: si-tech
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-5-14
	 */
	public FusionCharts queryNetList(CalendarObj calendarObj);

}
