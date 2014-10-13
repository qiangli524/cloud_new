package com.sitech.basd.sxcloud.cloud.dao.appmessage;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.appmessage.TbCloudAppInfoObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbCloudAppInfoDaoImpl extends BaseDao implements TbCloudAppInfoDao {
	/**
	 * @Title:查询业务系统下的应用
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryBizsysAppList(TbCloudAppInfoObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbCloudAppInfo.queryAppListBizsys",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloudAppInfo.queryAppListBizsys:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:插入应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(TbCloudAppInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbCloudAppInfo.insertByObj", obj);
			ret = Integer.parseInt(o.toString());
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbCloudAppInfo.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询和应用关联的基准应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppListBase(TbCloudAppInfoObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbCloudAppInfo.queryAppListBase",
					obj);
		} catch (Exception sqlException) {
			LogHelper.error("TbCloudAppInfo.queryAppListBase:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: getHostByBusi
	 * @Description: 根据业务系统获取对应应用
	 * @param
	 * @return TbCloudAppInfoObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 26, 2013 3:28:15 PM
	 */
	public List getAppByBusi(TbCloudAppInfoObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbCloudAppInfo.getAppByBusi", obj);
		} catch (Exception sqlException) {
			LogHelper.error("TbCloudAppInfo.getAppByBusi:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return lst;
	}
}
