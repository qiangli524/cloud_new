package com.sitech.basd.resource.dao.calendar;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.sitech.basd.resource.domain.calendar.CalendarObj;

/**
 * 
 * <p>Title: CalendarDao </p>
 * <p>Description: 资源使用情况统计 </p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p>Company: SI-TECH</p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2013-11-21 上午10:46:10
 * 
 */
public interface CalendarDao {
	/**
	 * 获取资源使用情况
	 * @param eq_type 设备类型
	 * @param proportion 比例数
	 * @param check_date 检查时间
	 * @return
	 */
	 //public Map<?, ?> getCpuResource(int eq_type, int proportion, Timestamp check_date);
	 public List<Map<String,String>> getResource(CalendarObj calendarObj);

}
