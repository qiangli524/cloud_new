package com.sitech.basd.resource.service.united;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.template.TemManDao;
import com.sitech.basd.resource.dao.united.UnitedTreeDao;
import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.util.AnalysisXenDataRelation;
import com.sitech.basd.resource.util.HostSystemType;
import com.sitech.basd.resource.util.RevertEntity;
import com.sitech.basd.resource.util.VirtualClient;
import com.sitech.basd.resource.util.VirtualMachineType;
import com.sitech.basd.resource.util.VirtualTemplateType;
import com.sitech.basd.sxcloud.cloud.dao.resource.HostInfoDao;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.yicloud.dao.xencluster.XenClusterDao;
import com.sitech.basd.yicloud.dao.xenstore.XenStoreDao;
import com.sitech.basd.yicloud.domain.xen.Host;
import com.sitech.basd.yicloud.domain.xen.Sr;
import com.sitech.basd.yicloud.domain.xen.Vm;
import com.sitech.basd.yicloud.domain.xenstore.XenStoreObj;
import com.sitech.basd.yicloud.domain.xentree.XenClusterObj;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.vo.united.ClusterUnitedVO;
import com.sitech.vo.united.DrsVO;
import com.sitech.vo.united.HaVO;
import com.sitech.vo.united.HostSystemConnectStatus;
import com.sitech.vo.united.VirtualMachineConnectStatus;
import com.sitech.vo.united.VirtualMachinePowerStatus;
import com.sitech.vo.util.CloudConstant;
import com.sitech.vo.util.UnitedConstant;
import com.sitech.vo.util.VirtualConstant;

@Service("unitedClusterService")
public class UnitedClusterServiceImpl implements UnitedClusterService {
	@Autowired
	private UnitedTreeDao unitedTreeDao;
	@Autowired
	private XenClusterDao xenClusterDao;
	@Autowired
	private HostInfoDao hostInfoDao;
	@Autowired
	private VMHostDao vmHostDao;
	@Autowired
	private XenStoreDao xenStoreDao;
	@Autowired
	private TemManDao temManDao;
	@Autowired
	private RabbitMQUtil rabbitmqUtil;

	/**
	 * 
	 * @Title: createCluster
	 * @Description: 创建集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 *             , Exce
	 * 
	 * @createtime Jul 23, 2013 9:33:49 AM
	 */
	public String createCluster(UnitedTreeObj obj, ClusterUnitedVO vo) throws Exception {
		String vtype = obj.getVtype();
		obj.setType(UnitedConstant.CLUSTER);
		String result = UnitedConstant.FAIL;
		String url = "";
		if (UnitedConstant.VMWARE.equals(vtype)) {
			vo = RevertEntity.toRestCluster(obj);
			url = "cluster/" + VirtualConstant.VT_VMWARE + "/create";
			vo = VirtualClient.post(url, vo, new JacksonUtil.TypeReference<ClusterUnitedVO>() {
			});
			if (vo.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				obj.setUuid(vo.getClusterCode());
				unitedTreeDao.insertByObj(obj);
			} else {
				result = result + vo.getLog();
			}

		} else if (UnitedConstant.XEN.equals(vtype)) {
			// update xugang 2013-11-29
			/*
			 * url = "conn/" + VirtualConstant.VT_XEN + "/init/" +
			 * vo.getAddress() + "/" + vo.getHostUserName() + "/" + password;
			 */
			url = "cluster/" + VirtualConstant.VT_XEN + "/create";
			vo = VirtualClient.post(url, vo, new JacksonUtil.TypeReference<ClusterUnitedVO>() {
			});
			if (vo.getIsSuccess()) {
				// 资源池入库
				// unitedTreeDao.insertByObj(obj);
				listAllHostsByCluster(obj.getParent_id(), vo);
				// 发送消息，通知监控，修改xen配置文件
				vo.setOptype(CloudConstant.CLUSTER_OPERATE_CREATE);
				rabbitmqUtil.publishMessage("", "xen.cluster.operate", vo);
				result = UnitedConstant.SUCCESS;
			} else {
				result = vo.getLog();
			}
		} else {
			unitedTreeDao.insertByObj(obj);
			obj.setConnect_id("");
			result = UnitedConstant.SUCCESS;
		}
		return result;
	}

