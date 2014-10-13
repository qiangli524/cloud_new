package com.sitech.basd.sxcloud.rsmu.dao.upgrade;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.upgrade.UpgradeStrategyObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class UpgradeStrategyDaoImpl extends BaseDao implements
		UpgradeStrategyDao {

	public int deleteByObj(UpgradeStrategyObj obj) {

		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().delete("UpgradeStrategy.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("UpgradeStrategy.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public int insertByObj(UpgradeStrategyObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().insert("UpgradeStrategy.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("UpgradeStrategy.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public UpgradeStrategyObj queryByObj(UpgradeStrategyObj obj) {
		// TODO Auto-generated method stub
		List lst = null;
		UpgradeStrategyObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (UpgradeStrategyObj) lst.get(0);
		}
		return tempObj;
	}

	@SuppressWarnings("unchecked")
	public List queryForListByObj(UpgradeStrategyObj obj) {
		// TODO Auto-generated method stub
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"UpgradeStrategy.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("UpgradeStrategy.queryForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("UpgradeStrategy.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	public int updateByObj(UpgradeStrategyObj obj) {

		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("UpgradeStrategy.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("UpgradeStrategy.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

}
