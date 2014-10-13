package com.sitech.basd.sxcloud.rsmu.dao.system;

import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysGroupfuncObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbSysGroupfuncDaoImpl extends BaseDao implements TbSysGroupfuncDao {
	/**
	 * @Title:根据部分用户组功能关联信息查询匹配的所有用户组功能关联信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysGroupfuncObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbSysGroupfunc.queryForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbSysGroupfunc.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:插入用户组功能关联信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysGroupfuncObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbSysGroupfunc.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysGroupfunc.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除用户组功能关联信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteByObj(TbSysGroupfuncObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbSysGroupfunc.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysGroupfunc.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: queryForListUseIn
	 * @Description: 根据传入的属性集合查询符合条件的集合
	 * @param
	 * @return List<TbSysGroupfuncObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-26 下午3:12:20
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbSysGroupfuncObj> queryForListUseIn(TbSysGroupfuncObj funcObj) {
		List<TbSysGroupfuncObj> list = new ArrayList<TbSysGroupfuncObj>();
		try {
			list = getSqlMap().queryForList("TbSysGroupfunc.queryForListUseIn",funcObj);
		} catch (Exception e) {
			LogHelper.error("TbSysGroupfunc.queryForListUseIn: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}
}
