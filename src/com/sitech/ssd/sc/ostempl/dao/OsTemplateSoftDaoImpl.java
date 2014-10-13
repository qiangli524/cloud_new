package com.sitech.ssd.sc.ostempl.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateSoft;

/**
 * 
 * @ClassName: OsTemplateSoftDaoImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author JamTau
 * @date 2014-9-8 下午3:26:22
 *
 */
@Repository("osTemplateSoftDao")
public class OsTemplateSoftDaoImpl extends BaseDao implements OsTemplateSoftDao {

	@Override
	public int insertOsTemplateSoft(OsTemplateSoft obj) {
		int _ret = 0;
		
		try {
			getSqlMap().insert("OsTemplateSoft.insertOsTemplateSoft", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateSoft.insertOsTemplateSoft:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int copyOsTemplateSoft(OsTemplateSoft obj) {
		int _ret = 0;
		
		try {
			getSqlMap().insert("OsTemplateSoft.copyOsTemplateSoft", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateSoft.copyOsTemplateSoft:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int deleteOsTemplateSoft(OsTemplateSoft obj) {
		int _ret = 0;
		
		try {
			getSqlMap().delete("OsTemplateSoft.deleteOsTemplateSoft", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateSoft.deleteOsTemplateSoft:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int updateOsTemplateSoft(OsTemplateSoft obj) {
		int _ret = 0;
		
		try {
			getSqlMap().update("OsTemplateSoft.updateOsTemplateSoft", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplateSoft.updateOsTemplateSoft:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public OsTemplateSoft selectOsTemplateSoft(OsTemplateSoft obj) {
		Object ret = null;
		try {
			ret = getSqlMap().queryForObject(
					"OsTemplateSoft.selectOsTemplateSoft", obj);
		} catch (SQLException e) {
			LogHelper.error("OsTemplateSoft.selectOsTemplateSoft:"
					+ e.getMessage() + getClass().getName());
		}
		return ret==null ? new OsTemplateSoft() : (OsTemplateSoft)ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsTemplateSoft> selectOsTemplateSoftList(OsTemplateSoft obj) {
		List<OsTemplateSoft> _list = null;
		try {
			_list = getSqlMap().queryForList(
					"OsTemplateSoft.selectOsTemplateSoftList", obj);
		} catch (SQLException e) {
			LogHelper.error("OsTemplateSoft.selectOsTemplateSoftList:"
					+ e.getMessage() + getClass().getName());
		}
		return _list;
	}
}
