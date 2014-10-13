package com.sitech.basd.sxcloud.rsmu.service.upgrade;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.upgrade.UpgradeAlarmDao;
import com.sitech.basd.sxcloud.rsmu.domain.upgrade.UpgradeAlarmObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class UpgradeAlarmServiceImpl extends BaseService implements
		UpgradeAlarmService {

	public List queryForListByObj(UpgradeAlarmObj obj) {
		// TODO Auto-generated method stub
		return upgradeAlarmDao.queryForListByObj(obj);
	}

	UpgradeAlarmDao upgradeAlarmDao;

	public void setUpgradeAlarmDao(UpgradeAlarmDao upgradeAlarmDao) {
		this.upgradeAlarmDao = upgradeAlarmDao;
	}
}
