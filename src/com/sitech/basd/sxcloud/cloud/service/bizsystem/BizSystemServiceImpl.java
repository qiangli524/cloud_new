package com.sitech.basd.sxcloud.cloud.service.bizsystem;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.dao.bizsystem.BizSystemDao;
import com.sitech.basd.sxcloud.cloud.domain.bizsystem.BizSystemObj;
import com.sitech.basd.sxcloud.cloud.domain.bizsystem.DomainObj;

public class BizSystemServiceImpl implements BizSystemService {

	@Override
	public List queryForListByObj(BizSystemObj obj) {
		// TODO Auto-generated method stub
		return bizSystemDao.queryForListByObj(obj);
	}

	@Override
	public int deleteByObj(BizSystemObj obj) {
		// TODO Auto-generated method stub
		return bizSystemDao.deleteByObj(obj);
	}

	@Override
	public String insertByObj(BizSystemObj obj) {
		// TODO Auto-generated method stub
		return bizSystemDao.insertByObj(obj);
	}

	@Override
	public int updateByObj(BizSystemObj obj) {
		// TODO Auto-generated method stub
		return bizSystemDao.updateByObj(obj);
	}

	BizSystemDao bizSystemDao;

	public void setBizSystemDao(BizSystemDao bizSystemDao) {
		this.bizSystemDao = bizSystemDao;
	}

	@Override
	public List queryForListByDomainObj(DomainObj obj) {
		// TODO Auto-generated method stub
		return bizSystemDao.queryForListByDomainObj(obj);
	}

	@Override
	public BizSystemObj queryByObj(BizSystemObj obj) {
		// TODO Auto-generated method stub
		return bizSystemDao.queryByObj(obj);
	}

	/*
	 * 查询业务系统下的信息，包括基准应用，部署实例个数
	 */
	public List busiSystemInfo(BizSystemObj obj) {
		return bizSystemDao.busiSystemInfo(obj);
	}
}
