package com.sitech.shop.webservice.service.status;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.shop.dao.balance.LoadBalanceDao;
import com.sitech.shop.dao.disk.VirtualDiskDao;
import com.sitech.shop.dao.publicIP.PublicIPDao;
import com.sitech.shop.dao.vlan.PhysicalVlanDao;
import com.sitech.shop.dao.vm.VirtualMachineDao;
import com.sitech.shop.domain.balance.LoadBalanceObj;
import com.sitech.shop.domain.disk.VirtualDiskObj;
import com.sitech.shop.domain.ip.PublicIPObj;
import com.sitech.shop.domain.vlan.PhysicalVlanObj;
import com.sitech.ssd.billing.vo.resourceInfo.VmInfo;
import com.sitech.utils.jackson.JacksonUtil;

@Service("putResourceServeStatus")
public class PutResourceServeStatusImpl implements PutResourceServeStatus {
	Logger logger = LoggerFactory.getLogger(PutResourceServeStatusImpl.class);
	@Autowired
	private VMHostDao vmHostDao;
	@Autowired
	private VirtualMachineDao virtualMachineDao;
	@Autowired
	private VirtualDiskDao virtualDiskDao;
	@Autowired
	private PublicIPDao publicIPDao;
	@Autowired
	private PhysicalVlanDao physicalVlanDao;
	@Autowired
	private LoadBalanceDao loadBalanceDao;

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Title: updateVMTime
	 * @Description: 更新虚拟机开始和到期状态等
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-7 上午11:24:02
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public VmInfo updateServeStatusProcess(VmInfo info) throws Exception {
		logger.info("更新服务状态,传入的参数为VmInfo：" + JacksonUtil.formatJson(JacksonUtil.toJson(info)));
		VmInfo vmi = info.clone();
		if (info.getVmProductInstanceId() != null && !"".equals(info.getVmProductInstanceId())) {
			try {
				logger.info("执行更新虚拟机的服务状态方法：开始");
				VMHostObj obj = new VMHostObj();
				obj.setVH_UUID(info.getVmCode());
				obj.setConnectId(info.getConnectCode());
				// 更新虚拟机服务状态
				obj.setBUSI_STATUS(info.getStatus());
				virtualMachineDao.updateVMHostObj(obj);
				vmi.setIsSuccess(true);
			} catch (Exception e) {
				logger.error("执行更新虚拟机的服务状态方法：失败");
				throw new Exception(e);
			}
			logger.info("执行更新虚拟机的服务状态方法：结束");
			return vmi;
		}

		if (info.getVmdkList() != null && info.getVmdkList().size() > 0) {
			String vmdkProductId = info.getVmdkList().get(0).getVmdkProduceInstaceId();
			if (vmdkProductId != null && !"".equals(vmdkProductId)) {
				try {
					logger.info("执行更新磁盘的服务状态方法：开始");
					VirtualDiskObj disk = new VirtualDiskObj();
					disk.setId(info.getVmdkList().get(0).getVmdk_id());
					disk.setService_status(info.getStatus());
					virtualDiskDao.updateByObj(disk);
					vmi.setIsSuccess(true);
					logger.debug("执行更新磁盘的服务状态方法，返回的数据为："
							+ JacksonUtil.formatJson(JacksonUtil.toJson(vmi)));
					logger.info("执行更新磁盘的服务状态方法：结束");
				} catch (Exception e) {
					logger.error("执行更新磁盘的服务状态方法：失败");
					throw new Exception(e);
				}

				return vmi;

			}
		}

		if (info.getIpProductInstanceId() != null && !"".equals(info.getIpProductInstanceId())) {

			try {
				logger.info("执行更新公网IP的服务状态方法：开始");
				PublicIPObj ip = new PublicIPObj();
				ip.setIpaddress(info.getInternet_ip());
				List<PublicIPObj> ipLists = publicIPDao.queryForListByObj(ip);
				if (ipLists != null && ipLists.size() > 0) {
					ip = ipLists.get(0);
				}
				ip.setType(info.getStatus());
				publicIPDao.updateByObj(ip);
				vmi.setIsSuccess(true);
				logger.debug("执行更新公网IP的服务状态方法，返回的数据为："
						+ JacksonUtil.formatJson(JacksonUtil.toJson(vmi)));
				logger.info("执行更新公网IP的服务状态方法：结束");
			} catch (Exception e) {
				logger.error("执行更新公网IP的服务状态方法：失败");
				throw new Exception(e);
			}

			return vmi;

		}
		if (info.getvLanProductInstanceId() != null && !"".equals(info.getvLanProductInstanceId())) {
			try {
				logger.info("执行公网带宽的服务状态方法：开始");
				PhysicalVlanObj vlan = new PhysicalVlanObj();
				vlan.setVlan_id(info.getVlan_id());
				vlan = physicalVlanDao.getPhysicalVlanByObj(vlan);
				vlan.setUser_id("");
				vlan.setBehavior(info.getStatus());
				physicalVlanDao.updateByObj(vlan);
				vmi.setIsSuccess(true);
				logger.debug("执行更新公网带宽的服务状态方法，返回的数据为："
						+ JacksonUtil.formatJson(JacksonUtil.toJson(vmi)));
				logger.info("执行更新公网带宽的服务状态方法：结束");

			} catch (Exception e) {
				logger.error("执行更新公网IP的服务状态方法：失败");
				throw new Exception(e);
			}

			return vmi;

		}
		if (info.getLoadProductInstanceId() != null && !"".equals(info.getLoadProductInstanceId())) {
			try {
				logger.info("执行更新负载均衡的服务状态方法：开始");
				LoadBalanceObj balance = new LoadBalanceObj();
				balance.setId(info.getLoadId());
				balance.setService_status(info.getStatus());
				loadBalanceDao.updateByObj(balance);
				vmi.setIsSuccess(true);
				logger.debug("执行更新负载均衡的服务状态方法，返回的数据为："
						+ JacksonUtil.formatJson(JacksonUtil.toJson(vmi)));
				logger.info("执行更新负载均衡的服务状态方法：结束");
			} catch (Exception e) {
				logger.error("执行更新负载均衡的服务状态方法：失败");
				throw new Exception(e);
			}

			return vmi;

		}
		return vmi;
	}
}
