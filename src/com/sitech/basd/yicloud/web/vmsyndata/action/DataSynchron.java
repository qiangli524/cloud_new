package com.sitech.basd.yicloud.web.vmsyndata.action;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.yicloud.dao.cluster.ClusterDao;
import com.sitech.basd.yicloud.domain.cluster.ClusterObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreInfo;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.entityreference.EntityReferenceObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityConObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.nic.NicObj;
import com.sitech.basd.yicloud.domain.nic.NicRelationObj;
import com.sitech.basd.yicloud.domain.nic.VirtualNicObj;
import com.sitech.basd.yicloud.domain.portgroup.PortGroup;
import com.sitech.basd.yicloud.domain.switches.VirtualSwitch;
import com.sitech.basd.yicloud.domain.templettree.TempletTreeObj;
import com.sitech.basd.yicloud.domain.vmman.ClusterInfoObj;
import com.sitech.basd.yicloud.domain.vmman.DataCenterInfoObj;
import com.sitech.basd.yicloud.service.datastore.DataStoreService;
import com.sitech.basd.yicloud.service.entityreference.EntityRefService;
import com.sitech.basd.yicloud.service.entitytree.EntityTreeService;
import com.sitech.basd.yicloud.service.nic.NicRelationService;
import com.sitech.basd.yicloud.service.nic.NicService;
import com.sitech.basd.yicloud.service.nic.VirtualNicService;
import com.sitech.basd.yicloud.service.portgroup.PortGroupService;
import com.sitech.basd.yicloud.service.switches.VirtualSwitchService;
import com.sitech.basd.yicloud.service.templettree.TempletTreeService;
import com.sitech.basd.yicloud.util.HttpClientUtil;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.JSONUtil;
import com.sitech.basd.yicloud.util.JsonUtils;
import com.sitech.ws.Operation;
import com.sitech.ws.Type;
import com.sitech.ws.web.NoticeUtil;

public class DataSynchron extends CRUDBaseAction {
	private EntityTreeService entityTreeService;
	private HostInfoService hostInfoService;
	private VMHostService vmHostService;
	private ClusterDao clusterDao;
	private DataStoreService dataStoreService;
	private PortGroupService portGroupService;
	private VirtualSwitchService virtualSwitchService;
	private TempletTreeService templetTreeService;
	private EntityRefService entityRefService;;
	private NicService nicService;
	private VirtualNicService virtualNicService;
	private NicRelationService nicRelationService;

	public void setNicRelationService(NicRelationService nicRelationService) {
		this.nicRelationService = nicRelationService;
	}

	public void setVirtualNicService(VirtualNicService virtualNicService) {
		this.virtualNicService = virtualNicService;
	}

	public void setNicService(NicService nicService) {
		this.nicService = nicService;
	}

	public void setEntityRefService(EntityRefService entityRefService) {
		this.entityRefService = entityRefService;
	}

	public void setTempletTreeService(TempletTreeService templetTreeService) {
		this.templetTreeService = templetTreeService;
	}

	public void setVirtualSwitchService(VirtualSwitchService virtualSwitchService) {
		this.virtualSwitchService = virtualSwitchService;
	}

	public void setPortGroupService(PortGroupService portGroupService) {
		this.portGroupService = portGroupService;
	}

	public void setDataStoreService(DataStoreService dataStoreService) {
		this.dataStoreService = dataStoreService;
	}

	public void setClusterDao(ClusterDao clusterDao) {
		this.clusterDao = clusterDao;
	}

