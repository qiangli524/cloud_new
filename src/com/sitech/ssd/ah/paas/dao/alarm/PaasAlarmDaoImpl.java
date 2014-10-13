package com.sitech.ssd.ah.paas.dao.alarm;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.ah.paas.domain.alarm.PaasAlarmObj;

@Repository("paasAlarmDao")
public class PaasAlarmDaoImpl extends BaseDao implements PaasAlarmDao {

	@Override
	public List<PaasAlarmObj> queryForAlarmList(PaasAlarmObj obj) {
		List<PaasAlarmObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("PaasAlarm.queryForAlarmListCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList("PaasAlarm.queryForAlarmList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("PaasAlarm.queryForAlarmList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
}
