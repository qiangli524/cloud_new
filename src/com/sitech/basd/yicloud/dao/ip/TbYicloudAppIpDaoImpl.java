package com.sitech.basd.yicloud.dao.ip;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.ip.TbYicloudAppIpObj;

public class TbYicloudAppIpDaoImpl extends BaseDao implements TbYicloudAppIpDao {
	/**
	 * @Title:查询已有IP列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryForListByObj(TbYicloudAppIpObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbYicloudAppIp.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("TbYicloudAppIp.queryForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbYicloudAppIp.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询一条IP信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public TbYicloudAppIpObj queryByObj(TbYicloudAppIpObj obj) {
		List lst = null;
		TbYicloudAppIpObj tObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tObj = (TbYicloudAppIpObj) lst.get(0);
		}
		return tObj;
	}

	/**
	 * @Title:查询业务应用Id列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryForAppIdList() {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbYicloudAppIp.queryForAppIdList");
		} catch (Exception sqlexception) {
			LogHelper.error("TbYicloudAppIp.queryForAppIdList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询绑定设备主机Id列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryForBindDeviceList() {
		List lst = null;
		try {
			lst = getSqlMap().queryForList(
					"TbYicloudAppIp.queryForBindDeviceList");
		} catch (Exception sqlexception) {
			LogHelper.error("TbYicloudAppIp.queryForBindDeviceList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:插入新添加的IP信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int insertByObj(TbYicloudAppIpObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbYicloudAppIp.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbYicloudAppIp.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更新IP信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int updateByObj(TbYicloudAppIpObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbYicloudAppIp.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbYicloudAppIp.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除P信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int deleteByObj(TbYicloudAppIpObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbYicloudAppIp.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbYicloudAppIp.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
}
