package com.sitech.basd.sxcloud.rsmu.service.deploy.version;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.deploy.version.TbCloud3AppFileVersionDao;
import com.sitech.basd.sxcloud.rsmu.dao.deploy.version.TbCloud3AppRollbackInfoDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppFileVersionVO;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppRollbackInfoVO;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class RollbackInfoServiceImpl extends BaseService implements
RollbackInfoService {

	

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-25 下午2:46:51
	 */
	public String insertByObj(TbCloud3AppRollbackInfoVO obj) {
		// TODO Auto-generated method stub
		return tbCloud3AppRollbackInfoDao.insertByVO(obj);
	}

	
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-25 下午2:46:51
	 */
	public List queryForListByObj(TbCloud3AppRollbackInfoVO obj) {
		// TODO Auto-generated method stub
		return tbCloud3AppRollbackInfoDao.queryListByObj(obj);
	}
	/**
	 * 
	 * @Title: queryVOByObj
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return TbCloud3AppVersionVO
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-25 下午2:51:09
	 */
	public TbCloud3AppRollbackInfoVO queryVOByObj(TbCloud3AppRollbackInfoVO obj) {
		// TODO Auto-generated method stub
		return tbCloud3AppRollbackInfoDao.queryVOByObj(obj);
	}
	

	TbCloud3AppRollbackInfoDao tbCloud3AppRollbackInfoDao;


	public TbCloud3AppRollbackInfoDao getTbCloud3AppRollbackInfoDao() {
		return tbCloud3AppRollbackInfoDao;
	}


	public void setTbCloud3AppRollbackInfoDao(
			TbCloud3AppRollbackInfoDao tbCloud3AppRollbackInfoDao) {
		this.tbCloud3AppRollbackInfoDao = tbCloud3AppRollbackInfoDao;
	}

	
	
}
