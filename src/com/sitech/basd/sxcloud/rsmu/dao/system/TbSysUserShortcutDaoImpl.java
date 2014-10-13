package com.sitech.basd.sxcloud.rsmu.dao.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserShortcutObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TempSysUserShortctObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbSysUserShortcutDaoImpl extends BaseDao implements
		TbSysUserShortcutDao {
	/**
	 * @Title:插入收藏功能
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysUserShortcutObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbSysUserShortcut.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysUserShortcut.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:根据账号ID查出所有收藏菜单
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List findFavoriteList(int id) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList(
					"TbSysUserShortcut.findFavoriteList", id);
		} catch (Exception sqlexception) {
			LogHelper.error("TbSysUserShortcut.findFavoriteList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:删除收藏菜单
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteForTbSysUserShortcut(TempSysUserShortctObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete(
					"TbSysUserShortcut.deleteForTbSysUserShortcut", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysUserShortcut.deleteForTbSysUserShortcut:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
}