	public void listAllHostsByCluster(String parent_id, ClusterUnitedVO vo)
			throws HttpClientException, SQLException {
		String poolUuid = vo.getConnectCode();
		/**
		 * 查询池链接下所有虚拟机的信息
		 */
		String getVmUrl = "conn/" + VirtualConstant.VT_XEN + "/getVms/" + poolUuid;
		ClusterUnitedVO vmsClu = VirtualClient.get(getVmUrl,
				new JacksonUtil.TypeReference<ClusterUnitedVO>() {
				});
		if (!vmsClu.isSuccess) {
			return;
		}
		/**
		 * 查询链接下所有主机的信息
		 */
		String getHostUrl = "conn/" + VirtualConstant.VT_XEN + "/gethosts/" + poolUuid;
		ClusterUnitedVO hostsClu = VirtualClient.get(getHostUrl,
				new JacksonUtil.TypeReference<ClusterUnitedVO>() {
				});
		if (!hostsClu.isSuccess) {
			return;
		}
		/**
		 * 查询链接下所有存储的信息
		 */
		String getDsUrl = "conn/" + VirtualConstant.VT_XEN + "/getDS/" + poolUuid;
		ClusterUnitedVO datastoresClu = VirtualClient.get(getDsUrl,
				new JacksonUtil.TypeReference<ClusterUnitedVO>() {
				});
		if (!datastoresClu.isSuccess) {
			return;
		}

		// ----------------------接口查询成功，进行数据库操作--------------------------------------
		// 向树表中插入数据,向集群表中插入数据
		UnitedTreeObj cluTreeObj = new UnitedTreeObj();
		XenClusterObj clu = new XenClusterObj();
		if (vo.getClusterName() == null || "".equals(vo.getClusterName())) {
			cluTreeObj.setName(vo.getAddress());
			clu.setName(vo.getAddress());
		} else {
			cluTreeObj.setName(vo.getClusterName());
			clu.setName(vo.getClusterName());
		}
		cluTreeObj.setUuid(vo.getConnectCode());
		cluTreeObj.setParent_id(parent_id);
		cluTreeObj.setVtype(UnitedConstant.XEN);
		cluTreeObj.setType(UnitedConstant.CLUSTER);
		cluTreeObj.setConnect_id(vo.getConnectCode());
		// ---------------------
		int cls_ID = xenClusterDao.getIdSequence();
		clu.setId(cls_ID);
		clu.setC_uuid(vo.getConnectCode());
		clu.setType(UnitedConstant.CLUSTER);
		clu.setDrsstate("0");
		clu.setHastate("0");
		String clusterTree_id = unitedTreeDao.insertByObj(cluTreeObj);
		xenClusterDao.insertForConnection(clu);

		// ----------------------构建树中主机和虚拟机和存储之间的关系操作
		List<Host> tempHosts = AnalysisXenDataRelation.analysisHostAndVm(hostsClu.getHosts(),
				vmsClu.getVms(), datastoresClu.getDatastores(), vo.getConnectCode());
		for (Host host : tempHosts) {
			int host_id = hostInfoDao.getIdSequence();
			String hostName = host.getHostName();
			String hostUuid = host.getHostUuid();
			Integer hostCpuNum = host.getHostCpuNum();
			String hostCpuDesc = host.getHostCpuDesc();
			Double hostCpuSpeed = host.getHostCpuSpeed();
			String hostIp = host.getHostIp();
			long memSize = host.getMemSize();
			String hostTree_id = "";
			if (!hostUuid.equals(poolUuid)) {
				// 向united_tree中插入数据
				UnitedTreeObj hostTreeObj = new UnitedTreeObj();
				hostTreeObj.setName(host.getHostName());
				hostTreeObj.setUuid(host.getHostUuid());
				hostTreeObj.setParent_id(clusterTree_id);
				hostTreeObj.setVtype(UnitedConstant.XEN);
				hostTreeObj.setType(UnitedConstant.HOST);
				hostTreeObj.setConnect_id(poolUuid);
				hostTree_id = unitedTreeDao.insertByObj(hostTreeObj);
				// 向Host表中插入数据
				TbCloud2HostInfoObj tb = new TbCloud2HostInfoObj();
				tb.setConnectId(poolUuid);
				tb.setId(host_id);
				tb.setEq_id(host_id + "");
				tb.setEq_name(hostName);
				tb.setH_uuid(hostUuid);
				tb.setEq_type("2");// 表示刀片
				tb.setHasvertual(HostSystemType.XEN);// 表示虚拟化类型
				tb.setCpu_fq(hostCpuSpeed + "");
				tb.setCpu_cl(hostCpuNum + "");
				tb.setCPU_DESC(hostCpuDesc);
				tb.setEq_ip(hostIp);
				tb.setMem(memSize + "");
				tb.setNIC_NUM(2);//
				// 暂时写死，需要从接口获取。----------------------------------------------
				tb.setEq_hostname(hostName);

				tb.setMaxVcpu(host.getMaxHostSupportedVcpus());
				tb.setUsedCpu(host.getUsedCpuMHz());
				tb.setUsedMemMb(host.getUserMemMb());
				tb.setMaxCpu(host.getMaxCpuMHz());
				tb.setUsedVcpu(host.getUsedHostVcpus());
				tb.setAllocated(1);
				tb.setConnectStatus(HostSystemConnectStatus.connected);
				hostInfoDao.insertByObj(tb);
			}
			/**
			 * 遍历虚拟机
			 */
			List<Vm> tempVms = host.getVms();
			for (Vm vm : tempVms) {
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
				if (type.equals("IsVm")) {
					// 向entity_tree中插入数据
					UnitedTreeObj vmTreeObj = new UnitedTreeObj();
					vmTreeObj.setName(vm.getVmName());
					vmTreeObj.setUuid(vm.getVmUuid());
					if (hostUuid.equals(poolUuid)) {
						vmTreeObj.setParent_id(clusterTree_id);
					} else {
						vmTreeObj.setParent_id(hostTree_id);
					}
					vmTreeObj.setVtype(UnitedConstant.XEN);
					vmTreeObj.setType(UnitedConstant.VM);
					vmTreeObj.setConnect_id(poolUuid);
					unitedTreeDao.insertByObj(vmTreeObj);
					// 向vmHost表中插入数据
					VMHostObj tempVm = new VMHostObj();
					tempVm.setVH_TYPE(VirtualMachineType.XEN);
					int vm_id = vmHostDao.queryVhostIdSequence();
					tempVm.setID(vm_id);
					tempVm.setVH_ID(String.valueOf(vm_id));
					tempVm.setVH_NAME(vmName);
					tempVm.setVH_UUID(vm_uuid);
					tempVm.setVH_CPU(cpuNum + "");
					tempVm.setVH_MEM(memDynamicMax + "");
					tempVm.setVH_STORAGE(virtualSrSize + "");
					tempVm.setVH_SYSTEM(osName);
					tempVm.setEQ_ID(host_id + "");
					tempVm.setVH_IP(vmIp);
					tempVm.setConnectId(poolUuid);
					tempVm.setHostCode(host.getHostUuid());
					if (VirtualMachinePowerStatus.powerOff.equalsIgnoreCase(powerState)) {
						tempVm.setVH_STAT("0");
					} else if (VirtualMachinePowerStatus.powerOn.equalsIgnoreCase(powerState)) {
						tempVm.setVH_STAT("1");
					} else {
						// 暂时写成这样
						tempVm.setVH_STAT("2");
					}
					tempVm.setVH_NETWORK(vifNum + "");
					tempVm.setConnectStatus(VirtualMachineConnectStatus.connected);
					vmHostDao.insertByVMhostObj(tempVm);
				} else {
					TemManObj temObj = new TemManObj();
					temObj.setName(vmName);
					temObj.setType(VirtualTemplateType.XEN);
					temObj.setIsPublic("0");
					temObj.setRemark(vm.getDescription());
					temObj.setSystem(vm.getOsName());
					temObj.setCpu(new Long(vm.getCpuNum()).intValue());
					temObj.setMem(new Long(vm.getMemDynamicMax()).intValue());
					temObj.setStore(vm.getVirtualSrSize());
					temObj.setHostCode(host.getHostUuid());
					temObj.setConnectId(poolUuid);
					temObj.setTemplateCode(vm.getVmUuid());
					temManDao.insertByObj(temObj);
				}
			}
			/**
			 * 遍历存储
			 */
			List<Sr> stores = host.getSrs();
			for (Sr sr : stores) {
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
				String srHostName = sr.getHostName();
				List<String> srHostUuid = sr.getHostUuid();

				int sr_id = xenStoreDao.querySRIdSequence();
				// 向datestore_info表中插入数据
				XenStoreObj storeObj = new XenStoreObj();
				storeObj.setId(sr_id);
				storeObj.setName(srName);
				storeObj.setStore_uuid(srUuid);
				storeObj.setSr_url(srUrl);
				storeObj.setState(srState);
				storeObj.setType(srType.toUpperCase());
				storeObj.setFree_size(freeSize + "");
				storeObj.setUse_per(usePer);
				storeObj.setSr_size(srSize + "");
				storeObj.setShared(srShare.toString());
				storeObj.setDependent_host_uuid(host.getHostUuid());
				storeObj.setConnect_id(poolUuid);
				boolean isPool = false;
				if (!host.getHostUuid().equals(poolUuid)) {
					xenStoreDao.insertDatastore(storeObj);
				} else {
					isPool = true;
				}
				if (srShare) {
					if (sr.getHostUuid() != null && sr.getHostUuid().size() > 0) {
						List<String> hostUuids = sr.getHostUuid();
						for (String s : hostUuids) {
							storeObj.setDependent_host_uuid(s);
							if (isPool) {
								int ret = xenStoreDao.insertDatastore(storeObj);
								if (ret != -1)
									isPool = false;
							} else {
								storeObj.setId(xenStoreDao.querySRIdSequence());
								xenStoreDao.insertDatastore(storeObj);
							}
						}
					}
				}

			}
		}
	}

