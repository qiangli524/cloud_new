package com.sitech.basd.resource.dao.global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import util.DomainUtil;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("alarmGlobalDao")
public class AlarmGlobalDaoImpl extends BaseDao implements AlarmGlobalDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Double> queryTopAlarm(Map<String, Integer> map) {
		List<Double> list = new ArrayList<Double>();
		try {
			Map<String, Object> paramMap = DomainUtil.setDomainToObjMap(map);
			list = getSqlMap().queryForList("AlarmGlobal.queryTopAlarm", paramMap);
		} catch (Exception e) {
			LogHelper
					.error("AlarmGlobal.queryTopAlarm: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryAlarmXData(Map<String, Integer> map) {
		List<String> list = new ArrayList<String>();
		try {
			Map<String, Object> paramMap = DomainUtil.setDomainToObjMap(map);
			list = getSqlMap().queryForList("AlarmGlobal.queryAlarmXData", paramMap);
		} catch (Exception e) {
			LogHelper.error("AlarmGlobal.queryAlarmXData: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

}
