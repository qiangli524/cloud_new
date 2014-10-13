package com.sitech.basd.util.data.dao;

import java.sql.SQLException;
import java.util.List;

public interface DataExportDao {
	public List readTableData(String sql) throws SQLException;
}
