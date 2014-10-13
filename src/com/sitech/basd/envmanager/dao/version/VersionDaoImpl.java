package com.sitech.basd.envmanager.dao.version;

import java.util.List;

import com.sitech.basd.envmanager.domain.ledger.LedgerObj;
import com.sitech.basd.envmanager.domain.version.VersionObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class VersionDaoImpl  extends BaseDao implements VersionDao{

	/**
	 * @Title:查询所有版本信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public List queryVersionObj(VersionObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Version.queryVersionObjCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("Version.queryVersionObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Version.queryVersionObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	@Override
	public int deleteVersionObj(VersionObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Version.deleteVersionObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Version.deleteVersionObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
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
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Version.insertVersionObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Version.insertVersionObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@Override
	public VersionObj queryVersionOne(VersionObj obj) {
		VersionObj vObj=null;
		try {
			vObj= (VersionObj) getSqlMap().queryForObject("Version.queryVersionOne",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Version.queryVersionOne:" 
					+ sqlexception.getMessage() + getClass().getName());
		}
		return vObj;
	}

	@Override
	public int updateVersionObj(VersionObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Version.updateVersionObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Version.updateVersionObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

}
