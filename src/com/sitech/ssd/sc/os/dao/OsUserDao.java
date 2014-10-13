package com.sitech.ssd.sc.os.dao;

import java.util.List;

import com.sitech.ssd.sc.os.domain.OsUserModel;

/**
 * @ClassName OsUserDao
 * @Desc 
 * @Author JamTau
 * @date May 21, 2014 6:46:25 PM
 */
public interface OsUserDao {

	/**
	 * @Tittle insertUser
	 * @Description 
	 * @Modify Date May 21, 2014 6:47:29 PM
	 * @param osUser
	 * @return int
	 */
	public int insertUser(OsUserModel user);
	public int copyOsTemplateUser(OsUserModel user) ;
	public int deleteUser(OsUserModel user);
	public int updateUser(OsUserModel user);	
	
	/**
	 * @Tittle queryForList
	 * @Description 
	 * @Modify Date May 21, 2014 6:48:39 PM
	 * @param user
	 * @return List<UserModel>
	 */
	public List<OsUserModel> queryForList(OsUserModel user);
	public List<OsUserModel> showOsUserList(OsUserModel user);
	public OsUserModel queryForObj(OsUserModel user);
	public List<OsUserModel> unionOsUserList(OsUserModel user);
}
