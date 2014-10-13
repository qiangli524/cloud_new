package com.sitech.taokeeper.dao.impl;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.taokeeper.dao.AlarmSettingsDAO;
import com.sitech.taokeeper.model.AlarmSettings;
import common.toolkit.java.exception.DaoException;

/**
 * Description: Access DB for alarm settings
 * 
 * @author yinshi.nc
 * @Date 2011-10-28
 */
@Repository("alarmSettingsDAO")
public class AlarmSettingsDAOImpl extends BaseDao implements AlarmSettingsDAO {

	@Override
	public AlarmSettings getAlarmSettingsByCulsterId(int clusterId) throws DaoException {
		AlarmSettings alarmSettings = null;
		try {
			AlarmSettings obj = new AlarmSettings();
			obj.setClusterId(clusterId);

			alarmSettings = (AlarmSettings) getSqlMapClient().queryForObject(
					"ZookeeperMonitorForAlarm.queryZooKeeperAlarmSettingsObjList", obj);
			return alarmSettings;
		} catch (Exception e) {
			throw new DaoException("Error when query AlarmSettings by cluster_id: " + clusterId
					+ ", Error: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean updateAlarmSettingsByClusterId(AlarmSettings alarmSettings) throws DaoException {

		if (null == alarmSettings)
			return false;
		try {
			int num = getSqlMapClient().update(
					"ZookeeperMonitorForAlarm.updateZooKeeperAlarmSettings", alarmSettings);
			if (1 == num) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new DaoException("Error when update AlarmSettings by cluster_id: "
					+ alarmSettings + ", Error: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean addAlarmSettings(AlarmSettings alarmSettings) throws DaoException {
		if (null == alarmSettings)
			return false;

		// 从数据库中获取指定zookeeper集群中所有机器
		try {
			getSqlMapClient().insert("ZookeeperMonitorForAlarm.insertZooKeeperAlarmSettings",
					alarmSettings);

			// if( 1 == num ){
			// return true;
			// }
			return true;
		} catch (Exception e) {
			throw new DaoException("Error when add AlarmSettings: " + alarmSettings + ", Error: "
					+ e.getMessage(), e);
		}
	}

	@Override
	public void deleteAlarmSettings(AlarmSettings obj) {
		try {
			Object ob = getSqlMap().delete("ZookeeperMonitorForAlarm.deleteAlarmSettings", obj);
		} catch (Exception e) {
			LogHelper.error("ZookeeperMonitorForAlarm.deleteAlarmSettings: " + e.getMessage()
					+ e.getClass().getName());
		}
	}
}
