package com.sitech.ssd.sc.ostempl.dao;

import java.util.List;

import com.sitech.ssd.sc.ostempl.domain.OsTemplateUser;

/**
 * 
 * @ClassName: OsTemplateUserDao
 * @Description: OS安装模版 -- 用户DAO
 * @author JamTau
 * @date 2014-8-20 下午5:28:29
 *
 */
public interface OsTemplateUserDao {

	/**
	 * 
	 * @Title: insertOsTemplateUser
	 * @Description: insertOsTemplateUser
	 * @param OsTemplateUser 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int insertOsTemplateUser(OsTemplateUser obj);
	
	/**
	 * 
	 * @Title: copyOsTemplateUser
	 * @Description: copyOsTemplateUser
	 * @param  OsTemplateUser 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int copyOsTemplateUser(OsTemplateUser obj);
	
	/**
	 * 
	 * @Title: deleteOsTemplateUser
	 * @Description: deleteOsTemplateUser
	 * @param OsTemplateUser 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsTemplateUser(OsTemplateUser obj);
	
	/**
	 * 
	 * @Title: updateOsTemplateUser
	 * @Description: updateOsTemplateUser
	 * @param OsTemplateUser 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int updateOsTemplateUser(OsTemplateUser obj);
	
	/**
	 * 
	 * @Title: selectOsTemplateUser
	 * @Description: selectOsTemplateUser
	 * @param OsTemplateUser 输入参数
	 * @return OsTemplateUser 返回类型
	 * @throws
	 */
	public OsTemplateUser selectOsTemplateUser(OsTemplateUser obj);
	
	/**
	 * 
	 * @Title: selectOsTemplateUserList
	 * @Description: selectOsTemplateUserList
	 * @param OsTemplateUser 输入参数
	 * @return List<OsTemplateUser> 返回类型
	 * @throws
	 */
	public List<OsTemplateUser> selectOsTemplateUserList(OsTemplateUser obj);
}
