package com.sitech.basd.sxcloud.rsmu.service.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployAlarmObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.rsmu.dao.deploy.TbBusiDeployAlarmDao;

public class DeployAlarmServiceImpl extends BaseService implements
		DeployAlarmService {

	/**
     * @Title:删除异常告警信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiDeployAlarmObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployAlarmDao.deleteByObj(obj);
	}

	/**
     * @Title:插入异常告警信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiDeployAlarmObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployAlarmDao.insertByObj(obj);
	}

	/**
     * @Title:查询出具体异常告警信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiDeployAlarmObj queryByObj(TbBusiDeployAlarmObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployAlarmDao.queryByObj(obj);
	}
	/**
     * @Title:根据异常告警部分信息查询匹配的所有异常告警信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiDeployAlarmObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployAlarmDao.queryForListByObj(obj);
	}

	/**
     * @Title:更新异常告警信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiDeployAlarmObj obj) {
		// TODO Auto-generated method stub
		return tbBusiDeployAlarmDao.updateByObj(obj);
	}
	TbBusiDeployAlarmDao tbBusiDeployAlarmDao;
	public void setTbBusiDeployAlarmDao(TbBusiDeployAlarmDao tbBusiDeployAlarmDao) {
		this.tbBusiDeployAlarmDao = tbBusiDeployAlarmDao;
	}
	
	
}
