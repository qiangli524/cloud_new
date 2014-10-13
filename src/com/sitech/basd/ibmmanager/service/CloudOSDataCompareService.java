package com.sitech.basd.ibmmanager.service;

import java.util.List;
import java.util.Map;

import com.sitech.basd.exception.DataSynchroException;
import com.sitech.basd.ibmmanager.domain.ClusterObj;
import com.sitech.basd.ibmmanager.domain.HostObj;
import com.sitech.basd.ibmmanager.domain.VMObj;

/**
 * 
 * <p>
 * Title: CloudOSDataCompareService
 * </p>
 * <p>
 * Description: X86数据比对相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2013-11-9 下午12:04:36
 * 
 */
public interface CloudOSDataCompareService {
	/**
	 * 
	 * @Title: initCloudOSApiDataMap
	 * @Description: 
	 *               查询X86数据(在TB_CLOUD_CLUSTER_INFO,TB_CLOUD2_HOST_INFO,TB_CLOUD2_VMHOST_INFO
	 *               )(key分别是X86@uuid@Cluster,X86@uuid@Host,X86@uuid@VM)(
	 *               Object是key拆分开相对应的对象)
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-9 下午12:08:32
	 */
	public Map<String, Object> initCloudOSApiDataMap()
			throws DataSynchroException;

	/**
	 * 
	 * @Title: initCloudOSDbDataList
	 * @Description: 
	 *               查询出树表中X86的数据(String是X86@uuid@Cluster,X86@uuid@Host,X86@uuid@VM)
	 * @param
	 * @return List<String>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-9 下午12:13:02
	 */
	public List<String> initCloudOSDbDataList();

	/**
	 * 
	 * @Title: getClusterData
	 * @Description: 获取集群数据
	 * @param
	 * @return List<ClusterObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-9 上午11:24:01
	 */
	public List<ClusterObj> getClusterData();

	/**
	 * 
	 * @Title: getHostData
	 * @Description: 获取主机数据
	 * @param
	 * @return List<HostObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-9 上午11:30:46
	 */
	public List<HostObj> getHostData();

	/**
	 * 
	 * @Title: getVMData
	 * @Description: 获取虚拟机数据
	 * @param
	 * @return List<VMObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-9 上午11:37:06
	 */
	public List<VMObj> getVMData();

	/**
	 * 
	 * @Title: CloudOSDataCompare
	 * @Description: X86数据同步
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-9 下午12:20:52
	 */
	public void CloudOSDataCompare() throws DataSynchroException;
}
