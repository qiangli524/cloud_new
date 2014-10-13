package com.sitech.basd.yicloud.dao.database;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.appmessage.TbCloudAppInfoObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.database.AppDatabaseObj;
import com.sitech.utils.randomid.RandomUUID;

public class AppDatabaseDaoImpl extends BaseDao implements AppDatabaseDao {
	/**
	 * @Title:查询已有列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List<AppDatabaseObj> queryForListByObj(AppDatabaseObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"AppDatabase.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap()
					.queryForList("AppDatabase.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("AppDatabase.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询一条信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public AppDatabaseObj queryByObj(AppDatabaseObj obj) {
		List lst = null;
		AppDatabaseObj tObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tObj = (AppDatabaseObj) lst.get(0);
		}
		return tObj;
	}

	/**
	 * @Title:插入新添加的信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String insertByObj(AppDatabaseObj obj) {
		String ret = "";
		try {
			String ID = RandomUUID.getUuid();
			obj.setId(ID);
			Object o = getSqlMap().insert("AppDatabase.insertByObj", obj);
			if (o != null) {
				ret = o.toString();
			}
		} catch (Exception sqlexception) {
			ret = "-1";
			LogHelper.error("AppDatabase.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更新信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int updateByObj(AppDatabaseObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("AppDatabase.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("AppDatabase.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int deleteByObj(AppDatabaseObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("AppDatabase.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("AppDatabase.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询应用连接的数据库
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List<AppDatabaseObj> queryBizSysDataBase(TbCloudAppInfoObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("AppDatabase.queryBizSysDataBase",
					obj);
		} catch (Exception sqlException) {
			LogHelper.error("AppDatabase.queryBizSysDataBase:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return lst;
	}
}
