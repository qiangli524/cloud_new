package com.sitech.ssd.sc.ostempl.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.ostempl.domain.OsTemplatePart;

@Repository("osTemplatePartDao")
public class OsTemplatePartDaoImpl extends BaseDao implements OsTemplatePartDao {
	 
	@Override
	public int insertOsTemplatePart(OsTemplatePart obj) {
		int _ret = 0;
		
		try {
			getSqlMap().insert("OsTemplatePart.insertOsTemplatePart", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplatePart.insertOsTemplatePart:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int copyOsTemplatePart(OsTemplatePart obj) {
		int _ret = 0;
		
		try {
			getSqlMap().insert("OsTemplatePart.copyOsTemplatePart", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplatePart.copyOsTemplatePart:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int deleteOsTemplatePart(OsTemplatePart obj) {
		int _ret = 0;
		
		try {
			getSqlMap().delete("OsTemplatePart.deleteOsTemplatePart", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplatePart.deleteOsTemplatePart:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int updateOsTemplatePart(OsTemplatePart obj) {
		int _ret = 0;
		
		try {
			getSqlMap().update("OsTemplatePart.updateOsTemplatePart", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplatePart.updateOsTemplatePart:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public OsTemplatePart selectOsTemplatePart(OsTemplatePart obj) {
		Object ret = null;
		try {
			ret = getSqlMap().queryForObject(
					"OsTemplatePart.selectOsTemplatePart", obj);
		} catch (SQLException e) {
			LogHelper.error("OsTemplatePart.selectOsTemplatePart:"
					+ e.getMessage() + getClass().getName());
		}
		return ret==null ? new OsTemplatePart() : (OsTemplatePart)ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsTemplatePart> selectOsTemplatePartList(OsTemplatePart obj) {
		List<OsTemplatePart> _list = null;
		try {
			_list = getSqlMap().queryForList(
					"OsTemplatePart.selectOsTemplatePartList", obj);
		} catch (SQLException e) {
			LogHelper.error("OsTemplatePart.selectOsTemplatePartList:"
					+ e.getMessage() + getClass().getName());
		}
		return _list;
	}

}