	public void setEntityTreeService(EntityTreeService entityTreeService) {
		this.entityTreeService = entityTreeService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public void setVmHostService(VMHostService vmHostService) {
		this.vmHostService = vmHostService;
	}

	/**
	 * 
	 * @Title: synDataCenter
	 * @Description: 进行数据同步
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 6, 2012 1:49:11 PM
	 */
	public String synDataCenter() throws Exception {
		EntityTreeObj e = new EntityTreeObj();
		// 通过后台接口获取数据中心的信息
		String param = "/vmware/domain/synDataCenterData";
		String result = InvokeUtil.invoke(param);
		List<DataCenterInfoObj> dcList = (List<DataCenterInfoObj>) JsonUtils
				.jsonToList(result, DataCenterInfoObj.class);
		List<String> alldcName = new ArrayList<String>(dcList.size());
		for (DataCenterInfoObj dc : dcList) {
			String dcName = dc.getNAME();
			alldcName.add(dc.getUuid());
			if (dcName == null || "".equals(dcName)) {
				break;
			}
			/** 保存数据中心数据 */
			EntityTreeObj dcObj = new EntityTreeObj();
			dcObj.setType("8");
			dcObj.setParentId(1);
			dcObj.setName(dcName);
			dcObj.setEntityId(dc.getUuid());
			int dcInsert = entityTreeService.insertTreeNode(dcObj);
			dcObj = entityTreeService.queryTreeNode(dcObj);
			/** 保存数据中心数据 */

			// 如果数据中心下有主机
			String host2dc = "/vmware/host/synHostData/[dcName:" + dc.getUuid() + "]/";
			String hostResult = InvokeUtil.invoke(host2dc);
			List<TbCloud2HostInfoObj> host2dcList = (List<TbCloud2HostInfoObj>) JsonUtils.jsonToList(hostResult,
					TbCloud2HostInfoObj.class);
			// 保存数据中心下的主机信息
			saveHostInfo(host2dcList, dcObj.getId(), dcObj.getId(), 0);

			String cluster_param = "/vmware/domain/synClusterData/[dcName:" + dc.getUuid() + "]/";
			String cluster = InvokeUtil.invoke(cluster_param);
			List<ClusterInfoObj> cluList = (List<ClusterInfoObj>) JsonUtils.jsonToList(cluster, ClusterInfoObj.class);

			for (ClusterInfoObj clu : cluList) {
				String cluName = clu.getNAME();
				if (cluName == null || "".equals(cluName)) {
					break;
				}
				/** 插入集群信息表 */
				ClusterObj cObj = new ClusterObj();
				cObj.setName(cluName);
				cObj.setDrsstate(clu.getDrsstate());
				cObj.setHastate(clu.getHastate());
				cObj.setType("1");
				cObj.setC_uuid(clu.getC_uuid());
				int cluret = clusterDao.insertByObj(cObj);

				/** 保存集群数据 */
				EntityTreeObj clObj = new EntityTreeObj();
				clObj.setType("3");
				clObj.setParentId(dcObj.getId());
				clObj.setName(cluName);
				clObj.setEntityId(clu.getC_uuid());
				int clInsert = entityTreeService.insertTreeNode(clObj);
				clObj = entityTreeService.queryTreeNode(clObj);
				/** 保存集群数据 */

				// 主机信息
				String host_param = "/vmware/host/synHostData/[cluName:" + clu.getC_uuid() + "]/";
				String host = InvokeUtil.invoke(host_param);
				List<TbCloud2HostInfoObj> hostList = (List<TbCloud2HostInfoObj>) JsonUtils.jsonToList(host,
						TbCloud2HostInfoObj.class);
				// 保存集群下的主机信息
				saveHostInfo(hostList, clObj.getId(), dcObj.getId(), cluret);
				
			}
			//保存数据中心下的分布式网络信息
			
			
			/** 查询主机下的网络，包括主机、虚拟机交换机、物理网卡、虚拟机网卡、虚拟机、端口组之间的关系 */
			//this is wrong ,please change in the future ----note by duangh
			String net_param = "/vmware/domain/getDistributedVirtualSwitch/[CODE:" + dc.getUuid() + "]/";
			String net = InvokeUtil.invoke(net_param);
			List<JSONObject> netjo = JSONArray.toList(JSONArray.fromObject(net), JSONObject.class);
			for (JSONObject jo : netjo) {
				if (jo.get("TTYPE").equals("TTYPE_CI")) {
					if (jo.getString("TYPE").equals("DVPORTGROUP")) {
						PortGroup obj = new PortGroup();
						obj.setName(jo.get("NAME") == null ? "" : jo.getString("NAME"));
						obj.setType("");
						obj.setVlanId(jo.get("VLAN") == null ? 0 : jo.getInt("VLAN"));
						obj.setPguuid(jo.get("CODE") == null ? "" : jo.getString("CODE"));
						obj.setType("1");
						portGroupService.insertByObj(false, "", obj);
					} else if (jo.getString("TYPE").equals("DVSS")) {
						VirtualSwitch vs = new VirtualSwitch();
						vs.setName(jo.get("NAME") == null ? "" : jo.getString("NAME"));
						vs.setNumPorts(jo.get("NUMPORTS") == null ? 0 : jo.getInt("NUMPORTS"));
						vs.setNumPortsAvailable(jo.get("NUMPORTSAVAILABLE") == null ? 0 : jo
								.getInt("NUMPORTSAVAILABLE"));
						vs.setVssuuid(jo.get("CODE") == null ? "" : jo.getString("CODE"));
						vs.setHostName(dc.getNAME());
						vs.setType("1");
						virtualSwitchService.insertByObj(vs);
					}
				} else if (jo.get("RELATIONSHIP") != null) {
					// 暂只取主机和网卡、虚拟网卡和虚拟机、端口组和虚拟机、网卡和虚拟交换机、虚拟交换机和主机的关系
					if (jo.getString("RELATIONSHIP").equals("DVPG_VM")
							|| jo.getString("RELATIONSHIP").equals("DVSS_NIC")
							|| jo.getString("RELATIONSHIP").equals("DVSS_HOST")
							|| jo.getString("RELATIONSHIP").equals("DVSS_DVPORTGROUP")
							) {
						NicRelationObj nicRelationObj = new NicRelationObj();
						nicRelationObj.setType(jo.get("RELATIONSHIP") == null ? "" : jo.getString("RELATIONSHIP"));
						nicRelationObj.setToUuid(jo.get("TO_CODE") == null ? "" : jo.getString("TO_CODE"));
						nicRelationObj.setFromUuid(jo.get("FROM_CODE") == null ? "" : jo.getString("FROM_CODE"));
						nicRelationService.insertByObj(nicRelationObj);
					}
				}
			}


		}
		try {
			String[] a = new String[dcList.size()];
			String noticeResult = NoticeUtil.getInstance().globalSynchNotice(alldcName.toArray(a));
//			 JSONObject jsonResult = JSONObject.fromObject(noticeResult);
//			 String monitorResult = (String) jsonResult.get("monitorResult");
//			 if (monitorResult != null && !"".equals(monitorResult)) {
//			 JSONArray entities = JSONArray.fromObject(monitorResult);
//			 List<Map<String, String>> entityList = JSONArray
//			 .toList(entities);
//			 for (int i = 0; i < entityList.size(); i++) {
//			 Map<String, String> entity = entityList.get(0);
//			 String unitId = entity.get("FWGL_UIID");
//			 String code = entity.get("CLOUD_CODE");
//			 EntityReferenceObj erObj = new EntityReferenceObj();
//			 erObj.setUnit_id(unitId);
//			 erObj.setCode(code);
//			 entityRefService.insertByObj(erObj);
//			 }
//			 }
		} catch (Exception ex) {

		}
		return null;
	}

	private String saveHostInfo(List<TbCloud2HostInfoObj> hostList, int... param) {

		int parentId = param[0];// 主机的parentID,即父节点id
		int dcId = param[1];// 数据中心id,数据中心暂时没有表，存tree表中的数据中心id
		int clusterId = param[2];// 集群id

		for (TbCloud2HostInfoObj hos : hostList) {
			String hostName = hos.getEq_name();
			if (hostName == null && "".equals(hostName)) {
				break;
			}

			/** 保存主机数据 */
			// 主机表
			int hostId = hostInfoService.getIdSequence();
			hos.setId(hostId);
			hos.setEq_id(String.valueOf(hostId));
			hos.setHasvertual("4");// 4为vmware虚拟化类型
			hos.setEq_type("2");// 2为刀片
			hos.setEq_hostname(hostName);
			if (hos.getSTATUS() != null) {
				hos.setSTATUS(hos.getSTATUS());
			}
			hostInfoService.insertByObj(hos);
			// Tree表
			EntityTreeObj hostObj = new EntityTreeObj();
			hostObj.setType("1");
			hostObj.setParentId(parentId);
			hostObj.setName(hostName);
			hostObj.setEntityId(hos.getH_uuid());
			int hostInsert = entityTreeService.insertTreeNode(hostObj);
			hostObj = entityTreeService.queryTreeNode(hostObj);
			// Tree-Con表
			EntityConObj hostCObj = new EntityConObj();
			hostCObj.setDataCenterId(dcId);
			hostCObj.setClusterId(clusterId);
			hostCObj.setName(hostName);
			hostCObj.setType(1);
			hostCObj.setEntityId(hos.getH_uuid());
			entityTreeService.insertConObj(hostCObj);
			/** 保存主机数据 */

			/** 保存存储数据 */
			String ds_param = "/vmware/storage/collDatastoreInfo/[hostname:" + hos.getH_uuid() + "]/";
			String ds = InvokeUtil.invoke(ds_param);
			HashMap<String, DataStoreInfo> dsmap = JSONUtil.fromJSON(ds,
					new JSONUtil.TypeReference<HashMap<String, DataStoreInfo>>() {
					});

			Collection<DataStoreInfo> c = dsmap.values();
			for (Iterator<DataStoreInfo> it = c.iterator(); it.hasNext();) {
				DataStoreInfo infoObj = it.next();
				DataStoreObj dsObj = new DataStoreObj();
				dsObj.setNAME(infoObj.getName());
				dsObj.setFREE_SPACE(infoObj.getFreeSpace());
				dsObj.setSTORAGE_URL(infoObj.getStorageUrl());
				dsObj.setHOST_ID(hos.getH_uuid());
				dsObj.setCAPACITY(infoObj.getCapacity());
				dsObj.setTYPE(infoObj.getType());
				dsObj.setDATACENTER_ID(dcId + "");
				dsObj.setCLUSTER_ID(clusterId + "");
				dsObj.setStore_uuid(infoObj.getCode());
				int dsret = dataStoreService.insertDatastore(dsObj);//
			}
			/** 保存存储数据 */

			// /** 保存虚拟交换机和端口组信息 */
			// // 端口组列表
			// String portgroupUrl = "/vmware/network/getportgroup/[hostName:"
			// + hos.getH_uuid() + "]/";
			// String getResult = InvokeUtil.invoke(portgroupUrl);
			// List<PortGroup> portGroupList = new ArrayList<PortGroup>();
			// if (getResult != null) {
			// portGroupList = JSONArray.toList(JSONArray
			// .fromObject(getResult), PortGroup.class);
			// }
			// // 虚拟交换机列表
			// String vswitchUrl = "/vmware/network/getvirtualswitch/[hostName:"
			// + hos.getH_uuid() + "]/";
			// String getvSwitch = InvokeUtil.invoke(vswitchUrl);
			// if (getvSwitch != null) {
			// int ret = -1;
			// List<VirtualSwitch> switchList = new ArrayList<VirtualSwitch>();
			// switchList = JSONArray.toList(JSONArray.fromObject(getvSwitch),
			// VirtualSwitch.class);
			// for (VirtualSwitch vs : switchList) {
			// ret = virtualSwitchService.insertByObj(vs);
			// if (ret != -1) {
			// for (PortGroup pg : portGroupList) {
			// if (pg.getVswitchName().equals(vs.getName())) {
			// PortGroup obj = new PortGroup();
			// obj.setVswitchId(ret);
			// obj.setName(pg.getName());
			// obj.setType(pg.getType());
			// obj.setVswitchName(pg.getVswitchName());
			// obj.setVlanId(pg.getVlanId());
			// portGroupService.insertByObj(false, hostName,
			// obj);
			// }
			// }
			// }
			// }
			// }
			/** 查询主机下的网络，包括主机、虚拟机交换机、物理网卡、虚拟机网卡、虚拟机、端口组之间的关系 */
			//this is wrong ,please change in the future ----note by duangh
			String net_param = "/vmware/domain/synVCenterTemp/[TYPE:HOST].[CODE:" + hos.getH_uuid() + "]/";
			String net = InvokeUtil.invoke(net_param);
			List<JSONObject> netjo = JSONArray.toList(JSONArray.fromObject(net), JSONObject.class);
			for (JSONObject jo : netjo) {
				if (jo.get("TYPE") != null) {
					if (jo.getString("TYPE").equals("PORTGROUP")) {
						PortGroup obj = new PortGroup();
						obj.setName(jo.get("NAME") == null ? "" : jo.getString("NAME"));
						obj.setType("");
						obj.setVlanId(jo.get("VLAN") == null ? 0 : jo.getInt("VLAN"));
						obj.setPguuid(jo.get("CODE") == null ? "" : jo.getString("CODE"));
						obj.setType("0");
						portGroupService.insertByObj(false, hostName, obj);
					} else if (jo.getString("TYPE").equals("VSS")) {
						VirtualSwitch vs = new VirtualSwitch();
						vs.setName(jo.get("NAME") == null ? "" : jo.getString("NAME"));
						vs.setNumPorts(jo.get("NUMPORTS") == null ? 0 : jo.getInt("NUMPORTS"));
						vs.setNumPortsAvailable(jo.get("NUMPORTSAVAILABLE") == null ? 0 : jo
								.getInt("NUMPORTSAVAILABLE"));
						vs.setVssuuid(jo.get("CODE") == null ? "" : jo.getString("CODE"));
						vs.setHostName(hostName);
						vs.setType("0");
						virtualSwitchService.insertByObj(vs);
					} else if (jo.getString("TYPE").equals("NIC")) {
						NicObj nicObj = new NicObj();
						nicObj.setName(jo.get("NAME") == null ? "" : jo.getString("NAME"));
						nicObj.setLinkduplex(jo.get("LINKDUPLEX") == null ? "" : jo.getString("LINKDUPLEX"));
						nicObj.setLinkspeed(jo.get("LINKSPEED") == null ? 0 : jo.getInt("LINKSPEED"));
						nicObj.setStatus(jo.get("STATUS") == null ? "" : jo.getString("STATUS"));
						nicObj.setUuid(jo.get("CODE") == null ? "" : jo.getString("CODE"));
						nicService.insertByObj(nicObj);
					} else if (jo.getString("TYPE").equals("VNIC")) {
						VirtualNicObj vNicObj = new VirtualNicObj();
						vNicObj.setName(jo.get("NAME") == null ? "" : jo.getString("NAME"));
						vNicObj.setIpAddress(jo.get("IPADDRESS") == null ? "" : jo.getString("IPADDRESS"));
						vNicObj.setUuid(jo.get("CODE") == null ? "" : jo.getString("CODE"));
						virtualNicService.insertByObj(vNicObj);
					}
				} else if (jo.get("RELATIONSHIP") != null) {
					// 暂只取主机和网卡、虚拟网卡和虚拟机、端口组和虚拟机、网卡和虚拟交换机、虚拟交换机和主机的关系
					if (jo.getString("RELATIONSHIP").equals("NIC_HOST")
							|| jo.getString("RELATIONSHIP").equals("VNIC_VM")
							|| jo.getString("RELATIONSHIP").equals("PORTGROUP_VM")
							|| jo.getString("RELATIONSHIP").equals("NIC_VSS")
							|| jo.getString("RELATIONSHIP").equals("VSS_HOST")
							|| jo.getString("RELATIONSHIP").equals("PORTGROUP_VSS")
							|| jo.getString("RELATIONSHIP").equals("VNIC_NIC")
							|| jo.getString("RELATIONSHIP").equals("VNIC_PORTGROUP")) {
						NicRelationObj nicRelationObj = new NicRelationObj();
						nicRelationObj.setType(jo.get("RELATIONSHIP") == null ? "" : jo.getString("RELATIONSHIP"));
						nicRelationObj.setToUuid(jo.get("TO_CODE") == null ? "" : jo.getString("TO_CODE"));
						nicRelationObj.setFromUuid(jo.get("FROM_CODE") == null ? "" : jo.getString("FROM_CODE"));
						nicRelationService.insertByObj(nicRelationObj);
					}
				}
			}

			/** 保存虚拟机数据 */
			String vm_param = "/vmware/domain/synDomainData/[hostName:" + hos.getH_uuid() + "]/";
			String vm = InvokeUtil.invoke(vm_param);
			List<VMHostObj> vmList = (List<VMHostObj>) JsonUtils.jsonToList(vm, VMHostObj.class);
			for (VMHostObj vmObj : vmList) {
				String vmName = vmObj.getVH_NAME();
				/** 保存虚拟机数据 */
				// 主机表
				int vmId = vmHostService.queryVhostIdSequence();
				vmObj.setID(vmId);
				vmObj.setVH_ID(createNewVhostId2(String.valueOf(hostId)));
				if (vmObj.getVH_TYPE().equals("5")) {// 虚拟机模板
					vmObj.setVH_DESC("cpu:" + vmObj.getVH_CPU() + ";内存:" + vmObj.getVH_MEM() + "(MB),存储:"
							+ vmObj.getVH_STORAGE() + "(KB)");
				}
				vmObj.setEQ_ID(String.valueOf(hostId));
				vmHostService.insertByVMhostObj(vmObj);

				// 模板树表
				if (vmObj.getVH_TYPE().equals("5")) {
					TempletTreeObj temObj = new TempletTreeObj();
					temObj.setParent_id(-1);
					temObj.setName(vmObj.getVH_NAME());
					temObj.setType(2);
					temObj.setTemplet_id(String.valueOf(vmId));
					templetTreeService.creatMenu(temObj);
				}

				// Tree表
				EntityTreeObj vmTreeObj = new EntityTreeObj();
				if (vmObj.getVH_TYPE().equals("5")) {
					vmTreeObj.setType("23");
				} else if (vmObj.getVH_TYPE().equals("1")) {
					vmTreeObj.setType("0");
				}
				vmTreeObj.setParentId(hostObj.getId());
				vmTreeObj.setName(vmName);
				vmTreeObj.setEntityId(vmObj.getVH_UUID());
				int vmInsert = entityTreeService.insertTreeNode(vmTreeObj);
				vmTreeObj = entityTreeService.queryTreeNode(vmTreeObj);
				// Tree-Con表
				EntityConObj vmCObj = new EntityConObj();
				vmCObj.setDataCenterId(dcId);
				vmCObj.setClusterId(clusterId);
				vmCObj.setName(vmName);
				if (vmObj.getVH_TYPE().equals("5")) {
					vmCObj.setType(23);
				} else if (vmObj.getVH_TYPE().equals("1")) {
					vmCObj.setType(0);
				}
				vmCObj.setEntityId(vmObj.getVH_UUID());
				entityTreeService.insertConObj(vmCObj);
			}

		}
		return null;
	}

	/**
	 * 
	 * @Title: createNewVhostId2
	 * @Description: 创建虚拟机ID
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 28, 2012 3:24:59 PM
	 */
	public String createNewVhostId2(String eqId) {
		String vhostId = null;
		if (null != eqId && !"".equals(eqId)) {
			int seq = vmHostService.queryVhostIdSequence();
			NumberFormat formatter = NumberFormat.getNumberInstance(); // 设置数据格式
			formatter.setMinimumIntegerDigits(3); // 设置最小长度
			formatter.setMaximumIntegerDigits(3); // 设置最大长度
			vhostId = eqId + "_" + formatter.format(seq);
		}
		return vhostId;
	}

	public String entittyRef() {
		List<String> list = new ArrayList<String>(1);
		list.add("datacenter-15");
		String[] a = new String[1];
		// list.toArray(a)));
		String noticeResult = NoticeUtil.getInstance().globalSynchNotice(list.toArray(a));
		// Map ps = ParamParser.makeup(noticeResult);
		// String monitorResult = (String) ps.get("monitorResult");
		JSONObject jsonResult = JSONObject.fromObject(noticeResult);
		String monitorResult = (String) jsonResult.get("monitorResult");
		JSONArray entities = JSONArray.fromObject(monitorResult);
		List<Map<String, String>> entityList = JSONArray.toList(entities);
		for (int i = 0; i < entityList.size(); i++) {
			Map<String, String> entity = entityList.get(0);
			String unitId = entity.get("FWGL_UIID");
			String code = entity.get("CLOUD_CODE");
			EntityReferenceObj erObj = new EntityReferenceObj();
			erObj.setUnit_id(unitId);
			erObj.setCode(code);
			entityRefService.insertByObj(erObj);
		}
		return null;
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>(2);
		list.add("datacenter-15");
		list.add("datacenter-274");
		String[] a = new String[2];
		list.toArray(a);
		List<Map<String, String>> list2 = new ArrayList<Map<String, String>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put("TTYPE", Type.TTYPE_CI);
			map.put("CODE", list.get(i));
			map.put("TYPE", Type.CTYPE_VDC);
			map.put("OPERATE", Operation.OPER_ADD);
			list2.add(map);
		}
		String result = HttpClientUtil.post(
				"http://172.21.105.230:8080/WsServer/WebService/service/incrementalResources", JSONArray
						.fromObject(list2));
		// list.toArray(a)));
	}
}
