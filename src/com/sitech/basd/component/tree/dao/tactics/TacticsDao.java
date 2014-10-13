package com.sitech.basd.component.tree.dao.tactics;

import java.util.List;

import com.sitech.basd.component.tree.domain.tactics.TacticsObj;


public interface TacticsDao {

	public int insertTacticsObj(TacticsObj tacticsObj);
	
	public int deleteTacticsObj(TacticsObj tacticsObj);
	
	public int updateTacticsObj(TacticsObj tacticsObj);
	
	public List<TacticsObj> queryForList(TacticsObj tacticsObj);

	public int countNum(TacticsObj tacticsObj);

	public int deleteTacticsByTaskId(TacticsObj tacticsObj);
}
