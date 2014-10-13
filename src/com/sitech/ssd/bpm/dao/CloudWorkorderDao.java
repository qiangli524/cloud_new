package com.sitech.ssd.bpm.dao;

import java.util.List;

import com.sitech.ssd.bpm.domain.CloudWorkorder;


public interface CloudWorkorderDao{

	/** 
	*
	* @Title: insertByObj 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param t
	* @param @return    设定文件 
	* @return int    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	int insertByObj(CloudWorkorder t);

	/** 
	*
	* @Title: deleteByObj 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param t
	* @param @return    设定文件 
	* @return int    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	int deleteByObj(CloudWorkorder t);

	/** 
	*
	* @Title: updateByObj 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param t
	* @param @return    设定文件 
	* @return int    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	int updateByObj(CloudWorkorder t);

	/** 
	*
	* @Title: queryForList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param t
	* @param @return    设定文件 
	* @return List<CloudWorkorder>    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	List<CloudWorkorder> queryForList(CloudWorkorder t);

	/** 
	*
	* @Title: queryForObj 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param t
	* @param @return    设定文件 
	* @return CloudWorkorder    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	CloudWorkorder queryForObj(CloudWorkorder t);


}