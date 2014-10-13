package com.sitech.basd.sxcloud.cloud.dao.monitor;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.monitor.HealthyAlarmObj;
import com.sitech.basd.sxcloud.cloud.domain.monitor.MonitorAlarmObj;
import com.sitech.basd.sxcloud.cloud.domain.monitor.MonitorObj;

public interface MonitorDao {
	/**
	 * @Title:查询监控列表
	 * @Copyright: Copyright (c) 2012-01-07
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryPhysicForListByObj(MonitorObj obj);
	
	/**
	 * @Title:查询健康管理中监控告警列表
	 * @Copyright: Copyright (c) 2012-07-27
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryAlarmForListByEntityIds(HealthyAlarmObj obj);

	/**
	 * @Title:查询监控列表并返回一个对象
	 * @Copyright: Copyright (c) 2012-01-07
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public MonitorObj queryPhysicByObj(MonitorObj obj);

	/**
	 * @Title:查询监控列表
	 * @Copyright: Copyright (c) 2012-01-07
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryVirtualForListByObj(MonitorObj obj);

	/**
	 * @Title:查询监控列表并返回一个对象
	 * @Copyright: Copyright (c) 2012-01-07
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public MonitorObj queryVirtualByObj(MonitorObj obj);

	/**
	 * @Title:查询监控列表
	 * @Copyright: Copyright (c) 2012-01-07
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppForListByObj(MonitorObj obj);

	/**
	 * @Title:查询监控列表并返回一个对象
	 * @Copyright: Copyright (c) 2012-01-07
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public MonitorObj queryAppByObj(MonitorObj obj);

	/**
	 * @Title:查询监控告警
	 * @Copyright: Copyright (c) 2012-03-23
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAlarmForListByObj(MonitorAlarmObj obj);
	
	/**
	 * 查询告警列表，首页使用
	 * @Title: queryAlarmForListByObj_index
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-8-6 上午10:22:21
	 */
	public List queryAlarmForListByObj_index(MonitorAlarmObj obj);

	/**
	 * @Title:处理监控告警
	 * @Copyright: Copyright (c) 2012-03-26
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(MonitorAlarmObj obj);

	/**
	 * @Title:删除监控告警信息
	 * @Copyright: Copyright (c) 2012-03-26
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(MonitorAlarmObj obj);

	/**
	 * @Title:插入监控告警历史
	 * @Copyright: Copyright (c) 2012-03-26
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(MonitorAlarmObj obj);

	/**
	 * @Title:查询监控告警历史
	 * @Copyright: Copyright (c) 2012-03-26
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAlarmHisForList(MonitorAlarmObj obj);

}
