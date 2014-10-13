package com.sitech.basd.yicloud.web.xensyndata.action;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.xen.Host;
import com.sitech.basd.yicloud.domain.xen.Sr;
import com.sitech.basd.yicloud.domain.xen.Vm;
import com.sitech.basd.yicloud.domain.xenstore.XenStoreObj;
import com.sitech.basd.yicloud.domain.xentree.XenClusterObj;
import com.sitech.basd.yicloud.domain.xentree.XenEntityConObj;
import com.sitech.basd.yicloud.domain.xentree.XenEntityTreeObj;
import com.sitech.basd.yicloud.service.xen.XenManService;
import com.sitech.basd.yicloud.service.xencluster.XenClusterService;
import com.sitech.basd.yicloud.service.xenstore.XenStoreService;
import com.sitech.basd.yicloud.service.xentree.XenEntityTreeService;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.TypeConstant;
import com.sitech.basd.yicloud.web.vmsyndata.action.DataCompare;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * @author 数据比对
 * 
 */
public class XenDataCompare {
	private static final Logger logger = Logger.getLogger(DataCompare.class.getName());
	private VMHostService vmHostService;
	private XenClusterService xenClusterService;
	private HostInfoService hostInfoService;
	// /private XenStoreService xenStoreService;
	private XenEntityTreeService xenEntityTreeService;
	private XenManService xenManService;
	private XenStoreService xenStoreService;
	private static List<JSONObject> clusters;
	private static List<JSONObject> hosts;
	private static List<JSONObject> vms;
	private static List<JSONObject> srs;
	private static Map<String, Object> dbMap;
	private static Map vmHost = new HashMap();
	// private static List<String> dbVmUuids = new ArrayList<String>();
	private static List<String> interVmUuids = new ArrayList<String>();

