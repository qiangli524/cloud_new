package com.sitech.basd.yicloud.dao.storage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.storage.TbYicloudAppStorageObj;

public class TbYicloudAppStorageDaoImpl extends BaseDao implements
		TbYicloudAppStorageDao {
	/**
	 * @Title:查询已有列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryForListByObj(TbYicloudAppStorageObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbYicloudAppStorage.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList(
					"TbYicloudAppStorage.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbYicloudAppStorage.queryForListByObj:"
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
	public TbYicloudAppStorageObj queryByObj(TbYicloudAppStorageObj obj) {
		List lst = null;
		TbYicloudAppStorageObj tObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tObj = (TbYicloudAppStorageObj) lst.get(0);
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
	public int insertByObj(TbYicloudAppStorageObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbYicloudAppStorage.insertByObj",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbYicloudAppStorage.insertByObj:"
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
	public int updateByObj(TbYicloudAppStorageObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbYicloudAppStorage.updateByObj",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbYicloudAppStorage.updateByObj:"
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
	public int deleteByObj(TbYicloudAppStorageObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbYicloudAppStorage.deleteByObj",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbYicloudAppStorage.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
}
