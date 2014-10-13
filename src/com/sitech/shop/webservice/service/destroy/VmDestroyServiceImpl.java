/**   
 * Copyright: Copyright (c) 2014
 * Company: SI-TECH
 *
 * @Title: BandWidthDestroyServiceImpl.java 
 * @Package com.sitech.shop.service.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author wanglei_bj@si-tech.com.cn 
 * @date 2014-9-16 上午9:50:11 
 * @version V1.0   
 */
package com.sitech.shop.webservice.service.destroy;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import publiccloud.ServiceStatus;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedVMService;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.shop.service.vm.VirtualMachineService;
import com.sitech.shop.webservice.service.OrderRelation;
import com.sitech.ssd.billing.vo.order.OrderVO;
import com.sitech.ssd.billing.vo.order.ProInsVO;
import com.sitech.utils.http.HttpClientCustomUtil;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.publicShop.PublicCloudUtil;
import com.sitech.vo.united.VirtualMachinePowerStatus;
import com.sitech.vo.util.UnitedConstant;

/**
 * @ClassName: BandWidthDestroyServiceImpl
 * @Description: 带宽销毁实现类
 * @author wanglei_bj@si-tech.com.cn
 * @date 2014-9-16 上午9:50:11
 * @version 1.0
 */
@Service("vmDestroyService")
public class VmDestroyServiceImpl implements ResourceDestroyService<VMHostObj> {
	Logger logger = LoggerFactory.getLogger(VmDestroyServiceImpl.class);
	@Resource
	private VirtualMachineService virtualMachineService;
	@Resource
	private UnitedVMService unitedVMService;
	@Resource
	private OrderRelation orderRelationService;
	// 计费接口地址
	String billpath = PropertiesUtil.getString("properties/public_cloud", "billpath_status_mod");

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: checkRelation
	 * </p>
	 * <p>
	 * Description:检测虚拟机和其他资源的关系
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 * @return
	 * @throws Exception
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#checkRelation(java.lang.Object)
	 */
	@Override
	public Boolean checkRelation(VMHostObj t) throws Exception {
		return false;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: destroyResourse
	 * </p>
	 * <p>
	 * Description: 关机并销毁虚拟机
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 * @return
	 * @throws Exception
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#destroyResourse(java.lang.Object)
	 */
	@Override
	public Boolean destroyResourse(VMHostObj vm) throws Exception {
		logger.info("执行销毁虚拟机方法：开始");
		boolean result = true;

		VMHostObj obj = new VMHostObj();
		BeanUtils.copyProperties(obj, vm);
		
		// 0.封装接口对象
		UnitedTreeObj tree = new UnitedTreeObj();
		tree.setOper(VirtualMachinePowerStatus.powerOff);// 执行关机操作
		tree.setConnect_id(obj.getConnectId());
		tree.setUuid(obj.getVH_UUID());
		tree.setVtype(UnitedConstant.VMWARE);

		// 1.验证主机是否开机，开机则调用接口关机
		if (obj.getVH_STAT().equals(VirtualMachinePowerStatus.powerOn)) {
			try {
				logger.info("执行修改虚拟机电源：开始");
				String ret = virtualMachineService.putVMPowerState(tree);
				logger.info("修改虚拟机电源结果" + (ret.equals(UnitedConstant.SUCCESS) ? "成功" : "失败"));
			} catch (Exception e) {
				logger.error("修改虚拟机电源失败" + e.getMessage());
				result = false;
			}
		}

		// 2.调用接口销毁主机
		if (result) {
			try {
				logger.info("执行销毁主机：开始");
				String ret = unitedVMService.deleteVM(tree);
				logger.info("执行销毁主机结果" + (ret.equals(UnitedConstant.SUCCESS) ? "成功" : "失败"));
			} catch (Exception e) {
				logger.error("执行销毁主机失败" + e.getMessage());
				result = false;
			}
		}
		logger.info("执行销毁虚拟机方法：结束");
		return result;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: rollbackResourse
	 * </p>
	 * <p>
	 * Description: 如果主机已销毁，没办法回滚
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 * @return
	 * @throws Exception
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#rollbackResourse(java.lang.Object)
	 */
	@Override
	public Boolean rollbackResourse(VMHostObj obj) throws Exception {
		return true;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: recycleResourse
	 * </p>
	 * <p>
	 * Description: 删除主机数据中数据:在销毁的同时已经回收
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 * @return
	 * @throws Exception
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#recycleResourse(java.lang.Object)
	 */
	@Override
	public Boolean recycleResourse(VMHostObj t) throws Exception {
		return true;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: noticeBilling
	 * </p>
	 * <p>
	 * Description: 用户手动删除通知计费
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 * @return
	 * @throws Exception
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#noticeBilling(java.lang.Object)
	 */
	@Override
	public Boolean noticeBilling(VMHostObj vm) throws Exception {
		logger.info("手动删除资源通知计费接口执行开始:" + billpath);
		OrderVO orderVO = new OrderVO();
		List<ProInsVO> piList = new ArrayList<ProInsVO>();
		ProInsVO proInsVo = new ProInsVO();

		// 查询订购关系ID
		String product_instance_id = orderRelationService.getVmAndDiskRelationId(vm.getConnectId(),
				vm.getVH_UUID(), null)[0];
		// 查询对应的商城订购关系状态
		String status = PublicCloudUtil.getMallStatus(ServiceStatus.DESTROY);

		proInsVo.setProduct_instance_id(product_instance_id);
		proInsVo.setStatus(status);
		piList.add(proInsVo);
		orderVO.setPiList(piList);
		logger.debug("调用计费接口的对象为：" + JacksonUtil.formatJson(JacksonUtil.toJson(orderVO)));

		// 调用接口，并获得返回值
		OrderVO bill_result = HttpClientCustomUtil.post(billpath, orderVO,
				new JacksonUtil.TypeReference<OrderVO>() {
				});
		if (!bill_result.getIsSuccess()) {
			logger.error("手动删除资源通知计费接口执行失败:" + bill_result.getLog());
			// TODO 启动相关流程，手动通知计费等操作
			return false;
		} else {
			logger.info("手动删除资源通知计费接口执行成功!");
			return true;
		}
	}

}
