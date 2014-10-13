package com.sitech.basd.envmanager.service.version;

import java.util.List;

import com.sitech.basd.envmanager.dao.version.VersionDao;
import com.sitech.basd.envmanager.domain.version.VersionObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class VersionServiceImpl extends BaseService implements VersionService{
	
	VersionDao versionDao;


	/**
	 * @Title:删除版本信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int deleteVersionObj(VersionObj obj) {
		// TODO Auto-generated method stub
		return versionDao.deleteVersionObj(obj);
	}
	/**
	 * @Title:添加版本信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int insertVersionObj(VersionObj obj) {
		// TODO Auto-generated method stub
		return versionDao.insertVersionObj(obj);
	}
	/**
	 * @Title:查询所有版本信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public List queryVersionObj(VersionObj obj) {
		// TODO Auto-generated method stub
		return versionDao.queryVersionObj(obj);
	}
	/**
	 * @Title:查询某个版本信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public VersionObj queryVersionOne(VersionObj obj) {
		// TODO Auto-generated method stub
		return versionDao.queryVersionOne(obj);
	}
	/**
	 * @Title:修改某个版本信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int updateVersionObj(VersionObj obj) {
		// TODO Auto-generated method stub
		return versionDao.updateVersionObj(obj);
	}
	
	
	public VersionDao getVersionDao() {
		return versionDao;
	}

	public void setVersionDao(VersionDao versionDao) {
		this.versionDao = versionDao;
	}
	

}
