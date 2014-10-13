package com.sitech.basd.yicloud.dao.business;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.business.TbYicloudBusinessAppObj;

public class TbYicloudBusinessAppDaoImpl extends BaseDao implements
		TbYicloudBusinessAppDao {
	/**
	 * @Title:查询已有列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryForListByObj(TbYicloudBusinessAppObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj
						.getPagination()
						.setTotalCount(
								((Integer) getSqlMap()
										.queryForObject(
												"TbYicloudBusinessApp.queryByObjForCount",
												obj)).intValue());
			}
			lst = getSqlMap().queryForList(
					"TbYicloudBusinessApp.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbYicloudBusinessApp.queryForListByObj:"
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
	public TbYicloudBusinessAppObj queryByObj(TbYicloudBusinessAppObj obj) {
		List lst = null;
		TbYicloudBusinessAppObj tObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tObj = (TbYicloudBusinessAppObj) lst.get(0);
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
	public int insertByObj(TbYicloudBusinessAppObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbYicloudBusinessApp.insertByObj",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbYicloudBusinessApp.insertByObj:"
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
	public int updateByObj(TbYicloudBusinessAppObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbYicloudBusinessApp.updateByObj",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbYicloudBusinessApp.updateByObj:"
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
	public int deleteByObj(TbYicloudBusinessAppObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbYicloudBusinessApp.deleteByObj",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbYicloudBusinessApp.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询业务应用ID
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryAppIdList(TbYicloudBusinessAppObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList(
					"TbYicloudBusinessApp.queryAppIdList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbYicloudBusinessApp.queryAppIdList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
}
