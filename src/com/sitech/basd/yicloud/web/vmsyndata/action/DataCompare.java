package com.sitech.basd.yicloud.web.vmsyndata.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.SpringUtil;
import com.sitech.basd.yicloud.dao.cluster.ClusterDao;
import com.sitech.basd.yicloud.domain.cluster.ClusterObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreInfo;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.vmman.ClusterInfoObj;
import com.sitech.basd.yicloud.domain.vmman.DataCenterInfoObj;
import com.sitech.basd.yicloud.service.cluster.ClusterService;
import com.sitech.basd.yicloud.service.datastore.DataStoreService;
import com.sitech.basd.yicloud.service.entitytree.EntityTreeService;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.JSONUtil;
import com.sitech.basd.yicloud.util.JsonUtils;
import com.sitech.basd.yicloud.util.TypeConstant;
import com.sitech.basd.yicloud.web.vmsyndata.constant.CompConstant;
import com.sitech.basd.yicloud.web.vmsyndata.form.DataConstant;
import com.sitech.ws.Operation;
import com.sitech.ws.web.NoticeUtil;

/**
 * 
 * <p>
 * Title: DataCompare
 * </p>
 * <p>
 * Description: 接口和数据库数据比对
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Oct 11, 2012 9:23:07 AM
 * 
 */
public class DataCompare extends BaseAction {
	private static final Logger logger = Logger.getLogger(DataCompare.class.getName());
	/**
	 * @Fields serialVersionUID : 自动生成序列号
	 */
	private static final long serialVersionUID = -4979497695152463348L;

	private EntityTreeService entityTreeService;
	private HostInfoService hostInfoService;
	private VMHostService vmHostService;
	private DataStoreService dataStoreService;
	private ClusterService clusterService;
	private ClusterDao clusterDao;

	public EntityTreeService getEntityTreeService() {
		return (EntityTreeService) SpringUtil.getBean("entityTreeService");
	}

	public ClusterDao getClusterDao() {
		return (ClusterDao) SpringUtil.getBean("clusterDao");
	}

	public ClusterService getClusterService() {
		return (ClusterService) SpringUtil.getBean("clusterService");
	}

	public HostInfoService getHostInfoService() {
		return (HostInfoService) SpringUtil.getBean("hostInfoService");
	}

	public VMHostService getVmHostService() {
		return (VMHostService) SpringUtil.getBean("vmHostService");
	}

	public DataStoreService getDataStoreService() {
		return (DataStoreService) SpringUtil.getBean("dataStoreService");
	}

