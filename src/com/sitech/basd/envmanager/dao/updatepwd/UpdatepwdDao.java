package com.sitech.basd.envmanager.dao.updatepwd;

import java.util.List;

import com.sitech.basd.envmanager.domain.updatepwd.UpdatepwdObj;

public interface UpdatepwdDao {
	
	/**
	 * @Title:查询所有用户名密码信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public List queryUpdatepwdObj(UpdatepwdObj obj);
	/**
	 * @Title:查询某个用户名密码信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public UpdatepwdObj queryPwdOne(UpdatepwdObj obj);
	/**
	 * @Title:添加用户名密码信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int insertPwdObj(UpdatepwdObj obj);
	/**
	 * @Title:修改用户名密码信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int updatePwdObj(UpdatepwdObj obj);
	/**
	 * @Title:删除用户名密码信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int deletePwdObj(UpdatepwdObj obj);



}
