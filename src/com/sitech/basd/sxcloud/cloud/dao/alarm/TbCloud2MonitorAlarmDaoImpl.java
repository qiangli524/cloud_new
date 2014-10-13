package com.sitech.basd.sxcloud.cloud.dao.alarm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitech.basd.sxcloud.cloud.domain.alarm.TbCloud2MonitorAlarmObj;
import com.sitech.basd.sxcloud.cloud.domain.monitor.MonitorAlarmLocationObj;
import com.sitech.basd.sxcloud.cloud.domain.monitor.MonitorAlarmObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * 
 * <p>
 * Title: TbCloud2MonitorAlarmDaoImpl
 * </p>
 * <p>
 * Description: 告警事件操作类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Mar 26, 2012 4:47:25 PM
 * 
 */
public class TbCloud2MonitorAlarmDaoImpl extends BaseDao implements TbCloud2MonitorAlarmDao {
	/*
	 * <p>Title: insertAlarmByObj</p> <p>Description: </p> @param obj @return
	 * 
	 * @see
	 * com.sitech.basd.cloud.dao.alarm.TbCloud2MonitorAlarmDao#insertAlarmByObj
	 * (com.sitech.basd.cloud.domain.alarm.TbCloud2MonitorAlarmObj)
	 */
	public int insertAlarmByObj(TbCloud2MonitorAlarmObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbCloud2MonitorAlarm.insertAlarmByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("TbCloud2MonitorAlarm.insertAlarmByObj:" + e.getMessage());
		}
		return ret;
	}

	/*
	 * <p>Title: queryOutOfDateData</p> <p>Description: </p> @param obj
	 * 
	 * @return
	 * 
	 * @see
	 * com.sitech.basd.cloud.dao.alarm.TbCloud2MonitorAlarmDao#queryOutOfDateData
	 * (com.sitech.basd.cloud.domain.alarm.TbCloud2MonitorAlarmObj)
	 */
	public List<TbCloud2MonitorAlarmObj> queryOutOfDateData(TbCloud2MonitorAlarmObj obj) {
		List<TbCloud2MonitorAlarmObj> list = null;
		try {
			list = (List<TbCloud2MonitorAlarmObj>) getSqlMap().queryForList(
					"TbCloud2MonitorAlarm.queryAlarmDataOutOneMonth", obj);
		} catch (Exception e) {
			LogHelper.error("TbCloud2MonitorAlarm.queryAlarmDataOutOneMonth:" + e.getMessage());
		}
		return list;
	}

	/*
	 * <p>Title: deleteAlarmByObj</p> <p>Description: </p> @param obj @return
	 * 
	 * @see
	 * com.sitech.basd.cloud.dao.alarm.TbCloud2MonitorAlarmDao#deleteAlarmByObj
	 * (com.sitech.basd.cloud.domain.alarm.TbCloud2MonitorAlarmObj)
	 */
	public int deleteAlarmByObj(TbCloud2MonitorAlarmObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbCloud2MonitorAlarm.deleteAlarmDataByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("TbCloud2MonitorAlarm.deleteAlarmDataByObj:" + e.getMessage());
		}
		return ret;
	}

	/*
	 * <p>Title: queryAlarmDataByObj</p> <p>Description: </p> @param obj
	 * 
	 * @return
	 * 
	 * @see
	 * com.sitech.basd.cloud.dao.alarm.TbCloud2MonitorAlarmDao#queryAlarmDataByObj
	 * (com.sitech.basd.cloud.domain.alarm.TbCloud2MonitorAlarmObj)
	 */
	public TbCloud2MonitorAlarmObj queryAlarmDataByObj(TbCloud2MonitorAlarmObj obj) {
		TbCloud2MonitorAlarmObj rObj = null;
		try {
			rObj = (TbCloud2MonitorAlarmObj) getSqlMap().queryForObject(
					"TbCloud2MonitorAlarm.queryAlarmDataByObj", obj);
		} catch (Exception e) {
			LogHelper.error("TbCloud2MonitorAlarm.queryAlarmDataByObj:" + e.getMessage());
		}
		return rObj;
	}

	/*
	 * <p>Title: updateAlarmDataTime</p> <p>Description: </p> @param obj
	 * 
	 * @return
	 * 
	 * @see
	 * com.sitech.basd.cloud.dao.alarm.TbCloud2MonitorAlarmDao#updateAlarmDataTime
	 * (com.sitech.basd.cloud.domain.alarm.TbCloud2MonitorAlarmObj)
	 */
	public int updateAlarmDataTime(TbCloud2MonitorAlarmObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbCloud2MonitorAlarm.updateAlarmDataTime", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("TbCloud2MonitorAlarm.updateAlarmDataTime:" + e.getMessage());
		}
		return ret;
	}

	/*
	 * <p>Title: updateAlarmDataStat</p> <p>Description: </p> @param obj
	 * 
	 * @return
	 * 
	 * @see
	 * com.sitech.basd.cloud.dao.alarm.TbCloud2MonitorAlarmDao#updateAlarmDataStat
	 * (com.sitech.basd.cloud.domain.alarm.TbCloud2MonitorAlarmObj)
	 */
	public int updateAlarmDataStat(TbCloud2MonitorAlarmObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbCloud2MonitorAlarm.updateAlarmDataStat", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("TbCloud2MonitorAlarm.updateAlarmDataStat:" + e.getMessage());
		}
		return ret;
	}

	private static String pattern = "yyyyMMddHHmmss";
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

	/*
	 * <p>Title: getDemoAlarmObj</p> <p>Description: </p> @return
	 * 
	 * @see
	 * com.sitech.basd.cloud.dao.alarm.TbCloud2MonitorAlarmDao#getDemoAlarmObj()
	 */
	public TbCloud2MonitorAlarmObj getDemoAlarmObj() {
		TbCloud2MonitorAlarmObj alarmobj = new TbCloud2MonitorAlarmObj();
		alarmobj.setALARM_TIME(dateFormat.format(new Date()));
		alarmobj.setEVENT_LEVEL("0");// 0严重告警,1主要告警，2次要告警，3不确定告警
		alarmobj.setEVENT_LOCATION("告警发生位置");
		alarmobj.setEVENT_STAT("0");// 默认0未处理状态
		alarmobj.setTITILE("告警标题-告警标题");
		alarmobj.setCONTENT("告警内容-告警详细信息");
		alarmobj.setEVENT_TYPE("0");// 0应用告警，1虚拟机，2物理主机，3机房告警
		return alarmobj;
	}

	/*
	 * <p>Title: insertOrUpdateAlarm</p> <p>Description: </p> @param obj
	 * 
	 * @see
	 * com.sitech.basd.cloud.dao.alarm.TbCloud2MonitorAlarmDao#insertOrUpdateAlarm
	 * (com.sitech.basd.cloud.domain.alarm.TbCloud2MonitorAlarmObj)
	 */
	public void insertOrUpdateAlarm(TbCloud2MonitorAlarmObj obj) {
		TbCloud2MonitorAlarmObj rObj = queryAlarmDataByObj(obj);
		if (rObj != null) {
			int count = rObj.getEVENT_COUNT();
			rObj.setEVENT_COUNT(count + 1);
			rObj.setALARM_TIME(obj.getALARM_TIME());
			updateAlarmDataTime(rObj);
		} else {
			insertAlarmByObj(obj);
		}
	}

	/*
	 * <p>Title: alarmClear</p> <p>Description: </p> @param obj
	 * 
	 * @see
	 * com.sitech.basd.cloud.dao.alarm.TbCloud2MonitorAlarmDao#alarmClear(com
	 * .sitech.basd.cloud.domain.alarm.TbCloud2MonitorAlarmObj)
	 */
	public void alarmClear(TbCloud2MonitorAlarmObj obj) {
		TbCloud2MonitorAlarmObj rObj = queryAlarmDataByObj(obj);
		if (rObj != null) {
			insertAlarmByObj(rObj);
			deleteAlarmByObj(rObj);
		}
	}

	/*
	 * <p>Title: updateAlarmDataStatService</p> <p>Description: </p> @param obj
	 * 
	 * @see com.sitech.basd.cloud.dao.alarm.TbCloud2MonitorAlarmDao#
	 * updateAlarmDataStatService
	 * (com.sitech.basd.cloud.domain.alarm.TbCloud2MonitorAlarmObj)
	 */
	public void updateAlarmDataStatService(TbCloud2MonitorAlarmObj obj) {
		TbCloud2MonitorAlarmObj rObj = queryAlarmDataByObj(obj);
		updateAlarmDataStat(rObj);
		insertAlarmByObj(rObj);
		deleteAlarmByObj(rObj);
	}

	/**
	 * 
	 * @Title: insertAlarmWhenConnError
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 29, 2012 8:13:07 PM
	 */
	public void insertAlarmWhenConnError() {
		String pattern = "yyyyMMddHHmmss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		TbCloud2MonitorAlarmObj alarmObj = new TbCloud2MonitorAlarmObj();
		alarmObj.setALARM_TIME(dateFormat.format(new Date()));
		alarmObj.setEVENT_LEVEL("1");// 0严重告警,1主要告警，2次要告警，3不确定告警
		alarmObj.setEVENT_LOCATION("KVM(Or Director)内部错误或连接错误");
		alarmObj.setEVENT_STAT("0");// 默认0未处理状态
		alarmObj.setEVENT_TYPE("1");// 0应用告警，1虚拟机，2物理主机，3机房告警
		alarmObj.setTITILE("KVM(Or Director)内部错误或连接错误");
		alarmObj.setCONTENT("KVM(Or Director)内部错误或连接错误：连接失败原因，网络故障或者等原因！");
		insertOrUpdateAlarm(alarmObj);
	}

	public Map<String,Long> getAlarms(MonitorAlarmObj obj) {
		Map<String,Long> map=new HashMap<String,Long>();
		try {
			map = getSqlMap().queryForMap("TbCloud2MonitorAlarm.getAlarmsNum",obj,"level","levelcount");
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud2MonitorAlarm.getAlarmsNum:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return map;
	}
	public MonitorAlarmLocationObj queryLocationForVm(MonitorAlarmLocationObj obj){
		MonitorAlarmLocationObj Obj = null;
		try {
			Obj = (MonitorAlarmLocationObj) getSqlMap().queryForObject(
					"TbCloud2MonitorAlarm.queryLocationForVm", obj);
		} catch (Exception e) {
			LogHelper.error("TbCloud2MonitorAlarm.queryLocationForVm:" + e.getMessage());
		}
		return Obj;
	}
	public MonitorAlarmLocationObj queryLocationForHost(MonitorAlarmLocationObj obj){
		MonitorAlarmLocationObj Obj = null;
		try {
			Obj = (MonitorAlarmLocationObj) getSqlMap().queryForObject(
					"TbCloud2MonitorAlarm.queryLocationForHost", obj);
		} catch (Exception e) {
			LogHelper.error("TbCloud2MonitorAlarm.queryLocationForHost:" + e.getMessage());
		}
		return Obj;
	}
	public MonitorAlarmLocationObj queryLocationForStore(MonitorAlarmLocationObj obj){
		MonitorAlarmLocationObj Obj = null;
		try {
			Obj = (MonitorAlarmLocationObj) getSqlMap().queryForObject(
					"TbCloud2MonitorAlarm.queryLocationForStore", obj);
		} catch (Exception e) {
			LogHelper.error("TbCloud2MonitorAlarm.queryLocationForStore:" + e.getMessage());
		}
		return Obj;
	}
}
