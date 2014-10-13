package com.sitech.basd.ibmmanager.dao;

import java.util.List;

import com.sitech.basd.ibmmanager.domain.ClusterObj;
import com.sitech.basd.ibmmanager.domain.HostObj;
import com.sitech.basd.ibmmanager.domain.VMObj;

/**
 * 
 * <p>
 * Title: CloudOSDataCompareDao
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
 * @createtime 2013-11-9 上午11:17:24
 * 
 */
public interface CloudOSDataCompareDao {
	/**
	 * 
	 * @Title: getCloudUniqueEntity
	 * @Description: 获取X86唯一实体（用于数据比对）
	 * @param
	 * @return List<String>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-9 上午11:16:18
	 */
	public List<String> getCloudUniqueEntity();

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
}
