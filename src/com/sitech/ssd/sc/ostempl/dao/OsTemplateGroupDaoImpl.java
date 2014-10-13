package com.sitech.ssd.sc.ostempl.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroup;

/**
 * 
 * @ClassName: OsTemplateGroupDaoImpl
 * @Description: OS安装模版 -- 用户组
 * @author JamTau
 * @date 2014-8-20 下午7:06:01
 *
 */
@Repository("osTemplateGroupDao")
public class OsTemplateGroupDaoImpl extends BaseDao implements
		OsTemplateGroupDao {

	@Override
	public int insertOsTemplateGroup(OsTemplateGroup obj) {
		int _ret = 0;
		
		try {
			getSqlMap().insert("OsTemplateGroup.insertOsTemplateGroup", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateGroup.insertOsTemplateGroup:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int copyOsTemplateGroup(OsTemplateGroup obj) {
		int _ret = 0;
		
		try {
			getSqlMap().insert("OsTemplateGroup.copyOsTemplateGroup", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateGroup.copyOsTemplateGroup:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int deleteOsTemplateGroup(OsTemplateGroup obj) {
		int _ret = 0;
		
		try {
			getSqlMap().delete("OsTemplateGroup.deleteOsTemplateGroup", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateGroup.deleteOsTemplateGroup:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int updateOsTemplateGroup(OsTemplateGroup obj) {
		int _ret = 0;
		
		try {
			getSqlMap().update("OsTemplateGroup.updateOsTemplateGroup", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateGroup.updateOsTemplateGroup:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public OsTemplateGroup selectOsTemplateGroup(OsTemplateGroup obj) {
		Object ret = null;
		try {
			ret = getSqlMap().queryForObject(
					"OsTemplateGroup.selectOsTemplateGroup", obj);
		} catch (SQLException e) {
			LogHelper.error("OsTemplateGroup.selectOsTemplateGroup:"
					+ e.getMessage() + getClass().getName());
		}
		return ret==null ? new OsTemplateGroup() : (OsTemplateGroup)ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsTemplateGroup> selectOsTemplateGroupList(OsTemplateGroup obj) {
		List<OsTemplateGroup> _list = null;
		try {
			_list = getSqlMap().queryForList(
					"OsTemplateGroup.selectOsTemplateGroupList", obj);
		} catch (SQLException e) {
			LogHelper.error("OsTemplateGroup.selectOsTemplateGroupList:"
					+ e.getMessage() + getClass().getName());
		}
		return _list;
	}

}
