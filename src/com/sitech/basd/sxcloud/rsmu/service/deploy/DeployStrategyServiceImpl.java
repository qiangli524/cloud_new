package com.sitech.basd.sxcloud.rsmu.service.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployStrategyObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.rsmu.dao.deploy.TbBusiDeployStrategyDao;
public class DeployStrategyServiceImpl extends BaseService implements
		DeployStrategyService {

	 /**
     * @Title:删除部署策略信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiDeployStrategyObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployStrategyDao.deleteByObj(obj);
	}

	/**
     * @Title:插入部署策略信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiDeployStrategyObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployStrategyDao.insertByObj(obj);
	}

	/**
     * @Title:查询出具体部署策略信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiDeployStrategyObj queryByObj(TbBusiDeployStrategyObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployStrategyDao.queryByObj(obj);
	}

	/**
     * @Title:根据部署策略信息查询匹配的所有部署策略信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiDeployStrategyObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployStrategyDao.queryForListByObj(obj);
	}

	/**
     * @Title:更新部署策略信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiDeployStrategyObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployStrategyDao.updateByObj(obj);
	}
	
	public List queryNameListByObj(TbBusiDeployStrategyObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployStrategyDao.queryNameListByObj(obj);
	}

	TbBusiDeployStrategyDao tbBusiDeployStrategyDao;
	
	public void setTbBusiDeployStrategyDao(
			TbBusiDeployStrategyDao tbBusiDeployStrategyDao) {
		this.tbBusiDeployStrategyDao = tbBusiDeployStrategyDao;
	}

}
