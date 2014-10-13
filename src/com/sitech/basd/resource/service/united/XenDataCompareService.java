package com.sitech.basd.resource.service.united;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.template.TemManDao;
import com.sitech.basd.resource.dao.united.UnitedTreeDao;
import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.util.AnalysisXenDataRelation;
import com.sitech.basd.resource.util.HostSystemType;
import com.sitech.basd.resource.util.VirtualClient;
import com.sitech.basd.resource.util.VirtualMachineType;
import com.sitech.basd.resource.util.VirtualTemplateType;
import com.sitech.basd.sxcloud.cloud.dao.resource.HostInfoDao;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.yicloud.dao.xencluster.XenClusterDao;
import com.sitech.basd.yicloud.dao.xenstore.XenStoreDao;
import com.sitech.basd.yicloud.domain.xen.Host;
import com.sitech.basd.yicloud.domain.xen.Sr;
import com.sitech.basd.yicloud.domain.xen.Vm;
import com.sitech.basd.yicloud.domain.xenstore.XenStoreObj;
import com.sitech.basd.yicloud.domain.xentree.XenClusterObj;
import com.sitech.basd.yicloud.service.xencluster.XenClusterService;
import com.sitech.basd.yicloud.service.xenstore.XenStoreService;
import com.sitech.basd.yicloud.web.vmsyndata.action.DataCompare;
import com.sitech.ssd.ah.nas.dao.NasFileSysDao;
import com.sitech.ssd.ah.nas.domain.VmIpObj;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.vo.united.ClusterUnitedVO;
import com.sitech.vo.united.DatastoreUnitedVO;
import com.sitech.vo.united.HostSystemConnectStatus;
import com.sitech.vo.united.HostUnitedVO;
import com.sitech.vo.united.VirtualMachineConnectStatus;
import com.sitech.vo.united.VirtualMachinePowerStatus;
import com.sitech.vo.united.VirtualMachineUnitedVO;
import com.sitech.vo.united.VirtualNicUnitedVO;
import com.sitech.vo.util.UnitedConstant;
import com.sitech.vo.util.VirtualConstant;
import com.sitech.vo.util.XenTypes;

@Service("xenDataCompareService")
@SuppressWarnings("all")
public class XenDataCompareService {

	private static final Logger logger = Logger.getLogger(DataCompare.class.getName());
	@Autowired
	private VMHostService vmHostService;
	@Autowired
	private XenClusterService xenClusterService;
	@Autowired
	private HostInfoService hostInfoService;
	@Autowired
	private UnitedTreeService unitedTreeService;
	@Autowired
	private XenStoreService xenStoreService;
	@Autowired
	private TemManDao temManDao;
	@Autowired
	private XenClusterDao xenClusterDao;
	@Autowired
	private UnitedTreeDao unitedTreeDao;
	@Autowired
	private HostInfoDao hostInfoDao;
	@Autowired
	private VMHostDao vmHostDao;
	@Autowired
	private XenStoreDao xenStoreDao;
	@Autowired
	private NasFileSysDao nasFileSysDao;
	@Autowired
	private AreaResourcePoolRelationService areaResourcePoolRelationService;
	private static final String ROOT_ID = "1";

	private class XenDate {
		private List<HostUnitedVO> hosts;
		private List<VirtualMachineUnitedVO> vms;
		private List<DatastoreUnitedVO> srs;
		private Map<String, Object> dbMap;
		private Map<String, Object> srMap;
		private Map<String, Object> temMap;
		private Map<String, Object> temInterMap;
		private Map<String, Object> srInterMap;
	}

