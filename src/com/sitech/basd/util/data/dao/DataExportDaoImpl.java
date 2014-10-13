package com.sitech.basd.util.data.dao;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;


public class DataExportDaoImpl extends BaseDao implements DataExportDao{
	
	public List readTableData(String sql) throws SQLException {
		return getSqlMap().queryForList("Data.queryForExport", sql);
	}
}
