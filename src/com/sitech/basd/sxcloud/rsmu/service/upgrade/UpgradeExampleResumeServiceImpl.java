package com.sitech.basd.sxcloud.rsmu.service.upgrade;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.upgrade.UpgradeExampleResumeDao;
import com.sitech.basd.sxcloud.rsmu.domain.upgrade.UpgradeExampleResumeObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class UpgradeExampleResumeServiceImpl extends BaseService implements
		UpgradeExampleResumeService {

	public int deleteByObj(UpgradeExampleResumeObj obj) {
		// TODO Auto-generated method stub
		return upgradeExampleResumeDao.deleteByObj(obj);
	}

	@SuppressWarnings("unchecked")
	public List queryForListByObj(UpgradeExampleResumeObj obj) {
		// TODO Auto-generated method stub
		return upgradeExampleResumeDao.queryForListByObj(obj);
	}

	public int resumeByObj(UpgradeExampleResumeObj obj) {
		// TODO Auto-generated method stub
		return upgradeExampleResumeDao.resumeByObj(obj);
	}

	UpgradeExampleResumeDao upgradeExampleResumeDao;

	public void setUpgradeExampleResumeDao(
			UpgradeExampleResumeDao upgradeExampleResumeDao) {
		this.upgradeExampleResumeDao = upgradeExampleResumeDao;
	}

}
