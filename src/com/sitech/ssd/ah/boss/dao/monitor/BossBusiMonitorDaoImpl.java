package com.sitech.ssd.ah.boss.dao.monitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.ah.boss.domain.monitor.BossBusiMonitorObj;
import com.sitech.ssd.ah.busiMonitor.domain.MonitorCfgObj;

/**
 * <p>
 * Title: BossBusiMonitorDaoImpl
 * </p>
 * <p>
 * Description: Boss业务监控DAO实现类
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
 * @createtime 2014-9-2 上午10:03:14
 * 
 */
@SuppressWarnings("unchecked")
@Repository("bossBusiMonitorDao")
public class BossBusiMonitorDaoImpl extends BaseDao implements BossBusiMonitorDao {
	/**
	 * 端口 当前数据
	 */
	@Override
	public List queryListForPort(BossBusiMonitorObj obj) {
		List<BossBusiMonitorObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"BossBusiMonitor.queryListForPortForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("BossBusiMonitor.queryListForPort", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonitor.queryListForPort:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public List queryListForPortProcess(BossBusiMonitorObj obj) {
		List<BossBusiMonitorObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination()
						.setTotalCount(
								((Integer) getSqlMap().queryForObject(
										"BossBusiMonitor.queryListForPortProcessForCount", obj))
										.intValue());
			}
			lst = getSqlMap().queryForList("BossBusiMonitor.queryListForPortProcess", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonitor.queryListForProcess:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public List<Data> queryChartJSONOfPort(BossBusiMonitorObj obj) {
		List<Data> lst = null;
		try {
			lst = getSqlMap().queryForList("BossBusiMonitor.queryDataChartForPort", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonitor.queryDataChartForPort:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 端口 历史数据
	 */
	@Override
	public List queryHosiListForPort(BossBusiMonitorObj obj) {
		List<BossBusiMonitorObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"BossBusiMonitor.queryHosiListForPortForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("BossBusiMonitor.queryHosiListForPort", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonitor.queryHosiListForPort:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public List queryHosiListForPortDesc(BossBusiMonitorObj obj) {
		List<BossBusiMonitorObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"BossBusiMonitor.queryHosiListForPortDescForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("BossBusiMonitor.queryHosiListForPortDesc", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonitor.queryHosiListForPortDesc:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public List<Data> queryHosiChartJSONForPool(BossBusiMonitorObj obj) {
		List<Data> lst = null;
		try {
			lst = getSqlMap().queryForList("BossBusiMonitor.queryHosiDataChartForPort", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonitor.queryHosiDataChartForPort:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	public List<Data> queryChartJSONForProcess(BossBusiMonitorObj obj) {
		List<Data> lst = null;
		try {
			lst = getSqlMap().queryForList("BossBusiMonitor.queryDataChartForProcess", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonitor.queryDataChartForProcess:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public List queryListForErrorLog(BossBusiMonitorObj obj) {
		List<BossBusiMonitorObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"BossBusiMonitor.queryListForLogForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("BossBusiMonitor.queryListForLog", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonitor.queryListForLog:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public List<Data> queryHosiChartJSONForLog(BossBusiMonitorObj obj) {
		List<Data> lst = null;
		try {
			lst = getSqlMap().queryForList("BossBusiMonitor.queryDataChartForLog", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonitor.queryDataChartForLog:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public List queryForErrorLogDesc(BossBusiMonitorObj obj) {
		List<BossBusiMonitorObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"BossBusiMonitor.queryForErrorLogDescForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("BossBusiMonitor.queryForErrorLogDesc", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonitor.queryForErrorLogDesc:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public List queryListForMonitorDir(MonitorCfgObj obj) {
		List<MonitorCfgObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"BossBusiMonitor.queryListForMonitorDirForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("BossBusiMonitor.queryListForMonitorDir", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonitor.queryListForMonitorDir:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public List queryListForMonitorDirDesc(BossBusiMonitorObj obj) {
		List<BossBusiMonitorObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"BossBusiMonitor.queryListForMonitorDirDescForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("BossBusiMonitor.queryListForMonitorDirDesc", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonitor.queryListForMonitorDirDesc:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	@Override
	public List<Data> queryChartJSONForDirDesc(BossBusiMonitorObj obj) {
		List<Data> lst = null;
		try {
			lst = getSqlMap().queryForList("BossBusiMonitor.queryChartJSONForDirDesc", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonitor.queryChartJSONForDirDesc:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public Map queryNctRatio(BossBusiMonitorObj obj) {
		Map map = new HashMap();
		try {
			List<Map> lst = getSqlMap().queryForList("BossBusiMonitor.queryNctRatio", obj);
			if (lst != null && lst.size() > 0) {
				Double onLineSum = 0d, offlineSum = 0d;
				try {
					for (Map m : lst) {
						onLineSum = "1".equals(m.get("online_flag")) ? (Double) m.get("sumIncount")
								: onLineSum;
						offlineSum = "0".equals(m.get("online_flag")) ? (Double) m
								.get("sumIncount") : offlineSum;
					}
					map.put("onLineSum", onLineSum);
					map.put("offLineSum", offlineSum);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonitor.queryNctRatio:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return map;
	}

	@Override
	public Map queryHosiNctRatio(BossBusiMonitorObj obj) {
		Map map = new HashMap();
		try {
			List<Map> lst = getSqlMap().queryForList("BossBusiMonitor.queryHosiNctRatio", obj);
			if (lst != null && lst.size() > 0) {
				Double onLineSum = 0d, offlineSum = 0d;
				try {
					for (Map m : lst) {
						onLineSum = "1".equals(m.get("online_flag")) ? (Double) m.get("sumIncount")
								: onLineSum;
						offlineSum = "0".equals(m.get("online_flag")) ? (Double) m
								.get("sumIncount") : offlineSum;
					}
					map.put("onLineSum", onLineSum);
					map.put("offLineSum", offlineSum);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonitor.queryNctRatio:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return map;
	}

}
