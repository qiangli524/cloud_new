package com.sitech.ssd.sc.ostempl.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.ostempl.domain.OsTemplate;

/**
 * 
 * @ClassName: OsTemplateDaoImpl
 * @Description: OS安装模版 DAO实现类
 * @author JamTau
 * @date 2014-8-20 下午7:02:38
 *
 */
@Repository("osTemplateDao")
public class OsTemplateDaoImpl extends BaseDao implements OsTemplateDao {

	@Override
	public int insertOsTemplate(OsTemplate obj) {
		int _ret = 0;
		
		try {
			getSqlMap().insert("OsTemplate.insertOsTemplate", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplate.insertOsTemplate:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int deleteOsTemplate(OsTemplate obj) {
		int _ret = 0;
		
		try {
			getSqlMap().delete("OsTemplate.deleteOsTemplate", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplate.deleteOsTemplate:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public int updateOsTemplate(OsTemplate obj) {
		int _ret = 0;
		
		try {
			getSqlMap().update("OsTemplate.updateOsTemplate", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsTemplate.updateOsTemplate:" 
					+ e.getMessage() + getClass().getName());
		}
		
		return _ret;
	}

	@Override
	public OsTemplate selectOsTemplate(OsTemplate obj) {
		Object ret = null;
		try {
			ret = getSqlMap().queryForObject(
					"OsTemplate.selectOsTemplate", obj);
		} catch (SQLException e) {
			LogHelper.error("OsTemplate.selectOsTemplate:"
					+ e.getMessage() + getClass().getName());
		}
		return ret == null ? new OsTemplate() : (OsTemplate)ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsTemplate> selectOsTemplateList(OsTemplate obj) {
		List<OsTemplate> _list = null;
		try {
			_list = getSqlMap().queryForList(
					"OsTemplate.selectOsTemplateList", obj);
		} catch (SQLException e) {
			LogHelper.error("OsTemplate.selectOsTemplateList:"
					+ e.getMessage() + getClass().getName());
		}
		return _list;
	}

}
