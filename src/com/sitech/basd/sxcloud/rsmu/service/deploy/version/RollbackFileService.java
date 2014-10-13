package com.sitech.basd.sxcloud.rsmu.service.deploy.version;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppRollbackFileVO;

public interface RollbackFileService {


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
	public String insertByObj(TbCloud3AppRollbackFileVO obj);

	
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
	public List queryForListByObj(TbCloud3AppRollbackFileVO obj);
	
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
	public TbCloud3AppRollbackFileVO queryVOByObj(TbCloud3AppRollbackFileVO obj) ;
	

}
