package com.sitech.basd.sxcloud.rsmu.service.deploy.version;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.deploy.version.TbCloud3AppVersionDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppVersionVO;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class AppVersionServiceImpl extends BaseService implements
		AppVersionService {

	/**
	 * \
	 * 
	 * @Title: queryByObj
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return TbCloud3AppVersionVO
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-29 下午4:08:51
	 */
	public TbCloud3AppVersionVO queryByObj(TbCloud3AppVersionVO obj) {
		return tbCloud3AppVersionDao.queryVOByObj(obj);
	}
	
	/**
	 * 
	 * @Title: queryMinAppVersion
	 * @Description: TODO(查询初始化版本)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-4-9 下午4:20:34
	 */
	public String queryMinAppVersion(TbCloud3AppVersionVO obj) {
		return tbCloud3AppVersionDao.queryMinAppVersion(obj);
	}

	/**
	 * 
	 * @Title: queryCollListByObj
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List<TbCloud3AppVersionVO>
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-29 上午11:47:57
	 */
	public List<TbCloud3AppVersionVO> queryCollListByObj(
			TbCloud3AppVersionVO obj) {
		return tbCloud3AppVersionDao.queryCollListByObj(obj);
	}

	TbCloud3AppVersionDao tbCloud3AppVersionDao;

	public TbCloud3AppVersionDao getTbCloud3AppVersionDao() {
		return tbCloud3AppVersionDao;
	}

	public void setTbCloud3AppVersionDao(
			TbCloud3AppVersionDao tbCloud3AppVersionDao) {
		this.tbCloud3AppVersionDao = tbCloud3AppVersionDao;
	}

}
