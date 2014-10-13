package com.sitech.basd.yicloud.dao.mytstat;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.mytstat.MysqlTableStatusObj;

/**
 * 
 * <p>
 * Title: MysqlTableStatusDaoImpl
 * </p>
 * <p>
 * Description: Mysql数据库表状态信息接口实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Jun 15, 2012 2:45:22 PM
 * 
 */
public class MysqlTableStatusDaoImpl extends BaseDao implements
		MysqlTableStatusDao {
	/**
	 * 
	 * @Title: getMysqlTableStatus
	 * @Description: Mysql数据库表状态信息接口
	 * @param
	 * @return MysqlTableStatusObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jun 15, 2012 2:50:21 PM
	 */
	@Override
	public MysqlTableStatusObj getMysqlTableStatus(String tableName) {
		String sql = "show table status like '" + tableName + "'";
		MysqlTableStatusObj obj = null;
		try {
			obj = (MysqlTableStatusObj) getSqlMap().queryForObject(
					"MysqlTableStatus.queryTableStatus", sql);
		} catch (Exception e) {
			LogHelper.error("MysqlTableStatus.queryTableStatus:"
					+ e.getMessage() + getClass().getName());
		}
		return obj;
	}

	public int getMysqlTableStatusString(String tableName) {
		String sql = "show table status like '" + tableName + "'";
		Integer obj = null;
		try {
			obj = (Integer) getSqlMap().queryForObject(
					"MysqlTableStatus.queryTableStatusString", sql);
		} catch (Exception e) {
			LogHelper.error("MysqlTableStatus.queryTableStatusString:"
					+ e.getMessage() + getClass().getName());
		}
		return obj;
	}

}
