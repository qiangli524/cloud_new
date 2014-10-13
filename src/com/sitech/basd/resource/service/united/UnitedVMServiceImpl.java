package com.sitech.basd.resource.service.united;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelSftp;
import com.sitech.basd.busimanager.dao.busitree.BusiManagerTreeDao;
import com.sitech.basd.busimanager.domain.busitree.BusiManagerTree;
import com.sitech.basd.exception.CloudUnitedException;
import com.sitech.basd.exception.ConsoleException;
import com.sitech.basd.resource.dao.template.TemManDao;
import com.sitech.basd.resource.dao.template.TemVmRelationDao;
import com.sitech.basd.resource.dao.united.UnitedTreeDao;
import com.sitech.basd.resource.dao.united.vm.VmDatastoreRelationDao;
import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.basd.resource.domain.template.TemVmRelationObj;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.domain.united.vm.VmDatastoreRelationObj;
import com.sitech.basd.resource.util.RPCClient;
import com.sitech.basd.resource.util.RevertEntity;
import com.sitech.basd.resource.util.VirtualClient;
import com.sitech.basd.resource.util.VirtualMachineType;
import com.sitech.basd.sxcloud.cloud.dao.globalconfig.TbGlobalConfigDao;
import com.sitech.basd.sxcloud.cloud.dao.net.TbIpDao;
import com.sitech.basd.sxcloud.cloud.dao.net.TbNetDao;
import com.sitech.basd.sxcloud.cloud.dao.resource.HostInfoDao;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.yicloud.domain.vmauthority.VmAuthorityObj;
import com.sitech.basd.yicloud.service.vmauthority.VmAuthorityService;
import com.sitech.console.dao.VncPortDao;
import com.sitech.console.domain.TbVncPortVO;
import com.sitech.ssd.ah.nas.dao.NasFileSysDao;
import com.sitech.ssd.ah.nas.domain.VmIpObj;
import com.sitech.utils.encrypt.DES3;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.exception.SSHException;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.utils.sftp.SftpUtil;
import com.sitech.utils.ssh.SSHUtil;
import com.sitech.utils.ssh.SshConnection;
import com.sitech.vo.enumtype.Types;
import com.sitech.vo.united.DatastoreUnitedVO;
import com.sitech.vo.united.HostUnitedVO;
import com.sitech.vo.united.NetworkUnitedVO;
import com.sitech.vo.united.RenameVO;
import com.sitech.vo.united.SnapshotUnitedVO;
import com.sitech.vo.united.VirtualDiskUnitedVO;
import com.sitech.vo.united.VirtualMachineConsole;
import com.sitech.vo.united.VirtualMachineDiskOper;
import com.sitech.vo.united.VirtualMachinePowerStateUnitedVO;
import com.sitech.vo.united.VirtualMachinePowerStatus;
import com.sitech.vo.united.VirtualMachineUnitedVO;
import com.sitech.vo.united.VirtualMachineVNCConfig;
import com.sitech.vo.united.VirtualNicUnitedVO;
import com.sitech.vo.util.UnitedConstant;
import com.sitech.vo.util.UnitedRestURI;
import com.sitech.vo.util.VMwareEntityType;
import com.sitech.vo.util.VirtualConstant;

@Service("unitedVMService")
public class UnitedVMServiceImpl implements UnitedVMService {
	private static final Logger LOG = LoggerFactory.getLogger(UnitedVMService.class);
	@Autowired
	private UnitedTreeDao unitedTreeDao;
	@Autowired
	private VMHostDao vmHostDao;
	@Autowired
	private VMwareDataCompareService vmwareDataCompareService;
	@Autowired
	private TemVmRelationDao temVmRelationDao;
	@Autowired
	private TbNetDao tbNetDao;
	@Autowired
	private TbIpDao tbIpDao;
	@Autowired
	private VmAuthorityService vmAuthorityService;
	@Autowired
	private RabbitMQUtil rabbitmqUtil;
	@Autowired
	private RPCClient rpcClient;
	@Autowired
	private TemManDao temManDao;
	@Autowired
	private BusiManagerTreeDao busiDao;
	@Autowired
	private NasFileSysDao nasFileSysDao;
	@Autowired
	private TbGlobalConfigDao tbGlobalConfigDao;
	@Autowired
	private VncPortDao vncPortDao;
	@Autowired
	private HostInfoService hostInfoService;
	@Autowired
	private VmDatastoreRelationDao vmDatastoreRelationDao;
	// @Autowired
	// private WorkOrderDao workOrderDao;
	@Autowired
	private HostInfoDao hostInfoDao;

	// @Autowired
	// private DataStoreDao dataStoreDao;
	// @Autowired
	// private XenStoreDao xenStoreDao;

	/**
	 * 
	 * @Title: putVMPowerState
	 * @Description: 修改虚拟机电源状态
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime Jul 27, 2013 5:19:43 PM
	 */
	public String putVMPowerState(UnitedTreeObj obj) throws HttpClientException {
		String result = UnitedConstant.FAIL;
		String vtype = obj.getVtype();
		String url = "";
		VirtualMachinePowerStateUnitedVO vo = new VirtualMachinePowerStateUnitedVO();
		String state = obj.getOper();
		vo = RevertEntity.toRestVMPowerState(obj);
		vo.setVirtualMachinePowerOper(state);
		if (UnitedConstant.VMWARE.equals(vtype)) {
			if ("resume".equals(state)) {
				vo.setVirtualMachinePowerOper("powerOn");
			}
			url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/change/powerstate";
		} else if (UnitedConstant.XEN.equals(vtype)) {
			url = "virtualmachine/" + VirtualConstant.VT_XEN + "/change/powerstate";
		}
		// 请求接口
		vo = VirtualClient.put(url, vo,
				new JacksonUtil.TypeReference<VirtualMachinePowerStateUnitedVO>() {
				});
		if (!vo.getIsSuccess()) {
			result = vo.getLog();
		} else {
			result = UnitedConstant.SUCCESS;
			// 更新库中信息
			VMHostObj vmObj = new VMHostObj();
			vmObj.setVH_UUID(obj.getUuid());
			if (state.equals("powerOn")) {
				vmObj.setVH_STAT("1");
			} else if (state.equals("powerOff")) {
				vmObj.setVH_STAT("0");
			} else {
				vmObj.setVH_STAT("2");
			}
			vmHostDao.updateVmStateByObj(vmObj);
		}
		return result;
	}

	/**
	 * 
	 * @Title: putVMPowerState_BD
	 * @Description: 修改虚拟机电源状态(添加重启)-北京电信
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-2-15 下午2:41:44
	 */
	public String putVMPowerState_BD(UnitedTreeObj obj, HttpServletRequest request)
			throws HttpClientException {
		String result = UnitedConstant.FAIL;
		String vtype = obj.getVtype();
		String url = "";
		VirtualMachinePowerStateUnitedVO vo = new VirtualMachinePowerStateUnitedVO();
		vo = RevertEntity.toRestVMPowerState(obj);
		String state = request.getParameter("state");
		String vh_stat = request.getParameter("vh_stat");
		if (UnitedConstant.VMWARE.equals(vtype)) {
			url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/change/powerstate";
		} else if (UnitedConstant.XEN.equals(vtype)) {
			url = "virtualmachine/" + VirtualConstant.VT_XEN + "/change/powerstate";
		}
		if (state.equals("restart")) {// 重启
			if (vh_stat.equals("1")) {// 1是开机
				VirtualMachinePowerStateUnitedVO offVo = new VirtualMachinePowerStateUnitedVO();
				offVo = RevertEntity.toRestVMPowerState(obj);
				offVo.setVirtualMachinePowerOper("powerOff");
				// 关机
				offVo = VirtualClient.put(url, offVo,
						new JacksonUtil.TypeReference<VirtualMachinePowerStateUnitedVO>() {
						});
			}
			VirtualMachinePowerStateUnitedVO onVo = new VirtualMachinePowerStateUnitedVO();
			onVo = RevertEntity.toRestVMPowerState(obj);
			onVo.setVirtualMachinePowerOper("powerOn");
			// 开机
			onVo = VirtualClient.put(url, onVo,
					new JacksonUtil.TypeReference<VirtualMachinePowerStateUnitedVO>() {
					});
			vo.setIsSuccess(onVo.getIsSuccess());
		} else {
			vo.setVirtualMachinePowerOper(state);
			// 修改虚拟机状态
			vo = VirtualClient.put(url, vo,
					new JacksonUtil.TypeReference<VirtualMachinePowerStateUnitedVO>() {
					});
		}
		if (!vo.getIsSuccess()) {
			result = vo.getLog();
		} else {
			result = UnitedConstant.SUCCESS;
			// 更新库中信息
			VMHostObj vmObj = new VMHostObj();
			vmObj.setVH_UUID(obj.getUuid());
			if (state.equals("powerOn")) {
				vmObj.setVH_STAT("1");
			} else if (state.equals("powerOff")) {
				vmObj.setVH_STAT("0");
			} else {
				if (state.equals("restart")) {
					vmObj.setVH_STAT("1");
				} else {
					vmObj.setVH_STAT("2");
				}
			}
			vmHostDao.updateVmStateByObj(vmObj);
		}
		return result;
	}

