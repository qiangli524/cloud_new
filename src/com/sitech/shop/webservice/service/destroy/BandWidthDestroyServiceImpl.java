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

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import publiccloud.PublicCloudConstant;
import publiccloud.ScriptConstant;
import publiccloud.ServiceStatus;
import publiccloud.VlanType;

import com.sitech.basd.util.PropertyUtil;
import com.sitech.shop.dao.broadband.AclNumberDao;
import com.sitech.shop.dao.broadband.RuleNumberDao;
import com.sitech.shop.dao.vlan.PhysicalVlanDao;
import com.sitech.shop.domain.broadband.AclNumberObj;
import com.sitech.shop.domain.broadband.RuleNumberObj;
import com.sitech.shop.domain.vlan.PhysicalVlanObj;
import com.sitech.shop.webservice.service.OrderRelation;
import com.sitech.ssd.billing.vo.order.OrderVO;
import com.sitech.ssd.billing.vo.order.ProInsVO;
import com.sitech.utils.http.HttpClientCustomUtil;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.publicShop.PublicCloudSshUtil;
import com.sitech.utils.publicShop.PublicCloudUtil;

/**
 * @ClassName: BandWidthDestroyServiceImpl
 * @Description: 带宽销毁实现类
 * @author wanglei_bj@si-tech.com.cn
 * @date 2014-9-16 上午9:50:11
 * @version 1.0
 */
@Service("bandWidthDestroyService")
public class BandWidthDestroyServiceImpl implements ResourceDestroyService<PhysicalVlanObj> {
	Logger logger = LoggerFactory.getLogger(BandWidthDestroyServiceImpl.class);
	@Autowired
	private AclNumberDao aclNumberDao;
	@Autowired
	private RuleNumberDao ruleNumberDao;
	@Autowired
	private PhysicalVlanDao physicalVlanDao;
	@Autowired
	private PropertyUtil bjShellProp;
	@Autowired
	private OrderRelation orderRelationService;

	String billpath = PropertiesUtil.getString("properties/public_cloud", "billpath_status_mod");

	/**
	 * <p>
	 * Title: checkRelation
	 * </p>
	 * <p>
	 * Description: 查询用户是否有公网带宽
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 *            .user_id,t.vlan_type = VlanType.public_vlan
	 * @return
	 * @throws Exception
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#checkRelation(java.lang.Object)
	 */
	@Override
	public Boolean checkRelation(PhysicalVlanObj t) throws Exception {
		logger.info("检测用户带宽方法执行开始……");
		PhysicalVlanObj vlan = new PhysicalVlanObj();
		vlan.setUser_id(t.getUser_id());
		vlan.setVlan_type(VlanType.public_vlan);
		vlan.setService_type(t.getService_type());
		List<PhysicalVlanObj> vlanList = physicalVlanDao.queryForListByObj(vlan);
		if (vlanList != null && vlanList.size() == 1 && vlanList.get(0).getFlow_size() != null
				&& vlanList.get(0).getFlow_size() > 0) {
			logger.debug("用户带宽查询结果：" + JacksonUtil.formatJson(JacksonUtil.toJson(vlanList.get(0))));
			logger.info("检测用户带宽结果：用户存在公网带宽（单位M）：" + vlanList.get(0).getFlow_size());
			return true;
		} else {
			logger.info("检测用户带宽结果：用户没有公网带宽！");
			return false;
		}
	}

