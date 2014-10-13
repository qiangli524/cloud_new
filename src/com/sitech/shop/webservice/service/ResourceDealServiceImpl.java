package com.sitech.shop.webservice.service;

import ip.IpType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import publiccloud.MaintenanceOrderConstant;
import publiccloud.ObtainResourceWayConstant;
import publiccloud.OperateSystemLogInfo;
import publiccloud.OperateSystemType;
import publiccloud.PublicCloudPowerSatus;
import publiccloud.QuotaConstant;
import publiccloud.ServiceStatus;
import publiccloud.SystemIpAndPassword;
import publiccloud.UserOperType;
import publiccloud.UserType;
import publiccloud.VirtualDiskStatus;
import publiccloud.VirtualEthernetConstant;
import publiccloud.VirtualMachineSellType;
import publiccloud.VlanType;

import com.sitech.basd.exception.ConsoleException;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.resource.util.VirtualClient;
import com.sitech.basd.sxcloud.cloud.dao.globalconfig.TbGlobalConfigDao;
import com.sitech.basd.sxcloud.cloud.dao.net.TbIpDao;
import com.sitech.basd.sxcloud.cloud.dao.net.TbNetDao;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.basd.yicloud.dao.vmauthority.VmAuthorityDao;
import com.sitech.basd.yicloud.domain.vmauthority.VmAuthorityObj;
import com.sitech.shop.dao.balance.LoadBalanceDao;
import com.sitech.shop.dao.broadband.AclNumberDao;
import com.sitech.shop.dao.broadband.RuleNumberDao;
import com.sitech.shop.dao.disk.VirtualDiskDao;
import com.sitech.shop.dao.disk.VmDiskRelationDao;
import com.sitech.shop.dao.publicIP.PublicIPDao;
import com.sitech.shop.dao.vlan.PhysicalVlanDao;
import com.sitech.shop.dao.vm.VirtualMachineDao;
import com.sitech.shop.domain.balance.LoadBalanceObj;
import com.sitech.shop.domain.disk.VirtualDiskObj;
import com.sitech.shop.domain.disk.VmDiskRelationObj;
import com.sitech.shop.domain.ip.PublicIPObj;
import com.sitech.shop.domain.vlan.PhysicalVlanObj;
import com.sitech.ssd.ah.nas.dao.NasFileSysDao;
import com.sitech.ssd.ah.nas.domain.VmIpObj;
import com.sitech.ssd.billing.vo.resourceInfo.VmInfo;
import com.sitech.utils.common.IncreaseNumberUtil;
import com.sitech.utils.encrypt.DoubleEncryptUtils;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.publicShop.PsexecOperator;
import com.sitech.utils.publicShop.PublicCloudUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.vo.united.ResultSet;
import com.sitech.vo.united.VirtualDiskType;
import com.sitech.vo.united.VirtualDiskUnitedVO;
import com.sitech.vo.united.VirtualEthernetCardUnitedVO;
import com.sitech.vo.united.VirtualMachinePowerStateUnitedVO;
import com.sitech.vo.united.VirtualMachinePowerStatus;
import com.sitech.vo.united.VirtualMachineUnitedVO;
import com.sitech.vo.util.UnitedConstant;
import com.sitech.vo.util.UnitedRestURI;
import com.sitech.vo.util.VirtualConstant;

@Service("resourceDealService")
public class ResourceDealServiceImpl implements ResourceDealService {
	Logger logger = LoggerFactory.getLogger(ResourceDealServiceImpl.class);
	@Autowired
	private VMHostDao vmHostDao;
	@Autowired
	private VirtualMachineDao virtualMachineDao;
	@Autowired
	private VmAuthorityDao vmAuthorityDao;
	@Autowired
	private VirtualDiskDao virtualDiskDao;
	@Autowired
	private VmDiskRelationDao vmDiskRelationDao;
	@Autowired
	private PublicIPDao publicIPDao;
	@Autowired
	private TbNetDao tbNetDao;
	@Autowired
	private TbIpDao tbIpDao;
	@Autowired
	private PhysicalVlanDao physicalVlanDao;
	@Autowired
	private UnitedTreeService unitedTreeService;
	@Autowired
	private NasFileSysDao nasFileSysDao;
	@Autowired
	private LoadBalanceDao loadBalanceDao;
	@Autowired
	private AclNumberDao aclNumberDao;
	@Autowired
	private RuleNumberDao ruleNumberDao;
	@Autowired
	private PublicCloudOrderUtil publicCloudOrderUtil;
	@Autowired
	private PropertyUtil bjShellProp;
	@Autowired
	private TbGlobalConfigDao tbGlobalConfigDao;

	private VMHostObj vmHostObj;
	private VmInfo vmi;

