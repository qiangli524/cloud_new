package com.sitech.basd.resource.dao.united.vm;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.resource.domain.united.vm.VmDatastoreRelationObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;

public interface VmDatastoreRelationDao {
	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询虚拟机实体列表
	 * @param
	 * @return List<VmDatastoreRelationObj>
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @createtime 2014-9-16 上午9:54:08
	 */
	public List<VMHostObj> queryForList(VmDatastoreRelationObj obj) throws SQLException;

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询存储实体列表
	 * @param
	 * @return List<VmDatastoreRelationObj>
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @createtime 2014-9-16 上午9:54:08
	 */
	public List<DataStoreObj> queryDatastoreForList(VmDatastoreRelationObj obj) throws SQLException;

	/**
	 * 
	 * @Title: queryRelationListByDatastoreuuid
	 * @Description: 查询虚拟机和存储的相关信息(通过存储uuid)
	 * @param
	 * @return List<VmDatastoreRelationObj>
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @createtime 2014-9-17 上午9:18:54
	 */
	public List<VmDatastoreRelationObj> queryRelationListByDatastoreuuid(VmDatastoreRelationObj obj)
			throws SQLException;

	public List<VmDatastoreRelationObj> queryRelationListByVmuuid(VmDatastoreRelationObj obj)
			throws SQLException;

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @createtime 2014-9-16 上午9:55:29
	 */
	public int deleteByObj(VmDatastoreRelationObj obj) throws SQLException;

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一条数据
	 * @param
	 * @return String
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @createtime 2014-9-16 上午9:55:59
	 */
	public String insertByObj(VmDatastoreRelationObj obj) throws SQLException;

}
