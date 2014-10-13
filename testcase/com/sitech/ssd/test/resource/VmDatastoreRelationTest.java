package com.sitech.ssd.test.resource;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.sitech.basd.resource.dao.united.vm.VmDatastoreRelationDao;
import com.sitech.basd.resource.domain.united.vm.VmDatastoreRelationObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.util.AppContext;

public class VmDatastoreRelationTest {

	@Test
	public void testQueryForList() {
		VmDatastoreRelationDao vmDatastoreRelationDao = AppContext.getBean(
				"vmDatastoreRelationDao", VmDatastoreRelationDao.class);
		VmDatastoreRelationObj vo = new VmDatastoreRelationObj();
		// vo.setId("1");
		// vo.setVm_uuid("vm1");
		vo.setConnectid("VCENTER.m0");
		vo.setDatastore_uuid("datastore-11");
		// vo.setHost_uuid("h1");
		vo.setVtype("1");

		/*
		 * try { vmDatastoreRelationDao.insertByObj(vo); } catch (SQLException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		List<VMHostObj> list = null;
		try {
			list = vmDatastoreRelationDao.queryForList(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++)
			System.out.println(list.get(i).getVH_NAME());

	}

}
