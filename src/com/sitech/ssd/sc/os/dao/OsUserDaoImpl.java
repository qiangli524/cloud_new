package com.sitech.ssd.sc.os.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.os.domain.OsUserModel;
/**
 * @ClassName OsUserDaoImpl
 * @Desc 
 * @Author JamTau
 * @date May 21, 2014 6:59:37 PM
 */
@Repository("osUserDao")
public class OsUserDaoImpl extends BaseDao implements OsUserDao {

	@Override
	public int insertUser(OsUserModel user) {
		int ret = 0;
		try {
			getSqlMap().insert("OsUser.insertUser",user);
		} catch (SQLException e) {
			ret = -1;
			LogHelper.error("OsUser.insertUser:" + e.getMessage() + 
					getClass().getName());
		}
		return ret;
	}

	@Override
	public int copyOsTemplateUser(OsUserModel user) {
		int ret = 0;
		try {
			getSqlMap().insert("OsUser.copyOsTemplateUser",user);
		} catch (SQLException e) {
			ret = -1;
			LogHelper.error("OsUser.copyOsTemplateUser:" + e.getMessage() + 
					getClass().getName());
		}
		return ret;
	}

	@Override
	public int deleteUser(OsUserModel user) {
		int ret = 0;
		try {
			ret = getSqlMap().delete("OsUser.deleteUser",user);
		} catch (SQLException e) {
			ret = -1;
			LogHelper.error("OsUser.deleteUser:" + e.getMessage() + 
					getClass().getName());
		}
		return ret;
	}

	@Override
	public int updateUser(OsUserModel user) {
		int ret = 0;
		try {
			ret = getSqlMap().update("OsUser.updateUser",user);
		} catch (SQLException e) {
			ret = -1;
			LogHelper.error("OsUser.updateUser:" + e.getMessage() + 
					getClass().getName());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsUserModel> queryForList(OsUserModel user) {
		List<OsUserModel> list = null;
		try {
			list = getSqlMap().queryForList("OsUser.queryForList", user);
		} catch (SQLException e) {
			LogHelper.error("OsUser.queryForList:" + e.getMessage() + 
					getClass().getName());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsUserModel> showOsUserList(OsUserModel user){
		List<OsUserModel> list = null;
		try {
			list = getSqlMap().queryForList("OsUser.showOsUserList", user);
		} catch (SQLException e) {
			LogHelper.error("OsUser.showOsUserList:" + e.getMessage() + 
					getClass().getName());
		}
		return list;
	}
	
	
	@Override
	public OsUserModel queryForObj(OsUserModel user) {
		Object obj = null;
		try {
			obj = getSqlMap().queryForObject("OsUser.queryForObj",user);
		} catch (SQLException e) {
			LogHelper.error("OsUser.queryForObj:" + e.getMessage() + 
					getClass().getName());
		}
		return obj==null?new OsUserModel():(OsUserModel)obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsUserModel> unionOsUserList(OsUserModel user){
		List<OsUserModel> list = null;
		try {
			list = getSqlMap().queryForList("OsUser.unionOsUserList", user);
		} catch (SQLException e) {
			LogHelper.error("OsUser.unionOsUserList:" + e.getMessage() + 
					getClass().getName());
		}
		return list;
	}
}
