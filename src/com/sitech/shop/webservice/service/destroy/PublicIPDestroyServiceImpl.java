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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import publiccloud.PublicCloudConstant;
import publiccloud.ServiceStatus;

import com.sitech.basd.sxcloud.cloud.dao.net.TbIpDao;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.shop.dao.publicIP.PublicIPDao;
import com.sitech.shop.domain.ip.PublicIPObj;
import com.sitech.shop.webservice.service.OrderRelation;
import com.sitech.ssd.ah.nas.dao.NasFileSysDao;
import com.sitech.ssd.billing.vo.order.OrderVO;
import com.sitech.ssd.billing.vo.order.ProInsVO;
import com.sitech.utils.http.HttpClientCustomUtil;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.publicShop.PublicCloudSshUtil;
import com.sitech.utils.publicShop.PublicCloudUtil;

/**
 * @ClassName: PublicIPDestroyServiceImpl
 * @Description: TODO(删除公网ip)
 * @author wanglei_bj@si-tech.com.cn
 * @date 2014-9-12 下午2:00:30
 * @version 1.0
 */
@Service("publicIPDestroyService")
public class PublicIPDestroyServiceImpl implements ResourceDestroyService<PublicIPObj> {
	private static final Logger logger = LoggerFactory.getLogger(PublicIPDestroyServiceImpl.class);
	@Autowired
	private PublicIPDao publicIPDao;
	@Autowired
	private PropertyUtil bjShellProp;
	@Autowired
	private OrderRelation orderRelationService;
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
	 * @see com.sitech.shop.service.ResourceDestroyService#checkRelation(java.lang.Object)
	 */
	@Override
	public Boolean checkRelation(PublicIPObj t)  throws Exception {
		logger.info("检测公网IP方法执行开始……");
		PublicIPObj ipObj = new PublicIPObj();
		ipObj.setIpaddress(t.getIpaddress());
		ipObj.setUser_id(t.getUser_id());
		List<PublicIPObj> ipList = publicIPDao.queryForListByObj(ipObj);
		if (ipList != null && ipList.size() == 1) {
			logger.debug("公网IP查询结果：" + JacksonUtil.formatJson(JacksonUtil.toJson(ipList.get(0))));
			if (StringUtils.isBlank(ipList.get(0).getIntranet_ip())) {
				logger.info("检测公网IP结果：该公网IP没有绑定内网IP！");
				return false;
			}

			logger.info("检测公网IP方法执行结束");
			return true;
		} else {
			logger.info("检测公网IP结果：没有该公网IP！");
			return false;
		}
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: destroyResourse
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param ipObj
	 *            中的内网ip一定是最新的
	 * @return
	 * @throws Exception 
	 * @see com.sitech.shop.service.ResourceDestroyService#destroyResourse(java.lang.Object)
	 */
	@Override
	public Boolean destroyResourse(PublicIPObj ipObj) throws Exception {
		logger.info("执行公网IP解除绑定关系方法：开始");
		logger.debug("ip信息：" + JacksonUtil.formatJson(JacksonUtil.toJson(ipObj)));
		boolean flag =false;
		try {
			// 调用脚本解除绑定关系
			List<PublicIPObj> list = publicIPDao.queryForListByObj(ipObj);
			if (list != null && list.size() > 0) {
				ipObj = list.get(0);
			} else {
				logger.info("检测公网IP结果：没有该公网IP！");
			}
			// 1.调用脚本解除绑定公网IP地址
			String ipAddress = bjShellProp.getString("ip");
			String username = bjShellProp.getString("username");
			String password = bjShellProp.getString("password");

			StringBuilder command = new StringBuilder();
			command.append(bjShellProp.getString("path")).append(" ").append(
					bjShellProp.getString("bind")).append(" ").append(
					ipObj.getIpaddress()).append(" ").append(
					ipObj.getIntranet_ip());
			String cmd = command.toString();
			logger.info("执行解绑定公网IP脚本：" + cmd);

			String result = PublicCloudSshUtil.exeFWScript(ipAddress, username,
					password, cmd);
			if (PublicCloudConstant.SUCCESS.equals(result)) {
				logger.info("执行公网IP解除绑定关系方法：结束");
				flag= true;
			} else {
				logger.error("执行解绑公网IP脚本：失败");
			}
		} catch (Exception e) {
			logger.error("执行解绑公网IP脚本：失败",e);
			throw new Exception(e);
		}
		return flag;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: recycleResourse
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 * @return
	 * @throws Exception 
	 * @see com.sitech.shop.service.ResourceDestroyService#recycleResourse(java.lang.Object)
	 */
	@Override
	public Boolean recycleResourse(PublicIPObj ipObj) throws Exception {
		logger.info("回收公网IP方法：开始");
		logger.debug("ip信息：" + JacksonUtil.formatJson(JacksonUtil.toJson(ipObj)));
		boolean flag = false;
		try {
			publicIPDao.releaseByObj(ipObj);// 释放公网IP和虚拟机的绑定关系
			flag=true;
		} catch (Exception e) {
			logger.error("回收公网IP:失败",e);
			throw new Exception(e);
		}

		logger.info("回收公网IP方法：结束");
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
	 * @throws Exception 
	 * @see com.sitech.shop.service.ResourceDestroyService#noticeBilling(java.lang.Object)
	 */
	@Override
	public Boolean noticeBilling(PublicIPObj t) throws Exception {
		// 1.查询订购关系ID
		// 2.发送到计费
		logger.info("手动删除资源通知计费接口执行开始:" + billpath);
		logger.debug("公网IP的对象为：" + JacksonUtil.formatJson(JacksonUtil.toJson(t)));
		OrderVO orderVO = new OrderVO();
		List<ProInsVO> piList = new ArrayList<ProInsVO>();
		ProInsVO proInsVo = new ProInsVO();
		boolean flag= false;
		try {
			// 查询订购关系ID
			String product_instance_id = orderRelationService.getIPId(t.getIP_ID());
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
			} else {
				logger.info("手动删除资源通知计费接口执行成功!");
				flag= true;
			}

		} catch (Exception e) {
			logger.error("手动删除资源通知计费接口执行失败",e);
			throw new Exception(e);
		}
		return flag;
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
	 * @throws Exception 
	 * @see com.sitech.shop.service.ResourceDestroyService#rollbackResourse(java.lang.Object)
	 */
	@Override
	public Boolean rollbackResourse(PublicIPObj ipObj) throws Exception {
		PublicIPObj newObj=new PublicIPObj();
		BeanUtils.copyProperties(newObj, ipObj);
		logger.info("执行公网IP绑定关系方法：开始");
		boolean flag =false;
		try {
			StringBuilder command = new StringBuilder();
			command.append(bjShellProp.getString("path")).append(" ")
					.append(bjShellProp.getString("bind")).append(" ").append(ipObj.getIpaddress())
					.append(" ").append(ipObj.getIntranet_ip());
			String cmd = command.toString();
			// 1.调用脚本绑定公网IP地址
			String ipAddress = bjShellProp.getString("ip");
			String username = bjShellProp.getString("username");
			String password = bjShellProp.getString("password");
			logger.info("执行绑定公网IP脚本：" + cmd);
			String result = PublicCloudSshUtil.exeFWScript(ipAddress, username, password, cmd);
			if (PublicCloudConstant.SUCCESS.equals(result)) {
				// 2.将公网IP地址中心的部分信息进行更新
				logger.info("执行绑定公网IP脚本：成功");
				newObj.setStatus(1);
				newObj.setIntranet_ip(ipObj.getIntranet_ip());
				publicIPDao.updateByObj(newObj);// 更新被绑定的公网IP地址的记录
				flag= true;
			} else {
				logger.error("执行绑定公网IP脚本：失败");
			}
		} catch (Exception e) {
			logger.error("执行绑定公网IP脚本：失败");
			throw new Exception(e);
		}
		return flag;
	}
}
