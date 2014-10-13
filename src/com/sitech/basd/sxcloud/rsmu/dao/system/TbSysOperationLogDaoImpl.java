package com.sitech.basd.sxcloud.rsmu.dao.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbSysOperationLogDaoImpl extends BaseDao implements
		TbSysOperationLogDao {

	public int insertByObj(TbSysOperationLogObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbSysOperationLog.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			sqlexception.printStackTrace();
			LogHelper.error("TbSysOperationLog.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public List queryForListByObj(TbSysOperationLogObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbSysOperationLog.queryForListByObjForCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList(
					"TbSysOperationLog.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbSysOperationLog.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:根据条件查询操作日志列表
	 * @Copyright: Copyright (c) 2012-10-24
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryOperationLogList(TbSysOperationLogObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						Integer.parseInt(obj.getCount()));
			}
			lst = getSqlMap().queryForList(
					"TbSysOperationLog.queryOperationLogList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbSysOperationLog.queryOperationLogList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
}
