package com.sitech.ssd.sc.os.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.ssd.sc.os.domain.AdapterModel;
import com.sitech.ssd.sc.os.domain.HostModel;
import com.sitech.utils.randomid.RandomUUID;

@Repository("osXlsSupportDao")
public class OsXlsSupportDaoImpl extends BaseDao implements OsXlsSupportDao {

	/**
	 * 把数据插入到HostModel表中
	 */
	@Override
	public String insert(List<HostModel> hostModels) {
		String result = "";
		// TODO Auto-generated method stub
		try {
			getSqlMap().startTransaction();
			if (hostModels != null && hostModels.size() > 0) {
				AdapterModel adapterObj = null;
				HostModel hostObj = null;
				HostModel queryObj = null;
//				UnitedTreeObj uniteTreeObj = new UnitedTreeObj();
				String eq_id = "";
//				UnitedTreeObj treeIdObj = (UnitedTreeObj) getSqlMap()
//						.queryForObject("OsHost.selectUnitTreesId", null);
//				String uniteTreeId = treeIdObj.getId();
				for (int i = 0; i < hostModels.size(); i++) {
					hostObj = hostModels.get(i);
					queryObj = (HostModel) getSqlMap().queryForObject(
							"OsHost.queryForListBySn", hostObj);
					if (queryObj == null) {
						if (hostObj.getEq_mac1() != null
								&& !"".equals(hostObj.getEq_mac1())) {
							adapterObj = new AdapterModel();
							adapterObj.setOs_host_id(hostObj.getId());
							adapterObj.setNic_order("eth0");
							adapterObj.setMac(hostObj.getEq_mac1());
							getSqlMap().insert("OsHost.insertHostAdapter",
									adapterObj);
						}
						if (hostObj.getEq_mac2() != null
								&& !"".equals(hostObj.getEq_mac2())) {
							adapterObj = new AdapterModel();
							adapterObj.setOs_host_id(hostObj.getId());
							adapterObj.setNic_order("eth1");
							adapterObj.setMac(hostObj.getEq_mac2());
							getSqlMap().insert("OsHost.insertHostAdapter",
									adapterObj);
						}
						if (hostObj.getEq_mac3() != null
								&& !"".equals(hostObj.getEq_mac3())) {
							adapterObj = new AdapterModel();
							adapterObj.setOs_host_id(hostObj.getId());
							adapterObj.setNic_order("eth2");
							adapterObj.setMac(hostObj.getEq_mac3());
							getSqlMap().insert("OsHost.insertHostAdapter",
									adapterObj);
						}
						if (hostObj.getEq_mac4() != null
								&& !"".equals(hostObj.getEq_mac4())) {
							adapterObj = new AdapterModel();
							adapterObj.setOs_host_id(hostObj.getId());
							adapterObj.setNic_order("eth3");
							adapterObj.setMac(hostObj.getEq_mac4());
							getSqlMap().insert("OsHost.insertHostAdapter",
									adapterObj);
						}
						// 获取设备编号
						eq_id = queryEqId();
						hostObj.setEq_id(eq_id);
						getSqlMap().insert("OsHost.insertXmlImportHost",
								hostObj);
						getSqlMap().insert("OsHost.insertCloud2HostInfo",
								hostObj);
//						uniteTreeObj.setId(RandomUUID.getUuid());
//						uniteTreeObj.setParent_id(uniteTreeId);
//						uniteTreeObj.setType("3");
//						uniteTreeObj.setVtype("7");
//						uniteTreeObj.setUuid(eq_id);
//						uniteTreeObj.setConnect_id(eq_id);
//						uniteTreeObj.setName("新设备(" + hostObj.getSerial_num()
//								+ ")");
//						getSqlMap().insert("OsHost.insertUnitTrees",
//								uniteTreeObj);
						// 设备编号+1
						eq_id = String.valueOf((Integer.valueOf(eq_id) + 1));
						hostObj.setEq_id(eq_id);
						updateEqId(hostObj);
					}
				}
				getSqlMap().commitTransaction();
			}
			result = "success";
		} catch (SQLException e) {
			result = "error";
			e.printStackTrace();
		} finally {
			try {
				getSqlMap().endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public String queryEqId() {
		String eq_id = null;
		try {
			eq_id = (String) getSqlMapClient().queryForObject(
					"OsHost.selectEqId", "");
		} catch (SQLException sqlexception) {
			logger.error("OsHost.selectEqId:" + sqlexception.getMessage()
					+ getClass().getName(), sqlexception);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eq_id;
	}

	public void updateEqId(HostModel HostModel) {

		try {
			getSqlMapClient().update("OsHost.updateEqId", HostModel);
		} catch (SQLException sqlexception) {
			logger.error("OsHost.updateEqId:" + sqlexception.getMessage()
					+ getClass().getName(), sqlexception);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
