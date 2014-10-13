package com.sitech.basd.resource.service.switchBoard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.switchBoard.StaticRouterDao;
import com.sitech.basd.resource.domain.switchBoard.StaticRouterObj;

@Service("staticRouterService")
public class StaticRouterServiceImpl implements StaticRouterService {
	@Autowired
	private StaticRouterDao staticRouterDao;
	
	@Override
	public List<StaticRouterObj> queryForList(StaticRouterObj obj) {
		return staticRouterDao.queryForList(obj);
	}
	@Override
	public int deleteByObj(StaticRouterObj obj) {
		return staticRouterDao.deleteByObj(obj);
	}
	
	@Override
	public int insertByObj(StaticRouterObj obj) {
		// TODO Auto-generated method stub
		return staticRouterDao.insertByObj(obj);
	}
	
	@Override
	public int updateByObj(StaticRouterObj obj) {
		// TODO Auto-generated method stub
		return staticRouterDao.updateByObj(obj);
	}
	
}
