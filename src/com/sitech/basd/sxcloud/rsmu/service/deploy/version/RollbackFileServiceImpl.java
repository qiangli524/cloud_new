package com.sitech.basd.sxcloud.rsmu.service.deploy.version;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.deploy.version.TbCloud3AppFileVersionDao;
import com.sitech.basd.sxcloud.rsmu.dao.deploy.version.TbCloud3AppRollbackFileDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppFileVersionVO;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppRollbackFileVO;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class RollbackFileServiceImpl extends BaseService implements
RollbackFileService {

	

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
	public String insertByObj(TbCloud3AppRollbackFileVO obj) {
		// TODO Auto-generated method stub
		return tbCloud3AppRollbackFileDao.insertByVO(obj);
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
	public List queryForListByObj(TbCloud3AppRollbackFileVO obj) {
		// TODO Auto-generated method stub
		return tbCloud3AppRollbackFileDao.queryListByObj(obj);
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
	public TbCloud3AppRollbackFileVO queryVOByObj(TbCloud3AppRollbackFileVO obj) {
		// TODO Auto-generated method stub
		return tbCloud3AppRollbackFileDao.queryVOByObj(obj);
	}
	

	TbCloud3AppRollbackFileDao tbCloud3AppRollbackFileDao;


	public TbCloud3AppRollbackFileDao getTbCloud3AppRollbackFileDao() {
		return tbCloud3AppRollbackFileDao;
	}


	public void setTbCloud3AppRollbackFileDao(
			TbCloud3AppRollbackFileDao tbCloud3AppRollbackFileDao) {
		this.tbCloud3AppRollbackFileDao = tbCloud3AppRollbackFileDao;
	}
	
}
