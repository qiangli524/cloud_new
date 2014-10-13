package com.sitech.taokeeper.dao;

import com.sitech.taokeeper.model.AlarmSettings;
import common.toolkit.java.exception.DaoException;

/**
 * Description: Access DB for alarm settings
 * 
 * @author yinshi.nc
 * @Date 2011-10-31
 */
public interface AlarmSettingsDAO {

	public AlarmSettings getAlarmSettingsByCulsterId(int clusterId) throws DaoException;

	public boolean updateAlarmSettingsByClusterId(AlarmSettings alarmSettings) throws DaoException;

	/** 添加一个报警设置 */
	public boolean addAlarmSettings(AlarmSettings alarmSettings) throws DaoException;

	/**
	 * 删除集群对应的告警配置
	 */
	public void deleteAlarmSettings(AlarmSettings obj);
}
