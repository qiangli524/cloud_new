package com.sitech.basd.sxcloud.rsmu.dao.hostmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbBusiHostConfigDaoImpl extends BaseDao implements TbBusiHostConfigDao {

	/**
	 * @Title:删除主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByObj(TbBusiHostConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbBusiHostConfig.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiHostConfig.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:插入主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int insertByObj(TbBusiHostConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbBusiHostConfig.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiHostConfig.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询出具体主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public TbBusiHostConfigObj queryByObj(TbBusiHostConfigObj obj) {
		List lst = null;
		TbBusiHostConfigObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbBusiHostConfigObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:根据主机配置部分信息查询匹配的所有主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(TbBusiHostConfigObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbBusiHostConfig.queryByObjForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("TbBusiHostConfig.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbBusiHostConfig.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryForListByHostId
	 * @Description: TODO(通过主机编号查找用户信息)
	 * @param
	 * @return List
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-4-12 下午7:31:05
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByHostId(TbBusiHostConfigObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbBusiHostConfig.queryForListByHostId", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbBusiHostConfig.queryForListByHostId:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: updateHostConfigByUsername
	 * @Description: 更新用户关联基准应用
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-13 下午1:06:44
	 */
	public int updateHostConfigByUsername(TbBusiHostConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbBusiHostConfig.updateHostConfigByUsername", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiHostConfig.updateHostConfigByUsername:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 */
	public int updateByObj(TbBusiHostConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbBusiHostConfig.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiHostConfig.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateHostConfigByAppId
	 * @Description: 根据应用的id修改主机的配置信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 27, 2013 10:41:46 AM
	 */
	public int updateHostConfigByAppId(TbBusiHostConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbBusiHostConfig.updateHostConfigByAppId", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiHostConfig.updateHostConfigByAppId:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForHostConfigAndDepListByObj
	 * @Description: 查询主机下实例的信息
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jul 9, 2013 10:31:25 AM
	 */
	public List queryForHostConfigAndDepListByObj(TbBusiHostConfigObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbBusiHostConfig.queryForHostConfigAndDepForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("TbBusiHostConfig.queryForHostConfigAndDepListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbBusiHostConfig.queryForHostConfigAndDepListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

}