	public String xenDataCompare() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String pool_uuid = request.getParameter("pool_uuid");
		String result = "";
		try {
			boolean complete = getInterfaceData(pool_uuid);
			if (complete) {
				dbMap = getDBMap(pool_uuid);
				dataCompare(pool_uuid);
				result = "{\"result\":\"1\"}";
				returnMessage(result);
			} else {
				result = "{\"result\":\"-1\"}";
				returnMessage(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: getInterfaceData
	 * @Description: 获取接口中数据
	 * @param
	 * @return boolean
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 16, 2013 5:07:56 PM
	 */
	private boolean getInterfaceData(String pool_uuid) {
		// 查询池信息
		// String cluster_param = "/xen/pools/lookup/[connPoolUuid:" + pool_uuid
		// + "]/";
		// String cluster = InvokeUtil.invoke(cluster_param);
		// JSONArray clu = JSONArray.fromObject(cluster);
		// clusters = JSONArray.toList(clu, JSONObject.class);
		// if (clusters.get(0).get("responseCode").equals("-1")) {
		// result = "{\"result\":\"-1\"}";
		// returnMessage(result);
		// return null;
		// } else {
		// clusters.remove(0);
		// }
		// 查询链接下所有主机的信息
		String host_param = "/xen/hostbypool/lookup/[poolUuid:" + pool_uuid + "]/";
		String host = InvokeUtil.invoke(host_param);
		JSONArray jo = JSONArray.fromObject(host);
		hosts = JSONArray.toList(jo, JSONObject.class);
		if (hosts.get(0).get("responseCode").equals("-1")) {
			return false;
		} else {
			hosts.remove(0);
		}
		// 查询池链接下所有虚拟机的信息
		String vm_param = "/xen/vms/lookup/[poolUuid:" + pool_uuid + "]/";
		String vm = InvokeUtil.invoke(vm_param);
		JSONArray jm = JSONArray.fromObject(vm);
		vms = JSONArray.toList(jm, JSONObject.class);
		if (vms.get(0).get("responseCode").equals("-1")) {
			return false;
		} else {
			vms.remove(0);
		}
		/**
		 * 查询链接下所有存储的信息
		 */
		String sr_param = "/xen/srs/all/get/[poolUuid:" + pool_uuid + "]/";
		String sr = InvokeUtil.invoke(sr_param);
		JSONArray jsr = JSONArray.fromObject(sr);
		srs = JSONArray.toList(jsr, JSONObject.class);
		if (srs.get(0).get("responseCode").equals("-1")) {
			return false;
		} else {
			srs.remove(0);
		}
		return true;
	}

	/**
	 * 
	 * @Title: getDBMap
	 * @Description: 获取数据库中资源池下的数据
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 16, 2013 4:35:08 PM
	 */
	public Map<String, Object> getDBMap(String pool_uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpServletRequest request = Struts2Utils.getRequest();
		// TbSysUserinfoObj user = (TbSysUserinfoObj)
		// request.getSession().getAttribute(Constant.USER_SESSION_KEY);
		// 查询库中集群下的主机、虚拟机、存储
		XenEntityTreeObj poolObj = new XenEntityTreeObj();
		poolObj.setUuid(pool_uuid);
		poolObj = xenEntityTreeService.queryTreeNode(poolObj);
		if (poolObj != null) {
			XenEntityTreeObj queryPoolObj = new XenEntityTreeObj();
			queryPoolObj.setParentId(poolObj.getId());
			queryPoolObj.setUserId(request.getSession().getAttribute("id").toString());
			List<XenEntityTreeObj> clusterSonList = xenEntityTreeService.queryForTree(queryPoolObj);
			if (clusterSonList != null && clusterSonList.size() > 0) {
				for (XenEntityTreeObj clusterSonObj : clusterSonList) {
					map.put(clusterSonObj.getUuid(), clusterSonObj);
					if (clusterSonObj.getType().equals(TypeConstant.XEN_HOST)) {
						XenEntityTreeObj queryHostObj = new XenEntityTreeObj();
						queryHostObj.setParentId(clusterSonObj.getId());
						queryHostObj.setUserId(request.getSession().getAttribute("id").toString());
						List<XenEntityTreeObj> hostSonList = xenEntityTreeService
								.queryForTree(queryHostObj);
						if (hostSonList != null && hostSonList.size() > 0) {
							for (XenEntityTreeObj hostSonObj : hostSonList) {
								map.put(hostSonObj.getUuid(), hostSonObj);
							}
						}
					}
				}
			}
		}
		return map;
	}

	/**
	 * 
	 * 
	 * @Title: dataCompare
	 * @Description: 数据比较
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 16, 2013 4:35:37 PM
	 */
	public void dataCompare(String pool_uuid) {
		Map<String, Object> intermap = new HashMap<String, Object>();
		XenEntityTreeObj clusterObj = new XenEntityTreeObj();
		clusterObj.setUuid(pool_uuid);
		clusterObj = xenEntityTreeService.queryTreeNode(clusterObj);

		List<Host> tempHosts = xenManService.analysisHostAndVm(hosts, vms, srs, pool_uuid);

		if (tempHosts.size() > 0) {
			// 接口中的数据和库中数据比较
			interfaceCompareDatastore(pool_uuid, dbMap, intermap, clusterObj, tempHosts);
			// 库中数据和接口中的数据比较
			datastoreCompareInterface(dbMap, intermap);
		}
	}

	/**
	 * 
	 * @Title: interfaceCompareDatastore
	 * @Description: 接口中数据和库中数据比较
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 16, 2013 4:35:55 PM
	 */
	private void interfaceCompareDatastore(String pool_uuid, Map<String, Object> dbMap,
			Map<String, Object> intermap, XenEntityTreeObj clusterObj, List<Host> tempHosts) {
		for (Host host : tempHosts) {
			// 更新主机信息
			XenEntityTreeObj enti = new XenEntityTreeObj();
			enti.setUuid(host.getHostUuid());
			enti = xenEntityTreeService.queryTreeNode(enti);
			if (enti == null) {
				enti = new XenEntityTreeObj();
			}
			String hostUuid = host.getHostUuid();
			intermap.put(hostUuid, host);
			if (dbMap.get(hostUuid) == null) {
				String hostName = host.getHostName();
				Integer hostCpuNum = host.getHostCpuNum();
				String hostCpuDesc = host.getHostCpuDesc();
				Double hostCpuSpeed = host.getHostCpuSpeed();
				String hostIp = host.getHostIp();
				long memSize = host.getMemSize();
				if (!hostUuid.equals(pool_uuid)) {
					boolean isexist = confirmAndRemoveRepeatData(hostUuid);
					if (isexist) {
						// 更新主机的关系
						updateXenHostData(clusterObj, enti, hostUuid);
					} else {
						int host_id = hostInfoService.getIdSequence();
						enti = insertXenHostData(clusterObj, enti, hostUuid, host_id, hostName,
								hostCpuNum, hostCpuDesc, hostCpuSpeed, hostIp, memSize);
					}
				}
			}

			// 更新池或主机下虚拟机信息
			List<Vm> tempVms = host.getVms();
			for (Vm vm : tempVms) {
				intermap.put(vm.getVmUuid(), vm);
				// 获取基本信息
				String type = vm.getVmType();
				String vmName = vm.getVmName();
				String vm_uuid = vm.getVmUuid();
				long cpuNum = vm.getCpuNum();
				long memDynamicMax = vm.getMemDynamicMax();
				long virtualSrSize = vm.getVirtualSrSize();
				String vmIp = vm.getVmIps();
				String powerState = vm.getPowerState();
				String osName = vm.getOsName();
				long vifNum = vm.getVifNum();
				int parent_id = enti.getId();
				// 进行比对
				if (dbMap.get(vm_uuid) == null) {
					boolean isexist = confirmAndRemoveRepeatData(vm_uuid);
					if (isexist) {
						updataXenVmData(pool_uuid, clusterObj, hostUuid, type, vmName, vm_uuid,
								cpuNum, memDynamicMax, virtualSrSize, vmIp, powerState, osName,
								vifNum, parent_id);
					} else {
						insertXenVmData(pool_uuid, clusterObj, enti, hostUuid, type, vmName,
								vm_uuid, cpuNum, memDynamicMax, virtualSrSize, vmIp, powerState,
								osName, vifNum, parent_id);
					}

				} else {
					updataXenVmData(pool_uuid, clusterObj, hostUuid, type, vmName, vm_uuid, cpuNum,
							memDynamicMax, virtualSrSize, vmIp, powerState, osName, vifNum,
							parent_id);
				}

			}

			// 更新池或主机下的存储信息
			List<Sr> stores = host.getSrs();
			for (Sr sr : stores) {
				intermap.put(sr.getSrUuid(), sr);
				// 获取基本信息
				String srName = sr.getSrName();
				String srUuid = sr.getSrUuid();
				String srType = sr.getSrType();
				String srState = sr.getSrState();
				Boolean srShare = sr.isSrShared();
				long srSize = sr.getSrPhysicalSize();
				long useSize = sr.getSrPhysicalUtilisation();
				long freeSize = srSize - useSize;
				String srUrl = sr.getSrUrl();
				DecimalFormat format = new DecimalFormat("0");
				String usePer = "";
				if (useSize > 0 && srSize > 0) {
					usePer = format.format(useSize * 100 / srSize);
				} else {
					usePer = "0";
				}
				if (srSize > 0) {
					srSize = srSize / 1024;
				}
				if (freeSize > 0) {
					freeSize = freeSize / 1024;
				}
				int parent_id = enti.getId();
				// 进行存储比对
				if (dbMap.get(sr.getSrUuid()) == null) {
					boolean isexist = confirmAndRemoveRepeatData(srUuid);
					if (isexist) {
						updataXenStoreData(pool_uuid, clusterObj, hostUuid, sr, srName, srUuid,
								srType, srState, srShare, srSize, freeSize, srUrl, usePer,
								parent_id);
					} else {
						insertXenStoreData(pool_uuid, clusterObj, host, enti, hostUuid, sr, srName,
								srUuid, srType, srState, srShare, srSize, freeSize, srUrl, usePer,
								parent_id);
					}
				} else {
					updataXenStoreData(pool_uuid, clusterObj, hostUuid, sr, srName, srUuid, srType,
							srState, srShare, srSize, freeSize, srUrl, usePer, parent_id);
				}
			}
		}
	}

	/**
	 * 
	 * @Title: updateXenHostData
	 * @Description: 更新xen主机数据
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 16, 2013 5:27:26 PM
	 */
	private void updateXenHostData(XenEntityTreeObj clusterObj, XenEntityTreeObj enti,
			String hostUuid) {
		// 更新entity_tree中主机关系
		XenEntityTreeObj en = new XenEntityTreeObj();
		en.setUuid(hostUuid);
		en.setParentId(clusterObj.getId());
		xenEntityTreeService.updateNodeParentId(en);
		// 更新entity_con中主机关系
		XenEntityConObj ent = new XenEntityConObj();
		ent.setClusterId(clusterObj.getId());
		ent.setDataCenterId(clusterObj.getParentId());
		ent.setEntityId(enti.getEntityId());
		ent.setType(Integer.parseInt(TypeConstant.XEN_HOST));
		xenEntityTreeService.updateConRelation(ent);
	}

	/**
	 * 
	 * @Title: insertXenHostData
	 * @Description: 插入xen主机数据
	 * @param
	 * @return XenEntityTreeObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 16, 2013 5:26:34 PM
	 */
	private XenEntityTreeObj insertXenHostData(XenEntityTreeObj clusterObj, XenEntityTreeObj enti,
			String hostUuid, int host_id, String hostName, Integer hostCpuNum, String hostCpuDesc,
			Double hostCpuSpeed, String hostIp, long memSize) {
		// 向entity_tree中插入数据
		XenEntityTreeObj en = new XenEntityTreeObj();
		en.setEntityId(String.valueOf(host_id));
		en.setName(hostName);
		en.setUuid(hostUuid);
		en.setParentId(clusterObj.getId());
		en.setType(TypeConstant.XEN_HOST);
		xenEntityTreeService.insertTreeNode(en);
		// 查询主机的id
		enti.setEntityId(String.valueOf(host_id));
		enti = xenEntityTreeService.queryTreeNode(enti);
		// 向entity_con中插入数据
		XenEntityConObj ent = new XenEntityConObj();
		ent.setClusterId(enti.getParentId());
		ent.setDataCenterId(clusterObj.getParentId());
		ent.setName(hostName);
		ent.setEntityId(String.valueOf(host_id));
		ent.setType(Integer.parseInt(TypeConstant.XEN_HOST));
		xenEntityTreeService.insertConObj(ent);
		// 向Host表中插入数据
		TbCloud2HostInfoObj tb = new TbCloud2HostInfoObj();
		tb.setId(host_id);
		tb.setEq_id(host_id + "");
		tb.setEq_name(hostName);
		tb.setH_uuid(hostUuid);
		tb.setEq_type("2");// 表示刀片
		tb.setHasvertual("3");// 表示虚拟化类型
		tb.setCpu_fq(hostCpuSpeed + "");
		tb.setCpu_cl(hostCpuNum + "");
		tb.setCPU_DESC(hostCpuDesc);
		tb.setEq_ip(hostIp);
		tb.setMem(memSize + "");
		tb.setNIC_NUM(2);// 暂时写死，需要从接口获取。----------------------------------------------
		hostInfoService.insertByObj(tb);
		return enti;
	}

	/**
	 * 
	 * @Title: insertXenVmData
	 * @Description: 插入xen虚拟机数据
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 16, 2013 5:25:14 PM
	 */
	private void insertXenVmData(String pool_uuid, XenEntityTreeObj clusterObj,
			XenEntityTreeObj enti, String hostUuid, String type, String vmName, String vm_uuid,
			long cpuNum, long memDynamicMax, long virtualSrSize, String vmIp, String powerState,
			String osName, long vifNum, int parent_id) {
		// 向entity_tree中插入数据
		int vm_id = vmHostService.queryVhostIdSequence();
		XenEntityTreeObj eo = new XenEntityTreeObj();
		XenEntityConObj eno = new XenEntityConObj();
		eo.setEntityId(String.valueOf(vm_id));
		eo.setName(vmName);
		eo.setUuid(vm_uuid);
		if (hostUuid.equals(pool_uuid)) {
			eo.setParentId(clusterObj.getId());
			eno.setClusterId(clusterObj.getId());
		} else {
			eo.setParentId(parent_id);
			eno.setClusterId(enti.getParentId());
		}
		// 向entity_con中插入数据
		eno.setDataCenterId(clusterObj.getParentId());
		eno.setName(vmName);
		eno.setEntityId(String.valueOf(vm_id));
		// 向vmHost表中插入数据
		VMHostObj tempVm = new VMHostObj();
		if (type.equals("IsVm")) {
			eo.setType(TypeConstant.XEN_VM);
			eno.setType(Integer.parseInt(TypeConstant.XEN_VM));
			tempVm.setVH_TYPE("3");
		} else {
			eno.setType(Integer.parseInt(TypeConstant.XEN_IMAGE));
			eo.setType(TypeConstant.XEN_IMAGE);
			tempVm.setVH_TYPE("6");
		}
		xenEntityTreeService.insertTreeNode(eo);
		xenEntityTreeService.insertConObj(eno);
		tempVm.setID(vm_id);
		tempVm.setVH_ID(String.valueOf(vm_id));
		tempVm.setVH_NAME(vmName);
		tempVm.setVH_UUID(vm_uuid);
		tempVm.setVH_CPU(cpuNum + "");
		tempVm.setVH_MEM(memDynamicMax / 1024 / 1024 + "");
		tempVm.setVH_STORAGE(virtualSrSize / 1024 + "");
		tempVm.setVH_SYSTEM(osName);
		TbCloud2HostInfoObj tb = new TbCloud2HostInfoObj();
		tb.setHostUuids(hostUuid);
		tb = hostInfoService.queryByObj(tb);
		tempVm.setEQ_ID(tb.getEq_id() + "");
		tempVm.setVH_IP(vmIp);
		if ("HALTED".equalsIgnoreCase(powerState)) {
			tempVm.setVH_STAT("0");
		} else if ("RUNNING".equalsIgnoreCase(powerState)) {
			tempVm.setVH_STAT("1");
		} else {
			// 暂时写成这样
			tempVm.setVH_STAT("2");
		}
		tempVm.setVH_NETWORK(vifNum + "");
		vmHostService.insertByVMhostObj(tempVm);
	}

	/**
	 * 
	 * @Title: updataXenVmData
	 * @Description: 更新xen虚拟机的数据
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 16, 2013 5:24:17 PM
	 */
	private void updataXenVmData(String pool_uuid, XenEntityTreeObj clusterObj, String hostUuid,
			String type, String vmName, String vm_uuid, long cpuNum, long memDynamicMax,
			long virtualSrSize, String vmIp, String powerState, String osName, long vifNum,
			int parent_id) {
		// 更新entity_tree中数据
		XenEntityTreeObj eo = new XenEntityTreeObj();
		eo.setName(vmName);
		eo.setUuid(vm_uuid);
		if (hostUuid.equals(pool_uuid)) {
			eo.setParentId(clusterObj.getId());
		} else {
			eo.setParentId(parent_id);
		}
		// 更新vmHost表中数据
		VMHostObj tempVm = new VMHostObj();
		if (type.equals("IsVm")) {
			eo.setType(TypeConstant.XEN_VM);
			tempVm.setVH_TYPE("3");
		} else {
			eo.setType(TypeConstant.XEN_IMAGE);
			tempVm.setVH_TYPE("6");
		}
		xenEntityTreeService.updateTreeNodeMess(eo);
		tempVm.setVH_NAME(vmName);
		tempVm.setVH_UUID(vm_uuid);
		tempVm.setVH_CPU(cpuNum + "");
		tempVm.setVH_MEM(memDynamicMax / 1024 / 1024 + "");
		tempVm.setVH_STORAGE(virtualSrSize / 1024 + "");
		tempVm.setVH_SYSTEM(osName);
		if (hostUuid.equals(pool_uuid)) {
			XenClusterObj clObj = new XenClusterObj();
			clObj.setC_uuid(pool_uuid);
			clObj = xenClusterService.queryByObj(clObj);
			if (clObj != null) {
				tempVm.setEQ_ID(clObj.getId() + "");
			}
		} else {
			TbCloud2HostInfoObj tb = new TbCloud2HostInfoObj();
			tb.setH_uuid(hostUuid);
			tb = hostInfoService.queryByObj(tb);
			if (tb != null) {
				tempVm.setEQ_ID(tb.getEq_id() + "");
			}
		}
		tempVm.setVH_IP(vmIp);
		if ("HALTED".equalsIgnoreCase(powerState)) {
			tempVm.setVH_STAT("0");
		} else if ("RUNNING".equalsIgnoreCase(powerState)) {
			tempVm.setVH_STAT("1");
		} else {
			// 暂时写成这样
			tempVm.setVH_STAT("2");
		}
		tempVm.setVH_NETWORK(vifNum + "");
		vmHostService.updateVMHostMess(tempVm);
	}

	/**
	 * 
	 * @Title: insertXenStoreData
	 * @Description: 插入xen存储数据
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 16, 2013 5:21:41 PM
	 */
	private void insertXenStoreData(String pool_uuid, XenEntityTreeObj clusterObj, Host host,
			XenEntityTreeObj enti, String hostUuid, Sr sr, String srName, String srUuid,
			String srType, String srState, Boolean srShare, long srSize, long freeSize,
			String srUrl, String usePer, int parent_id) {
		// 向entity_tree中插入数据
		int sr_id = xenStoreService.querySRIdSequence();
		XenEntityTreeObj treeObj = new XenEntityTreeObj();
		XenEntityConObj conObj = new XenEntityConObj();
		treeObj.setEntityId(String.valueOf(sr_id));
		treeObj.setName(srName);
		treeObj.setUuid(srUuid);
		if (hostUuid.equals(pool_uuid)) {
			treeObj.setParentId(clusterObj.getId());
			conObj.setClusterId(clusterObj.getId());
		} else {
			treeObj.setParentId(parent_id);
			conObj.setClusterId(enti.getParentId());
		}
		// 向entity_con中插入数据
		conObj.setDataCenterId(clusterObj.getParentId());
		conObj.setName(srName);
		conObj.setEntityId(String.valueOf(sr_id));
		treeObj.setType(TypeConstant.XEN_HOST_STORAGE);
		conObj.setType(Integer.parseInt(TypeConstant.XEN_HOST_STORAGE));
		xenEntityTreeService.insertTreeNode(treeObj);
		xenEntityTreeService.insertConObj(conObj);

		// 向datestore_info表中插入数据
		XenStoreObj storeObj = new XenStoreObj();
		storeObj.setId(sr_id);
		storeObj.setName(srName);
		storeObj.setStore_uuid(srUuid);
		storeObj.setSr_url(srUrl);
		storeObj.setState(srState);
		storeObj.setType(srType);
		storeObj.setFree_size(freeSize + "");
		storeObj.setUse_per(usePer);
		storeObj.setSr_size(srSize + "");
		storeObj.setShared(srShare.toString());
		storeObj.setDependent_host_uuid(host.getHostUuid());
		boolean isPool = false;
		if (!host.getHostUuid().equals(pool_uuid)) {
			xenStoreService.insertDatastore(storeObj);
		} else {
			isPool = true;
		}
		if (srShare) {
			if (sr.getHostUuid() != null && sr.getHostUuid().size() > 0) {
				List<String> hostUuids = sr.getHostUuid();
				for (String s : hostUuids) {
					storeObj.setDependent_host_uuid(s);
					if (isPool) {
						int ret = xenStoreService.insertDatastore(storeObj);
						if (ret != -1)
							isPool = false;
					} else {
						storeObj.setId(xenStoreService.querySRIdSequence());
						xenStoreService.insertDatastore(storeObj);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @Title: updataXenStoreData
	 * @Description: 更新存储的数据
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 16, 2013 5:20:21 PM
	 */
	private void updataXenStoreData(String pool_uuid, XenEntityTreeObj clusterObj, String hostUuid,
			Sr sr, String srName, String srUuid, String srType, String srState, Boolean srShare,
			long srSize, long freeSize, String srUrl, String usePer, int parent_id) {
		// 更新entity_tree中的数据
		XenEntityTreeObj treeObj = new XenEntityTreeObj();
		treeObj.setName(srName);
		treeObj.setUuid(srUuid);
		if (hostUuid.equals(pool_uuid)) {
			treeObj.setParentId(clusterObj.getId());
		} else {
			treeObj.setParentId(parent_id);
		}
		treeObj.setType(TypeConstant.XEN_HOST_STORAGE);
		xenEntityTreeService.updateTreeNodeMess(treeObj);

		// 更新datestore_info表中的数据
		XenStoreObj storeObj = new XenStoreObj();
		storeObj.setName(srName);
		storeObj.setStore_uuid(srUuid);
		storeObj.setSr_url(srUrl);
		storeObj.setState(srState);
		storeObj.setType(srType);
		storeObj.setFree_size(freeSize + "");
		storeObj.setUse_per(usePer);
		storeObj.setSr_size(srSize + "");
		storeObj.setShared(srShare.toString());
		xenStoreService.updateXenStoreMess(storeObj);
		// 判断共享存储下关联的主机
		if (srShare) {
			if (sr.getHostUuid() != null && sr.getHostUuid().size() > 0) {
				List<String> hostUuids = sr.getHostUuid();
				XenStoreObj stObj = new XenStoreObj();
				stObj.setStore_uuid(srUuid);
				List storeLst = xenStoreService.queryForStoreList(stObj);
				boolean match = false;
				// 库中关系和接口中关系比较
				for (Object o : storeLst) {
					stObj = (XenStoreObj) o;
					for (String s : hostUuids) {
						if (stObj.getDependent_host_uuid().equals(s)) {
							match = true;
						}
					}
					if (!match) {
						xenStoreService.deleteStoreRelation(stObj);
					}
					match = false;
				}
				// 接口中关系和库中关系比较
				for (String s : hostUuids) {
					for (Object o : storeLst) {
						stObj = (XenStoreObj) o;
						if (stObj.getDependent_host_uuid().equals(s)) {
							match = true;
						}
					}
					if (!match) {
						storeObj.setId(xenStoreService.querySRIdSequence());
						storeObj.setDependent_host_uuid(s);
						xenStoreService.insertDatastore(storeObj);
					}
					match = false;
				}
			}
		}
	}

	/**
	 * 
	 * @Title: datastoreCompareInterface
	 * @Description: 数据库中数据和接口中数据比较
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 16, 2013 5:18:05 PM
	 */
	private void datastoreCompareInterface(Map<String, Object> dbMap, Map<String, Object> intermap) {
		Iterator<Entry<String, Object>> dbIterator = dbMap.entrySet().iterator();
		while (dbIterator.hasNext()) {
			Entry<String, Object> entry = dbIterator.next();
			String key = entry.getKey();
			if (intermap.get(key) == null) {
				XenEntityTreeObj obj = (XenEntityTreeObj) entry.getValue();
				if (obj.getType().equals(TypeConstant.XEN_HOST)) {
					logger.info("接口中不存在!---" + obj.getName());
					deleteHostData(obj);
				} else if (obj.getType().equals(TypeConstant.XEN_VM)
						|| obj.getType().equals(TypeConstant.XEN_IMAGE)) {
					logger.info("接口中不存在!---" + obj.getName());
					// 删除数据
					deleteVmData(obj);
				} else if (obj.getType().equals(TypeConstant.XEN_HOST_STORAGE)) {
					logger.info("接口中不存在!---" + obj.getName());
					// 删除数据
					deleteStoreData(obj);
				}
			}
		}
	}

	/**
	 * 
	 * @Title: deleteStoreData
	 * @Description: 删除存储相关数据
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 16, 2013 5:16:41 PM
	 */
	private void deleteStoreData(XenEntityTreeObj obj) {
		// storage_info
		XenStoreObj storeObj = new XenStoreObj();
		storeObj.setStore_uuid(obj.getUuid());
		xenStoreService.deleteByObj(storeObj);
		// entity_tree
		XenEntityTreeObj tree = new XenEntityTreeObj();
		tree.setUuid(obj.getUuid());
		xenEntityTreeService.delTreeNode(tree);
		// entity_con
		XenEntityConObj con = new XenEntityConObj();
		con.setType(Integer.parseInt(TypeConstant.XEN_HOST_STORAGE));
		con.setEntityId(String.valueOf(obj.getEntityId()));
		xenEntityTreeService.deleteConObj(con);
	}

	/**
	 * 
	 * @Title: deleteVmData
	 * @Description: 删除虚拟机相关数据
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 16, 2013 5:15:40 PM
	 */
	private void deleteVmData(XenEntityTreeObj obj) {
		// vmhost_info
		VMHostObj vmObj = new VMHostObj();
		vmObj.setVH_UUID(obj.getUuid());
		vmHostService.deleteByObj(vmObj);
		// entity_tree
		XenEntityTreeObj tree = new XenEntityTreeObj();
		tree.setUuid(obj.getUuid());
		xenEntityTreeService.delTreeNode(tree);
		// entity_con
		XenEntityConObj con = new XenEntityConObj();
		if (obj.getType().equals(TypeConstant.XEN_VM)) {
			con.setType(Integer.parseInt(TypeConstant.XEN_VM));
		} else if (obj.getType().equals(TypeConstant.XEN_IMAGE)) {
			con.setType(Integer.parseInt(TypeConstant.XEN_IMAGE));
		}
		con.setEntityId(String.valueOf(obj.getEntityId()));
		xenEntityTreeService.deleteConObj(con);
	}

	/**
	 * 
	 * @Title: deleteHostData
	 * @Description: 删除主机相关数据
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 16, 2013 5:13:51 PM
	 */
	private void deleteHostData(XenEntityTreeObj obj) {
		// 删除数据host_info
		TbCloud2HostInfoObj HostObj = new TbCloud2HostInfoObj();
		String hostUuidDel = obj.getUuid();
		HostObj.setH_uuid(hostUuidDel);
		hostInfoService.deleteByObj(HostObj);
		// entity_tree
		XenEntityTreeObj tree = new XenEntityTreeObj();
		tree.setUuid(hostUuidDel);
		xenEntityTreeService.delTreeNode(tree);
		// entity_con
		XenEntityConObj con = new XenEntityConObj();
		con.setType(Integer.parseInt(TypeConstant.XEN_HOST));
		con.setEntityId(obj.getEntityId());
		xenEntityTreeService.deleteConObj(con);
	}

	/**
	 * 
	 * @Title: returnMessage
	 * @Description: ajax返回信息
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws IOException
	 * @createtime Mar 25, 2013 3:52:19 PM
	 */
	public void returnMessage(String result) throws IOException {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = JSONObject.fromObject(result);
		// PrintWriter p = response.getWriter();
		// p.print(json.toString());
		// p.close();
		PrintWriterOut.printWirter(response, json.toString());
	}

	/**
	 * 
	 * @Title: confirmAndRemoveRepeatData
	 * @Description: 确定是否存在及删除重复数据
	 * @param
	 * @return boolean
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 16, 2013 1:09:58 PM
	 */
	private boolean confirmAndRemoveRepeatData(String Uuid) {
		XenEntityTreeObj treeObj = new XenEntityTreeObj();
		treeObj.setUuid(Uuid);
		treeObj = xenEntityTreeService.queryTreeNode(treeObj);
		if (treeObj == null) {
			return false;
		} else {
			// //如果数据重复，保留序号较小的，其它删除
			// while (hostLst.size()==1) {
			// int maxId = 0;
			// int index = 0;
			// for(int i=0;i<hostLst.size();i++){
			// int id = hostLst.get(i).getId();
			// if(maxId< id){
			// maxId = id;
			// index = i;
			// }
			// }
			// XenEntityTreeObj obj = hostLst.get(index);
			// hostLst.remove(index);
			//
			// if(obj.getType().equals(TypeConstant.XEN_VM)){
			// // vmhost_info
			// VMHostObj vmObj = new VMHostObj();
			// vmObj.setID(Integer.parseInt(obj.getEntityId()==null||obj.getEntityId().equals("")?"0":obj.getEntityId()));
			// vmHostService.deleteByObj(vmObj);
			// // entity_con
			// XenEntityConObj con = new XenEntityConObj();
			// con.setType(Integer.parseInt(TypeConstant.XEN_VM));
			// con.setEntityId(obj.getEntityId());
			// xenEntityTreeService.deleteConObj(con);
			// }else if(obj.getType().equals(TypeConstant.XEN_IMAGE)){
			// VMHostObj vmObj = new VMHostObj();
			// vmObj.setID(Integer.parseInt(obj.getEntityId()==null||obj.getEntityId().equals("")?"0":obj.getEntityId()));
			// vmHostService.deleteByObj(vmObj);
			// // entity_con
			// XenEntityConObj con = new XenEntityConObj();
			// con.setType(Integer.parseInt(TypeConstant.XEN_IMAGE));
			// con.setEntityId(obj.getEntityId());
			// xenEntityTreeService.deleteConObj(con);
			// }else if(obj.getType().equals(TypeConstant.XEN_HOST)){
			// // 删除数据host_info
			// TbCloud2HostInfoObj HostObj = new TbCloud2HostInfoObj();
			// HostObj.setId(Integer.parseInt(obj.getEntityId()==null||obj.getEntityId().equals("")?"0":obj.getEntityId()));
			// hostInfoService.deleteByObj(HostObj);
			// // entity_con
			// XenEntityConObj con = new XenEntityConObj();
			// con.setType(Integer.parseInt(TypeConstant.XEN_HOST));
			// con.setEntityId(obj.getEntityId());
			// xenEntityTreeService.deleteConObj(con);
			// }else if(obj.getType().equals(TypeConstant.XEN_HOST_STORAGE)){
			// // storage_info
			// XenStoreObj storeObj = new XenStoreObj();
			// storeObj.setId(Integer.parseInt(obj.getEntityId()==null||obj.getEntityId().equals("")?"0":obj.getEntityId()));
			// xenStoreService.deleteByObj(storeObj);
			// // entity_con
			// XenEntityConObj con = new XenEntityConObj();
			// con.setType(Integer.parseInt(TypeConstant.XEN_HOST_STORAGE));
			// con.setEntityId(obj.getEntityId());
			// xenEntityTreeService.deleteConObj(con);
			// }
			// // entity_tree
			// XenEntityTreeObj tree = new XenEntityTreeObj();
			// tree.setId(obj.getId());
			// xenEntityTreeService.delTreeNode(tree);
			// }
			return true;
		}
	}

	public void setXenManService(XenManService xenManService) {
		this.xenManService = xenManService;
	}

	public void setVmHostService(VMHostService vmHostService) {
		this.vmHostService = vmHostService;
	}

	public void setXenClusterService(XenClusterService xenClusterService) {
		this.xenClusterService = xenClusterService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public void setXenEntityTreeService(XenEntityTreeService xenEntityTreeService) {
		this.xenEntityTreeService = xenEntityTreeService;
	}

	public void setXenStoreService(XenStoreService xenStoreService) {
		this.xenStoreService = xenStoreService;
	}

}
