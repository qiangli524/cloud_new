package com.sitech.basd.yicloud.dao.database;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.appmessage.TbCloudAppInfoObj;
import com.sitech.basd.yicloud.domain.database.AppDatabaseObj;

public interface AppDatabaseDao {
	/**
	 * @Title:查询已有列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List<AppDatabaseObj> queryForListByObj(AppDatabaseObj obj);

	/**
	 * @Title:查询一条信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public AppDatabaseObj queryByObj(AppDatabaseObj obj);

	/**
	 * @Title:插入新添加的信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String insertByObj(AppDatabaseObj obj);

	/**
	 * @Title:更新信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int updateByObj(AppDatabaseObj obj);

	/**
	 * @Title:删除信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int deleteByObj(AppDatabaseObj obj);

	/**
	 * @Title:查询应用连接的数据库
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List<AppDatabaseObj> queryBizSysDataBase(TbCloudAppInfoObj obj);
}
