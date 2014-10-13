package com.sitech.basd.envmanager.service.condition;

import java.util.List;

import com.sitech.basd.envmanager.domain.condition.ConditionObj;





public interface ConditionService {
	
	public List queryConditionObj(ConditionObj obj);
	
	public ConditionObj queryConditionOne(ConditionObj obj);
	
	public int insertConditionObj(ConditionObj obj);
	
	public int updateConditionObj(ConditionObj obj);
	
	public int deleteConditionObj(ConditionObj obj);

}
