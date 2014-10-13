package com.sitech.basd.sxcloud.cloud.dao.virtual;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualPoolObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.UUIDGenerator;

@SuppressWarnings("all")
public class TbVirtualPoolDaoImpl extends BaseDao implements TbVirtualPoolDao {
	/**
	 * @Title:查询已有监控虚拟机列表
	 * @Copyright: Copyright (c) 2012-03-31
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryForListByObj(TbCloud2VirtualPoolObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbVirtualPool.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("TbVirtualPool.queryForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbVirtualPool.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:添加监控虚拟机
	 * @Copyright: Copyright (c) 2012-03-31
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(TbCloud2VirtualPoolObj obj) {
		UUIDGenerator uuid = new UUIDGenerator();
		obj.setVH_ID(uuid.getUUID());
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbVirtualPool.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbVirtualPool.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除已监控虚拟机
	 * @Copyright: Copyright (c) 2012-03-31
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(TbCloud2VirtualPoolObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbVirtualPool.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbVirtualPool.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询并返回一个虚拟机对象
	 * @Copyright: Copyright (c) 2012-03-31
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public TbCloud2VirtualPoolObj queryByObj(TbCloud2VirtualPoolObj obj) {
		List lst = null;
		TbCloud2VirtualPoolObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbCloud2VirtualPoolObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:更新监控虚拟机信息
	 * @Copyright: Copyright (c) 2012-03-31
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(TbCloud2VirtualPoolObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbVirtualPool.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbVirtualPool.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:同步虚拟机数据到虚拟机资源池
	 * @Copyright: Copyright (c) 2012-04-11
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObjForSyn(TbCloud2VirtualPoolObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbVirtualPool.insertByObjForSyn",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbVirtualPool.insertByObjForSyn:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
}
