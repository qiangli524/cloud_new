package com.sitech.ssd.sc.os.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.sc.os.dao.OsUserDao;
import com.sitech.ssd.sc.os.domain.OsUserModel;
import com.sitech.utils.randomid.RandomUUID;

@Service("osUserService")
public class OsUserServiceImpl implements OsUserService {

	@Autowired
	private OsUserDao osUserDao;
	
	@Override
	public int addOsUser(OsUserModel user) {
		user.setId(RandomUUID.getUuid());
		return osUserDao.insertUser(user);
	}

	@Override
	public int copyOsTemplateUser(OsUserModel user) {
		return osUserDao.copyOsTemplateUser(user);
	}

	@Override
	public int batchAddOsUser(List<OsUserModel> list) {
		return 0;
	}

	@Override
	public int deleteOsUser(OsUserModel user) {
		return osUserDao.deleteUser(user);
	}

	@Override
	public int modifyOsUser(OsUserModel user) {
		return osUserDao.updateUser(user);
	}

	@Override
	public List<OsUserModel> queryForList(OsUserModel user) {
		return osUserDao.queryForList(user);
	}
	
	@Override
	public List<OsUserModel> showOsUserList(OsUserModel user) {
		return osUserDao.showOsUserList(user);
	}

	@Override
	public OsUserModel queryOsUser(OsUserModel user) {
		OsUserModel _ret = null;
//		if(!"".equals(user.getOs_host_id())&&
//				!"".equals(user.getUser_name())){
//			List<OsUserModel> _temp = osUserDao.queryForList(user);
//			if(_temp.size()>0){
//				_retModel = _temp.get(0);
//			}
//		}		
		if(!"".equals(user.getId())){
			user.setUser_name("");
			List<OsUserModel> _temp = osUserDao.queryForList(user);
			if(_temp.size()>0){
				_ret = _temp.get(0);
			}
		}
		return _ret==null? new OsUserModel() : _ret;
	}

	@Override
	public List<OsUserModel> unionOsUserList(OsUserModel user) {
		return osUserDao.unionOsUserList(user);
	}
	
	
}
