package com.sitech.basd.sxcloud.rsmu.dao.cloudschedu;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.cloudschedu.RealServerObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;


public class RealServerDaoImpl extends BaseDao implements RealServerDao {

	public int deleteByObj(RealServerObj obj) {

		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().delete("RealServer.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("RealServer.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public int insertByObj(RealServerObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().insert("RealServer.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("RealServer.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public RealServerObj queryByObj(RealServerObj obj) {
		// TODO Auto-generated method stub
		List lst = null;
		RealServerObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (RealServerObj) lst.get(0);
		}
		return tempObj;
	}

	public List queryForListByObj(RealServerObj obj) {
		// TODO Auto-generated method stub
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"RealServer.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("RealServer.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("RealServer.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	public int updateByObj(RealServerObj obj) {

		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("RealServer.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("RealServer.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

}
