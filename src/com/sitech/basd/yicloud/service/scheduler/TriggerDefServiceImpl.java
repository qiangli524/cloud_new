package com.sitech.basd.yicloud.service.scheduler;

import java.util.List;

import com.sitech.basd.yicloud.dao.scheduler.TriggerDefDao;
import com.sitech.basd.yicloud.domain.scheduler.DefinitionObj;

public class TriggerDefServiceImpl implements TriggerDefService {
	private TriggerDefDao triggerDefDao;

	public void setTriggerDefDao(TriggerDefDao triggerDefDao) {
		this.triggerDefDao = triggerDefDao;
	}

	/**
	 * 
	 * @Title: listTriggerDefinition
	 * @Description: 查询所有的触发器条件信息
	 * @param
	 * @return List
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public List listTriggerDefinition(DefinitionObj obj) {
		return triggerDefDao.listTriggerDefinition(obj);
	}

	/**
	 * 
	 * @Title: insertTriggerDefinition
	 * @Description: 插入触发器条件信息
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public int insertTriggerDefinition(DefinitionObj obj) {
		return triggerDefDao.insertTriggerDefinition(obj);
	}

	/**
	 * 
	 * @Title: updateTriggerDefinition
	 * @Description: 修改触发器条件信息
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public int updateTriggerDefinition(DefinitionObj obj) {
		return triggerDefDao.updateTriggerDefinition(obj);
	}

	/**
	 * 
	 * @Title: delTriggerDefinition
	 * @Description: 删除触发器条件信息
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public int delTriggerDefinition(DefinitionObj obj) {
		return triggerDefDao.delTriggerDefinition(obj);
	}
}
