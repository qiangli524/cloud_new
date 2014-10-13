package com.sitech.basd.sxcloud.rsmu.dao.deploy.version;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppRollbackInfoVO;

/** 
 * 
 * <p>Title: TbCloud3AppFileVersionDao</p>
 * <p>Description: TODO(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author wangqxa
 * @version 1.0
 * @createtime 2013-3-22 上午10:12:56
 *
 */
public interface TbCloud3AppRollbackInfoDao  {


	
	/**
	 * 
	 * @Title: insertByVO
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-22 上午10:02:50
	 */
	public String insertByVO(TbCloud3AppRollbackInfoVO obj) ;

	

	/**
	 * 
	 * @Title: queryVOById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return TbCloud3AppFileVersionDao
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-22 上午10:02:41
	 */
	public TbCloud3AppRollbackInfoVO queryVOByObj(TbCloud3AppRollbackInfoVO obj);
	
	/**
	 * 
	 * @Title: queryVOById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return TbCloud3AppFileVersionDao
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-22 上午10:02:41
	 */
	public List queryListByObj(TbCloud3AppRollbackInfoVO obj) ;


}
