package com.sitech.ssd.sc.ostempl.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateUser;

/**
 * 
 * @ClassName: OsTemplateUserDaoImpl
 * @Description: OS安装模版 -- 用户
 * @author JamTau
 * @date 2014-8-20 下午7:08:48
 *
 */
@Repository("osTemplateUserDao")
public class OsTemplateUserDaoImpl extends BaseDao implements OsTemplateUserDao {

	@Override
	public int insertOsTemplateUser(OsTemplateUser obj) {
		int _ret = 0;
		
		try {
			getSqlMap().insert("OsTemplateUser.insertOsTemplateUser", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateUser.insertOsTemplateUser:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int copyOsTemplateUser(OsTemplateUser obj) {
		int _ret = 0;
		
		try {
			getSqlMap().insert("OsTemplateUser.copyOsTemplateUser", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateUser.copyOsTemplateUser:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int deleteOsTemplateUser(OsTemplateUser obj) {
		int _ret = 0;
		
		try {
			getSqlMap().delete("OsTemplateUser.deleteOsTemplateUser", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateUser.deleteOsTemplateUser:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int updateOsTemplateUser(OsTemplateUser obj) {
		int _ret = 0;
		
		try {
			getSqlMap().update("OsTemplateUser.updateOsTemplateUser", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateUser.updateOsTemplateUser:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public OsTemplateUser selectOsTemplateUser(OsTemplateUser obj) {
		Object ret = null;
		try {
			ret = getSqlMap().queryForObject(
					"OsTemplateUser.selectOsTemplateUser", obj);
		} catch (SQLException e) {
			LogHelper.error("OsTemplateUser.selectOsTemplateUser:"
					+ e.getMessage() + getClass().getName());
		}
		return ret==null ? new OsTemplateUser() : (OsTemplateUser)ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsTemplateUser> selectOsTemplateUserList(OsTemplateUser obj) {
		List<OsTemplateUser> _list = null;
		try {
			_list = getSqlMap().queryForList(
					"OsTemplateUser.selectOsTemplateUserList", obj);
		} catch (SQLException e) {
			LogHelper.error("OsTemplateUser.selectOsTemplateUserList:"
					+ e.getMessage() + getClass().getName());
		}
		return _list;
	}

}
