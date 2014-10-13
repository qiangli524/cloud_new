package com.sitech.ssd.sc.os.dao;

import java.util.List;

import com.sitech.ssd.sc.os.domain.OsGroupUserModel;

public interface OsGroupUserDao {
	
	
	public int insertOsGroupUser(OsGroupUserModel model);
	
	public int copyOsTemplateGroupUser(OsGroupUserModel model);
	
	public int deleteOsGroupUser(OsGroupUserModel model);
	
	public int updateOsGroupUser(OsGroupUserModel model);
	
	public OsGroupUserModel selectOsGroupUser(OsGroupUserModel model);
	
	public List<OsGroupUserModel> selectOsGroupUserList(OsGroupUserModel model);

	public List<OsGroupUserModel> showOsGroupUserList(OsGroupUserModel model);
	
	public List<OsGroupUserModel> unionOsGroupUserList(OsGroupUserModel model);
	
}
