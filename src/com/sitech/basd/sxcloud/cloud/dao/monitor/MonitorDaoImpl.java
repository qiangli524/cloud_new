package com.sitech.basd.sxcloud.cloud.dao.monitor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sitech.basd.sxcloud.cloud.domain.monitor.HealthyAlarmObj;
import com.sitech.basd.sxcloud.cloud.domain.monitor.MonitorAlarmObj;
import com.sitech.basd.sxcloud.cloud.domain.monitor.MonitorObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class MonitorDaoImpl extends BaseDao implements MonitorDao {
	
	/**
	 * @Title:查询物理监控列表
	 * @Copyright: Copyright (c) 2012-01-07
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryPhysicForListByObj(MonitorObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"MonitorObj.queryPhysicByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList(
					"MonitorDao.queryPhysicForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("MonitorDao.queryPhysicForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询物理监控列表并返回一个对象
	 * @Copyright: Copyright (c) 2012-01-07
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public MonitorObj queryPhysicByObj(MonitorObj obj) {
		List lst = null;
		MonitorObj tempObj = null;
		lst = queryPhysicForListByObj(tempObj);
		if (lst != null && lst.size() > 0) {
			tempObj = (MonitorObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:查询虚拟监控列表
	 * @Copyright: Copyright (c) 2012-01-07
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryVirtualForListByObj(MonitorObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"MonitorObj.queryVirtualByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList(
					"MonitorDao.queryVirtualForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("MonitorDao.queryVirtualForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询虚拟监控列表并返回一个对象
	 * @Copyright: Copyright (c) 2012-01-07
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public MonitorObj queryVirtualByObj(MonitorObj obj) {
		List lst = null;
		MonitorObj tempObj = null;
		lst = queryVirtualForListByObj(tempObj);
		if (lst != null && lst.size() > 0) {
			tempObj = (MonitorObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:查询应用监控列表
	 * @Copyright: Copyright (c) 2012-01-07
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryAppForListByObj(MonitorObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"MonitorObj.queryAppByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("MonitorDao.queryAppForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("MonitorDao.queryAppForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询监控列表并返回一个对象
	 * @Copyright: Copyright (c) 2012-01-07
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public MonitorObj queryAppByObj(MonitorObj obj) {
		List lst = null;
		MonitorObj tempObj = null;
		lst = queryAppForListByObj(tempObj);
		if (lst != null && lst.size() > 0) {
			tempObj = (MonitorObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:查询监控告警列表
	 * @Copyright: Copyright (c) 2012-03-23
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryAlarmForListByObj(MonitorAlarmObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"MonitorDao.queryAlarmByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("MonitorDao.queryAlarmForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("MonitorDao.queryAlarmForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	
	public List queryAlarmForListByObj_index(MonitorAlarmObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("MonitorDao.queryAlarmForListByObj_index",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("MonitorDao.queryAlarmForListByObj_index:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	
	/**
	 * @Title:查询健康管理中监控告警列表
	 * @Copyright: Copyright (c) 2012-07-27
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryAlarmForListByEntityIds(HealthyAlarmObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"MonitorDao.queryAlarmForCountByEntityIds", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("MonitorDao.queryAlarmForListByEntityIds",
					obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("MonitorDao.queryAlarmForListByEntityIds:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:处理监控告警列表
	 * @Copyright: Copyright (c) 2012-03-26
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(MonitorAlarmObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("MonitorDao.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("MonitorDao.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除监控告警信息
	 * @Copyright: Copyright (c) 2012-03-26
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(MonitorAlarmObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("MonitorDao.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("MonitorDao.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:插入监控告警历史
	 * @Copyright: Copyright (c) 2012-03-26
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(MonitorAlarmObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("MonitorDao.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("MonitorDao.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询监控告警历史列表
	 * @Copyright: Copyright (c)2012-03-26
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryAlarmHisForList(MonitorAlarmObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"MonitorDao.queryAlarmHisForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("MonitorDao.queryAlarmHisForList",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("MonitorDao.queryAlarmHisForList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

}
