package com.sitech.basd.resource.service.united.vm;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.united.vm.VmDatastoreRelationDao;
import com.sitech.basd.resource.domain.united.vm.VmDatastoreRelationObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;

@Service("vmDatastoreRelationService")
public class VmDatastoreRelationServiceImpl implements VmDatastoreRelationService {

	@Autowired
	private VmDatastoreRelationDao vmDatastoreRelationDao;

	private static final Logger log = LoggerFactory.getLogger(VmDatastoreRelationServiceImpl.class);

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询虚拟机实体列表（通过存储uuid，connectid,vtype 缺一不可）
	 * @param
	 * @return List<VmDatastoreRelationObj>
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2014-9-16 上午9:54:08
	 */
	public List<VMHostObj> queryForList(VmDatastoreRelationObj obj) {
		List<VMHostObj> list = null;

		if (obj.getConnectid() == null) {
			log.error("VmDatastoreRelationService.queryForList 缺少connectid");
		}
		if (obj.getVtype() == null) {
			log.error("VmDatastoreRelationService.queryForList 缺少vtype");
		}
		if (obj.getDatastore_uuid() == null) {
			log.error("VmDatastoreRelationService.queryForList 缺少存储uuid");
		}
		try {
			list = vmDatastoreRelationDao.queryForList(obj);
		} catch (SQLException e) {
			log.error("VmDatastoreRelationService.queryForList 查询虚拟机失败" + "存储uuid为："
					+ obj.getDatastore_uuid());
		}

		return list;
	}

	/**
	 * 
	 * @Title: queryDatastoreForList
	 * @Description: 查询存储实体列表（通过vmuuid，connectid,vtype 缺一不可）
	 * @param
	 * @return List<DataStoreForm>
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @createtime 2014-9-17 下午1:50:06
	 */
	public List<DataStoreObj> queryDatastoreForList(VmDatastoreRelationObj obj) {
		List<DataStoreObj> list = null;

		if (obj.getConnectid() == null) {
			log.error("VmDatastoreRelationService.queryForList 缺少connectid");
		}
		if (obj.getVtype() == null) {
			log.error("VmDatastoreRelationService.queryForList 缺少vtype");
		}
		if (obj.getDatastore_uuid() == null) {
			log.error("VmDatastoreRelationService.queryForList 缺少vm_uuid");
		}
		try {
			list = vmDatastoreRelationDao.queryDatastoreForList(obj);
		} catch (SQLException e) {
			log.error("VmDatastoreRelationService.queryDatastoreForList 查询虚拟机失败" + "vm_uuid为："
					+ obj.getVm_uuid());
		}

		return list;
	}

	/**
	 * 
	 * @Title: queryRelationListByDatastoreuuid
	 * @Description: 查询虚拟机和存储的相关信息
	 * @param
	 * @return List<VmDatastoreRelationObj>
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @createtime 2014-9-17 上午9:24:04
	 */
	public List<VmDatastoreRelationObj> queryRelationListByDatastoreuuid(VmDatastoreRelationObj obj) {
		List<VmDatastoreRelationObj> list = null;

		try {
			list = vmDatastoreRelationDao.queryRelationListByDatastoreuuid(obj);
		} catch (SQLException e) {
			log.error("VmDatastoreRelationService.queryRelationListByDatastoreuuid 查询虚拟机和存储相关失败"
					+ "存储uuid为：" + obj.getDatastore_uuid());
		}

		return list;

	}

	public List<VmDatastoreRelationObj> queryRelationListByVmuuid(VmDatastoreRelationObj obj) {
		List<VmDatastoreRelationObj> list = null;

		try {
			list = vmDatastoreRelationDao.queryRelationListByVmuuid(obj);
		} catch (SQLException e) {
			log.error("VmDatastoreRelationService.queryRelationListByVmuuid 查询虚拟机和存储相关失败"
					+ "vm_uuid为：" + obj.getVm_uuid());
		}

		return list;

	}
}
