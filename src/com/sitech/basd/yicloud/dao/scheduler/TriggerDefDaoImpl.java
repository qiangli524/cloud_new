package com.sitech.basd.yicloud.dao.scheduler;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.scheduler.DefinitionObj;

public class TriggerDefDaoImpl extends BaseDao implements TriggerDefDao {
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
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Trigger.queryForCount", obj)).intValue());
			}
			list = getSqlMap().queryForList("Trigger.queryForList", obj);
		} catch (Exception e) {
			LogHelper.error("Trigger.queryForList:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
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
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Trigger.insertTriggerDefinition",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Trigger.insertTriggerDefinition:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
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
		int ret = 0;
		try {
			Object o = getSqlMap().update("Trigger.updateTriggerDefinition",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Trigger.updateTriggerDefinition:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
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
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Trigger.delTriggerDefinition", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Trigger.delTriggerDefinition:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
}
