package com.sitech.basd.sxcloud.cloud.dao.eventmanage;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.eventmanage.EventManageObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class EventManageDaoImpl extends BaseDao implements EventManageDao {
	/**
	 * @Title:查询出具体事件信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public EventManageObj queryByObj(EventManageObj obj) {
		List lst = null;
		EventManageObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (EventManageObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:查询匹配的所有事件信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(EventManageObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"EventManageInfo.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("EventManageInfo.queryForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("EventManageInfo.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
}
