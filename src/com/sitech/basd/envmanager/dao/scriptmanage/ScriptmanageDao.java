package com.sitech.basd.envmanager.dao.scriptmanage;

import java.util.List;

import com.sitech.basd.envmanager.domain.scriptmanage.ScriptmanageObj;

public interface ScriptmanageDao {
	/**
	 * @Title:查询使用用户
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public List queryConfigObj(ScriptmanageObj obj);
	/**
	 * @Title:查询主机IP 
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public List queryHostipObj(ScriptmanageObj obj);
	/**
	 * @Title:查询主机脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public List queryScriptmanageObj(ScriptmanageObj obj);
	
	/**
	 * @Title:查询某一个主机脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public ScriptmanageObj queryScriptmanageOne(ScriptmanageObj obj);
	
	/**
	 * @Title:增加主机脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int insertScriptmanageObj(ScriptmanageObj obj);
	
	/**
	 * @Title:修改主机脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int updateScriptmanageObj(ScriptmanageObj obj);
	
	/**
	 * @Title:删除主机脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int deleteScriptmanageObj(ScriptmanageObj obj);
	
	public int getConfigId();
	
	public int insertHostipObj(ScriptmanageObj obj);
	

	public int insertByConfigObj(ScriptmanageObj obj);
	
	/**
	 * @Title:查询某一个IP
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public ScriptmanageObj queryHostipOne(ScriptmanageObj obj);
	
	/**
	 * @Title:查询某一个用户
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public ScriptmanageObj queryHostNameOne(ScriptmanageObj obj);


}
