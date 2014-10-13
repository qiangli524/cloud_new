package com.sitech.basd.cloud3.dao.sysappconfig;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.cloud3.domain.sysappconfig.SysAppConfigObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("sysAppConfigDao")
public class SysAppConfigDaoImpl extends BaseDao implements SysAppConfigDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SysAppConfigObj> query4List(SysAppConfigObj sysAppConfigObj) {

		List<SysAppConfigObj> list = null;
		try {
			if (sysAppConfigObj.getPagination() != null) {
				sysAppConfigObj.setFIRSTROWNUM(sysAppConfigObj.getPagination().getFirstRownum());
				sysAppConfigObj.setPAGESIZE(sysAppConfigObj.getPagination().getPageSize());
				sysAppConfigObj.getPagination().setTotalCount(
						((Integer)getSqlMap().queryForObject("SysAppConfig.queryTotalCount", sysAppConfigObj)).intValue()
						);
			}
			list = getSqlMap().queryForList("SysAppConfig.query4List", sysAppConfigObj);
		} catch (Exception e) {
			LogHelper.error("SysAppConfig.query4List: " +e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	@Override
	public SysAppConfigObj queryOne(SysAppConfigObj sysAppConfigObj) {
		SysAppConfigObj obj = null;
		try {
			obj = (SysAppConfigObj) getSqlMap().queryForObject("SysAppConfig.queryOne", sysAppConfigObj);
		} catch (Exception e) {
			LogHelper.error("SysAppConfig.queryOne: " + e.getMessage() + e.getClass().getName());
		}
		return obj;
	}

	@Override
	public int insertSysAppConfigObj(SysAppConfigObj sysAppConfigObj) {
		int ret = 0;
		try {
			Object obj = getSqlMap().insert("SysAppConfig.insertSysAppConfigObj", sysAppConfigObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("SysAppConfig.insertSysAppConfigObj: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	@Override
	public int deleteSysAppConfigObj(SysAppConfigObj sysAppConfigObj) {
		int ret = 0;
		try {
			Object obj = getSqlMap().delete("SysAppConfig.deleteSysAppConfigObj", sysAppConfigObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("SysAppConfig.deleteSysAppConfigObj: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	@Override
	public int updateSysAppConfigObj(SysAppConfigObj sysAppConfigObj) {
		int ret = 0;
		try {
			Object obj = getSqlMap().update("SysAppConfig.updateSysAppConfigObj", sysAppConfigObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("SysAppConfig.updateSysAppConfigObj: " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

}
