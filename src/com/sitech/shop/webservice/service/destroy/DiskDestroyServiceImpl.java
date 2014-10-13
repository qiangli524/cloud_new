/**   
 * Copyright: Copyright (c) 2014
 * Company: SI-TECH
 *
 * @Title: PublicIPDestroyServiceImpl.java 
 * @Package com.sitech.shop.webservice.service.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author wanglei_bj@si-tech.com.cn 
 * @date 2014-9-12 下午2:00:30 
 * @version V1.0   
 */
package com.sitech.shop.webservice.service.destroy;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import publiccloud.ServiceStatus;
import publiccloud.VirtualDiskStatus;

import com.sitech.basd.resource.util.VirtualClient;
import com.sitech.shop.dao.disk.VirtualDiskDao;
import com.sitech.shop.dao.disk.VmDiskRelationDao;
import com.sitech.shop.domain.disk.VirtualDiskObj;
import com.sitech.shop.domain.disk.VmDiskRelationObj;
import com.sitech.shop.webservice.service.OrderRelation;
import com.sitech.ssd.billing.vo.order.OrderVO;
import com.sitech.ssd.billing.vo.order.ProInsVO;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.http.HttpClientCustomUtil;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.publicShop.PublicCloudUtil;
import com.sitech.vo.united.ResultSet;
import com.sitech.vo.united.VirtualDiskUnitedVO;
import com.sitech.vo.util.UnitedRestURI;
import com.sitech.vo.util.VirtualConstant;

/**
 * @ClassName: DiskDestroyServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author jily@si-tech.com.cn
 * @date 2014-9-12 下午2:00:30
 * @version 1.0
 */
@Service("diskDestroyService")
public class DiskDestroyServiceImpl implements ResourceDestroyService<VirtualDiskObj> {
	private static final Logger logger = LoggerFactory.getLogger(DiskDestroyServiceImpl.class);
	@Autowired
	private VirtualDiskDao virtualDiskDao;

	@Autowired
	private VmDiskRelationDao vmDiskRelationDao;

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
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 * @return
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#checkRelation(java.lang.Object)
	 */
	@Override
	public Boolean checkRelation(VirtualDiskObj t) throws Exception {
		logger.info("检测磁盘方法执行开始……");
		logger.debug("磁盘信息：" + JacksonUtil.formatJson(JacksonUtil.toJson(t)));
		List<VirtualDiskObj> diskList = virtualDiskDao.queryForListByObj(t);
		if (diskList != null && diskList.size() == 1) {
			logger.debug("磁盘查询结果：" + JacksonUtil.formatJson(JacksonUtil.toJson(diskList.get(0))));
			logger.info("检测磁盘方法执行结束！");
			return true;
		} else {
			logger.info("检测磁盘结果：没有该磁盘信息！");
			return false;
		}
	}

