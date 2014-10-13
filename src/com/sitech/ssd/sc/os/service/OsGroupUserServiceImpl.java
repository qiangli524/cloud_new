package com.sitech.ssd.sc.os.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.ssd.sc.os.dao.OsGroupUserDao;
import com.sitech.ssd.sc.os.domain.OsGroupUserModel;

@Service("osGroupUserService")
public class OsGroupUserServiceImpl extends BaseDao implements
		OsGroupUserService {

	@Autowired
	private OsGroupUserDao osGroupUserDao;

	@Override
	public int addOsGroupUser(OsGroupUserModel model) {
		return osGroupUserDao.insertOsGroupUser(model);
	}

	@Override
	public int copyOsTemplateGroupUser(OsGroupUserModel model) {
		return osGroupUserDao.copyOsTemplateGroupUser(model);
	}

	@Override
	public int deleteOsGroupUser(OsGroupUserModel model) {
		return osGroupUserDao.deleteOsGroupUser(model);
	}

	@Override
	public int modifyOsGroupUser(OsGroupUserModel model) {
		return osGroupUserDao.updateOsGroupUser(model);
	}
	
	@Override
	public OsGroupUserModel queryOsGroupUser(OsGroupUserModel model) {
		return osGroupUserDao.selectOsGroupUser(model);
	}

	@Override
	public List<OsGroupUserModel> queryOsGroupUserList(OsGroupUserModel model) {
		return osGroupUserDao.selectOsGroupUserList(model);
	}
	
	@Override
	public List<OsGroupUserModel> showOsGroupUserList(OsGroupUserModel model){
		return osGroupUserDao.showOsGroupUserList(model);
	}

	@Override
	public List<OsGroupUserModel> unionOsGroupUserList(OsGroupUserModel model) {
		return osGroupUserDao.unionOsGroupUserList(model);
	}
	
}
