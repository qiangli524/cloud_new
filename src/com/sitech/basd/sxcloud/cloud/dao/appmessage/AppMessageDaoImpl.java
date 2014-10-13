package com.sitech.basd.sxcloud.cloud.dao.appmessage;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.appmessage.AppMessageObj;
import com.sitech.basd.sxcloud.cloud.domain.appmessage.BizsysObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.UUIDGenerator;

@SuppressWarnings("rawtypes")
public class AppMessageDaoImpl extends BaseDao implements AppMessageDao {
	/**
	 * @Title:根据模块ID模糊查询应用信息列表
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public List queryAppMessageObjByUnion(AppMessageObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList(
					"AppMessage.queryAppMessageObjByUnion", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("AppMessage.queryAppMessageObjByUnion:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询出具体应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public AppMessageObj queryByObj(AppMessageObj obj) {
		List lst = null;
		AppMessageObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (AppMessageObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:根据应用部分信息查询匹配的所有应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public List queryForListByObj(AppMessageObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"AppMessageInfo.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("AppMessageInfo.queryForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("AppMessageInfo.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:添加应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("static-access")
	public int insertByObj(AppMessageObj obj) {
		UUIDGenerator uuid = new UUIDGenerator();
		obj.setAPP_ID(uuid.getUUID());
		int ret = 0;
		try {
			Object o = getSqlMap().insert("AppMessageInfo.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("AppMessageInfo.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更新应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int updateByObj(AppMessageObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("AppMessageInfo.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("AppMessageInfo.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除应用信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByObj(AppMessageObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("AppMessageInfo.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("AppMessageInfo.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:根据应用部分信息查询匹配的所有应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public List queryForListByBizsysObj(BizsysObj obj) {
		List lst = null;
		try {

			lst = getSqlMap().queryForList(
					"AppMessageInfo.queryForListByBizsysObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("AppMessageInfo.queryForListByBizsysObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
}
