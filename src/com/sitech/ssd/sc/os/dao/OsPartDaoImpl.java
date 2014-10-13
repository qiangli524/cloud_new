package com.sitech.ssd.sc.os.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.os.domain.OsPartModel;

@Repository("osPartDao")
public class OsPartDaoImpl extends BaseDao implements OsPartDao {

	@Override
	public int copyOsTemplatePart(OsPartModel part) {
		int _ret = 0;
		try {
			getSqlMap().insert("OsPart.copyOsTemplatePart",part);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsPart.copyOsTemplatePart:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret;
	}

	@Override
	public int deleteOsPart(OsPartModel part) {
		int _ret = 0;
		try {
			_ret = getSqlMap().delete("OsPart.deleteOsPart",part);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsPart.deleteOsPart:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsPartModel> selectOsPartList(OsPartModel part) {
		List<OsPartModel> _list = null;
		try {
			_list = getSqlMap().queryForList("OsPart.selectOsPartList", part);
		} catch (SQLException e) {
			LogHelper.error("OsPart.selectOsPartList:" + e.getMessage()
					+ getClass().getName());
		}
		return _list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OsPartModel> unionOsPartList(OsPartModel part) {
		List<OsPartModel> _list = null;
		try {
			_list = getSqlMap().queryForList("OsPart.unionOsPartList", part);
		} catch (SQLException e) {
			LogHelper.error("OsPart.unionOsPartList:" + e.getMessage()
					+ getClass().getName());
		}
		return _list;
	}	
}
