package com.sitech.basd.sxcloud.rsmu.dao.cloudschedu;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.cloudschedu.VirtualServerObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class VirtualServerDaoImpl extends BaseDao implements VirtualServerDao {

	public int deleteByObj(VirtualServerObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().delete("VirtualServer.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VirtualServer.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public int insertByObj(VirtualServerObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("VirtualServer.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VirtualServer.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public VirtualServerObj queryByObj(VirtualServerObj obj) {
		List lst = null;
		VirtualServerObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (VirtualServerObj) lst.get(0);
		}
		return tempObj;
	}

	public List queryForListByObj(VirtualServerObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"VirtualServer.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("VirtualServer.queryForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("VirtualServer.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	public int updateByObj(VirtualServerObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("VirtualServer.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VirtualServer.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

}
