package com.sitech.basd.ibmmanager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.exception.DataSynchroException;
import com.sitech.basd.ibmmanager.dao.CloudOSDataCompareDao;
import com.sitech.basd.ibmmanager.domain.ClusterObj;
import com.sitech.basd.ibmmanager.domain.HostObj;
import com.sitech.basd.ibmmanager.domain.VMObj;
import com.sitech.basd.ibmmanager.util.IBMConstant;
import com.sitech.vo.united.ResultSet;

/**
 * 
 * <p>
 * Title: CloudOSDataCompareServiceImpl
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
 * @createtime 2013-11-9 下午12:04:48
 * 
 */
@Service("cloudOSDataCompareService")
public class CloudOSDataCompareServiceImpl implements CloudOSDataCompareService {
	private static final Logger LOG = LoggerFactory.getLogger(CloudOSDataCompareServiceImpl.class);
	private static final String SPLIT = "@";
	private static final String MAP_KEY_LIST = "map_key_list";
	private static final String MAP_KEY_MAP = "map_key_map";
	private static final String ROOT_ID = "2";
	@Autowired
	private CloudOSDataCompareDao cloudOSDataCompareDao;
	@Autowired
	private IBMDataCompareService ibmDataCompareService;

	/**
	 * 
	 * @Title: IBMDataCompare
	 * @Description: IBM 数据同步
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 下午3:58:37
	 */
	@Override
	public void CloudOSDataCompare() throws DataSynchroException {
		Map<String, Object> apiDataMap = initCloudOSApiDataMap();
		Map<String, ResultSet> entityDataMap = (Map<String, ResultSet>) apiDataMap.get(MAP_KEY_MAP);
		List<String> entityKeyList = (List<String>) apiDataMap.get(MAP_KEY_LIST);
		List<String> osDBList = initCloudOSDbDataList();
		if (entityKeyList != null) {
			Set<String> entityMapKeySet = entityDataMap.keySet();
			for (String eneityKey : entityKeyList) {
				String[] keyArray = eneityKey.split(SPLIT);
				String entityType = keyArray[2];
				if (osDBList.contains(eneityKey)) {
					if (IBMConstant.CLUSTER_STRING.equals(entityType)) {// 集群
						ClusterObj clusterObj = (ClusterObj) entityDataMap.get(eneityKey);
						ibmDataCompareService.updateTreeParentNode(clusterObj,
								IBMConstant.CLUSTER_STRING, ROOT_ID);
					} else if (IBMConstant.HOST_STRING.equals(entityType)) {// 主机
						HostObj hostObj = (HostObj) entityDataMap.get(eneityKey);
						String parentId = ibmDataCompareService.getParentNodeId(hostObj
								.getClusterCode());
						ibmDataCompareService.updateTreeParentNode(hostObj,
								IBMConstant.HOST_STRING, parentId);
					} else if (IBMConstant.VM_STRING.equals(entityType)) {// 虚拟机
						VMObj vmObj = (VMObj) entityDataMap.get(eneityKey);
						String parentId = ibmDataCompareService
								.getParentNodeId(vmObj.getHostCode());
						ibmDataCompareService.updateTreeParentNode(vmObj, IBMConstant.VM_STRING,
								parentId);
					}
				} else {
					if (IBMConstant.CLUSTER_STRING.equals(entityType)) {// 集群
						ClusterObj clusterObj = (ClusterObj) entityDataMap.get(eneityKey);
						ibmDataCompareService.saveTreeNode(clusterObj, IBMConstant.CLUSTER_STRING,
								ROOT_ID);
					} else if (IBMConstant.HOST_STRING.equals(entityType)) {// 主机
						HostObj hostObj = (HostObj) entityDataMap.get(eneityKey);
						String parentId = ibmDataCompareService.getParentNodeId(hostObj
								.getClusterCode());
						ibmDataCompareService.saveTreeNode(hostObj, IBMConstant.HOST_STRING,
								parentId);
					} else if (IBMConstant.VM_STRING.equals(entityType)) {// 虚拟机
						VMObj vmObj = (VMObj) entityDataMap.get(eneityKey);
						String parentId = ibmDataCompareService
								.getParentNodeId(vmObj.getHostCode());
						ibmDataCompareService.saveTreeNode(vmObj, IBMConstant.VM_STRING, parentId);
					}
				}
			}
			// 遍历数据库实体
			for (String entityKey : osDBList) {
				String[] keyArray = entityKey.split(SPLIT);
				String entityCode = keyArray[1];
				String entityType = keyArray[2];
				if (!entityMapKeySet.contains(entityKey)) {// 若vCenter中无数据库中实体，则进行数据库数据移除
					if (IBMConstant.CLUSTER_STRING.equals(entityType)) {
						// 删除树节点数据
						ibmDataCompareService.deleteTreeNode(entityCode);
					} else if (IBMConstant.HOST_STRING.equals(entityType)) {
						// 删除树节点数据
						ibmDataCompareService.deleteTreeNode(entityCode);
					} else if (IBMConstant.VM_STRING.equals(entityType)) {
						// 删除树节点数据
						ibmDataCompareService.deleteTreeNode(entityCode);
					}
				} else {

				}
			}
		}
		// 垃圾数据回收
		if (apiDataMap != null) {
			apiDataMap.clear();
			apiDataMap = null;
		}
		if (osDBList != null) {
			osDBList.clear();
			osDBList = null;
		}
		if (entityDataMap != null) {
			entityDataMap.clear();
			entityDataMap = null;
		}
		if (entityKeyList != null) {
			entityKeyList.clear();
			entityKeyList = null;
		}
		System.gc();

	}

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
	@Override
	public Map<String, Object> initCloudOSApiDataMap() throws DataSynchroException {
		String log = "";
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> entityKeyList = new ArrayList<String>();
		HashMap<String, ResultSet> entityMap = new HashMap<String, ResultSet>();

		try {
			List<ClusterObj> clusterList = getClusterData();
			for (ClusterObj clusterObj : clusterList) {
				String clusterKey = IBMConstant.X86_STRING + SPLIT + clusterObj.getClusterCode()
						+ SPLIT + IBMConstant.CLUSTER_STRING;
				entityMap.put(clusterKey, clusterObj);
				entityKeyList.add(clusterKey);
			}
			List<HostObj> hostList = getHostData();
			for (HostObj hostObj : hostList) {
				String hostKey = IBMConstant.X86_STRING + SPLIT + hostObj.getHostCode() + SPLIT
						+ IBMConstant.HOST_STRING;
				entityMap.put(hostKey, hostObj);
				entityKeyList.add(hostKey);
			}
			List<VMObj> vmList = getVMData();
			for (VMObj vmObj : vmList) {
				String vmKey = IBMConstant.X86_STRING + SPLIT + vmObj.getVmCode() + SPLIT
						+ IBMConstant.VM_STRING;
				entityMap.put(vmKey, vmObj);
				entityKeyList.add(vmKey);
			}
			result.put(MAP_KEY_MAP, entityMap);
			result.put(MAP_KEY_LIST, entityKeyList);
		} catch (Exception e) {
			log = "为获取IBM数据信息:";
			LOG.error(log + e.getMessage() + e);
			throw new DataSynchroException(log, e);
		}
		return result;
	}

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
	@Override
	public List<String> initCloudOSDbDataList() {
		List<String> list = cloudOSDataCompareDao.getCloudUniqueEntity();
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
		return cloudOSDataCompareDao.getClusterData();
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
		return cloudOSDataCompareDao.getHostData();
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
		return cloudOSDataCompareDao.getVMData();
	}
}
