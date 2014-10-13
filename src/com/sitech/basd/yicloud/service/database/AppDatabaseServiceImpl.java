package com.sitech.basd.yicloud.service.database;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.appmessage.TbCloudAppInfoObj;
import com.sitech.basd.yicloud.dao.database.AppDatabaseDao;
import com.sitech.basd.yicloud.domain.database.AppDatabaseObj;

public class AppDatabaseServiceImpl implements AppDatabaseService {
	/**
	 * @Title:查询已有列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List<AppDatabaseObj> queryForListByObj(AppDatabaseObj obj) {
		return appDatabaseDao.queryForListByObj(obj);
	}

	/**
	 * @Title:查询一条信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public AppDatabaseObj queryByObj(AppDatabaseObj obj) {
		return appDatabaseDao.queryByObj(obj);
	}

	/**
	 * @Title:插入新添加的信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String insertByObj(AppDatabaseObj obj) {
		return appDatabaseDao.insertByObj(obj);
	}

	/**
	 * @Title:更新信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int updateByObj(AppDatabaseObj obj) {
		return appDatabaseDao.updateByObj(obj);
	}

	/**
	 * @Title:删除信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int deleteByObj(AppDatabaseObj obj) {
		return appDatabaseDao.deleteByObj(obj);
	}

	/**
	 * @Title:查询应用连接的数据库
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List<AppDatabaseObj> queryBizSysDataBase(TbCloudAppInfoObj obj) {
		return appDatabaseDao.queryBizSysDataBase(obj);
	}

	AppDatabaseDao appDatabaseDao;

	public void setAppDatabaseDao(AppDatabaseDao appDatabaseDao) {
		this.appDatabaseDao = appDatabaseDao;
	}

}
