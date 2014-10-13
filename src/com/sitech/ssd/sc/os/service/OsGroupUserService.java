package com.sitech.ssd.sc.os.service;

import java.util.List;

import com.sitech.ssd.sc.os.domain.OsGroupUserModel;

public interface OsGroupUserService {
	
	public int addOsGroupUser(OsGroupUserModel model);	
	
	public int copyOsTemplateGroupUser(OsGroupUserModel model);
	
	public int deleteOsGroupUser(OsGroupUserModel model);
	
	public int modifyOsGroupUser(OsGroupUserModel model);
	
	public OsGroupUserModel queryOsGroupUser(OsGroupUserModel model);
	
	public List<OsGroupUserModel> queryOsGroupUserList(OsGroupUserModel model);
	
	public List<OsGroupUserModel> showOsGroupUserList(OsGroupUserModel model);

	public List<OsGroupUserModel> unionOsGroupUserList(OsGroupUserModel model);
}