	/**
	 * 
	 * @Title: deleteVM
	 * @Description: 删除虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime Jul 29, 2013 10:12:11 AM
	 */
	public String deleteVM(UnitedTreeObj obj) throws HttpClientException, SQLException {
		String result = UnitedConstant.FAIL;
		String url = "";
		String vtype = obj.getVtype();
		VirtualMachineUnitedVO vo = new VirtualMachineUnitedVO();
		if (UnitedConstant.VMWARE.equals(vtype)) {
			url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/destory/" + obj.getConnect_id()
					+ "/" + obj.getUuid();
		} else if (UnitedConstant.XEN.equals(vtype)) {
			url = "virtualmachine/" + VirtualConstant.VT_XEN + "/destory/" + obj.getConnect_id()
					+ "/" + obj.getUuid();
		}
		vo = VirtualClient.delete(url, new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
		});
		if (vo.getIsSuccess()) {
			// 删除虚拟机和模板之间的关系
			TemVmRelationObj relation = new TemVmRelationObj();
			relation.setConnectCode(obj.getConnect_id());
			relation.setVmCode(obj.getUuid());
			temVmRelationDao.deleteByObj(relation);
			result = UnitedConstant.SUCCESS;

			unitedTreeDao.deleteByObj(obj);
			// 删除虚拟机后回收IP地址
			VmIpObj vmIpObj = new VmIpObj();
			vmIpObj.setConnectid(obj.getConnect_id());
			vmIpObj.setVm_uuid(obj.getUuid());
			List<VmIpObj> ipList = nasFileSysDao.queryVmIpRelation(vmIpObj);
			if (ipList != null && ipList.size() > 0) {
				for (VmIpObj ipObj : ipList) {
					String ip = ipObj.getIp();
					if (ip != null && !"".equals(ip)) {
						TbCloud2IpInfoObj ipobj = new TbCloud2IpInfoObj();
						ipobj.setIPADDRESS(ip);
						ipobj.setISUSED("0");
						tbIpDao.updateIPByObj(ipobj);
					}
				}
			}
			/*
			 * 删除虚拟机表及关联关系
			 */
			VMHostObj vm = new VMHostObj();
			vm.setVH_UUID(obj.getUuid());
			vm.setConnectId(obj.getConnect_id());
			// 删除虚拟机后删除业务中心中的对应虚拟机
			BusiManagerTree bObj = new BusiManagerTree();
			bObj.setEntity_id(vm.getVH_UUID());
			bObj.setConnect_id(vm.getConnectId());
			busiDao.deleteBusiManagerTreeById(bObj);
			/*
			 * 释放Vnc端口
			 */
			String hostCode = vmHostDao.queryByObj(vm).getHostCode();
			TbVncPortVO portVO = new TbVncPortVO();
			portVO.setConnect_id(obj.getConnect_id());
			portVO.setHost_code(hostCode);
			portVO.setVmcode(vm.getVH_UUID());
			List<TbVncPortVO> vncPortList = vncPortDao.queryForListByObj(portVO);
			if (vncPortList != null && vncPortList.size() > 0) {
				TbVncPortVO port = vncPortList.get(0);
				releaseVncPostByVm(port.getConnect_id(), port.getHost_code(),
						Integer.parseInt(port.getPort()));
				// 删除VNC配置文件
				deleteVmVncConfig(obj.getConnect_id(), vm.getVH_UUID());
			}
			vmHostDao.deleteByObj(vm);
			// 删除虚拟机与数据存储关联关系
			deleteVmDatastoreRelation(obj.getConnect_id(), obj.getUuid(), vtype);
		} else {
			result = vo.getLog();
		}
		return result;
	}

	/**
	 * 
	 * @Title: createVMByTem
	 * @Description: 閫氳繃妯℃澘鍒涘缓铏氭嫙鏈�
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 30, 2013 8:16:45 PM
	 */
	public String createVMByTem(UnitedTreeObj obj, VirtualMachineUnitedVO vo,
			HttpServletRequest request) throws Exception {
		String result = UnitedConstant.FAIL;
		String vtype = obj.getVtype();
		int userid = obj.getUser_id();
		obj.setName(vo.getNewVmName());
		obj.setType(UnitedConstant.VM);
		String url = "";
		/*
		 * 修改为支持多网卡
		 */
		String netWay = request.getParameter("netWay");
		String[] netWays = filterStringSplit(netWay);
		String net_id = request.getParameter("net_id");
		String ips = request.getParameter("ips");
		String[] ipArray = filterStringSplit(ips);
		String memUnit = request.getParameter("memUnit");
		if (UnitedConstant.VMWARE.equals(vtype)) {
			vo = initVmwareVmNicInfo(obj, vo, netWays, net_id, ipArray, memUnit);
			vo.setHostCode(obj.getParent_uuid());
		}else if(UnitedConstant.XEN.equals(vtype)){
			vo = initXenServerVmNicInfo(obj, vo, netWays, net_id, ipArray, memUnit);
			vo.setHostCode(obj.getParent_uuid());
		}

		// 模板克隆时为调整虚拟机磁盘为编辑模式By huojla
		vo.setVmDiskOper(VirtualMachineDiskOper.EDIT);
		// 设置操作类型
		vo.setCreateType(Types.createType.TEMPLATE_TO_VM);
		vo.setStorageSizeInMb(vo.getStorageSizeInMb() * 1024);// /由于在页面上的存储的单位是G，所以此处进行一次转化
		TemVmRelationObj relation = new TemVmRelationObj();
		relation.setTemCode(vo.getVmCode());
		if (UnitedConstant.VMWARE.equals(vtype)) {
			String dataCenterCode = getDataCenterCode(vo.getHostCode(), vo.getConnectCode());
			vo.setDataCenterCode(dataCenterCode);// 涉及多个数据中心时，相应的要区分不同数据中心下的vlan
			/*
			 * 获取可用VNC端口，暂时不支持集群上创建资源
			 */
			int port = getHostFreePort(vo.getConnectCode(), vo.getHostCode());
			VirtualMachineVNCConfig vncConfig = new VirtualMachineVNCConfig();
			vncConfig.setIfVncOpen(true);
			vncConfig.setVncPort(port);
			vo.setVncConfig(vncConfig);
			url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/clone";
			VirtualMachineUnitedVO vir = new VirtualMachineUnitedVO();
			try {
				vir = VirtualClient.post(url, vo,
						new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
						});
			} catch (Exception e) {
				vir.setIsSuccess(false);
			}
			if (vir.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				// 建立虚拟机和模板之间的关系
				relation.setConnectCode(obj.getConnect_id());
				relation.setVmCode(vir.getVmCode());
				temVmRelationDao.insertByObj(relation);
				// 插入树表(插入前查看是否存在，和定时同步冲突 update by hehui)
				Boolean isExist = vmwareDataCompareService
						.queryTbCloud2VMHostInfoByVirtualMachineUnitedVO(vir);
				if (isExist) {
					String eq_id = vmwareDataCompareService.getTbCloud2HostId(vir);
					vmwareDataCompareService.updateTbCloud2VMHostInfo(vir, eq_id);
					obj.setUuid(vir.getVmCode());
					unitedTreeDao.updateByObj(obj);
				} else {
					obj.setUuid(vir.getVmCode());
					unitedTreeDao.insertByObj(obj);
					String eq_id = vmwareDataCompareService.getTbCloud2HostId(vir);
					vir.setVirtualNicList(vo.getVirtualNicList());
					vmwareDataCompareService.saveTbCloud2VMHostInfo(vir, eq_id);
					// 非admin用户，插入entityuser
					if (userid != 1) {
						VmAuthorityObj authorObj = new VmAuthorityObj();
						authorObj.setUSERID(userid);
						authorObj.setENTITY_NAME(obj.getName());
						authorObj.setENTITY_TYPE("4");// 虚拟机的资源类型为4
						authorObj.setENTITY_ID(obj.getUuid());
						authorObj.setOPERAUTHORITY("0");// 给与创建虚拟机人所有的操作权限
						authorObj.setTYPE(vtype);
						authorObj.setCONNECT_ID(obj.getConnect_id());
						vmAuthorityService.insertByObj(authorObj);
					}
				}

				/*
				 * 处理虚拟机VNC服务配置文件@huojla@20140730
				 */
				dealVmVncConfig(vir.getConnectCode(), vir.getHostCode(), vir.getVmCode(), port);
				updateVncPostByVm(vir.getConnectCode(), vir.getHostCode(), vir.getVmCode(), port);
				/*
				 * 处理虚拟机与数据存储关系
				 */
				dealVmDatastoreRelation(vir, vtype);
			} else {
				// 创建虚拟机失败时需要将相应的IP地址释放
				freeVMIpaddress(vo);
				result = vir.getLog();
				// 抛出异常，交由Action处理
				throw new CloudUnitedException(vir.getLog());
			}
		} else if (UnitedConstant.XEN.equals(vtype)) {

			// xen 首先是否存在mac地址能够添加虚拟机
			// if (UnitedConstant.XEN.equals(vtype)) {
			// // 获取mac地址
			// String cmd = "get-leisure-ip";
			// String response = null;
			// String mac = null;
			// String exist = null;
			// // RPCClient rpcClient = new RPCClient(rabbitmqUtil);
			// response = rpcClient.call(cmd, "");
			// JSONObject json = JacksonUtil.fromJSON(response,
			// new JacksonUtil.TypeReference<JSONObject>() {
			// });
			// exist = json.get("exist").toString();
			// if (exist.equals("true")) {
			// mac = json.get("mac").toString();
			// ipMac = json.get("ip").toString();
			// } else {
			// throw new RuntimeException("没有空余mac地址！");
			// }
			//
			// if (mac != null) {
			// vo.setMac(mac);
			// }
			// }
			vo.setCpuMax(vo.getNumCPUs());
			vo.setMemDynamicMax(vo.getMemoryMB().longValue());
			vo.setMemDynamicMin(vo.getMemoryMB().longValue());
			vo.setMemStaticMax(vo.getMemoryMB().longValue());
			vo.setMemStaticMin(vo.getMemoryMB().longValue());
			url = "virtualmachine/" + VirtualConstant.VT_XEN + "/clone";
			VirtualMachineUnitedVO reVo = VirtualClient.post(url, vo,
					new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
					});
			if (reVo.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				// 建立虚拟机和模板之间的关系
				relation.setConnectCode(obj.getConnect_id());
				relation.setVmCode(vo.getVmCode());
				temVmRelationDao.insertByObj(relation);
				// 插入树表
				obj.setUuid(reVo.getVmCode());
				unitedTreeDao.insertByObj(obj);
				String eq_id = vmwareDataCompareService.getTbCloud2HostId(vo);
				VMHostObj vmhost = new VMHostObj();
				int vm_id = vmHostDao.queryVhostIdSequence();
				vmhost.setID(vm_id);
				vmhost.setVH_ID(vm_id + "");
				vmhost.setEQ_ID(eq_id);
				vmhost.setVH_NAME(reVo.getVmName());
				vmhost.setVH_UUID(reVo.getVmCode());
				vmhost.setVH_CPU(reVo.getNumCPUs() + "");
				vmhost.setVH_MEM(reVo.getMemStaticMax() + "");
				Double storageSizeMb = reVo.getStorageSizeInMb();
				DecimalFormat dcFormat = new DecimalFormat("0.0");
				String fmStorageSizeMb = dcFormat.format(storageSizeMb);
				vmhost.setVH_STORAGE(fmStorageSizeMb);
				vmhost.setVH_SYSTEM(reVo.getOperationSystemName());
				vmhost.setVH_IP(reVo.getIp());
				vmhost.setConnectId(obj.getConnect_id());
				String powerState = reVo.getPowerState();
				if (VirtualMachinePowerStatus.powerOff.equalsIgnoreCase(powerState)) {
					vmhost.setVH_STAT("0");
				} else if (VirtualMachinePowerStatus.powerOn.equalsIgnoreCase(powerState)) {
					vmhost.setVH_STAT("1");
				} else {
					// 暂时写成这样
					vmhost.setVH_STAT("2");
				}
				vmhost.setVH_TYPE(VirtualMachineType.XEN);
				vmhost.setVH_NETWORK(reVo.getVifNum() + "");
				vmhost.setHostCode(obj.getParent_uuid());
				vmHostDao.insertByVMhostObj(vmhost);
				// 非admin用户，插入entityuser
				if (userid != 1) {
					VmAuthorityObj authorObj = new VmAuthorityObj();
					authorObj.setUSERID(userid);
					authorObj.setENTITY_NAME(obj.getName());
					authorObj.setENTITY_TYPE("4");// 虚拟机的资源类型为4
					authorObj.setENTITY_ID(obj.getUuid());
					authorObj.setOPERAUTHORITY("0");// 给与创建虚拟机人所有的操作权限
					authorObj.setTYPE(vtype);
					authorObj.setCONNECT_ID(obj.getConnect_id());
					vmAuthorityService.insertByObj(authorObj);
				}
			} else {
				result = vo.getLog();
			}
			// 判断虚拟机电源状态
			// ScriptUnitedVO scriptUnitedVO = new ScriptUnitedVO();
			// scriptUnitedVO.setPoolUuid(vo.getConnectCode());
			// scriptUnitedVO.setVmUuid(reVo.getVmCode());
			// scriptUnitedVO.setIp(vo.getIp());
			// scriptUnitedVO.setOriginalIp(ipMac);
			// scriptUnitedVO.setEthName("eth0");
			// /*************
			// * 待修改，先写死******************* -----------需要从netINfo表里取
			// **/
			// scriptUnitedVO.setGateway("172.21.3.1");
			// scriptUnitedVO.setNetmask("255.255.255.0");
			// scriptUnitedVO.setPassword("111111");
			// String url1 = "virtualmachine/" + VirtualConstant.VT_XEN +
			// "/modify/ip";
			// int i = 0;
			// while (i < 12) {
			// Thread.sleep(5000);
			// if (SshPingIp.pingOtherServer(ipMac)) {
			// try {
			// // 调用修改Ip的接口
			// ScriptUnitedVO sVo = VirtualClient.put(url1, scriptUnitedVO,
			// new JacksonUtil.TypeReference<ScriptUnitedVO>() {
			// });
			// if (sVo.isSuccess) {// 假定一定会成功
			// // 返还mac地址
			// String cmd = "set-leisure-ip";
			// String response = null;
			// String mac = vo.getMac();
			// String exist = "";
			// String error = "";
			// // RPCClient rpcClient = new
			// // RPCClient(rabbitmqUtil);
			// response = rpcClient.call(cmd, mac);
			// JSONObject json = JacksonUtil.fromJSON(response,
			// new JacksonUtil.TypeReference<JSONObject>() {
			// });
			// exist = json.get("exist").toString();
			// if (!Boolean.getBoolean(exist)) {
			// error = json.get("error").toString();
			// result = error;
			// }
			// break;
			// }
			// } catch (HttpClientException e) {
			// e.printStackTrace();
			// }
			// }
			// i++;
			// if (i == 12) {// 当ping最后一次失败时，执行修改IP脚本
			// // 调用修改Ip的接口
			// ScriptUnitedVO sVo = VirtualClient.post(url1, scriptUnitedVO,
			// new JacksonUtil.TypeReference<ScriptUnitedVO>() {
			// });
			// // 放开已占用的IP、mac,待开发
			//
			// throw new Exception("修改虚拟机ip失败，请手动配置虚拟机ip");
			// }
			// }
		}
		return result;
	}

	/**
	 * 
	 * @Title: initVmwareVmNicInfo
	 * @Description: 实例Vmware虚拟机多网卡信息
	 * @param
	 * @return VirtualMachineUnitedVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-20 下午3:38:42
	 */
	private VirtualMachineUnitedVO initVmwareVmNicInfo(UnitedTreeObj obj,
			VirtualMachineUnitedVO vo, String[] netWays, String net_id, String[] ipArray,
			String memUnit) throws Exception {
		// 组合网络信息
		List<VirtualNicUnitedVO> nicList = new ArrayList<VirtualNicUnitedVO>();
		String[] net_ids = filterStringSplit(net_id);

		for (int i = 0; i < net_ids.length; i++) {
			VirtualNicUnitedVO nicUnitedVO = new VirtualNicUnitedVO();
			String ip_if_auto = netWays[i];
			if ("1".equals(ip_if_auto)&&ipArray.length>i) {// 手动配置IP
				String ip = ipArray[i];
				TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
				ipObj.setIPADDRESS(ip);
				ipObj.setISUSED("1");
				tbIpDao.updateIPByObj(ipObj);
				String vlan = getVlan(ip);
				// 判断vlan是否获取到
				if (vlan == null) {
					// 抛出异常，交由Action处理
					throw new CloudUnitedException("虚拟机创建失败，未获取到Vlan信息！");
				} else {
					// /通过net_id查询该网络对应的子网掩码。网关等信息
					String netid = net_ids[i];
					TbCloud2NetInfoObj net = new TbCloud2NetInfoObj();
					net.setNET_ID(netid);
					net = tbNetDao.queryByObj(net);

					nicUnitedVO.setGateway(new String[] { net.getGATEWAY1() });
					nicUnitedVO.setDnsServerList(new String[] { net.getDNS1() });
					nicUnitedVO.setSubnetMask(net.getSUBNET());
					nicUnitedVO.setVlanId(Integer.parseInt(vlan));
					nicUnitedVO.setIpAddress(ip);
				}
			} else {// 自动分配IP
				nicUnitedVO = getNetRelase(net_ids[i]);
			}
			nicList.add(nicUnitedVO);
		}
		vo.setVirtualNicList(nicList);
		vo = RevertEntity.toRestVMForCloneTem(obj, vo, memUnit);
		return vo;
	}
	
	
	/**
	 * xenServer暂时不需要设置IP
	 * @Title: initXenServerVmNicInfo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return VirtualMachineUnitedVO
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-9-16 下午1:25:20
	 */
	private VirtualMachineUnitedVO initXenServerVmNicInfo(UnitedTreeObj obj,
			VirtualMachineUnitedVO vo, String[] netWays, String net_id, String[] ipArray,
			String memUnit) throws Exception {
		// 组合网络信息
		List<VirtualNicUnitedVO> nicList = new ArrayList<VirtualNicUnitedVO>();
//		String[] net_ids = filterStringSplit(net_id);
//
//		for (int i = 0; i < net_ids.length; i++) {
//			VirtualNicUnitedVO nicUnitedVO = new VirtualNicUnitedVO();
//			String ip_if_auto = netWays[i];
//			if ("1".equals(ip_if_auto)&&ipArray.length>i) {// 手动配置IP
//				String ip = ipArray[i];
//				TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
//				ipObj.setIPADDRESS(ip);
//				ipObj.setISUSED("1");
//				tbIpDao.updateIPByObj(ipObj);
//				String vlan = getVlan(ip);
//				// 判断vlan是否获取到
//				if (vlan == null) {
//					// 抛出异常，交由Action处理
//					throw new CloudUnitedException("虚拟机创建失败，未获取到Vlan信息！");
//				} else {
//					// /通过net_id查询该网络对应的子网掩码。网关等信息
//					String netid = net_ids[i];
//					TbCloud2NetInfoObj net = new TbCloud2NetInfoObj();
//					net.setNET_ID(netid);
//					net = tbNetDao.queryByObj(net);
//
//					nicUnitedVO.setGateway(new String[] { net.getGATEWAY1() });
//					nicUnitedVO.setDnsServerList(new String[] { net.getDNS1() });
//					nicUnitedVO.setSubnetMask(net.getSUBNET());
//					nicUnitedVO.setVlanId(Integer.parseInt(vlan));
//					nicUnitedVO.setIpAddress(ip);
//				}
//			} else {// 自动分配IP
//				nicUnitedVO = getNetRelase(net_ids[i]);
//			}
//			nicList.add(nicUnitedVO);
//		}
		vo.setVirtualNicList(nicList);
		vo = RevertEntity.toRestVMForCloneTem(obj, vo, memUnit);
		return vo;
	}

	/**
	 * 
	 * @Title: filterStringSplit
	 * @Description: 处理界面传递多网卡数据字符串
	 * @param
	 * @return String[]
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-20 下午3:36:44
	 */
	private String[] filterStringSplit(String param) {
		if (param.indexOf(",") != -1) {
			param = param.substring(0, param.lastIndexOf(","));
		}
		return param.split(",");
	}

	/**
	 * 
	 * @Title: createVMByVM
	 * @Description: 通过虚拟机创建虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 30, 2013 8:17:32 PM
	 */
	public String createVMByVM(UnitedTreeObj obj, VirtualMachineUnitedVO vo,
			HttpServletRequest request) throws Exception {
		String result = UnitedConstant.FAIL;
		String vtype = obj.getVtype();
		int userid = obj.getUser_id();
		obj.setName(vo.getNewVmName());
		obj.setType(UnitedConstant.VM);
		String url = "";
		// 设置操作类型
		vo.setCreateType(Types.createType.VM_TO_VM);
		int vm_id = vmHostDao.queryVhostIdSequence();
		// vo = RevertEntity.toRestVMForCloneTem(obj, vo, request);
		// 模板克隆时为调整虚拟机磁盘为编辑模式 By huojla
		vo.setVmDiskOper(VirtualMachineDiskOper.EDIT);
		// 建立新的虚拟机和模板的关系
		TemVmRelationObj relation = new TemVmRelationObj();
		relation.setVmCode(vo.getVmCode());
		relation.setConnectCode(obj.getConnect_id());
		List<TemVmRelationObj> relationList = temVmRelationDao.queryForList(relation);
		if (relationList != null && relationList.size() > 0) {
			relation = relationList.get(0);
		}
		/*
		 * 修改为支持多网卡
		 */
		String netWay = request.getParameter("netWay");
		String[] netWays = filterStringSplit(netWay);
		String net_id = request.getParameter("net_id");
		String ips = request.getParameter("ips");
		String[] ipArray = filterStringSplit(ips);
		String memUnit = request.getParameter("memUnit");

		vo = initVmwareVmNicInfo(obj, vo, netWays, net_id, ipArray, memUnit);

		if (UnitedConstant.VMWARE.equals(vtype)) {
			url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/clone";
			vo.setStorageSizeInMb(vo.getStorageSizeInMb() * 1024);
			String dataCenterCode = getDataCenterCode(vo.getHostCode(), vo.getConnectCode());
			vo.setDataCenterCode(dataCenterCode);// 涉及多个数据中心时，相应的要区分不同数据中心下的vlan
			int port = getHostFreePort(vo.getConnectCode(), vo.getHostCode());
			VirtualMachineVNCConfig vncConfig = new VirtualMachineVNCConfig();
			vncConfig.setIfVncOpen(true);
			vncConfig.setVncPort(port);
			vo.setVncConfig(vncConfig);

			VirtualMachineUnitedVO vir = VirtualClient.post(url, vo,
					new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
					});

			if (vir.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				// 建立新创建虚拟机和模板关系
				relation.setVmCode(vir.getVmCode());
				temVmRelationDao.insertByObj(relation);
				obj.setUuid(vir.getVmCode());
				UnitedTreeObj un = new UnitedTreeObj();
				/*
				 * 修改为克隆到不同虚拟机un.setUuid(obj.getParent_uuid());
				 */
				un.setUuid(vo.getHostCode());
				un.setConnect_id(obj.getConnect_id());
				List<UnitedTreeObj> list = unitedTreeDao.queryForUnitedTree(un);
				if (list != null && list.size() > 0) {
					un = list.get(0);
					obj.setParent_id(un.getId());
				}
				unitedTreeDao.insertByObj(obj);
				/**
				 * 修改原有插入虚拟机数据代码
				 */
				String eq_id = vmwareDataCompareService.getTbCloud2HostId(vir);
				vir.setVirtualNicList(vo.getVirtualNicList());
				vmwareDataCompareService.saveTbCloud2VMHostInfo(vir, eq_id);

				// 非admin用户，插入entityuser
				if (userid != 1) {
					VmAuthorityObj authorObj = new VmAuthorityObj();
					authorObj.setUSERID(userid);
					authorObj.setENTITY_NAME(obj.getName());
					authorObj.setENTITY_TYPE("4");// 虚拟机的资源类型为4
					authorObj.setENTITY_ID(obj.getUuid());
					authorObj.setOPERAUTHORITY("0");// 给与创建虚拟机人所有的操作权限
					authorObj.setTYPE(vtype);
					authorObj.setCONNECT_ID(obj.getConnect_id());
					vmAuthorityService.insertByObj(authorObj);
				}

				/*
				 * 处理虚拟机VNC服务配置文件@huojla@20140730
				 */
				dealVmVncConfig(vir.getConnectCode(), vir.getHostCode(), vir.getVmCode(), port);
				updateVncPostByVm(vir.getConnectCode(), vir.getHostCode(), vir.getVmCode(), port);
				/*
				 * 处理虚拟机与数据存储关系
				 */
				dealVmDatastoreRelation(vir, vtype);
			} else {
				freeVMIpaddress(vo);
				result = vo.getLog();
				// 抛出异常，交由Action处理
				throw new CloudUnitedException(vo.getLog());
			}
		} else if (UnitedConstant.XEN.equals(vtype)) {
			url = "virtualmachine/" + VirtualConstant.VT_XEN + "/clone";
			vo.setIsTemplate(false);
			VMHostObj vm = new VMHostObj();
			vm.setVH_UUID(vo.getVmCode());
			vm.setConnectId(vo.getClusterCode());
			vm = vmHostDao.queryByObj(vm);
			vo = VirtualClient.post(url, vo,
					new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
					});
			if (vo.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				relation.setVmCode(vo.getVmCode());
				temVmRelationDao.insertByObj(relation);
				obj.setUuid(vo.getVmCode());
				// 判断虚拟机的父节点
				String hostUuid = "";
				String residentUuid = vo.getResidentOnUuid() == null ? "" : vo.getResidentOnUuid();
				String affinityUuid = vo.getAffinityUuid() == null ? "" : vo.getAffinityUuid();
				List<UnitedTreeObj> list = null;
				UnitedTreeObj un = new UnitedTreeObj();
				if (!"".equals(residentUuid)) {
					obj.setUuid(vo.getVmCode());
					un.setConnect_id(obj.getConnect_id());
					un.setUuid(residentUuid);
					list = unitedTreeDao.queryForUnitedTree(un);
				}
				if (list != null && list.size() > 0) {
					un = list.get(0);
					obj.setParent_id(un.getId());
					hostUuid = residentUuid;
				} else {
					UnitedTreeObj affUn = new UnitedTreeObj();
					List<UnitedTreeObj> affList = null;
					if (!"".equals(affinityUuid)) {
						affUn.setConnect_id(obj.getConnect_id());
						affUn.setUuid(affinityUuid);
						affList = unitedTreeDao.queryForUnitedTree(affUn);
					}
					if (affList != null && affList.size() > 0) {
						affUn = affList.get(0);
						obj.setParent_id(affUn.getId());
						hostUuid = affinityUuid;
					} else {
						UnitedTreeObj poolUn = new UnitedTreeObj();
						poolUn.setConnect_id(obj.getConnect_id());
						poolUn.setUuid(obj.getConnect_id());
						List<UnitedTreeObj> poolList = unitedTreeDao.queryForUnitedTree(poolUn);
						if (poolList != null && poolList.size() > 0) {
							poolUn = poolList.get(0);
							obj.setParent_id(poolUn.getId());
							hostUuid = affinityUuid;
						}
					}
				}
				unitedTreeDao.insertByObj(obj);
				vo.setHostCode(hostUuid);
				vo.setConnectCode(obj.getConnect_id());
				String eq_id = vmwareDataCompareService.getTbCloud2HostId(vo);
				vm.setID(vm_id);
				vm.setVH_ID(vm_id + "");
				vm.setVH_NAME(obj.getName());
				vm.setEQ_ID(eq_id);
				vm.setVH_UUID(vo.getVmCode());
				vm.setConnectId(obj.getConnect_id());
				vmHostDao.insertByVMhostObj(vm);

				// 非admin用户，插入entityuser
				if (userid != 1) {
					VmAuthorityObj authorObj = new VmAuthorityObj();
					authorObj.setUSERID(userid);
					authorObj.setENTITY_NAME(obj.getName());
					authorObj.setENTITY_TYPE("4");// 虚拟机的资源类型为4
					authorObj.setENTITY_ID(obj.getUuid());
					authorObj.setOPERAUTHORITY("0");// 给与创建虚拟机人所有的操作权限
					authorObj.setTYPE(vtype);
					authorObj.setCONNECT_ID(obj.getConnect_id());
					vmAuthorityService.insertByObj(authorObj);
				}
			} else {
				result = vo.getLog();
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: freeVMIpaddress
	 * @Description: 创建虚拟机失败释放IP地址
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-21 上午11:37:03
	 */
	private void freeVMIpaddress(VirtualMachineUnitedVO vo) {
		List<VirtualNicUnitedVO> nicList = vo.getVirtualNicList();
		if (nicList != null && nicList.size() > 0) {
			for (VirtualNicUnitedVO nicVo : nicList) {
				String ip = nicVo.getIpAddress();
				TbCloud2IpInfoObj p = new TbCloud2IpInfoObj();
				if (ip != null && !"".equals(ip)) {
					p.setIPADDRESS(ip);
					p.setISUSED("0");
					tbIpDao.updateIPByObj(p);
				}
			}
		}
	}

	/**
	 * 
	 * @Title: markAsTemplate
	 * @Description: 将虚拟机转化为模板
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2013-9-24 下午5:20:17
	 */
	public String markAsTemplate(UnitedTreeObj obj) throws HttpClientException, SQLException {
		String result = UnitedConstant.FAIL;
		VirtualMachineUnitedVO vo = new VirtualMachineUnitedVO();
		vo.setVmCode(obj.getUuid());
		vo.setConnectCode(obj.getConnect_id());
		String vtype = obj.getVtype();
		String url = getRemoteUrl("virtualmachine/", vtype, "/vmtotemplate");
		vo = VirtualClient.put(url, vo, new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
		});
		if (vo.getIsSuccess()) {
			result = UnitedConstant.SUCCESS;
			// 将虚拟机的相关信息添加到模板表里
			VMHostObj v = new VMHostObj();
			v.setConnectId(obj.getConnect_id());
			v.setVH_UUID(obj.getUuid());
			v = vmHostDao.queryByObj(v);
			TemManObj tem = new TemManObj();
			tem.setConnectId(v.getConnectId());
			tem.setTemplateCode(v.getVH_UUID());
			tem.setCpu(Integer.parseInt(v.getVH_CPU()));
			tem.setMem(Integer.parseInt(v.getVH_MEM()));
			tem.setStore(Double.parseDouble(v.getVH_STORAGE()));
			tem.setHostCode(v.getHostCode());
			tem.setIsPhysical("0");// 非物理模板
			tem.setIsPublic("0");// 公有
			tem.setSystem(v.getVH_SYSTEM());
			tem.setType(UnitedConstant.VMWARE);
			tem.setName(v.getVH_NAME());
			temManDao.insertByObj(tem);
			// 将虚拟机在united_tree表的数据删除
			unitedTreeDao.deleteByObj(obj);
			// 将虚拟机从虚拟机表中删除
			vmHostDao.deleteByObj(v);
		}
		return result;

	}

	/**
	 * 
	 * @Title: createSnapShot
	 * @Description: 创建虚拟机快照
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime Aug 1, 2013 9:43:19 AM
	 */
	public String createSnapShot(UnitedTreeObj obj, SnapshotUnitedVO vo) throws HttpClientException {
		String result = UnitedConstant.FAIL;
		String url = "";
		String vtype = obj.getVtype();
		if (UnitedConstant.VMWARE.equals(vtype)) {
			url = "snapshot/" + VirtualConstant.VT_VMWARE + "/create";
			vo = RevertEntity.copyToSnapshotUnitedVO(obj, vo);
			vo = VirtualClient.post(url, vo, new JacksonUtil.TypeReference<SnapshotUnitedVO>() {
			});
			if (!vo.getIsSuccess()) {
				result = vo.getLog();
			} else {
				result = UnitedConstant.SUCCESS;
			}
		} else if (UnitedConstant.XEN.equals(vtype)) {
			url = "snapshot/" + VirtualConstant.VT_XEN + "/create";
			vo = RevertEntity.copyToSnapshotUnitedVO(obj, vo);
			vo = VirtualClient.post(url, vo, new JacksonUtil.TypeReference<SnapshotUnitedVO>() {
			});
			if (!vo.getIsSuccess()) {
				result = vo.getLog();
			} else {
				result = UnitedConstant.SUCCESS;
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: getSnapShotList
	 * @Description: 获取虚拟机快照列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime Aug 1, 2013 3:00:33 PM
	 */
	public SnapshotUnitedVO getSnapShotList(UnitedTreeObj obj) throws HttpClientException {
		String vtype = obj.getVtype();
		SnapshotUnitedVO result = new SnapshotUnitedVO();
		String url = "";
		if (UnitedConstant.VMWARE.equals(vtype)) {

			url = "snapshot/" + VirtualConstant.VT_VMWARE + "/search/" + obj.getConnect_id() + "/"
					+ obj.getUuid();
			result = VirtualClient.get(url, new JacksonUtil.TypeReference<SnapshotUnitedVO>() {
			});
		} else if (UnitedConstant.XEN.equals(vtype)) {
			url = "snapshot/" + VirtualConstant.VT_XEN + "/search/" + obj.getConnect_id() + "/"
					+ obj.getUuid();
			result = VirtualClient.get(url, new JacksonUtil.TypeReference<SnapshotUnitedVO>() {
			});
		}
		return result;
	}

	/**
	 * 
	 * @Title: operSnapHost
	 * @Description: 对于快照进行操作
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 1, 2013 7:19:47 PM
	 */
	public String operSnapShot(UnitedTreeObj obj, HttpServletRequest request)
			throws HttpClientException {
		String oper = request.getParameter("oper");
		String snapshotCode = request.getParameter("snapshotCode");
		String vtype = obj.getVtype();
		SnapshotUnitedVO vo = new SnapshotUnitedVO();
		String url = "";
		String result = UnitedConstant.FAIL;
		if (UnitedConstant.VMWARE.equals(vtype)) {
			vo = RevertEntity.copyToSnapshotUnitedVO(obj, vo);
			if ("recover".equals(oper)) {
				url = "snapshot/" + VirtualConstant.VT_VMWARE + "/revert";
				vo.setSnapshotCode(snapshotCode);
				vo = VirtualClient.put(url, vo, new JacksonUtil.TypeReference<SnapshotUnitedVO>() {
				});
			} else if ("delete".equals(oper)) {
				url = "snapshot/" + VirtualConstant.VT_VMWARE + "/delete/" + obj.getConnect_id()
						+ "/host/" + snapshotCode;
				vo = VirtualClient.delete(url, new JacksonUtil.TypeReference<SnapshotUnitedVO>() {
				});
			}
			if (!vo.getIsSuccess()) {
				result = vo.getLog();
			} else {
				result = UnitedConstant.SUCCESS;
			}

		} else if (UnitedConstant.XEN.equals(vtype)) {
			vo = RevertEntity.copyToSnapshotUnitedVO(obj, vo);
			if ("recover".equals(oper)) {
				url = "snapshot/" + VirtualConstant.VT_XEN + "/revert";
				vo.setSnapshotCode(snapshotCode);
				vo = VirtualClient.put(url, vo, new JacksonUtil.TypeReference<SnapshotUnitedVO>() {
				});
			} else if ("delete".equals(oper)) {
				url = "snapshot/" + VirtualConstant.VT_XEN + "/delete/" + obj.getConnect_id()
						+ "/host/" + snapshotCode;
				vo = VirtualClient.delete(url, new JacksonUtil.TypeReference<SnapshotUnitedVO>() {
				});
			}
			if (!vo.getIsSuccess()) {
				result = vo.getLog();
			} else {
				result = UnitedConstant.SUCCESS;
			}
		}
		return result;

	}

	/**
	 * 
	 * @Title: console
	 * @Description: 打开虚拟机控制台
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime Aug 1, 2013 4:06:10 PM
	 */
	public String console(UnitedTreeObj obj) throws HttpClientException {
		String vtype = obj.getVtype();
		String url = "";
		String ticket = "";
		if (UnitedConstant.VMWARE.equals(vtype)) {
			url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/ticket/" + obj.getConnect_id();
			ticket = VirtualClient.get(url);
		}
		return ticket;
	}

	/**
	 * 
	 * @Title: saveVMInfo
	 * @Description: 保存修改后的虚拟机信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @throws HttpClientException
	 * @throws SQLException
	 * @createtime Jul 30, 2013 8:17:32 PM
	 */
	@Override
	public String saveVMInfo(UnitedTreeObj obj, VirtualMachineUnitedVO vo)
			throws HttpClientException, SQLException {
		String result = UnitedConstant.FAIL;
		String vtype = obj.getVtype();
		String url = "";
		VirtualMachineUnitedVO vi = RevertEntity.toVirtualMachineUnitedVO(obj, vo);
		if (UnitedConstant.VMWARE.equals(vtype)) {
			url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/adjust";
			VirtualMachineUnitedVO vir = VirtualClient.put(url, vi,
					new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
					});
			if (vir.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				String eq_id = vmwareDataCompareService.getTbCloud2HostId(vi);
				vi.setStorageSizeInMb(vir.getStorageSizeInMb());
				vmwareDataCompareService.updateTbCloud2VMHostInfo(vi, eq_id);
			} else {
				result = vo.getLog();
			}
		} else if (UnitedConstant.XEN.equals(vtype)) {
			url = "virtualmachine/" + VirtualConstant.VT_XEN + "/adjust";
			vi.setCpuMax(vi.getNumCPUs());
			vi.setMemDynamicMax(vi.getMemoryMB().longValue());
			vi.setMemDynamicMin(vi.getMemoryMB().longValue());
			vi.setMemStaticMax(vi.getMemoryMB().longValue());
			vi.setMemStaticMin(vi.getMemoryMB().longValue());
			VirtualMachineUnitedVO vir = VirtualClient.put(url, vi,
					new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
					});
			if (vir.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				VMHostObj vmHostObj = new VMHostObj();
				vmHostObj.setVH_UUID(obj.getUuid());
				vmHostObj.setConnectId(obj.getConnect_id());
				vmHostObj.setVH_CPU(vi.getNumCPUs() + "");
				vmHostObj.setVH_MEM(vi.getMemoryMB() + "");
				vmHostDao.updateVMHostInfo(vmHostObj);
			} else {
				result = vo.getLog();
			}
		}
		return result;
	}

	/**
	 * <p>
	 * Title: migrateVM
	 * </p>
	 * <p>
	 * Description: 迁移虚拟机
	 * 
	 * @param obj
	 * @param vo
	 * @return
	 * @throws Exception
	 * @see com.sitech.basd.resource.service.united.UnitedVMService#migrateVM(com.sitech.basd.resource.domain.united.UnitedTreeObj,
	 *      com.sitech.vo.united.VirtualMachineUnitedVO)
	 */
	@Override
	public String migrateVM(UnitedTreeObj obj, VirtualMachineUnitedVO v) throws Exception {

		String result = UnitedConstant.FAIL;
		String vtype = obj.getVtype();
		obj.setType(UnitedConstant.VM);
		VirtualMachineUnitedVO vi = RevertEntity.toRestVM(obj);

		String url = "";
		if (UnitedConstant.VMWARE.equals(vtype)) {
			url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/migrate";
			// 迁移存储
			if (v.getDatastoreCode() != null && !"".equals(v.getDatastoreCode())) {// /在本机存储之间进行迁移
				vi.setDatastoreCode(v.getDatastoreCode());
				vi.setHostCode(null);// 先清空code
				String hostCode = obj.getParent_uuid();
				if (hostCode != null && !"".equals(hostCode) && !"null".equals(hostCode)) {
					vi.setHostCode(obj.getParent_uuid());
				}
				vi.setConnectCode(obj.getConnect_id());
				url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/relocate";
			}
			VirtualMachineUnitedVO vir = VirtualClient.put(url, vi,
					new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
					});
			// 迁移主机
			if (vir.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				String eq_id = vmwareDataCompareService.getTbCloud2HostId(vi);
				vmwareDataCompareService.updateVMhostAfterMigrate(vi, eq_id);
				unitedTreeDao.updateByObj(obj);
			} else {
				result = vir.getLog();
				// 抛出异常，交由Action处理
				throw new CloudUnitedException("迁移失败：" + vir.getLog());
			}
		} else if (UnitedConstant.XEN.equals(vtype)) {
			url = "virtualmachine/" + VirtualConstant.VT_XEN + "/migrate";
			VirtualMachineUnitedVO vir = VirtualClient.put(url, vi,
					new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
					});
			if (vir.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				String eq_id = vmwareDataCompareService.getTbCloud2HostId(vi);
				vmwareDataCompareService.updateVMhostAfterMigrate(vi, eq_id);
				unitedTreeDao.updateByObj(obj);
			} else {
				result = vir.getLog();
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: relocateVM
	 * @Description: 重定位虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-24 下午3:12:57
	 */
	public String relocateVM(VirtualMachineUnitedVO v) throws Exception {
		String result = UnitedConstant.FAIL;
		String url = "";
		String vtype = v.getVType();
		if (UnitedConstant.VMWARE.equals(vtype)) {
			url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/relocate";
			VirtualMachineUnitedVO vir = VirtualClient.put(url, v,
					new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
					});
			if (vir.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				String eq_id = vmwareDataCompareService.getTbCloud2HostId(v);
				vmwareDataCompareService.updateVMhostAfterMigrate(v, eq_id);
			} else {
				result = vir.getLog();
				throw new CloudUnitedException("迁移失败：" + vir.getLog());
			}
		} else if (UnitedConstant.XEN.equals(vtype)) {
			url = "virtualmachine/" + VirtualConstant.VT_XEN + "/migrate";
			VirtualMachineUnitedVO vir = VirtualClient.put(url, v,
					new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
					});
			if (vir.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				String eq_id = vmwareDataCompareService.getTbCloud2HostId(v);
				vmwareDataCompareService.updateVMhostAfterMigrate(v, eq_id);
			} else {
				result = vir.getLog();
			}
		}

		// 更新树表
		UnitedTreeObj parentObj = new UnitedTreeObj();
		parentObj.setUuid(v.getHostCode());
		parentObj.setConnect_id(v.getConnectCode());
		parentObj = unitedTreeDao.queryForListByObj(parentObj).get(0);

		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setUuid(v.getVmCode());
		obj.setConnect_id(v.getConnectCode());
		obj = unitedTreeDao.queryForListByObj(obj).get(0);
		obj.setParent_id(parentObj.getId());
		unitedTreeDao.updateByObj(obj);
		return result;
	}

	/**
	 * 
	 * @Title: copyVOtoVmHostObj
	 * @Description: 将从克隆后从接口获取的vo的信息转化为vmhostObj
	 * @param
	 * @return VMHostObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 2, 2013 4:25:14 PM
	 */
	public VMHostObj copyVOtoVmHostObj(VirtualMachineUnitedVO vo, String vtype) {
		VMHostObj vmhostObj = new VMHostObj();
		vmhostObj.setVH_UUID(vo.getVmCode());
		vmhostObj.setVH_CPU(vo.getNumCPUs() + "");
		vmhostObj.setVH_MEM(vo.getMemoryMB().intValue() + "");
		vmhostObj.setVH_STORAGE(vo.getStorageSizeInMb() + "");
		vmhostObj.setVH_SYSTEM(vo.getOperationSystemName());
		vmhostObj.setVH_NAME(vo.getVmName());
		vmhostObj.setVH_IP(vo.getIp());
		String vmStat = vo.getPowerState();
		if ("poweredOn".equals(vmStat)) {
			vmhostObj.setVH_STAT("1");
		} else if ("poweredOff".equals(vmStat)) {
			vmhostObj.setVH_STAT("0");
		}
		vmhostObj.setDNS(vo.getDns());
		vmhostObj.setVH_TYPE(vtype);
		vmhostObj.setConnectId(vo.getConnectCode());
		return vmhostObj;
	}

	/**
	 * 获取虚拟机的控制台参数
	 * 
	 * @param vi
	 * @param vtype
	 * @return
	 * @throws HttpClientException
	 */
	public VirtualMachineConsole vmConsole(VirtualMachineUnitedVO vi, String vtype)
			throws HttpClientException {
		VirtualMachineConsole vir = null;
		if (UnitedConstant.XEN.equals(vtype)) {
			String url = "virtualmachine/" + VirtualConstant.VT_XEN + "/console/"
					+ vi.getConnectCode() + "/" + vi.getVmCode();
			vir = VirtualClient.get(url, new JacksonUtil.TypeReference<VirtualMachineConsole>() {
			});
		}
		return vir;
	}

	/**
	 * 
	 * @Title: getXenMigrateHost
	 * @Description: 获取xen虚拟机可以迁移的主机
	 * @param
	 * @return List<TbCloud2HostInfoObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime Aug 9, 2013 2:54:35 PM
	 */
	public HostUnitedVO getXenMigrateHost(String connectCode, String vmCode)
			throws HttpClientException {
		HostUnitedVO hostVo = null;
		String url = "virtualmachine/" + VirtualConstant.VT_XEN + "/migrate/lookup/host/"
				+ connectCode + "/" + vmCode;
		hostVo = VirtualClient.get(url, new JacksonUtil.TypeReference<HostUnitedVO>() {
		});
		return hostVo;
	}

	/**
	 * 
	 * @Title: getVMNetWork
	 * @Description: 查看虚拟机的网络信息
	 * @param
	 * @return VirtualMachineConsole
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Aug 9, 2013 11:51:51 AM
	 */
	public NetworkUnitedVO getVMNetWork(VirtualMachineUnitedVO obj) throws HttpClientException {
		NetworkUnitedVO networkUnitedVO = new NetworkUnitedVO();
		String vtype = "";
		if (obj.getVmType().equals(UnitedConstant.POWERVM)) {
			vtype = VirtualConstant.VT_IBM;
		} else if (obj.getVmType().equals(UnitedConstant.XEN)) {
			vtype = VirtualConstant.VT_XEN;
		} else if (obj.getVmType().equals(UnitedConstant.KVM)) {
			vtype = VirtualConstant.VT_KVM;
		} else {
			vtype = VirtualConstant.VT_VMWARE;
		}
		String url = "virtualmachine/" + vtype + "/network/lookup/" + obj.getConnectCode() + "/"
				+ obj.getVmCode();
		networkUnitedVO = VirtualClient.get(url, new JacksonUtil.TypeReference<NetworkUnitedVO>() {
		});
		return networkUnitedVO;
	}

	/**
	 * @Title: getNetRelase
	 * @Description: 获取网络相关
	 * @param
	 * @return VirtualMachineUnitedVO
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-2 上午11:42:23
	 */
	private synchronized VirtualNicUnitedVO getNetRelase(String network_id) throws Exception {
		TbCloud2NetInfoObj netObj = new TbCloud2NetInfoObj();
		netObj.setNET_ID(network_id);
		@SuppressWarnings("unchecked")
		List<TbCloud2NetInfoObj> netList = tbNetDao.queryByNetObjForList(netObj);
		if (netList.size() > 0) {
			netObj = netList.get(0);
		} else {
			throw new Exception("没有合适的网络");
		}

		VirtualNicUnitedVO vi = new VirtualNicUnitedVO();
		if (netObj != null) {
			TbCloud2IpInfoObj ipobj = new TbCloud2IpInfoObj();
			ipobj.setNET_ID(netObj.getNET_ID());
			ipobj.setISUSED("0");
			List<TbCloud2IpInfoObj> iplist = tbIpDao.queryForListByObj(ipobj);
			if (iplist != null && iplist.size() > 0) {
				TbCloud2IpInfoObj ipObj = iplist.get(0);
				vi.setIpAddress(ipObj.getIPADDRESS());
				// 将ip的使用状态标记为已使用,锁定ip，避免多线程时选取同一个ip
				ipObj.setISUSED("1");
				tbIpDao.updateIPByObj(ipObj);
			} else {
				throw new Exception("没有剩余的ip");
			}
			vi.setGateway(new String[] { netObj.getGATEWAY1() });
			vi.setDnsServerList(new String[] { netObj.getDNS1() });
			vi.setSubnetMask(netObj.getSUBNET());
			vi.setVlanId(Integer.parseInt(netObj.getVLAN_ID()));
		} else {
			throw new Exception("没有合适的网络域");
		}
		return vi;
	}

	/**
	 * 
	 * @Title: getNetRelase
	 * @Description: 获取网络相关,实现多网卡，不再使用
	 * @param
	 * @return VirtualMachineUnitedVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-21 下午3:09:18
	 */
	@Deprecated
	private synchronized VirtualMachineUnitedVO getNetRelase(String network_id,
			VirtualMachineUnitedVO vo) throws Exception {
		TbCloud2NetInfoObj netObj = new TbCloud2NetInfoObj();
		netObj.setNET_ID(network_id);
		@SuppressWarnings("unchecked")
		List<TbCloud2NetInfoObj> netList = tbNetDao.queryByNetObjForList(netObj);
		if (netList.size() > 0) {
			netObj = netList.get(0);
		} else {
			throw new Exception("没有合适的网络");
		}

		VirtualNicUnitedVO vi = new VirtualNicUnitedVO();
		if (netObj != null) {
			List<VirtualNicUnitedVO> nicList = new ArrayList<VirtualNicUnitedVO>();
			TbCloud2IpInfoObj ipobj = new TbCloud2IpInfoObj();
			ipobj.setNET_ID(netObj.getNET_ID());
			ipobj.setISUSED("0");
			List<TbCloud2IpInfoObj> iplist = tbIpDao.queryForListByObj(ipobj);
			int vlan = 0;
			if (iplist != null && iplist.size() > 0) {
				TbCloud2IpInfoObj ipObj = iplist.get(0);
				vi.setIpAddress(ipObj.getIPADDRESS());
				vo.setIp(ipObj.getIPADDRESS());
				// 将ip的使用状态标记为已使用,锁定ip，避免多线程时选取同一个ip
				ipObj.setISUSED("1");
				tbIpDao.updateIPByObj(ipObj);
				vlan = Integer.parseInt(netObj.getVLAN_ID());
			} else {
				throw new Exception("没有剩余的ip");
			}
			// 待定，双网卡时改造
			vi.setGateway(new String[] { netObj.getGATEWAY1() });
			vi.setDnsServerList(new String[] { netObj.getDNS1() });
			vi.setSubnetMask(netObj.getSUBNET());
			vi.setVlanId(vlan);
			nicList.add(vi);
			vo.setVirtualNicList(nicList);
		} else {
			throw new Exception("没有合适的网络域");
		}
		return vo;
	}

	/**
	 * 
	 * @Title: getVlan
	 * @Description: 通过IP地址获取vlan
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-6 上午10:40:05
	 */
	public String getVlan(String ip) {
		TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
		ipObj.setIPADDRESS(ip);
		ipObj = tbIpDao.queryByObj(ipObj);
		String net_id = ipObj.getNET_ID();
		TbCloud2NetInfoObj net = new TbCloud2NetInfoObj();
		net.setNET_ID(net_id);
		net = tbNetDao.queryByObj(net);
		/* String net_name = net.getVLAN_ID(); */
		String vlan = "";
		vlan = net.getVLAN_ID();
		/*
		 * if (net_name != null && !"".equals(net_name)) { vlan =
		 * net_name.substring(net_name.lastIndexOf("-") + 1, net_name.length());
		 * }
		 */
		return vlan;
	}

	public VmAuthorityService getVmAuthorityService() {
		return vmAuthorityService;
	}

	public void setVmAuthorityService(VmAuthorityService vmAuthorityService) {
		this.vmAuthorityService = vmAuthorityService;
	}

	/**
	 * @Title: createVMByTemAuto
	 * @Description: 通过bomc一键式删除虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2013-8-23 下午4:34:12
	 */
	@Override
	public String deleteVMByBomc(UnitedTreeObj uobj) {
		String result = "success";
		String vtype = uobj.getVtype();
		String vmCode = uobj.getUuid();
		String connectCode = uobj.getConnect_id();
		if (vmCode == null || "".equals(vmCode) || connectCode == null || "".equals(connectCode)) {
			result = "删除虚拟机失败：无法获取虚拟机或者数据中心的链接";
		} else {
			String getpowerurl = "";
			String putpowerurl = "";
			String deleteurl = "";
			VirtualMachineUnitedVO vo = new VirtualMachineUnitedVO();
			VirtualMachinePowerStateUnitedVO powervo = new VirtualMachinePowerStateUnitedVO();
			powervo.setVmCode(vmCode);
			powervo.setConnectCode(connectCode);
			if ("1".equals(vtype)) {
				getpowerurl = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/vms/powerstate/"
						+ connectCode + "/" + vmCode;
				putpowerurl = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/change/powerstate";
				deleteurl = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/destory/"
						+ connectCode + "/" + vmCode;
				try {
					powervo = VirtualClient.get(getpowerurl,
							new JacksonUtil.TypeReference<VirtualMachinePowerStateUnitedVO>() {
							});
					if (powervo.isSuccess) {
						if (VirtualMachinePowerStatus.powerOn.equals(powervo
								.getVirtualMachinePowerState())) {
							powervo.setVirtualMachinePowerOper(VirtualMachinePowerStatus.powerOff);
							try {
								powervo = VirtualClient
										.put(putpowerurl,
												powervo,
												new JacksonUtil.TypeReference<VirtualMachinePowerStateUnitedVO>() {
												});
								if (powervo.isSuccess) {
									try {
										vo = VirtualClient
												.delete(deleteurl,
														new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
														});
										if (!vo.isSuccess) {
											result = "删除虚拟机失败：无法删除，请稍后再试或者联系管理员";
										}
									} catch (Exception e) {
										result = "删除虚拟机失败：无法删除，请稍后再试或者联系管理员";
									}
								} else {
									result = "删除虚拟机失败：关闭虚拟机电源失败";
								}
							} catch (Exception e) {
								result = "删除虚拟机失败：关闭虚拟机电源失败";
							}
						} else if (VirtualMachinePowerStatus.powerOff.equals(powervo
								.getVirtualMachinePowerState())) {
							try {
								vo = VirtualClient.delete(deleteurl,
										new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
										});
								if (!vo.isSuccess) {
									result = "删除虚拟机失败：无法删除，请稍后再试或者联系管理员";
								}
							} catch (Exception e) {
								result = "删除虚拟机失败：无法删除，请稍后再试或者联系管理员";
							}
						} else {
							result = "删除虚拟机失败：虚拟机处于挂起状态，无法删除，请稍后再试或者联系管理员";
						}
					} else {
						result = "删除虚拟机失败：获取虚拟机电源状态失败";
					}
				} catch (Exception e) {
					result = "删除虚拟机失败：获取虚拟机电源状态失败";
				}
			} else {
				result = "删除虚拟机失败：暂不支持该虚拟化类型的，亲";
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: getVMDiskList
	 * @Description: 获取虚拟机对应的磁盘列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-17 下午3:41:00
	 */
	public List getVMDiskList(UnitedTreeObj obj) {
		List<VirtualDiskUnitedVO> diskList = new ArrayList<VirtualDiskUnitedVO>();
		String vtype = "";
		if (UnitedConstant.VMWARE.equals(obj.getVtype())) {
			vtype = VirtualConstant.VT_VMWARE;
		}
		String param = UnitedRestURI.paramvType + vtype + UnitedRestURI.paramconnectCode
				+ obj.getConnect_id();
		try {
			diskList = VirtualClient.get(UnitedRestURI.VMHARDWAREURI + obj.getUuid()
					+ UnitedRestURI.VDISK + param,
					new JacksonUtil.TypeReference<List<VirtualDiskUnitedVO>>() {
					});

			if (diskList != null && diskList.size() > 0) {
				for (int i = 0; i < diskList.size(); i++) {
					VirtualDiskUnitedVO v = new VirtualDiskUnitedVO();
					v = diskList.get(i);
					v.setCapacityInMB(v.getCapacityInMB() / 1024);// 单位转化为G
					diskList.set(i, v);
				}
			}
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
		return diskList;
	}

	/**
	 * 
	 * @Title: insertVMhostHist
	 * @Description: 在虚拟机创建和删除的时候将操作信息
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-19 下午5:02:16
	 */
	public void insertVMhostHist(String oper, VMHostObj obj) {
		if ("1".equals(oper)) {// 创建虚拟机
			vmHostDao.insertVmhostHis(obj);
		} else {// 删除虚拟机
			vmHostDao.insertByVMhostObj(obj);
		}
	}

	/**
	 * 
	 * @Title: getDataCenterCode
	 * @Description: 获取主机所在数据中心的code
	 * @param
	 * @return VirtualMachineUnitedVO
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-11-2 下午12:16:40
	 */
	public String getDataCenterCode(String hostCode, String connectCode) {
		UnitedTreeObj host = new UnitedTreeObj();
		host.setUuid(hostCode);
		host.setConnect_id(connectCode);
		String dataCenterCode = "";
		try {
			host = unitedTreeDao.queryByObj(host);

			if (host != null) {
				UnitedTreeObj parent = new UnitedTreeObj();
				parent.setId(host.getParent_id());
				parent = unitedTreeDao.queryByObj(parent);
				if (UnitedConstant.DATACENTER.equals(parent.getType())) {// 此时数据中心是主机的父节点
					dataCenterCode = parent.getUuid();
				} else {
					UnitedTreeObj grand = new UnitedTreeObj();
					grand.setId(parent.getParent_id());
					grand = unitedTreeDao.queryByObj(grand);
					if (grand != null) {
						dataCenterCode = grand.getUuid();
					}
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return dataCenterCode;
	}

	/**
	 * 
	 * @Title: saveRenameVM
	 * @Description: 针对虚拟机进行重命名； 修改流程：1、判断新的虚拟机名称，是否已经存在；
	 *               2、调用接口修改虚拟机的名称；接口返回成功后，更新数据库；
	 * @param
	 * @return String
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @throws SQLException
	 * @throws HttpClientException
	 * @createtime 2013-11-5 上午11:25:11
	 */
	public String renameVM(UnitedTreeObj unitedTreeObj) throws SQLException {
		RenameVO vo = new RenameVO();
		String result = UnitedConstant.FAIL + "Failed:";
		String type = unitedTreeObj.getType();
		if (UnitedConstant.DATACENTER.equals(type)) {
			vo.setEntityType(VMwareEntityType.DATACENTER);
		} else if (UnitedConstant.CLUSTER.equals(type)) {
			vo.setEntityType(VMwareEntityType.CLUSTERCOMPUTERESOURCE);
		} else if (UnitedConstant.HOST.equals(type)) {
			vo.setEntityType(VMwareEntityType.HOSTSYSTEM);
		} else if (UnitedConstant.VM.equals(type)) {
			vo.setEntityType(VMwareEntityType.VIRTUALMACHINE);
		} else if (UnitedConstant.STORAGE.equals(type)) {
			vo.setEntityType(VMwareEntityType.DATASTORE);
		}
		vo.setConnectCode(unitedTreeObj.getConnect_id());
		vo.setEntityCode(unitedTreeObj.getUuid());
		vo.setNewName(unitedTreeObj.getName());
		// 调用接口修改，虚拟机的名称
		String url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/rename";
		try {
			vo = VirtualClient.put(url, vo, new JacksonUtil.TypeReference<RenameVO>() {
			});
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
		if (vo.getIsSuccess()) {
			result = UnitedConstant.SUCCESS;
			// 更新树表数据名称
			unitedTreeDao.updateByObj(unitedTreeObj);
			if (UnitedConstant.HOST.equals(type)) {
				// 更新主机表
				TbCloud2HostInfoObj host = new TbCloud2HostInfoObj();
				host.setH_uuid(unitedTreeObj.getUuid());
				host.setConnectId(unitedTreeObj.getConnect_id());
				host = hostInfoDao.queryByObj(host);
				if (host != null) {
					host.setEq_name(unitedTreeObj.getName());
					hostInfoDao.updateByObj(host);
				}
			} else if (UnitedConstant.VM.equals(type)) {
				// 更新虚拟机表
				VMHostObj vm = new VMHostObj();
				vm.setConnectId(unitedTreeObj.getConnect_id());
				vm.setVH_UUID(unitedTreeObj.getUuid());
				vm = vmHostDao.queryByObj(vm);
				if (vm != null) {
					vm.setVH_NAME(unitedTreeObj.getName());
					vmHostDao.updateName(vm);
				}
			}
		}
		return result;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: importOvf
	 * </p>
	 * <p>
	 * Description:调用rest接口导入OVF，成功得到
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @param ovf
	 * @return
	 * @see com.sitech.basd.resource.service.united.UnitedVMService#importOvf(com.sitech.basd.resource.domain.united.UnitedTreeObj,
	 *      com.sitech.vo.united.OvfUnitedVO)
	 */
	@Override
	public String importOvf(UnitedTreeObj obj, VirtualMachineUnitedVO ovf) {
		String result = UnitedConstant.SUCCESS;
		/*
		 * VirtualMachineUnitedVO vmVo = new VirtualMachineUnitedVO(); String
		 * result = UnitedConstant.FAIL; String vtype = obj.getVtype(); String
		 * url = getRemoteUrl("ovf/", vtype , "/import"); try { vmVo =
		 * VirtualClient.post(url, ovf, new
		 * JacksonUtil.TypeReference<VirtualMachineUnitedVO>() { }); } catch
		 * (HttpClientException e) { e.printStackTrace(); } if
		 * (vmVo.getIsSuccess()) { UnitedTreeObj vmTreeObj = new
		 * UnitedTreeObj(); vmTreeObj.setConnect_id(obj.getConnect_id());
		 * vmTreeObj.setVtype(obj.getVtype());
		 * vmTreeObj.setType(UnitedConstant.VM);
		 * vmTreeObj.setParent_id(obj.getId());
		 * vmTreeObj.setUser_id(obj.getUser_id());
		 * 
		 * vmTreeObj.setUuid(vmVo.getVmCode());
		 * vmTreeObj.setName(vmVo.getVmName());
		 * 
		 * try { // 更新树表数据,插入vm到主机下 unitedTreeDao.insertByObj(vmTreeObj); //
		 * 添加到虚拟机表 vmHostDao.insertByVMhostObj(copyVOtoVmHostObj(vmVo,vtype));
		 * 
		 * result = UnitedConstant.SUCCESS; } catch (SQLException e) {
		 * e.printStackTrace(); result = UnitedConstant.FAIL; } }
		 */
		return result;
	}

	/**
	 * 
	 * @Title: getRemoteUrl
	 * @Description: TODO(根据虚拟机类型及操作拼装 url)
	 * @param @param vtype
	 * @param @param action
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	private String getRemoteUrl(String startStr, String vtype, String endStr) {
		String url = "";
		if (UnitedConstant.VMWARE.equals(vtype)) {
			url = startStr + VirtualConstant.VT_VMWARE + endStr;
		} else if (UnitedConstant.XEN.equals(vtype)) {
			url = startStr + VirtualConstant.VT_XEN + endStr;
		} else if (UnitedConstant.KVM.equals(vtype)) {
			url = startStr + VirtualConstant.VT_KVM + endStr;
		} else if (UnitedConstant.POWERVM.equals(vtype)) {
			url = startStr + VirtualConstant.VT_IBM + endStr;
		}
		return url;
	}

	/**
	 * 
	 * @Title: queryVmIpRelation
	 * @Description: 查询虚拟机及IP关联关系
	 * @param
	 * @return List<VmIpObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-23 下午7:11:32
	 */
	public List<VmIpObj> queryVmIpRelation(VmIpObj obj) {
		List<VmIpObj> result = nasFileSysDao.queryVmIpRelation(obj);
		if (result == null) {
			result = new ArrayList<VmIpObj>();
		}
		return result;
	}

	/**
	 * 
	 * @Title: dealVmVncConfig
	 * @Description: 处理虚拟机使用noVnc服务相关配置
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SQLException
	 * @throws ConsoleException
	 * @createtime 2014-7-30 下午5:39:09
	 */
	public void dealVmVncConfig(String connectId, String hostCode, String vmCode, int vncport)
			throws SQLException, ConsoleException {
		String entityCode = connectId + "_" + vmCode;
		LOG.info("开始处理虚拟机Vnc配置！" + entityCode);
		TbGlobalConfigObj param = new TbGlobalConfigObj();
		param.setKEY("websockify_deploy_host");
		param = tbGlobalConfigDao.queryByObj(param);
		if (param != null) {
			/*
			 * 写虚拟机vnc配置文件
			 */
			TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
			obj.setConnectId(connectId);
			obj.setH_uuid(hostCode);
			obj = hostInfoService.queryByObj(obj);

			String eq_ip = obj.getEq_ip();
			if (eq_ip == null || "".equals(eq_ip)) {
				LOG.error("写VncPort配置文件失败，获取宿主机IP失败！" + entityCode + ",未向下进行！已return!");
				// throw new ConsoleException("写VncPort配置文件失败，获取宿主机IP失败！" +
				// entityCode
				// + ",未向下进行！已return!");
				return;
			}
			LOG.info("开始写虚拟机Vnc配置！" + entityCode);
			String resourcePath = Thread.currentThread().getContextClassLoader().getResource("")
					.getFile();
			entityCode = entityCode.replace(".", "_");
			// Linux系统
			String localFilePath = resourcePath.substring(0) + File.separator + entityCode;
			localFilePath = localFilePath.replace("%20", " ");
			File file = new File(localFilePath);
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter(file, false));
				// bw.write(connectId + "_" + vmCode + ": " + eq_ip + ":" +
				// vncport);
				// 修改为MD5转码
				bw.write(DigestUtils.md5Hex(connectId + "_" + vmCode) + ": " + eq_ip + ":"
						+ vncport);
				bw.flush();
				bw.close();
			} catch (IOException e) {
				LOG.error(
						"BufferedWriter写VncPort配置文件失败！" + entityCode + ",未向下进行！已return!"
								+ e.getMessage(), e);
				return;
				// throw new ConsoleException("BufferedWriter写VncPort配置文件失败！" +
				// entityCode
				// + ",未向下进行！已return!" + e.getMessage(), e);
			} finally {
				if (bw != null) {
					try {
						bw.close();
					} catch (IOException e) {
						LOG.error("BufferedWriter写VncPort配置文件后关闭失败！" + entityCode
								+ ",未向下进行！已return!" + e.getMessage(), e);
						// throw new
						// ConsoleException("BufferedWriter写VncPort配置文件后关闭失败！" +
						// entityCode
						// + ",未向下进行！已return!" + e.getMessage(), e);
						return;
					}
				}
			}
			/*
			 * 上传文件
			 */
			LOG.info("开始上传虚拟机Vnc配置！" + entityCode);
			String[] hostParams = param.getVALUE().split(":");
			String host = hostParams[0];
			String port = hostParams[1];
			String username = hostParams[2];
			String password = DES3.decrypt(hostParams[3]);
			String filepath = hostParams[4];

			ChannelSftp sftp = SftpUtil.getSftpConnection(host, Integer.parseInt(port), username,
					password);
			try {
				SftpUtil.uploadLocalFileToRemote(sftp, filepath, localFilePath);
			} catch (SSHException e) {
				LOG.error(
						"Sftp上传文件至websockify服务器失败！" + entityCode + ",未向下进行！已return!"
								+ e.getMessage(), e);
				return;
				// throw new ConsoleException("Sftp上传文件至websockify服务器失败！" +
				// entityCode
				// + ",未向下进行！已return!" + e.getMessage(), e);
			}

			SftpUtil.quitSftpConnection(sftp);

			LOG.info("上传虚拟机Vnc配置成功，准备删除文件！" + entityCode);
			file.delete();
		} else {
			LOG.error("全局配置表未配置websockify_deploy_host！格式：ip:port:username:path:websockify部署路径"
					+ connectId + "_" + vmCode + ",未向下进行！已return!");
			return;
			// throw new ConsoleException(
			// "全局配置表未配置websockify_deploy_host！格式：ip:port:username:path:websockify部署路径"
			// + connectId + "_" + vmCode + ",未向下进行！已return!");
		}

		LOG.info("结束处理虚拟机Vnc配置！" + connectId + "_" + vmCode);
	}

	/**
	 * 
	 * @Title: getHostFreePort
	 * @Description: 获取端口号
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-31 下午5:25:40
	 */
	private int getHostFreePort(String connectId, String hostCode) throws SQLException,
			ConsoleException {
		int port = -1;
		/*
		 * 更新vnc端口表数据状态
		 */
		TbVncPortVO portVO = new TbVncPortVO();
		portVO.setConnect_id(connectId);
		portVO.setHost_code(hostCode);
		// portVO.setHost_ip("");
		portVO.setIfused("0");// 未使用
		List<TbVncPortVO> portList = vncPortDao.queryForListByObj(portVO);
		if (portList != null && portList.size() > 0) {
			TbVncPortVO unUsedPort = portList.get(0);
			unUsedPort.setIfused("1");
			// 更新端口状态
			vncPortDao.updateByObj(unUsedPort);
			port = Integer.parseInt(unUsedPort.getPort());
		} else {
			LOG.error("宿主机（" + hostCode + "@" + connectId + "）端口已全部被使用或未配置端口" + connectId
					+ ",向下进行！未处理return!");
			// throw new ConsoleException("宿主机（" + hostCode + "@" + connectId +
			// "）端口已全部被使用或未配置端口"
			// + connectId + ",未向下进行！已return!");
		}
		return port;
	}

	/**
	 * 
	 * @Title: updateVncPostByVm
	 * @Description: 创建虚拟机成功后更新端口关联虚拟机状态
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-31 下午5:51:54
	 */
	private void updateVncPostByVm(String connectId, String hostCode, String vmCode, int vncport)
			throws SQLException {
		TbVncPortVO portVO = new TbVncPortVO();
		portVO.setConnect_id(connectId);
		portVO.setHost_code(hostCode);
		portVO.setVmcode(vmCode);
		portVO.setPort(vncport + "");
		vncPortDao.updateByObj(portVO);
	}

	/**
	 * 
	 * @Title: releaseVncPostByVm
	 * @Description: 释放Vnc端口
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-31 下午9:45:37
	 */
	private void releaseVncPostByVm(String connectId, String hostCode, int vncport)
			throws SQLException {
		TbVncPortVO portVO = new TbVncPortVO();
		portVO.setConnect_id(connectId);
		portVO.setHost_code(hostCode);
		portVO.setVmcode("");
		portVO.setPort(vncport + "");
		vncPortDao.releaseVncPost(portVO);
	}

	/**
	 * 
	 * @Title: deleteVmVncConfig
	 * @Description: 虚拟机删除后，处理VNC配置文件
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-9-16 上午11:20:46
	 */
	public void deleteVmVncConfig(String connectId, String vmCode) throws SQLException {
		String entityCode = connectId + "_" + vmCode;
		LOG.info("开始删除虚拟机Vnc配置！" + entityCode);
		TbGlobalConfigObj param = new TbGlobalConfigObj();
		param.setKEY("websockify_deploy_host");
		param = tbGlobalConfigDao.queryByObj(param);
		if (param != null) {
			/*
			 * 删除文件
			 */
			LOG.info("开始删除虚拟机Vnc配置！" + entityCode);
			String[] hostParams = param.getVALUE().split(":");
			String host = hostParams[0];
			String port = hostParams[1];
			String username = hostParams[2];
			String password = DES3.decrypt(hostParams[3]);
			String filepath = hostParams[4];

			SshConnection sshConnection = SSHUtil.getSshConnection(false, host,
					Integer.parseInt(port), username, password);
			SSHUtil.sendShellToSSHChannel(sshConnection, true, "rm -rf " + filepath + "/"
					+ entityCode.replace(".", "_"));
			SSHUtil.closed(sshConnection);
			LOG.info("删除虚拟机Vnc配置成功！" + entityCode);
		} else {
			LOG.error("全局配置表未配置websockify_deploy_host！格式：ip:port:username:path:websockify部署路径"
					+ connectId + "_" + vmCode + ",未向下进行！已return!");
			return;
			// throw new ConsoleException(
			// "全局配置表未配置websockify_deploy_host！格式：ip:port:username:path:websockify部署路径"
			// + connectId + "_" + vmCode + ",未向下进行！已return!");
		}

		LOG.info("结束删除虚拟机Vnc配置！" + connectId + "_" + vmCode);
	}

	/**
	 * 
	 * @Title: dealVmDatastoreRelation
	 * @Description: 处理虚拟机与数据存储关联关系
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-9-16 上午11:40:25
	 */
	public void dealVmDatastoreRelation(VirtualMachineUnitedVO vo, String vtype) {
		List<DatastoreUnitedVO> datastores = vo.getDatastores();
		if (datastores != null && datastores.size() > 0) {
			for (DatastoreUnitedVO datastore : datastores) {
				String datastore_uuid = datastore.getDatastoreCode();
				String host_uuid = vo.getHostCode();
				String vm_uuid = vo.getVmCode();
				String connectid = vo.getConnectCode();
				VmDatastoreRelationObj obj = new VmDatastoreRelationObj();
				obj.setConnectid(connectid);
				obj.setDatastore_uuid(datastore_uuid);
				obj.setHost_uuid(host_uuid);
				obj.setVm_uuid(vm_uuid);
				obj.setVtype(vtype);

				try {
					vmDatastoreRelationDao.insertByObj(obj);
				} catch (SQLException e) {
					LOG.error("插入虚拟机与数据存储关系失败！" + e.getMessage(), e);
				}

			}
		}
	}

	/**
	 * 
	 * @Title: deleteVmDatastoreRelation
	 * @Description: 删除虚拟机与数据存储关联关系
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-9-16 上午11:40:25
	 */
	public void deleteVmDatastoreRelation(String connectId, String vmCode, String vtype) {
		VmDatastoreRelationObj obj = new VmDatastoreRelationObj();
		obj.setConnectid(connectId);
		obj.setVm_uuid(vmCode);
		obj.setVtype(vtype);
		try {
			vmDatastoreRelationDao.deleteByObj(obj);
		} catch (SQLException e) {
			LOG.error("删除虚拟机与数据存储关系失败！" + e.getMessage(), e);
		}
	}
}
