package com.sitech.basd.sxcloud.rsmu.service.deploy.version;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.deploy.version.TbCloud3AppOnlineFilepathDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppOnlineFilepathVO;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class OnlineFilepathServiceImpl extends BaseService implements
		OnlineFilepathService {

	TbCloud3AppOnlineFilepathDao tbCloud3AppOnlineFilepathDao;

	public TbCloud3AppOnlineFilepathDao getTbCloud3AppOnlineFilepathDao() {
		return tbCloud3AppOnlineFilepathDao;
	}

	public void setTbCloud3AppOnlineFilepathDao(
			TbCloud3AppOnlineFilepathDao tbCloud3AppOnlineFilepathDao) {
		this.tbCloud3AppOnlineFilepathDao = tbCloud3AppOnlineFilepathDao;
	}

	/**
	 * 
	 */
	public String insertByVO(TbCloud3AppOnlineFilepathVO obj) {
		return tbCloud3AppOnlineFilepathDao.insertByVO(obj);
	}

	/**
	 * 
	 */
	public String updateByVO(TbCloud3AppOnlineFilepathVO obj) {
		return tbCloud3AppOnlineFilepathDao.updateByVO(obj);
	}

	public TbCloud3AppOnlineFilepathVO queryVOByObj(
			TbCloud3AppOnlineFilepathVO obj) {
		return tbCloud3AppOnlineFilepathDao.queryVOByObj(obj);
	}

	public List<TbCloud3AppOnlineFilepathVO> queryListByObj(
			TbCloud3AppOnlineFilepathVO obj) {
		return tbCloud3AppOnlineFilepathDao.queryListByObj(obj);
	}

	/**
	 * 
	 * @Title: queryListByAppId
	 * @Description: TODO(根据appid查找上线文件List)
	 * @param
	 * @return List<TbCloud3AppOnlineFilepathVO>
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-4-14 下午4:18:34
	 */
	public List<TbCloud3AppOnlineFilepathVO> queryListByAppId(
			TbCloud3AppOnlineFilepathVO obj) {
		return tbCloud3AppOnlineFilepathDao.queryListByAppId(obj);
	}

}
