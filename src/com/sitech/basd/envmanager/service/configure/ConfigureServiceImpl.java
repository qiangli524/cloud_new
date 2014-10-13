package com.sitech.basd.envmanager.service.configure;

import java.util.List;

import com.sitech.basd.envmanager.dao.configure.ConfigureDao;
import com.sitech.basd.envmanager.domain.configure.ConfigureObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class ConfigureServiceImpl  extends BaseService implements ConfigureService{

	ConfigureDao configureDao;

	public ConfigureDao getConfigureDao() {
		return configureDao;
	}
	
	public void setConfigureDao(ConfigureDao configureDao) {
		this.configureDao = configureDao;
	}
	/**
	 * @Title:查询所有开发环境使用情况信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public List queryConfigureObj(ConfigureObj obj) {
		// TODO Auto-generated method stub
		return configureDao.queryConfigureObj(obj);
	}
	/**
	 * @Title:查某个开发环境使用情况信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public ConfigureObj queryConfigureOne(ConfigureObj obj) {
		// TODO Auto-generated method stub
		return configureDao.queryConfigureOne(obj);
	}
	/**
	 * @Title:删除开发环境使用情况信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int deleteConfigureObj(ConfigureObj obj) {
		// TODO Auto-generated method stub
		return configureDao.deleteConfigureObj(obj);
	}
	/**
	 * @Title:添加开发环境使用情况信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int insertConfigureObj(ConfigureObj obj) {
		// TODO Auto-generated method stub
		return configureDao.insertConfigureObj(obj);
	}

	/**
	 * @Title:修改开发环境使用情况信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int updateConfigureObj(ConfigureObj obj) {
		// TODO Auto-generated method stub
		return configureDao.updateConfigureObj(obj);
	}
	
	
}
