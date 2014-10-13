package com.sitech.basd.sxcloud.rsmu.service.deploy.deployfileversion;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.deploy.deployfileversion.DeployFileVersionDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.deployfileversion.DeployFileVersionObj;

public class DeployFileVersionServiceImpl implements DeployFileVersionService {

	private DeployFileVersionDao deployFileVersionDao;

	public void setDeployFileVersionDao(DeployFileVersionDao deployFileVersionDao) {
		this.deployFileVersionDao = deployFileVersionDao;
	}

	@Override
	public int deleteFileVersionObj(DeployFileVersionObj obj) {
		return deployFileVersionDao.deleteFileVersionObj(obj);
	}

	@Override
	public int insertFileVersionObj(DeployFileVersionObj obj) {
		// TODO Auto-generated method stub
		return deployFileVersionDao.insertFileVersionObj(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List queryFileVersionList(DeployFileVersionObj obj) {
		// TODO Auto-generated method stub
		return deployFileVersionDao.queryFileVersionList(obj);
	}

	@Override
	public DeployFileVersionObj queryFileVersionOne(DeployFileVersionObj obj) {
		// TODO Auto-generated method stub
		return deployFileVersionDao.queryFileVersionOne(obj);
	}

	@Override
	public int updateFileVersionObj(DeployFileVersionObj obj) {
		// TODO Auto-generated method stub
		return deployFileVersionDao.updateFileVersionObj(obj);
	}

	@Override
	public double getMaxVersionNoByAppid(DeployFileVersionObj obj) {
		// TODO Auto-generated method stub
		return deployFileVersionDao.getMaxVersionNoByAppid(obj);
	}

	@Override
	public int insertFileVersionHis(DeployFileVersionObj obj) {
		// TODO Auto-generated method stub
		return deployFileVersionDao.insertFileVersionHis(obj);
	}

	@Override
	public List queryFileVersionHisList(DeployFileVersionObj obj) {
		// TODO Auto-generated method stub
		return deployFileVersionDao.queryFileVersionHisList(obj);
	}

	@Override
	public String querySoftCatchFiles(DeployFileVersionObj obj) {
		// TODO Auto-generated method stub
		return deployFileVersionDao.querySoftCatchFiles(obj);
	}

}
