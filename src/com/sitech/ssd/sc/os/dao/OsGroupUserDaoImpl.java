package com.sitech.ssd.sc.os.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.os.domain.OsGroupUserModel;

@Repository("osGroupUserDao")
public class OsGroupUserDaoImpl extends BaseDao implements OsGroupUserDao {

	@Override
	public int insertOsGroupUser(OsGroupUserModel model) {
		int _ret = 0;
		try {
			getSqlMap().insert("OsGroupUser.insertOsGroupUser", model);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsGroupUser.insertOsGroupUser:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret;
	}

	@Override
	public int copyOsTemplateGroupUser(OsGroupUserModel model) {
		int _ret = 0;
		try {
			getSqlMap().insert("OsGroupUser.copyOsTemplateGroupUser", model);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsGroupUser.copyOsTemplateGroupUser:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret;
	}

	@Override
	public int deleteOsGroupUser(OsGroupUserModel model) {
		int _ret = 0;
		try {
			_ret = getSqlMap().delete("OsGroupUser.deleteOsGroupUser", model);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsGroupUser.deleteOsGroupUser:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret;
	}

	@Override
	public int updateOsGroupUser(OsGroupUserModel model) {
		int _ret = 0;
		try {
			_ret = getSqlMap().update("OsGroupUser.updateOsGroupUser", model);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsGroupUser.updateOsGroupUser:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret;
	}

	@Override
	public OsGroupUserModel selectOsGroupUser(OsGroupUserModel model) {
		Object _ret = null;
		try {
			_ret = getSqlMap().queryForObject("OsGroupUser.selectOsGroupUser", model);
		} catch (SQLException e) {
			LogHelper.error("OsGroupUser.selectOsGroupUser:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret==null ? new OsGroupUserModel() : (OsGroupUserModel)_ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsGroupUserModel> selectOsGroupUserList(OsGroupUserModel model) {
		List<OsGroupUserModel> _list = null;
		try {
			_list = getSqlMap().queryForList("OsGroupUser.selectOsGroupUserList", model);
		} catch (SQLException e) {
			LogHelper.error("OsGroupUser.selectOsGroupUserList:" + e.getMessage()
					+ getClass().getName());
		}
		return _list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OsGroupUserModel> showOsGroupUserList(OsGroupUserModel model){
		List<OsGroupUserModel> _list = null;
		try {
			_list = getSqlMap().queryForList("OsGroupUser.showOsGroupUserList", model);
		} catch (SQLException e) {
			LogHelper.error("OsGroupUser.showOsGroupUserList:" + e.getMessage()
					+ getClass().getName());
		}
		return _list;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<OsGroupUserModel> unionOsGroupUserList(OsGroupUserModel model) {
		List<OsGroupUserModel> _list = null;
		try {
			_list = getSqlMap().queryForList("OsGroupUser.unionOsGroupUserList", model);
		} catch (SQLException e) {
			LogHelper.error("OsGroupUser.unionOsGroupUserList:" + e.getMessage()
					+ getClass().getName());
		}
		return _list;
	}	

}
