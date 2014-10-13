package com.sitech.basd.sxcloud.cloud.dao.resource;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostPoolObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class HostPoolDaoImpl extends BaseDao implements HostPoolDao {

	/**
	 * 
	 * @Title: 删除主机池信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	@Override
	public int deleteHostForPool(TbCloud2HostPoolObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("HostPool.deleteHostForPool", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("HostPool.deleteHostForPool:" + sqlexception.getMessage()
					+ getClass().getName());
		}

		return ret;
	}

	/**
	 * 
	 * @Title: 增加主机池信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	@Override
	public int insertHostForPool(TbCloud2HostPoolObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("HostPool.insertHostForPool", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("HostPool.insertHostForPool:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: 查询主机池信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	@Override
	public List queryHostForPool(TbCloud2HostPoolObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap()
								.queryForObject("HostPool.queryHostPoolForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("HostPool.queryHostForPool", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HostPool.queryHostForPool:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public List queryHostName(TbCloud2HostPoolObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("HostPool.queryHostPoolNameCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList("HostPool.queryHostForPoolName", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HostPool.queryHostForPool:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 修改主机池信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	@Override
	public int updateHostForPool(TbCloud2HostPoolObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("HostPool.updateHostForPool", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("HostPool.updateHostForPool:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	@Override
	public TbCloud2HostPoolObj queryHostObj(TbCloud2HostPoolObj obj) {

		TbCloud2HostPoolObj tObj = null;
		try {
			tObj = (TbCloud2HostPoolObj) getSqlMap().queryForObject("HostPool.queryHostObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HostPool.queryHostObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return tObj;
	}

	/**
	 * 
	 * @Title: 相同主机名称的个数统计
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	@Override
	public int getHostPoolCount(TbCloud2HostPoolObj obj) {
		int lstCount = 0;
		try {
			lstCount = ((Integer) getSqlMap().queryForObject("HostPool.getHostCount", obj))
					.intValue();
		} catch (Exception sqlexception) {
			LogHelper.error("HostPool.getHostCount:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lstCount;
	}

	@Override
	public int deleteHostRelation(TbCloud2HostPoolObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("HostPool.deleteHostRelation", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("HostPool.deleteHostRelation:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

}
