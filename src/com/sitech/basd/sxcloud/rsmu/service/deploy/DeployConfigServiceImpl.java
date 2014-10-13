package com.sitech.basd.sxcloud.rsmu.service.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployConfigObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.rsmu.dao.deploy.TbBusiDeployConfigDao;
public class DeployConfigServiceImpl extends BaseService implements
		DeployConfigService {

	/**
     * @Title:删除部署基本信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiDeployConfigObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployConfigDao.deleteByObj(obj);
	}

	/**
     * @Title:插入部署基本信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiDeployConfigObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployConfigDao.insertByObj(obj);
	}

	/**
     * @Title:查询出具体部署基本信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiDeployConfigObj queryByObj(TbBusiDeployConfigObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployConfigDao.queryByObj(obj);
	}

	/**
     * @Title:根据部署基本信息查询匹配的所有部署信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiDeployConfigObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployConfigDao.queryForListByObj(obj);
	}

	/**
     * @Title:更新部署基本信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiDeployConfigObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployConfigDao.updateByObj(obj);
	}
	
	TbBusiDeployConfigDao tbBusiDeployConfigDao;
	
	public void setTbBusiDeployConfigDao(TbBusiDeployConfigDao tbBusiDeployConfigDao) {
		this.tbBusiDeployConfigDao = tbBusiDeployConfigDao;
	}
	
	
}
