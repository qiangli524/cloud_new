package com.sitech.basd.sxcloud.cloud.dao.loadBalancing;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.FarmObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class FarmsDaoImpl extends BaseDao implements FarmsDao {
	/**
	 * @Title:刪除Farm信息
	 * @Copyright: Copyright (c) 201010
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int deleteByObj(FarmObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Farms.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Farms.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:插入Farm信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int insertByObj(FarmObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Farms.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Farms.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询出具体Farm信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public FarmObj queryByObj(FarmObj obj) {
		// TODO Auto-generated method stub
		List lst = null;
		FarmObj FarmObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			FarmObj = (FarmObj) lst.get(0);
		}
		return FarmObj;
	}

	/**
	 * @Title:根据Farm部分信息查询匹配的所有Farm信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryForListByObj(FarmObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Farms.queryByObjForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("Farms.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Farms.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:更新Farm信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int updateByObj(FarmObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("Farms.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Farms.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

}
