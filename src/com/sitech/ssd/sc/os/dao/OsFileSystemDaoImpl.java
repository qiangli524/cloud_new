package com.sitech.ssd.sc.os.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.os.domain.OsFileSystemModel;

/**
 * @Title:
 * @Description:
 * 
 * @Copyight: Copyright (c) 2014
 * @Company: SI-Tech
 * @Author: JamTau
 * @Date 2014-7-9 下午06:18:31
 */
@Repository("osFileSystemDao")
public class OsFileSystemDaoImpl extends BaseDao implements OsFileSystemDao {

	@Override
	public int insertOsFileSystem(OsFileSystemModel model) {
		int _ret = 0;
		try {
			getSqlMap().insert("OsFileSystem.insertOsFileSystem", model);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsFileSystem.insertOsFileSystem:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret;
	}

	
	@Override
	public int copyOsTemplateFileSystem(OsFileSystemModel model) {
		int _ret = 0;
		try {
			getSqlMap().insert("OsFileSystem.copyOsTemplateFileSystem", model);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsFileSystem.copyOsTemplateFileSystem:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret;
	}


	@Override
	public int deleteOsFileSystem(OsFileSystemModel model) {
		int _ret = 0;
		try {
			_ret = getSqlMap().delete("OsFileSystem.deleteOsFileSystem", model);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsFileSystem.deleteOsFileSystem:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret;
	}

	@Override
	public int updateOsFileSystem(OsFileSystemModel model) {
		int _ret = 0;
		try {
			_ret = getSqlMap().update("OsFileSystem.updateOsFileSystem", model);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsFileSystem.updateOsFileSystem:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret;
	}
	
	@Override
	public OsFileSystemModel selectOsFileSystem(OsFileSystemModel model){
		Object ret = null;
		try {
			ret = getSqlMap().queryForObject("OsFileSystem.selectOsFileSystem", model);
		} catch (SQLException e) {
			LogHelper.error("OsFileSystem.selectOsFileSystem:" + e.getMessage()
					+ getClass().getName());
			e.printStackTrace();
		}
		return ret == null ? new OsFileSystemModel() : (OsFileSystemModel)ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsFileSystemModel> selectOsFileSystemList(
			OsFileSystemModel model) {
		List<OsFileSystemModel> _list = null;
		try {
			_list = getSqlMap().queryForList(
					"OsFileSystem.selectOsFileSystemList", model);
		} catch (SQLException e) {
			LogHelper.error("OsFileSystem.selectOsFileSystemList:"
					+ e.getMessage() + getClass().getName());
		}
		return _list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsFileSystemModel> selectHomeDirList(OsFileSystemModel model) {
		List<OsFileSystemModel> _list = null;
		try {
			_list = getSqlMap().queryForList("OsFileSystem.selectHomeDirList",
					model);
		} catch (SQLException e) {
			LogHelper.error("OsFileSystem.selectHomeDirList:" + e.getMessage()
					+ getClass().getName());
		}
		return _list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsFileSystemModel> unionOsFileSystemList(
			OsFileSystemModel model) {
		List<OsFileSystemModel> _list = null;
		try {
			_list = getSqlMap().queryForList(
					"OsFileSystem.unionOsFileSystemList", model);
		} catch (SQLException e) {
			LogHelper.error("OsFileSystem.unionOsFileSystemList:"
					+ e.getMessage() + getClass().getName());
		}
		return _list;
	}
	
}
