package com.sitech.shop.webservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import publiccloud.PublicCloudPowerSatus;
import rabbitmq.QueueDefind;

import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.shop.domain.disk.VirtualDiskObj;
import com.sitech.shop.domain.ip.PublicIPObj;
import com.sitech.shop.domain.vlan.PhysicalVlanObj;
import com.sitech.shop.webservice.service.status.PutResourceServeStatus;
import com.sitech.ssd.billing.vo.resourceInfo.VmInfo;
import com.sitech.utils.common.CommonUtil;
import com.sitech.utils.exception.RabbitMQException;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.vo.united.VirtualDiskUnitedVO;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
	@Autowired
	private ResourceDealService resourceDealService;
	@Autowired
	private BandwidthDealService bandwidthDealService;
	@Autowired
	private RabbitMQUtil rabbitmqUtil;
	@Autowired
	private OrderRelation orderRelation;
	@Autowired
	private ResourceDeleteService resourceDeleteService;
	@Autowired
	private PutResourceServeStatus putResourceServeStatus;

	/**
	 * 
	 * @Title: applyVM
	 * @Description:
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws RabbitMQException
	 * @throws Exception
	 * @createtime 2014-6-18 上午10:06:30
	 */
	public void applyVM(VmInfo info) throws RabbitMQException {
		VmInfo vmi = info.clone();
		boolean flag = true;
		try {
			boolean if_init = info.if_Init;
			boolean send_type = info.getSend_Type();
			if (if_init && send_type) {// 第一次购买并且是商城发送的单子才分配IP地址及vlan
				resourceDealService.allocateRescoure(info);
			}
		} catch (Exception e) {
			logger.error("分配IP及vlan过程中出现异常", e);
			vmi.setLog("IPERROR:分配IP或者VLAN失败！");
			flag = false;
		}
		if (flag) {
			try {
				vmi = resourceDealService.applyVMProcess(info);
			} catch (Exception e) {
				logger.error("申请虚拟机过程中出现异常", e);
			}
		}

		if (CommonUtil.isEmpty(vmi)) {
			vmi = info.clone();
		}
		rabbitmqUtil.publishMessage("", QueueDefind.BJ_CALLBACKMESSAGE, vmi);
	}

	/**
	 * 
	 * @Title: mountDisk
	 * @Description: 挂载磁盘
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-25 下午3:30:35
	 */
	public void mountDisk(List<VirtualDiskUnitedVO> disks) {
		try {
			for (VirtualDiskUnitedVO disk : disks) {

				orderRelation.mountDisk(disk.getConnectCode(), disk.getVmCode(), disk.getVmdk_id());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @Title: unmountDisk
	 * @Description: 卸载磁盘
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-25 下午3:31:02
	 */
	public void unmountDisk(List<VirtualDiskUnitedVO> diskList) {
		try {
			for (VirtualDiskUnitedVO disk : diskList) {

				orderRelation.uninstallDisk(disk.getConnectCode(), disk.getVmCode(),
						disk.getVmdk_id());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: putVMPowerState
	 * @Description:修改虚拟机电源状态
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-6-26 下午8:24:10
	 */
	public VmInfo putResourceState(VmInfo info) throws Exception {
		// 回调黎明的方法进行处理
		VmInfo vmi = info.clone();
		boolean result = false;
		String state = info.getStatus();
		//
		if (PublicCloudPowerSatus.notice_destory.equals(state)) {

			if (info.getVmProductInstanceId() != null && !"".equals(info.getVmProductInstanceId())) {
				try {
					VMHostObj vm = new VMHostObj();
					vm.setVH_UUID(info.getVmCode());
					vm.setConnectId(info.getConnectCode());
					result = resourceDeleteService.systemDeleteVm(vm);
					vmi.setIsSuccess(result);
				} catch (Exception e) {
				}
				return vmi;
			}
			if (info.getVmdkList() != null && info.getVmdkList().size() > 0) {
				String vmdkProductId = info.getVmdkList().get(0).getVmdkProduceInstaceId();
				if (vmdkProductId != null && !"".equals(vmdkProductId)) {
					try {
						VirtualDiskObj disk = new VirtualDiskObj();
						disk.setId(info.getVmdkList().get(0).getVmdk_id());
						result = resourceDeleteService.systemDeleteDisk(disk);
						vmi.setIsSuccess(result);
					} catch (Exception e) {
						logger.error("执行更新磁盘的服务时间方法：失败");
						throw new Exception(e);
					}
					return vmi;
				}
			}
			if (info.getIpProductInstanceId() != null && !"".equals(info.getIpProductInstanceId())) {
				try {
					PublicIPObj ip = new PublicIPObj();
					ip.setIpaddress(info.getIntranet_ip());
					result = resourceDeleteService.systemDeletePublicIP(ip);
					vmi.setIsSuccess(result);
				} catch (Exception e) {
					throw new Exception(e);
				}

				return vmi;

			}
			if (info.getvLanProductInstanceId() != null
					&& !"".equals(info.getvLanProductInstanceId())) {
				try {
					PhysicalVlanObj vlan = new PhysicalVlanObj();
					vlan.setVlan_id(info.getVlan_id());
					result = resourceDeleteService.systemDeleteBandWidth(vlan);
					vmi.setIsSuccess(result);
				} catch (Exception e) {
					throw new Exception(e);
				}
				return vmi;

			}
		} else if (PublicCloudPowerSatus.notice_off.equals(state)) {
			vmi = putResourceServeStatus.updateServeStatusProcess(info);
		}
		rabbitmqUtil.publishMessage("", QueueDefind.BJ_CALLBACKMESSAGE, vmi);
		return vmi;

	}

	/**
	 * 
	 * @Title: editVM
	 * @Description: 重新配置虚拟机
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws RabbitMQException
	 * @createtime 2014-6-28 上午11:54:40
	 */
	public void editVM(VmInfo info) throws RabbitMQException {
		// 回调黎明的方法进行处理
		VmInfo vmi = info.clone();
		try {
			vmi = resourceDealService.editVMProcess(info);
		} catch (Exception e) {
			vmi.setIsSuccess(false);
			logger.error("重新配置虚拟机过程中出现异常");
		}
		rabbitmqUtil.publishMessage("", QueueDefind.BJ_CALLBACKMESSAGE, vmi);

	}

	/**
	 * 
	 * @Title: applyPublicIpProcess
	 * @Description: 购买公网IP处理过程
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-3 下午5:08:34
	 */
	public void applyPublicIP(VmInfo info) throws Exception {
		VmInfo vmi = info.clone();
		try {
			vmi = resourceDealService.applyPublicIPProcess(info);
			// 回调黎明的方法返回公网IP地址购买的结果
		} catch (Exception e) {
			logger.error("申请公网IP过程中出现异常");
		}
		rabbitmqUtil.publishMessage("", QueueDefind.BJ_CALLBACKMESSAGE, vmi);

	}

	/**
	 * 
	 * @Title: applyBandwidth
	 * @Description: 购买公网带宽
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-3 下午5:52:31
	 */
	public void applyBandwidth(VmInfo info) throws Exception {
		VmInfo vmi = info.clone();
		try {
			vmi = bandwidthDealService.applyBandwidthProcess(info);
			// 回调黎明的方法返回公网带宽购买的结果
		} catch (Exception e) {
			logger.error("申请公网带宽过程中出现异常");
		}
		rabbitmqUtil.publishMessage("", QueueDefind.BJ_CALLBACKMESSAGE, vmi);

	}

	/**
	 * 
	 * @Title: expandBandwidth
	 * @Description:更改带宽
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-8 上午10:05:20
	 */
	public void expandBandwidth(VmInfo info) throws Exception {
		VmInfo vmi = info.clone();
		try {
			vmi = bandwidthDealService.expandBandwidthProcess(info);
			// 回调黎明的方法返回公网IP地址购买的结果
		} catch (Exception e) {
			logger.error("变更公网带宽过程中出现异常");
		}
		rabbitmqUtil.publishMessage("", QueueDefind.BJ_CALLBACKMESSAGE, vmi);

	}

	/**
	 * 
	 * @Title: applayBalance
	 * @Description:申请负载均衡
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-7 上午11:52:43
	 */
	public void applyBalance(VmInfo info) throws Exception {
		VmInfo vmi = info.clone();
		try {
			vmi = resourceDealService.applyBalanceProcess(info);
		} catch (Exception e) {
			logger.error("申请负载均衡过程中出现异常");
		}
		rabbitmqUtil.publishMessage("", QueueDefind.BJ_CALLBACKMESSAGE, vmi);

	}

	/**
	 * 
	 * @Title: updateVMTime
	 * @Description: 更新虚拟机开始和到期时间等
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-7 上午11:24:02
	 */
	public void updateServeTime(VmInfo info) throws Exception {
		VmInfo vmi = info.clone();
		{
			try {
				vmi = resourceDealService.updateServeTimeProcess(info);
				// 回调黎明的方法返回更新时间的结果
			} catch (Exception e) {
				logger.error("变更时长过程中出现异常");
			}
			rabbitmqUtil.publishMessage("", QueueDefind.BJ_CALLBACKMESSAGE, vmi);

		}
	}
}