	/**
	 * 
	 * @Title: queryRelateDisks
	 * @Description: 查询和主机关联的磁盘列表
	 * @param @param t
	 * @param @return 设定文件
	 * @return List<VirtualDiskObj> 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 * @createtime 2014-9-17
	 */
	public List<VirtualDiskObj> queryRelatedDisks(VirtualDiskObj disk) {
		List<VirtualDiskObj> diskList = virtualDiskDao.queryForListByObj(disk);
		return diskList;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: destroyResourse
	 * </p>
	 * <p>
	 * Description:磁盘卸载，如果磁盘已经卸载直接返回true
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @throws Exception 
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#destroyResourse(java.lang.Object)
	 */
	@Override
	public Boolean destroyResourse(VirtualDiskObj obj) throws Exception {
		VirtualDiskObj newObj=new VirtualDiskObj();
		BeanUtils.copyProperties(newObj, obj);
		logger.info("执行磁盘卸载方法：开始");
		logger.debug("磁盘信息：" + JacksonUtil.formatJson(JacksonUtil.toJson(obj)));
		VirtualDiskUnitedVO result = new VirtualDiskUnitedVO();
		boolean flag = false;
		if (obj.getState().equals("0")) {
			logger.info("该磁盘已经卸载！");
			logger.info("执行磁盘卸载方法：结束");
			return true;
		}
		try {
			// 请求接口卸载虚拟磁盘
			String url = "virtualhardware/disk/remove/true?vType=" + VirtualConstant.VT_VMWARE
					+ "&connectCode=" + obj.getConnectCode() + "&datacenterCode="
					+ obj.getDatacenterCode() + "&vmCode=" + obj.getVm_uuid() + "&vmdkLabel="
					+ obj.getLabel();

			logger.debug("调用vcenter接口url信息：" + url);
			result = VirtualClient.delete(url,
					new JacksonUtil.TypeReference<VirtualDiskUnitedVO>() {
					});

			logger.debug("磁盘vo信息：" + JacksonUtil.formatJson(JacksonUtil.toJson(result)));
		} catch (Exception e) {
			logger.error("请求接口卸载虚拟磁盘：失败！",e);
			throw new Exception(e);
		}

		if (result.getIsSuccess()) {
			// 删除数据库信息
			VmDiskRelationObj relationObj = new VmDiskRelationObj();
			relationObj.setConnect_id(obj.getConnectCode());
			relationObj.setVm_uuid(obj.getVm_uuid());
			relationObj.setVirdisk_id(obj.getId());
			vmDiskRelationDao.deleteByObj(relationObj);

			// 更新磁盘状态
			newObj.setState(VirtualDiskStatus.unmount);
			virtualDiskDao.updateByPath(newObj);

			// 更新label
			List<VirtualDiskUnitedVO> virList = result.getSelfList();
			for (VirtualDiskUnitedVO vo : virList) {
				VirtualDiskObj virtualDiskObj = new VirtualDiskObj();
				virtualDiskObj.setPath(vo.getVmdkPath());
				virtualDiskObj.setConnectCode(vo.getConnectCode());
				virtualDiskObj.setDatacenterCode(vo.getDatacenterCode());
				virtualDiskObj.setLabel(vo.getVmdkLabel());
				try {
					virtualDiskDao.updateByPath(virtualDiskObj);
				} catch (Exception e) {
					logger.debug("磁盘label信息："
							+ JacksonUtil.formatJson(JacksonUtil.toJson(virtualDiskObj)));
					logger.error("更新label：失败！",e);
					throw new Exception(e);
				}
			}
			flag=true;
		} else {
			logger.error("请求接口删除虚拟磁盘：失败！");
		}

		logger.info("执行磁盘卸载方法：结束");
		return flag;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: recycleResourse
	 * </p>
	 * <p>
	 * Description:删除磁盘，磁盘卸载后才能删除
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 * @return
	 * @throws Exception 
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#recycleResourse(java.lang.Object)
	 */
	@Override
	public Boolean recycleResourse(VirtualDiskObj obj) throws Exception {
		logger.info("删除磁盘方法:开始");
		// String url = "virtualhardware/disk/destory?vType=" +
		// VirtualConstant.VT_VMWARE +
		// "&connectCode="+obj.getConnectCode() + "&datacenterCode=" +
		// obj.getDatacenterCode()
		// + "&vmdkPath="+obj.getPath();
		logger.debug("磁盘信息：" + JacksonUtil.formatJson(JacksonUtil.toJson(obj)));
		boolean flag = false;
		VirtualDiskUnitedVO result = new VirtualDiskUnitedVO();
		String vType = VirtualConstant.VT_VMWARE;
		String param = UnitedRestURI.paramvType + vType + UnitedRestURI.paramconnectCode
				+ obj.getConnectCode()  + "&datacenterCode="
				+ obj.getDatacenterCode() + "&vmdkPath=" + obj.getPath();
		try {
			String url = UnitedRestURI.VMHARDWAREURI + "disk/destory" + param;
			logger.debug("调用vcenter接口url信息：" + url);
			result = VirtualClient.delete(url, new JacksonUtil.TypeReference<VirtualDiskUnitedVO>() {
			});

			logger.debug("调用vcenter接口返回信息：" + JacksonUtil.formatJson(JacksonUtil.toJson(result)));
			if (result.getIsSuccess()) {
				// 信息同步给商城后台，待开发，此处如果失败，不进行下面的操作，通知运维处理
				// 删除数据库信息
				virtualDiskDao.deleteByObj(obj);
				VmDiskRelationObj relationObj = new VmDiskRelationObj();
				relationObj.setVirdisk_id(obj.getId());
				vmDiskRelationDao.deleteByObj(relationObj);
			} else {
				logger.error("调用vcenter接口：失败！");
			}
			flag =true;
		} catch (HttpClientException e) {
			logger.error("磁盘删除：失败！",e);
			throw new Exception(e);
		}
		logger.info("删除磁盘方法:结束！");
		return flag;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: noticeBilling
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 * @return
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#noticeBilling(java.lang.Object)
	 */
	@Override
	public Boolean noticeBilling(VirtualDiskObj disk) throws Exception {
		logger.info("手动删除磁盘通知计费接口执行开始:" + billpath);
		logger.debug("调用计费接口的对象为：" + JacksonUtil.formatJson(JacksonUtil.toJson(disk)));
		OrderVO orderVO = new OrderVO();
		List<ProInsVO> piList = new ArrayList<ProInsVO>();
		ProInsVO proInsVo = new ProInsVO();

		// 查询订购关系ID
		String product_instance_id = orderRelationService.getVmAndDiskRelationId(
				disk.getConnectCode(), null, disk.getId())[0];
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
			logger.error("手动删除磁盘通知计费接口执行失败:" + bill_result.getLog());
			// TODO 启动相关流程，手动通知计费等操
			return false;
		} else {
			logger.info("手动删除磁盘通知计费接口执行结束!");
			return true;
		}
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: rollbackResourse
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param ipObj
	 *            中的内网ip一定是最新的
	 * @return
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#rollbackResourse(java.lang.Object)
	 */
	@Override
	public Boolean rollbackResourse(VirtualDiskObj ipObj) {
		return true;
	}
}
