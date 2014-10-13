package com.sitech.ssd.sc.os.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.ssd.sc.os.domain.HostModel;

/**
 * @ClassName OsHostDaoImpl
 * @Desc
 * @Author JamTau
 * @date May 21, 2014 6:57:55 PM
 */
@Repository("osHostDao")
public class OsHostDaoImpl extends BaseDao implements OsHostDao {

	@Override
	public int insertHost(HostModel host) {
		try {
			getSqlMap().startTransaction();
			String eq_id = queryEqId();
			host.setEq_id(eq_id);
			getSqlMap().insert("OsHost.insertHost", host);
			String memory = host.getMemory();
			String store = host.getStore();
			memory = String.valueOf((Integer.valueOf(memory) * 1024));
			store = String.valueOf((Integer.valueOf(store) * 1024));
			host.setMemory(memory);
			host.setStore(store);
			getSqlMap().insert("OsHost.insertCloud2HostInfo", host);
			eq_id = String.valueOf((Integer.valueOf(eq_id) + 1));
			host.setEq_id(eq_id);
			updateEqId(host);
			getSqlMap().commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				getSqlMap().endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public String queryEqId() {
		String eq_id = null;
		try {
			eq_id = (String) getSqlMapClient().queryForObject(
					"OsHost.selectEqId", "");
		} catch (SQLException sqlexception) {
			logger.error("OsInstall.selectEqId:" + sqlexception.getMessage()
					+ getClass().getName(), sqlexception);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eq_id;
	}

	public void updateEqId(HostModel host) {

		try {
			getSqlMapClient().update("OsHost.updateEqId", host);
		} catch (SQLException sqlexception) {
			logger.error("OsInstall.updateEqId:" + sqlexception.getMessage()
					+ getClass().getName(), sqlexception);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int deleteHost(HostModel host) {
		int ret = 0;
		try {
			// UserModel user = new UserModel();
			// user.setOs_host_id(host.getId());
			// PartModel part = new PartModel();
			// part.setOs_host_id(host.getId());
			getSqlMap().startTransaction();
			getSqlMap().delete("OsHost.deleteCloud2HostInfo", host);
			ret = getSqlMap().delete("OsHost.deleteHost", host);
			// getSqlMap().delete("OsPart.deletePart", part);
			// getSqlMap().insert("OsUser.deleteUser", user);
			getSqlMap().commitTransaction();
		} catch (SQLException e) {
			ret = -1;
			e.printStackTrace();
		} finally {
			try {
				getSqlMap().endTransaction();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}

	@Override
	public int updateOsHost(HostModel host) {
		int ret = 0;
		try {
			getSqlMap().update("OsHost.updateOsHost", host);
		} catch (SQLException e) {
			ret = -1;
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int updateInstallState(HostModel host){
		int ret = 0;
		try {
			ret = getSqlMap().update("OsHost.updateInstallState", host);
		} catch (SQLException e) {
			ret = -1;
			e.printStackTrace();
		}
		return ret;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HostModel> queryForList(HostModel host) {
		List<HostModel> list = null;
		try {
			if (host.getPagination() != null) {
				host.setFIRSTROWNUM(host.getPagination().getFirstRownum());
				host.setPAGESIZE(host.getPagination().getPageSize());
				int _total_cout = ((Integer) getSqlMap().queryForObject(
						"OsHost.queryForCount", host)).intValue();
				host.getPagination().setTotalCount(_total_cout);
			}
			list = getSqlMap().queryForList("OsHost.queryForList", host);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public HostModel queryForObject(HostModel host) {
		Object obj = null;
		try {
			obj = getSqlMap().queryForObject("OsHost.queryForObject", host);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj == null ? new HostModel() : (HostModel) obj;
	}
	
	@Override
	public Map<String,String> queryHostBuss(HostModel host){
		Object obj = null;
		try {
			obj = getSqlMap().queryForObject("OsHost.queryHostBuss", host);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj == null ? new HashMap<String,String>() : (HashMap<String,String>) obj;
	}
}
