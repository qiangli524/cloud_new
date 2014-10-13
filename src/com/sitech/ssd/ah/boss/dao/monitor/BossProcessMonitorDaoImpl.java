package com.sitech.ssd.ah.boss.dao.monitor;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.ah.boss.domain.common.CommonObj;
import com.sitech.ssd.ah.boss.domain.monitor.BossMonitorObj;

/**
 * <p>
 * Title: BossProcessMonitorDaoImpl
 * </p>
 * <p>
 * Description: boss进程监控Dao实现类
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
 * @createtime 2014-8-4 下午4:50:53
 * 
 */
@SuppressWarnings("unchecked")
@Repository("bossProcessMonitorDao")
public class BossProcessMonitorDaoImpl extends BaseDao implements BossProcessMonitorDao {

	@Override
	public List<BossMonitorObj> queryMonitorObjList(BossMonitorObj obj) {
		List<BossMonitorObj> lst = null;
		try {
			lst = getSqlMap().queryForList("BossMonitor.queryMonitorObjList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossMonitor.queryMonitorObjList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public void updateMonitorObj(BossMonitorObj obj) {
		try {
			Object ob = getSqlMap().update("BossMonitor.updateByObj", obj);
		} catch (Exception e) {
			LogHelper.error("BossMonitor.updateByObj: " + e.getMessage() + e.getClass().getName());
		}
	}

	@Override
	public BossMonitorObj queryMonitorObj(BossMonitorObj obj) {
		BossMonitorObj ob = new BossMonitorObj();
		try {
			ob = (BossMonitorObj) getSqlMap().queryForObject("BossMonitor.queryByObj", obj);
		} catch (Exception e) {
			LogHelper.error("BossMonitor.queryByObj: " + e.getMessage() + e.getClass().getName());
		}
		return ob;
	}

	@Override
	public List<CommonObj> queryCommonObjList(CommonObj obj) {
		List<CommonObj> lst = null;
		try {
			lst = getSqlMap().queryForList("BossMonitor.queryCommonObjList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossMonitor.queryCommonObjList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public List<BossMonitorObj> queryMonitorObjByClu(BossMonitorObj obj) {
		List<BossMonitorObj> lst = null;
		try {
			lst = getSqlMap().queryForList("BossMonitor.queryMonitorObjByClu", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossMonitor.queryMonitorObjByClu:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public List queryMonitorObjByPoolAndClu(BossMonitorObj obj) {
		List<BossMonitorObj> lst = null;
		try {
			lst = getSqlMap().queryForList("BossMonitor.queryMonitorObjByPoolAndClu", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossMonitor.queryMonitorObjByPoolAndClu:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public List<BossMonitorObj> queryMonitorObjListf(BossMonitorObj obj) {
		List<BossMonitorObj> lst = null;
		try {
			lst = getSqlMap().queryForList("BossMonitor.queryMonitorObjListf", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossMonitor.queryMonitorObjListf:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}


}
