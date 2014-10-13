package com.sitech.basd.envmanager.dao.scriptmanage;

import java.util.List;


import com.sitech.basd.envmanager.domain.scriptmanage.ScriptmanageObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class ScriptmanageDaoImpl extends BaseDao implements ScriptmanageDao{

	/**
	 * @Title:删除主机脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int deleteScriptmanageObj(ScriptmanageObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Scriptmanage.deleteScriptmanageObj", obj);
			if(o!=null){ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Scriptmanage.deleteScriptmanageObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:增加主机脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int insertScriptmanageObj(ScriptmanageObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Scriptmanage.insertScriptmanageObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Scriptmanage.insertScriptmanageObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询主机脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public List queryScriptmanageObj(ScriptmanageObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Scriptmanage.queryScriptmanageObjCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("Scriptmanage.queryScriptmanageObj", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("Scriptmanage.queryScriptmanageObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询某一个主机脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public ScriptmanageObj queryScriptmanageOne(ScriptmanageObj obj) {
		ScriptmanageObj sObj=null;
		try {
			sObj= (ScriptmanageObj) getSqlMap().queryForObject("Scriptmanage.queryScriptmanageOne",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Scriptmanage.queryScriptmanageOne:" 
					+ sqlexception.getMessage() + getClass().getName());
		}
		return sObj;
	}

	/**
	 * @Title:修改主机脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int updateScriptmanageObj(ScriptmanageObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Scriptmanage.updateScriptmanageObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Scriptmanage.updateScriptmanageObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@Override
	public List queryHostipObj(ScriptmanageObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Scriptmanage.queryHostipCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("Scriptmanage.queryHostipObj", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("Scriptmanage.queryHostipObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	@Override
	public List queryConfigObj(ScriptmanageObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Scriptmanage.queryConfigObj", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("Scriptmanage.queryConfigObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	@Override
	public int getConfigId() {
		int ret = 0;
		try {
			Object obj = getSqlMap().queryForObject(
					"Scriptmanage.getConfigId");
			if (null != obj) {
				ret = Integer.valueOf(obj.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("Scriptmanage.getConfigId:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@Override
	public int insertHostipObj(ScriptmanageObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Scriptmanage.insertHostipObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Scriptmanage.insertHostipObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@Override
	public int insertByConfigObj(ScriptmanageObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Scriptmanage.insertByConfigObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			ret = -1;
			LogHelper.error("Scriptmanage.insertByConfigObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@Override
	public ScriptmanageObj queryHostipOne(ScriptmanageObj obj) {
		ScriptmanageObj sObj=null;
		try {
			sObj= (ScriptmanageObj) getSqlMap().queryForObject("Scriptmanage.queryHostipOne",obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("Scriptmanage.queryHostipOne:" 
					+ sqlexception.getMessage() + getClass().getName());
		}
		return sObj;
	}

	@Override
	public ScriptmanageObj queryHostNameOne(ScriptmanageObj obj) {
		ScriptmanageObj sObj=null;
		try {
			sObj= (ScriptmanageObj) getSqlMap().queryForObject("Scriptmanage.queryHostNameOne",obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("Scriptmanage.queryHostNameOne:" 
					+ sqlexception.getMessage() + getClass().getName());
		}
		return sObj;
	}


}
