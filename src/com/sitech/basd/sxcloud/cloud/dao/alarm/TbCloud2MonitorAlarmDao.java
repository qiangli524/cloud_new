package com.sitech.basd.sxcloud.cloud.dao.alarm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitech.basd.sxcloud.cloud.domain.alarm.TbCloud2MonitorAlarmObj;
import com.sitech.basd.sxcloud.cloud.domain.monitor.MonitorAlarmLocationObj;
import com.sitech.basd.sxcloud.cloud.domain.monitor.MonitorAlarmObj;

public interface TbCloud2MonitorAlarmDao {

	/**
	 * 
	 * @Title: insertAlarmByObj
	 * @Description: 插入告警数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 26, 2012 4:48:02 PM
	 */
	public int insertAlarmByObj(TbCloud2MonitorAlarmObj obj);

	/**
	 * 
	 * @Title: queryOutOfDateData
	 * @Description: 查询超出一个月的数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 26, 2012 5:44:37 PM
	 */
	public List<TbCloud2MonitorAlarmObj> queryOutOfDateData(TbCloud2MonitorAlarmObj obj);

	/**
	 * 
	 * @Title: deleteAlarmByObj
	 * @Description: 删除告警数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 26, 2012 4:48:02 PM
	 */
	public int deleteAlarmByObj(TbCloud2MonitorAlarmObj obj);

	/**
	 * 
	 * @Title: queryAlarmDataByObj
	 * @Description: 根据obj条件查询告警信息
	 * @param
	 * @return TbCloud2MonitorAlarmObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 3:18:18 PM
	 */
	public TbCloud2MonitorAlarmObj queryAlarmDataByObj(TbCloud2MonitorAlarmObj obj);

	/**
	 * 
	 * @Title: updateAlarmDataTime
	 * @Description: 更新告警数据时间和告警次数
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 3:21:21 PM
	 */
	public int updateAlarmDataTime(TbCloud2MonitorAlarmObj obj);

	/**
	 * 
	 * @Title: updateAlarmDataTime
	 * @Description: 更新告警状态：已解决、未解决
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 3:21:21 PM
	 */
	public int updateAlarmDataStat(TbCloud2MonitorAlarmObj obj);

	/**
	 * 
	 * @Title: getDemoAlarmObj
	 * @Description: 获取告警对象模板
	 * @param
	 * @return TbCloud2MonitorAlarmObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 4:52:42 PM
	 */
	public TbCloud2MonitorAlarmObj getDemoAlarmObj();

	/**
	 * 
	 * @Title: checkAlarmUnique
	 * @Description:产生告警或者更新告警， 检查告警是否已产生，若存在此类告警，则将告警次数+1并更新告警时间，若无此类告警，则进行添加
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 5:02:40 PM
	 */
	public void insertOrUpdateAlarm(TbCloud2MonitorAlarmObj obj);

	/**
	 * 
	 * @Title: alarmClear
	 * @Description:告警消除，若整个程序执行完无告警产生，则检查告警表中是否有此类告警，存在则将该类告警自动消除；
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 5:10:27 PM
	 */
	public void alarmClear(TbCloud2MonitorAlarmObj obj);

	/**
	 * 
	 * @Title: updateAlarmDataStat
	 * @Description: 更新告警状态：解决、未解决
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 5:17:38 PM
	 */
	public void updateAlarmDataStatService(TbCloud2MonitorAlarmObj obj);

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
	public void insertAlarmWhenConnError();

	/**
	 * 
	 * @Title: getAlarms
	 * @Description: 统计不同级别告警数
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 22, 2013 3:56:39 PM
	 */
	public Map<String,Long> getAlarms(MonitorAlarmObj obj);
	
	/**
	 * @Title:queryLocationForVm
	 * @Description:查询告警虚拟机定位信息
	 * @param MonitorAlarmLocationObj
	 * @return MonitorAlarmLocationObj
	 * @author liwq_bj
	 * @version 1.0
	 * @createtime 2014-8-9 下午05:52:02
	 */
	public MonitorAlarmLocationObj queryLocationForVm(MonitorAlarmLocationObj obj);
	
	/**
	 * @Title:queryLocationForHost
	 * @Description:查询告警主机定位信息
	 * @param MonitorAlarmLocationObj
	 * @return MonitorAlarmLocationObj
	 * @author liwq_bj
	 * @version 1.0
	 * @createtime 2014-8-9 下午05:52:02 
	 */
	public MonitorAlarmLocationObj queryLocationForHost(MonitorAlarmLocationObj obj);
	
	/**
	 * @Title:queryLocationForStore
	 * @Description:查询告警主机定位信息
	 * @param MonitorAlarmLocationObj
	 * @return MonitorAlarmLocationObj
	 * @author liwq_bj
	 * @version 1.0
	 * @createtime 2014-8-9 下午05:52:02 
	 */
	public MonitorAlarmLocationObj queryLocationForStore(MonitorAlarmLocationObj obj);

}