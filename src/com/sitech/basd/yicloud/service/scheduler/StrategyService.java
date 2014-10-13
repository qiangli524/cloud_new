package com.sitech.basd.yicloud.service.scheduler;

import java.util.List;

import com.sitech.basd.yicloud.domain.scheduler.KPIWeightObj;
import com.sitech.basd.yicloud.domain.scheduler.StaskObj;
import com.sitech.basd.yicloud.domain.scheduler.StrategyObj;
import com.sitech.basd.yicloud.domain.scheduler.TriggerRelationObj;

public interface StrategyService {
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
	public List<StrategyObj> listStrategy(StrategyObj obj);

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
	public int insertByObj(StrategyObj obj);

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
	public int updateByObj(StrategyObj obj);

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
	public int deleteByObj(StrategyObj obj);

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
	public List listKPIWeights(KPIWeightObj obj);

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
	public int insertTriggerRelation(TriggerRelationObj obj);

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
	public int deleteRelation(TriggerRelationObj obj);

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
	public int updateRelation(TriggerRelationObj obj);

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
	public int insertTaskObj(StaskObj obj);

	/**
	 * 
	 * @Title: 插入策略扩展表
	 * @Copyright:Copyright (c) Aug 29, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int insertStrategyExpand(StrategyObj obj);

	/**
	 * 
	 * @Title: 更新策略扩展表
	 * @Copyright:Copyright (c) Aug 29, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int updateStrategyExpand(StrategyObj obj);

	/**
	 * 
	 * @Title: 删除策略扩展表
	 * @Copyright:Copyright (c) Aug 29, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int deleteStrategyExpand(StrategyObj obj);

}
