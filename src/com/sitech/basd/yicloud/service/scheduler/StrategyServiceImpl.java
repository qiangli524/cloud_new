package com.sitech.basd.yicloud.service.scheduler;

import java.util.List;

import com.sitech.basd.yicloud.dao.scheduler.StrategyDao;
import com.sitech.basd.yicloud.domain.scheduler.KPIWeightObj;
import com.sitech.basd.yicloud.domain.scheduler.StaskObj;
import com.sitech.basd.yicloud.domain.scheduler.StrategyObj;
import com.sitech.basd.yicloud.domain.scheduler.TriggerRelationObj;

public class StrategyServiceImpl implements StrategyService {
	private StrategyDao strategyDao;

	public void setStrategyDao(StrategyDao strategyDao) {
		this.strategyDao = strategyDao;
	}

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
		return strategyDao.listStrategy(obj);
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
		return strategyDao.insertByObj(obj);
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
		return strategyDao.updateByObj(obj);
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
		return strategyDao.deleteByObj(obj);
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
		return strategyDao.listKPIWeights(obj);
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
		return strategyDao.insertTriggerRelation(obj);
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
		return strategyDao.deleteRelation(obj);
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
		return strategyDao.updateRelation(obj);
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
		// TODO Auto-generated method stub
		return strategyDao.insertTaskObj(obj);
	}

	@Override
	public int insertStrategyExpand(StrategyObj obj) {
		// TODO Auto-generated method stub
		return strategyDao.insertStrategyExpand(obj);
	}

	@Override
	public int deleteStrategyExpand(StrategyObj obj) {
		// TODO Auto-generated method stub
		return strategyDao.deleteStrategyExpand(obj);
	}

	@Override
	public int updateStrategyExpand(StrategyObj obj) {
		// TODO Auto-generated method stub
		return strategyDao.updateStrategyExpand(obj);
	}
}
