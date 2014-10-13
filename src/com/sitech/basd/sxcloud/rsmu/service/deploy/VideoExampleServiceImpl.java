package com.sitech.basd.sxcloud.rsmu.service.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.deploy.TbBusiDeployVideoCommandsetDao;
import com.sitech.basd.sxcloud.rsmu.dao.deploy.TbBusiDeployVideorecordDao;
import com.sitech.basd.sxcloud.rsmu.dao.deploy.TbBusiVideoExampleDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployVideoCommandsetObj;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployVideorecordObj;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiVideoExampleObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class VideoExampleServiceImpl extends BaseService implements VideoExampleService{
	
	public int deleteByObj(TbBusiVideoExampleObj obj) {
		// TODO Auto-generated method stub
		return tbBusiVideoExampleDao.deleteByObj(obj);
	}

	public TbBusiVideoExampleObj queryByObj(TbBusiVideoExampleObj obj) {
		// TODO Auto-generated method stub
		return tbBusiVideoExampleDao.queryByObj(obj);
	}

	public List queryForListByObj(TbBusiVideoExampleObj obj) {
		// TODO Auto-generated method stub
		return tbBusiVideoExampleDao.queryForListByObj(obj);
	}

	public int updateByObj(TbBusiVideoExampleObj obj) {
		// TODO Auto-generated method stub
		return tbBusiVideoExampleDao.updateByObj(obj);
	}
	public int insertByObj(TbBusiVideoExampleObj obj) {
		// TODO Auto-generated method stub
		return tbBusiVideoExampleDao.insertByObj(obj);
	}
	
	
	public List queryIDListByObj(TbBusiDeployVideorecordObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployVideorecordDao.queryIDListByObj(obj);
	}
	
	public List queryForCommandListByVideoid(TbBusiDeployVideoCommandsetObj obj){
		
		return tbBusiDeployVideoCommandsetDao.queryForCommandListByVideoid(obj);
	}

	TbBusiVideoExampleDao tbBusiVideoExampleDao;
	TbBusiDeployVideorecordDao tbBusiDeployVideorecordDao ;
	TbBusiDeployVideoCommandsetDao tbBusiDeployVideoCommandsetDao;
	
	public void setTbBusiVideoExampleDao(TbBusiVideoExampleDao tbBusiVideoExampleDao) {
		this.tbBusiVideoExampleDao = tbBusiVideoExampleDao;
	}

	public void setTbBusiDeployVideorecordDao(
			TbBusiDeployVideorecordDao tbBusiDeployVideorecordDao) {
		this.tbBusiDeployVideorecordDao = tbBusiDeployVideorecordDao;
	}

	public void setTbBusiDeployVideoCommandsetDao(
			TbBusiDeployVideoCommandsetDao tbBusiDeployVideoCommandsetDao) {
		this.tbBusiDeployVideoCommandsetDao = tbBusiDeployVideoCommandsetDao;
	}
	
}
