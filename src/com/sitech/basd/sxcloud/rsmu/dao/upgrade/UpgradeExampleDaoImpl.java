package com.sitech.basd.sxcloud.rsmu.dao.upgrade;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.upgrade.UpgradeExampleObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class UpgradeExampleDaoImpl extends BaseDao implements UpgradeExampleDao {

	public int deleteByObj(UpgradeExampleObj obj) {

		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().delete("UpgradeExample.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("UpgradeExample.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public int insertByObj(UpgradeExampleObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().insert("UpgradeExample.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("UpgradeExample.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	public UpgradeExampleObj queryByObj(UpgradeExampleObj obj) {
		// TODO Auto-generated method stub
		List lst = null;
		UpgradeExampleObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (UpgradeExampleObj) lst.get(0);
		}
		return tempObj;
	}

	@SuppressWarnings("unchecked")
	public List queryForListByObj(UpgradeExampleObj obj) {
		// TODO Auto-generated method stub
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"UpgradeExample.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("UpgradeExample.queryForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("UpgradeExample.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List queryListIDByObj(UpgradeExampleObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("UpgradeExample.queryListIDByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("UpgradeExample.queryListIDByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	public int updateByObj(UpgradeExampleObj obj) {

		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("UpgradeExample.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("UpgradeExample.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public int upgradeByObj(UpgradeExampleObj obj) {

		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("UpgradeExample.upgradeByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("UpgradeExample.upgradeByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public int startByObj(UpgradeExampleObj obj) {

		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("UpgradeExample.startByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("UpgradeExample.startByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public int stopByObj(UpgradeExampleObj obj) {

		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("UpgradeExample.stopByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("UpgradeExample.stopByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更改部署或卸载的完成百分比
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public void updateUPGRADE_PERCENT(UpgradeExampleObj obj) {
		try {
			getSqlMap().update("UpgradeExample.updateUPGRADE_PERCENT", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("UpgradeExample.updateUPGRADE_PERCENT:"
					+ sqlexception.getMessage());
		}
	}

	/**
	 * @Title:更改启动或停止的完成百分比
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public void updateSTART_STOP_PERCENT(UpgradeExampleObj obj) {
		try {
			getSqlMap().update("UpgradeExample.updateSTART_STOP_PERCENT", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("UpgradeExample.updateSTART_STOP_PERCENT:"
					+ sqlexception.getMessage());
		}
	}
}
