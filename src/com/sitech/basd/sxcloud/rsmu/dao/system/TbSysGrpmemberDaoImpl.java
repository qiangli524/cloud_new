package com.sitech.basd.sxcloud.rsmu.dao.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysGrpmemberObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbSysGrpmemberDaoImpl extends BaseDao implements TbSysGrpmemberDao {
	/**
	 * @Title:查询出所有的组成员
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysGrpmemberObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbSysGrpmember.queryForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbSysGrpmember.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:删除用户组成员
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteByObj(TbSysGrpmemberObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbSysGrpmember.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysGrpmember.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	/**
	 * @Title:删除用户组成员
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteUserByObj(TbSysGrpmemberObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbSysGrpmember.deleteUserByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysGrpmember.deleteUserByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	/**
	 * @Title:插入组成员
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysGrpmemberObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbSysGrpmember.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysGrpmember.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
}