	/**
	 * @param
	 * @return VmInfo
	 * @throws
	 * @throws Exception
	 * @Title: applyVM
	 * @Description: 申请虚拟机
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-6 上午9:24:27
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public synchronized VmInfo applyVMProcess(VmInfo info) throws Exception {
		logger.info("执行购买虚拟机流程处理方法：开始");
		logger.debug("执行购买虚拟机流程处理方法，传入的数据为：" + JacksonUtil.formatJson(JacksonUtil.toJson(info)));
		try {
			vmi = info.clone();
			// 1.检查资源是否可用
			boolean isEnough;
			isEnough = resourceCheck(info);
			logger.debug("资源检查结果：" + isEnough);
			if (!isEnough) {
				releaseVM(vmHostObj);
				logger.error("ERROR：资源不足，orderId:" + info.getOrderId());
				throw new Exception("ERROR：资源不足");
			}

			// 3.修改虚拟机的密码
			boolean change_result = false;

			if (OperateSystemType.windows.equals(info.getOs_type())) {
				vmi.setLoginName(OperateSystemLogInfo.windows_username);
				vmi.setLoginPassword(DoubleEncryptUtils.decrypt(vmHostObj.getVH_PASS()));
				change_result = true;
			} else if (OperateSystemType.linux.equals(info.getOs_type())) {
				SystemIpAndPassword sys = generateSystemIpAndPassword(null, vmHostObj.getVH_UUID(),
						vmHostObj.getConnectId(), VlanType.inner_vlan);
				sys.setUser(OperateSystemLogInfo.linux_username);
				sys.setPass(OperateSystemLogInfo.linux_password);
				sys.setOldPass(OperateSystemLogInfo.linux_password);
				sys.setNewPass(PublicCloudUtil.getPassword(8));
				boolean changepassword_result = false;
				logger.debug("修改密码的对象信息:" + JacksonUtil.formatJson(JacksonUtil.toJson(sys)));
				changepassword_result = changePassword(info.getOs_type(), sys);
				if (changepassword_result) {
					vmHostObj.setVH_USER(DoubleEncryptUtils.encrypt(sys.getUser()));
					vmHostObj.setVH_PASS(DoubleEncryptUtils.encrypt(sys.getNewPass()));
					vmi.setLoginName(sys.getUser());
					vmi.setLoginPassword(sys.getNewPass());
					change_result = true;
				}
			}

			if (!change_result) {
				publicCloudOrderUtil.startOrder(info.getUser_id(), info.getOrderId(),
						MaintenanceOrderConstant.changePassword_vm_fail,
						MaintenanceOrderConstant.apply_vm_question,
						MaintenanceOrderConstant.password_advice);
				logger.error("ERROR：修改密码失败，orderId:" + info.getOrderId() + "虚拟机标识： "
						+ vmHostObj.getVH_UUID());
				throw new Exception("ERROR：修改密码失败，orderId:" + info.getOrderId() + "虚拟机标识： "
						+ vmHostObj.getVH_UUID());
			}

			// 3.先关闭虚拟机电源
			boolean poweroff_result = this.putVmPowerState(vmHostObj,
					VirtualMachinePowerStatus.powerOff);
			if (!poweroff_result) {
				logger.error("关闭虚拟机电源失败，orderId:" + info.getOrderId() + "虚拟机标识： "
						+ vmHostObj.getVH_UUID());
				throw new Exception("关闭虚拟机电源失败，orderId:" + info.getOrderId() + "虚拟机标识： "
						+ vmHostObj.getVH_UUID());
			}
			// 2.编辑虚拟机
			boolean config_result = false;
			config_result = reconfigVM(info, vmHostObj);
			logger.debug("重新配置虚拟机结果：" + config_result);
			if (!config_result) {
				publicCloudOrderUtil.startOrder(info.getUser_id(), info.getOrderId(),
						MaintenanceOrderConstant.vm_shortcut,
						MaintenanceOrderConstant.apply_vm_question,
						MaintenanceOrderConstant.vm_advice + info.getOper_system());
				logger.error("编辑虚拟机设置失败，orderId:" + info.getOrderId() + "虚拟机标识： "
						+ vmHostObj.getVH_UUID());
				throw new Exception("编辑虚拟机设置失败，orderId:" + info.getOrderId() + "虚拟机标识： "
						+ vmHostObj.getVH_UUID());
			}
			boolean powerOn_result = this.putVmPowerState(vmHostObj,
					VirtualMachinePowerStatus.powerOn);
			if (!powerOn_result) {
				logger.error("开启虚拟机电源失败，orderId:" + info.getOrderId() + "虚拟机标识： "
						+ vmHostObj.getVH_UUID());
				throw new Exception("开启虚拟机电源失败，orderId:" + info.getOrderId() + "虚拟机标识： "
						+ vmHostObj.getVH_UUID());
			}

			/*
			 * // 虚拟机加入网络
			 * 
			 * boolean inner_join = false; boolean public_join = false;
			 * PhysicalVlanObj obj1 = getVlanInfo(info.getUser_id(),
			 * VlanType.inner_vlan); inner_join = joinNetwork(vmHostObj, obj1);
			 * if (!inner_join) { throw new Exception("虚拟机加入内网vlan:失败,vlan_id为："
			 * + obj1.getVlan_id()); } PhysicalVlanObj obj2 =
			 * getVlanInfo(info.getUser_id(), VlanType.public_vlan); public_join
			 * = joinNetwork(vmHostObj, obj2); if (!public_join) { throw new
			 * Exception("虚拟机加入公网vlan:失败,vlan_id为：" + obj2.getVlan_id()); }
			 */
			vmi.setIsSuccess(true);

		} catch (Exception e) {
			logger.error("执行购买虚拟机流程处理方法：异常", e);
			throw new Exception(e);
		}
		return vmi;

	}

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Title: allocateRescoure
	 * @Description: 分配资源
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-8-21 上午10:47:25
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void allocateRescoure(VmInfo info) throws Exception {
		logger.info("执行分配资源IP及vlan资源开始：");
		boolean init = info.isIf_Init();
		try {
			if (init) {
				List<TbCloud2IpInfoObj> ipList = checkPublicIP(info);
				if (ipList == null || ipList.size() == 0) {
					publicCloudOrderUtil.startOrder(info.getUser_id(), info.getOrderId(),
							MaintenanceOrderConstant.ip_shortcut,
							MaintenanceOrderConstant.apply_vm_question,
							MaintenanceOrderConstant.ip_advice);
					throw new Exception("IP资源不足，请补充公网IP资源，用户类型为：" + info.getPayment_type());
				}
				List<PhysicalVlanObj> vlanList = checkVlan(info);
				if (vlanList != null && vlanList.size() > 0) {
					allocatePublicIp(ipList, info, ObtainResourceWayConstant.give);
					allocateVlan(vlanList, info, ObtainResourceWayConstant.give);
				} else {
					publicCloudOrderUtil.startOrder(info.getUser_id(), info.getOrderId(),
							MaintenanceOrderConstant.vlan_shortcut,
							MaintenanceOrderConstant.apply_vm_question,
							MaintenanceOrderConstant.vlan_advice);
					throw new Exception("vlan资源不足，请补充vlan资源，用户类型为：" + info.getPayment_type());
				}
			}
		} catch (Exception e) {
			logger.error("分配资源IP及vlan资源异常：", e);
			throw new Exception(e);
		}
	}

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Title: dealReconfigResult
	 * @Description: 处理调用编辑虚拟机接口后返回的结果
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-12 下午2:42:06
	 */
	public boolean reconfigVM(VmInfo info, VMHostObj vmHost) throws Exception {
		logger.info("执行重新配置虚拟机方法：开始");
		logger.debug("执行重新配置虚拟机方法传入的VmInfo数据为：" + JacksonUtil.formatJson(JacksonUtil.toJson(info)));
		logger.debug("执行重新配置虚拟机方法传入的VmHost数据为："
				+ JacksonUtil.formatJson(JacksonUtil.toJson(vmHost)));
		boolean config_result = false;
		try {
			VirtualMachineUnitedVO vir = new VirtualMachineUnitedVO();
			vir.setVmdkList(info.getVmdkList());
			vir.setNumCPUs(info.getCpu());
			vir.setMemoryMB(info.getMemoryInMb());// 单位为M
			vir.setVmCode(vmHost.getVH_UUID());
			vir.setConnectCode(vmHost.getConnectId()); // 调用接口来编辑虚拟机
			String url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/adjust";
			VirtualMachineUnitedVO edit = VirtualClient.put(url, vir,
					new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
					});
			if (edit.getIsSuccess()) {
				config_result = dealReconfigResult(info, vmHost, edit);
			} else {
				logger.error("执行重新配置虚拟机方法：失败");
				logger.debug("执行重新配置虚拟机方法，返回的结果为：" + config_result);
				logger.info("执行重新配置虚拟机方法：结束");
				throw new Exception("编辑虚拟机失败，需要回滚");
			}
		} catch (Exception e) {
			logger.info("执行重新配置虚拟机方法：失败", e);
			logger.debug("执行重新配置虚拟机方法，返回的结果为：" + config_result);
		}
		return config_result;
	}

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Title: dealReconfigResult
	 * @Description: 处理编辑虚拟机成功后的数据
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-18 下午3:06:09
	 */
	public boolean dealReconfigResult(VmInfo info, VMHostObj host, VirtualMachineUnitedVO vo)
			throws Exception {
		logger.info("执行处理重新配置虚拟机后，处理相应返回结果方法：开始");
		logger.debug("执行处理重新配置虚拟机后，处理相应返回结果方法，传入的VmInfo参数为："
				+ JacksonUtil.formatJson(JacksonUtil.toJson(info)));
		logger.debug("执行处理重新配置虚拟机后，处理相应返回结果方法，传入的VMHostObj参数为："
				+ JacksonUtil.formatJson(JacksonUtil.toJson(host)));
		logger.debug("执行处理重新配置虚拟机后，处理相应返回结果方法，传入的VirtualMachineUnitedVO参数为："
				+ JacksonUtil.formatJson(JacksonUtil.toJson(vo)));
		// 更新虚拟机表
		boolean result = false;
		try {
			VMHostObj vmHostObj = new VMHostObj();
			if (info.getCpu() != null) {
				vmHostObj.setVH_CPU(info.getCpu() + "");
			}
			if (info.getMemoryInMb() != null) {
				vmHostObj.setVH_MEM(info.getMemoryInMb() + "");
			}

			vmHostObj.setVH_STORAGE(vo.getStorageSizeInMb() + "");
			vmHostObj.setOs_type(info.getOs_type());// 操作系统类型
			vmHostObj.setVH_UUID(host.getVH_UUID());
			vmHostObj.setConnectId(host.getConnectId());
			// TODO 加入操作类型（需要删除）
			// info.setOper_type(UserOperType.buy_vm);
			if (UserOperType.buy_vm.equals(info.getOper_type())) {// 只有在购买的时候，才更新以下信息
				// 生成虚拟机的别名
				String alias = IncreaseNumberUtil.getSerialNumber("vmAlias.dat", 5, "yyyyMMdd");
				vmHostObj.setVH_DESC("云主机" + alias);
				vmHostObj.setVH_CID("BT" + alias);
				vmHostObj.setVH_STATUS(info.getDuration_unit());
				vmHostObj.setVH_STAT(PublicCloudPowerSatus.powerOn);// 虚拟机开始状态
				vmHostObj.setVH_USER(host.getVH_USER());
				vmHostObj.setVH_PASS(host.getVH_PASS());
				vmi.setVmName(alias);
			} else {
				vmHostObj.setVH_DESC("");
				vmHostObj.setVH_STATUS(info.getDuration_unit());
			}

			virtualMachineDao.updateVMHostObj(vmHostObj);

			// 构造磁盘list,批量插入
			List<VirtualDiskUnitedVO> diskList = vo.getVmdkList();
			List<VirtualDiskUnitedVO> resultDisk = new ArrayList<VirtualDiskUnitedVO>();
			List<VirtualDiskObj> disks = new ArrayList<VirtualDiskObj>();
			List<VmDiskRelationObj> relations = new ArrayList<VmDiskRelationObj>();
			// 通过虚拟机查询对应的数据中心节点信息
			UnitedTreeObj tree = new UnitedTreeObj();
			tree.setConnect_id(host.getConnectId());
			tree.setUuid(host.getVH_UUID());
			tree = unitedTreeService.queryByObj(tree);
			tree = unitedTreeService.getUnitedTreeObj(tree.getId(), UnitedConstant.DATACENTER, 0);
			List<VirtualDiskUnitedVO> interList = new ArrayList<VirtualDiskUnitedVO>();
			w: for (int i = 0; i < diskList.size(); i++) {
				VirtualDiskUnitedVO virt = diskList.get(i);
				// 插入虚拟磁盘表
				if (!VirtualDiskType.system_disk_label.equals(virt.getLabelName())) {
					String disk_id = RandomUUID.getUuid();
					VirtualDiskObj obj = new VirtualDiskObj();
					obj.setConnectCode(host.getConnectId());
					obj.setPath(virt.getVmdkPath());
					obj.setDatacenterCode(tree.getUuid());
					obj.setDatastoreCode(virt.getDatastoreCode());
					Integer exsit = virtualDiskDao.queryForCount(obj);
					if (exsit == 0) {
						obj.setId(disk_id);
						obj.setCapacityInMB(virt.getCapacityInMB() * 1.0);
						String alais = IncreaseNumberUtil.getSerialNumber("diskAlias.dat", 5,
								"yyyyMMdd");
						obj.setName("磁盘" + alais);
						obj.setLabel(virt.getLabelName());
						obj.setUser_id(info.getUser_id());
						obj.setDisk_type(VirtualDiskType.data_disk);// 数据盘
						obj.setState(VirtualDiskStatus.mount);
						// 服务状态
						obj.setService_status(ServiceStatus.SERVICE);
						disks.add(obj);
						// 构造主机磁盘关系
						VmDiskRelationObj relation = new VmDiskRelationObj();
						relation.setConnect_id(host.getConnectId());
						relation.setVm_uuid(host.getVH_UUID());
						relation.setVirdisk_id(disk_id);
						relation.setId(RandomUUID.getUuid());
						relations.add(relation);
						virt.setVmdk_id(disk_id);
						if (info.getVmdkList() != null && info.getVmdkList().size() > 0) {

							for (VirtualDiskUnitedVO vm_disk : info.getVmdkList()) {
								if (virt.getCapacityInMB().longValue() == vm_disk.getCapacityInMB()
										.longValue()) {
									if (!interList.contains(vm_disk)) {
										virt.setVmdkProduceInstaceId(vm_disk
												.getVmdkProduceInstaceId());
										interList.add(vm_disk);
										resultDisk.add(virt);
										continue w;
									}

								}
							}
						}

					}

				}

			}
			vmi.setVmdkList(resultDisk);
			virtualDiskDao.insertForBatch(disks);
			vmDiskRelationDao.insertForBatch(relations);
			result = true;

		} catch (Exception e) {
			logger.error("执行处理重新配置虚拟机后，处理相应返回结果方法：异常", e);
			throw new Exception(e);
		}
		logger.debug("执行处理重新配置虚拟机后，处理相应返回结果方法,返回值为：" + result);
		logger.info("执行处理重新配置虚拟机后，处理相应返回结果方法：结束");
		return result;
	}

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Title: changeIp
	 * @Description: 修改虚拟机的IP地址
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-14 上午11:37:09
	 */
	public boolean changeIp(String system_type, SystemIpAndPassword sys) throws Exception {
		logger.info("执行修改虚拟机IP地址方法：开始");
		logger.debug("执行修改虚拟机IP地址方法，传入的操作系统类型为：" + system_type);
		logger.debug("执行修改虚拟机IP地址方法，传入的SystemIpAndPassword为："
				+ JacksonUtil.formatJson(JacksonUtil.toJson(sys)));
		boolean change_result = false;
		PsexecOperator px = new PsexecOperator();
		// 根据不同的操作系统类型需要调用不同的方法
		if (OperateSystemType.linux.equals(system_type)) {
			// 调用linux改脚本的方法
			change_result = px.psexecForLinuxCIP(sys);
		} else if (OperateSystemType.windows.equals(system_type)) {
			// 调用window改IP及用户名的方法
			change_result = px.psexecForWinCIP(sys);
		}
		if (!change_result) {
			throw new Exception("修改虚拟机IP地址失败，回滚之前所有操作");
		}
		logger.debug("执行修改虚拟机IP地址方法,返回结果为：" + change_result);
		logger.info("执行修改虚拟机IP地址方法：结束");
		return change_result;

	}

	/**
	 * @param
	 * @return String
	 * @throws
	 * @Title: changePassword
	 * @Description: 修改虚拟机密码
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-19 上午10:56:17
	 */
	public boolean changePassword(String system_type, SystemIpAndPassword sys) throws Exception {
		logger.info("执行修改虚拟机密码方法：开始");
		logger.debug("执行修改虚拟机密码方法，传入的操作系统类型为：" + system_type);
		logger.debug("执行修改虚拟机密码方法，传入的SystemIpAndPassword为："
				+ JacksonUtil.formatJson(JacksonUtil.toJson(sys)));
		boolean change_result = false;
		try {
			PsexecOperator px = new PsexecOperator();
			// 根据不同的操作系统类型需要调用不同的方法
			if (OperateSystemType.linux.equals(system_type)) {
				change_result = px.psexecForLinuxCPass(sys);
			} else if (OperateSystemType.windows.equals(system_type)) {
				// change_result = px.psexecForWinCPass(sys);
			}
			if (!change_result) {
				logger.debug("执行修改虚拟机密码方法,返回结果为：" + change_result);
				logger.info("执行修改虚拟机密码方法：结束");
				throw new Exception("修改虚拟机密码失败，回滚之前所有操作");
			}
			logger.debug("执行修改虚拟机密码方法,返回结果为：" + change_result);
			logger.info("执行修改虚拟机密码方法：结束");
		} catch (Exception e) {
			logger.error("执行修改虚拟机密码方法：失败");
			logger.debug("执行修改虚拟机密码方法,返回结果为：" + change_result);
			logger.info("执行修改虚拟机密码方法：结束");
			throw new Exception(e);
		}
		return change_result;

	}

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Title: changeIPAndpassword
	 * @Description: 生成虚拟机的IP地址操作类, vlan_id, vlan_type可以根据需要传递，如果不需要传为null
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-14 上午9:58:00
	 */
	public SystemIpAndPassword generateSystemIpAndPassword(String vlan_id, String vmCode,
			String connectCode, String vlan_type) throws Exception {
		logger.info("执行生成虚拟机IP地址密码操作类:开始");
		SystemIpAndPassword oper = new SystemIpAndPassword();
		try {

			// 1.根据虚拟机标示，获取虚拟机对应的IP地址列表
			VmIpObj ip = new VmIpObj();
			ip.setVm_uuid(vmCode);
			ip.setConnectid(connectCode);
			List<VmIpObj> ipsList = virtualMachineDao.queryIpListByVm(ip);
			if (ipsList != null && ipsList.size() > 0) {
				for (VmIpObj v : ipsList) {
					if (VlanType.inner_vlan.equals(vlan_type)) {// 要修改的是网卡内网vlan的IP地址
						if (IpType.intranet_ip.equals(v.getIpType())) {// 通过用于出公网的IP地址链接
							oper.setIp(v.getIp());// 用于做NAt转换的内网IP
							break;
						}
					} else {// 要修改的是网卡出公网vlan的IP地址
						if (VlanType.public_vlan.equals(vlan_type)) {// 通过用于出公网的IP地址链接
							if (IpType.inner_ip.equals(v.getIpType())) {
								oper.setIp(v.getIp());// 用于做NAt转换的内网IP
								break;
							}

						}
					}

				}
			}
			// 2.根据vlan查找该vlan下对应的闲置的内网IP地址
			if (vlan_id != null && !"".equals(vlan_id)) {
				TbCloud2NetInfoObj net = new TbCloud2NetInfoObj();
				net.setVLAN_ID(vlan_id);
				net = tbNetDao.queryByObj(net);
				TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
				ipObj.setISUSED(QuotaConstant.not_used);
				ipObj.setIP_TYPE(IpType.inner_ip);
				ipObj.setNET_ID(net.getNET_ID());
				ipObj = tbIpDao.queryByObj(ipObj);
				oper.setGateway(net.getGATEWAY1());
				oper.setNewIp(ipObj.getIPADDRESS());
				oper.setSubnetMask(net.getSUBNET());
			}
		} catch (Exception e) {
			logger.info("执行生成虚拟机IP地址密码操作类:异常");
			logger.info("执行生成虚拟机IP地址密码操作类:结束");
			throw new Exception(e);
		}

		return oper;

	}

	/**
	 * @param
	 * @return boolean
	 * @throws
	 * @Title: allocateVlan
	 * @Description: 给用户分配vlan
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-19 下午4:04:45
	 */
	public void allocateVlan(List<PhysicalVlanObj> vlanList, VmInfo info, String obtain_way)
			throws Exception {
		try {

			logger.info("执行分配Vlan方法：开始");
			logger.debug("执行分配Vlan方法，传入的vlanList为："
					+ JacksonUtil.formatJson(JacksonUtil.toJson(vlanList)));
			logger.debug("执行分配Vlan方法，传入的VmInfo为："
					+ JacksonUtil.formatJson(JacksonUtil.toJson(info)));
			logger.debug("执行分配Vlan方法，传入的obtain_way为：" + obtain_way);
			boolean init = info.isIf_Init();
			List<PhysicalVlanObj> resultList = new ArrayList<PhysicalVlanObj>();
			if (init) {
				for (PhysicalVlanObj v : vlanList) {
					v.setUser_id(info.getUser_id());
					v.setArea_id(info.getArea_id());
					v.setObtain_way(obtain_way);
					v.setPayment_type(info.getPayment_type());
					resultList.add(v);
				}
				physicalVlanDao.updateForBatch(resultList);
				logger.info("执行分配Vlan方法：结束");
			}
		} catch (Exception e) {
			logger.info("执行分配Vlan方法：异常", e);
			logger.info("执行分配Vlan方法：结束");
			throw new Exception(e);
		}

	}

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Title: allocatePublicIp
	 * @Description: 给用户分配公网IP
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-21 下午1:42:32
	 */
	public void allocatePublicIp(List<TbCloud2IpInfoObj> ipList, VmInfo info, String obtain_way)
			throws Exception {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			logger.info("执行分配公网IP地址方法：开始");
			logger.debug("执行分配公网IP地址方法，传入的ipList为："
					+ JacksonUtil.formatJson(JacksonUtil.toJson(ipList)));
			logger.debug("执行分配公网IP地址方法，传入的VmInfo为："
					+ JacksonUtil.formatJson(JacksonUtil.toJson(info)));
			logger.debug("执行分配公网IP地址方法，传入的obtain_way为：" + obtain_way);
			List<PublicIPObj> pubList = new ArrayList<PublicIPObj>();
			List<TbCloud2IpInfoObj> ip_list = new ArrayList<TbCloud2IpInfoObj>();
			// 将IP地址预先占用
			for (TbCloud2IpInfoObj ip : ipList) {
				PublicIPObj p = new PublicIPObj();
				p.setIpaddress(ip.getIPADDRESS());
				p.setStatus(0);
				p.setRegion_id(info.getArea_id());
				p.setUser_id(info.getUser_id());
				p.setArea_id(info.getArea_id());
				p.setObtain_way(obtain_way);
				p.setPayment_type(info.getPayment_type());
				if (ObtainResourceWayConstant.give.equals(obtain_way)) {
					p.setCreate_time(format.format(new Date()));
				}
				pubList.add(p);
				ip.setISUSED(QuotaConstant.is_used);
				ip_list.add(ip);

			}
			tbIpDao.updateIPStateForBatch(ip_list);
			publicIPDao.insertForBatch(pubList);
			logger.info("执行分配公网IP地址方法：结束");
		} catch (Exception e) {
			logger.info("执行分配公网IP地址方法：异常");
			logger.info("执行分配公网IP地址方法：结束");
			throw new Exception(e);
		}

	}

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Title: allocateVM
	 * @Description: 分配虚拟机
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-21 下午4:55:31
	 */
	public void allocateVM(VMHostObj vm, VmInfo info) throws Exception {
		try {

			logger.info("执行为用户分配虚拟机方法：开始");
			logger.debug("执行为用户分配虚拟机方法,传入的VmHostObj参数为："
					+ JacksonUtil.formatJson(JacksonUtil.toJson(vm)));
			logger.debug("执行为用户分配虚拟机方法,传入的VmInfo参数为："
					+ JacksonUtil.formatJson(JacksonUtil.toJson(info)));
			VmAuthorityObj auth = new VmAuthorityObj();
			auth.setCONNECT_ID(vm.getConnectId());
			auth.setENTITY_ID(vm.getVH_UUID());
			auth.setENTITY_TYPE(UnitedConstant.VM);
			auth.setENTITY_NAME(vm.getVH_DESC());
			auth.setUSERID(Integer.parseInt(info.getUser_id()));
			auth.setENTITY_NAME(vm.getVH_NAME());
			vmAuthorityDao.insertByObj(auth);
			logger.info("执行为用户分配虚拟机方法：结束");
		} catch (Exception e) {
			logger.error("执行为用户分配虚拟机方法：异常");
			logger.info("执行为用户分配虚拟机方法：结束");
			throw new Exception(e);
		}

	}

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Title: releaseVM
	 * @Description: 释放虚拟机
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-23 下午3:35:49
	 */
	public void releaseVM(VMHostObj vm) throws Exception {
		try {
			logger.info("执行释放虚拟机方法：开始");
			logger.debug("执行释放虚拟机方法,传入的VmHostObj参数为："
					+ JacksonUtil.formatJson(JacksonUtil.toJson(vm)));
			VmAuthorityObj auth = new VmAuthorityObj();
			auth.setCONNECT_ID(vm.getConnectId());
			auth.setENTITY_ID(vm.getVH_UUID());
			vmAuthorityDao.deleteByObj(auth);
			logger.info("执行释放虚拟机方法：结束");
		} catch (Exception e) {
			logger.error("执行释放虚拟机方法：异常");
			logger.info("执行释放虚拟机方法：结束");
			throw new Exception(e);
		}

	}

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Title: resourceCheck
	 * @Description: 进行资源检查
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-12 下午7:12:10
	 */
	public boolean resourceCheck(VmInfo info) throws Exception {
		logger.info("执行资源检查方法：开始");
		logger.debug("执行资源检查方法，传入的VmInfo参数信息为：" + JacksonUtil.formatJson(JacksonUtil.toJson(info)));
		boolean isEnough = false;
		boolean init = info.if_Init;
		logger.info("查找空闲虚拟机：开始");
		vmHostObj = checkVM(info);
		logger.info("查找空闲虚拟机：结束");
		logger.debug("查找到的空闲的虚拟机信息为：" + JacksonUtil.formatJson(JacksonUtil.toJson(vmHostObj)));
		if (vmHostObj != null) {
			vmi.setVmCode(vmHostObj.getVH_UUID());
			vmi.setConnectCode(vmHostObj.getConnectId());
			allocateVM(vmHostObj, info);
			isEnough = true;
		} else {
			publicCloudOrderUtil.startOrder(info.getUser_id(), info.getOrderId(),
					MaintenanceOrderConstant.vm_shortcut,
					MaintenanceOrderConstant.apply_vm_question, MaintenanceOrderConstant.vm_advice
							+ info.getOper_system());
		}

		return isEnough;

	}

	/**
	 * @param
	 * @return boolean
	 * @throws
	 * @Title: checkVMResource
	 * @Description: 检查虚拟机资源是否可用
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-12 下午7:22:14
	 */
	public VMHostObj checkVM(VmInfo info) throws Exception {
		logger.info("执行检查指定操作系统的未被占用的虚拟机：开始");
		logger.debug("执行检查指定操作系统的未被占用的虚拟机,传入的参数信息为："
				+ JacksonUtil.formatJson(JacksonUtil.toJson(info)));
		String system_name = info.getOper_system();// 暂定是名称
		VMHostObj vm = new VMHostObj();
		vm.setVH_LOG(system_name);
		// TODO测试用虚拟机
		vm.setVH_UNITID(VirtualMachineSellType.TEST_SELL);
		vmHostObj = virtualMachineDao.queryByObj(vm);
		logger.debug("执行检查指定操作系统的未被占用的虚拟机,返回的参数信息为："
				+ JacksonUtil.formatJson(JacksonUtil.toJson(vmHostObj)));
		logger.info("执行检查指定操作系统的未被占用的虚拟机：结束");
		return vmHostObj;
	}

	/**
	 * @param
	 * @return List<PublicIPObj>
	 * @throws
	 * @Title: checkPublicIP
	 * @Description: 检查公网IP资源
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-12 下午7:28:15
	 */
	public List<TbCloud2IpInfoObj> checkPublicIP(VmInfo info) throws Exception {
		logger.info("执行查找未被占用的公网IP地址方法：开始");
		logger.debug("执行查找未被占用的公网IP地址方法,传入的参数信息为："
				+ JacksonUtil.formatJson(JacksonUtil.toJson(info)));
		List<TbCloud2IpInfoObj> ipList = new ArrayList<TbCloud2IpInfoObj>();
		String user_type = info.getUser_Type();
		TbCloud2IpInfoObj ip = new TbCloud2IpInfoObj();
		ip.setIP_TYPE(IpType.public_ip);
		ip.setISUSED(QuotaConstant.not_used);
		List<TbCloud2IpInfoObj> publicIpList = tbIpDao.queryForListByObj(ip);
		if (publicIpList != null && publicIpList.size() > 0) {
			if (UserType.afterpay_user.equals(user_type)
					&& publicIpList.size() >= QuotaConstant.afterPay_ip_num) {
				ipList = publicIpList.subList(0, QuotaConstant.afterPay_ip_num);
			} else if (UserType.prepay_user.equals(user_type)
					&& publicIpList.size() >= QuotaConstant.prePay_ip_num) {
				ipList = publicIpList.subList(0, QuotaConstant.prePay_ip_num);
			} else {
				ipList = publicIpList.subList(0, info.getIp_sum());
			}
		}
		logger.debug("执行查找未被占用的公网IP地址方法,返回的参数信息为："
				+ JacksonUtil.formatJson(JacksonUtil.toJson(ipList)));
		logger.debug("查找到的未被占用的公网IP地址的个数为：", ipList == null ? 0 : ipList.size());
		logger.info("执行查找未被占用的公网IP地址方法：结束");
		return ipList;

	}

	/**
	 * @param
	 * @return List<PhysicalVlanObj>
	 * @throws
	 * @throws Exception
	 * @Title: checkVlan
	 * @Description: 检查vlan资源
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-13 下午1:12:23
	 */
	public List<PhysicalVlanObj> checkVlan(VmInfo info) throws Exception {
		logger.info("执行查找未被占用的VLAN方法：开始");
		logger.debug("执行查找未被占用的VLAN方法,传入的参数信息为：" + JacksonUtil.formatJson(JacksonUtil.toJson(info)));
		List<PhysicalVlanObj> vlanList = new ArrayList<PhysicalVlanObj>();
		String user_type = info.getUser_Type();
		PhysicalVlanObj vlan1 = new PhysicalVlanObj();
		vlan1.setVlan_type(VlanType.public_vlan);
		vlan1.setArea_id(info.getArea_id());
		if (UserType.afterpay_user.equals(user_type)) {
			vlan1.setService_type(UserType.afterpay_user);
		} else {
			vlan1.setService_type(UserType.prepay_user);
		}
		List<PhysicalVlanObj> public_list = physicalVlanDao.queryNoAssignForListByObj(vlan1);
		// 查询内网vlan
		vlan1.setVlan_type(VlanType.inner_vlan);
		List<PhysicalVlanObj> inner_list = physicalVlanDao.queryNoAssignForListByObj(vlan1);
		if (UserType.prepay_user.equals(user_type)) {// 预付费用户，一个公网vlan,一个内网vlan
			vlanList.addAll(public_list.subList(0, QuotaConstant.public_vlan_num));
			vlanList.addAll(inner_list.subList(0, QuotaConstant.prePay_vlan_num));
		} else if (UserType.afterpay_user.equals(user_type)) {// 后付费用户，一个公网vlan,2个内网vlan
			vlanList.addAll(public_list.subList(0, QuotaConstant.public_vlan_num));
			vlanList.addAll(inner_list.subList(0, QuotaConstant.afterPay_vlan_num));
		}

		logger.debug("执行查找未被占用的VLAN方法,返回的vlanList信息为："
				+ JacksonUtil.formatJson(JacksonUtil.toJson(vlanList)));
		logger.debug("查找到的未被占用的VLAN的个数为：", +(vlanList == null ? 0 : vlanList.size()));
		logger.info("执行查找未被占用的VLAN方法：结束");
		return vlanList;

	}

	/**
	 * @param
	 * @return VMInfo
	 * @throws
	 * @Title: editVM
	 * @Description: 更改虚拟机配置
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-6 上午9:25:28
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public synchronized VmInfo editVMProcess(VmInfo info) throws Exception {
		boolean config_result = false;
		logger.info("执行更改虚拟机配置流程处理方法：开始");
		logger.debug("执行更改虚拟机配置流程处理方法,传入的参数信息为：" + JacksonUtil.formatJson(JacksonUtil.toJson(info)));
		try {
			vmi = info.clone();
			VMHostObj vmHost = new VMHostObj();
			vmHost.setVH_UUID(info.getVmCode());
			vmHost.setConnectId(info.getConnectCode());
			config_result = reconfigVM(info, vmHost);
			if (config_result) {//
				vmi.setIsSuccess(true);
				logger.info("执行配置虚拟机流程处理方法：结束");
				logger.debug("执行配置虚拟机流程处理方法结果：" + vmi.getIsSuccess());
				return vmi;
			} else {
				logger.error("执行配置虚拟机流程处理方法：失败");
				throw new Exception();
			}
		} catch (Exception e) {
			logger.error("执行配置虚拟机流程处理方法：异常", e);
			throw new Exception(e);
		}

	}

	/**
	 * 虚拟机延时
	 * 
	 * @param info
	 *            虚拟机信息
	 * @return null
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public synchronized VmInfo delayVMProcess(VmInfo info) throws Exception {
		// 更新虚拟机的到期时间
		return null;
	}

	/**
	 * @param
	 * @return VMInfo
	 * @throws
	 * @Title: editVM
	 * @Description: 修改虚拟机电源状态
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-6 上午9:25:28
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public synchronized VmInfo putVMPowerStateProcess(VmInfo info) throws Exception {
		logger.info("执行更改虚拟机电源状态方法：开始");
		vmi = info.clone();
		boolean result = false;
		try {
			VMHostObj vmHost = new VMHostObj();
			vmHost.setVH_UUID(info.getVmCode());
			String state = info.getStatus();
			VirtualMachinePowerStateUnitedVO vo = new VirtualMachinePowerStateUnitedVO();
			vo.setVmCode(info.getVmCode());
			vo.setConnectCode(info.getConnectCode());
			vo.setVirtualMachinePowerOper(info.getStatus());
			String url = "";
			if (VirtualMachinePowerStatus.DESTROY.equals(state)) {

				url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/destory/"
						+ vo.getConnectCode() + "/" + vo.getVmCode();
				VirtualMachineUnitedVO vir = VirtualClient.delete(url,
						new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
						});
				result = vir.getIsSuccess();
			} else {
				url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/change/powerstate";
				vo = VirtualClient.put(url, vo,
						new JacksonUtil.TypeReference<VirtualMachinePowerStateUnitedVO>() {
						});
				result = vo.getIsSuccess();
			}

			if (result) {
				if (VirtualMachinePowerStatus.DESTROY.equals(state)) {
					// 删除虚拟机信息，虚拟机IP地址信息
					VMHostObj vmObj = new VMHostObj();
					vmObj.setVH_UUID(info.getVmCode());
					vmObj.setConnectId(info.getConnectCode());
					vmHostDao.deleteByObj(vmObj);
					VmIpObj ip = new VmIpObj();
					ip.setConnectid(info.getConnectCode());
					ip.setVm_uuid(info.getVmCode());
					List<VmIpObj> ipList = nasFileSysDao.queryVmIpRelation(ip);
					List<TbCloud2IpInfoObj> ips = new ArrayList<TbCloud2IpInfoObj>();
					for (VmIpObj vm : ipList) {
						TbCloud2IpInfoObj tb = new TbCloud2IpInfoObj();
						tb.setIPADDRESS(vm.getIp());
						tb.setISUSED(QuotaConstant.not_used);
						ips.add(tb);
					}
					nasFileSysDao.delVmIpRelationByObj(ip);
					tbIpDao.updateIPStateForBatch(ips);
					vmi.setIsSuccess(true);
					logger.info("更改虚拟机状态：结束");
					logger.info("此次操作是销毁虚拟机,操作结果为：" + vmi.getIsSuccess());
					return vmi;
				} else {
					// 更新库中信息
					VMHostObj vmObj = new VMHostObj();
					vmObj.setVH_UUID(info.getVmCode());
					vmObj.setConnectId(info.getConnectCode());
					if (VirtualMachinePowerStatus.powerOn.equals(state)) {// 开机
						vmObj.setVH_STAT(PublicCloudPowerSatus.powerOff);
					} else if (VirtualMachinePowerStatus.powerOff.equals(state)) {// 关机
						vmObj.setVH_STAT(PublicCloudPowerSatus.notice_off);
					} else if (VirtualMachinePowerStatus.DESTROY.equals(state)) {
						vmObj.setVH_STAT(PublicCloudPowerSatus.notice_destory);
					}
					vmHostDao.updateVmStateByObj(vmObj);
					vmi.setIsSuccess(true);
					logger.info("更改虚拟机状态：结束");
					logger.debug("此次操作是修改虚拟机电源状态,操作结果为：" + vmi.getIsSuccess());
					return vmi;
				}

			} else {
				logger.info("执行更改虚拟机电源状态方法：结束");
				logger.error("更改虚拟机状态结果：失败");
				throw new Exception("执行更改虚拟机电源状态方法：失败,回滚");
			}
		} catch (Exception e) {
			logger.error("更改虚拟机状态结果：失败");
			logger.info("执行更改虚拟机电源状态方法：结束");
			throw new Exception(e);
		}

	}

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Title: applyPublicIpProcess
	 * @Description: 购买公网IP处理过程
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-3 下午5:08:34
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public synchronized VmInfo applyPublicIPProcess(VmInfo info) throws Exception {
		try {
			logger.info("执行用户购买公网IP地址方法：开始");
			VmInfo vmi = info.clone();
			info.setUser_Type(null);// 标示为用户购买的公网IP地址，非免费分配的
			Integer ip_sum = info.getIp_sum();
			logger.info("查询当前公网IP地址是否足够：开始");
			logger.debug("当前用户购买的公网IP地址的个数是：" + info.getIp_sum() + "个");
			List<TbCloud2IpInfoObj> ipList = checkPublicIP(info);
			logger.info("查询当前公网IP地址是否足够：结束");
			if (ipList.size() == ip_sum) {
				allocatePublicIp(ipList, info, ObtainResourceWayConstant.buy);
				vmi.setInternet_ip(ipList.get(0).getIPADDRESS());
				vmi.setIsSuccess(true);
				logger.info("执行用户购买公网IP地址方法：结束");
				logger.debug("执行用户购买公网IP地址方法结果：" + vmi.getIsSuccess());
				logger.debug("执行用户购买公网IP地址方法，返回的数据为："
						+ JacksonUtil.formatJson(JacksonUtil.toJson(vmi)));
				return vmi;
			} else {
				logger.info("没有足够的公网IP");
				return vmi;
			}

		} catch (Exception e) {
			logger.error("执行用户购买公网IP地址方法：异常", e);
			throw new Exception(e);
		}

	}

	/**
	 * @param
	 * @return VmInfo
	 * @throws
	 * @Title: delayPublicIPProcess
	 * @Description: 公网IP地址延时
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-7 下午4:38:16
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public VmInfo delayPublicIPProcess(VmInfo info) throws Exception {
		return null;
	}

	/**
	 * @param
	 * @return VmInfo
	 * @throws
	 * @Title: delayPublicIPProcess
	 * @Description: 公网带宽延时
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-7 下午4:38:16
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public VmInfo delayBandwidthProcess(VmInfo info) throws Exception {
		return null;
	}

	/**
	 * @param
	 * @return VmInfo
	 * @throws
	 * @Title: applyBalanceProcess
	 * @Description:购买负载均衡
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-7 下午1:13:07
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public VmInfo applyBalanceProcess(VmInfo info) throws Exception {
		logger.info("执行用户购买负载均衡服务方法：开始");
		try {
			vmi = info.clone();
			LoadBalanceObj balance = new LoadBalanceObj();
			String id = "LB" + IncreaseNumberUtil.getSerialNumber("loadAlias.dat", 5, "yyyyMMdd");
			balance.setId(id);
			balance.setName(id);
			balance.setType(info.getLoadType());
			balance.setUser_id(info.getUser_id());
			balance.setArea_id(info.getArea_id());
			logger.debug("负载均衡的信息为:" + JacksonUtil.formatJson(JacksonUtil.toJson(balance)));
			loadBalanceDao.insertByObj(balance);
			vmi.setLoadId(id);
			vmi.setIsSuccess(true);
			logger.debug("执行用户购买负载均衡服务方法结果：" + vmi.getIsSuccess());
			logger.debug("执行用户购买负载均衡服务方法，返回的数据为：" + JacksonUtil.formatJson(JacksonUtil.toJson(vmi)));
			logger.info("执行用户购买负载均衡服务方法：结束");
		} catch (Exception e) {
			logger.error("执行用户购买负载均衡服务方法：异常");
			throw new Exception(e);
		}
		return vmi;

	}

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Title: updateVMTime
	 * @Description: 更新虚拟机开始和到期时间等
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-7 上午11:24:02
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public synchronized VmInfo updateServeTimeProcess(VmInfo info) throws Exception {
		logger.info("更改服务时长,传入的参数为VmInfo：" + JacksonUtil.formatJson(JacksonUtil.toJson(info)));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		vmi = info.clone();
		if (info.getVmProductInstanceId() != null && !"".equals(info.getVmProductInstanceId())) {
			try {
				logger.info("执行更新虚拟机的服务时间方法：开始");
				VMHostObj obj = new VMHostObj();
				obj.setVH_UUID(info.getVmCode());
				obj.setConnectId(info.getConnectCode());
				obj.setVH_STARTED(format.format(info.getService_begin_time()));
				obj.setVH_COMPLETED(format.format(info.getService_end_time()));
				obj.setBUSI_STATUS(ServiceStatus.SERVICE);
				virtualMachineDao.updateVMHostObj(obj);
				vmi.setIsSuccess(true);

			} catch (Exception e) {
				logger.error("执行更新虚拟机的服务时间方法：失败");
				throw new Exception(e);
			}
			logger.info("执行更新虚拟机的服务时间方法：结束");
			return vmi;
		}

		if (info.getVmdkList() != null && info.getVmdkList().size() > 0) {
			String vmdkProductId = info.getVmdkList().get(0).getVmdkProduceInstaceId();
			if (vmdkProductId != null && !"".equals(vmdkProductId)) {
				try {
					logger.info("执行更新磁盘的服务时间方法：开始");
					VirtualDiskObj disk = new VirtualDiskObj();
					disk.setId(info.getVmdkList().get(0).getVmdk_id());
					disk.setCreate_time(format.format(info.getService_begin_time()));
					disk.setEnd_time(format.format(info.getService_end_time()));
					disk.setService_status(ServiceStatus.SERVICE);
					virtualDiskDao.updateByObj(disk);
					vmi.setIsSuccess(true);
					logger.debug("执行更新磁盘的服务时间方法，返回的数据为："
							+ JacksonUtil.formatJson(JacksonUtil.toJson(vmi)));
					logger.info("执行更新磁盘的服务时间方法：结束");

				} catch (Exception e) {
					logger.error("执行更新磁盘的服务时间方法：失败");
					throw new Exception(e);
				}

				return vmi;

			}
		}

		if (info.getIpProductInstanceId() != null && !"".equals(info.getIpProductInstanceId())) {

			try {
				logger.info("执行更新公网IP的服务时间方法：开始");
				PublicIPObj ip = new PublicIPObj();
				ip.setIpaddress(info.getInternet_ip());
				List<PublicIPObj> ipLists = publicIPDao.queryForListByObj(ip);
				if (ipLists != null && ipLists.size() > 0) {
					ip = ipLists.get(0);
				}
				ip.setCreate_time(format.format(info.getService_begin_time()));
				ip.setEnd_time(format.format(info.getService_end_time()));
				ip.setType(ServiceStatus.SERVICE);
				publicIPDao.updateByObj(ip);
				vmi.setIsSuccess(true);
				logger.debug("执行更新公网IP的服务时间方法，返回的数据为："
						+ JacksonUtil.formatJson(JacksonUtil.toJson(vmi)));
				logger.info("执行更新公网IP的服务时间方法：结束");
			} catch (Exception e) {
				logger.error("执行更新公网IP的服务时间方法：失败");
				throw new Exception(e);
			}

			return vmi;

		}
		if (info.getvLanProductInstanceId() != null && !"".equals(info.getvLanProductInstanceId())) {
			try {
				logger.info("执行公网带宽的服务时间方法：开始");
				PhysicalVlanObj vlan = new PhysicalVlanObj();
				vlan.setVlan_id(info.getVlan_id());
				vlan = physicalVlanDao.getPhysicalVlanByObj(vlan);
				vlan.setCreate_time(format.format(info.getService_begin_time()));
				vlan.setEnd_time(format.format(info.getService_end_time()));
				// 服务状态
				vlan.setBehavior(ServiceStatus.SERVICE);
				vlan.setUser_id("");
				physicalVlanDao.updateByObj(vlan);
				vmi.setIsSuccess(true);
				logger.debug("执行更新公网带宽的服务时间方法，返回的数据为："
						+ JacksonUtil.formatJson(JacksonUtil.toJson(vmi)));
				logger.info("执行更新公网带宽的服务时间方法：结束");

			} catch (Exception e) {
				logger.error("执行更新公网IP的服务时间方法：失败");
				throw new Exception(e);
			}

			return vmi;

		}
		if (info.getLoadProductInstanceId() != null && !"".equals(info.getLoadProductInstanceId())) {
			try {
				logger.info("执行更新负载均衡的服务时间方法：开始");
				LoadBalanceObj balance = new LoadBalanceObj();
				balance.setId(info.getLoadId());
				balance.setBegin_time(format.format(info.getService_begin_time()));
				balance.setEnd_time(format.format(info.getService_end_time()));
				// 服务状态
				balance.setService_status(ServiceStatus.SERVICE);
				loadBalanceDao.updateByObj(balance);
				vmi.setIsSuccess(true);
				logger.debug("执行更新负载均衡的服务时间方法，返回的数据为："
						+ JacksonUtil.formatJson(JacksonUtil.toJson(vmi)));
				logger.info("执行更新负载均衡的服务时间方法：结束");
			} catch (Exception e) {
				logger.error("执行更新负载均衡的服务时间方法：失败");
				throw new Exception(e);
			}

			return vmi;

		}
		return vmi;
	}

	/**
	 * @param
	 * @return Boolean
	 * @throws
	 * @Title: putVmPowerState
	 * @Description: 修改虚拟机电源状态
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-8-17 下午2:29:12
	 */
	public Boolean putVmPowerState(VMHostObj vmhost, String state) throws Exception {
		Boolean result = false;
		logger.info("开始修改虚拟机电源状态：", "state参数为：" + state);
		try {
			VirtualMachinePowerStateUnitedVO vo = new VirtualMachinePowerStateUnitedVO();
			vo.setVmCode(vmhost.getVH_UUID());
			vo.setConnectCode(vmhost.getConnectId());
			vo.setVirtualMachinePowerOper(state);
			String url = "";
			url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/change/powerstate";
			vo = VirtualClient.put(url, vo,
					new JacksonUtil.TypeReference<VirtualMachinePowerStateUnitedVO>() {
					});
			result = vo.getIsSuccess();
			logger.info("开始修改虚拟机电源状态：结束");
		} catch (Exception e) {
			logger.error("修改虚拟机电源状态：失败", e);
			throw new Exception(e);
		}
		return result;

	}

	/**
	 * 
	 * @Title: joinNetwork
	 * @Description: 虚拟机加入网络
	 * @param
	 * @return boolean
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-9-16 上午9:27:38
	 */
	public boolean joinNetwork(VMHostObj host, PhysicalVlanObj obj) throws Exception {
		logger.info("执行虚拟机加入网络方法：开始");
		logger.debug("执行虚拟机加入网络方法：传入的vmhost参数为：" + JacksonUtil.formatJson(JacksonUtil.toJson(host)));
		logger.debug("执行虚拟机加入网络方法：传入的PhysicalVlanObj参数为："
				+ JacksonUtil.formatJson(JacksonUtil.toJson(obj)));
		try {
			VMHostObj hostObj = new VMHostObj();
			hostObj = virtualMachineDao.queryByObj(host);
			SystemIpAndPassword ipObj = new SystemIpAndPassword();
			// 0.根据vlan id查找对应的内网vlan下的空闲的IP地址

			obj = physicalVlanDao.getPhysicalVlanByObj(obj);
			// 1.调用接口修改虚拟机所对应的端口组
			VirtualEthernetCardUnitedVO vir = new VirtualEthernetCardUnitedVO();
			vir.setVmCode(host.getVH_UUID());
			vir.setConnectCode(host.getConnectId());
			if (VlanType.inner_vlan.equals(obj.getVlan_type())) {// 内网
				vir.setEthernetCardName(VirtualEthernetConstant.card2);// 默认网络适配器2是内网网卡
			} else if (VlanType.public_vlan.equals(obj.getVlan_type())) {// 公网
				vir.setEthernetCardName(VirtualEthernetConstant.card1);// 默认网络适配器1是外网网卡
			}
			boolean change_result = false;
			// 构造更改IP对象及修改IP
			// 1.调用脚本修改虚拟机的IP地址
			ipObj = this.generateSystemIpAndPassword(host, obj);
			logger.debug("获取新的虚拟机IP地址对象信息为：" + JacksonUtil.formatJson(JacksonUtil.toJson(ipObj)));
			change_result = this.changeIp(host.getOs_type(), ipObj);
			logger.debug("修改IP地址的结果为：" + change_result);
			if (change_result) {
				// 更新端口组
				String portGroup = "vlan" + obj.getVlan_id();
				vir.setPortGroup(portGroup);
				String url = UnitedRestURI.VMHARDWAREURI + vir.getVmCode() + UnitedRestURI.VNIC
						+ UnitedRestURI.SEPERATER + vir.getEthernetCardName();

				ResultSet edit = VirtualClient.put(url, vir,
						new JacksonUtil.TypeReference<ResultSet>() {
						});
				logger.debug("修改虚拟机的端口组，结果为：" + edit.getIsSuccess());
				if (!edit.getIsSuccess()) {
					throw new Exception("修改虚拟机端口组：失败");
				}
				if (VlanType.public_vlan.equals(obj.getVlan_type())) {// 若为公网IP，则需检测公网IP是否修改成功
					boolean checkOutNic = false;
					if (OperateSystemType.linux.equals(host.getOs_type())) {// Linux操作系统验证公网IP修改
						checkOutNic = this.checkLinuxHasGatNicIfSuccess(ipObj);
					} else {// windows操作系统验证公网IP修改
						checkOutNic = this.checkWindowsHasGatNicIfSuccess(ipObj);
					}
					logger.debug("验证修改IP是否成功的结果为：" + checkOutNic);
					if (!checkOutNic) {
						vir.setEthernetCardName(VirtualEthernetConstant.card1);

						// 更新回原来的端口组
						String url1 = UnitedRestURI.VMHARDWAREURI + vir.getVmCode()
								+ UnitedRestURI.VNIC + UnitedRestURI.SEPERATER
								+ vir.getEthernetCardName();
						vir.setPortGroup("vlan9");
						ResultSet edit1 = VirtualClient.put(url1, vir,
								new JacksonUtil.TypeReference<ResultSet>() {
								});
						if (!edit1.getIsSuccess()) {
							logger.error("编辑虚拟机端口组失败");
							throw new Exception("编辑虚拟机端口组失败");
						}

						throw new Exception("加入公网Vlan失败，回滚所有！");
					}
				}

				// 1.修改虚拟机和IP关系数据
				VmIpObj relation = new VmIpObj();
				relation.setVm_uuid(host.getVH_UUID());
				relation.setConnectid(host.getConnectId());
				if (VlanType.inner_vlan.equals(obj.getVlan_type())) {// 内网
					relation.setIpType(IpType.inner_ip);
				} else if (VlanType.public_vlan.equals(obj.getVlan_type())) {// 公网
					relation.setIpType(IpType.intranet_ip);
				}

				List<VmIpObj> ipList = nasFileSysDao.queryVmIpRelation(relation);
				logger.debug("查找到虚拟机对应的IP地址列表信息为："
						+ JacksonUtil.formatJson(JacksonUtil.toJson(ipList)));
				if (ipList != null && ipList.size() > 0) {
					// 释放虚拟机原来的内网IP地址
					TbCloud2IpInfoObj old = new TbCloud2IpInfoObj();
					old.setIPADDRESS(ipList.get(0).getIp());
					old.setISUSED(QuotaConstant.not_used);
					old.setISBLOCKED(QuotaConstant.not_used);
					tbIpDao.updateIPStat(old);
					// 更新
					relation = ipList.get(0);
					relation.setIp(ipObj.getNewIp());// 置为最新的IP地址
					nasFileSysDao.updatVmIpObj(relation);
				}
				logger.info("执行虚拟机加入网络方法：结束");
				return true;
			} else {
				throw new Exception("修改虚拟机IP地址失败");
			}
		} catch (Exception e) {
			logger.error("虚拟机加入网络失败:", e);
			throw new Exception("虚拟就加入网络失败");
		}

	}

	/**
	 * 
	 * @Title: changeIPAndpassword
	 * @Description: 生成虚拟机的IP地址操作类,vlan_id,vlan_type可以根据需要传递，如果不需要传为null
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-14 上午9:58:00
	 */
	public SystemIpAndPassword generateSystemIpAndPassword(VMHostObj host, PhysicalVlanObj obj)
			throws Exception {
		String vlan_id = obj.getVlan_id();
		String vlan_type = obj.getVlan_type();
		String vmCode = host.getVH_UUID();
		String connectCode = host.getConnectId();
		String os_type = host.getOs_type();
		String vh_pass = host.getVH_PASS();// 数据库中已经double加密

		SystemIpAndPassword oper = new SystemIpAndPassword();
		// 1.根据虚拟机标示，获取虚拟机对应的IP地址列表
		VmIpObj ip = new VmIpObj();
		ip.setVm_uuid(vmCode);
		ip.setConnectid(connectCode);
		List<VmIpObj> ipsList = virtualMachineDao.queryIpListByVm(ip);
		if (ipsList != null && ipsList.size() > 0) {
			for (VmIpObj v : ipsList) {
				if (OperateSystemType.linux.equals(os_type)) {
					if (IpType.intranet_ip.equals(v.getIpType())) {// 通过用于出公网的IP地址链接
						if (VlanType.inner_vlan.equals(vlan_type)) {
							oper.setConnectName("eth3");
							oper.setNic_type(IpType.inner_nic);// 内网网卡，修改IP后，需执行脚本获取结果
						} else if (VlanType.public_vlan.equals(vlan_type)) {
							oper.setConnectName("eth2");
							oper.setNic_type(IpType.outer_nic);// 外网网卡，修改IP后，需执行新连iP连接获取结果
						}
						oper.setUser(OperateSystemLogInfo.linux_username);
						oper.setPass(vh_pass);
						oper.setIp(v.getIp());// 用于做NAt转换的内网IP
						break;
					}
				} else if (OperateSystemType.windows.equals(os_type)) {
					if (VlanType.inner_vlan.equals(vlan_type)) {// 要修改的是网卡内网vlan的IP地址
						if (IpType.intranet_ip.equals(v.getIpType())) {// 通过用于出公网的IP地址链接
							oper.setConnectName("本地连接 2");
							oper.setUser(OperateSystemLogInfo.windows_username);
							oper.setPass(vh_pass);
							oper.setIp(v.getIp());// 用于做NAt转换的内网IP
							oper.setNic_type(IpType.inner_nic);
							break;
						}
					} else if (VlanType.public_vlan.equals(vlan_type)) {// 通过用于出公网的IP地址链接
						if (IpType.intranet_ip.equals(v.getIpType())) {
							// oper.setIp(DoubleEncryptUtils.encrypt(v.getIp()));//
							// 用于做NAt转换的内网IP
							oper.setConnectName(null);
							oper.setUser(OperateSystemLogInfo.windows_username);
							oper.setPass(vh_pass);
							oper.setIp(v.getIp());// 用于做NAt转换的内网IP
							oper.setNic_type(IpType.outer_nic);
							break;
						}
					}
				}
			}
		}
		// 2.根据vlan查找该vlan下对应的闲置的内网IP地址
		if (vlan_id != null && !"".equals(vlan_id)) {
			TbCloud2NetInfoObj net = new TbCloud2NetInfoObj();
			net.setVLAN_ID(vlan_id);
			net = tbNetDao.queryByObj(net);
			TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
			ipObj.setISUSED(QuotaConstant.not_used);
			// ipObj.setIP_TYPE(IpType.inner_ip);
			ipObj.setNET_ID(net.getNET_ID());
			ipObj = tbIpDao.queryByObj(ipObj);
			if (ipObj != null) {
				ipObj.setISUSED(QuotaConstant.is_used);
				tbIpDao.updateIPStat(ipObj);
			}

			if (VlanType.inner_vlan.equals(vlan_type) && OperateSystemType.windows.equals(os_type)) {
				oper.setGateway("0.0.0.0");
			} else {
				oper.setGateway(net.getGATEWAY1());
			}

			oper.setNewIp(ipObj.getIPADDRESS());
			oper.setSubnetMask(net.getSUBNET());
		}
		return oper;
	}

	/**
	 * 
	 * @Title: checkLinuxHasGatNicIfSuccess
	 * @Description: 检测是否外网网卡修改成功
	 * @param
	 * @return boolean
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-8-28 下午7:21:59
	 */
	public boolean checkLinuxHasGatNicIfSuccess(SystemIpAndPassword sys) throws Exception {
		boolean change_result = false;
		PsexecOperator px = new PsexecOperator();
		TbGlobalConfigObj param = new TbGlobalConfigObj();
		param.setKEY("console_host_info");
		param = tbGlobalConfigDao.queryByObj(param);
		if (param != null) {
			// ip:port:username:password:shellpath
			String[] params = param.getVALUE().split(":");
			String localip = params[0];
			String port = params[1];// 预留字段
			String username = params[2];
			String password = params[3];
			String shellpath = params[4];
			// 调用linux改脚本的方法
			change_result = px.checkLinuxHasGatNicIfSuccess(DoubleEncryptUtils.encrypt(localip),
					Integer.parseInt(port), DoubleEncryptUtils.encrypt(username),
					DoubleEncryptUtils.encrypt(password), shellpath, sys);
		} else {
			throw new ConsoleException(
					"全局配置表未配置‘websockify_param’参数，参数格式为：{websockify_host : websockify_port}");
		}
		return change_result;
	}

	/**
	 * 
	 * @Title: checkWindowsHasGatNicIfSuccess
	 * @Description: 验证Windows检测是否外网网卡修改成功
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 * @createtime 2014-8-29
	 */
	private boolean checkWindowsHasGatNicIfSuccess(SystemIpAndPassword ipAndPass) {
		PsexecOperator px = new PsexecOperator();
		ipAndPass.setConnectName("check");
		ipAndPass.setNic_type("");
		ipAndPass.setIp(ipAndPass.getNewIp());
		return px.psexecForWinCIP(ipAndPass);
	}

	/**
	 * 
	 * @Title: getVlanInfo
	 * @Description: 获取不同vlan信息
	 * @param
	 * @return PhysicalVlanObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-9-16 上午9:50:47
	 */
	public PhysicalVlanObj getVlanInfo(String user_id, String vlan_type) throws Exception {
		PhysicalVlanObj obj = new PhysicalVlanObj();
		obj.setUser_id(user_id);
		obj.setVlan_type(vlan_type);
		obj = physicalVlanDao.getPhysicalVlanByObj(obj);
		return obj;
	}
}
