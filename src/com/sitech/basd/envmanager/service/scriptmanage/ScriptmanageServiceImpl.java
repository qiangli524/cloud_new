package com.sitech.basd.envmanager.service.scriptmanage;

import java.util.List;

import com.sitech.basd.envmanager.dao.scriptmanage.ScriptmanageDao;
import com.sitech.basd.envmanager.domain.performance.PerformanceObj;
import com.sitech.basd.envmanager.domain.scriptmanage.ScriptmanageObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class ScriptmanageServiceImpl  extends BaseService implements ScriptmanageService{

	private ScriptmanageDao scriptmanageDao;
	
	/**
	 * @Title:删除主机脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int deleteScriptmanageObj(ScriptmanageObj obj) {
		// TODO Auto-generated method stub
		return scriptmanageDao.deleteScriptmanageObj(obj);
	}

	/**
	 * @Title:增加主机脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int insertScriptmanageObj(ScriptmanageObj obj) {
		// TODO Auto-generated method stub
		return scriptmanageDao.insertScriptmanageObj(obj);
	}

	/**
	 * @Title:查询主机脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public List queryScriptmanageObj(ScriptmanageObj obj) {
		// TODO Auto-generated method stub
		return scriptmanageDao.queryScriptmanageObj(obj);
	}

	/**
	 * @Title:查询某一个主机脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public ScriptmanageObj queryScriptmanageOne(ScriptmanageObj obj) {
		// TODO Auto-generated method stub
		return scriptmanageDao.queryScriptmanageOne(obj);
	}

	/**
	 * @Title:修改主机脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int updateScriptmanageObj(ScriptmanageObj obj) {
		// TODO Auto-generated method stub
		return scriptmanageDao.updateScriptmanageObj(obj);
	}
	
	/**
	 * @Title:查询主机IP 
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public List queryHostipObj(ScriptmanageObj obj) {
		// TODO Auto-generated method stub
		return scriptmanageDao.queryHostipObj(obj);
	}
	/**
	 * @Title:查询使用用户
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public List queryConfigObj(ScriptmanageObj obj) {
		return scriptmanageDao.queryConfigObj(obj);
	}
	
	@Override
	public int insertHostipObj(ScriptmanageObj obj) {
		// TODO Auto-generated method stub
		return scriptmanageDao.insertHostipObj(obj);
	}
	
	@Override
	public int getConfigId() {
		// TODO Auto-generated method stub
		return scriptmanageDao.getConfigId();
	}
	
	@Override
	public int insertByConfigObj(ScriptmanageObj obj) {
		// TODO Auto-generated method stub
		return scriptmanageDao.insertByConfigObj(obj);
	}

	@Override
	public ScriptmanageObj queryHostipOne(ScriptmanageObj obj) {
		// TODO Auto-generated method stub
		return scriptmanageDao.queryHostipOne(obj);
	}
	

	@Override
	public ScriptmanageObj queryHostNameOne(ScriptmanageObj obj) {
		// TODO Auto-generated method stub
		return scriptmanageDao.queryHostNameOne(obj);
	}
	
	public ScriptmanageDao getScriptmanageDao() {
		return scriptmanageDao;
	}

	public void setScriptmanageDao(ScriptmanageDao scriptmanageDao) {
		this.scriptmanageDao = scriptmanageDao;
	}


	


	



	
	

}
