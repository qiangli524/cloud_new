package com.sitech.basd.sxcloud.cloud.dao.loadBalancing;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.FarmServerObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class FarmsServerDaoImpl extends BaseDao implements FarmsServerDao {
	/**
	 * @Title:刪除FarmServer信息
	 * @Copyright: Copyright (c) 201010
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int deleteByObj(FarmServerObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().delete("FarmsService.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("FarmsService.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:插入FarmServer信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int insertByObj(FarmServerObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().insert("FarmsService.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("FarmsService.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询出具体FarmServer信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public FarmServerObj queryByObj(FarmServerObj obj) {
		// TODO Auto-generated method stub
		List lst = null;
		FarmServerObj FarmObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			FarmObj = (FarmServerObj) lst.get(0);
		}
		return FarmObj;
	}

	/**
	 * @Title:根据FarmServer部分信息查询匹配的所有FarmServer信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryForListByObj(FarmServerObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"FarmsService.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("FarmsService.queryForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("FarmsService.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:更新FarmServer信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int updateByObj(FarmServerObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("FarmsService.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("FarmsService.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

}
