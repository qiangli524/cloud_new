package com.sitech.basd.sxcloud.cloud.dao.globalconfig;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.UUIDGenerator;

@SuppressWarnings("all")
public class TbGlobalConfigDaoImpl extends BaseDao implements TbGlobalConfigDao {
	/**
	 * @Title:查询出全局配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryForListByObj(TbGlobalConfigObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("TbGlobalConfig.queryByObjForCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList("TbGlobalConfig.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbGlobalConfig.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询全局配置信息返回一个对象
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public TbGlobalConfigObj queryByObj(TbGlobalConfigObj obj) {
		List lst = null;
		TbGlobalConfigObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbGlobalConfigObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:添加全局配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(TbGlobalConfigObj obj) {
		UUIDGenerator uuid = new UUIDGenerator();
		obj.setID(uuid.getUUID());
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbGlobalConfig.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbGlobalConfig.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更新全局配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(TbGlobalConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbGlobalConfig.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbGlobalConfig.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除全局配置信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(TbGlobalConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbGlobalConfig.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbGlobalConfig.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	/**
	 * 
	 * @Title: 判断关键字是否重复
	 * @Copyright:Copyright (c) Aug 16, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int checkKeyExists(TbGlobalConfigObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().queryForObject("TbGlobalConfig.validateNameByEdit",obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("TbGlobalConfig.validateNameByEdit:"+e.getMessage()+e.getClass().getName());
		}
		return ret;
	}

	@Override
	public boolean checkUserContains(String username) {
		boolean result = false;
		try {
			Object temp = getSqlMap().queryForObject("TbGlobalConfig.validateUserAuth",username);
			Integer index = Integer.valueOf(temp.toString());
			result = (index>0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
