package com.sitech.basd.sxcloud.rsmu.service.deploy.version;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppOnlineFilepathVO;

public interface OnlineFilepathService {

	/**
	 * 
	 */
	public String insertByVO(TbCloud3AppOnlineFilepathVO obj);

	/**
	 * 
	 */
	public String updateByVO(TbCloud3AppOnlineFilepathVO obj);

	public TbCloud3AppOnlineFilepathVO queryVOByObj(
			TbCloud3AppOnlineFilepathVO obj);

	public List<TbCloud3AppOnlineFilepathVO> queryListByObj(
			TbCloud3AppOnlineFilepathVO obj);

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
			TbCloud3AppOnlineFilepathVO obj);

}
