/**   
 * Copyright: Copyright (c) 2014
 * Company: SI-TECH
 *
 * @Title: ResourceDeleteServiceImpl.java 
 * @Package com.sitech.shop.webservice.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author wanglei_bj@si-tech.com.cn 
 * @date 2014-9-12 下午2:50:04 
 * @version V1.0   
 */
package com.sitech.shop.webservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import publiccloud.PublicCloudConstant;

import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.shop.domain.balance.LoadBalanceObj;
import com.sitech.shop.domain.disk.VirtualDiskObj;
import com.sitech.shop.domain.firewall.RulesObj;
import com.sitech.shop.domain.ip.PublicIPObj;
import com.sitech.shop.domain.vlan.PhysicalVlanObj;
import com.sitech.shop.service.disk.VirtualDiskService;
import com.sitech.shop.service.firewall.RulesService;
import com.sitech.shop.service.publicip.PublicIPService;
import com.sitech.shop.service.vm.VirtualMachineService;
import com.sitech.shop.webservice.service.destroy.ResourceDestroyService;
import com.sitech.utils.jackson.JacksonUtil;

/**
 * @ClassName: ResourceDeleteServiceImpl
 * @Description: 资源删除，回收
 * @author wanglei_bj@si-tech.com.cn
 * @date 2014-9-12 下午2:50:04
 * @version 1.0
 */
