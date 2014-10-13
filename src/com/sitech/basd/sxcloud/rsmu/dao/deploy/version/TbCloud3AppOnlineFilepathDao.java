package com.sitech.basd.sxcloud.rsmu.dao.deploy.version;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppOnlineFilepathVO;

/**
 * 
 * <p>
 * Title: TbCloud3AppFileVersionDao
 * </p>
 * <p>
 * Description: TODO(用一句话描述该文件做什么)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author wangqxa
 * @version 1.0
 * @createtime 2013-3-22 上午10:12:56
 * 
 */
public interface TbCloud3AppOnlineFilepathDao {

	/**
	 * 
	 * @Title: insertByVO
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-28 下午5:12:01
	 */
	public String insertByVO(TbCloud3AppOnlineFilepathVO obj);

	/**
	 * 
	 * @Title: updateByVO
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-28 下午5:12:04
	 */
	public String updateByVO(TbCloud3AppOnlineFilepathVO obj);

	/**
	 * 
	 * @Title: queryVOByObj
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return TbCloud3AppCurrentFilepathVO
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-28 下午5:12:07
	 */
	public TbCloud3AppOnlineFilepathVO queryVOByObj(
			TbCloud3AppOnlineFilepathVO obj);

	/**
	 * 
	 * @Title: queryCollListByObj
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List<TbCloud3AppOnlineFilepathVO>
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-29 上午11:47:57
	 */
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
	 * @createtime 2013-3-29 上午11:47:57
	 */
	public List<TbCloud3AppOnlineFilepathVO> queryListByAppId(
			TbCloud3AppOnlineFilepathVO obj);

}
