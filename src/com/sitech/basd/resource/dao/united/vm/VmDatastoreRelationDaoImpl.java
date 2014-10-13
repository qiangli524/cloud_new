package com.sitech.basd.resource.dao.united.vm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.resource.domain.united.vm.VmDatastoreRelationObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.utils.randomid.RandomUUID;

@Repository("vmDatastoreRelationDao")
public class VmDatastoreRelationDaoImpl extends BaseDao implements VmDatastoreRelationDao {

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询虚拟机实体列表（通过存储uuid，connectid,vtype 缺一不可）
	 * @param
	 * @return List<VMHostObj>
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2014-9-16 上午9:54:08
	 */
	public List<VMHostObj> queryForList(VmDatastoreRelationObj obj) throws SQLException {
		List<VMHostObj> vmHostList = new ArrayList<VMHostObj>();
		if (obj.getPagination() != null) {
			obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
			obj.setPAGESIZE(obj.getPagination().getPageSize());
			obj.getPagination().setTotalCount(
					((Integer) getSqlMap().queryForObject(
							"VmDatastoreRelation.queryVmListForCount", obj)).intValue());
		}

		vmHostList = getSqlMap().queryForList("VmDatastoreRelation.queryForList", obj);
		return vmHostList;
	}

	/**
	 * 
	 * @Title: queryDatastoreForList
	 * @Description: 查询存储实体列表
	 * @param
	 * @return List<DataStoreForm>
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2014-9-16 上午9:54:08
	 */
	public List<DataStoreObj> queryDatastoreForList(VmDatastoreRelationObj obj) throws SQLException {

		List<DataStoreObj> vmHostList = new ArrayList<DataStoreObj>();
		if (obj.getPagination() != null) {
			obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
			obj.setPAGESIZE(obj.getPagination().getPageSize());
			obj.getPagination().setTotalCount(
					((Integer) getSqlMap().queryForObject(
							"VmDatastoreRelation.queryDatastoreListForCount", obj)).intValue());
		}

		vmHostList = getSqlMap().queryForList("VmDatastoreRelation.queryDatastoreForList", obj);
		return vmHostList;

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
	 * @throws SQLException
	 * @createtime 2014-9-17 上午9:18:54
	 */
	public List<VmDatastoreRelationObj> queryRelationListByDatastoreuuid(VmDatastoreRelationObj obj)
			throws SQLException {

		List<VmDatastoreRelationObj> list = new ArrayList<VmDatastoreRelationObj>();
		list = getSqlMap()
				.queryForList("VmDatastoreRelation.queryRelationListByDatastoreuuid", obj);
		return list;

	}

	public List<VmDatastoreRelationObj> queryRelationListByVmuuid(VmDatastoreRelationObj obj)
			throws SQLException {

		List<VmDatastoreRelationObj> list = new ArrayList<VmDatastoreRelationObj>();
		list = getSqlMap().queryForList("VmDatastoreRelation.queryRelationListByVmuuid", obj);
		return list;

	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2014-9-16 上午9:55:29
	 */
	public int deleteByObj(VmDatastoreRelationObj obj) throws SQLException {
		getSqlMap().delete("VmDatastoreRelation.deleteByObj", obj);

		return 0;
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一条数据
	 * @param
	 * @return String
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2014-9-16 上午9:55:59
	 */
	public String insertByObj(VmDatastoreRelationObj obj) throws SQLException {
		obj.setId(RandomUUID.getUuid());
		getSqlMap().insert("VmDatastoreRelation.insertByObj", obj);
		return null;
	}

}
