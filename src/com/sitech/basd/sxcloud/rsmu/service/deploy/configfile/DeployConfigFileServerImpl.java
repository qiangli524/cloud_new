package com.sitech.basd.sxcloud.rsmu.service.deploy.configfile;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.dao.deploy.configfile.DeployConfigFileDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.configfile.DeployConfigFile;

public class DeployConfigFileServerImpl extends BaseDao implements DeployConfigFileServer {
	
	private DeployConfigFileDao deployConfigFileDao;
	
	public void setDeployConfigFileDao(DeployConfigFileDao deployConfigFileDao) {
		this.deployConfigFileDao = deployConfigFileDao;
	}

	public int deleteByObj(DeployConfigFile obj) {
		return deployConfigFileDao.deleteByObj(obj);
	}
	
	public int insertByObj(DeployConfigFile obj) {
		return deployConfigFileDao.insertByObj(obj);
	}

	public List queryForListByObj(DeployConfigFile obj) {
		return deployConfigFileDao.queryForListByObj(obj);
	}

}
