package com.sitech.basd.yicloud.dao.opersystem;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.opersystem.OperSystemObj;

public class OperSystemDaoImpl extends BaseDao implements OperSystemDao {

	/**
	 * @Title:查询已有操作系统列表
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryForListByObj(OperSystemObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"OperSystem.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("OperSystem.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("OperSystem.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询一条操作系统信息
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public OperSystemObj queryByObj(OperSystemObj obj) {
		List lst = null;
		OperSystemObj tObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tObj = (OperSystemObj) lst.get(0);
		}
		return tObj;
	}

	/**
	 * @Title:插入新添加的操作系统信息
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(OperSystemObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("OperSystem.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("OperSystem.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更新操作系统信息
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(OperSystemObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("OperSystem.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("OperSystem.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除操作系统信息
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(OperSystemObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("OperSystem.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("OperSystem.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

}
