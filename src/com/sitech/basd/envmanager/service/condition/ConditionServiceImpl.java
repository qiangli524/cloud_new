package com.sitech.basd.envmanager.service.condition;

import java.util.List;



import com.sitech.basd.envmanager.dao.condition.ConditionDao;
import com.sitech.basd.envmanager.domain.condition.ConditionObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class ConditionServiceImpl extends BaseService implements ConditionService{
	
	private ConditionDao conditionDao;

	public ConditionDao getConditionDao() {
		return conditionDao;
	}

	public void setConditionDao(ConditionDao conditionDao) {
		this.conditionDao = conditionDao;
	}

	@Override
	public int deleteConditionObj(ConditionObj obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertConditionObj(ConditionObj obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List queryConditionObj(ConditionObj obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConditionObj queryConditionOne(ConditionObj obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateConditionObj(ConditionObj obj) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
