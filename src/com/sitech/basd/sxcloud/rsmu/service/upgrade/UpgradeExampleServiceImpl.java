package com.sitech.basd.sxcloud.rsmu.service.upgrade;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.upgrade.UpgradeExampleDao;
import com.sitech.basd.sxcloud.rsmu.domain.upgrade.UpgradeExampleObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class UpgradeExampleServiceImpl extends BaseService implements
		UpgradeExampleService {

	public int deleteByObj(UpgradeExampleObj obj) {
		// TODO Auto-generated method stub
		return upgradeExampleDao.deleteByObj(obj);
	}

	public int insertByObj(UpgradeExampleObj obj) {
		// TODO Auto-generated method stub
		return upgradeExampleDao.insertByObj(obj);
	}

	public UpgradeExampleObj queryByObj(UpgradeExampleObj obj) {
		// TODO Auto-generated method stub
		return upgradeExampleDao.queryByObj(obj);
	}

	public List queryForListByObj(UpgradeExampleObj obj) {
		// TODO Auto-generated method stub
		return upgradeExampleDao.queryForListByObj(obj);
	}

	public List queryListIDByObj(UpgradeExampleObj obj) {
		// TODO Auto-generated method stub
		return upgradeExampleDao.queryListIDByObj(obj);
	}

	public int updateByObj(UpgradeExampleObj obj) {
		// TODO Auto-generated method stub
		return upgradeExampleDao.updateByObj(obj);
	}

	public int upgradeByObj(UpgradeExampleObj obj) {
		// TODO Auto-generated method stub
		return upgradeExampleDao.upgradeByObj(obj);
	}

	public int startByObj(UpgradeExampleObj obj) {
		// TODO Auto-generated method stub
		return upgradeExampleDao.startByObj(obj);
	}

	public int stopByObj(UpgradeExampleObj obj) {
		// TODO Auto-generated method stub
		return upgradeExampleDao.stopByObj(obj);
	}

	/**
	 * @Title:更改部署或卸载的完成百分比
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public void updateUPGRADE_PERCENT(UpgradeExampleObj obj) {
		upgradeExampleDao.updateUPGRADE_PERCENT(obj);
	}

	/**
	 * @Title:更改启动或停止的完成百分比
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public void updateSTART_STOP_PERCENT(UpgradeExampleObj obj) {
		upgradeExampleDao.updateSTART_STOP_PERCENT(obj);
	}

	UpgradeExampleDao upgradeExampleDao;

	public void setUpgradeExampleDao(UpgradeExampleDao upgradeExampleDao) {
		this.upgradeExampleDao = upgradeExampleDao;
	}
}
