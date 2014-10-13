package com.sitech.basd.envmanager.util.device;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DeviceOraCon {

	private String URL = "";
	private String USERNAME = "";
	private String PASSWORD = "";
	private String TNAME = "";

	public DeviceOraCon(String url, String uname, String pwd, String tname) {
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

	public ArrayList getResult() {
		Connection conn = getCon();
		DeviceExcelObject eobj = null;
		ArrayList lobj = new ArrayList();
		if (conn == null)
			return lobj;
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.createStatement();
			String sql = "select * from "
					+ TNAME
					+ " order by capital_type,USE_RES,mech_id,capital_id,mech_type,mech_conf,sys_system,ip_physics,IP_VIRTUAL,ip_ilo,store,use_doman,use_res";
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				eobj = new DeviceExcelObject();
				eobj.setID(rs.getInt("LD_ID"));
				eobj.setMECH_ROOM(rs.getString("MECH_ROOM"));
				eobj.setCAPITAL_TYPE(rs.getString("CAPITAL_TYPE"));
				eobj.setMECH_ID(rs.getString("MECH_ID"));
				eobj.setCAPITAL_ID(rs.getString("CAPITAL_ID"));
				eobj.setMECH_TYPE(rs.getString("MECH_TYPE"));
				eobj.setMECH_CONF(rs.getString("MECH_CONF"));
				eobj.setSYS_SYSTEM(rs.getString("SYS_SYSTEM"));
				eobj.setSYS_HOSTNAME(rs.getString("SYS_HOSTNAME"));
				eobj.setSYS_VM(rs.getString("SYS_VM"));
				eobj.setIP_PHYSICS(rs.getString("IP_PHYSICS"));
				eobj.setIP_VIRTUAL(rs.getString("IP_VIRTUAL"));
				eobj.setIP_ILO(rs.getString("IP_ILO"));
				eobj.setPWD_SYSTEM(rs.getString("PWD_SYSTEM"));
				eobj.setPWD_CONSOLE(rs.getString("PWD_CONSOLE"));
				eobj.setSTORE(rs.getString("STORE"));
				eobj.setUSE_DOMAN(rs.getString("USE_DOMAN"));
				eobj.setUSE_RES(rs.getString("USE_RES"));
				eobj.setUSE_DEPARTMENT(rs.getString("USE_DEPART"));
				eobj.setUSE_DESCRIP(rs.getString("USE_DESCRIP"));
				eobj.setMANAG_PERSON(rs.getString("MANAG_PERSON"));
				eobj.setMANAG_RECORD(rs.getString("MANAG_RECORD"));
				eobj.setMANAG_REPAIR(rs.getString("MANAG_REPAIR"));
				eobj.setMANAG_USE(rs.getString("MANAG_USE"));
				eobj.setMANAG_USABLE(rs.getString("MANAG_USABLE"));
				eobj.setMANAG_DESCRIP(rs.getString("MANAG_DESCRIP"));

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
}
