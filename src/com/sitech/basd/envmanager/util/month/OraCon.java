package com.sitech.basd.envmanager.util.month;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class OraCon {

	private String URL = "";
	private String USERNAME = "";
	private String PASSWORD = "";
	private String TNAME = "";

	public OraCon(String url, String uname, String pwd, String tname) {
		URL = url;
		USERNAME = uname;
		PASSWORD = pwd;
		TNAME = tname;
	}

	public Connection getCon() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public ArrayList getResult(String domainname) {
		Connection conn = getCon();
		ExcelObject eobj = null;
		ArrayList lobj = new ArrayList();
		if (conn == null)
			return lobj;
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.createStatement();
			String sql = "select * from "
					+ TNAME
					+ " where cf_domain='"
					+ domainname
					+ "' order by CF_SOURCE,CF_CLASS,CF_HOSTTYPE,CF_HOSTNUM,CF_DESCRIPTION,CF_SYSTEM,CF_HOSTNAME,CF_IP,CF_PRODUCT,CF_SID,CF_FILESYSNAM,CF_SID1";
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				eobj = new ExcelObject();
				eobj.setENV(rs.getInt("CF_ENV"));
				eobj.setDOMAIN(rs.getString("CF_DOMAIN"));
				eobj.setRESOURCE(rs.getString("CF_SOURCE"));
				eobj.setCLASS(rs.getString("CF_CLASS"));
				eobj.setHOSTTYPE(rs.getString("CF_HOSTTYPE"));
				eobj.setHOSTNUM(rs.getString("CF_HOSTNUM"));
				eobj.setDESCRIPTION(rs.getString("CF_DESCRIPTION"));
				eobj.setSYSTEM(rs.getString("CF_SYSTEM"));
				eobj.setHOSTNAME(rs.getString("CF_HOSTNAME"));
				eobj.setIP(rs.getString("CF_IP"));
				eobj.setPRODUCT(rs.getString("CF_PRODUCT"));
				eobj.setDEVEPROD(rs.getString("CF_DEVEPROD"));
				eobj.setSID(rs.getString("CF_SID"));
				eobj.setTABLESPACE(rs.getObject("CF_TABLESPACE"));
				eobj.setFILESYSNAM(rs.getString("CF_FILESYSNAM"));
				eobj.setFILEAPPNUM(rs.getObject("CF_FILEAPPNUM"));
				eobj.setCPUUSED(rs.getString("CF_CPUUSED"));
				eobj.setMEM(rs.getObject("CF_MEM"));
				eobj.setFILEUSERD(rs.getObject("CF_FILEUSERD"));
				eobj.setFILEUSEPER(rs.getString("CF_FILEUSEPER"));
				eobj.setSID1(rs.getString("CF_SID1"));
				eobj.setTABSPAUSED(rs.getObject("CF_TABSPAUSED"));
				eobj.setTABSPAUSEPER(rs.getString("CF_TABSPAUSEPER"));
				eobj.setSGA(rs.getString("CF_SGA"));
				eobj.setCPULEFT(rs.getString("CF_CPULEFT"));
				eobj.setMEMLEFT(rs.getString("CF_MEMLEFT"));
				eobj.setSTORAGE(rs.getString("CF_STORAGE"));
				eobj.setDATE(rs.getString("CF_DATE"));

				lobj.add(eobj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stat != null) {
					stat.close();
					stat = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lobj;
	}

	public ArrayList getSum() {
		Connection conn = getCon();
		SumObject sobj = null;
		ArrayList lobj = new ArrayList();
		if (conn == null)
			return lobj;
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.createStatement();
			StringBuffer sql = new StringBuffer("select a.*,b.cf_mem from ");
			sql
					.append("(select cf_product,cf_domain,sum(nvl(cf_apply,0)) cf_apply,sum(nvl(cf_used,0)) cf_used from (");
			sql
					.append(
							"(select cf_product,cf_domain,cf_fileappnum cf_apply,cf_fileuserd cf_used from ")
					.append(TNAME).append(" where cf_source='应用池') ");
			sql.append("union all ");
			sql
					.append(
							"(select cf_product,cf_domain,cf_tablespace cf_apply,cf_tabspaused cf_used from ")
					.append(TNAME).append(" where cf_source='数据池')");
			sql.append(") group by cf_product,cf_domain) a,");
			sql
					.append(
							"(select cf_product,cf_domain,round(sum(nvl(cf_mem,0))/1024,2) cf_mem from (select distinct cf_product,cf_domain,cf_ip,cf_mem from ")
					.append(TNAME)
					.append(") group by cf_product,cf_domain) b ");
			sql
					.append("where a.cf_product=b.cf_product and a.cf_domain=b.cf_domain order by a.cf_product,a.cf_domain");

			rs = stat.executeQuery(sql.toString());

			while (rs.next()) {
				sobj = new SumObject();
				sobj.setPRODUCT(rs.getString("CF_PRODUCT"));
				sobj.setDOMAIN(rs.getString("CF_DOMAIN"));
				sobj.setAPPLY(rs.getObject("CF_APPLY"));
				sobj.setUSED(rs.getObject("CF_USED"));
				sobj.setMEM(rs.getObject("CF_MEM"));
				lobj.add(sobj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stat != null) {
					stat.close();
					stat = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lobj;
	}
}
