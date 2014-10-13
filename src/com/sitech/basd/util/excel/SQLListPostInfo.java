package com.sitech.basd.util.excel;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.sitech.basd.sxcloud.cloud.domain.exportconfig.ExportConfigObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;

public class SQLListPostInfo extends BaseDao {

	private StringBuffer querySQL;
	// private StringBuffer oraQuerySQL;
	private StringBuffer countSQL;
	private int cols;
	private int pageSize;
	private List sqlPara = Collections.EMPTY_LIST;

	public SQLListPostInfo() {
	}

	public int getPageSize() {
		return pageSize;
	}

	public StringBuffer getQuerySQL() {
		return querySQL;
	}

	/**
	 * add by Mumu Lee at 20080218
	 * 
	 * @param key
	 * @param map
	 * @param sqlPara
	 */
	public SQLListPostInfo(String key, Map map, List sqlPara, int type) {

		String sql = getSqlString(key, type);

		querySQL = new StringBuffer();
		querySQL.append(sql);

		FilterTextManipulator filter = new FilterTextManipulator();
		filter.manipulate(querySQL, map, sqlPara);
		int pos1 = querySQL.toString().toLowerCase().indexOf("select");
		int pos2 = querySQL.toString().toLowerCase().indexOf("from");

		countSQL = new StringBuffer();

		countSQL.append(" select count(1) from (");
		countSQL.append(querySQL);
		countSQL.append(")");

		StringTokenizer st = new StringTokenizer((querySQL.substring(pos1 + 6, pos2)).replaceAll(
				"\\([^)]*\\)", "@"), ",");
		cols = st.countTokens();

		this.sqlPara = sqlPara;
	}

	public StringBuffer getOraQuerySQL(int start, int end) {
		StringBuffer oraQuerySQL = new StringBuffer();
		oraQuerySQL.append("select * from ( select rownum rnm, aa.* from (").append(querySQL)
				.append(" ) aa where rownum <= ").append(end).append("  ) where rnm >= ").append(
						start);
		// log.debug("oraQuerySQL===" + oraQuerySQL);
		return oraQuerySQL;
	}

	public StringBuffer getOraQuerySQL(long start, long end) {
		StringBuffer oraQuerySQL = new StringBuffer();
		oraQuerySQL.append("select * from ( select rownum rnm, aa.* from (").append(querySQL)
				.append(" ) aa) where rnm <= ").append(end).append("  and rnm > ").append(start);

		return oraQuerySQL;
	}

	public static String getSqlString(String key, int type) {
		String strSql = null;
		ExportConfigObj tempObj = new ExportConfigObj();
		ExportConfigObj greqobj = new ExportConfigObj();
		greqobj.setKEY(key);
		greqobj.setTYPE(type);
		try {
			tempObj = (ExportConfigObj) sqlMapClient.queryForObject(
					"ExportConfig.queryForObjByObj", greqobj);
			if (tempObj != null && tempObj.getTYPE() > 0) {
				strSql = tempObj.getVALUE();
			}

			/*
			 * conn = (Connection) getJDBCInstance();
			 * 
			 * conn = (Connection) getJDBCInstance(); sql = "select value from
			 * tb_cloud2_export_config a where a.key = ? and a.type = ?"; ps =
			 * conn.prepareStatement(sql); SqlParameter sp = null;
			 * ((PreparedStatement) sp).setString(1, key); ((PreparedStatement)
			 * sp).setLong(2, type);
			 * 
			 * rs = ps.executeQuery(); strSql = rs.getString(1);
			 */
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行DBUtil方法异常");
		}
		return strSql;
	}

	public List getSqlPara() {
		return sqlPara;
	}

	public void setSqlPara(List sqlPara) {
		this.sqlPara = sqlPara;
	}

	public void setQuerySQL(StringBuffer querySQL) {
		this.querySQL = querySQL;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}
}