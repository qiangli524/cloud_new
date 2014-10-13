package com.sitech.basd.resource.service.united;

import ip.IpType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import publiccloud.VirtualMachineSellType;

import com.sitech.basd.exception.CloudUnitedException;
import com.sitech.basd.exception.DataSynchroException;
import com.sitech.basd.resource.dao.template.TemManDao;
import com.sitech.basd.resource.dao.united.UnitedTreeDao;
import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.util.HostSystemType;
import com.sitech.basd.resource.util.RevertEntity;
import com.sitech.basd.resource.util.VirtualClient;
import com.sitech.basd.resource.util.VirtualMachineType;
import com.sitech.basd.resource.util.VirtualTemplateType;
import com.sitech.basd.sxcloud.cloud.dao.globalconfig.TbGlobalConfigDao;
import com.sitech.basd.sxcloud.cloud.dao.resource.HostInfoDao;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.yicloud.dao.datastore.DataStoreDao;
import com.sitech.basd.yicloud.dao.ipinfo.IpinfoDao;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.ssd.ah.nas.dao.NasFileSysDao;
import com.sitech.ssd.ah.nas.domain.VmIpObj;
import com.sitech.utils.encrypt.DoubleEncryptUtils;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.publicShop.PublicCloudUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.xml.XmlProperties;
import com.sitech.vo.united.ClusterUnitedVO;
import com.sitech.vo.united.DataCenterUnitedVO;
import com.sitech.vo.united.DatastoreUnitedVO;
import com.sitech.vo.united.HostUnitedVO;
import com.sitech.vo.united.ResultSet;
import com.sitech.vo.united.VirtualMachineUnitedVO;
import com.sitech.vo.united.VirtualNicUnitedVO;
import com.sitech.vo.util.UnitedConstant;
import com.sitech.vo.util.VirtualConstant;

/**
 * 
 * <p>
 * Title: VMwareDataCompareService
 * </p>
 * <p>
 * Description: VMware-vCenter数据比对，用于同步数据及后续数据比对工作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-7-26 上午10:11:32
 * 
 */
