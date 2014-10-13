package com.sitech.basd.sxcloud.rsmu.service.deploy.version;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.deploy.version.TbCloud3AppVersionDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppVersionVO;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class VersionResumeServiceImpl extends BaseService implements
VersionResumeService {

	

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
	public String insertByObj(TbCloud3AppVersionVO obj) {
		// TODO Auto-generated method stub
		return tbCloud3AppVersionDao.insertByVO(obj);
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
	public List queryForListByObj(TbCloud3AppVersionVO obj) {
		// TODO Auto-generated method stub
		return tbCloud3AppVersionDao.queryListByObj(obj);
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
	public TbCloud3AppVersionVO queryVOByObj(TbCloud3AppVersionVO obj) {
		// TODO Auto-generated method stub
		return tbCloud3AppVersionDao.queryVOByObj(obj);
	}
	

	TbCloud3AppVersionDao tbCloud3AppVersionDao;
	public TbCloud3AppVersionDao getTbCloud3AppVersionDao() {
		return tbCloud3AppVersionDao;
	}

	public void setTbCloud3AppVersionDao(TbCloud3AppVersionDao tbCloud3AppVersionDao) {
		this.tbCloud3AppVersionDao = tbCloud3AppVersionDao;
	}
	
	
}
