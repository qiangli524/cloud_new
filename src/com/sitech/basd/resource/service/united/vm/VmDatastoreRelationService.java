package com.sitech.basd.resource.service.united.vm;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.resource.domain.united.vm.VmDatastoreRelationObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;

/**
 * 
 * <p>
 * Title: VmDatastoreRelationService
 * </p>
 * <p>
 * Description: 虚拟机和存储相关关系
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author liuqi
 * @version 1.0
 * @createtime 2014-9-17 上午7:52:14
 * 
 */
public interface VmDatastoreRelationService {
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
	public List<VMHostObj> queryForList(VmDatastoreRelationObj obj);

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
	public List<DataStoreObj> queryDatastoreForList(VmDatastoreRelationObj obj);

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
	public List<VmDatastoreRelationObj> queryRelationListByDatastoreuuid(VmDatastoreRelationObj obj);

	public List<VmDatastoreRelationObj> queryRelationListByVmuuid(VmDatastoreRelationObj obj);
}
