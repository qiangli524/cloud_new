package com.sitech.basd.cloud3.dao.monitor;

import java.util.List;

import com.sitech.basd.cloud3.domain.monitor.InterfaceDetailObj;
import com.sitech.basd.cloud3.domain.monitor.InterfaceInfoObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class InterDaoImpl extends BaseDao implements InterDao {
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询接口执行的详细信息
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jan 9, 2013 4:35:26 PM
	 */
	public List queryForListByObj(InterfaceDetailObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"InterDetail.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap()
					.queryForList("InterDetail.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("InterDetail.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 显示几种接口相关信息
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jan 9, 2013 7:02:48 PM
	 */
	public List queryForList(InterfaceInfoObj obj) {
		List lst = null;
		try {

			lst = getSqlMap().queryForList("InterDetail.queryForList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("InterDetail.queryForList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
}
