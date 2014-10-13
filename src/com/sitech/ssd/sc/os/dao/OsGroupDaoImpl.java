package com.sitech.ssd.sc.os.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.os.domain.OsGroupModel;

@Repository("osGroupDao")
public class OsGroupDaoImpl extends BaseDao implements OsGroupDao {

	@Override
	public int insertOsGroup(OsGroupModel model) {
		int _ret = 0;
		try {
			getSqlMap().insert("OsGroup.insertOsGroup", model);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsGroup.insertOsGroup:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret;
	}

	
	@Override
	public int copyOsTemplateGroup(OsGroupModel model) {
		int _ret = 0;
		try {
			getSqlMap().insert("OsGroup.copyOsTemplateGroup", model);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsGroup.copyOsTemplateGroup:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret;
	}


	@Override
	public int deleteOsGroup(OsGroupModel model) {
		int _ret = 0;
		try {
			_ret = getSqlMap().delete("OsGroup.deleteOsGroup", model);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsGroup.deleteOsGroup:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret;
	}

	@Override
	public int updateOsGroup(OsGroupModel model) {
		int _ret = 0;
		try {
			_ret = getSqlMap().update("OsGroup.updateOsGroup", model);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsGroup.updateOsGroup:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret;
	}
	@Override
	public OsGroupModel selectOsGroup(OsGroupModel model) { 
		Object _ret = null;
		try {
			_ret = getSqlMap().queryForObject("OsGroup.selectOsGroup", model);
		} catch (SQLException e) {
			LogHelper.error("OsGroup.selectOsGroup:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret==null ? new OsGroupModel() : (OsGroupModel)_ret ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OsGroupModel> selectOsGroupList(OsGroupModel model) {
		List<OsGroupModel> _list = null;
		try {
			_list = getSqlMap().queryForList("OsGroup.selectOsGroupList", model);
		} catch (SQLException e) {
			LogHelper.error("OsGroup.selectOsGroupList:" + e.getMessage()
					+ getClass().getName());
		}
		return _list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsGroupModel> unionOsGroupList(OsGroupModel model) {
		List<OsGroupModel> _list = null;
		try {
			_list = getSqlMap().queryForList("OsGroup.unionOsGroupList", model);
		} catch (SQLException e) {
			LogHelper.error("OsGroup.unionOsGroupList:" + e.getMessage()
					+ getClass().getName());
		}
		return _list;
	}	
	
}
