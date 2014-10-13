package com.sitech.basd.sxcloud.rsmu.dao.upgrade;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.upgrade.UpgradeAlarmObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class UpgradeAlarmDaoImpl extends BaseDao implements UpgradeAlarmDao {

	public List queryForListByObj(UpgradeAlarmObj obj) {
		// TODO Auto-generated method stub
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"UpgradeAlarm.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("UpgradeAlarm.queryForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("UpgradeAlarm.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

}
