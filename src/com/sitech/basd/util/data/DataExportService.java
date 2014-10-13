package com.sitech.basd.util.data;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.util.data.dao.DataExportDao;;

public class DataExportService {
	
	private DataExportDao dataExportDao;
	
	public List readTableData(String sql) throws SQLException {
		List lst = null;
		try {
			lst = dataExportDao.readTableData(sql);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error(sql + sqlexception.getMessage());
		}
		return lst;
	}
	
	public void setDataExportDao(DataExportDao dataExportDao) {
		this.dataExportDao = dataExportDao;
	}
}
