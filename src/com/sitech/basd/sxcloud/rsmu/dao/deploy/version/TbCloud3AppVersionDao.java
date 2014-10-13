package com.sitech.basd.sxcloud.rsmu.dao.deploy.version;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppVersionVO;

/**
 * 
 * <p>
 * Title: TbCloud3AppVersionDao
 * </p>
 * <p>
 * Description: 应用版本Dao
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-3-23 上午10:20:56
 * 
 */
public interface TbCloud3AppVersionDao {

	
	/**
	 * 
	 * @Title: insertByVO
	 * @Description: 插入数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-3-23 上午10:21:45
	 */
	public String insertByVO(TbCloud3AppVersionVO obj) ;

	/**
	 * @Title: queryVOByID
	 * @Description: 查询数据
	 * @param
	 * @return TbCloud3AppVersionDao
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-3-23 上午10:21:33
	 */
	public TbCloud3AppVersionVO queryVOByObj(TbCloud3AppVersionVO obj) ;
	
	/**
	 * 
	 * @Title: queryListByObj
	 * @Description: 查询数据
	 * @param
	 * @return TbCloud3AppVersionDao
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-3-23 上午10:21:33
	 */
	public List<TbCloud3AppVersionVO> queryListByObj(TbCloud3AppVersionVO obj) ;
	
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
	public List<TbCloud3AppVersionVO> queryCollListByObj(TbCloud3AppVersionVO obj);
	
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
	public String queryMinAppVersion(TbCloud3AppVersionVO obj);

	
}
