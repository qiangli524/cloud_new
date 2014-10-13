package com.sitech.ssd.sc.ostempl.dao;

import java.util.List;

import com.sitech.ssd.sc.ostempl.domain.OsTemplateVolGroup;

/**
 * 
 * @ClassName: OsTemplateVolGroupDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author JamTau
 * @date 2014-9-16 下午9:19:35
 *
 */
public interface OsTemplateVolGroupDao {
	/**
	 * 
	 * @Title: insertOsTemplateVolGroup
	 * @Description: insertOsTemplateVolGroup
	 * @param OsTemplateVolGroup 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int insertOsTemplateVolGroup(OsTemplateVolGroup obj);
	
	/**
	 * 
	 * @Title: copyOsTemplateVolGroup
	 * @Description: copyOsTemplateVolGroup
	 * @param  OsTemplateVolGroup 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int copyOsTemplateVolGroup(OsTemplateVolGroup obj);
	
	/**
	 * 
	 * @Title: deleteOsTemplateVolGroup
	 * @Description: deleteOsTemplateVolGroup
	 * @param OsTemplateVolGroup 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsTemplateVolGroup(OsTemplateVolGroup obj);
	
	/**
	 * 
	 * @Title: updateOsTemplateVolGroup
	 * @Description: updateOsTemplateVolGroup
	 * @param OsTemplateVolGroup 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int updateOsTemplateVolGroup(OsTemplateVolGroup obj);
	
	/**
	 * 
	 * @Title: selectOsTemplateVolGroup
	 * @Description: selectOsTemplateVolGroup
	 * @param OsTemplateVolGroup 输入参数
	 * @return OsTemplateVolGroup 返回类型
	 * @throws
	 */
	public OsTemplateVolGroup selectOsTemplateVolGroup(OsTemplateVolGroup obj);
	
	/**
	 * 
	 * @Title: selectOsTemplateVolGroupList
	 * @Description: selectOsTemplateVolGroupList
	 * @param OsTemplateVolGroup 输入参数
	 * @return List<OsTemplateVolGroup> 返回类型
	 * @throws
	 */
	public List<OsTemplateVolGroup> selectOsTemplateVolGroupList(OsTemplateVolGroup obj);
}