	public String xenDataCompare() {
		try {
			compareCluster();
			UnitedTreeObj treeObj = new UnitedTreeObj();
			treeObj.setVtype(UnitedConstant.XEN);
			treeObj.setType(UnitedConstant.CLUSTER);
			List<UnitedTreeObj> treeClus = unitedTreeService.queryForTreeList(treeObj);
			if (treeClus != null && treeClus.size() > 0) {
				for (UnitedTreeObj unitedTreeObj : treeClus) {
					XenDate xenDate = initXenDate(unitedTreeObj.getUuid());
					dataCompare(unitedTreeObj.getUuid(), xenDate);
				}
			}
		} catch (HttpClientException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void compareCluster() throws SQLException, HttpClientException {
		List<ClusterUnitedVO> interCluLst = null;// 接口中正确的数据
		String url = "conn/" + VirtualConstant.VT_XEN + "/getPools/";
		interCluLst = VirtualClient.get(url,
				new JacksonUtil.TypeReference<List<ClusterUnitedVO>>() {
				});
		UnitedTreeObj treeObj = new UnitedTreeObj();
		treeObj.setVtype(UnitedConstant.XEN);
		treeObj.setType(UnitedConstant.CLUSTER);
		List<UnitedTreeObj> treeClus = unitedTreeService.queryForTreeList(treeObj);// 数据库中的数据

		List<ClusterUnitedVO> interCluLst1 = new ArrayList<ClusterUnitedVO>();// 需要插入的数据
		List<UnitedTreeObj> deleteCluLst1 = new ArrayList<UnitedTreeObj>();// 需要删除的数据
		// 接口中数据和库中数据比较 找出缺少的数据
		for (ClusterUnitedVO clusterUnitedVO : interCluLst) {
			boolean match = false;
			for (UnitedTreeObj unitedTreeObj : treeClus) {
				if (clusterUnitedVO.getConnectCode().equals(unitedTreeObj.getConnect_id())) {
					match = true;
					break;
				}
			}
			if (!match) {
				// 需要插入的数据
				interCluLst1.add(clusterUnitedVO);
			}
		}

		/**
		 * 接口中存在但库中不存在的需要删除
		 */
		for (UnitedTreeObj unitedTreeObj : treeClus) {
			boolean match = false;
			for (ClusterUnitedVO clusterUnitedVO : interCluLst) {
				if (clusterUnitedVO.getConnectCode().equals(unitedTreeObj.getConnect_id())) {
					match = true;
					break;
				}
			}
			if (!match) {
				// 需要删除的数据
				deleteCluLst1.add(unitedTreeObj);
			}
		}

		for (ClusterUnitedVO clusterUnitedVO : interCluLst1) {
			UnitedTreeObj dcTreeObj = new UnitedTreeObj();
			 dcTreeObj.setVtype(UnitedConstant.XEN);
			 dcTreeObj.setType(UnitedConstant.DATACENTER);
			 List<UnitedTreeObj> treeDcLst =unitedTreeService.queryForTreeList(dcTreeObj);
			 if(treeDcLst!=null && treeDcLst.size()>0){
				 /**
				  * 待修改---shijc--目前只支持把xen同步到第一个手动创建的xen数据中心下面，目前不支持多个
				  */
				 dcTreeObj = treeDcLst.get(0);  
				 String parent_id = dcTreeObj.getId();
				// 根据安徽移动需要修改，需要将xen资源池挂到地域节点下，因与vmware结构保持一致，需要在相应的配置文件中配置数据中心和集群的对应关系
				// taoxue修改
//				String parent_id = getParentIdByConnectCode(clusterUnitedVO.getConnectCode(),pid);
				// 向树中和实体表中插入集群数据
				UnitedTreeObj cluTreeObj = new UnitedTreeObj();
				XenClusterObj clu = new XenClusterObj();
				if (clusterUnitedVO.getClusterName() == null
						|| "".equals(clusterUnitedVO.getClusterName())) {
					cluTreeObj.setName(clusterUnitedVO.getAddress());
					clu.setName(clusterUnitedVO.getAddress());
				} else {
					cluTreeObj.setName(clusterUnitedVO.getClusterName());
					clu.setName(clusterUnitedVO.getClusterName());
				}
				cluTreeObj.setUuid(clusterUnitedVO.getConnectCode());
				cluTreeObj.setParent_id(parent_id);
				cluTreeObj.setVtype(UnitedConstant.XEN);
				cluTreeObj.setType(UnitedConstant.CLUSTER);
				cluTreeObj.setConnect_id(clusterUnitedVO.getConnectCode());
				// ---------------------
				int cls_ID = xenClusterDao.getIdSequence();
				clu.setId(cls_ID);
				clu.setC_uuid(clusterUnitedVO.getConnectCode());
				clu.setType(UnitedConstant.CLUSTER);
				clu.setDrsstate("0");
				clu.setHastate("0");
				String clusterTree_id = unitedTreeDao.insertByObj(cluTreeObj);
				xenClusterDao.insertForConnection(clu);
			 }
		}
		// 库中数据和接口中数据比较 找出多余的数据
		for (UnitedTreeObj unitedTreeObj : deleteCluLst1) {
			// 删除库中多余的数据
			String connectCode = unitedTreeObj.getConnect_id();
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
	}

	/**
	 * 
	 * @Title: initXenDate
	 * @Description: 获取接口和数据库中的数据
	 * @param
	 * @return XenDate
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws HttpClientException
	 * @throws SQLException
	 * @createtime Aug 2, 2013 11:25:31 AM
	 */
	public XenDate initXenDate(String pool_uuid) throws HttpClientException, SQLException {
		XenDate xenDate = new XenDate();
		getInterfaceData(pool_uuid, xenDate);
		getDBMap(pool_uuid, xenDate);
		return xenDate;
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
	 * @throws HttpClientException
	 * @createtime May 16, 2013 5:07:56 PM
	 */
	private XenDate getInterfaceData(String poolUuid, XenDate xenDate) throws HttpClientException {
		/**
		 * 查询池链接下所有虚拟机的信息
		 */
		String getVmUrl = "conn/" + VirtualConstant.VT_XEN + "/getVms/" + poolUuid;
		ClusterUnitedVO vmsClu = VirtualClient.get(getVmUrl,
				new JacksonUtil.TypeReference<ClusterUnitedVO>() {
				});
		if (!vmsClu.isSuccess) {
			return null;
		} else {
			xenDate.vms = vmsClu.getVms();
		}
		/**
		 * 查询链接下所有主机的信息
		 */
		String getHostUrl = "conn/" + VirtualConstant.VT_XEN + "/gethosts/" + poolUuid;
		ClusterUnitedVO hostsClu = VirtualClient.get(getHostUrl,
				new JacksonUtil.TypeReference<ClusterUnitedVO>() {
				});
		if (!hostsClu.isSuccess) {
			return null;
		} else {
			xenDate.hosts = hostsClu.getHosts();
		}
		/**
		 * 查询链接下所有存储的信息
		 */
		String getDsUrl = "conn/" + VirtualConstant.VT_XEN + "/getDS/" + poolUuid;
		ClusterUnitedVO datastoresClu = VirtualClient.get(getDsUrl,
				new JacksonUtil.TypeReference<ClusterUnitedVO>() {
				});
		if (!datastoresClu.isSuccess) {
			return null;
		} else {
			xenDate.srs = datastoresClu.getDatastores();
		}
		return xenDate;
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
	 * @throws SQLException
	 * @createtime May 16, 2013 4:35:08 PM
	 */
	public XenDate getDBMap(String pool_uuid, XenDate xenDate) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		// HttpServletRequest request = Struts2Utils.getRequest();
		// TbSysUserinfoObj user = (TbSysUserinfoObj)
		// request.getSession().getAttribute(
		// Constant.USER_SESSION_KEY);
		//初始化虚拟机IP对应关系
		VmIpObj viObj = new VmIpObj();
		viObj.setVtype("2");
		nasFileSysDao.delVmIpRelationByObj(viObj);
		// 查询库中集群下的主机、虚拟机
		UnitedTreeObj treeObj = new UnitedTreeObj();
		treeObj.setVtype(UnitedConstant.XEN);
		treeObj.setConnect_id(pool_uuid);
		List<UnitedTreeObj> trees = unitedTreeService.queryForTreeList(treeObj);
		Map<String, Object> dbMap = new HashMap<String, Object>();
		for (UnitedTreeObj unitedTreeObj : trees) {
			if (unitedTreeObj.getUuid() == null || unitedTreeObj.getUuid().equals("null")) {
				deleteVmData(unitedTreeObj);
			} else {
				dbMap.put(unitedTreeObj.getUuid(), unitedTreeObj);
			}
		}
		xenDate.dbMap = dbMap;
		// 查询连接池下的存储
		XenStoreObj storeObj = new XenStoreObj();
		storeObj.setConnect_id(pool_uuid);
		List<XenStoreObj> stoLst = xenStoreService.queryForListByObj(storeObj);
		Map<String, Object> srMap = new HashMap<String, Object>();
		for (XenStoreObj xenStoreObj : stoLst) {
			srMap.put(xenStoreObj.getStore_uuid(), xenStoreObj);
		}

		xenDate.srMap = srMap;
		// 查询连接池下的模板
		TemManObj temManObj = new TemManObj();
		temManObj.setConnectId(pool_uuid);
		List<TemManObj> temLst = temManDao.queryForList(temManObj);
		Map<String, Object> temMap = new HashMap<String, Object>();
		for (TemManObj temManObj2 : temLst) {
			temMap.put(temManObj2.getTemplateCode(), temManObj2);
		}
		xenDate.temMap = temMap;
		return xenDate;
	}

	/**
	 * 
	 * 
	 * @Title: dataCompare
	 * @Description: 数据比较，更新所有数据，以接口为标准
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws SQLException
	 * @createtime May 16, 2013 4:35:37 PM
	 */
	public void dataCompare(String pool_uuid, XenDate xenDate) throws SQLException {
		Map<String, Object> intermap = new HashMap<String, Object>();
		UnitedTreeObj cluTreeObj = new UnitedTreeObj();
		cluTreeObj.setVtype(UnitedConstant.XEN);
		cluTreeObj.setUuid(pool_uuid);
		List<UnitedTreeObj> cluLst = unitedTreeService.queryForTreeList(cluTreeObj);
		if (cluLst != null && cluLst.size() > 0) {
			cluTreeObj = cluLst.get(0);
		}

		List<Host> tempHosts = AnalysisXenDataRelation.analysisHostAndVm(xenDate.hosts,
				xenDate.vms, xenDate.srs, pool_uuid);
		if (tempHosts.size() > 0) {
			// 接口中的数据和库中数据比较
			interfaceCompareDatastore(pool_uuid, xenDate, intermap, cluTreeObj, tempHosts);
			// 库中数据和接口中的数据比较
			datastoreCompareInterface(xenDate, intermap);
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
	 * @throws SQLException
	 * @createtime May 16, 2013 4:35:55 PM
	 */
	private void interfaceCompareDatastore(String pool_uuid, XenDate xenDate,
			Map<String, Object> intermap, UnitedTreeObj clusterObj, List<Host> tempHosts)
			throws SQLException {
		Map<String, Object> srInterMap = new HashMap<String, Object>();
		Map<String, Object> temInterMap = new HashMap<String, Object>();

		Map<String, Object> dbMap = xenDate.dbMap;
		for (Host host : tempHosts) {
			// 更新主机信息
			UnitedTreeObj enti = new UnitedTreeObj();
			enti.setUuid(host.getHostUuid());
			List<UnitedTreeObj> hostLst = unitedTreeService.queryForTreeList(enti);
			if (hostLst != null && hostLst.size() > 0) {
				enti = hostLst.get(0);
			} else {
				enti = new UnitedTreeObj();
			}
			String hostUuid = host.getHostUuid();
			if (!hostUuid.equals(pool_uuid)) {
				intermap.put(hostUuid, host);
				if (dbMap.get(hostUuid) == null) {
					if (!hostUuid.equals(pool_uuid)) {
						boolean isexist = confirmRepeatData(hostUuid);
						if (isexist) {
							// 更新主机的关系
							updateXenHostData(host, pool_uuid);
						} else {
							int host_id = hostInfoService.getIdSequence();
							enti = insertXenHostData(clusterObj, enti, host_id, host);
						}
					}
				} else {
					updateXenHostData(host, pool_uuid);
				}
			}

			// 更新池或主机下虚拟机信息
			List<Vm> tempVms = host.getVms();
			for (Vm vm : tempVms) {
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
				String parent_id = enti.getId();
				String parent_uuid = enti.getUuid();
				List<VirtualNicUnitedVO> list = new ArrayList<VirtualNicUnitedVO>();
				list = vm.getVirtualNicList();
				if(vm.getVirtualNicList()!=null&&vm.getVirtualNicList().size()>0){
					/*************保存多网卡信息*******************/
					for(VirtualNicUnitedVO vo :list){//入关系表
						VmIpObj viObj = new VmIpObj();
						viObj.setId(RandomUUID.getUuid());
						viObj.setVm_uuid(vm_uuid);
						viObj.setIp(vo.getIpAddress());
						viObj.setConnectid(parent_uuid);//待查
						viObj.setVtype("2");
						viObj.setIpType(adjustIpType(vo.getIpAddress()));
						nasFileSysDao.insertVmIpRelation(viObj);
					}
				}
				
				if (vm.getVmType().equals("IsVm")) {
					intermap.put(vm.getVmUuid(), vm);
					// 虚拟机进行比对
					if (dbMap.get(vm_uuid) == null) {
						boolean isexist = confirmRepeatData(vm_uuid);
						if (isexist) {
							updataXenVmData(pool_uuid, clusterObj, hostUuid, type, vmName, vm_uuid,
									cpuNum, memDynamicMax, virtualSrSize, vmIp, powerState, osName,
									vifNum, parent_id);
						} else {
							insertXenVmData(pool_uuid, clusterObj, enti, hostUuid, type, vmName,
									vm_uuid, cpuNum, memDynamicMax, virtualSrSize, vmIp,
									powerState, osName, vifNum, parent_id, parent_uuid);
						}
					} else {
						updataXenVmData(pool_uuid, clusterObj, hostUuid, type, vmName, vm_uuid,
								cpuNum, memDynamicMax, virtualSrSize, vmIp, powerState, osName,
								vifNum, parent_id);
					}
				} else if (vm.getVmType().equals("IsTemplate")) {
					temInterMap.put(vm_uuid, vm);
					// 模板
					Map<String, Object> temMap = xenDate.temMap;
					if (temMap.get(vm_uuid) == null) {
						boolean isexist = confirmNullTemData(vm_uuid);
						if (!isexist) {
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
							temObj.setConnectId(pool_uuid);
							temObj.setTemplateCode(vm.getVmUuid());
							temObj.setIsPhysical("0");
							temManDao.insertByObj(temObj);
						}
					}
				}

			}

			// 更新池或主机下的存储信息
			for (Sr sr : host.getSrs()) {
				srInterMap.put(sr.getSrUuid(), sr);
				// 获取基本信息
				String srName = sr.getSrName();
				String srUuid = sr.getSrUuid();
				String srType = sr.getSrType().toUpperCase();
				String srState = sr.getSrState();
				Boolean srShare = sr.isSrShared();
				long srSize = sr.getSrPhysicalSize();
				long useSize = sr.getSrPhysicalUtilisation();
				long purchase_space = sr.getVirtualSrSize();
				long freeSize = srSize - useSize;
				String srUrl = sr.getSrUrl();
				DecimalFormat format = new DecimalFormat("0");
				String usePer = "";
				if (useSize > 0 && srSize > 0) {
					usePer = format.format(useSize * 100 / srSize);
				} else {
					usePer = "0";
				}
				// 进行存储比对
				if (xenDate.srMap.get(sr.getSrUuid()) == null) {
					boolean isexist = confirmNullSrData(srUuid);
					if (isexist) {
						updataXenStoreData(pool_uuid, sr, srName, srUuid, srType, srState, srShare,
								srSize, freeSize,purchase_space, srUrl, usePer);
					} else {
						insertXenStoreData(pool_uuid, host, sr, srName, srUuid, srType, srState,
								srShare, srSize, freeSize,purchase_space, srUrl, usePer);
					}
				} else {
					updataXenStoreData(pool_uuid, sr, srName, srUuid, srType, srState, srShare,
							srSize, freeSize,purchase_space, srUrl, usePer);
				}
			}

		}
		xenDate.srInterMap = srInterMap;
		xenDate.temInterMap = temInterMap;
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
	private void updateXenHostData(Host host, String pool_uuid) throws SQLException {
		List<TbCloud2HostInfoObj> queryHostObj = new ArrayList<TbCloud2HostInfoObj>();
		TbCloud2HostInfoObj hObj = new TbCloud2HostInfoObj();
		hObj.setH_uuid(host.getHostUuid());
		queryHostObj = hostInfoService.queryForListByObj(hObj);
		if (queryHostObj == null || queryHostObj.size() == 0) {// 判断主机数据是否存在
			TbCloud2HostInfoObj insertHostObj = new TbCloud2HostInfoObj();
			int host_id = hostInfoService.getIdSequence();
			insertHostObj.setId(host_id);
			insertHostObj.setEq_id(host_id + "");
			insertHostObj.setEq_name(host.getHostName());
			insertHostObj.setH_uuid(host.getHostUuid());
			insertHostObj.setEq_type("5");// 表示机架
			insertHostObj.setHasvertual(HostSystemType.XEN);// 表示虚拟化类型
			insertHostObj.setCpu_fq(host.getHostCpuSpeed() + "");
			insertHostObj.setCpu_cl(host.getHostCpuNum() + "");
			insertHostObj.setCPU_DESC(host.getHostCpuDesc());
			insertHostObj.setEq_ip(host.getHostIp());
			insertHostObj.setMem(host.getMemSize() + "");
			insertHostObj.setNIC_NUM(2);// 暂时写死，需要从接口获取。----------------------------------------------
			insertHostObj.setEq_hostname(host.getHostName());

			insertHostObj.setMaxVcpu(host.getMaxHostSupportedVcpus());
			insertHostObj.setUsedCpu(host.getUsedCpuMHz());
			insertHostObj.setUsedMemMb(host.getUserMemMb());
			insertHostObj.setMaxCpu(host.getMaxCpuMHz());
			insertHostObj.setUsedVcpu(host.getUsedHostVcpus());
			insertHostObj.setAllocated(1);
			insertHostObj.setConnectId(pool_uuid);
			insertHostObj.setConnectStatus(HostSystemConnectStatus.connected);
			hostInfoService.insertByObj(insertHostObj);
		} else {
			// 若主机表中存在多条主机id相同的数据，则删除
			if (queryHostObj.size() > 1) {
				for (int i = 1; i < queryHostObj.size(); i++) {// 仅留下queryHostObj.get(0)
					deleteHostDataInHostInfo(queryHostObj.get(i));
				}
			}
			// 更新主机数据
			TbCloud2HostInfoObj tb = new TbCloud2HostInfoObj();
			tb.setEq_name(host.getHostName());
			tb.setEq_hostname(host.getHostName());
			tb.setH_uuid(host.getHostUuid());
			tb.setCpu_fq(host.getHostCpuSpeed() + "");
			tb.setCpu_cl(host.getHostCpuNum() + "");
			tb.setCPU_DESC(host.getHostCpuDesc());
			tb.setEq_ip(host.getHostIp());
			tb.setMem(host.getMemSize() + "");

			tb.setMaxVcpu(host.getMaxHostSupportedVcpus());
			tb.setUsedCpu(host.getUsedCpuMHz());
			tb.setUsedMemMb(host.getUserMemMb());
			tb.setMaxCpu(host.getMaxCpuMHz());
			tb.setUsedVcpu(host.getUsedHostVcpus());
			tb.setConnectStatus(HostSystemConnectStatus.connected);
			hostInfoService.updateUnitedDataByObj(tb);
		}

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
	 * @throws SQLException
	 * @createtime May 16, 2013 5:26:34 PM
	 */
	private UnitedTreeObj insertXenHostData(UnitedTreeObj clusterObj, UnitedTreeObj enti,
			int host_id, Host host) throws SQLException {
		// 向entity_tree中插入数据
		UnitedTreeObj hostTreeObj = new UnitedTreeObj();
		hostTreeObj.setConnect_id(clusterObj.getConnect_id());
		hostTreeObj.setName(host.getHostName());
		hostTreeObj.setParent_id(clusterObj.getId());
		hostTreeObj.setParent_uuid(clusterObj.getUuid());
		hostTreeObj.setType(UnitedConstant.HOST);
		hostTreeObj.setVtype(UnitedConstant.XEN);
		hostTreeObj.setUuid(host.getHostUuid());
		unitedTreeService.insertByObj(hostTreeObj);
		// 查询主机的id
		enti.setUuid(host.getHostUuid());
		enti = unitedTreeService.queryByObj(enti);
		TbCloud2HostInfoObj queryHostObj = new TbCloud2HostInfoObj();
		queryHostObj.setH_uuid(host.getHostUuid());
		queryHostObj = hostInfoService.queryByObj(queryHostObj);
		if (queryHostObj != null) {
			TbCloud2HostInfoObj delHostObj = new TbCloud2HostInfoObj();
			delHostObj.setH_uuid(host.getHostUuid());
			hostInfoService.deleteByObj(delHostObj);
		}
		// 向Host表中插入数据
		TbCloud2HostInfoObj tb = new TbCloud2HostInfoObj();
		tb.setId(host_id);
		tb.setEq_id(host_id + "");
		tb.setEq_name(host.getHostName());
		tb.setH_uuid(host.getHostUuid());
		tb.setEq_type("2");// 表示刀片
		tb.setHasvertual(HostSystemType.XEN);// 表示虚拟化类型
		tb.setCpu_fq(host.getHostCpuSpeed() + "");
		tb.setCpu_cl(host.getHostCpuNum() + "");
		tb.setCPU_DESC(host.getHostCpuDesc());
		tb.setEq_ip(host.getHostIp());
		tb.setMem(host.getMemSize() + "");
		tb.setNIC_NUM(2);// 暂时写死，需要从接口获取。----------------------------------------------
		tb.setEq_hostname(host.getHostName());

		tb.setMaxVcpu(host.getMaxHostSupportedVcpus());
		tb.setUsedCpu(host.getUsedCpuMHz());
		tb.setUsedMemMb(host.getUserMemMb());
		tb.setMaxCpu(host.getMaxCpuMHz());
		tb.setUsedVcpu(host.getUsedHostVcpus());
		tb.setAllocated(1);
		tb.setConnectStatus(HostSystemConnectStatus.connected);
		tb.setConnectId(clusterObj.getConnect_id());
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
	 * @throws SQLException
	 * @createtime May 16, 2013 5:25:14 PM
	 */
	private void insertXenVmData(String pool_uuid, UnitedTreeObj clusterObj, UnitedTreeObj enti,
			String hostUuid, String type, String vmName, String vm_uuid, long cpuNum,
			long memDynamicMax, long virtualSrSize, String vmIp, String powerState, String osName,
			long vifNum, String parent_id, String parent_uuid) throws SQLException {
		// 向entity_tree中插入数据
		UnitedTreeObj vmTreeObj = new UnitedTreeObj();
		vmTreeObj.setUuid(vm_uuid);
		vmTreeObj.setConnect_id(clusterObj.getConnect_id());
		vmTreeObj.setName(vmName);
		vmTreeObj.setParent_id(parent_id);
		vmTreeObj.setParent_uuid(parent_uuid);
		vmTreeObj.setType(UnitedConstant.VM);
		vmTreeObj.setVtype(UnitedConstant.XEN);
		unitedTreeService.insertByObj(vmTreeObj);
		int vm_id = vmHostService.queryVhostIdSequence();
		// 判断vmHost表中是否有重复数据
		VMHostObj queryVm = new VMHostObj();
		queryVm.setVH_UUID(vm_uuid);
		queryVm = vmHostService.queryByObj(queryVm);
		if (queryVm != null) {
			VMHostObj delVm = new VMHostObj();
			delVm.setVH_UUID(vm_uuid);
			vmHostService.deleteByObj(delVm);
		}
		// 向vmHost表中插入数据
		VMHostObj tempVm = new VMHostObj();
		tempVm.setVH_TYPE(VirtualMachineType.XEN);
		tempVm.setID(vm_id);
		tempVm.setVH_ID(String.valueOf(vm_id));
		tempVm.setVH_NAME(vmName);
		tempVm.setVH_UUID(vm_uuid);
		tempVm.setVH_CPU(cpuNum + "");
		tempVm.setVH_MEM(memDynamicMax + "");
		tempVm.setVH_STORAGE(virtualSrSize + "");
		tempVm.setVH_SYSTEM(osName);
		TbCloud2HostInfoObj tb = new TbCloud2HostInfoObj();
		tb.setHostUuids(hostUuid);
		tb = hostInfoService.queryByObj(tb);
		tempVm.setEQ_ID(tb.getEq_id() + "");
		tempVm.setVH_IP(vmIp);
		if (VirtualMachinePowerStatus.PAUSED.equalsIgnoreCase(powerState)) {
			tempVm.setVH_STAT("0");
		} else if (VirtualMachinePowerStatus.RUNNING.equalsIgnoreCase(powerState)) {
			tempVm.setVH_STAT("1");
		} else {
			// 暂时写成这样
			tempVm.setVH_STAT("2");
		}
		tempVm.setVH_NETWORK(vifNum + "");
		tempVm.setConnectId(pool_uuid);
		tempVm.setHostCode(hostUuid);
		tempVm.setConnectStatus(VirtualMachineConnectStatus.connected);
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
	 * @throws SQLException
	 * @createtime May 16, 2013 5:24:17 PM
	 */
	private void updataXenVmData(String pool_uuid, UnitedTreeObj clusterObj, String hostUuid,
			String type, String vmName, String vm_uuid, long cpuNum, long memDynamicMax,
			long virtualSrSize, String vmIp, String powerState, String osName, long vifNum,
			String parent_id) throws SQLException {
		// 更新entity_tree中数据
		UnitedTreeObj vmTreeObj = new UnitedTreeObj();
		vmTreeObj.setName(vmName);
		vmTreeObj.setUuid(vm_uuid);
		if (hostUuid.equals(pool_uuid)) {
			vmTreeObj.setParent_id(clusterObj.getId());
		} else {
			vmTreeObj.setParent_id(parent_id);
		}
		unitedTreeService.updateByObj(vmTreeObj);
		// 更新vmHost表中数据
		VMHostObj queryVMObj = new VMHostObj();
		queryVMObj.setVH_UUID(vm_uuid);
		queryVMObj = vmHostService.queryByObj(queryVMObj);
		if (queryVMObj == null) {
			// 向entity_tree中插入数据
			VMHostObj tempVm = new VMHostObj();
			tempVm.setVH_TYPE(VirtualMachineType.XEN);
			int vm_id = vmHostService.queryVhostIdSequence();
			// 向vmHost表中插入数据
			tempVm.setID(vm_id);
			tempVm.setVH_ID(String.valueOf(vm_id));
			tempVm.setVH_NAME(vmName);
			tempVm.setVH_UUID(vm_uuid);
			tempVm.setVH_CPU(cpuNum + "");
			tempVm.setVH_MEM(memDynamicMax + "");
			tempVm.setVH_STORAGE(virtualSrSize + "");
			tempVm.setVH_SYSTEM(osName);
			tempVm.setConnectId(pool_uuid);
			TbCloud2HostInfoObj tb = new TbCloud2HostInfoObj();
			tb.setHostUuids(hostUuid);
			tb = hostInfoService.queryByObj(tb);
			tempVm.setEQ_ID(tb.getEq_id() + "");
			tempVm.setVH_IP(vmIp);
			tempVm.setHostCode(hostUuid);
			if (XenTypes.VmPowerState.HALTED.toString().equals(powerState)) {
				tempVm.setVH_STAT("0");
			} else if (XenTypes.VmPowerState.RUNNING.toString().equals(powerState)) {
				tempVm.setVH_STAT("1");
			} else {
				// 暂时写成这样
				tempVm.setVH_STAT("2");
			}
			tempVm.setVH_NETWORK(vifNum + "");
			tempVm.setConnectStatus(VirtualMachineConnectStatus.connected);
			vmHostService.insertByVMhostObj(tempVm);
		} else {
			VMHostObj tempVm = new VMHostObj();
			tempVm.setConnectId(pool_uuid);
			tempVm.setVH_TYPE("3");
			tempVm.setVH_NAME(vmName);
			tempVm.setVH_UUID(vm_uuid);
			tempVm.setVH_CPU(cpuNum + "");
			tempVm.setVH_MEM(memDynamicMax + "");
			tempVm.setVH_STORAGE(virtualSrSize + "");
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
			if (XenTypes.VmPowerState.HALTED.toString().equals(powerState)) {
				tempVm.setVH_STAT("0");
			} else if (XenTypes.VmPowerState.RUNNING.toString().equals(powerState)) {
				tempVm.setVH_STAT("1");
			} else {
				// 暂时写成这样
				tempVm.setVH_STAT("2");
			}
			tempVm.setVH_NETWORK(vifNum + "");
			tempVm.setHostCode(hostUuid);
			tempVm.setConnectStatus(VirtualMachineConnectStatus.connected);
			vmHostService.updateVMHostInfo(tempVm);
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
	 * @throws SQLException
	 * @createtime May 16, 2013 5:18:05 PM
	 */
	private void datastoreCompareInterface(XenDate xenDate, Map<String, Object> intermap)
			throws SQLException {
		// 比对树上数据
		Map<String, Object> dbMap = xenDate.dbMap;
		Iterator<Entry<String, Object>> dbIterator = dbMap.entrySet().iterator();
		while (dbIterator.hasNext()) {
			Entry<String, Object> entry = dbIterator.next();
			String key = entry.getKey();
			if (intermap.get(key) == null) {
				UnitedTreeObj obj = (UnitedTreeObj) entry.getValue();
				if (obj.getType().equals(UnitedConstant.HOST)) {
					logger.info("接口中不存在!---" + obj.getName());
					deleteHostData(obj);
				} else if (obj.getType().equals(UnitedConstant.VM)) {
					logger.info("接口中不存在!---" + obj.getName());
					// 删除数据
					deleteVmData(obj);
				}
			}
		}
		// 比对模板数据
		Map<String, Object> temMap = xenDate.temMap;
		Iterator<Entry<String, Object>> temIterator = temMap.entrySet().iterator();
		while (temIterator.hasNext()) {
			Entry<String, Object> entry = temIterator.next();
			String key = entry.getKey();
			if (xenDate.temInterMap.get(key) == null) {
				TemManObj obj = (TemManObj) entry.getValue();
				logger.info("接口中不存在!---" + obj.getName());
				temManDao.deleteByObj(obj);
			}
		}
		// 比对存储数据
		Map<String, Object> srMap = xenDate.srMap;
		Iterator<Entry<String, Object>> srIterator = srMap.entrySet().iterator();
		while (srIterator.hasNext()) {
			Entry<String, Object> entry = srIterator.next();
			String key = entry.getKey();
			if (xenDate.srInterMap.get(key) == null) {
				XenStoreObj obj = (XenStoreObj) entry.getValue();
				logger.info("接口中不存在!---" + obj.getName());
				deleteStoreData(obj);
			}
		}
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
	 * @throws SQLException
	 * @createtime May 16, 2013 5:15:40 PM
	 */
	private void deleteVmData(UnitedTreeObj obj) throws SQLException {
		// vmhost_info
		VMHostObj vmObj = new VMHostObj();
		vmObj.setVH_UUID(obj.getUuid());
		vmHostService.deleteByObj(vmObj);
		// entity_tree
		unitedTreeService.deleteByObj(obj);
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
	 * @throws SQLException
	 * @createtime May 16, 2013 5:13:51 PM
	 */
	private void deleteHostData(UnitedTreeObj obj) throws SQLException {
		// 删除数据host_info
		TbCloud2HostInfoObj HostObj = new TbCloud2HostInfoObj();
		String hostUuidDel = obj.getUuid();
		HostObj.setH_uuid(hostUuidDel);
		hostInfoService.deleteByObj(HostObj);
		// entity_tree
		unitedTreeService.deleteByObj(obj);
	}

	/**
	 * 
	 * @Title: deleteHostData
	 * @Description: 删除主机表内重复数据
	 * @param
	 * @return void
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2013-10-28 10:13:51 PM
	 */
	private void deleteHostDataInHostInfo(TbCloud2HostInfoObj obj) throws SQLException {
		// 删除数据host_info
		hostInfoService.deleteByObj(obj);
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
	 * @throws SQLException
	 * @createtime May 16, 2013 1:09:58 PM
	 */
	private boolean confirmRepeatData(String Uuid) throws SQLException {
		UnitedTreeObj treeObj = new UnitedTreeObj();
		treeObj.setUuid(Uuid);
		List<UnitedTreeObj> trees = unitedTreeService.queryForTreeList(treeObj);
		if (trees != null && trees.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @Title: compareSr
	 * @Description: 对存储进行比对
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Aug 2, 2013 5:49:03 PM
	 */
	public void compareSr(List<Sr> interfaceSrs, String pool_uuid) {

	}

	/**
	 * 
	 * @Title: confirmSrRepeatData
	 * @Description: 确认存储是否存在
	 * @param
	 * @return Boolean
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Aug 3, 2013 11:47:19 AM
	 */
	public Boolean confirmNullSrData(String srUuid) {
		XenStoreObj storeObj = new XenStoreObj();
		storeObj.setStore_uuid(srUuid);
		storeObj = xenStoreService.queryByObj(storeObj);
		if (storeObj == null) {
			return false;
		} else {
			return true;
		}
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
	private void insertXenStoreData(String pool_uuid, Host host, Sr sr, String srName,
			String srUuid, String srType, String srState, Boolean srShare, long srSize,
			long freeSize, long purchase_space,String srUrl, String usePer) {
		// 向datestore_info表中插入数据
		int sr_id = xenStoreService.querySRIdSequence();
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
		storeObj.setConnect_id(pool_uuid);
		storeObj.setPurchase_space(purchase_space+"");
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
	private void updataXenStoreData(String pool_uuid, Sr sr, String srName, String srUuid,
			String srType, String srState, Boolean srShare, long srSize, long freeSize,long purchase_space,
			String srUrl, String usePer) {
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
		storeObj.setPurchase_space(purchase_space+"");
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
	 * @Title: deleteStoreData
	 * @Description: 删除存储相关数据
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 16, 2013 5:16:41 PM
	 */
	private void deleteStoreData(XenStoreObj obj) {
		// storage_info
		XenStoreObj storeObj = new XenStoreObj();
		storeObj.setStore_uuid(obj.getStore_uuid());
		xenStoreService.deleteByObj(storeObj);
	}

	/**
	 * 
	 * @Title: confirmNullTemData
	 * @Description: 确认存储数据是否存在数据库中
	 * @param
	 * @return Boolean
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Aug 3, 2013 3:23:20 PM
	 */
	public Boolean confirmNullTemData(String tem_uuid) {
		TemManObj temManObj = new TemManObj();
		temManObj.setTemplateCode(tem_uuid);
		List temLst = temManDao.queryForList(temManObj);
		if (temLst != null && temLst.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @Title: getParentIdByConnectCode
	 * @Description: 通过链接ID获取对应的父节点
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-11-22 上午9:54:43
	 */
	public String getParentIdByConnectCode(String connectCode,String pid) {
		String areaId = "";
		// 对于xen来说areaId就是数据中心id
		try {
			areaId = areaResourcePoolRelationService.getAreaId(connectCode,pid);
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
	 * @Title: adjustIpType
	 * @Description: 判断IP类型
	 * @param
	 * @return void
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014年5月21日10:59:01
	 */
	private String adjustIpType(String ip){
		Pattern privateIp = Pattern.compile("^"+PropertiesUtil.getString("properties.IP", "private")+"$");
		Pattern publicIp = Pattern.compile("^"+PropertiesUtil.getString("properties.IP", "public")+"$");
		Matcher m1 = privateIp.matcher(ip);
		Matcher m2 = publicIp.matcher(ip);
		if(m1.find()){//私网IP地址
			return "1";
		}else if(m2.find()){//外网IP地址
			return "2";
		}else{//如有需要可增加
			return "0";
		}	
	}
}
