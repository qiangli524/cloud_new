package com.sitech.basd.sxcloud.cloud.service.monitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import rabbitmq.QueueDefind;

import alarm.MonitorAlarmBase;

import com.sitech.basd.sxcloud.cloud.dao.monitor.MonitorDao;
import com.sitech.basd.sxcloud.cloud.domain.monitor.HealthyAlarmObj;
import com.sitech.basd.sxcloud.cloud.domain.monitor.MonitorAlarmObj;
import com.sitech.basd.sxcloud.cloud.domain.monitor.MonitorObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.cloud.service.userentity.UserEntityService;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.utils.date.DateUtil;
import com.sitech.utils.exception.RabbitMQException;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.utils.string.StringUtil;

import enumtype.Types.Grade;

@SuppressWarnings({"rawtypes","unchecked"})
public class MonitorServiceImpl extends BaseService implements MonitorService {
	
	@Autowired
	private TbGlobalConfigService tbGlobalConfigService;
	@Autowired
	private UserEntityService userentityService;
	@Autowired
	private RabbitMQUtil rabbitmqUtil;
	
	
	/**
	 * @Title:查询物理主机监控列表
	 * @Copyright: Copyright (c) 2012-01-07
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryPhysicForListByObj(MonitorObj obj) {
		boolean flag = false;
		{
			// 默认从接口获取数据
			// 获取数据成功，则flag设为true
		}
		if (!flag) {
			return monitorDao.queryPhysicForListByObj(obj);
		}
		return monitorDao.queryPhysicForListByObj(obj);
	}

	/**
	 * @Title:查询物理主机监控列表并返回一个对象
	 * @Copyright: Copyright (c) 2012-01-07
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public MonitorObj queryPhysicByObj(MonitorObj obj) {
		boolean flag = false;
		{
			// 默认从接口获取数据
			// 获取数据成功，则flag设为true
		}
		if (!flag) {
			return monitorDao.queryPhysicByObj(obj);
		}
		return monitorDao.queryPhysicByObj(obj);
	}

	/**
	 * @Title:查询物理主机监控列表
	 * @Copyright: Copyright (c) 2012-01-09
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryVirtualForListByObj(MonitorObj obj) {
		boolean flag = false;
		{
			// 默认从接口获取数据
			// 获取数据成功，则flag设为true
		}
		if (!flag) {
			return monitorDao.queryVirtualForListByObj(obj);
		}
		return monitorDao.queryVirtualForListByObj(obj);
	}

	/**
	 * @Title:查询物理主机监控列表并返回一个对象
	 * @Copyright: Copyright (c) 2012-01-09
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public MonitorObj queryVirtualByObj(MonitorObj obj) {
		boolean flag = false;
		{
			// 默认从接口获取数据
			// 获取数据成功，则flag设为true
		}
		if (!flag) {
			return monitorDao.queryVirtualByObj(obj);
		}
		return monitorDao.queryVirtualByObj(obj);
	}

	/**
	 * @Title:查询应用监控列表
	 * @Copyright: Copyright (c) 2012-01-09
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppForListByObj(MonitorObj obj) {
		boolean flag = false;
		{
			// 默认从接口获取数据
			// 获取数据成功，则flag设为true
		}
		if (!flag) {
			return monitorDao.queryAppForListByObj(obj);
		}
		return monitorDao.queryAppForListByObj(obj);
	}

	/**
	 * @Title:查询应用监控列表并返回一个对象
	 * @Copyright: Copyright (c) 2012-01-09
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public MonitorObj queryAppByObj(MonitorObj obj) {
		boolean flag = false;
		{
			// 默认从接口获取数据
			// 获取数据成功，则flag设为true
		}
		if (!flag) {
			return monitorDao.queryAppByObj(obj);
		}
		return monitorDao.queryAppByObj(obj);
	}

	/**
	 * @Title:查询监控告警
	 * @Copyright: Copyright (c) 2012-03-23
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAlarmForListByObj(MonitorAlarmObj obj) {
		boolean flag = false;
		{
			// 默认从接口获取数据
			// 获取数据成功，则flag设为true
		}
		if (!flag) {
			return monitorDao.queryAlarmForListByObj(obj);
		}
		return monitorDao.queryAlarmForListByObj(obj);
	}
	
	public List queryAlarmForListByObj_index(MonitorAlarmObj obj, String loginName, String userid) {
		
		List alarms = monitorDao.queryAlarmForListByObj_index(obj);
		List result = new ArrayList();
		//查看特殊权限user_auth配置
		boolean flag = tbGlobalConfigService.checkUserContains(loginName);
		if (!flag){
			//告警分权处理
			List entities = userentityService.getUserAllEntitys(userid);
			for (Iterator iterator = alarms.iterator(); iterator.hasNext();) {
				MonitorAlarmObj alarm = (MonitorAlarmObj) iterator.next();
				for (Iterator iterator2 = entities.iterator(); iterator2.hasNext();) {
					String entityId = (String) iterator2.next();
					if (entityId!=null && entityId.equals(alarm.getENTITY_ID())){
						result.add(alarm);
					}
				}
			}
			return result;
		}
		
		return alarms;
	}
	
	/**
	 * @Title:查询健康管理中监控告警
	 * @Copyright: Copyright (c) 2012-07-27
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryAlarmForListByEntityIds(HealthyAlarmObj obj) {
		return monitorDao.queryAlarmForListByEntityIds(obj);
	}

	/**
	 * @Title:处理监控告警
	 * @Copyright: Copyright (c) 2012-03-26
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(MonitorAlarmObj obj) {
		boolean flag = false;
		{
			// 默认从接口获取数据
			// 获取数据成功，则flag设为true
		}
		if (!flag) {
			return monitorDao.updateByObj(obj);
		}
		return monitorDao.updateByObj(obj);
	}

	/**
	 * @Title:删除监控告警信息
	 * @Copyright: Copyright (c) 2012-03-26
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(MonitorAlarmObj obj) {
		boolean flag = false;
		{
			// 默认从接口获取数据
			// 获取数据成功，则flag设为true
		}
		if (!flag) {
			return monitorDao.deleteByObj(obj);
		}
		return monitorDao.deleteByObj(obj);
	}

	/**
	 * @Title:插入监控告警历史
	 * @Copyright: Copyright (c) 2012-03-26
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(MonitorAlarmObj obj) {
		boolean flag = false;
		{
			// 默认从接口获取数据
			// 获取数据成功，则flag设为true
		}
		if (!flag) {
			return monitorDao.insertByObj(obj);
		}
		return monitorDao.insertByObj(obj);
	}

	/**
	 * @Title:查询应用监控列表
	 * @Copyright: Copyright (c) 2012-03-26
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAlarmHisForList(MonitorAlarmObj obj) {
		boolean flag = false;
		{
			// 默认从接口获取数据
			// 获取数据成功，则flag设为true
		}
		if (!flag) {
			return monitorDao.queryAlarmHisForList(obj);
		}
		return monitorDao.queryAlarmHisForList(obj);
	}
	
	@Override
	public void publishOKToMQ(MonitorAlarmObj tempObj) throws RabbitMQException {
		MonitorAlarmBase base = convertMonitorAlarmBase(tempObj);
		try {
			rabbitmqUtil.publishMessage(QueueDefind.ALARM_DOMAIN_EXCHANGE,"",base);
		} catch (RabbitMQException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * @Title: convertMonitorAlarmBase
	 * @Description: 将MonitorAlarmObj转换为mq需要的MonitorAlarmBase
	 * @param obj
	 * @return
	 * @return MonitorAlarmBase
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @createtime 2014-8-26 下午5:43:24
	 */
	private MonitorAlarmBase convertMonitorAlarmBase(MonitorAlarmObj obj){
		if (null == obj) {
			return null;
		}
		MonitorAlarmBase base = new MonitorAlarmBase();
		base.setEntityId(obj.getENTITY_ID());
		base.setContent(obj.getCONTENT());
		base.setGrade(Grade.OK);
		base.setAlarmTime(DateUtil.getCurrentDate());
		return base;
	}

	private MonitorDao monitorDao;

	public void setMonitorDao(MonitorDao monitorDao) {
		this.monitorDao = monitorDao;
	}


}
