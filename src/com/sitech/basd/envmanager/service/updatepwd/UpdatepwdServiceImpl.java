package com.sitech.basd.envmanager.service.updatepwd;

import java.util.List;

import com.sitech.basd.envmanager.dao.updatepwd.UpdatepwdDao;
import com.sitech.basd.envmanager.domain.updatepwd.UpdatepwdObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class UpdatepwdServiceImpl  extends BaseService implements UpdatepwdService{

	
	private UpdatepwdDao updatepwdDao;

	public UpdatepwdDao getUpdatepwdDao() {
		return updatepwdDao;
	}

	public void setUpdatepwdDao(UpdatepwdDao updatepwdDao) {
		this.updatepwdDao = updatepwdDao;
	}
	/**
	 * @Title:删除用户名密码信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int deletePwdObj(UpdatepwdObj obj) {
		// TODO Auto-generated method stub
		return updatepwdDao.deletePwdObj(obj);
	}
	/**
	 * @Title:添加用户名密码信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int insertPwdObj(UpdatepwdObj obj) {
		// TODO Auto-generated method stub
		return updatepwdDao.insertPwdObj(obj);
	}
	/**
	 * @Title:查询某个用户名密码信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public UpdatepwdObj queryPwdOne(UpdatepwdObj obj) {
		// TODO Auto-generated method stub
		return updatepwdDao.queryPwdOne(obj);
	}
	/**
	 * @Title:查询所有用户名密码信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public List queryUpdatepwdObj(UpdatepwdObj obj) {
		// TODO Auto-generated method stub
		return updatepwdDao.queryUpdatepwdObj(obj);
	}
	/**
	 * @Title:修改某个用户名密码信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int updatePwdObj(UpdatepwdObj obj) {
		// TODO Auto-generated method stub
		return updatepwdDao.updatePwdObj(obj);
	}
	
	
}
