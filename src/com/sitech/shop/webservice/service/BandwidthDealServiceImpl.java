package com.sitech.shop.webservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import publiccloud.PublicCloudConstant;
import publiccloud.ScriptConstant;
import publiccloud.VlanType;

import com.sitech.basd.util.PropertyUtil;
import com.sitech.shop.dao.broadband.AclNumberDao;
import com.sitech.shop.dao.broadband.RuleNumberDao;
import com.sitech.shop.dao.vlan.PhysicalVlanDao;
import com.sitech.shop.domain.broadband.AclNumberObj;
import com.sitech.shop.domain.broadband.RuleNumberObj;
import com.sitech.shop.domain.vlan.PhysicalVlanObj;
import com.sitech.ssd.billing.vo.resourceInfo.VmInfo;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.publicShop.PublicCloudSshUtil;

@Service("bandwidthDealService")
public class BandwidthDealServiceImpl implements BandwidthDealService {
	Logger logger = LoggerFactory.getLogger(BandwidthDealServiceImpl.class);
	@Autowired
	private AclNumberDao aclNumberDao;
	@Autowired
	private RuleNumberDao ruleNumberDao;
	@Autowired
	private PhysicalVlanDao physicalVlanDao;
	@Autowired
	private PropertyUtil bjShellProp;

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Title: applyBandwidth
	 * @Description: 购买公网带宽
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-3 下午5:52:31
	 */
	public VmInfo applyBandwidthProcess(VmInfo info) throws Exception {
		logger.info("执行用户购买公网带宽方法：开始");
		logger.debug("执行购买带宽方法，传入的VmInfo参数为：" + JacksonUtil.formatJson(JacksonUtil.toJson(info)));
		try {
			VmInfo vmi = info.clone();
			String userId = info.getUser_id();
			Assert.notNull(userId, "购买带宽的用户不能为空!!!");
			// 查询用户公网的
			PhysicalVlanObj vlan = checkVlan(userId, info.getUser_Type());
			String ip = vlan.getIp();// 限速的IP段信息
			String netmask = vlan.getSubnet_mask();// 要限速的子网掩码信息
			// 检查ACL信息
			AclNumberObj inaclNum = checkAcl(info.getBand_width(), ScriptConstant.vlan_up);
			AclNumberObj outaclNum = checkAcl(info.getBand_width(), ScriptConstant.vlan_down);
			// 查询可用的rule number
			RuleNumberObj inRuleNum = checkRuleNum("0", inaclNum.getId(), null);
			RuleNumberObj outRuleNum = checkRuleNum("0", outaclNum.getId(), null);

			String ipAddress = bjShellProp.getString("ip");
			String username = bjShellProp.getString("username");
			String password = bjShellProp.getString("password");

			String inCommand = commandBuilder("sw_ssh_qos_in", inaclNum.getAcl(),
					inRuleNum.getRule(), ip, netmask);
			String inScriptResult = PublicCloudSshUtil.exeFWScript(ipAddress, username, password,
					inCommand.toString());
			if (PublicCloudConstant.SUCCESS.equals(inScriptResult)) {
				inRuleNum.setIsused("1");
				// inRuleNum.setAcl_id(inRuleNum.getId());
				inRuleNum.setUser_id(userId);
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
				// outRuleNum.setAcl_id(outaclNum.getId());
				outRuleNum.setUser_id(userId);
				ruleNumberDao.updataRuleNum(outRuleNum);
				logger.info("调用sw_ssh_qos_out脚本成功!脚本命令：" + outCommand);
			} else {
				logger.error("调用sw_ssh_qos_out脚本失败!脚本命令：" + outCommand);
				throw new Exception("调用sw_ssh_qos_out脚本失败!脚本命令：" + outCommand);
			}
			// 调用脚本对公网带宽做限速
			vlan.setUser_id("");
			// vlan.setArea_id(info.getArea_id());
			vlan.setFlow_size(info.getBand_width());
			logger.debug("要更新的vlan信息为：" + JacksonUtil.formatJson(JacksonUtil.toJson(vlan)));
			physicalVlanDao.updateByObj(vlan);
			vmi.setVlan_id(vlan.getVlan_id());
			vmi.setIsSuccess(true);
			logger.debug("执行用户购买公网带宽方法结果：" + vmi.getIsSuccess());
			logger.info("执行用户购买公网带宽方法：结束");
			return vmi;
		} catch (Exception e) {
			logger.error("执行用户购买公网带宽方法：异常 " + e.getMessage());
			throw new Exception(e);
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

	/**
	 * @param
	 * @return VmInfo
	 * @throws
	 * @Title: expandBandwidthProcess
	 * @Description: 公网带宽变更
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-7 下午4:38:16
	 */

	public VmInfo expandBandwidthProcess(VmInfo info) throws Exception {
		logger.info("执行用户变更公网带宽方法：开始");
		try {
			VmInfo vmi = info.clone();
			// 查询用户公网的
			String userId = info.getUser_id();
			PhysicalVlanObj vlan = checkVlan(userId, info.getPayment_type());

			String ip = vlan.getIp();// 限速的IP段信息
			String netmask = vlan.getSubnet_mask();// 要限速的子网掩码信息

			// 查询未升级之前的ACL号信息,先取消之前的宽带限制
			AclNumberObj inaclNum = checkAcl(vlan.getFlow_size(), ScriptConstant.vlan_up);
			AclNumberObj outaclNum = checkAcl(vlan.getFlow_size(), ScriptConstant.vlan_down);

			// 查询之前使用的rule number
			RuleNumberObj inRuleNum = checkRuleNum("1", inaclNum.getId(), userId);
			RuleNumberObj outRuleNum = checkRuleNum("1", outaclNum.getId(), userId);

			// 检查要升级的acl信息
			AclNumberObj newInaclNum = checkAcl(info.getBand_width(), ScriptConstant.vlan_up);
			AclNumberObj newOutaclNum = checkAcl(info.getBand_width(), ScriptConstant.vlan_down);

			// 查询升级后可以使用的rule number
			RuleNumberObj newInRuleNum = checkRuleNum("0", newInaclNum.getId(), null);
			RuleNumberObj newOutRuleNum = checkRuleNum("0", newOutaclNum.getId(), null);

			String ipAddress = bjShellProp.getString("ip");
			String username = bjShellProp.getString("username");
			String password = bjShellProp.getString("password");

			String undoInCommand = commandBuilder("sw_ssh_undo_qos_in", inaclNum.getAcl(),
					inRuleNum.getRule(), ip, netmask);
			String inScriptResult = PublicCloudSshUtil.exeFWScript(ipAddress, username, password,
					undoInCommand.toString());
			if (PublicCloudConstant.SUCCESS.equals(inScriptResult)) {
				logger.info("执行脚本sw_ssh_undo_qos_in成功！脚本命令:" + undoInCommand);
				ruleNumberDao.releaseRuleNum(inRuleNum);
			} else {
				logger.error("执行脚本sw_ssh_undo_qos_in失败！脚本命令:" + undoInCommand);
				throw new Exception("执行脚本sw_ssh_undo_qos_in失败！脚本命令:" + undoInCommand);
			}
			String undoOutCommand = commandBuilder("sw_ssh_undo_qos_out", outaclNum.getAcl(),
					outRuleNum.getRule(), ip, netmask);
			String outScriptResult = PublicCloudSshUtil.exeFWScript(ipAddress, username, password,
					undoOutCommand.toString());
			if (PublicCloudConstant.SUCCESS.equals(outScriptResult)) {
				logger.info("执行脚本sw_ssh_undo_qos_out成功！脚本命令:" + undoOutCommand);
				ruleNumberDao.releaseRuleNum(outRuleNum);
			} else {
				logger.error("执行脚本sw_ssh_undo_qos_out失败！脚本命令:" + undoOutCommand);
				throw new Exception("执行脚本sw_ssh_undo_qos_out失败！脚本命令:" + undoOutCommand);
			}

			String newInCommand = commandBuilder("sw_ssh_qos_in", newInaclNum.getAcl(),
					newInRuleNum.getRule(), ip, netmask);
			String newInScriptResult = PublicCloudSshUtil.exeFWScript(ipAddress, username,
					password, newInCommand.toString());
			if (PublicCloudConstant.SUCCESS.equals(newInScriptResult)) {
				logger.info("调用脚本sw_ssh_qos_in成功！脚本命令: " + newInCommand);
				// newInRuleNum.setAcl_id(newInaclNum.getId());
				newInRuleNum.setIsused("1");
				newInRuleNum.setUser_id(userId);
				ruleNumberDao.updataRuleNum(newInRuleNum);
			} else {
				logger.error("调用脚本sw_ssh_qos_in失败！脚本命令: " + newInCommand);
				throw new Exception("调用脚本sw_ssh_qos_in失败！脚本命令: " + newInCommand);
			}

			String newOutCommand = commandBuilder("sw_ssh_qos_out", newOutaclNum.getAcl(),
					newOutRuleNum.getRule(), ip, netmask);
			String newOutScriptResult = PublicCloudSshUtil.exeFWScript(ipAddress, username,
					password, newOutCommand.toString());
			if (PublicCloudConstant.SUCCESS.equals(newOutScriptResult)) {
				logger.info("调用脚本sw_ssh_qos_out成功！脚本命令: " + newOutCommand);
				// newOutRuleNum.setAcl_id(newOutaclNum.getId());
				newOutRuleNum.setIsused("1");
				newOutRuleNum.setUser_id(userId);
				ruleNumberDao.updataRuleNum(newOutRuleNum);
			} else {
				logger.error("调用脚本sw_ssh_qos_out失败！脚本命令: " + newOutCommand);
				throw new Exception("调用脚本sw_ssh_qos_out失败！脚本命令: " + newOutCommand);
			}

			// vlan.setArea_id(info.getArea_id());
			vlan.setFlow_size(info.getBand_width());
			vlan.setUser_id("");
			physicalVlanDao.updateByObj(vlan);
			vmi.setVlan_id(vlan.getVlan_id());
			vmi.setIsSuccess(true);
			logger.debug("执行用户变更公网带宽方法结果：" + vmi.getIsSuccess());
			logger.debug("当前用户没有对应的公网vlan。用户为：" + info.getUser_id() + "地域为：" + info.getArea_id());
			return vmi;
		} catch (Exception e) {
			logger.error("执行用户变更公网带宽方法:异常" + e.getMessage());
			throw new Exception(e);
		}
	}

	// 检查用户的vlan信息
	private PhysicalVlanObj checkVlan(String userId, String userType) throws Exception {
		// 查询用户公网的
		PhysicalVlanObj vlan = new PhysicalVlanObj();
		vlan.setUser_id(userId);
		vlan.setVlan_type(VlanType.public_vlan);
		vlan.setService_type(userType);
		List<PhysicalVlanObj> vlanList = physicalVlanDao.queryForListByObj(vlan);
		Assert.isTrue(vlanList != null && vlanList.size() == 1, "查询到的VLAN信息错误!!!,用户应该有且一条公网VLAN!");
		return vlanList.get(0);
	}

}
