package com.sitech.ssd.ah.boss.dao.procedure;

import java.sql.SQLException;
import java.util.List;

import jxl.common.Logger;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.ah.boss.domain.procedure.ProcedureObj;
import com.sitech.utils.randomid.RandomUUID;

/**
 * <p>
 * Title: BossProcedureDaoImpl
 * </p>
 * <p>
 * Description: boss应用进程DAO实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-8-1 下午4:25:48
 * 
 */
@SuppressWarnings("unchecked")
@Repository("bossProcedureDao")
public class BossProcedureDaoImpl extends BaseDao implements BossProcedureDao {
	private static final Logger logger = Logger.getLogger(BossProcedureDaoImpl.class);

	@Override
	public List queryBossProcedure(ProcedureObj obj) {
		List<ProcedureObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"BossProcedure.queryBossProcedureCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("BossProcedure.queryBossProcedureList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossProcedure.queryBossProcedureList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public int insertByObj(ProcedureObj obj) {
		int ret = 0;
		String uid = RandomUUID.getUuid();
		try {
			obj.setUid(uid);
			getSqlMap().insert("BossProcedure.insertByObj", obj);
		} catch (SQLException e) {
			ret = -1;
			logger.error("BossProcedure.insertByObj: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	@Override
	public int queryForIpAndAppCount(ProcedureObj obj) {
		int ret = 0;
		try {
			ret = (Integer) getSqlMap().queryForObject("BossProcedure.queryForIpAndAppCount", obj);
		} catch (SQLException e) {
			ret = -1;
			logger.error("BossProcedure.queryForIpAndAppCount: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	@Override
	public ProcedureObj queryProcedureByUid(ProcedureObj obj) {
		ProcedureObj ob = new ProcedureObj();
		try {
			ob = (ProcedureObj) getSqlMap()
					.queryForObject("BossProcedure.queryProcedureByUid", obj);
		} catch (SQLException e) {
			logger.error("BossProcedure.queryProcedureByUid: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ob;
	}

	@Override
	public int updateProcedureObj(ProcedureObj obj) {
		int ret = 0;
		try {
			logger.info("开始执行修改操作！！");
			Object ob = getSqlMap().update("BossProcedure.updateByObj", obj);
			logger.info("执行修改完成！");
		} catch (Exception e) {
			ret = -1;
			LogHelper
					.error("BossProcedure.updateByObj: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	@Override
	public int unloadProcedureObj(ProcedureObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().update("BossProcedure.unloadProcedureObj", obj);
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("BossProcedure.unloadProcedureObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	@Override
	public List queryProcedureListByCluAndPool(ProcedureObj obj) {
		List<ProcedureObj> lst = null;
		try {
			lst = getSqlMap().queryForList("BossProcedure.queryProcedureListByCluAndPool", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossProcedure.queryProcedureListByCluAndPool:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
}
