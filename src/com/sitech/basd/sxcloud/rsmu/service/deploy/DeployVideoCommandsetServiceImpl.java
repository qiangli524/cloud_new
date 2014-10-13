package com.sitech.basd.sxcloud.rsmu.service.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.deploy.TbBusiDeployVideoCommandsetDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployVideoCommandsetObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class DeployVideoCommandsetServiceImpl extends BaseService implements DeployVideoCommandsetService{
	
	public int deleteByObj(TbBusiDeployVideoCommandsetObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployVideoCommandsetDao.deleteByObj(obj);
	}

	public TbBusiDeployVideoCommandsetObj queryByObj(TbBusiDeployVideoCommandsetObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployVideoCommandsetDao.queryByObj(obj);
	}

	public List queryForListByObj(TbBusiDeployVideoCommandsetObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployVideoCommandsetDao.queryForListByObj(obj);
	}

	public int updateByObj(TbBusiDeployVideoCommandsetObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployVideoCommandsetDao.updateByObj(obj);
	}
	public int insertByObj(TbBusiDeployVideoCommandsetObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployVideoCommandsetDao.insertByObj(obj);
	}


	TbBusiDeployVideoCommandsetDao tbBusiDeployVideoCommandsetDao;


	public void setTbBusiDeployVideoCommandsetDao(
			TbBusiDeployVideoCommandsetDao tbBusiDeployVideoCommandsetDao) {
		this.tbBusiDeployVideoCommandsetDao = tbBusiDeployVideoCommandsetDao;
	}
	
	
	
}
