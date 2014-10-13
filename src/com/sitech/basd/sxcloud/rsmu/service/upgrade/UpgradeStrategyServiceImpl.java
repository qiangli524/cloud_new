package com.sitech.basd.sxcloud.rsmu.service.upgrade;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.upgrade.UpgradeStrategyDao;
import com.sitech.basd.sxcloud.rsmu.domain.upgrade.UpgradeStrategyObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class UpgradeStrategyServiceImpl extends BaseService implements
		UpgradeStrategyService {

	public int deleteByObj(UpgradeStrategyObj obj) {
		// TODO Auto-generated method stub
		return upgradeStrategyDao.deleteByObj(obj);
	}

	public int insertByObj(UpgradeStrategyObj obj) {
		// TODO Auto-generated method stub
		return upgradeStrategyDao.insertByObj(obj);
	}

	public UpgradeStrategyObj queryByObj(UpgradeStrategyObj obj) {
		// TODO Auto-generated method stub
		return upgradeStrategyDao.queryByObj(obj);
	}

	public List queryForListByObj(UpgradeStrategyObj obj) {
		// TODO Auto-generated method stub
		return upgradeStrategyDao.queryForListByObj(obj);
	}

	public int updateByObj(UpgradeStrategyObj obj) {
		// TODO Auto-generated method stub
		return upgradeStrategyDao.updateByObj(obj);
	}

	UpgradeStrategyDao upgradeStrategyDao;

	public void setUpgradeStrategyDao(UpgradeStrategyDao upgradeStrategyDao) {
		this.upgradeStrategyDao = upgradeStrategyDao;
	}

}
