package com.sitech.basd.sxcloud.cloud.service.eventmanage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitech.basd.rest.event.domain.EventInfo;
import com.sitech.basd.rest.event.domain.EventsInfo;
import com.sitech.basd.rest.event.operation.EventOperation;
import com.sitech.basd.sxcloud.cloud.dao.alarm.TbCloud2MonitorAlarmDao;
import com.sitech.basd.sxcloud.cloud.dao.eventmanage.EventManageDao;
import com.sitech.basd.sxcloud.cloud.domain.eventmanage.EventManageObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class EventManageServiceImpl extends BaseService implements
		EventManageService {

	@SuppressWarnings( { "unchecked", "unused" })
	private static List EventManageList = null;

	/**
	 * @Title:查询出具体SKC事件信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public EventManageObj queryByObj(EventManageObj obj) {
		EventManageObj eObj = new EventManageObj();
		EventInfo info = null;
		try {
			info = EventOperation.getEventInfoById(obj.getEVENT_ID());
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		}
		if (info != null) {
			eObj.setEVENT_ID(info.getId());
			eObj.setEVENT_INFO(info.getMessage());
			eObj.setINS_DATE(info.getTimestamp());
			eObj.setSOURCE_HAPPEN(info.getOriginator());
			eObj.setSERIOUS(info.getSeverity());
			return eObj;
		} else {
			eObj = eventManageDao.queryByObj(obj);
			return eObj;
		}
	}

	/**
	 * @Title:根据条件查询所有SKC事件信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(EventManageObj obj) {
		List<EventManageObj> list = new ArrayList<EventManageObj>();
		Map<String, String> paramMap = new HashMap<String, String>();
		if (obj.getPagination() != null) {
			paramMap.put("start", new Integer(obj.getPagination()
					.getFirstRownum()).toString());
			paramMap.put("count",
					new Integer(obj.getPagination().getPageSize()).toString());
		} else {
			paramMap.put("start", "0");
			paramMap.put("count", "8");
		}
		paramMap.put("sortBy", "timestamp");
		paramMap.put("order", "desc");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (obj.getStart_eventTime() != null
				&& !"".equals(obj.getStart_eventTime())) {
			Date startDate = new Date();
			try {
				startDate = sdf.parse(obj.getStart_eventTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			paramMap.put("startTime", new Long(startDate.getTime()).toString());
		}
		if (obj.getEnd_eventTime() != null
				&& !"".equals(obj.getEnd_eventTime())) {
			Date endDate = new Date();
			try {
				endDate = sdf.parse(obj.getEnd_eventTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			paramMap.put("endTime", new Long(endDate.getTime()).toString());
		}
		EventsInfo info = null;
		try {
			info = EventOperation.getAllEvent(paramMap);
			if (info != null) {
				if (obj.getPagination() != null) {
					obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
					obj.setPAGESIZE(obj.getPagination().getPageSize());
					obj.getPagination().setTotalCount(info.getTotal());
				}
				List<EventInfo> infoList = info.getEventList();
				for (EventInfo i : infoList) {
					EventManageObj o = new EventManageObj();
					o.setEVENT_ID(i.getId());
					o.setEVENT_INFO(i.getMessage());
					o.setINS_DATE(i.getTimestamp());
					o.setSOURCE_HAPPEN(i.getOriginator());
					// o.setSERIOUS(i.getSeverity());
					if ("INFO".equals(i.getSeverity())) {
						o.setSERIOUS("0");
					} else if ("WARNING".equals(i.getSeverity())) {
						o.setSERIOUS("1");
					} else if ("SERVER".equals(i.getSeverity())) {
						o.setSERIOUS("2");
					}
					list.add(o);
				}
				if (list != null && list.size() > 0) {
					return list;
				} else {
					return eventManageDao.queryForListByObj(obj);
				}
			} else {
				return eventManageDao.queryForListByObj(obj);
			}
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
			return null;
		}
	}

	private EventManageDao eventManageDao;
	private TbCloud2MonitorAlarmDao tbCloud2MonitorAlarmDao;

	public void setTbCloud2MonitorAlarmDao(
			TbCloud2MonitorAlarmDao tbCloud2MonitorAlarmDao) {
		this.tbCloud2MonitorAlarmDao = tbCloud2MonitorAlarmDao;
	}

	public void setEventManageDao(EventManageDao eventManageDao) {
		this.eventManageDao = eventManageDao;
	}

}