	/**
	 * 
	 * @Title: getInterfaceMap
	 * @Description: 获取接口信息Map
	 * @param
	 * @return Map<String,List<?>>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 11, 2012 9:25:53 AM
	 */
	public Map<String, Object> getInterfaceMap(String type, String... params) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (DataConstant.TYPE_VM.equals(type)) {
			String vm_param = "/vmware/domain/synDomainData/[" + "]/";
			String vm = InvokeUtil.invoke(vm_param);
			List<VMHostObj> vmList = (List<VMHostObj>) JsonUtils.jsonToList(vm, VMHostObj.class);
			for (VMHostObj vmObj : vmList) {
				map.put(vmObj.getVH_UUID(), vmObj);
			}
		} else if (DataConstant.TYPE_HOST.equals(type)) {
			String host_param = "/vmware/host/synHostData/[cluName:" + "]/";
			String host = InvokeUtil.invoke(host_param);
			List<TbCloud2HostInfoObj> hostList = (List<TbCloud2HostInfoObj>) JsonUtils.jsonToList(
					host, TbCloud2HostInfoObj.class);
			for (TbCloud2HostInfoObj hos : hostList) {
				map.put(hos.getH_uuid(), hos);
			}
		} else if (DataConstant.TYPE_DC.equals(type)) {
			String param = "/vmware/domain/synDataCenterData";
			String result = InvokeUtil.invoke(param);
			List<DataCenterInfoObj> dcList = (List<DataCenterInfoObj>) JsonUtils.jsonToList(result,
					DataCenterInfoObj.class);
			for (DataCenterInfoObj dc : dcList) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put(CompConstant.VDC, dc.getNAME());
				resMap.put(CompConstant.VDC_CODE, dc.getUuid());
				map.put(dc.getUuid(), resMap);
			}
		} else if (DataConstant.TYPE_CLUSTER.equals(type)) {
			String cluster_param = "/vmware/domain/synClusterData/[" + "]/";
			String cluster = InvokeUtil.invoke(cluster_param);
			List<ClusterInfoObj> cluList = (List<ClusterInfoObj>) JsonUtils.jsonToList(cluster,
					ClusterInfoObj.class);
			for (ClusterInfoObj clu : cluList) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put(CompConstant.CLUSTER, clu.getNAME());
				resMap.put(CompConstant.CLUSTER_CODE, clu.getC_uuid());
				resMap.put(CompConstant.CLUSTER_HA, clu.getHastate());
				resMap.put(CompConstant.CLUSTER_DRS, clu.getDrsstate());
				map.put(clu.getC_uuid(), resMap);
			}
		} else if (DataConstant.TYPE_STORAGE.equals(type)) {

		} else if (DataConstant.TYPE_VNIC.equals(type)) {

		} else if (DataConstant.TYPE_VPORTGROU.equals(type)) {

		} else if (DataConstant.TYPE_HOST_STORAGE.equals(type)) {// 主机的存储
			String hostName = params[0];
			String code = params[1];
			String url = "/vmware/storage/collDatastoreInfo/[hostname:" + code + "]/";
			// 查询主机在哪个数据中心和集群下
			// EntityConObj conObj = new EntityConObj();
			// conObj.setName(hostName);
			// conObj.setType(Integer.valueOf(TypeConstant.VMWARE_HOST));
			// List<EntityConObj> conList = this.getEntityTreeService()
			// .queryEntityData(conObj);
			// if (conList != null && conList.size() > 0) {
			// conObj = conList.get(0);
			// }
			// int dcId = conObj.getDataCenterId();
			// int clusterId = conObj.getClusterId();

			EntityTreeObj treeObj = new EntityTreeObj();
			treeObj.setName(hostName);
			treeObj.setType(TypeConstant.VMWARE_HOST);
			treeObj = this.getEntityTreeService().queryTreeNode(treeObj);
			int dcId = 0;
			int clusterId = 0;
			if (treeObj != null) {
				// 查询集群Id
				int treeClusterId = treeObj.getParentId();
				EntityTreeObj treeClusterObj = new EntityTreeObj();
				treeClusterObj.setId(treeClusterId);
				treeClusterObj = this.getEntityTreeService().queryTreeNode(treeClusterObj);
				if (treeClusterObj != null) {
					if (treeClusterObj.getType().equals(TypeConstant.VMWARE_CLUSTER)) {
						dcId = treeClusterObj.getParentId();
						String treeClusterEntityId = "";
						treeClusterEntityId = treeClusterObj.getEntityId();
						ClusterObj clusterObj = new ClusterObj();
						clusterObj.setC_uuid(treeClusterEntityId);
						clusterObj = this.getClusterService().queryByObj(clusterObj);
						if (clusterObj != null) {
							clusterId = clusterObj.getId();
						}
					} else if (treeObj.getType().equals(TypeConstant.VMWARE_DATACENTER)) {
						dcId = treeObj.getParentId();
					}
				}
			}

			String result = InvokeUtil.invoke(url);
			HashMap<String, DataStoreInfo> dsmap = JSONUtil.fromJSON(result,
					new JSONUtil.TypeReference<HashMap<String, DataStoreInfo>>() {
					});
			Collection<DataStoreInfo> c = dsmap.values();
			for (Iterator<DataStoreInfo> it = c.iterator(); it.hasNext();) {
				DataStoreInfo infoObj = it.next();
				DataStoreObj dsObj = new DataStoreObj();
				dsObj.setNAME(infoObj.getName());
				dsObj.setFREE_SPACE(infoObj.getFreeSpace());
				dsObj.setSTORAGE_URL(infoObj.getStorageUrl());
				dsObj.setHOST_ID(code);
				dsObj.setCAPACITY(infoObj.getCapacity());
				dsObj.setTYPE(infoObj.getType());
				dsObj.setDATACENTER_ID(dcId + "");
				dsObj.setCLUSTER_ID(clusterId + "");
				dsObj.setStore_uuid(infoObj.getCode());
				map.put(hostName + "_" + dsObj.getNAME(), dsObj);
			}
		}
		return map;
	}

	/**
	 * 
	 * @Title: getDBMap
	 * @Description: 获取数据库信息Map
	 * @param
	 * @return Map<String,List<?>>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 11, 2012 9:25:53 AM
	 */
	public Map<String, Object> getDBMap(String type, String... params) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (DataConstant.TYPE_VM.equals(type)) {
			VMHostObj obj = new VMHostObj();
			obj.setVH_TYPE("1");
			obj.setVH_TYPE_1("5");
			List<VMHostObj> vmList = this.getVmHostService().queryForListByObj(obj);
			for (VMHostObj vmObj : vmList) {
				map.put(vmObj.getVH_UUID(), vmObj);
			}
		} else if (DataConstant.TYPE_HOST.equals(type)) {
			TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
			List<TbCloud2HostInfoObj> hostList = this.getHostInfoService().queryForListByObj(obj);
			for (TbCloud2HostInfoObj hostObj : hostList) {
				map.put(hostObj.getH_uuid(), hostObj);
			}
		} else if (DataConstant.TYPE_DC.equals(type)) {
			EntityTreeObj treeObj = new EntityTreeObj();
			treeObj.setType("8");
			List<EntityTreeObj> list = this.getEntityTreeService().queryClusterList(treeObj);
			for (EntityTreeObj tree : list) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put(CompConstant.VDC, tree.getName());
				resMap.put(CompConstant.VDC_CODE, tree.getEntityId());
				map.put(tree.getEntityId(), resMap);
			}
		} else if (DataConstant.TYPE_CLUSTER.equals(type)) {
			ClusterObj obj = new ClusterObj();
			List<ClusterObj> list = this.getClusterService().queryListByObj(obj);
			for (ClusterObj co : list) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put(CompConstant.CLUSTER, co.getName());
				resMap.put(CompConstant.CLUSTER_CODE, co.getC_uuid());
				resMap.put(CompConstant.CLUSTER_HA, co.getHastate());
				resMap.put(CompConstant.CLUSTER_DRS, co.getDrsstate());
				map.put(co.getC_uuid(), resMap);
			}
		} else if (DataConstant.TYPE_STORAGE.equals(type)) {

		} else if (DataConstant.TYPE_VNIC.equals(type)) {

		} else if (DataConstant.TYPE_VPORTGROU.equals(type)) {

		} else if (DataConstant.TYPE_HOST_STORAGE.equals(type)) {
			/*
			 * //查询主机在哪个数据中心和集群下 String hostName = params[0]; EntityConObj
			 * conObj = new EntityConObj(); conObj.setName(hostName);
			 * conObj.setType(Integer.valueOf(TypeConstant.VMWARE_HOST));
			 * List<EntityConObj> conList = this.getEntityTreeService()
			 * .queryEntityData(conObj); if (conList != null && conList.size() >
			 * 0) { conObj = conList.get(0); }
			 */
			String hostName = params[0];
			String code = params[1];
			DataStoreObj dsObj = new DataStoreObj();
			dsObj.setHOST_ID(code);
			List<DataStoreObj> dsList = this.getDataStoreService().queryForListByObj(dsObj);
			for (DataStoreObj ds : dsList) {
				ds.setHOST_ID(code);
				map.put(hostName + "_" + ds.getNAME(), ds);
			}

		}
		return map;
	}

	/**
	 * 
	 * @Title: dataCompare
	 * @Description: 数据比对
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 11, 2012 3:18:01 PM
	 */
	public void dataCompare(String type, String... params) {
		Map<String, Object> interMap = getInterfaceMap(type, params);
		Map<String, Object> dbMap = getDBMap(type, params);
		// 判断数据比对是否一致，若一致，则不进行遍历
		if (interMap.equals(dbMap)) {
			logger.info(type + "数据比对一致~");
		} else {
			// 遍历接口返回数据
			Iterator<Entry<String, Object>> interIterator = interMap.entrySet().iterator();
			while (interIterator.hasNext()) {
				Entry<String, Object> entry = interIterator.next();
				String key = entry.getKey();
				/** 判断实体类型 */
				if (DataConstant.TYPE_VM.equals(type)) {
					VMHostObj value = (VMHostObj) entry.getValue();
					if (dbMap.get(key) == null) {// vCenter中存在，DB中不存在
						logger.info("DB中不存在!---" + value.getVH_NAME());
						try {
							NoticeUtil.getInstance().addVM(value.getVH_UUID());
						} catch (Exception e) {

						}
						// 注： VMHostObj--VH_ID字段暂定值，后续需修改
						int vmId = this.getVmHostService().queryVhostIdSequence();
						value.setID(vmId);
						int rs = this.getVmHostService().insertByVMhostObj(value);
						/** 查询VM关系 */
						String vm_param = "/vmware/entity/singlerelation/[type:" + CompConstant.VM
								+ "].[code:" + value.getVH_UUID() + "]/";
						String vm = InvokeUtil.invoke(vm_param);
						try {
							JSONObject clus = JSONObject.fromObject(vm);
							String host_code = (String) clus.get(CompConstant.HOST_CODE);
							String vm_name = (String) clus.get(CompConstant.VM);
							String vm_code = (String) clus.get(CompConstant.VM_CODE);
							String cluster_code = (String) clus.get(CompConstant.CLUSTER_CODE);
							String dvc_code = (String) clus.get(CompConstant.VDC_CODE);
							EntityTreeObj parent = new EntityTreeObj();
							parent.setEntityId(host_code);
							parent = this.getEntityTreeService().queryTreeNode(parent);
							EntityTreeObj vmObj = new EntityTreeObj();
							vmObj.setType("0");
							vmObj.setParentId(parent.getId());
							vmObj.setName(vm_name);
							vmObj.setEntityId(vm_code);
							int vmInsert = this.getEntityTreeService().insertTreeNode(vmObj);
						} catch (Exception e) {
							logger.error("对比虚拟机异常：" + e.getMessage());
						}
					} else {// vCenter中存在，DB中存在,比对是否有差异
						if (!value.equals((VMHostObj) dbMap.get(key))) {
							int result = this.getVmHostService().updateInterByObj(value);
						}
					}
					/** 查询对比VM关系 */
					String vm_param = "/vmware/entity/singlerelation/[type:" + CompConstant.VM
							+ "].[code:" + value.getVH_UUID() + "]/";
					String vm = InvokeUtil.invoke(vm_param);
					try {
						JSONObject clus = JSONObject.fromObject(vm);
						String host_code = (String) clus.get(CompConstant.HOST_CODE);
						String vm_name = (String) clus.get(CompConstant.VM);
						String vm_code = (String) clus.get(CompConstant.VM_CODE);
						String cluster_code = (String) clus.get(CompConstant.CLUSTER_CODE);
						String dvc_code = (String) clus.get(CompConstant.VDC_CODE);
						EntityTreeObj parent = new EntityTreeObj();
						parent.setEntityId(host_code);
						parent = this.getEntityTreeService().queryTreeNode(parent);
						EntityTreeObj vmObj = new EntityTreeObj();
						vmObj.setEntityId(vm_code);
						vmObj = this.getEntityTreeService().queryTreeNode(vmObj);
						if (vmObj != null) {
							vmObj.setEntityId(vm_code);
							vmObj.setType(TypeConstant.VMWARE_VM);
							vmObj.setParentId(parent.getId());
							vmObj.setName(vm_name);
							int vmInsert = this.getEntityTreeService().updateTreeNodeByUuid(vmObj);
						} else {
							EntityTreeObj vObj = new EntityTreeObj();
							vObj.setEntityId(vm_code);
							vObj.setType(TypeConstant.VMWARE_VM);
							vObj.setParentId(parent.getId());
							vObj.setName(vm_name);
							int vmInsert = this.getEntityTreeService().insertTreeNode(vObj);
						}
					} catch (Exception e) {
						logger.error("对比虚拟机异常：" + e.getMessage());
					}
				} else if (DataConstant.TYPE_HOST.equals(type)) {
					TbCloud2HostInfoObj value = (TbCloud2HostInfoObj) entry.getValue();
					try {
						NoticeUtil.getInstance().updateCMDBHost(value.getH_uuid(),
								Operation.OPER_REL_MODI);// 通知cmdb
					} catch (Exception e) {

					}
					if (dbMap.get(key) == null) {// vCenter中存在，DB中不存在
						logger.info("DB中不存在!---" + value.getEq_name());
						int hostId = this.getHostInfoService().getIdSequence();
						value.setId(hostId);
						value.setEq_id(String.valueOf(hostId));
						int inResult = this.getHostInfoService().insertByObj(value);
						/** 查询HOST关系 */
						String host_param = "/vmware/entity/singlerelation/[type:"
								+ CompConstant.HOST + "].[code:" + value.getH_uuid() + "]/";
						String host = InvokeUtil.invoke(host_param);
						try {
							JSONObject clus = JSONObject.fromObject(host);
							String host_code = (String) clus.get(CompConstant.HOST_CODE);
							String host_name = (String) clus.get(CompConstant.HOST);
							String cluster_code = (String) clus.get(CompConstant.CLUSTER_CODE);
							String dvc_code = (String) clus.get(CompConstant.VDC_CODE);
							EntityTreeObj parent = new EntityTreeObj();
							if (cluster_code != null && !"".equals(cluster_code)) {
								parent.setEntityId(cluster_code);
							} else {
								parent.setEntityId(dvc_code);
							}
							parent = this.getEntityTreeService().queryTreeNode(parent);
							EntityTreeObj hostObj = new EntityTreeObj();
							hostObj.setType(TypeConstant.VMWARE_HOST);
							hostObj.setParentId(parent.getId());
							hostObj.setName(host_name);
							hostObj.setEntityId(host_code);
							int hostInsert = this.getEntityTreeService().insertTreeNode(hostObj);
						} catch (Exception e) {
							logger.error("对比宿主机异常：" + e.getMessage());
						}
					} else {// vCenter中存在，DB中存在,比对是否有差异
						if (!value.equals((TbCloud2HostInfoObj) dbMap.get(key))) {
							int result = this.getHostInfoService().updateInterByObj(value);
						}
					}
				} else if (DataConstant.TYPE_DC.equals(type)) {
					Map<String, Object> map = (Map<String, Object>) entry.getValue();
					if (dbMap.get(key) == null) {// vCenter中存在，DB中不存在
						logger.info(type + "-:-" + (String) map.get(CompConstant.VDC)
								+ "--vCenter中存在，DB中不存在");
						EntityTreeObj obj = new EntityTreeObj();
						obj.setName((String) map.get(CompConstant.VDC));
						obj.setParentId(1);
						obj.setType("8");
						obj.setEntityId((String) map.get(CompConstant.VDC_CODE));
						this.getEntityTreeService().insertTreeNode(obj);
					} else {// vCenter中存在，DB中存在,比对是否有差异

					}
				} else if (DataConstant.TYPE_CLUSTER.equals(type)) {
					Map<String, Object> map = (Map<String, Object>) entry.getValue();
					if (dbMap.get(key) == null) {// vCenter中存在，DB中不存在
						logger.info(type + "-:-" + (String) map.get(CompConstant.CLUSTER)
								+ "--vCenter中存在，DB中不存在");
						ClusterObj cObj = new ClusterObj();
						cObj.setName((String) map.get(CompConstant.CLUSTER));
						cObj.setDrsstate((String) map.get(CompConstant.CLUSTER_DRS));
						cObj.setHastate((String) map.get(CompConstant.CLUSTER_HA));
						cObj.setType("1");
						cObj.setC_uuid((String) map.get(CompConstant.CLUSTER_CODE));
						int result = this.getClusterDao().insertByObj(cObj);
						/** 保存集群数据 */
						String cluster_param = "/vmware/entity/singlerelation/[type:"
								+ CompConstant.CLUSTER + "].[code:"
								+ (String) map.get(CompConstant.CLUSTER_CODE) + "]/";
						String cluster = InvokeUtil.invoke(cluster_param);
						try {
							JSONObject clus = JSONObject.fromObject(cluster);
							String dc_entity = (String) clus.get(CompConstant.VDC_CODE);
							EntityTreeObj parent = new EntityTreeObj();
							parent.setEntityId(dc_entity);
							parent = this.getEntityTreeService().queryTreeNode(parent);
							EntityTreeObj clObj = new EntityTreeObj();
							clObj.setType("3");
							clObj.setParentId(parent.getId());
							clObj.setName((String) map.get(CompConstant.CLUSTER));
							clObj.setEntityId((String) map.get(CompConstant.CLUSTER_CODE));
							int clInsert = this.getEntityTreeService().insertTreeNode(clObj);
						} catch (Exception e) {
							logger.error("对比虚拟机异常：" + e.getMessage());
						}
					} else {// vCenter中存在，DB中存在,比对是否有差异
						if (!dbMap.get(key).equals(interMap.get(key))) {
							ClusterObj cObj = new ClusterObj();
							cObj.setName((String) map.get(CompConstant.CLUSTER));
							cObj.setDrsstate((String) map.get(CompConstant.CLUSTER_DRS));
							cObj.setHastate((String) map.get(CompConstant.CLUSTER_HA));
							cObj.setC_uuid((String) map.get(CompConstant.CLUSTER_CODE));
							int result = this.getClusterDao().updateByObjUuid(cObj);
						} else {
							logger.info((String) map.get(CompConstant.CLUSTER)
									+ "--vCenter与DB中数据比对一致！");
						}
					}
				} else if (DataConstant.TYPE_STORAGE.equals(type)) {

				} else if (DataConstant.TYPE_VNIC.equals(type)) {

				} else if (DataConstant.TYPE_VPORTGROU.equals(type)) {

				} else if (DataConstant.TYPE_HOST_STORAGE.equals(type)) {
					DataStoreObj ds = (DataStoreObj) entry.getValue();
					if (dbMap.get(key) == null) {// vceneter中存在，数据库中不存在
						int inResult = this.getDataStoreService().insertDatastore(ds);
						// 通知
						try {
							NoticeUtil.getInstance().updateCMDBHost(params[1],
									Operation.OPER_REL_MODI);
						} catch (Exception e) {

						}
						try {
							NoticeUtil.getInstance().addStorage(ds.getStore_uuid());// 服务管理
						} catch (Exception e) {

						}
					} else {// 更新数据库中的信息
						if (!((DataStoreObj) dbMap.get(key)).equals((DataStoreObj) interMap
								.get(key))) {
							int updateResult = this.getDataStoreService().updateByObj(ds);
							String code = ds.getStore_uuid();
							try {
								NoticeUtil.getInstance().updateCMDBDataStorage(code,
										Operation.OPER_MODI);
							} catch (Exception e) {

							}
						}
					}

				}
			}
			// 遍历数据库返回数据
			Iterator<Entry<String, Object>> dbIterator = dbMap.entrySet().iterator();
			while (dbIterator.hasNext()) {
				Entry<String, Object> entry = dbIterator.next();
				String key = entry.getKey();
				/** 判断实体类型 */
				if (DataConstant.TYPE_VM.equals(type)) {
					VMHostObj value = (VMHostObj) entry.getValue();
					if (interMap.get(key) == null) {// DB中存在，vCenter中不存在
						try {
							NoticeUtil.getInstance().delCMDBVM(value.getVH_UUID(),
									Operation.OPER_DEL);
						} catch (Exception e) {

						}
						try {
							NoticeUtil.getInstance().delVM(value.getVH_UUID());
						} catch (Exception e) {

						}
						logger.info("vCenter中不存在！---" + value.getVH_NAME());
						this.getVmHostService().deleteByObj(value);
						EntityTreeObj tree = new EntityTreeObj();
						tree.setEntityId(value.getVH_UUID());
						this.getEntityTreeService().delTreeNodeByEntity(tree);
					} else {// DB中存在，vCenter中存在,比对是否有差异

					}
				} else if (DataConstant.TYPE_HOST.equals(type)) {
					TbCloud2HostInfoObj value = (TbCloud2HostInfoObj) entry.getValue();
					if (interMap.get(key) == null) {// DB中存在，vCenter中不存在
						logger.info("vCenter中不存在！---" + value.getEq_name());
						this.getHostInfoService().deleteByObj(value);
						EntityTreeObj tree = new EntityTreeObj();
						tree.setEntityId(value.getH_uuid());
						this.getEntityTreeService().delTreeNodeByEntity(tree);
					} else {// DB中存在，vCenter中存在,比对是否有差异

					}
				} else if (DataConstant.TYPE_DC.equals(type)) {
					Map<String, Object> map = (Map<String, Object>) entry.getValue();
					if (interMap.get(key) == null) {// DB中存在，vCenter中不存在
						logger.info(type + ":" + (String) map.get(CompConstant.VDC)
								+ "DB中存在，vCenter中不存在");
						EntityTreeObj obj = new EntityTreeObj();
						obj.setEntityId((String) map.get(CompConstant.VDC_CODE));
						this.getEntityTreeService().delTreeNodeByEntity(obj);
					} else {// DB中存在，vCenter中存在,比对是否有差异

					}
				} else if (DataConstant.TYPE_CLUSTER.equals(type)) {
					Map<String, Object> map = (Map<String, Object>) entry.getValue();
					if (interMap.get(key) == null) {// DB中存在，vCenter中不存在
						logger.info(type + ":" + (String) map.get(CompConstant.CLUSTER)
								+ "DB中存在，vCenter中不存在");
						ClusterObj obj = new ClusterObj();
						obj.setC_uuid((String) map.get(CompConstant.CLUSTER_CODE));
						this.getClusterService().deleteByObj(obj);
					} else {// DB中存在，vCenter中存在,比对是否有差异

					}
				} else if (DataConstant.TYPE_STORAGE.equals(type)) {

				} else if (DataConstant.TYPE_VNIC.equals(type)) {

				} else if (DataConstant.TYPE_VPORTGROU.equals(type)) {

				} else if (DataConstant.TYPE_HOST_STORAGE.equals(type)) {
					DataStoreObj ds = (DataStoreObj) entry.getValue();
					if (interMap.get(key) == null) {// db中存在
						this.getDataStoreService().deleteByObj(ds);
						try {
							NoticeUtil.getInstance().delStorage(ds.getStore_uuid());// 服务管理
						} catch (Exception e) {

						}
						try {
							NoticeUtil.getInstance().updateCMDBDataStorage(ds.getStore_uuid(),
									Operation.OPER_DEL);
						} catch (Exception e) {

						}
					}
				}
			}
		}
	}

	public String get() {
		// try {
		// String result = getDBMap(DataConstant.TYPE_VM).toString();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// String rs = getInterfaceMap(DataConstant.TYPE_VM).toString();
		// dataCompare(DataConstant.TYPE_HOST_STORAGE, "172.21.1.105");
		// getInterfaceMap(DataConstant.TYPE_HOST_STORAGE, "172.21.1.105");
		boolean tag = false;
		if (!isNull(this.getClusterDao()) && !isNull(this.getClusterService())
				&& !isNull(this.getDataStoreService()) && !isNull(this.getEntityTreeService())
				&& !isNull(this.getHostInfoService()) && !isNull(this.getVmHostService())) {
			tag = true;
		}
		if (tag) {
			try {
				dataCompare(DataConstant.TYPE_DC);
			} catch (Exception e) {
				logger.error("DC数据比对异常：" + e.getMessage());
			}
			try {
				dataCompare(DataConstant.TYPE_CLUSTER);
			} catch (Exception e) {
				logger.error("CLUSTER数据比对异常：" + e.getMessage());
			}
			try {
				dataCompare(DataConstant.TYPE_HOST);
			} catch (Exception e) {
				logger.error("HOST数据比对异常：" + e.getMessage());
			}
			try {
				dataCompare(DataConstant.TYPE_VM);
			} catch (Exception e) {
				logger.error("VM数据比对异常：" + e.getMessage());
			}
		}
		// EntityTreeDao dao = (EntityTreeDao)
		// Map<String, Object> map = getDBMap(DataConstant.TYPE_VM);
		// SpringUtil.getBean("entityTreeDao");
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("vm_code", "vm-1675");
		// List<Map<String, Object>> list = dao.queryDcClHostVMRelat(map);
		return "success to datacompare";
	}

	/**
	 * 
	 * @Title: isNull
	 * @Description: 判断是否为null
	 * @param
	 * @return boolean
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Nov 14, 2012 10:18:26 AM
	 */
	public boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		} else {
			return false;
		}
	}
}
