package com.sitech.basd.component.tree.service.tactics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.component.tree.dao.tactics.TacticsDao;
import com.sitech.basd.component.tree.domain.tactics.TacticsObj;

@Service("tacticsService")
public class TacticsServiceImpl implements TacticsService{

	@Autowired
	private TacticsDao tacticsDao;
	
	@Override
	public int insertTacticsObj(TacticsObj tacticsObj) {
		// TODO Auto-generated method stub
		return tacticsDao.insertTacticsObj(tacticsObj);
	}

	@Override
	public int deleteTacticsObj(TacticsObj tacticsObj) {
		// TODO Auto-generated method stub
		return tacticsDao.deleteTacticsObj(tacticsObj);
	}

	@Override
	public int updateTacticsObj(TacticsObj tacticsObj) {
		// TODO Auto-generated method stub
		return tacticsDao.updateTacticsObj(tacticsObj);
	}

	@Override
	public List<TacticsObj> queryForList(TacticsObj tacticsObj) {
		// TODO Auto-generated method stub
		return tacticsDao.queryForList(tacticsObj);
	}

	@Override
	public int countNum(TacticsObj tacticsObj) {
		// TODO Auto-generated method stub
		return tacticsDao.countNum(tacticsObj);
	}

	@Override
	public int deleteTacticsByTaskId(TacticsObj tacticsObj) {
		// TODO Auto-generated method stub
		return tacticsDao.deleteTacticsByTaskId(tacticsObj);
	}

}
