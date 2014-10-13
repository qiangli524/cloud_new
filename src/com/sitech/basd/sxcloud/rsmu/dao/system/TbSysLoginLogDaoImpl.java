package com.sitech.basd.sxcloud.rsmu.dao.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysLoginLogObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbSysLoginLogDaoImpl extends BaseDao implements TbSysLoginLogDao {

	/**
	 * @Title:获得登录日志列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysLoginLogObj obj) {
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
												"TbSysLoginLog.queryForListByObjForCount",
												obj)).intValue());
			}
			lst = getSqlMap().queryForList("TbSysLoginLog.queryForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbSysLoginLog.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:插入登录日志
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysLoginLogObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbSysLoginLog.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysLoginLog.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更新登录日志时间
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int updateByObj(TbSysLoginLogObj obj) {
		int ret = 0;
		try {
			ret = getSqlMap().update("TbSysLoginLog.updateByObj", obj);
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("TbSysLoginLog.updateByObj:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
}