	/**
	 * 
	 * @Title: deleteCluster
	 * @Description: 删除集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @throws Exception
	 * @createtime Jul 26, 2013 6:03:46 PM
	 */
	public String deleteCluster(UnitedTreeObj obj) throws Exception {
		String vtype = obj.getVtype();
		String result = UnitedConstant.FAIL;
		String url = "";
		ClusterUnitedVO vo = new ClusterUnitedVO();
		if (UnitedConstant.VMWARE.equals(vtype)) {
			url = "cluster/" + VirtualConstant.VT_VMWARE + "/delete/" + obj.getConnect_id() + "/"
					+ obj.getUuid();
			vo = VirtualClient.delete(url, new JacksonUtil.TypeReference<ClusterUnitedVO>() {
			});
			if (vo.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				unitedTreeDao.deleteByObj(obj);
			} else {
				result = vo.getLog();
			}
		} else if (UnitedConstant.XEN.equals(vtype)) {
			if ("null".equals(obj.getConnect_id())) {
				obj.setConnect_id(null);
			}
			if ("null".equals(obj.getUuid())) {
				obj.setUuid(null);
			}
			if (obj.getConnect_id() != null) {
				url = "cluster/" + VirtualConstant.VT_XEN + "/delete/" + obj.getConnect_id() + "/"
						+ obj.getUuid();
				vo = VirtualClient.delete(url, new JacksonUtil.TypeReference<ClusterUnitedVO>() {
				});
				if (vo.getIsSuccess()) {
					// 发送消息，通知监控，修改xen配置文件
					vo.setOptype(CloudConstant.CLUSTER_OPERATE_DELETE);
					rabbitmqUtil.publishMessage("", "xen.cluster.operate", vo);
					result = UnitedConstant.SUCCESS;
					unitedTreeDao.deleteByObj(obj);
				} else {
					result = vo.getLog();
				}
			} else {
				unitedTreeDao.deleteByObj(obj);
				result = UnitedConstant.SUCCESS;
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: syncCluster
	 * @Description: xen同步集群
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws HttpClientException
	 * @throws SQLException
	 * @createtime 2013-9-18 上午11:17:33
	 */
	public String syncCluster(String parent_id) throws HttpClientException, SQLException {
		String result = UnitedConstant.FAIL;
		String url = "";
		List<ClusterUnitedVO> interCluLst = null;
		url = "conn/" + VirtualConstant.VT_XEN + "/getPools/";
		interCluLst = VirtualClient.get(url,
				new JacksonUtil.TypeReference<List<ClusterUnitedVO>>() {
				});
		if (interCluLst == null) {
			return result;
		} else {
			UnitedTreeObj treeObj = new UnitedTreeObj();
			treeObj.setType(UnitedConstant.XEN);
			treeObj.setVtype(UnitedConstant.CLUSTER);
			List<UnitedTreeObj> dbClulst = unitedTreeDao.queryForUnitedTree(treeObj);
			boolean match = false;
			for (int i = 0; i < interCluLst.size(); i++) {
				ClusterUnitedVO vo = interCluLst.get(i);
				for (UnitedTreeObj unitedTreeObj : dbClulst) {
					if (vo.getConnectCode().equals(unitedTreeObj.getConnect_id())) {
						match = true;
						break;
					}
				}
				if (!match) {
					// 删除库中新添加资源池下的实体
					if (vo.getConnectCode() != null && !"".equals(vo.getConnectCode())) {
						String connectCode = vo.getConnectCode();
						UnitedTreeObj delTreeObj = new UnitedTreeObj();
						delTreeObj.setConnect_id(connectCode);
						unitedTreeDao.deleteByObj(delTreeObj);
						VMHostObj vmHostObj = new VMHostObj();
						vmHostObj.setConnectId(connectCode);
						vmHostDao.deleteByObj(vmHostObj);
						TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
						hostObj.setConnectId(connectCode);
						hostInfoDao.deleteByObj(hostObj);
						XenStoreObj storeObj = new XenStoreObj();
						storeObj.setConnect_id(connectCode);
						xenStoreDao.deleteByObj(storeObj);
						TemManObj temObj = new TemManObj();
						temObj.setConnectId(connectCode);
						temManDao.deleteByObj(temObj);
						XenClusterObj clusterObj = new XenClusterObj();
						clusterObj.setC_uuid(connectCode);
						xenClusterDao.deleteByObj(clusterObj);
					}
					// 向库中添加新的资源池
					ClusterUnitedVO insertClu = new ClusterUnitedVO();
					insertClu.setAddress(vo.getAddress());
					insertClu.setClusterName(vo.getClusterName());
					insertClu.setClusterCode(vo.getConnectCode());
					insertClu.setConnectCode(vo.getConnectCode());
					listAllHostsByCluster(parent_id, insertClu);
				}
				match = false;
			}
		}
		result = UnitedConstant.SUCCESS;
		return result;
	}

	/**
	 * 
	 * @Title: getClusterInfo
	 * @Description: 获取指定集群的信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime 2014-4-1 下午2:37:06
	 */
	public ClusterUnitedVO getClusterInfo(UnitedTreeObj obj) throws HttpClientException {
		ClusterUnitedVO vo = new ClusterUnitedVO();
		if (UnitedConstant.VMWARE.equals(obj.getVtype())) {
			String url = "cluster/" + VirtualConstant.VT_VMWARE + "/info/" + obj.getConnect_id()
					+ "/" + obj.getUuid();
			vo = VirtualClient.get(url, new JacksonUtil.TypeReference<ClusterUnitedVO>() {
			});
		}
		return vo;
	}

	/**
	 * 
	 * @Title: editCluster
	 * @Description: 编辑设置集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime 2014-4-1 下午2:37:06
	 */
	public String editCluster(UnitedTreeObj obj, ClusterUnitedVO clusterUnitedVO)
			throws HttpClientException {
		String result = UnitedConstant.FAIL;
		if (UnitedConstant.VMWARE.equals(obj.getVtype())) {
			String url = "cluster/" + VirtualConstant.VT_VMWARE + "/edit";
			/*
			 * HaDrsVO vo = new HaDrsVO();
			 * vo.setEnableHA(clusterUnitedVO.isHastate());
			 * vo.setEnableDRS(clusterUnitedVO.isDrsstate());
			 * vo.setConnectCode(obj.getConnect_id());
			 * vo.setClusterCode(obj.getUuid());
			 * 
			 * 
			 * vo = VirtualClient.put(url, vo, new
			 * JacksonUtil.TypeReference<HaDrsVO>() {
			 */
			clusterUnitedVO.setConnectCode(obj.getConnect_id());
			clusterUnitedVO.setClusterCode(obj.getUuid());

			if (null != clusterUnitedVO.isHastate() && clusterUnitedVO.isHastate()) {
				clusterUnitedVO.getHaVO().setEnableHA(true);
			} else {
				HaVO havo = new HaVO();
				havo.setEnableHA(false);
				clusterUnitedVO.setHaVO(havo);
			}
			if (null != clusterUnitedVO.isDrsstate() && clusterUnitedVO.isDrsstate()) {
				clusterUnitedVO.getDrsVO().setEnableDRS(true);
			} else {
				DrsVO drsvo = new DrsVO();
				drsvo.setEnableDRS(false);
				clusterUnitedVO.setDrsVO(drsvo);
			}

			clusterUnitedVO = VirtualClient.put(url, clusterUnitedVO,
					new JacksonUtil.TypeReference<ClusterUnitedVO>() {
					});
			if (clusterUnitedVO.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
			} else {
				result = "编辑集群失败！Failed，" + clusterUnitedVO.getLog();
			}
		}
		return result;
	}
}
