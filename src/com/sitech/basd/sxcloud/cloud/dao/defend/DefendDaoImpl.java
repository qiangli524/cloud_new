package com.sitech.basd.sxcloud.cloud.dao.defend;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.defend.DefendObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class DefendDaoImpl extends BaseDao implements DefendDao {
	/**
	 * @Title:查询出具体防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public DefendObj queryByObj(DefendObj obj) {
		List lst = null;
		DefendObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (DefendObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:根据防篡改部分信息查询匹配的所有防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(DefendObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"DefendInfo.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("DefendInfo.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DefendInfo.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:添加防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int insertByObj(DefendObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("DefendInfo.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("DefendInfo.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更新防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int updateByObj(DefendObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("DefendInfo.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("DefendInfo.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int deleteByObj(DefendObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("DefendInfo.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("DefendInfo.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询主机名称与主机ID作为页面的下拉列表显示
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings( { "unchecked", "unchecked" })
	public List queryForListByTbBusiHostObj(TbBusiHostObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList(
					"DefendInfo.queryForListByTbBusiHostObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DefendInfo.queryForListByTbBusiHostObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:添加防篡改信息历史
	 * @Copyright: Copyright (c) 201203
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertHisByObj(DefendObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("DefendInfo.insertHisByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("DefendInfo.insertHisByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询防篡改历史
	 * @Copyright: Copyright (c) 201203
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryHisListByObj(DefendObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination()
						.setTotalCount(
								((Integer) getSqlMap().queryForObject(
										"DefendInfo.queryHisForCount", obj))
										.intValue());
			}
			lst = getSqlMap().queryForList("DefendInfo.queryHisListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DefendInfo.queryHisListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询可以添加防篡改的IP地址
	 * @Copyright: Copyright (c) 201204
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryDefendIp(DefendObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("DefendInfo.queryDefendIp", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DefendInfo.queryDefendIp:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
}
