package com.sitech.basd.sxcloud.rsmu.dao.deploy;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployTimeConfigObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * 
 * <p>
 * Title: TbBusiDeployTimeConfigDaoImpl
 * </p>
 * <p>
 * Description: 后台定时调度配置
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-6-28 下午7:33:25
 * 
 */
@Repository("TbBusiDeployTimeConfigDao")
public class TbBusiDeployTimeConfigDaoImpl extends BaseDao implements
		TbBusiDeployTimeConfigDao {

	@SuppressWarnings("unchecked")
	public List queryForListByObj(TbBusiDeployTimeConfigObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination()
						.setTotalCount(
								((Integer) getSqlMap()
										.queryForObject(
												"TbBusiDeployTimeConfig.queryForListByObjForCount",
												obj)).intValue());
			}
			lst = getSqlMap().queryForList(
					"TbBusiDeployTimeConfig.queryForListByObj", obj);
		} catch (SQLException sqlexception) {
			LogHelper.error("TbBusiDeployTimeConfig.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	public int updateByObj(TbBusiDeployTimeConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbBusiDeployTimeConfig.updateByObj",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiDeployTimeConfig.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public static void main(String[] args) {
		TbBusiDeployTimeConfigDaoImpl dao = new TbBusiDeployTimeConfigDaoImpl();
		List list = dao.queryForListByObj(new TbBusiDeployTimeConfigObj());
	}

	public int updateByObj2(TbBusiDeployTimeConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update(
					"TbBusiDeployTimeConfig.updateByObj2", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiDeployTimeConfig.updateByObj2:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public int updateByObj3(TbBusiDeployTimeConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update(
					"TbBusiDeployTimeConfig.updateByObj3", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiDeployTimeConfig.updateByObj3:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
}
