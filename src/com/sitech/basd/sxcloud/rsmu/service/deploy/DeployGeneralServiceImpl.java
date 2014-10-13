package com.sitech.basd.sxcloud.rsmu.service.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployGeneralObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.rsmu.dao.deploy.TbBusiDeployGeneralDao;
public class DeployGeneralServiceImpl extends BaseService implements
		DeployGeneralService {

	 /**
     * @Title:删除个性化配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiDeployGeneralObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployGeneralDao.deleteByObj(obj);
	}

	/**
     * @Title:插入个性化配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiDeployGeneralObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployGeneralDao.insertByObj(obj);
	}

	/**
     * @Title:查询出具体个性化配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiDeployGeneralObj queryByObj(TbBusiDeployGeneralObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployGeneralDao.queryByObj(obj);
	}

	/**
     * @Title:根据"部署个性化配置"部分信息查询匹配的所有部署个性化配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiDeployGeneralObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployGeneralDao.queryForListByObj(obj);
	}

	/**
     * @Title:更新个性化配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiDeployGeneralObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployGeneralDao.updateByObj(obj);
	}

	TbBusiDeployGeneralDao tbBusiDeployGeneralDao;

	public void setTbBusiDeployGeneralDao(
			TbBusiDeployGeneralDao tbBusiDeployGeneralDao) {
		this.tbBusiDeployGeneralDao = tbBusiDeployGeneralDao;
	}

	

	
}