@Service("resourceDeleteService")
public class ResourceDeleteServiceImpl implements ResourceDeleteService {
	private static final Logger logger = LoggerFactory.getLogger(ResourceDeleteServiceImpl.class);
	@Resource(name = "publicIPDestroyService")
	private ResourceDestroyService<PublicIPObj> publicIPDestroyService;
	@Resource(name = "bandWidthDestroyService")
	private ResourceDestroyService<PhysicalVlanObj> bandWidthDestroyService;
	@Resource(name = "diskDestroyService")
	private ResourceDestroyService<VirtualDiskObj> diskDestroyService;
	@Resource(name = "vmDestroyService")
	private ResourceDestroyService<VMHostObj> vmDestroyService;
	@Resource(name = "loadBalanceDestroyService")
	private ResourceDestroyService<LoadBalanceObj> loadBalanceDestroyService;
	@Resource
	private PublicIPService publicIPService;
	@Resource
	private VirtualDiskService virtualDiskService;
	@Resource
	private RulesService rulesService;
	@Resource
	private VirtualMachineService virtualMachineService;

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: systemDeletePublicIP
	 * </p>
	 * <p>
	 * Description: 系统删除公网IP
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 *            中的内网ip一定是最新的
	 * @return Boolean true 删除成功 false 删除失败
	 * @throws Exception 
	 * @see com.sitech.shop.webservice.service.ResourceDeleteService#systemDeletePublicIP(com.sitech.shop.domain.ip.PublicIPObj)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean systemDeletePublicIP(PublicIPObj obj) throws Exception {
		logger.info("执行系统删除公网ip方法：开始");
		logger.debug("传入的公网ip对象为：" + JacksonUtil.formatJson(JacksonUtil.toJson(obj)));
		boolean result = false;
		try {
			if (publicIPDestroyService.checkRelation(obj)) {
				Assert.isTrue(publicIPDestroyService.destroyResourse(obj), "销毁公网IP资源失败！");
				if (publicIPDestroyService.recycleResourse(obj)) {
					Assert.isTrue(publicIPDestroyService.noticeBilling(obj), "通知计费失败！");
				}else{
					Assert.isTrue(publicIPDestroyService.rollbackResourse(obj), "回滚公网IP资源失败！");
				}
			} else {
				logger.info("核对信息为false直接执行回收！");
				if (publicIPDestroyService.recycleResourse(obj)) {
					Assert.isTrue(publicIPDestroyService.noticeBilling(obj), "通知计费失败！");
				}else{
					Assert.isTrue(publicIPDestroyService.rollbackResourse(obj), "回滚公网IP资源失败！");
				}
			}
			result = true;
		} catch (Exception e) {
			logger.error("系统删除公网ip失败！",e);
			throw new Exception("系统删除公网ip失败！");
		}
		logger.info("执行系统删除公网ip方法：结束");
		return result;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: userDeletePublicIP
	 * </p>
	 * <p>
	 * Description: 用户删除公网IP，公控制台远程调用
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 *            中的内网ip一定是最新的
	 * @return string PublicCloudConstant.SUCCESS 删除成功 PublicCloudConstant.FAIL
	 *         删除失败
	 * @throws Exception 
	 * @see com.sitech.shop.webservice.service.ResourceDeleteService#userDeletePublicIP(com.sitech.shop.domain.ip.PublicIPObj)
	 */
	@Override
	public String userDeletePublicIP(PublicIPObj obj) throws Exception {
		logger.info("执行用户删除公网IP方法：开始");
		logger.debug("传入的公网IP对象为：" + JacksonUtil.formatJson(JacksonUtil.toJson(obj)));
		String result = PublicCloudConstant.FAIL;

		try {
			if (publicIPDestroyService.checkRelation(obj)) {
				Assert.isTrue(publicIPDestroyService.destroyResourse(obj), "销毁公网IP资源失败！");
				if (publicIPDestroyService.recycleResourse(obj)) {
					Assert.isTrue(publicIPDestroyService.noticeBilling(obj), "通知计费失败！");
				}else{
					Assert.isTrue(publicIPDestroyService.rollbackResourse(obj), "回滚公网IP资源失败！");
				}
			} else {
				logger.info("核对信息为false直接执行回收！");
				if (publicIPDestroyService.recycleResourse(obj)) {
					Assert.isTrue(publicIPDestroyService.noticeBilling(obj), "通知计费失败！");
				}else{
					Assert.isTrue(publicIPDestroyService.rollbackResourse(obj), "回滚公网IP资源失败！");
				}
			}
			result = PublicCloudConstant.SUCCESS;
		} catch (Exception e) {
			logger.error("用户删除公网ip失败！",e);
			throw new Exception("系统删除公网ip失败！");
			
		}
		logger.info("执行用户删除带宽方法：结束");

		return result;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: systemDeleteBandWidth
	 * </p>
	 * <p>
	 * Description: 系统删除带宽
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param vlan
	 * @return true or false
	 * @see com.sitech.shop.webservice.service.ResourceDeleteService#systemDeleteBandWidth(com.sitech.shop.domain.vlan.PhysicalVlanObj)
	 */
	@Override
	public Boolean systemDeleteBandWidth(PhysicalVlanObj vlan) {
		logger.info("执行系统删除带宽方法：开始");
		logger.debug("传入的带宽对象为：" + JacksonUtil.formatJson(JacksonUtil.toJson(vlan)));
		boolean result = true;
		try {
			if (bandWidthDestroyService.checkRelation(vlan)) {
				Assert.isTrue(bandWidthDestroyService.destroyResourse(vlan), "销毁带宽资源失败！");
				if (!bandWidthDestroyService.recycleResourse(vlan)) {
					Assert.isTrue(bandWidthDestroyService.rollbackResourse(vlan), "回滚带宽资源失败！");
				}
			} else {
				logger.info("用户没有带宽，不需要删除！");
			}
		} catch (Exception e) {
			logger.error("系统删除带宽失败！", e);
			result = false;
		}
		logger.info("执行系统删除带宽方法：结束");
		return result;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: userDeleteBandWidth
	 * </p>
	 * <p>
	 * Description: 用户删除带宽，公控制台远程调用
	 * </p>
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param vlan
	 * @return string PublicCloudConstant.SUCCESS 删除成功 PublicCloudConstant.FAIL
	 *         删除失败
	 * @see com.sitech.shop.webservice.service.ResourceDeleteService#userDeleteBandWidth(com.sitech.shop.domain.ip.PublicIPObj)
	 */
	@Override
	public String userDeleteBandWidth(PhysicalVlanObj vlan) {
		logger.info("执行用户删除带宽方法：开始");
		logger.debug("传入的带宽对象为：" + JacksonUtil.formatJson(JacksonUtil.toJson(vlan)));
		String result = PublicCloudConstant.SUCCESS;
		try {
			if (bandWidthDestroyService.checkRelation(vlan)) {
				Assert.isTrue(bandWidthDestroyService.destroyResourse(vlan), "销毁带宽资源失败！");
				if (!bandWidthDestroyService.recycleResourse(vlan)) {
					Assert.isTrue(bandWidthDestroyService.rollbackResourse(vlan), "回滚带宽资源失败！");
				}
				bandWidthDestroyService.noticeBilling(vlan);
			} else {
				logger.info("用户没有带宽，不需要删除！");
			}
		} catch (Exception e) {
			logger.error("系统删除带宽失败！", e);
			result = PublicCloudConstant.FAIL;
		}
		logger.info("执行用户删除带宽方法：结束");
		return result;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: systemDeleteVm
	 * </p>
	 * <p>
	 * Description: 系统删除虚拟机
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param vm
	 * @return Boolean
	 */
	@Override
	public Boolean systemDeleteVm(VMHostObj vm) {
		logger.info("执行系统删除虚拟机方法：开始");
		logger.debug("传入的虚拟机对象为：" + JacksonUtil.formatJson(JacksonUtil.toJson(vm)));

		checkVmParam(vm);

		boolean result = true;
		/*
		 * 删除先后顺序的原则：能回滚操作的，先执行。否则后执行。
		 */

		// 0.准备待检测对象
		// 1.检测是否挂载磁盘
		// 2.检测是否绑定公网IP
		// TODO 3.检测是否存在负载监听器中
		// 4.检测是否存在防火墙规则中
		// X 5.检测是否加入网络! 不需要移除网络
		// 6.关机
		// 7.销毁
		// 8.更新数据库

		// 0.1 准备磁盘对象
		List<VirtualDiskObj> disks = getVirtualDisksFromVm(vm);
		// 0.2准备公网IP数据
		PublicIPObj publicip = getPublicIPObjFromVm(vm);
		// 0.3准备防火墙规则数据
		List<RulesObj> rules = getFireRulesFromVm(vm);

		// 销毁关系
		try {
			// 1.解除主机与公网IP之间的关系
			if (publicip != null) {
				logger.info("正在解除与公网IP的绑定");
				logger.debug("正在解除的公网IP：" + JacksonUtil.formatJson(JacksonUtil.toJson(publicip)));
				if (publicIPDestroyService.destroyResourse(publicip)) {
					boolean ret = publicIPService.releaseByPublicIp(publicip);
					if (!ret) {
						// 回滚绑定绑定Ip
						publicIPDestroyService.rollbackResourse(publicip);
					}
					logger.info("解除绑定公网IP操作：" + (ret ? "成功" : "失败"));
				}
			}

			// 2.解除主机与磁盘挂载关系
			if (disks != null && disks.size() > 0) {
				for (VirtualDiskObj disk : disks) {
					logger.info("正在解除磁盘挂载关系");
					logger.debug("正在卸载磁盘：" + JacksonUtil.formatJson(JacksonUtil.toJson(disk)));
					int ret = virtualDiskService.unInstallDisk(disk);
					logger.info("卸载磁盘结果：" + (ret > 0 ? "成功" : "失败"));
				}
			}
			// 3.删除主机关联的防火墙规则
			if (rules != null && rules.size() > 0) {
				for (RulesObj rule : rules) {
					logger.info("正在删除主机关联的防火墙规则关系");
					logger.debug("正在删除防火墙规则：" + JacksonUtil.formatJson(JacksonUtil.toJson(rule)));
					String ret = rulesService.deleteRules(rule);
					logger.info("删除防火墙规则结果："
							+ (ret.equals(PublicCloudConstant.SUCCESS) ? "成功" : "失败"));
				}
			}
			// TODO 4.删除主机与负载均衡器的关系

			// 5.删除主机
			if (vmDestroyService.destroyResourse(vm)) {
				boolean ret = vmDestroyService.recycleResourse(vm);
				logger.info("销毁虚拟机" + (ret ? "成功" : "失败"));
			}

		} catch (Exception e) {
			logger.error("系统删除虚拟机失败！", e);
			result = false;
		}
		logger.info("执行系统删除虚拟机方法：结束");
		return result;
	}

	/**
	 * 
	 * @Title: checkVmParam
	 * @Description: 检测传入主机的参数
	 * @param @param vm 设定文件
	 * @return void 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 * @createtime 2014-9-18
	 */
	private void checkVmParam(VMHostObj vm) {
		Assert.isNull(vm, "要删除的虚拟机对象不能为空！");
		Assert.hasText(vm.getUSER_ID(), "用户ID不能为空！");
		Assert.hasText(vm.getVH_IP(), "内网IP不能为空！");
		Assert.hasText(vm.getConnectId(), "connectId不能为空！");
		Assert.hasText(vm.getVH_UUID(), "vm_uuid不能为空");
	}

	/**
	 * 
	 * @Title: getFireRulesFromVm
	 * @Description: 根据主机查询用户的建立在这台主机上的防火墙规则
	 * @param @param vm
	 * @param @return 设定文件
	 * @return List<RulesObj> 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 * @createtime 2014-9-18
	 */
	private List<RulesObj> getFireRulesFromVm(VMHostObj vm) {
		List<RulesObj> rules = new ArrayList<RulesObj>();
		RulesObj rule = new RulesObj();
		rule.setAction(vm.getVH_IP());// 主机的内网IP
		rules = rulesService.getRulesList(rule);
		return rules;
	}

	/**
	 * 
	 * @Title: getPublicIPObjFromVm
	 * @Description: 从虚拟机对象转成公网IP对象
	 * @param @param vm
	 * @param @return 设定文件
	 * @return PublicIPObj 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 * @createtime 2014-9-17
	 */
	private PublicIPObj getPublicIPObjFromVm(VMHostObj vm) {
		PublicIPObj publicIPObj = new PublicIPObj();
		publicIPObj.setConnect_id(vm.getConnectId());
		publicIPObj.setEntity_id(vm.getVH_UUID());
		publicIPObj.setUser_id(vm.getUSER_ID());
		publicIPObj = publicIPService.queryForObj(publicIPObj);
		return publicIPObj;
	}

	/**
	 * 
	 * @Title: getVirtualDisksFromVm
	 * @Description: 从虚拟机对象查询相关联虚拟机磁盘列表
	 * @param @param vm
	 * @return List<VirtualDiskObj> 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 * @createtime 2014-9-17
	 */
	private List<VirtualDiskObj> getVirtualDisksFromVm(VMHostObj vm) {
		List<VirtualDiskObj> disks = new ArrayList<VirtualDiskObj>();
		VirtualDiskObj diskObj = new VirtualDiskObj();
		diskObj.setUser_id(vm.getUSER_ID());
		diskObj.setConnectCode(vm.getConnectId());
		diskObj.setVm_uuid(vm.getVH_UUID());
		diskObj.setState("1");// 挂载中
		diskObj.setDisk_type("2");// 数据盘
		disks = virtualDiskService.queryForListByObj(diskObj);
		return disks;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: userDeleteVm
	 * </p>
	 * <p>
	 * Description:用户删除虚拟机
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param vm
	 * @return
	 * @see com.sitech.shop.webservice.service.ResourceDeleteService#userDeleteVm(com.sitech.shop.domain.vm.VirtualMachineObj)
	 */
	public String userDeleteVm(VMHostObj vm) {
		logger.info("用户删除虚拟机开始");
		String result = PublicCloudConstant.SUCCESS;
		try {
			if (systemDeleteVm(vm)) {
				boolean ret = vmDestroyService.noticeBilling(vm);
				logger.info("通知计费结果：" + (ret ? "成功" : "失败"));
			} else {
				logger.error("用户删除虚拟机失败！");
				result = PublicCloudConstant.FAIL;
			}
		} catch (Exception e) {
			logger.error("用户删除虚拟机失败！");
			result = PublicCloudConstant.FAIL;
		}
		logger.info("用户删除虚拟机结束");
		return result;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: systemDeleteDisk
	 * </p>
	 * <p>
	 * Description: 系统删除磁盘
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param disk
	 * @return
	 * @throws Exception 
	 * @see com.sitech.shop.webservice.service.ResourceDeleteService#systemDeleteDisk(com.sitech.shop.domain.disk.VirtualDiskObj)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean systemDeleteDisk(VirtualDiskObj disk) throws Exception {

		logger.info("执行删除磁盘方法：开始");
		logger.debug("传入的磁盘对象为：" + JacksonUtil.formatJson(JacksonUtil.toJson(disk)));
		boolean flag = false;
		try {
			if (diskDestroyService.checkRelation(disk)) {
				if (diskDestroyService.destroyResourse(disk)) {
					Assert.isTrue(diskDestroyService.recycleResourse(disk), "删除磁盘失败！");
				} else {
					logger.error("卸载磁盘失败！");
				}
			} else {
				logger.info("没有磁盘，不需要删除！");
				flag = true;
			}
		} catch (Exception e) {
			logger.error("系统删除磁盘失败！", e.getMessage());
			throw new Exception(e);
		}
		logger.info("执行系统删除磁盘方法：结束");
		return flag;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: userDeleteDisk
	 * </p>
	 * <p>
	 * Description:用户删除磁盘 通知计费
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param disk
	 * @return
	 * @throws Exception 
	 * @throws Exception 
	 * @see com.sitech.shop.webservice.service.ResourceDeleteService#userDeleteDisk(com.sitech.shop.domain.disk.VirtualDiskObj)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String userDeleteDisk(VirtualDiskObj disk) throws Exception {

		logger.info("执行删除磁盘方法：开始");
		String flag = PublicCloudConstant.FAIL;
		try {
			if (diskDestroyService.checkRelation(disk)) {
				if (diskDestroyService.destroyResourse(disk)) {
					logger.info("卸载磁盘成功！");
					if (diskDestroyService.recycleResourse(disk)) {
						logger.info("删除磁盘成功！");
						Assert.isTrue(diskDestroyService.noticeBilling(disk), "通知计费失败,请联系运维人员");
					} else {
						logger.error("删除磁盘失败！");
					}
				} else {
					logger.error("卸载磁盘失败！");
				}
			} else {
				logger.info("没有磁盘，不需要删除！");
			}
			flag = PublicCloudConstant.SUCCESS;
		} catch (Exception e) {
			logger.error("用户删除磁盘失败！", e);
			throw new Exception("用户删除磁盘失败！");
		}
		logger.info("执行用户删除磁盘方法：结束");
		return flag;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: systemDeleteLoadBalance
	 * </p>
	 * <p>
	 * Description: 系统删除负载均衡
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param lb
	 * @return
	 * @see com.sitech.shop.webservice.service.ResourceDeleteService#systemDeleteLoadBalance(com.sitech.shop.domain.balance.LoadBalanceObj)
	 */
	@Override
	public Boolean systemDeleteLoadBalance(LoadBalanceObj lb) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: userDeleteLoadBalance
	 * </p>
	 * <p>
	 * Description:用户删除负载均衡
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param lb
	 * @return
	 * @see com.sitech.shop.webservice.service.ResourceDeleteService#userDeleteLoadBalance(com.sitech.shop.domain.balance.LoadBalanceObj)
	 */
	@Override
	public String userDeleteLoadBalance(LoadBalanceObj lb) {
		// TODO Auto-generated method stub
		return null;
	}

}
