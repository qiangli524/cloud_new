package com.sitech.basd.envmanager.dao.configure;

import java.util.List;

import com.sitech.basd.envmanager.domain.configure.ConfigureObj;
import com.sitech.basd.envmanager.domain.version.VersionObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class ConfigureDaoImpl  extends BaseDao implements ConfigureDao{

	/**
	 * @Title:查询所有开发环境使用情况信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public List queryConfigureObj(ConfigureObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Configure.queryConfigureCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("Configure.queryConfigureObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Configure.queryConfigureObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	/**
	 * @Title:查某个开发环境使用情况信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public ConfigureObj queryConfigureOne(ConfigureObj obj) {
		ConfigureObj cObj=null;
		try {
			cObj= (ConfigureObj) getSqlMap().queryForObject("Configure.queryConfigureOne",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Configure.queryConfigureOne:" 
					+ sqlexception.getMessage() + getClass().getName());
		}
		return cObj;
	}
	/**
	 * @Title:删除开发环境使用情况信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int deleteConfigureObj(ConfigureObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Configure.deleteConfigureObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Configure.deleteConfigureObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	/**
	 * @Title:添加开发环境使用情况信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int insertConfigureObj(ConfigureObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Configure.insertConfigureObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Configure.insertConfigureObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	/**
	 * @Title:修改开发环境使用情况信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int updateConfigureObj(ConfigureObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Configure.updateConfigureObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Configure.updateConfigureObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

}
