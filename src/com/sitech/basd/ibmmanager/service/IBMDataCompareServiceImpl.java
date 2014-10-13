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
import org.springframework.transaction.annotation.Transactional;

import com.sitech.basd.exception.DataSynchroException;
import com.sitech.basd.ibmmanager.dao.IBMDataCompareDao;
import com.sitech.basd.ibmmanager.dao.IBMManagerTreeDao;
import com.sitech.basd.ibmmanager.domain.ClusterObj;
import com.sitech.basd.ibmmanager.domain.HMCObj;
import com.sitech.basd.ibmmanager.domain.HostObj;
import com.sitech.basd.ibmmanager.domain.IBMManagerTreeObj;
import com.sitech.basd.ibmmanager.domain.LparObj;
import com.sitech.basd.ibmmanager.domain.PowObj;
import com.sitech.basd.ibmmanager.domain.VMObj;
import com.sitech.basd.ibmmanager.util.IBMConstant;
import com.sitech.basd.ibmmanager.util.RevertEntity;
import com.sitech.basd.resource.util.HostSystemType;
import com.sitech.basd.resource.util.VirtualMachineType;
import com.sitech.basd.sxcloud.cloud.dao.resource.HostInfoDao;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.vo.united.ResultSet;
import com.sitech.vo.util.HostSystemEqType;

/**
 * 
 * <p>
 * Title: IBMDataCompareServiceImpl
 * </p>
 * <p>
 * Description: IBM数据对比相关操作
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
 * @createtime 2013-11-14 下午3:11:39
 * 
 */
