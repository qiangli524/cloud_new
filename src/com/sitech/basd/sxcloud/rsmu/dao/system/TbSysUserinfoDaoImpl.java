package com.sitech.basd.sxcloud.rsmu.dao.system;

import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbSysUserinfoDaoImpl extends BaseDao implements TbSysUserinfoDao {

	/**
	 * @Title:根据账户部分信息查询匹配的所有账户信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysUserinfoObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbSysUserinfo.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("TbSysUserinfo.queryForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbSysUserinfo.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	
	/**
	 * @Title:根据账户部分信息 模糊查询匹配的所有账户信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryLikeForListByObj(TbSysUserinfoObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbSysUserinfo.queryLikeByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("TbSysUserinfo.queryLikeForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbSysUserinfo.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询出具体账户信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public TbSysUserinfoObj queryByObj(TbSysUserinfoObj obj) {
		List lst = null;
		TbSysUserinfoObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbSysUserinfoObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:更新账户信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int updateByObj(TbSysUserinfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbSysUserinfo.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysUserinfo.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除账户信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteByObj(TbSysUserinfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbSysUserinfo.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysUserinfo.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:插入账户信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysUserinfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbSysUserinfo.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysUserinfo.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更新账户数据权限信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int updateForDATAAUTHORITYByObj(TbSysUserinfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update(
					"TbSysUserinfo.updateForDATAAUTHORITYByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysUserinfo.updateForDATAAUTHORITYByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: 通过用户id查询用户所在的用户组leader属性
	 * @Copyright: Copyright (c) 2012-2-22
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String queryLeaderOrNotByUid(int uid) {
		String ret = "";
		try {
			ret = (String) getSqlMap().queryForObject(
					"TbSysUserinfo.queryLeaderOrNotByUid", uid);
		} catch (Exception sqlexception) {
			LogHelper.error("TbSysUserinfo.queryLeaderOrNotByUid:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: queryForUserByObj
	 * @Description: 查询用户信息
	 * @param
	 * @return TbSysUserinfoObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-26 下午2:52:07
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbSysUserinfoObj> queryForUserByObj(TbSysUserinfoObj userObj) {
		List<TbSysUserinfoObj> list = new ArrayList<TbSysUserinfoObj>();
		try {
			list = getSqlMap().queryForList("TbSysUserinfo.queryForUserByObj", userObj);
		} catch (Exception e) {
			LogHelper.error("TbSysUserinfo.queryForUserByObj: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}
}
