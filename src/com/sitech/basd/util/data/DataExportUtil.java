package com.sitech.basd.util.data;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class DataExportUtil extends BaseDao {
	public List readTableData(String sql) throws SQLException {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Data.queryForExport", sql);
		} catch (SQLException sqlexception) {
			LogHelper.error(sql + sqlexception.getMessage());
		}
		return lst;

	}

	public static void main(String[] args) throws SQLException {
		DataExportUtil d = new DataExportUtil();
		List list = d.readTableData("select * from tb_cloud2_host_info");
	}
}
