package com.sitech.basd.resource.dao.global;

import java.util.List;
import java.util.Map;

public interface AlarmGlobalDao {

	public List<Double> queryTopAlarm(Map<String, Integer> map);

	public List<String> queryAlarmXData(Map<String, Integer> map);
}
