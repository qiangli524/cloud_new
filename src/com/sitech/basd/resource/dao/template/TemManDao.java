package com.sitech.basd.resource.dao.template;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.resource.domain.template.TemManObj;

public interface TemManDao {
	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询模板列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public List queryForList(TemManObj obj);

	/**
	 * 
	 * @Title: queryOneTemManObj
	 * @Description: 查询单个实体信息
	 * @param
	 * @return TemManObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-7 上午11:51:21
	 */
	public TemManObj queryOneTemManObj(TemManObj obj);

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 更新一条记录
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public int updateByObj(TemManObj obj);

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 删除一条记录
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public int deleteByObj(TemManObj obj);

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 插入一条记录
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public int insertByObj(TemManObj obj);

	/**
	 * 
	 * @Title: deleteByHostCodeAndTemplateCode
	 * @Description: 根据模板及连接Code删除信息
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-31 下午4:23:58
	 */
	public int deleteByHostCodeAndTemplateCode(TemManObj obj);

	/**
	 * 
	 * @Title: updateByHostCodeAndTemplateCode
	 * @Description: 根据模板及连接Code更新信息
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-31 下午4:24:31
	 */
	public int updateByHostCodeAndTemplateCode(TemManObj obj);

	/**
	 * 
	 * @Title: getAllTemplateEntity
	 * @Description:获取所有唯一实体列表（用于数据比对）
	 * @param
	 * @return List<String>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-3 上午10:09:59
	 */
	public List<String> getAllTemplateEntity(TemManObj obj);

	/**
	 * 
	 * @Title:根据模板ID获取同一虚拟化类型下的所有模板列表
	 * @Copyright:Copyright (c) Aug 29, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public List<TemManObj> queryTemListById(TemManObj obj);

	/**
	 * @throws SQLException  
	*
	* @Title: queryForCount 
	* @Description: TODO(查询映像总数) 
	* @param @param obj
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public Integer queryForCount(TemManObj obj) throws SQLException;
	
	/**
	 * @Title: queryTemSystemList
	 * @Description: 查询模板中操作系统的名称
	 * @return
	 * @throws SQLException
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-6-17 下午6:39:01
	 */
	public List<String> queryTemSystemList() throws SQLException;
	
	/**
	 * @Title: queryTemBySystem
	 * @Description: 根据操作系统名称，查询模板列表
	 * @param systemName
	 * @return
	 * @throws SQLException
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-6-17 下午6:44:38
	 */
	public List<TemManObj> queryTemBySystem(String systemName) throws SQLException;
}
