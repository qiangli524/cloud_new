package com.sitech.basd.sxcloud.rsmu.service.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.deploy.TbBusiDeployExampleHisDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleHisObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;


public class DeployExampleHisServiceImpl extends BaseService implements
		DeployExampleHisService {

	public int deleteByObj(TbBusiDeployExampleHisObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployExampleHisDao.deleteByObj(obj);
	}

	public int insertByObj(TbBusiDeployExampleHisObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployExampleHisDao.insertByObj(obj);
	}

	public TbBusiDeployExampleHisObj queryByObj(TbBusiDeployExampleHisObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployExampleHisDao.queryByObj(obj);
	}

	@SuppressWarnings("unchecked")
	public List queryForListByObj(TbBusiDeployExampleHisObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployExampleHisDao.queryForListByObj(obj);
	}


	TbBusiDeployExampleHisDao tbBusiDeployExampleHisDao;

	public void setTbBusiDeployExampleHisDao(
			TbBusiDeployExampleHisDao tbBusiDeployExampleHisDao) {
		this.tbBusiDeployExampleHisDao = tbBusiDeployExampleHisDao;
	}

	
}