	/**
	 * <p>
	 * Title: destroyResourse
	 * </p>
	 * <p>
	 * Description: 销毁带宽
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 *            完整的PhysicalVlanObj 对象
	 * @return
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#destroyResourse(java.lang.Object)
	 */
	@Override
	public Boolean destroyResourse(PhysicalVlanObj vlanObj) throws Exception {
		logger.info("执行用户带宽销毁方法：开始");

		PhysicalVlanObj vlan = new PhysicalVlanObj();
		BeanUtils.copyProperties(vlan, vlanObj);
		
		String ip = vlan.getIp();// 限速的IP段信息
		String netmask = vlan.getSubnet_mask();// 要限速的子网掩码信息

		// 查询用户现有带宽的ACL号信息,取消的宽带限制
		AclNumberObj inaclNum = checkAcl(vlan.getFlow_size(), ScriptConstant.vlan_up);
		AclNumberObj outaclNum = checkAcl(vlan.getFlow_size(), ScriptConstant.vlan_down);

		// 查询户现有带宽的rule number
		RuleNumberObj inRuleNum = checkRuleNum("1", inaclNum.getId(), vlan.getUser_id());
		RuleNumberObj outRuleNum = checkRuleNum("1", outaclNum.getId(), vlan.getUser_id());
		String ipAddress = bjShellProp.getString("ip");
		String username = bjShellProp.getString("username");
		String password = bjShellProp.getString("password");

		String undoInCommand = commandBuilder("sw_ssh_undo_qos_in", inaclNum.getAcl(),
				inRuleNum.getRule(), ip, netmask);
		String inScriptResult = PublicCloudSshUtil.exeFWScript(ipAddress, username, password,
				undoInCommand.toString());
		if (PublicCloudConstant.SUCCESS.equals(inScriptResult)) {
			logger.info("执行脚本sw_ssh_undo_qos_in成功！脚本命令:" + undoInCommand);
		} else {
			logger.error("执行脚本sw_ssh_undo_qos_in失败！脚本命令:" + undoInCommand);
		}
		String undoOutCommand = commandBuilder("sw_ssh_undo_qos_out", outaclNum.getAcl(),
				outRuleNum.getRule(), ip, netmask);
		String outScriptResult = PublicCloudSshUtil.exeFWScript(ipAddress, username, password,
				undoOutCommand.toString());
		if (PublicCloudConstant.SUCCESS.equals(outScriptResult)) {
			logger.info("执行脚本sw_ssh_undo_qos_out成功！脚本命令:" + undoOutCommand);
		} else {
			logger.error("执行脚本sw_ssh_undo_qos_out失败！脚本命令:" + undoOutCommand);
		}
		logger.info("执行用户带宽销毁方法：结束");
		if (PublicCloudConstant.SUCCESS.equals(inScriptResult)
				&& PublicCloudConstant.SUCCESS.equals(outScriptResult)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <p>
	 * Title: rollbackResourse
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 *            需要传入完整的PhysicalVlanObj 对象，并设置销毁带宽之前的 flow_size
	 * @return
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#rollbackResourse(java.lang.Object)
	 */
	@Override
	public Boolean rollbackResourse(PhysicalVlanObj vlanObj) throws Exception {
		logger.info("执行用户带宽销毁-回滚方法：开始");

		PhysicalVlanObj vlan = new PhysicalVlanObj();
		BeanUtils.copyProperties(vlan, vlanObj);
		
		String ip = vlan.getIp();// 限速的IP段信息
		String netmask = vlan.getSubnet_mask();// 要限速的子网掩码信息

		// 查询用户现有带宽的ACL号信息,取消的宽带限制
		AclNumberObj inaclNum = checkAcl(vlan.getFlow_size(), ScriptConstant.vlan_up);
		AclNumberObj outaclNum = checkAcl(vlan.getFlow_size(), ScriptConstant.vlan_down);

		// 查询可用的rule number
		RuleNumberObj inRuleNum = checkRuleNum("0", inaclNum.getId(), null);
		RuleNumberObj outRuleNum = checkRuleNum("0", outaclNum.getId(), null);
		String ipAddress = bjShellProp.getString("ip");
		String username = bjShellProp.getString("username");
		String password = bjShellProp.getString("password");

		String inCommand = commandBuilder("sw_ssh_qos_in", inaclNum.getAcl(), inRuleNum.getRule(),
				ip, netmask);
		String inScriptResult = PublicCloudSshUtil.exeFWScript(ipAddress, username, password,
				inCommand.toString());
		if (PublicCloudConstant.SUCCESS.equals(inScriptResult)) {
			inRuleNum.setIsused("1");
			inRuleNum.setUser_id(vlan.getUser_id());
			ruleNumberDao.updataRuleNum(inRuleNum);
			logger.info("调用sw_ssh_qos_in脚本成功!脚本命令：" + inCommand);
		} else {
			logger.error("调用sw_ssh_qos_in脚本失败!脚本命令：" + inCommand);
			throw new Exception("调用sw_ssh_qos_in脚本失败!脚本命令：" + inCommand);
		}

		String outCommand = commandBuilder("sw_ssh_qos_out", outaclNum.getAcl(),
				outRuleNum.getRule(), ip, netmask);
		String outScriptResult = PublicCloudSshUtil.exeFWScript(ipAddress, username, password,
				outCommand.toString());
		if (PublicCloudConstant.SUCCESS.equals(outScriptResult)) {
			outRuleNum.setIsused("1");
			outRuleNum.setUser_id(vlan.getUser_id());
			ruleNumberDao.updataRuleNum(outRuleNum);
			logger.info("调用sw_ssh_qos_out脚本成功!脚本命令：" + outCommand);
		} else {
			logger.error("调用sw_ssh_qos_out脚本失败!脚本命令：" + outCommand);
			throw new Exception("调用sw_ssh_qos_out脚本失败!脚本命令：" + outCommand);
		}
		// 调用脚本对公网带宽做限速
		vlan.setUser_id("");
		vlan.setFlow_size(vlan.getFlow_size());
		logger.debug("要更新的vlan信息为：" + JacksonUtil.formatJson(JacksonUtil.toJson(vlan)));
		int result = physicalVlanDao.updateByObj(vlan);
		logger.info("执行用户带宽销毁-回滚方法：结束");
		return result > 0;
	}

	/**
	 * <p>
	 * Title: recycleResourse
	 * </p>
	 * <p>
	 * Description:回收数据库资源，主要是回收规则号。
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 * @return
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#recycleResourse(java.lang.Object)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean recycleResourse(PhysicalVlanObj vlanObj) throws Exception{
		logger.info("执行回收带宽&规则号方法：开始");
		PhysicalVlanObj vlan = new PhysicalVlanObj();
		BeanUtils.copyProperties(vlan, vlanObj);
		Boolean result = true;
		try {
			// 查询用户现有带宽的ACL号信息,取消的宽带限制
			AclNumberObj inaclNum = checkAcl(vlan.getFlow_size(), ScriptConstant.vlan_up);
			AclNumberObj outaclNum = checkAcl(vlan.getFlow_size(), ScriptConstant.vlan_down);

			// 查询户现有带宽的rule number
			RuleNumberObj inRuleNum = checkRuleNum("1", inaclNum.getId(), vlan.getUser_id());
			logger.debug("回收ACL号：" + inaclNum + ",Rule号：" + inRuleNum);
			RuleNumberObj outRuleNum = checkRuleNum("1", outaclNum.getId(), vlan.getUser_id());
			logger.debug("回收ACL号：" + outaclNum + ",Rule号：" + outRuleNum);
			// 回收规则号
			ruleNumberDao.releaseRuleNum(inRuleNum);
			ruleNumberDao.releaseRuleNum(outRuleNum);
			// 流量设置为0
			vlan.setFlow_size(0);
			physicalVlanDao.updateByObj(vlan);
		} catch (Exception e) {
			logger.error("执行回收带宽&规则号方法失败！", e);
			result = false;
		}
		logger.info("执行回收带宽&规则号方法：结束");
		return result;

	}

	/**
	 * <p>
	 * Title: noticeBilling
	 * </p>
	 * <p>
	 * Description:通过控制手动删除资源，通知计费
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param t
	 * @return
	 * @see com.sitech.shop.service.destroy.ResourceDestroyService#noticeBilling(java.lang.Object)
	 */
	@Override
	public Boolean noticeBilling(PhysicalVlanObj vlanObj) throws Exception {
		logger.info("手动删除资源通知计费接口执行开始:" + billpath);
		
		PhysicalVlanObj vlan = new PhysicalVlanObj();
		BeanUtils.copyProperties(vlan, vlanObj);
		
		OrderVO orderVO = new OrderVO();
		List<ProInsVO> piList = new ArrayList<ProInsVO>();
		ProInsVO proInsVo = new ProInsVO();

		// 查询订购关系ID
		String product_instance_id = orderRelationService.getVlanId(vlan.getVlan_id());
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

	// 检查ACL
	private AclNumberObj checkAcl(Integer rate, String operType) {
		AclNumberObj aclNumObj = new AclNumberObj();
		aclNumObj.setRate(calRate(rate));
		aclNumObj.setOper_type(operType);
		List<AclNumberObj> aclNumList = queryAclNumList(aclNumObj);
		Assert.isTrue(aclNumList != null && aclNumList.size() == 1, "查询到的ACL号错误!!!,要查询的ACL号类型为："
				+ operType + ", 带宽为:" + rate);
		return aclNumList.get(0);
	}

	// 计算宽带信息，20-25
	private Integer calRate(Integer rate) {
		if (rate >= 1 && rate <= 20) {
			return rate;
		} else if (rate > 20 && rate <= 100) {
			int m = rate / 5;
			int n = rate % 5;
			if (n > 0) {
				return (m + 1) * 5;
			} else
				return m * 5;
		} else if (rate > 100 && rate <= 300) {
			int m = rate / 10;
			int n = rate % 10;
			if (n > 0) {
				return (m + 1) * 10;
			} else
				return m * 10;
		}
		return rate;
	}

	// 检查rule number
	private RuleNumberObj checkRuleNum(String isUsed, String aclId, String userId) {
		RuleNumberObj ruleNum = new RuleNumberObj();
		ruleNum.setType(ScriptConstant.switches);
		ruleNum.setIsused(isUsed);
		ruleNum.setAcl_id(aclId);
		ruleNum.setUser_id(userId);
		List<RuleNumberObj> ruleNumList = ruleNumberDao.queryRuleNumList(ruleNum);
		Assert.isTrue(ruleNumList != null && ruleNumList.size() >= 1,
				"无可用的rule number信息!!!,要查询的ruleNum使用情况为：" + isUsed + "ACL number为：" + aclId);// 应该有一个可用的规则号
		return ruleNumList.get(0);
	}

	/**
	 * 查询ACL号信息
	 * 
	 * @param aclNumObj
	 * @return acl号信息列表
	 */
	private List<AclNumberObj> queryAclNumList(AclNumberObj aclNumObj) {
		List<AclNumberObj> aclNumList = aclNumberDao.queryAclNumList(aclNumObj);
		return aclNumList;
	}

	/**
	 * 脚本拼接方法
	 * 
	 * @param type
	 * @return
	 */
	private String commandBuilder(String type, Integer aclNum, Integer ruleNum, String ip,
			String netmask) {
		StringBuilder command = new StringBuilder();
		command.append(bjShellProp.getString("sw_path")).append(" ").append(type).append(" ")
				.append(aclNum).append(" ").append(ruleNum).append(" ").append(ip).append(" ")
				.append(netmask);
		logger.debug("脚本调用：" + command.toString());
		return command.toString();
	}

}
