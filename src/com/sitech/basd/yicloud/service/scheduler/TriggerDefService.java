package com.sitech.basd.yicloud.service.scheduler;

import java.util.List;

import com.sitech.basd.yicloud.domain.scheduler.DefinitionObj;

public interface TriggerDefService {

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
	public List listTriggerDefinition(DefinitionObj obj);

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
	public int insertTriggerDefinition(DefinitionObj obj);

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
	public int updateTriggerDefinition(DefinitionObj obj);

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
	public int delTriggerDefinition(DefinitionObj obj);

}
