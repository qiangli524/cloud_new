package com.sitech.ssd.sc.ostempl.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateFileSystem;

/**
 * 
 * @ClassName: OsTemplateFileSystemDaoImpl
 * @Description: OS安装模版 -- 文件系统
 * @author JamTau
 * @date 2014-8-20 下午7:06:09
 *
 */
@Repository("osTemplateFileSystemDao")
public class OsTemplateFileSystemDaoImpl extends BaseDao implements
		OsTemplateFileSystemDao {

	@Override
	public int insertOsTemplateFileSystem(OsTemplateFileSystem obj) {
		int _ret = 0;
		
		try {
			getSqlMap().insert("OsTemplateFileSystem.insertOsTemplateFileSystem", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateFileSystem.insertOsTemplateFileSystem:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int copyOsTemplateFileSystem(OsTemplateFileSystem obj) {
		int _ret = 0;
		
		try {
			//id字段代替template_name
			getSqlMap().insert("OsTemplateFileSystem.copyOsTemplateFileSystem", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateFileSystem.copyOsTemplateFileSystem:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int deleteOsTemplateFileSystem(OsTemplateFileSystem obj) {
		int _ret = 0;
		
		try {
			getSqlMap().delete("OsTemplateFileSystem.deleteOsTemplateFileSystem", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateFileSystem.deleteOsTemplateFileSystem:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int updateOsTemplateFileSystem(OsTemplateFileSystem obj) {
		int _ret = 0;
		
		try {
			getSqlMap().update("OsTemplateFileSystem.updateOsTemplateFileSystem", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateFileSystem.updateOsTemplateFileSystem:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public OsTemplateFileSystem selectOsTemplateFileSystem(
			OsTemplateFileSystem obj) {
		Object ret = null;
		try {
			ret = getSqlMap().queryForObject(
					"OsTemplateFileSystem.selectOsTemplateFileSystem", obj);
		} catch (SQLException e) {
			LogHelper.error("OsTemplateFileSystem.selectOsTemplateFileSystem:"
					+ e.getMessage() + getClass().getName());
		}
		return ret==null ? new OsTemplateFileSystem() : (OsTemplateFileSystem)ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsTemplateFileSystem> selectOsTemplateFileSystemList(
			OsTemplateFileSystem obj) {
		List<OsTemplateFileSystem> _list = null;
		try {
			_list = getSqlMap().queryForList(
					"OsTemplateFileSystem.selectOsTemplateFileSystemList", obj);
		} catch (SQLException e) {
			LogHelper.error("OsTemplateFileSystem.selectOsTemplateFileSystemList:"
					+ e.getMessage() + getClass().getName());
		}
		return _list;
	}

}
