package com.sitech.basd.cloud3.dao.newui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sitech.basd.cloud3.domain.newui.HomePageSettingObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.UUIDGenerator;

/**
 * 
 * <p>
 * Title: NewUIFuncDaoImpl
 * </p>
 * <p>
 * Description: 新UI接口Dao
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Feb 28, 2013 5:46:16 PM
 * 
 */
public class NewUIFuncDaoImpl extends BaseDao implements NewUIFuncDao {
	/**
	 * 
	 * @Title: getNewUIFunc_new
	 * @Description: 查询用户自定义首页图标
	 * @param
	 * @return List<Map<String,String>>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 28, 2013 6:46:12 PM
	 */
	public List<Map<String, String>> getNewUIFunc_new(Map<String, String> params) {
		List<Map<String, String>> lst = null;
		try {
			lst = (List<Map<String, String>>) getSqlMap().queryForList(
					"NewUIFunc.getNewUIFunc_new", params);
		} catch (Exception sqlexception) {
			LogHelper.error("NewUIFunc.getNewUIFunc_new:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: getNewUIAllowAdd
	 * @Description: 获取当前用户允许添加的图标
	 * @param
	 * @return List<Map<String,String>>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 28, 2013 6:46:12 PM
	 */
	public List<Map<String, String>> getNewUIAllowAdd(Map<String, String> params) {
		List<Map<String, String>> lst = null;
		try {
			lst = (List<Map<String, String>>) getSqlMap().queryForList(
					"NewUIFunc.getNewUIAllowAdd", params);
		} catch (Exception sqlexception) {
			LogHelper.error("NewUIFunc.getNewUIAllowAdd:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: insertNewUIUserFunc
	 * @Description: 插入自定义用户图标
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 1, 2013 1:09:23 PM
	 */
	public int insertNewUIUserFunc(Map<String, String> params) {
		int i = -1;
		try {
			params.put("id", UUIDGenerator.getUUID());
			Object o = getSqlMap().insert("NewUIFunc.insertNewUIUserFunc",
					params);
			if (o != null) {
				i = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("NewUIFunc.insertNewUIUserFunc:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return i;
	}

	/**
	 * 
	 * @Title: deleteNewUIUserFunc
	 * @Description: 删除自定义用户图标
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 1, 2013 1:09:23 PM
	 */
	public int deleteNewUIUserFunc(Map<String, String> params) {
		int i = -1;
		try {
			Object o = getSqlMap().delete("NewUIFunc.deleteNewUIUserFunc",
					params);
			if (o != null) {
				i = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("NewUIFunc.deleteNewUIUserFunc:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return i;
	}

	/**
	 * 
	 * @Title: insertNewUIPicFunc
	 * @Description: 插入菜单图标
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 17, 2013 4:13:45 PM
	 */
	public int insertNewUIPicFunc(Map map) {
		int i = -1;
		try {
			map.put("id", UUIDGenerator.getUUID());
			Object o = getSqlMap().insert("NewUIFunc.insertNewUIPicFunc", map);
			if (o != null) {
				i = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("NewUIFunc.insertNewUIPicFunc:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return i;
	}

	/**
	 * 
	 * @Title: updateNewUIUserFunc
	 * @Description: 更新userFunc表
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 19, 2013 3:29:04 PM
	 */
	public int updateNewUIUserFunc(Map map) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("NewUIFunc.updateNewUIUserFunc", map);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("NewUIFunc.updateNewUIUserFunc:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateNewUIPicFunc
	 * @Description: 更新PicFunc表
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 19, 2013 3:29:38 PM
	 */
	public int updateNewUIPicFunc(Map map) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("NewUIFunc.updateNewUIPicFunc", map);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("NewUIFunc.updateNewUIPicFunc:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteNewUIPicFunc
	 * @Description: 删除一条PicFunc记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 2, 2013 11:23:57 AM
	 */
	public int deleteNewUIPicFunc(Map map) {
		int i = -1;
		try {
			Object o = getSqlMap().delete("NewUIFunc.deleteNewUIPicFunc", map);
			if (o != null) {
				i = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("NewUIFunc.deleteNewUIPicFunc:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return i;
	}

	@Override
	public List<HomePageSettingObj> getGroupHomePageSetting(Map<String, String> map) {
		List<HomePageSettingObj> result = new ArrayList<HomePageSettingObj>();
		try {
			result = getSqlMap().queryForList("NewUIFunc.queryHomePageSetting", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
