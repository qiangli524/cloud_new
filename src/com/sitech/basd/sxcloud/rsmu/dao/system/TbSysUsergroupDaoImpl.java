package com.sitech.basd.sxcloud.rsmu.dao.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUsergroupObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbSysUsergroupDaoImpl extends BaseDao implements TbSysUsergroupDao {
	/**
	 * @Title:根据用户组部分信息查询匹配的所有用户组信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysUsergroupObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbSysUsergroup.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("TbSysUsergroup.queryForListByObj",
					obj);
		} catch (Exception sqlexception) {sqlexception.printStackTrace();
			LogHelper.error("TbSysUsergroup.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询出具体用户组信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public TbSysUsergroupObj queryByObj(TbSysUsergroupObj obj) {
		List lst = null;
		TbSysUsergroupObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbSysUsergroupObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:更新用户组信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int updateByObj(TbSysUsergroupObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbSysUsergroup.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysUsergroup.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除用户组信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteByObj(TbSysUsergroupObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbSysUsergroup.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysUsergroup.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:插入用户组信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysUsergroupObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbSysUsergroup.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysUsergroup.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
}
