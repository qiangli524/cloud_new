package com.sitech.ssd.sc.ostempl.dao;

import java.util.List;

import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroup;

/**
 * 
 * @ClassName: OsTemplateGroupDao
 * @Description: OS安装模版 -- 用户组DAO
 * @author JamTau
 * @date 2014-8-20 下午5:22:47
 *
 */
public interface OsTemplateGroupDao {

	/**
	 * 
	 * @Title: insertOsTemplateGroup
	 * @Description: insertOsTemplateGroup
	 * @param OsTemplateGroup 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int insertOsTemplateGroup(OsTemplateGroup obj);
	
	/**
	 * 
	 * @Title: copyOsTemplateGroup
	 * @Description: copyOsTemplateGroup
	 * @param  输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int copyOsTemplateGroup(OsTemplateGroup obj);
	
	/**
	 * 
	 * @Title: deleteOsTemplateGroup
	 * @Description: deleteOsTemplateGroup
	 * @param OsTemplateGroup 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsTemplateGroup(OsTemplateGroup obj);
	
	/**
	 * 
	 * @Title: updateOsTemplateGroup
	 * @Description: updateOsTemplateGroup
	 * @param OsTemplateGroup 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int updateOsTemplateGroup(OsTemplateGroup obj);
	
	/**
	 * 
	 * @Title: selectOsTemplateGroup
	 * @Description: selectOsTemplateGroup
	 * @param OsTemplateGroup 输入参数
	 * @return OsTemplateGroup 返回类型
	 * @throws
	 */
	public OsTemplateGroup selectOsTemplateGroup(OsTemplateGroup obj);
	
	/**
	 * 
	 * @Title: selectOsTemplateGroupList
	 * @Description: selectOsTemplateGroupList
	 * @param OsTemplateGroup 输入参数
	 * @return List<OsTemplateGroup> 返回类型
	 * @throws
	 */
	public List<OsTemplateGroup> selectOsTemplateGroupList(OsTemplateGroup obj);
	
}
