package com.sitech.ssd.sc.os.service;

import java.util.List;

import com.sitech.ssd.sc.os.domain.OsUserModel;

/**
 * @ClassName OsUserService
 * @Desc 
 * @Author JamTau
 * @date May 23, 2014 3:51:46 PM
 */
public interface OsUserService {
	
	public int addOsUser(OsUserModel user);	
	
	public int copyOsTemplateUser(OsUserModel user);	
	
	public int batchAddOsUser(List<OsUserModel> list);
	
	public int modifyOsUser(OsUserModel user);
	
	public int deleteOsUser(OsUserModel user);
	
	public OsUserModel queryOsUser(OsUserModel user);
	
	public List<OsUserModel> queryForList(OsUserModel user);
	
	public List<OsUserModel> showOsUserList(OsUserModel user);
	
	public List<OsUserModel> unionOsUserList(OsUserModel user);
}
