package com.sitech.basd.resource.dao.calendar;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sitech.basd.resource.domain.calendar.CalendarObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * 
 * <p>
 * Title: CalendarDaoImpl
 * </p>
 * <p>
 * Description: 资源使用情况统计
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-05-21 上午10:46:39
 * 
 */
@Repository("calendarDao")
public class CalendarDaoImpl extends BaseDao implements CalendarDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,String>> getResource(CalendarObj calendarObj) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		List<Map<String,String>> lis = new ArrayList();
		paramMap.put("selectTime", calendarObj.getSelectTime());
		try {
			lis = getSqlMap().queryForList("CalendarNew.queryResource", paramMap);
		} catch (SQLException e) {
			LogHelper.error("CalendarNew.queryResource:" + e.getMessage()
					+ e.getClass().getName());
		}
		return lis;
	}

}
