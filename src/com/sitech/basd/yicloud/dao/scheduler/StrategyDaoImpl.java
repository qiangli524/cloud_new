package com.sitech.basd.yicloud.dao.scheduler;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.scheduler.KPIWeightObj;
import com.sitech.basd.yicloud.domain.scheduler.StaskObj;
import com.sitech.basd.yicloud.domain.scheduler.StrategyObj;
import com.sitech.basd.yicloud.domain.scheduler.TriggerRelationObj;

public class StrategyDaoImpl extends BaseDao implements StrategyDao {
	/**
	 * 
	 * @Title: listStrategy
	 * @Description: 查询调度策略列表
	 * @param
	 * @return List
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public List<StrategyObj> listStrategy(StrategyObj obj) {
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("strategy.queryForCount", obj))
								.intValue());
			}
			list = getSqlMap().queryForList("strategy.queryList", obj);
			TriggerRelationObj trObj = new TriggerRelationObj();
			for (Object o : list) {
				StrategyObj tempObj = (StrategyObj) o;
				int interval = Integer.parseInt(tempObj.getInterval());
				int tigger_n = Integer.parseInt(tempObj.getTigger_n());
				String operator = tempObj.getOperator();
				if (!operator.equals("in")) {
					tempObj.setContent(interval * tigger_n + "分钟内采集" + tempObj.getTigger_n()
							+ "次,触发条件：" + tempObj.getKpi_id() + tempObj.getOperator()
							+ tempObj.getThreshold() + "达到" + tempObj.getTrigger_m() + "次");
				} else {
					tempObj.setContent(interval * tigger_n + "分钟内采集" + tempObj.getTigger_n()
							+ "次,触发条件：" + tempObj.getKpi_id() + "大于等于" + tempObj.getMin() + "且小于等于"
							+ tempObj.getMax() + "达到" + tempObj.getTrigger_m() + "次");
				}
				// trObj.setScheduler_id(tempObj.getStrategy_id());
				// List tempList =
				// getSqlMap().queryForList("strategy.strategykpi", trObj);
				// if (tempList != null && tempList.size() > 0) {
				// trObj = (TriggerRelationObj) tempList.get(0);
				// }
				// tempObj.setContent(interval * tigger_n + "分钟内采集" +
				// tempObj.getTigger_n()
				// + "次,触发条件：" + tempObj.getKpi_desc() + tempObj.getOperator()
				// + tempObj.getThreshold());
				// tempObj.setKpi_id(trObj.getKpi_id());
			}

		} catch (Exception e) {
			LogHelper.error("strategy.queryList:" + e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 新增调度策略
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public int insertByObj(StrategyObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("strategy.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("strategy.insertByObj:" + e.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 修改调度策略
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public int updateByObj(StrategyObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("strategy.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("strategy.updateByObj:" + e.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 修改调度策略
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public int deleteByObj(StrategyObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("strategy.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("strategy.deleteByObj:" + e.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: listKPIWeights
	 * @Description: 查询所有的kpi
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public List listKPIWeights(KPIWeightObj obj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("strategy.querykpis", obj);
		} catch (Exception e) {
			LogHelper.error("strategy.querykpis:" + e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: insertTriggerRelation
	 * @Description: 插入策略和触发条件关系表信息
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public int insertTriggerRelation(TriggerRelationObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("strategy.insertTriggerRelation", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("strategy.insertTriggerRelation:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteRelation
	 * @Description: 删除策略和触发条件关系表信息
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public int deleteRelation(TriggerRelationObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("strategy.deleteRelation", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("strategy.deleteRelation:" + e.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateRelation
	 * @Description: 修改策略和触发条件关系表信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Dec 19, 2012 3:37:36 PM
	 */
	public int updateRelation(TriggerRelationObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("strategy.updateTriggerRelation", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("strategy.updateTriggerRelation:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: insertTaskObj
	 * @Description: 迁移虚拟机
	 * @param
	 * @return int
	 * @throws
	 * @author xumq
	 * @version 1.0
	 * @createtime 2013-3-20 3:37:36 PM
	 */
	public int insertTaskObj(StaskObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("strategy.insertTaskObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("strategy.insertTaskObj:" + e.getMessage() + getClass().getName());
		}
		return ret;
	}

	@Override
	public int insertStrategyExpand(StrategyObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("strategy.insertStrategyExpand", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("strategy.insertStrategyExpand:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	@Override
	public int deleteStrategyExpand(StrategyObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("strategy.deleteStrategyExpand", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("strategy.deleteStrategyExpand:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	@Override
	public int updateStrategyExpand(StrategyObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("strategy.updateStrategyExpand", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("strategy.updateStrategyExpand:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
}