@Service("ibmDataCompareService")
@Transactional("cloudIbatisTransactionManager")
public class IBMDataCompareServiceImpl implements IBMDataCompareService {
	private static final Logger LOG = LoggerFactory.getLogger(IBMDataCompareServiceImpl.class);
	private static final String SPLIT = "@";
	private static final String MAP_KEY_LIST = "map_key_list";
	private static final String MAP_KEY_MAP = "map_key_map";
	private static final String ROOT_ID = "1";
	@Autowired
	private IBMDataCompareDao ibmDataCompareDao;
	@Autowired
	private IBMManagerTreeDao ibmManagerTreeDao;
	@Autowired
	private VMHostDao vmHostDao;
	@Autowired
	private HostInfoDao hostInfoDao;

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
	public void IBMDataCompare() throws DataSynchroException {
		Map<String, Object> apiDataMap = initIBMApiDataMap();
		Map<String, ResultSet> entityDataMap = (Map<String, ResultSet>) apiDataMap.get(MAP_KEY_MAP);
		List<String> entityKeyList = (List<String>) apiDataMap.get(MAP_KEY_LIST);
		List<String> ibmDBList = initIBMDbDataList();
		if (entityKeyList != null) {
			Set<String> entityMapKeySet = entityDataMap.keySet();
			for (String eneityKey : entityKeyList) {
				String[] keyArray = eneityKey.split(SPLIT);
				String entityType = keyArray[2];
				if (ibmDBList.contains(eneityKey)) {
					if (IBMConstant.HMC_STRING.equals(entityType)) {// hmc
						HMCObj hmcObj = (HMCObj) entityDataMap.get(eneityKey);
						updateTreeParentNode(hmcObj, IBMConstant.HMC_STRING, ROOT_ID);
					} else if (IBMConstant.POWER_STRING.equals(entityType)) {// power
						PowObj powObj = (PowObj) entityDataMap.get(eneityKey);
						String parentId = getParentNodeId(powObj.getHmcCode());
						updateTreeParentNode(powObj, IBMConstant.POWER_STRING, parentId);
						/*
						 * 处理Power整机数据到宿主机表
						 */
						dealTbCloud2HostInfoByPowerObj(powObj);
					} else if (IBMConstant.LPAR_STRING.equals(entityType)) {// LPar
						LparObj lparObj = (LparObj) entityDataMap.get(eneityKey);
						String parentId = getParentNodeId(lparObj.getPowerCode());
						updateTreeParentNode(lparObj, IBMConstant.LPAR_STRING, parentId);
						/*
						 * 处理Power逻辑分区数据到虚拟机表
						 */
						dealTbCloud2VMHostInfoByLparObj(lparObj);
					}
				} else {
					if (IBMConstant.HMC_STRING.equals(entityType)) {// hmc
						HMCObj hmcObj = (HMCObj) entityDataMap.get(eneityKey);
						saveTreeNode(hmcObj, IBMConstant.HMC_STRING, ROOT_ID);
					} else if (IBMConstant.POWER_STRING.equals(entityType)) {// power
						PowObj powObj = (PowObj) entityDataMap.get(eneityKey);
						String parentId = getParentNodeId(powObj.getHmcCode());
						saveTreeNode(powObj, IBMConstant.POWER_STRING, parentId);
						/*
						 * 处理Power整机数据到宿主机表
						 */
						dealTbCloud2HostInfoByPowerObj(powObj);
					} else if (IBMConstant.LPAR_STRING.equals(entityType)) {// power
						LparObj lparObj = (LparObj) entityDataMap.get(eneityKey);
						String parentId = getParentNodeId(lparObj.getPowerCode());
						saveTreeNode(lparObj, IBMConstant.LPAR_STRING, parentId);
						/*
						 * 处理Power逻辑分区数据到虚拟机表
						 */
						dealTbCloud2VMHostInfoByLparObj(lparObj);
					}
				}
			}
			// 遍历数据库实体
			for (String entityKey : ibmDBList) {
				String[] keyArray = entityKey.split(SPLIT);
				String entityCode = keyArray[1];
				String entityType = keyArray[2];
				if (!entityMapKeySet.contains(entityKey)) {// 若vCenter中无数据库中实体，则进行数据库数据移除
					if (IBMConstant.HMC_STRING.equals(entityType)) {
						// 删除树节点数据
						deleteTreeNode(entityCode);
					} else if (IBMConstant.POWER_STRING.equals(entityType)) {
						// 删除树节点数据
						deleteTreeNode(entityCode);
					} else if (IBMConstant.LPAR_STRING.equals(entityType)) {
						// 删除树节点数据
						deleteTreeNode(entityCode);
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
		if (ibmDBList != null) {
			ibmDBList.clear();
			ibmDBList = null;
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
	 * @Title: initHMCApiDataMap
	 * @Description: 查询各个表中数据
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 上午11:49:54
	 */
	@Override
	public Map<String, Object> initIBMApiDataMap() throws DataSynchroException {
		String log = "";
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> entityKeyList = new ArrayList<String>();
		HashMap<String, ResultSet> entityMap = new HashMap<String, ResultSet>();

		try {
			List<HMCObj> hmcList = getHMCData();
			for (HMCObj hmcObj : hmcList) {
				String hmcKey = IBMConstant.IBM_STRING + SPLIT + hmcObj.getHmcCode() + SPLIT
						+ IBMConstant.HMC_STRING;
				entityMap.put(hmcKey, hmcObj);
				entityKeyList.add(hmcKey);
			}
			List<PowObj> powerList = getPowerData();
			for (PowObj powObj : powerList) {
				String powerKey = IBMConstant.IBM_STRING + SPLIT + powObj.getPowerCode() + SPLIT
						+ IBMConstant.POWER_STRING;
				entityMap.put(powerKey, powObj);
				entityKeyList.add(powerKey);
			}
			List<LparObj> lparList = getLparData();
			for (LparObj lparObj : lparList) {
				String lparId = lparObj.getPowerCode() + "_" + lparObj.getLparCode();
				String lparKey = IBMConstant.IBM_STRING + SPLIT + lparId + SPLIT
						+ IBMConstant.LPAR_STRING;
				entityMap.put(lparKey, lparObj);
				entityKeyList.add(lparKey);
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
	 * @Title: initIBMDbDataList
	 * @Description: 
	 *               查询出树表中IBM的数据(String是IBM@uuid@HMC,IBM@uuid@Power,IBM@uuid@Lpar)
	 * @param
	 * @return List<String>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 下午2:44:04
	 */
	@Override
	public List<String> initIBMDbDataList() {
		List<String> list = ibmDataCompareDao.getIBMUniqueEntity();
		return list;
	}

	/**
	 * 
	 * @Title: getHMCData
	 * @Description: 获取HMC数据
	 * @param
	 * @return List<HMCObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 下午2:40:45
	 */
	@Override
	public List<HMCObj> getHMCData() {
		return ibmDataCompareDao.getHMCData();
	}

	/**
	 * 
	 * @Title: getPowerData
	 * @Description: 获取Power数据
	 * @param
	 * @return List<PowObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 下午2:49:16
	 */
	@Override
	public List<PowObj> getPowerData() {
		return ibmDataCompareDao.getPowerData();
	}

	/**
	 * 
	 * @Title: getLparData
	 * @Description: 获取Lpar数据
	 * @param
	 * @return List<LparObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 下午2:49:35
	 */
	@Override
	public List<LparObj> getLparData() {
		return ibmDataCompareDao.getLparData();
	}

	/**
	 * 
	 * @Title: updateTreeParentNode
	 * @Description: 更新当前父节点数据
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 下午4:14:21
	 */
	@Override
	public void updateTreeParentNode(ResultSet entityObj, String entityType, String parentId) {
		IBMManagerTreeObj treeNode = new IBMManagerTreeObj();
		if (IBMConstant.HMC_STRING.equals(entityType)) {
			treeNode = RevertEntity.toTreeHMC((HMCObj) entityObj);
		} else if (IBMConstant.POWER_STRING.equals(entityType)) {
			treeNode = RevertEntity.toTreePower((PowObj) entityObj);
		} else if (IBMConstant.LPAR_STRING.equals(entityType)) {
			treeNode = RevertEntity.toTreeLpar((LparObj) entityObj);
		} else if (IBMConstant.CLUSTER_STRING.equals(entityType)) {
			treeNode = RevertEntity.toTreeCluster((ClusterObj) entityObj);
		} else if (IBMConstant.HOST_STRING.equals(entityType)) {
			treeNode = RevertEntity.toTreeHost((HostObj) entityObj);
		} else if (IBMConstant.VM_STRING.equals(entityType)) {
			treeNode = RevertEntity.toTreeVM((VMObj) entityObj);
		}
		treeNode.setParent_id(parentId);
		ibmManagerTreeDao.updateTreeByObj(treeNode);
	}

	/**
	 * 
	 * @Title: saveTreeNode
	 * @Description: 保存树节点
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 下午5:03:29
	 */
	@Override
	public void saveTreeNode(ResultSet entityObj, String entityType, String parentId) {
		IBMManagerTreeObj treeNode = new IBMManagerTreeObj();
		if (IBMConstant.HMC_STRING.equals(entityType)) {
			treeNode = RevertEntity.toTreeHMC((HMCObj) entityObj);
			treeNode.setVtype("0");
			treeNode.setType("1");
		} else if (IBMConstant.POWER_STRING.equals(entityType)) {
			treeNode = RevertEntity.toTreePower((PowObj) entityObj);
			treeNode.setVtype("0");
			treeNode.setType("2");
		} else if (IBMConstant.LPAR_STRING.equals(entityType)) {
			treeNode = RevertEntity.toTreeLpar((LparObj) entityObj);
			treeNode.setVtype("0");
			treeNode.setType("3");
		} else if (IBMConstant.CLUSTER_STRING.equals(entityType)) {
			treeNode = RevertEntity.toTreeCluster((ClusterObj) entityObj);
			treeNode.setVtype("1");
			treeNode.setType("4");
		} else if (IBMConstant.HOST_STRING.equals(entityType)) {
			treeNode = RevertEntity.toTreeHost((HostObj) entityObj);
			treeNode.setVtype("1");
			treeNode.setType("5");
		} else if (IBMConstant.VM_STRING.equals(entityType)) {
			treeNode = RevertEntity.toTreeVM((VMObj) entityObj);
			treeNode.setVtype("1");
			treeNode.setType("6");
		}
		treeNode.setId(RandomUUID.getUuid());
		treeNode.setParent_id(parentId);
		ibmManagerTreeDao.insertTree(treeNode);
	}

	/**
	 * 
	 * @Title: deleteTreeNode
	 * @Description: 删除树节点
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-8 下午3:01:10
	 */
	@Override
	public void deleteTreeNode(String entityCode) {
		IBMManagerTreeObj obj = new IBMManagerTreeObj();
		obj.setUuid(entityCode);
		ibmManagerTreeDao.deleteTreeById(obj);
	}

	/**
	 * 
	 * @Title: getParentNodeId
	 * @Description: 查询父节点ID
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 下午4:50:01
	 */
	@Override
	public String getParentNodeId(String entityCode) {
		IBMManagerTreeObj parentNode = new IBMManagerTreeObj();
		parentNode.setUuid(entityCode);
		String parentId = ibmManagerTreeDao.queryForTree(parentNode).get(0).getId();
		return parentId;
	}

	/**
	 * 
	 * @Title: saveIbmPowerToHostInfo
	 * @Description: 保存Ibmpower主机信息
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-14 下午7:04:31
	 */
	private void saveIbmPowerToHostInfo(PowObj powObj) {
		TbCloud2HostInfoObj tbCloud2HostInfoObj = RevertEntity.toTbCloud2HostInfoObj(powObj,
				HostSystemType.IBM_POWER);
		int id = hostInfoDao.getIdSequence();
		tbCloud2HostInfoObj.setId(id);
		tbCloud2HostInfoObj.setEq_id(id + "");
		tbCloud2HostInfoObj.setEq_type(HostSystemEqType.IBM_SMART_MACHINE);
		tbCloud2HostInfoObj.setHasvertual(HostSystemType.IBM_POWER);
		hostInfoDao.insertIbmPowerMachine(tbCloud2HostInfoObj);
	}

	/**
	 * 
	 * @Title: updateIbmPowerToHostInfo
	 * @Description: 保存Ibmpower主机信息
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-14 下午7:04:31
	 */
	private void updateIbmPowerToHostInfo(PowObj powObj) {
		TbCloud2HostInfoObj tbCloud2HostInfoObj = RevertEntity.toTbCloud2HostInfoObj(powObj,
				HostSystemType.IBM_POWER);
		hostInfoDao.updateIbmPowerMachine(tbCloud2HostInfoObj);
	}

	/**
	 * 
	 * @Title: queryTbCloud2HostInfoByPowerObj
	 * @Description: 查询TB_CLOUD2_HOST_INFO中是否有数据
	 * @param
	 * @return Boolean
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-7 上午11:15:49
	 */
	private Boolean tbCloud2HostInfoIsExist(PowObj powObj) {
		Boolean isExist = false;
		TbCloud2HostInfoObj result = queryTbCloud2HostInfoByPowerObj(powObj);
		if (result != null) {
			isExist = true;
		}
		return isExist;
	}

	/**
	 * 
	 * @Title: queryTbCloud2HostInfoByPowerObj
	 * @Description: 根据PowerObj查询tb_cloud2_host_info信息
	 * @param
	 * @return TbCloud2HostInfoObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-15 上午11:04:10
	 */
	private TbCloud2HostInfoObj queryTbCloud2HostInfoByPowerObj(PowObj powObj) {
		TbCloud2HostInfoObj tbCloud2HostInfoObj = RevertEntity.toTbCloud2HostInfoObj(powObj,
				HostSystemType.IBM_POWER);
		TbCloud2HostInfoObj result = hostInfoDao.queryByObj(tbCloud2HostInfoObj);
		return result;
	}

	/**
	 * 
	 * @Title: dealTbCloud2HostInfoByPowerObj
	 * @Description: 处理Power整机数据到宿主机表
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-14 下午7:24:14
	 */
	private void dealTbCloud2HostInfoByPowerObj(PowObj powObj) {
		/*
		 * 判断是否存储在宿主机表tb_cloud2_host_info
		 */
		Boolean isExist = tbCloud2HostInfoIsExist(powObj);
		if (!isExist) {
			// 新增tb_cloud2_host_info..........
			saveIbmPowerToHostInfo(powObj);
		} else {
			// 更新tb_cloud2_host_info..........
			updateIbmPowerToHostInfo(powObj);
		}
	}

	/**
	 * 
	 * @Title: saveIbmLogicalPartition
	 * @Description: 保存IBM逻辑分区信息
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-15 上午11:11:20
	 */
	private void saveIbmLogicalPartition(LparObj lparObj) {
		VMHostObj vmHostObj = RevertEntity.toVMHostObj(lparObj,
				VirtualMachineType.IBM_LOGICAL_PARTITION);
		// 查询逻辑分区所在宿主机
		int id = vmHostDao.queryVhostIdSequence();
		vmHostObj.setID(id);
		vmHostObj.setVH_ID(id + "");
		vmHostObj = queryVMHosrEqId(vmHostObj, lparObj);
		vmHostObj.setVH_TYPE(VirtualMachineType.IBM_LOGICAL_PARTITION);
		vmHostDao.insertIbmLogicalPartition(vmHostObj);
	}

	/**
	 * 
	 * @Title: queryVMHosrEqId
	 * @Description:获取当前虚拟机的主机eq-id
	 * @param
	 * @return VMHostObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-19 上午10:40:38
	 */
	private VMHostObj queryVMHosrEqId(VMHostObj vmHostObj, LparObj lparObj) {
		TbCloud2HostInfoObj tbCloud2HostInfoObj = new TbCloud2HostInfoObj();
		tbCloud2HostInfoObj.setHasvertual(HostSystemType.IBM_POWER);
		tbCloud2HostInfoObj.setH_uuid(lparObj.getPowerCode());
		TbCloud2HostInfoObj result = hostInfoDao.queryByObj(tbCloud2HostInfoObj);
		String eq_id = result == null ? "" : result.getEq_id();
		vmHostObj.setEQ_ID(eq_id);
		return vmHostObj;
	}

	/**
	 * 
	 * @Title: updateIbmLogicalPartition
	 * @Description: 更新IBM逻辑分区信息
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-15 上午11:11:20
	 */
	private void updateIbmLogicalPartition(LparObj lparObj) {
		VMHostObj vmHostObj = RevertEntity.toVMHostObj(lparObj,
				VirtualMachineType.IBM_LOGICAL_PARTITION);
		vmHostObj = queryVMHosrEqId(vmHostObj, lparObj);
		vmHostDao.updateVMHostInfo(vmHostObj);
	}

	/**
	 * 
	 * @Title: queryTbCloud2VMHostInfoByLparObj
	 * @Description: 根据LparObj查询tb_cloud2_vmhost_info信息
	 * @param
	 * @return VMHostObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-15 上午11:04:10
	 */
	private VMHostObj queryTbCloud2VMHostInfoByLparObj(LparObj lparObj) {
		VMHostObj vmHostObj = RevertEntity.toVMHostObj(lparObj,
				VirtualMachineType.IBM_LOGICAL_PARTITION);
		VMHostObj result = vmHostDao.queryByObj(vmHostObj);
		return result;
	}

	/**
	 * 
	 * @Title: queryTbCloud2HostInfoByPowerObj
	 * @Description: 查询TB_CLOUD2_HOST_INFO中是否有数据
	 * @param
	 * @return Boolean
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-7 上午11:15:49
	 */
	private Boolean tbCloud2VMHostInfoIsExist(LparObj lparObj) {
		Boolean isExist = false;
		VMHostObj result = queryTbCloud2VMHostInfoByLparObj(lparObj);
		if (result != null) {
			isExist = true;
		}
		return isExist;
	}

	/**
	 * 
	 * @Title: dealTbCloud2VMHostInfoByLparObj
	 * @Description: 处理Power逻辑分区数据到虚拟机表
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-19 上午10:18:20
	 */
	private void dealTbCloud2VMHostInfoByLparObj(LparObj lparObj) {
		/*
		 * 判断是否存储在宿主机表tb_cloud2_vmhost_info
		 */
		Boolean isExist = tbCloud2VMHostInfoIsExist(lparObj);
		if (!isExist) {
			// 新增tb_cloud2_vmhost_info..........
			saveIbmLogicalPartition(lparObj);
		} else {
			// 更新tb_cloud2_vmhost_info..........
			updateIbmLogicalPartition(lparObj);
		}
	}

	/**
	 * 
	 * @Title: deleteTreeNode
	 * @Description: 根据连接ID及实体ID删除树节点
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-26 下午8:25:34
	 */
	private void deleteTbCloud2HostInfoData(PowObj powObj) {
		TbCloud2HostInfoObj tbCloud2HostInfoObj = RevertEntity.toTbCloud2HostInfoObj(powObj,
				HostSystemType.IBM_POWER);
		tbCloud2HostInfoObj.setH_uuid(powObj.getPowerCode());
		hostInfoDao.deleteByObj(tbCloud2HostInfoObj);
	}

	/**
	 * 
	 * @Title: deleteTreeNode
	 * @Description: 根据连接ID及实体ID删除树节点
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-26 下午8:25:34
	 */
	private void deleteTbCloud2VMHostInfoData(LparObj lparObj) {
		VMHostObj vmHostObj = new VMHostObj();
		vmHostDao.deleteByObj(vmHostObj);
	}
}
