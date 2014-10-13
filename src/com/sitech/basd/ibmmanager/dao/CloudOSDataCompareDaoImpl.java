package com.sitech.basd.ibmmanager.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.ibmmanager.domain.ClusterObj;
import com.sitech.basd.ibmmanager.domain.HostObj;
import com.sitech.basd.ibmmanager.domain.VMObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * 
 * <p>
 * Title: CloudOSDataCompareDaoImpl
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
 * @createtime 2013-11-9 上午11:17:55
 * 
 */
@Repository("cloudOSDataCompareDao")
public class CloudOSDataCompareDaoImpl extends BaseDao implements
		CloudOSDataCompareDao {
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
	@Override
	public List<String> getCloudUniqueEntity() {
		List list = null;
		try {
			list = getSqlMap().queryForList(
					"CloudOSDataCompare.getCloudUniqueEntity");
		} catch (SQLException e) {
			LogHelper.error("CloudOSDataCompare.getCloudUniqueEntity:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}

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
	@Override
	public List<ClusterObj> getClusterData() {
		List<ClusterObj> list = null;
		try {
			list = getSqlMap()
					.queryForList("CloudOSDataCompare.getClusterData");
		} catch (SQLException e) {
			LogHelper.error("CloudOSDataCompare.getClusterData:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}

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
	@Override
	public List<HostObj> getHostData() {
		List<HostObj> list = null;
		try {
			list = getSqlMap().queryForList("CloudOSDataCompare.getHostData");
		} catch (SQLException e) {
			LogHelper.error("CloudOSDataCompare.getHostData:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

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
	@Override
	public List<VMObj> getVMData() {
		List<VMObj> list = null;
		try {
			list = getSqlMap().queryForList("CloudOSDataCompare.getVMData");
		} catch (SQLException e) {
			LogHelper.error("CloudOSDataCompare.getVMData:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}
}
