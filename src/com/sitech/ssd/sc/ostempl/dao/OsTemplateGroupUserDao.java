package com.sitech.ssd.sc.ostempl.dao;

import java.util.List;

import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroupUser;

/**
 * 
 * @ClassName: OsTemplateGroupUserDao
 * @Description: OS安装模版 -- 用户与组
 * @author JamTau
 * @date 2014-8-20 下午5:26:13
 *
 */
public interface OsTemplateGroupUserDao {

	/**
	 * 
	 * @Title: insertOsTemplateGroupUser
	 * @Description: insertOsTemplateGroupUser
	 * @param OsTemplateGroupUser 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int insertOsTemplateGroupUser(OsTemplateGroupUser obj);
	
	/**
	 * 
	 * @Title: insertOsTemplateGroupUser
	 * @Description: insertOsTemplateGroupUser
	 * @param  输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int copyOsTemplateGroupUser(OsTemplateGroupUser obj);
	
	/**
	 * 
	 * @Title: deleteOsTemplateGroupUser
	 * @Description: deleteOsTemplateGroupUser
	 * @param OsTemplateGroupUser 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsTemplateGroupUser(OsTemplateGroupUser obj);
	
	/**
	 * 
	 * @Title: updateOsTemplateGroupUser
	 * @Description: updateOsTemplateGroupUser
	 * @param OsTemplateGroupUser 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int updateOsTemplateGroupUser(OsTemplateGroupUser obj);
	
	/**
	 * 
	 * @Title: selectOsTemplateGroupUser
	 * @Description: selectOsTemplateGroupUser
	 * @param OsTemplateGroupUser 输入参数
	 * @return OsTemplateGroupUser 返回类型
	 * @throws
	 */
	public OsTemplateGroupUser selectOsTemplateGroupUser(OsTemplateGroupUser obj);
	
	/**
	 * 
	 * @Title: selectOsTemplateGroupUserList
	 * @Description: selectOsTemplateGroupUserList
	 * @param OsTemplateGroupUser 输入参数
	 * @return List<OsTemplateGroupUser> 返回类型
	 * @throws
	 */
	public List<OsTemplateGroupUser> selectOsTemplateGroupUserList(OsTemplateGroupUser obj);
}
