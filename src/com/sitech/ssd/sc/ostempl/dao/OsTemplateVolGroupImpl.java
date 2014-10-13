package com.sitech.ssd.sc.ostempl.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateVolGroup;
@Repository("osTemplateVolGroupDao")
public class OsTemplateVolGroupImpl extends BaseDao implements
		OsTemplateVolGroupDao {

	@Override
	public int insertOsTemplateVolGroup(OsTemplateVolGroup obj) {
		int _ret = 0;
		
		try {
			getSqlMap().insert("OsTemplateVolGroup.insertOsTemplateVolGroup", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateVolGroup.insertOsTemplateVolGroup:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int copyOsTemplateVolGroup(OsTemplateVolGroup obj) {
		int _ret = 0;
		
		try {
			getSqlMap().insert("OsTemplateVolGroup.copyOsTemplateVolGroup", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateVolGroup.copyOsTemplateVolGroup:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int deleteOsTemplateVolGroup(OsTemplateVolGroup obj) {
		int _ret = 0;
		
		try {
			getSqlMap().delete("OsTemplateVolGroup.deleteOsTemplateVolGroup", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateVolGroup.deleteOsTemplateVolGroup:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int updateOsTemplateVolGroup(OsTemplateVolGroup obj) {
		int _ret = 0;
		
		try {
			getSqlMap().update("OsTemplateVolGroup.updateOsTemplateVolGroup", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateVolGroup.updateOsTemplateVolGroup:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public OsTemplateVolGroup selectOsTemplateVolGroup(OsTemplateVolGroup obj) {
		Object ret = null;
		try {
			ret = getSqlMap().queryForObject(
					"OsTemplateVolGroup.selectOsTemplateVolGroup", obj);
		} catch (SQLException e) {
			LogHelper.error("OsTemplateVolGroup.selectOsTemplateVolGroup:"
					+ e.getMessage() + getClass().getName());
		}
		return ret==null ? new OsTemplateVolGroup() : (OsTemplateVolGroup)ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsTemplateVolGroup> selectOsTemplateVolGroupList(
			OsTemplateVolGroup obj) {
		List<OsTemplateVolGroup> _list = null;
		try {
			_list = getSqlMap().queryForList(
					"OsTemplateVolGroup.selectOsTemplateVolGroup", obj);
		} catch (SQLException e) {
			LogHelper.error("OsTemplateVolGroup.selectOsTemplateVolGroup:"
					+ e.getMessage() + getClass().getName());
		}
		return _list;
	}

}