@Service("vmwareDataCompareService")
// @Transactional("cloudIbatisTransactionManager")
public class VMwareDataCompareService {
	private static final Logger LOG = LoggerFactory.getLogger(VMwareDataCompareService.class);
	private static final String SPLIT = "@";
	private static final String MAP_KEY_LIST = "map_key_list";
	private static final String MAP_KEY_MAP = "map_key_map";
	private static final String ROOT_ID = "1";
	@Autowired
	private UnitedTreeDao unitedTreeDao;
	@Autowired
	private XmlProperties vmwareConnectionXml;
	@Autowired
	private VMHostDao vmHostDao;
	@Autowired
	private HostInfoDao hostInfoDao;
	@Autowired
	private DataStoreDao dataStoreDao;
	@Autowired
	private TemManDao temManDao;
	@Autowired
	private AreaResourcePoolRelationService areaResourcePoolRelationService;
	@Autowired
	private NasFileSysDao nasFileSysDao;
	@Autowired
	private TbGlobalConfigDao tbGlobalConfigDao;
	@Autowired
	private UnitedVMService unitedVMService;
	@Autowired
	private IpinfoDao ipinfoDao;
	/**
	 * 
	 * @Title: vmwareDataCompare
	 * @Description: VMware实体数据比对
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws DataSynchroException
	 * @createtime 2013-7-26 下午3:40:21
	 */
	// Spring定时调度
	// @Scheduled(cron = "${vmware.data.synchron.quzrtz}")
	public void vmwareDataCompare() throws DataSynchroException, SQLException {
		//蒋迪修改 2014-10-09
		//更新数据时，清空IP中占用的标识。在遍历虚拟机时，重新修改IP占用信息
		ipinfoDao.updateIpState();
		
		Map<String, Object> apiDataMap = initCloudUnitedApiDataMap();
		// 实例所有vCenter数据Map
		Map<String, ResultSet> entityDataMap = (Map<String, ResultSet>) apiDataMap.get(MAP_KEY_MAP);
		List<String> entityKeyList = (List<String>) apiDataMap.get(MAP_KEY_LIST);
		// 实例数据库实体Map
		List<String> cloudDbList = initCloudDbDataList();
		if (entityKeyList != null && cloudDbList != null) {
			Set<String> entityMapKeySet = entityDataMap.keySet();
			// 遍历vCenter实体数据
			for (String eneityKey : entityKeyList) {
				/*
				 * key格式：vm-330@VCENTER.m0@VM {1} - 实体Code {2} - vCenter ID {3}
				 * -
				 * 实体类型，详见com.sitech.basd.resource.domain.united。UnitedConstant
				 */
				String[] keyArray = eneityKey.split(SPLIT);
				// 实体Code
				String entityCode = keyArray[0];
				// vCenter ID
				String vcenterCode = keyArray[1];
				// 实体类型，详见com.sitech.basd.resource.domain.united。UnitedConstant
				String entityType = keyArray[2];
				if (keyArray.length == 4) {
					entityType = keyArray[3];
				}
				if (cloudDbList.contains(eneityKey)) {// 如果数据库中包含实体,则更新数据
					if (UnitedConstant.DATACENTER_STRING.equals(entityType)) {// 更新数据中心名称
						DataCenterUnitedVO dataCenterUnitedVO = (DataCenterUnitedVO) entityDataMap
								.get(eneityKey);
						// 获取当前vCenter所在的地域id，安徽移动需求----taoxue修改----------
						String parentId = getParentIdByVCenterCode(dataCenterUnitedVO
								.getConnectCode());
						updateTreeParentNode(dataCenterUnitedVO, UnitedConstant.DATACENTER,
								parentId);

					} else if (UnitedConstant.CLUSTER_STRING.equals(entityType)) {// 更新数据中心父节点
						ClusterUnitedVO clusterUnitedVO = (ClusterUnitedVO) entityDataMap
								.get(eneityKey);
						String parentId = getParentNodeId(vcenterCode,
								clusterUnitedVO.getDataCenterCode());
						// 更新当前节点父节点
						updateTreeParentNode(clusterUnitedVO, UnitedConstant.CLUSTER, parentId);
					} else if (UnitedConstant.HOST_STRING.equals(entityType)) {// 更新父节点关系
						HostUnitedVO hostUnitedVO = (HostUnitedVO) entityDataMap.get(eneityKey);
						String parentId = "";
						// 判断宿主机是否在数据中心下
						if (hostUnitedVO.getIsInDataCenter()) {
							parentId = getParentNodeId(vcenterCode,
									hostUnitedVO.getDataCenterCode());
						} else {
							parentId = getParentNodeId(vcenterCode, hostUnitedVO.getClusterCode());
						}
						// 更新当前节点父节点
						updateTreeParentNode(hostUnitedVO, UnitedConstant.HOST, parentId);
						// 判断TB_CLOUD2_HOST_INFO表是否存在数据,若不存在则新增
						Boolean isExist = queryTbCloud2HostInfoByHostUnitedVO(hostUnitedVO);
						if (!isExist) {
							// 新增tb_cloud2_host_info..........
							saveTbCloud2HostInfo(hostUnitedVO);
						} else {
							// 更新tb_cloud2_host_info..........
							updateTbCloud2HostInfoData(hostUnitedVO);
						}
						// 同步在vCenter中添加的主机，但是在云平台中是未分配的主机时候，需要将其从未分配资源池中删除，taoxue修改
						UnitedTreeObj unallocate = new UnitedTreeObj();
						unallocate.setName(hostUnitedVO.getHostname());
						unallocate.setVtype(UnitedConstant.UNALLOCATED);// 未分配资源
						unallocate = unitedTreeDao.queryByObj(unallocate);
						if (unallocate != null) {
							unitedTreeDao.deleteByObj(unallocate);
						}

					} else if (UnitedConstant.VM_STRING.equals(entityType)) {// 更新父节点关系及虚拟机配置
						VirtualMachineUnitedVO virtualMachineUnitedVO = (VirtualMachineUnitedVO) entityDataMap
								.get(eneityKey);
						String parentId = getParentNodeId(vcenterCode,
								virtualMachineUnitedVO.getHostCode());
						// tb_cloud2_host_info eq_id
						String eq_id = getTbCloud2HostId(virtualMachineUnitedVO);
						// 更新当前节点父节点
						updateTreeParentNode(virtualMachineUnitedVO, UnitedConstant.VM, parentId);
						
						//蒋迪修改 2014-10-09
						//更新IP数据
						List<VirtualNicUnitedVO> list = virtualMachineUnitedVO.getVirtualNicList();
						if (list != null && list.size() > 0) {
							VirtualNicUnitedVO vo = list.get(0);
							ipinfoDao.updateIpStateByIp(vo.getIpAddress());
						}
						// 判断TB_CLOUD2_HOST_INFO表是否存在数据,若不存在则新增
						Boolean isExist = queryTbCloud2VMHostInfoByVirtualMachineUnitedVO(virtualMachineUnitedVO);
						if (!isExist) {
							saveTbCloud2VMHostInfo(virtualMachineUnitedVO, eq_id);
							// 虚拟机与数据存储关联关系
							dealVmDatastoreRelation(virtualMachineUnitedVO);
						} else {
							// 更新TB_CLOUD2_VMHOST_INFO
							updateTbCloud2VMHostInfo(virtualMachineUnitedVO, eq_id);
							// 虚拟机与数据存储关联关系
							dealVmDatastoreRelation(virtualMachineUnitedVO);
						}
					} else if (UnitedConstant.DATASTORE_STRING.equals(entityType)) {// 数据存储
						DatastoreUnitedVO datastoreUnitedVO = (DatastoreUnitedVO) entityDataMap
								.get(eneityKey);
						updateTbYicloudDatastore(datastoreUnitedVO);
					} else if (UnitedConstant.TEMPLATE_STRING.equals(entityType)) {// 模板
						VirtualMachineUnitedVO virtualMachineUnitedVO = (VirtualMachineUnitedVO) entityDataMap
								.get(eneityKey);
						Boolean isExist = queryTbTemManByVirtualMachineUnitedVO(virtualMachineUnitedVO);
						if (!isExist) {
							saveTbTemManage(virtualMachineUnitedVO);
						} else {
							updateTbTemManage(virtualMachineUnitedVO);
						}
					}
				} else {// 若不包含，则新增
					if (UnitedConstant.DATACENTER_STRING.equals(entityType)) {// 插入树节点
						DataCenterUnitedVO dataCenterUnitedVO = (DataCenterUnitedVO) entityDataMap
								.get(eneityKey);
						// 保存树节点数据
						// / 获取当前vCenter所在的地域id，安徽移动需求----taoxue修改----------
						String parentId = getParentIdByVCenterCode(dataCenterUnitedVO
								.getConnectCode());
						saveTreeNode(dataCenterUnitedVO, UnitedConstant.DATACENTER, parentId);
					} else if (UnitedConstant.CLUSTER_STRING.equals(entityType)) {// 插入树节点
						ClusterUnitedVO clusterUnitedVO = (ClusterUnitedVO) entityDataMap
								.get(eneityKey);
						String parentId = getParentNodeId(vcenterCode,
								clusterUnitedVO.getDataCenterCode());
						// 保存树节点数据
						saveTreeNode(clusterUnitedVO, UnitedConstant.CLUSTER, parentId);
					} else if (UnitedConstant.HOST_STRING.equals(entityType)) {// 插入树节点及tb_cloud2_host_info
						HostUnitedVO hostUnitedVO = (HostUnitedVO) entityDataMap.get(eneityKey);
						String parentId = "";
						// 判断宿主机是否在数据中心下
						if (hostUnitedVO.getIsInDataCenter()) {
							parentId = getParentNodeId(vcenterCode,
									hostUnitedVO.getDataCenterCode());
						} else {
							parentId = getParentNodeId(vcenterCode, hostUnitedVO.getClusterCode());
						}
						// 保存树节点数据
						saveTreeNode(hostUnitedVO, UnitedConstant.HOST, parentId);
						// 新增tb_cloud2_host_info..........
						saveTbCloud2HostInfo(hostUnitedVO);
					} else if (UnitedConstant.VM_STRING.equals(entityType)) {// 插入树节点及tb_cloud2_vmhost_info
						VirtualMachineUnitedVO virtualMachineUnitedVO = (VirtualMachineUnitedVO) entityDataMap
								.get(eneityKey);
						String parentId = getParentNodeId(vcenterCode,
								virtualMachineUnitedVO.getHostCode());
						//蒋迪修改 2014-10-09
						//更新IP数据
						List<VirtualNicUnitedVO> list = virtualMachineUnitedVO.getVirtualNicList();
						if (list != null && list.size() > 0) {
							VirtualNicUnitedVO vo = list.get(0);
							ipinfoDao.updateIpStateByIp(vo.getIpAddress());
						}
						// tb_cloud2_host_info eq_id
						String eq_id = getTbCloud2HostId(virtualMachineUnitedVO);
						// 保存树节点数据
						saveTreeNode(virtualMachineUnitedVO, UnitedConstant.VM, parentId);
						// 新增tb_cloud2_vmhost_info..........
						saveTbCloud2VMHostInfo(virtualMachineUnitedVO, eq_id);
					} else if (UnitedConstant.DATASTORE_STRING.equals(entityType)) {// 数据存储
						DatastoreUnitedVO datastoreUnitedVO = (DatastoreUnitedVO) entityDataMap
								.get(eneityKey);
						// 保存数据
						saveTbYicloudDatastore(datastoreUnitedVO);
					} else if (UnitedConstant.TEMPLATE_STRING.equals(entityType)) {// 模板
						VirtualMachineUnitedVO virtualMachineUnitedVO = (VirtualMachineUnitedVO) entityDataMap
								.get(eneityKey);
						saveTbTemManage(virtualMachineUnitedVO);
					}
				}
			}
			// 遍历数据库实体
			for (String entityKey : cloudDbList) {
				/*
				 * key格式：vm-330@VCENTER.m0@VM {1} - 实体Code {2} - vCenter ID {3}
				 * -
				 * 实体类型，详见com.sitech.basd.resource.domain.united。UnitedConstant
				 */
				if (entityKey == null) {
					LOG.error("entityKey@cloudDbList is null!");
					continue;
				}
				String[] keyArray = entityKey.split(SPLIT);
				// 实体Code
				String entityCode = keyArray[0];
				// vCenter ID
				String vcenterCode = keyArray[1];
				// 实体类型，详见com.sitech.basd.resource.domain.united。UnitedConstant
				String entityType = keyArray[2];
				// 存储重复问题的修改
				/*
				 * if(keyArray!=null&&keyArray.length==4){
				 * vcenterCode=keyArray[2]; entityType= keyArray[3]; }
				 */
				if (!entityMapKeySet.contains(entityKey)) {// 若vCenter中无数据库中实体，则进行数据库数据移除
					if (UnitedConstant.DATACENTER_STRING.equals(entityType)) {
						// 删除树节点数据
						deleteTreeNode(vcenterCode, entityCode);
					} else if (UnitedConstant.CLUSTER_STRING.equals(entityType)) {
						// 删除树节点数据
						deleteTreeNode(vcenterCode, entityCode);
					} else if (UnitedConstant.HOST_STRING.equals(entityType)) {
						// 删除树节点数据
						deleteTreeNode(vcenterCode, entityCode);
						// 删除tb_cloud2_host_info..........
						deleteTbCloud2HostInfoData(vcenterCode, entityCode);
					} else if (UnitedConstant.VM_STRING.equals(entityType)) {
						// 删除树节点数据
						deleteTreeNode(vcenterCode, entityCode);
						// 删除tb_cloud2_vmhost_info..........
						deleteTbCloud2VMHostInfoData(vcenterCode, entityCode);
						// 删除虚拟机与数据存储关联关系
						unitedVMService.deleteVmDatastoreRelation(vcenterCode, entityCode,
								UnitedConstant.VMWARE);
					} else if (UnitedConstant.DATASTORE_STRING.equals(entityType)) {// 数据存储
						deleteTbYicloudDatastore(vcenterCode, entityCode);
					} else if (UnitedConstant.TEMPLATE_STRING.equals(entityType)) {// 模板
						// 删除模板信息
						deleteTbTemManage(vcenterCode, entityCode);
					}
				} 
			}
		}
		// 垃圾数据回收
		if (apiDataMap != null) {
			apiDataMap.clear();
			apiDataMap = null;
		}
		if (cloudDbList != null) {
			cloudDbList.clear();
			cloudDbList = null;
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
		validateHostData();
	}

	/**
	 * 
	 * @Title: initCloudUnitedApiDataMap
	 * @Description: 实例VMware接口实体类Map。用于遍历及数据比对
	 * @param
	 * @return Map<String,ResultSet> key格式：vm-330@VCENTER.m0@VM {1} - 实体Code {2}
	 *         - vCenter ID {3} -
	 *         实体类型，详见com.sitech.basd.resource.domain.united。UnitedConstant
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws DataSynchroException
	 * @createtime 2013-7-26 上午11:18:55
	 */
	private Map<String, Object> initCloudUnitedApiDataMap() throws DataSynchroException {
		String log = "";
		/*
		 * 用于存储结果集信息
		 */
		Map<String, Object> result = new HashMap<String, Object>();
		/*
		 * 用于存储实体key
		 */
		List<String> entityKeyList = new ArrayList<String>();
		HashMap<String, ResultSet> entityMap = new HashMap<String, ResultSet>();
		// 获取vCenter标识列表
		Map<String, String> vcenterInControl = vmwareConnectionXml.getMap();
		try {
			if (vcenterInControl == null) {
				LOG.error("未获取到VMware vCenter配置信息！");
				throw new CloudUnitedException("未获取到VMware vCenter配置信息！");
			}
			// vCenter标识集合
			Set<String> vcenterSet = vcenterInControl.keySet();
			Iterator<String> vcenterIterator = vcenterSet.iterator();
			while (vcenterIterator.hasNext()) {
				// vCenter标识
				String vCenterCode = vcenterIterator.next();
				DataCenterUnitedVO dataCenterUnitedVO = getDataCenterDataFromApi(vCenterCode);
				Assert.notNull(dataCenterUnitedVO, "获取" + vCenterCode + "数据中心数据错误！");
				// 获取数据中心列表
				List<DataCenterUnitedVO> dataCenterList = dataCenterUnitedVO.getDcList();
				if (dataCenterList != null && dataCenterList.size() > 0) {
					for (DataCenterUnitedVO dcUnitedVO : dataCenterList) {
						// 存储数据中心实体
						String datacenterKey = dcUnitedVO.getDataCenterCode() + SPLIT + vCenterCode
								+ SPLIT + UnitedConstant.DATACENTER_STRING;
						entityMap.put(datacenterKey, dcUnitedVO);
						// 存放keyList
						entityKeyList.add(datacenterKey);
						// 获取数据中心下集群信息
						ClusterUnitedVO clusterUnitedVO = getClusterDataFromApiByDc(vCenterCode,
								dcUnitedVO.getDataCenterCode());
						Assert.notNull(clusterUnitedVO, "获取" + vCenterCode + " : 数据中心-"
								+ dcUnitedVO.getName() + "下集群数据错误！");
						List<ClusterUnitedVO> clUnitedList = clusterUnitedVO.getClusterList();
						if (clUnitedList != null && clUnitedList.size() > 0) {
							for (ClusterUnitedVO clUnitedVO : clUnitedList) {
								// 存储集群实体
								String clusterKey = clUnitedVO.getClusterCode() + SPLIT
										+ vCenterCode + SPLIT + UnitedConstant.CLUSTER_STRING;
								entityMap.put(clusterKey, clUnitedVO);
								entityKeyList.add(clusterKey);
								HostUnitedVO clHostUnitedVO = getHostDataFromApiByCluster(
										vCenterCode, clUnitedVO.getClusterCode());
								Assert.notNull(clHostUnitedVO, "获取" + vCenterCode + "集群-"
										+ dcUnitedVO.getName() + "下宿主机数据错误！");
								List<HostUnitedVO> clHostUnitedList = clHostUnitedVO.getHostList();
								// 处理宿主机数据
								dealHostSystemData(clHostUnitedList, entityMap, entityKeyList,
										vCenterCode, dcUnitedVO.getDataCenterCode(),
										clUnitedVO.getClusterCode());
							}
						}
						// 获取数据中心下宿主机信息
						HostUnitedVO dcHostUnitedVO = getHostDataFromApiByDc(vCenterCode,
								dcUnitedVO.getDataCenterCode());
						Assert.notNull(dcHostUnitedVO,
								"获取" + vCenterCode + "数据中心-" + dcUnitedVO.getName() + "下宿主机数据错误！");
						List<HostUnitedVO> dcHostUnitedList = dcHostUnitedVO.getHostList();
						// 处理宿主机数据
						dealHostSystemData(dcHostUnitedList, entityMap, entityKeyList, vCenterCode,
								dcUnitedVO.getDataCenterCode(), null);
					}
				}
			}
			result.put(MAP_KEY_LIST, entityKeyList);
			result.put(MAP_KEY_MAP, entityMap);
		} catch (CloudUnitedException e) {
			log = "未获取到VMware vCenter配置信息！" + e.getMessage();
			LOG.error(log, e);
			throw new DataSynchroException(log, e);
		} catch (HttpClientException e) {
			log = "调用API接口获取数据错误！" + e.getMessage();
			LOG.error(log, e);
			throw new DataSynchroException(log, e);
		} catch (IllegalArgumentException e) {
			log = "获取参数信息错误,请查看！" + e.getMessage();
			LOG.error(log, e);
			throw new DataSynchroException(log, e);
		}
		return result;
	}

	/**
	 * 
	 * @Title: dealHostSystemData
	 * @Description: 处理宿主机数据
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws DataSynchroException
	 * @createtime 2013-7-31 上午11:09:31
	 */
	public void dealHostSystemData(List<HostUnitedVO> hostUnitedList,
			HashMap<String, ResultSet> entityMap, List<String> entityKeyList, String vCenterCode,
			String datacenterCode, String clusterCode) throws HttpClientException,
			DataSynchroException {
		if (hostUnitedList != null && hostUnitedList.size() > 0) {
			for (HostUnitedVO clHostSystemUnitedVO : hostUnitedList) {
				// 存储数据中心下宿主机实体
				String hostKey = clHostSystemUnitedVO.getHostCode() + SPLIT + vCenterCode + SPLIT
						+ UnitedConstant.HOST_STRING;
				entityMap.put(hostKey, clHostSystemUnitedVO);
				entityKeyList.add(hostKey);
				// 获取宿主机下虚拟机列表
				VirtualMachineUnitedVO clVirtualMachineUnitedVO = getVmDataFromApiByHost(
						vCenterCode, clHostSystemUnitedVO.getHostCode());
				Assert.notNull(clVirtualMachineUnitedVO, "获取" + vCenterCode + "宿主机-"
						+ clHostSystemUnitedVO.getHostname() + "下虚拟机数据错误！");
				List<VirtualMachineUnitedVO> vmUnitedList = clVirtualMachineUnitedVO.getVmList();
				if (vmUnitedList != null && vmUnitedList.size() > 0) {
					for (VirtualMachineUnitedVO vmUnitedVO : vmUnitedList) {
						// 存储虚拟机实体
						String vmKey = "";
						if (vmUnitedVO.getIsTemplate() != null
								&& vmUnitedVO.getIsTemplate() == false) {// 虚拟机
							vmKey = vmUnitedVO.getVmCode() + SPLIT + vCenterCode + SPLIT
									+ UnitedConstant.VM_STRING;
						} else if (vmUnitedVO.getIsTemplate() != null
								&& vmUnitedVO.getIsTemplate() == true) {// 模板
							vmKey = vmUnitedVO.getVmCode() + SPLIT + vCenterCode + SPLIT
									+ UnitedConstant.TEMPLATE_STRING;
						}
						entityMap.put(vmKey, vmUnitedVO);
						entityKeyList.add(vmKey);
					}
				}
				// 获取宿主机下存储
				DatastoreUnitedVO datastoreUnitedVO = getDatastoreListByHostSystem(vCenterCode,
						clHostSystemUnitedVO.getHostCode());
				Assert.notNull(datastoreUnitedVO, "获取" + vCenterCode + "宿主机-"
						+ clHostSystemUnitedVO.getHostname() + "下数据存储数据错误！");
				List<DatastoreUnitedVO> datastoreUnitedList = datastoreUnitedVO.getDatastoreList();
				if (datastoreUnitedList != null && datastoreUnitedList.size() > 0) {
					for (DatastoreUnitedVO dsUnitedVO : datastoreUnitedList) {
						String hostCode = dsUnitedVO.getHostCode();
						// 存储虚拟机实体
						String dsKey = dsUnitedVO.getDatastoreCode() + SPLIT + hostCode + SPLIT
								+ vCenterCode + SPLIT + UnitedConstant.DATASTORE_STRING;
						dsUnitedVO.setConnectCode(vCenterCode);
						dsUnitedVO.setHostCode(hostCode);
						dsUnitedVO.setDataCenterCode(datacenterCode);
						dsUnitedVO.setClusterCode(clusterCode);
						entityMap.put(dsKey, dsUnitedVO);
						entityKeyList.add(dsKey);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @Title: initCloudDbDataMap
	 * @Description:
	 * @param
	 * @return List<String>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2013-7-26 下午3:23:54
	 */
	private List<String> initCloudDbDataList() throws SQLException {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setVtype(UnitedConstant.VMWARE);
		List<String> list = unitedTreeDao.getAllUniqueEntity(unitedTreeObj);
		DataStoreObj dataStoreObj = new DataStoreObj();
		List<String> datastoreList = dataStoreDao.getAllDatastoreEntity(dataStoreObj);
		TemManObj temManObj = new TemManObj();
		temManObj.setType(VirtualTemplateType.VMWARE);
		List<String> templateList = temManDao.getAllTemplateEntity(temManObj);
		if (list == null) {
			list = new ArrayList<String>();
		}
		list.addAll(datastoreList);
		list.addAll(templateList);
		/*********** 清空虚拟机与IP的对应关系表，以便插入 ************************/
		VmIpObj viObj = new VmIpObj();
		viObj.setVtype("1");
		nasFileSysDao.delVmIpRelationByObj(viObj);

		return list;
	}

	/**
	 * 
	 * @Title: getDataCenterDataFromApi
	 * @Description: 获取数据中心数据
	 * @param
	 * @return DataCenterUnitedVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws HttpClientException
	 * @throws DataSynchroException
	 * @createtime 2013-7-26 上午11:43:11
	 */
	private DataCenterUnitedVO getDataCenterDataFromApi(String vcenterCode)
			throws HttpClientException, DataSynchroException {
		String url = "datacenter/" + VirtualConstant.VT_VMWARE + "/search/" + vcenterCode;
		DataCenterUnitedVO result = VirtualClient.get(url,
				new JacksonUtil.TypeReference<DataCenterUnitedVO>() {
				});
		if (!result.isSuccess) {
			throw new DataSynchroException(vcenterCode + " 同步数据中心数据错误！" + result.log);
		}
		return result;
	}

	/**
	 * 
	 * @Title: getClusterDataFromApiByDc
	 * @Description: 根据数据中心Id和vCenter连接Id获取集群信息
	 * @param
	 * @return DataCenterUnitedVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws HttpClientException
	 * @throws DataSynchroException
	 * @createtime 2013-7-26 上午11:43:11
	 */
	private ClusterUnitedVO getClusterDataFromApiByDc(String vcenterCode, String datacenterCode)
			throws HttpClientException, DataSynchroException {
		String url = "cluster/" + VirtualConstant.VT_VMWARE + "/search/" + vcenterCode + "/"
				+ datacenterCode;
		ClusterUnitedVO result = VirtualClient.get(url,
				new JacksonUtil.TypeReference<ClusterUnitedVO>() {
				});
		if (!result.isSuccess) {
			throw new DataSynchroException(datacenterCode + "@" + vcenterCode + " 同步集群数据错误！"
					+ result.log);
		}
		return result;
	}

	/**
	 * 
	 * @Title: getHostDataFromApiByDc
	 * @Description: 根据数据中心Id和vCenter连接Id获取宿主机信息
	 * @param
	 * @return DataCenterUnitedVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws HttpClientException
	 * @throws DataSynchroException
	 * @createtime 2013-7-26 上午11:43:11
	 */
	private HostUnitedVO getHostDataFromApiByDc(String vcenterCode, String datacenterCode)
			throws HttpClientException, DataSynchroException {
		String url = "hostsystem/" + VirtualConstant.VT_VMWARE + "/datacenter/search/"
				+ vcenterCode + "/" + datacenterCode;
		HostUnitedVO result = VirtualClient.get(url, new JacksonUtil.TypeReference<HostUnitedVO>() {
		});
		if (!result.isSuccess) {
			throw new DataSynchroException(datacenterCode + "@" + vcenterCode + " 同步宿主机数据错误！"
					+ result.log);
		}
		return result;
	}

	/**
	 * 
	 * @Title: getHostDataFromApiByCluster
	 * @Description: 根据集群Id和vCenter连接Id获取宿主机信息
	 * @param
	 * @return DataCenterUnitedVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws HttpClientException
	 * @throws DataSynchroException
	 * @createtime 2013-7-26 上午11:43:11
	 */
	private HostUnitedVO getHostDataFromApiByCluster(String vcenterCode, String clusterCode)
			throws HttpClientException, DataSynchroException {
		String url = "hostsystem/" + VirtualConstant.VT_VMWARE + "/cluster/search/" + vcenterCode
				+ "/" + clusterCode;
		HostUnitedVO result = VirtualClient.get(url, new JacksonUtil.TypeReference<HostUnitedVO>() {
		});
		if (!result.isSuccess) {
			throw new DataSynchroException(clusterCode + "@" + vcenterCode + " 同步宿主机数据错误！"
					+ result.log);
		}
		return result;
	}

	/**
	 * 
	 * @Title: getHostDataFromApiByCluster
	 * @Description: 根据集群Id和vCenter连接Id获取宿主机信息
	 * @param
	 * @return DataCenterUnitedVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws HttpClientException
	 * @throws DataSynchroException
	 * @createtime 2013-7-26 上午11:43:11
	 */
	private DatastoreUnitedVO getDatastoreListByHostSystem(String vcenterCode, String hostCode)
			throws HttpClientException, DataSynchroException {
		String url = "datastore/" + VirtualConstant.VT_VMWARE + "/search/byhost/" + vcenterCode
				+ "/" + hostCode;
		DatastoreUnitedVO result = VirtualClient.get(url,
				new JacksonUtil.TypeReference<DatastoreUnitedVO>() {
				});
		if (!result.isSuccess) {
			throw new DataSynchroException(hostCode + "@" + vcenterCode + " 同步数据存储数据错误！"
					+ result.log);
		}
		return result;
	}

	/**
	 * 
	 * @Title: getVmDataFromApiByHost
	 * @Description: 根据宿主机Id和vCenter连接Id获取虚拟机信息
	 * @param
	 * @return DataCenterUnitedVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws HttpClientException
	 * @throws DataSynchroException
	 * @createtime 2013-7-26 上午11:43:11
	 */
	private VirtualMachineUnitedVO getVmDataFromApiByHost(String vcenterCode, String hostCode)
			throws HttpClientException, DataSynchroException {
		String url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/search/" + vcenterCode + "/"
				+ hostCode;
		VirtualMachineUnitedVO result = VirtualClient.get(url,
				new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
				});
		if (!result.isSuccess) {
			throw new DataSynchroException(hostCode + "@" + vcenterCode + " 同步虚拟机数据错误！"
					+ result.log);
		}
		return result;
	}

	/**
	 * 
	 * @Title: getParentNodeId
	 * @Description: 查询父节点ID
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2013-7-26 下午4:55:35
	 */
	private String getParentNodeId(String vcenterCode, String entityCode) throws SQLException {
		UnitedTreeObj parentNode = new UnitedTreeObj();
		parentNode.setConnect_id(vcenterCode);
		parentNode.setUuid(entityCode);
		String parentId = unitedTreeDao.queryForUnitedTree(parentNode).get(0).getId();
		return parentId;
	}

	/**
	 * 
	 * @Title: saveTreeNode
	 * @Description: 保存树节点数据
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2013-7-26 下午5:02:21
	 */
	private void saveTreeNode(ResultSet eneityVO, String entityType, String parentId)
			throws SQLException {
		UnitedTreeObj treeNode = new UnitedTreeObj();
		if (UnitedConstant.DATACENTER.equals(entityType)) {
			treeNode = RevertEntity.toTreeDataCenter((DataCenterUnitedVO) eneityVO);
		} else if (UnitedConstant.CLUSTER.equals(entityType)) {
			treeNode = RevertEntity.toTreeCluster((ClusterUnitedVO) eneityVO);
		} else if (UnitedConstant.HOST.equals(entityType)) {
			treeNode = RevertEntity.toTreeHost((HostUnitedVO) eneityVO);
		} else if (UnitedConstant.VM.equals(entityType)) {
			treeNode = RevertEntity.toTreeVM((VirtualMachineUnitedVO) eneityVO);
		}
		treeNode.setVtype(UnitedConstant.VMWARE);
		treeNode.setType(entityType);
		treeNode.setParent_id(parentId);
		unitedTreeDao.insertByObj(treeNode);
	}

	/**
	 * 
	 * @Title: saveTbCloud2HostInfo
	 * @Description: 保存TB_cloud2_host_info数据
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-26 下午5:53:42
	 */
	private void saveTbCloud2HostInfo(HostUnitedVO hostVO) {
		TbCloud2HostInfoObj tbCloud2HostInfoObj = RevertEntity.toTbCloud2HostInfoObj(hostVO,
				HostSystemType.VMWARE);
		int id = hostInfoDao.getIdSequence();
		tbCloud2HostInfoObj.setId(id);
		tbCloud2HostInfoObj.setEq_id(id + "");
		hostInfoDao.insertByObj(tbCloud2HostInfoObj);
	}

	/**
	 * 
	 * @Title: updateTbCloud2HostInfoData
	 * @Description: 更新宿主机数据
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-10 下午8:30:11
	 */
	private void updateTbCloud2HostInfoData(HostUnitedVO hostVO) {
		TbCloud2HostInfoObj tbCloud2HostInfoObj = RevertEntity.toTbCloud2HostInfoObj(hostVO,
				HostSystemType.VMWARE);
		hostInfoDao.updateUnitedDataByObj(tbCloud2HostInfoObj);
	}

	/**
	 * 
	 * @Title: getParentNodeId
	 * @Description: 查询父节点ID
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2013-7-26 下午4:55:35
	 */
	public String getTbCloud2HostId(VirtualMachineUnitedVO hostVO) throws SQLException {
		String eq_id = "";
		TbCloud2HostInfoObj tbCloud2HostInfoObj = new TbCloud2HostInfoObj();
		tbCloud2HostInfoObj.setConnectId(hostVO.getConnectCode());
		tbCloud2HostInfoObj.setH_uuid(hostVO.getHostCode());
		TbCloud2HostInfoObj result = hostInfoDao.queryByObj(tbCloud2HostInfoObj);
		if (result != null) {
			eq_id = result.getEq_id();
		}
		return eq_id;
	}

	/**
	 * 
	 * @Title: saveTbCloud2VMHostInfo
	 * @Description: 保存tb_cloud2_vmhost_info
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-26 下午5:54:02
	 */
	public void saveTbCloud2VMHostInfo(VirtualMachineUnitedVO hostVO, String eq_id) {
		VMHostObj vmHostObj = RevertEntity.toVMHostObj(hostVO, VirtualMachineType.VMWARE);
		vmHostObj.setEQ_ID(eq_id);
		int id = vmHostDao.queryVhostIdSequence();
		vmHostObj.setID(id);
		vmHostObj.setVH_ID(id + "");
		// 多网卡的特殊处理
		getIpInfo(vmHostObj, hostVO);
		/*
		 * 配置标注，用于北京电信公有云，若不配置，则无影响
		 */
		vmHostObj.setVH_UNITID(PublicCloudUtil.analysisVMNameIfSell(hostVO));
		try {
			vmHostObj.setVH_PASS(DoubleEncryptUtils.encrypt(hostVO.getPassword()));
		} catch (Exception e) {
			LOG.error("虚拟机密码加密错误！" + e.getMessage(), e);
		}
		vmHostDao.insertByVMhostObj(vmHostObj);
	}

	/**
	 * 
	 * @Title: updateTbCloud2VMHostInfo
	 * @Description: 更新tb_cloud2_vmhost_info
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-26 下午5:54:02
	 */
	public void updateTbCloud2VMHostInfo(VirtualMachineUnitedVO hostVO, String eq_id) {
		VMHostObj vmHostObj = RevertEntity.toVMHostObj(hostVO, VirtualMachineType.VMWARE);
		vmHostObj.setEQ_ID(eq_id);
		getIpInfo(vmHostObj, hostVO);
		/*
		 * 配置标注，用于北京电信公有云，若不配置，则无影响
		 */
		vmHostObj.setVH_UNITID(PublicCloudUtil.analysisVMNameIfSell(hostVO));
		vmHostDao.updateVMHostInfo(vmHostObj);
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
	 * @throws SQLException
	 * @createtime 2013-7-26 下午8:25:34
	 */
	private void deleteTreeNode(String vcenterCode, String entityCode) throws SQLException {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setConnect_id(vcenterCode);
		unitedTreeObj.setUuid(entityCode);
		unitedTreeDao.deleteByUuidAndConnectId(unitedTreeObj);
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
	private void deleteTbCloud2HostInfoData(String vcenterCode, String entityCode) {
		TbCloud2HostInfoObj tbCloud2HostInfoObj = new TbCloud2HostInfoObj();
		tbCloud2HostInfoObj.setConnectId(vcenterCode);
		tbCloud2HostInfoObj.setH_uuid(entityCode);
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
	private void deleteTbCloud2VMHostInfoData(String vcenterCode, String entityCode) {
		VMHostObj vmHostObj = new VMHostObj();
		vmHostObj.setConnectId(vcenterCode);
		vmHostObj.setVH_UUID(entityCode);
		vmHostDao.deleteByObj(vmHostObj);
	}

	/**
	 * 
	 * @Title: updateTreeNode
	 * @Description: 更新当前实体父节点数据
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2013-7-26 下午5:02:21
	 */
	private void updateTreeParentNode(ResultSet eneityVO, String entityType, String parentId)
			throws SQLException {
		UnitedTreeObj treeNode = new UnitedTreeObj();
		if (UnitedConstant.DATACENTER.equals(entityType)) {// 更新数据中心数据与原来有差异，多加了一层地域节点
			treeNode = RevertEntity.toTreeDataCenter((DataCenterUnitedVO) eneityVO);
			// 通过vCenterCode来查找对应的地域节点----taoxue修改----
		} else if (UnitedConstant.CLUSTER.equals(entityType)) {
			treeNode = RevertEntity.toTreeCluster((ClusterUnitedVO) eneityVO);
		} else if (UnitedConstant.HOST.equals(entityType)) {
			treeNode = RevertEntity.toTreeHost((HostUnitedVO) eneityVO);
		} else if (UnitedConstant.VM.equals(entityType)) {
			treeNode = RevertEntity.toTreeVM((VirtualMachineUnitedVO) eneityVO);
		}
		treeNode.setParent_id(parentId);
		unitedTreeDao.updateByObj(treeNode);
	}

	/**
	 * 
	 * @Title: saveTbYicloudDatastore
	 * @Description: 保存VMware数据存储
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-31 上午11:12:33
	 */
	private void saveTbYicloudDatastore(DatastoreUnitedVO vo) {
		DataStoreObj dataStoreObj = RevertEntity.toDataStoreObj(vo);
		dataStoreObj.setDATACENTER_ID(vo.getDataCenterCode());
		dataStoreObj.setCLUSTER_ID(vo.getClusterCode());
		dataStoreObj.setConnectId(vo.getConnectCode());
		dataStoreObj.setHOST_ID(vo.getHostCode());
		dataStoreObj.setStoretype("hostFile");
		dataStoreDao.insertDatastore(dataStoreObj);
	}

	/**
	 * 
	 * @Title: updateTbYicloudDatastore
	 * @Description: 更新VMware数据存储
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-31 上午11:12:33
	 */
	private void updateTbYicloudDatastore(DatastoreUnitedVO vo) {
		DataStoreObj dataStoreObj = RevertEntity.toDataStoreObj(vo);
		dataStoreObj.setDATACENTER_ID(vo.getDataCenterCode());
		dataStoreObj.setCLUSTER_ID(vo.getClusterCode());
		dataStoreObj.setConnectId(vo.getConnectCode());
		dataStoreObj.setHOST_ID(vo.getHostCode());
		dataStoreObj.setStoretype("hostFile");
		dataStoreDao.updateDatastoreByObj(dataStoreObj);
	}

	/**
	 * 
	 * @Title: deleteTbYicloudDatastore
	 * @Description: 根据连接ID及实体ID删除数据存储数据节点
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-26 下午8:25:34
	 */
	private void deleteTbYicloudDatastore(String vcenterCode, String entityCode) {
		DataStoreObj dataStoreObj = new DataStoreObj();
		dataStoreObj.setConnectId(vcenterCode);
		dataStoreObj.setStore_uuid(entityCode);
		List<DataStoreObj> list = new ArrayList<DataStoreObj>();
		list = dataStoreDao.queryForListByObj(dataStoreObj);
		if (list.size() > 0 && list.get(0) != null) {
			if ("hostFile".equals(list.get(0))) {
				dataStoreDao.deleteByUuid(dataStoreObj);
			}
		}
	}

	/**
	 * 
	 * @Title: saveTbTemManage
	 * @Description: 保存VMware虚拟机模板信息
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-31 下午7:56:55
	 */
	public void saveTbTemManage(VirtualMachineUnitedVO hostVO) {
		TemManObj temManObj = RevertEntity.toTemManObj(hostVO, VirtualTemplateType.VMWARE);
		temManObj.setIsPhysical("0");

		temManDao.insertByObj(temManObj);
	}

	/**
	 * 
	 * @Title: updateTbTemManage
	 * @Description: 更新VMware虚拟机模板信息
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-31 下午7:56:55
	 */
	public void updateTbTemManage(VirtualMachineUnitedVO hostVO) {
		TemManObj temManObj = RevertEntity.toTemManObj(hostVO, VirtualTemplateType.VMWARE);
		TemManObj tem = new TemManObj();
		tem.setTemplateCode(temManObj.getTemplateCode());
		tem.setConnectId(temManObj.getConnectId());
		tem = temManDao.queryOneTemManObj(tem);
		if (tem != null) {
			temManObj.setId(tem.getId());
		}
		temManDao.updateByHostCodeAndTemplateCode(temManObj);
	}

	/**
	 * 
	 * @Title: deleteTbTemManage
	 * @Description: 删除VMware虚拟机模板信息
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-31 下午7:56:55
	 */
	public void deleteTbTemManage(String vcenterCode, String entityCode) {
		TemManObj temManObj = new TemManObj();
		temManObj.setConnectId(vcenterCode);
		temManObj.setTemplateCode(entityCode);
		temManDao.deleteByHostCodeAndTemplateCode(temManObj);
	}

	/**
	 * 
	 * @Title: queryTbCloud2HostInfoByHostUnitedVO
	 * @Description: 查询TB_CLOUD2_HOST_INFO中是否有数据
	 * @param
	 * @return Boolean
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-7 上午11:15:49
	 */
	private Boolean queryTbCloud2HostInfoByHostUnitedVO(HostUnitedVO hostUnitedVO) {
		Boolean isExist = true;
		TbCloud2HostInfoObj tbCloud2HostInfoObj = new TbCloud2HostInfoObj();
		tbCloud2HostInfoObj.setConnectId(hostUnitedVO.getConnectCode());
		tbCloud2HostInfoObj.setH_uuid(hostUnitedVO.getHostCode());
		TbCloud2HostInfoObj result = hostInfoDao.queryByObj(tbCloud2HostInfoObj);
		if (result == null) {
			isExist = false;
		}
		return isExist;
	}

	/**
	 * 
	 * @Title: queryTbCloud2VMHostInfoByHostUnitedVO
	 * @Description: 查询TB_CLOUD2_VMHOST_INFO中是否有数据
	 * @param
	 * @return Boolean
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-7 上午11:15:49
	 */
	public Boolean queryTbCloud2VMHostInfoByVirtualMachineUnitedVO(
			VirtualMachineUnitedVO virtualMachineUnitedVO) {
		Boolean isExist = true;
		VMHostObj vmHostObj = new VMHostObj();
		vmHostObj.setConnectId(virtualMachineUnitedVO.getConnectCode());
		vmHostObj.setVH_UUID(virtualMachineUnitedVO.getVmCode());
		VMHostObj result = vmHostDao.queryByObj(vmHostObj);
		if (result == null) {
			isExist = false;
		}
		return isExist;
	}

	/**
	 * 
	 * @Title: queryTbCloud2VMHostInfoByHostUnitedVO
	 * @Description: 查询TB_CLOUD2_VMHOST_INFO中是否有数据
	 * @param
	 * @return Boolean
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-7 上午11:15:49
	 */
	private Boolean queryTbTemManByVirtualMachineUnitedVO(
			VirtualMachineUnitedVO virtualMachineUnitedVO) {
		Boolean isExist = true;
		TemManObj temManObj = new TemManObj();
		temManObj.setConnectId(virtualMachineUnitedVO.getConnectCode());
		temManObj.setTemplateCode(virtualMachineUnitedVO.getVmCode());
		TemManObj result = temManDao.queryOneTemManObj(temManObj);
		if (result == null) {
			isExist = false;
		}
		return isExist;
	}

	/**
	 * @Title: updateVMhostAfterMigrate
	 * @Description: 迁移之后重置主机和虚拟机的关系
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-2 下午5:33:17
	 */
	public void updateVMhostAfterMigrate(VirtualMachineUnitedVO vi, String eq_id) {
		VMHostObj vmobj = new VMHostObj();
		vmobj.setVH_UUID(vi.getVmCode());
		vmobj.setConnectId(vi.getConnectCode());
		vmobj = vmHostDao.queryByObj(vmobj);
		vmobj.setEQ_ID(eq_id);
		vmobj.setHostCode(vi.getHostCode());
		vmHostDao.updateByObj(vmobj);
	}

	/**
	 * 
	 * @Title: getParentIdByVCenterCode
	 * @Description: 通过vCenter查找对应的数据中心的父节点（由于安徽移动需要，在原来数据中心和根节点之间添加了地域节点）
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-11-13 上午11:53:52
	 */
	public String getParentIdByVCenterCode(String vCenterCode) {
		String areaId = "";
		try {
			areaId = areaResourcePoolRelationService.getAreaId(vCenterCode, "1");
			if (areaId == null || "".equals(areaId)) {
				areaId = ROOT_ID;
			}
		} catch (Exception e) {
			areaId = ROOT_ID;
			e.printStackTrace();
		}
		return areaId;
	}

	/**
	 * 
	 * @Title: validateHostData
	 * @Description: 校对主机中已分配和未分配主机的信息（当前只适用于安徽的需要）
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-29 下午4:33:43
	 */
	public void validateHostData() {

		LOG.info("数据同步匹配,分配与未分配数据___Start;");
		TbCloud2HostInfoObj host = new TbCloud2HostInfoObj();
		host.setAllocated(1);// 1 已分配
		List<TbCloud2HostInfoObj> allocatedList = hostInfoDao.queryForListByObj(host);
		LOG.info("已分配个数：" + allocatedList.size());
		host.setAllocated(0);// 0未分配
		List<TbCloud2HostInfoObj> unAllocatedList = hostInfoDao.queryForListByObj(host);
		LOG.info("未分配个数：" + unAllocatedList.size());
		if (allocatedList != null) {
			for (TbCloud2HostInfoObj a1 : allocatedList) {
				for (TbCloud2HostInfoObj a2 : unAllocatedList) {
					String ip = a1.getEq_ip();
					String ip2 = a2.getEq_ip();
					String vType = a1.getHasvertual();
					String vType2 = a2.getHasvertual();
					TbCloud2HostInfoObj un = new TbCloud2HostInfoObj();
					if (ip != null && ip.equals(ip2) && vType != null && vType.equals(vType2)) {
						LOG.info("删除：" + ip + "  " + vType);
						try {
							un.setEq_ip(ip);
							un.setEq_type(vType);
							un.setAllocated(0);
							hostInfoDao.deleteByObj(un);
						} catch (Exception e) {
						}
						try {
							UnitedTreeObj tree = new UnitedTreeObj();
							if (a2.getEq_id() != null && !"".equals(a2.getEq_id())) {
								tree.setUuid(a2.getEq_id());
								tree.setType(UnitedConstant.HOST);
								unitedTreeDao.deleteByObj(tree);
							}

						} catch (Exception e) {
						}

					}

				}
			}
		}

	}

	/**
	 * 
	 * @Title: adjustIpType
	 * @Description: 判断IP类型
	 * @param
	 * @return void
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014年5月21日10:59:01
	 */
	private String adjustIpType(String ip) {
		Pattern privateIp = Pattern.compile("^"
				+ PropertiesUtil.getString("properties.IP", "private") + "$");
		Pattern publicIp = Pattern.compile("^"
				+ PropertiesUtil.getString("properties.IP", "public") + "$");
		Matcher m1 = privateIp.matcher(ip);
		Matcher m2 = publicIp.matcher(ip);
		if (m1.find()) {// 私网IP地址
			return IpType.inner_ip;
		}
		if (m2.find()) {// 外网IP地址
			return IpType.intranet_ip;
		} else {// 如有需要可增加
			return "0";
		}
	}

	/**
	 * 
	 * @Title: analysisVMIfSell
	 * @Description: 分析当前虚拟机是否可以出售，用于北京电信
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-8-13 下午4:20:09
	 */
	private String analysisVMIfSell(VirtualMachineUnitedVO hostVO) {
		String result = VirtualMachineSellType.NOT_SELL;
		TbGlobalConfigObj param = new TbGlobalConfigObj();
		param.setKEY(VirtualMachineSellType.GLOBAL_VM_SELL_KEY);
		param = tbGlobalConfigDao.queryByObj(param);
		if (param != null) {
			LOG.info("全局配置表未配置global_vm_sell_cluster集群参数已经获取，开始处理数据  " + param.getVALUE() + "！");
			String[] params = param.getVALUE().split(",");
			for (String param_value : params) {
				if (hostVO.getClusterCode().equals(param_value)) {
					result = VirtualMachineSellType.SELL;
					break;
				}
			}
		} else {
			LOG.info("全局配置表未配置global_vm_sell_cluster集群参数，此处不处理数据！");
		}
		return result;
	}

	private void getIpInfo(VMHostObj vmHostObj, VirtualMachineUnitedVO hostVO) {
		List<VirtualNicUnitedVO> list = new ArrayList<VirtualNicUnitedVO>();
		list = hostVO.getVirtualNicList();
		/************* 保存多网卡信息 *******************/
		if (list != null && list.size() > 0) {
			for (VirtualNicUnitedVO vo : list) {// 入关系表
				VmIpObj viObj = new VmIpObj();
				viObj.setId(RandomUUID.getUuid());
				viObj.setVm_uuid(vmHostObj.getVH_UUID());
				viObj.setIp(vo.getIpAddress());
				viObj.setConnectid(vmHostObj.getConnectId());
				viObj.setVtype(UnitedConstant.VMWARE);
				viObj.setIpType(adjustIpType(vo.getIpAddress()));
				if (vo.getIpAddress().contains(";")) {
					String[] ips = vo.getIpAddress().split(";");
					for (String ip : ips) {
						viObj.setIp(ip);
						nasFileSysDao.insertVmIpRelation(viObj);
					}
				} else {
					viObj.setIp(vo.getIpAddress());
					nasFileSysDao.insertVmIpRelation(viObj);
				}
			}
		}
		if (list != null && list.size() > 1) {// 若为多网卡
			String ipLocation = PropertiesUtil.getString("properties.IP", "IP");
			Pattern p = Pattern.compile("^" + ipLocation + "$");
			boolean flag = false; // 标志是否找到符合的IP
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getIpAddress().contains(";")) {// 存在ip拼接到一起的情况
					String[] ips = list.get(i).getIpAddress().split(";");
					for (int j = 0; j < ips.length; j++) {
						Matcher m = p.matcher(ips[j]);
						if (m.find()) {// 拼接的ip中存在配置文件中的
							vmHostObj.setVH_IP(ips[j]);
							flag = true;// 找到符合的 IP
							break;
						} else {
							if (j == (ips.length - 1)) {
								vmHostObj.setVH_IP(ips[0]);
							}
						}
					}
					if (flag) {// 若已找到符合的ip,则直接跳出循环
						break;
					}
				} else {
					Matcher m = p.matcher(list.get(i).getIpAddress());
					if (m.find()) {// 存在配置文件IP中配置的IP地址段，选取其中一个作为IP地址（默认满足条件的第一个）
						vmHostObj.setVH_IP(list.get(i).getIpAddress());
						break;
					} else {
						if (i == (list.size() - 1)) {// 不存在配置文件IP中配置的IP地址段，选取其中一个IP（默认第一个）
							vmHostObj.setVH_IP(list.get(0).getIpAddress());
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @Title: dealVmDatastoreRelation
	 * @Description: 处理虚拟机与数据存储关系
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-9-16 下午2:26:11
	 */
	private void dealVmDatastoreRelation(VirtualMachineUnitedVO virtualMachineUnitedVO) {
		// 删除虚拟机与数据存储关联关系
		unitedVMService.deleteVmDatastoreRelation(virtualMachineUnitedVO.getConnectCode(),
				virtualMachineUnitedVO.getVmCode(), UnitedConstant.VMWARE);
		// 保存虚拟机与数据存储关联关系
		unitedVMService.dealVmDatastoreRelation(virtualMachineUnitedVO, UnitedConstant.VMWARE);
	}
}
