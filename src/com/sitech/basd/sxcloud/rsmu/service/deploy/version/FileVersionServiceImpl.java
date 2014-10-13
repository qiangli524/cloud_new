package com.sitech.basd.sxcloud.rsmu.service.deploy.version;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.deploy.version.TbCloud3AppFileVersionDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppFileVersionVO;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class FileVersionServiceImpl extends BaseService implements
FileVersionService {

	

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
	public String insertByObj(TbCloud3AppFileVersionVO obj) {
		// TODO Auto-generated method stub
		return tbCloud3AppFileVersionDao.insertByVO(obj);
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
	public List queryForListByObj(TbCloud3AppFileVersionVO obj) {
		// TODO Auto-generated method stub
		return tbCloud3AppFileVersionDao.queryListByObj(obj);
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
	public TbCloud3AppFileVersionVO queryVOByObj(TbCloud3AppFileVersionVO obj) {
		// TODO Auto-generated method stub
		return tbCloud3AppFileVersionDao.queryVOByObj(obj);
	}
	
	/**
	 * 
	 * @Title: queryFileVersionForResumeByObj
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return TbCloud3AppFileVersionVO
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-29 下午4:28:53
	 */
	public TbCloud3AppFileVersionVO queryFileVersionForResumeByObj(TbCloud3AppFileVersionVO obj) {
		return tbCloud3AppFileVersionDao.queryFileVersionForResumeByObj(obj);
	}
	

	TbCloud3AppFileVersionDao tbCloud3AppFileVersionDao;


	public TbCloud3AppFileVersionDao getTbCloud3AppFileVersionDao() {
		return tbCloud3AppFileVersionDao;
	}


	public void setTbCloud3AppFileVersionDao(
			TbCloud3AppFileVersionDao tbCloud3AppFileVersionDao) {
		this.tbCloud3AppFileVersionDao = tbCloud3AppFileVersionDao;
	}
	
	
}
