package com.sitech.ssd.sc.ostempl.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroupUser;

/**
 * 
 * @ClassName: OsTemplateGroupUserDaoImpl
 * @Description: OS安装模版 -- 组与用户
 * @author JamTau
 * @date 2014-8-20 下午7:07:33
 *
 */
@Repository("osTemplateGroupUserDao")
public class OsTemplateGroupUserDaoImpl extends BaseDao implements
		OsTemplateGroupUserDao {

	@Override
	public int insertOsTemplateGroupUser(OsTemplateGroupUser obj) {
		int _ret = 0;
		
		try {
			getSqlMap().insert("OsTemplateGroupUser.insertOsTemplateGroupUser", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateGroupUser.insertOsTemplateGroupUser:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int copyOsTemplateGroupUser(OsTemplateGroupUser obj) {
		int _ret = 0;
		
		try {
			getSqlMap().insert("OsTemplateGroupUser.copyOsTemplateGroupUser", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateGroupUser.copyOsTemplateGroupUser:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int deleteOsTemplateGroupUser(OsTemplateGroupUser obj) {
		int _ret = 0;
		
		try {
			getSqlMap().delete("OsTemplateGroupUser.deleteOsTemplateGroupUser", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateGroupUser.deleteOsTemplateGroupUser:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int updateOsTemplateGroupUser(OsTemplateGroupUser obj) {
		int _ret = 0;
		
		try {
			getSqlMap().update("OsTemplateGroupUser.updateOsTemplateGroupUser", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateGroupUser.updateOsTemplateGroupUser:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public OsTemplateGroupUser selectOsTemplateGroupUser(OsTemplateGroupUser obj) {
		Object ret = null;
		try {
			ret = getSqlMap().queryForObject(
					"OsTemplateGroupUser.selectOsTemplateGroupUser", obj);
		} catch (SQLException e) {
			LogHelper.error("OsTemplateGroupUser.selectOsTemplateGroupUser:"
					+ e.getMessage() + getClass().getName());
		}
		return ret==null ? new OsTemplateGroupUser() : (OsTemplateGroupUser)ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsTemplateGroupUser> selectOsTemplateGroupUserList(
			OsTemplateGroupUser obj) {
		List<OsTemplateGroupUser> _list = null;
		try {
			_list = getSqlMap().queryForList(
					"OsTemplateGroupUser.selectOsTemplateGroupUserList", obj);
		} catch (SQLException e) {
			LogHelper.error("OsTemplateGroupUser.selectOsTemplateGroupUserList:"
					+ e.getMessage() + getClass().getName());
		}
		return _list;
	}

}
