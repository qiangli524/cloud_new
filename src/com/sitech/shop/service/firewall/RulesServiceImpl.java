package com.sitech.shop.service.firewall;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import publiccloud.QuotaConstant;
import publiccloud.ScriptConstant;

import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.shop.dao.broadband.AclNumberDao;
import com.sitech.shop.dao.broadband.RuleNumberDao;
import com.sitech.shop.dao.firewall.RulesDao;
import com.sitech.shop.domain.broadband.AclNumberObj;
import com.sitech.shop.domain.broadband.RuleNumberObj;
import com.sitech.shop.domain.firewall.RulesObj;
import com.sitech.utils.common.CommonUtil;
import com.sitech.utils.publicShop.PublicCloudConstant;
import com.sitech.utils.publicShop.PublicCloudSshUtil;
import com.sitech.vo.util.UnitedConstant;

/**
 * @ClassName: RulesServiceImpl
 * @Description: TODO(防火墙规则Service 实现类)
 * @author wanglei_bj@si-tech.com.cn
 * @date 2014-4-25 下午2:05:59
 * @version 1.0
 */
@Service("rulesService")
public class RulesServiceImpl implements RulesService {
	private static final Logger log = LoggerFactory.getLogger(RulesServiceImpl.class);
	@Autowired
	private RulesDao rulesDao;
	@Autowired
	private PropertyUtil sshProp;
	@Autowired
	private AclNumberDao aclNumberDao;
	@Autowired
	private RuleNumberDao ruleNumberDao;
	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getRulesList
	 * </p>
	 * <p>
	 * Description: 查询物理Vlan列表
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 */
	@Override
	public List<RulesObj> getRulesList(RulesObj obj) {
		return rulesDao.queryForListByObj(obj);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: createRules
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 */
	@Override
	//@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public String createRules(RulesObj obj) {
		String result = PublicCloudConstant.FAIL;
		
		//获取 AclNumber\RuleNumber
		AclNumberObj aclNumber = new AclNumberObj(ScriptConstant.firewall,ScriptConstant.firewall);
		RuleNumberObj ruleNumber = new RuleNumberObj();	
		aclNumber = aclNumberDao.getAclNumberByObj(aclNumber);
		ruleNumber.setAcl_id(aclNumber.getId());
		ruleNumber.setIsused(QuotaConstant.not_used);
		ruleNumber = ruleNumberDao.queryByObj(ruleNumber);
		
		if( CommonUtil.isEmpty(ruleNumber.getRule()) || CommonUtil.isEmpty(aclNumber.getAcl())){
			log.error("建立防火墙规则失败：没有可以用的策略号或规则号");
			//TODO 这里应该起一个运维工单，告诉运维人员来来处理（H3C厂家来处理）
			return result;
		}else {
			//设置为已用规则号
			handleRuleNumber(ruleNumber,QuotaConstant.is_used);
		}
		
		StringBuilder commend = new StringBuilder();
		String ip = sshProp.getString("ip");
		String username = sshProp.getString("username");
		String password = sshProp.getString("password");
		
		//判断 源IP是否为kong.为空或者 表示any
		if(CommonUtil.isEmpty(obj.getVal2()) || obj.getVal3() == "0.0.0.0"){
			commend.append(sshProp.getString("path"))
			   .append(" ")
			   .append(obj.getPriority().equals("1") ? sshProp.getString("fw_ssh_any_open") : sshProp.getString("fw_ssh_any_close")) //1.为接受（打开），0 为拒绝（关闭）
			   .append(" ")
			   .append(aclNumber.getAcl())
			   .append(" ")
			   .append(ruleNumber.getRule())
			   .append(" ")
			   .append(obj.getAction())
			   .append(" ")
			   .append(obj.getVal1());			
		}else {
			commend.append(sshProp.getString("path"))
			   .append(" ")
			   .append(obj.getPriority().equals("1") ? sshProp.getString("fw_ssh_ip_open") : sshProp.getString("fw_ssh_ip_close")) //1.为接受（打开），0 为拒绝（关闭）
			   .append(" ")
			   .append(aclNumber.getAcl())
			   .append(" ")
			   .append(ruleNumber.getRule())
			   .append(" ")
			   .append(obj.getAction())
			   .append(" ")
			   .append(obj.getVal1())
			   .append(" ")
		       .append(obj.getVal2())
		       .append(" ")
		       .append(obj.getVal3());
		}
		log.info("脚本调用："+commend.toString());
		result = PublicCloudSshUtil.executeSshCmd(ip,username, password,commend.toString());
		if (PublicCloudConstant.SUCCESS.equals(result)) {
			log.info("执行防火墙规则脚本：成功");
			obj.setRule_id(ruleNumber.getId());
			obj.setAcl(aclNumber.getAcl());
			obj.setRule(ruleNumber.getRule());
			obj.setRule_id(ruleNumber.getId());
			rulesDao.insertByObj(obj);
		}else{
			//取消占用规则号
			handleRuleNumber(ruleNumber,QuotaConstant.not_used);
			log.error("执行防火墙规则脚本：失败");
		}
		return result;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: updateRules
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 */
	@Override
	public String updateRules(RulesObj obj) {
		String result = UnitedConstant.FAIL;
		int ret = rulesDao.updateByObj(obj);
		if (ret > 0) {
			result = UnitedConstant.SUCCESS;
		}else {
			LogHelper.error("更新防火墙规则("+obj.getId()+")失败：");
		}
		return result;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: deleteRules
	 * </p>
	 * <p>
	 * Description:删除防火墙规则
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 */
	@Override
	//@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public String deleteRules(RulesObj obj) {
		String result = PublicCloudConstant.FAIL;
		int ret = -1;
		obj = rulesDao.getRulesByObj(obj);
		StringBuilder commend = new StringBuilder();
		String ip = sshProp.getString("ip");
		String username = sshProp.getString("username");
		String password = sshProp.getString("password");
		
		
		//判断 源IP是否为kong.为空或者 表示any
		if(CommonUtil.isEmpty(obj.getVal2()) || obj.getVal3() == "0.0.0.0"){
			commend.append(sshProp.getString("path"))
			   .append(" ")
			   .append(obj.getPriority().equals("0") ? sshProp.getString("fw_ssh_any_open") : sshProp.getString("fw_ssh_any_close")) //1.为接受（打开），0 为拒绝（关闭）
			   .append(" ")
			   .append(obj.getAcl())
			   .append(" ")
			   .append(obj.getRule())
			   .append(" ")
			   .append(obj.getAction())
			   .append(" ")
			   .append(obj.getVal1());			
		}else {
			commend.append(sshProp.getString("path"))
			   .append(" ")
			   .append(obj.getPriority().equals("0") ? sshProp.getString("fw_ssh_ip_open") : sshProp.getString("fw_ssh_ip_close")) //1.为接受（打开），0 为拒绝（关闭）
			   .append(" ")
			   .append(obj.getAcl())
			   .append(" ")
			   .append(obj.getRule())
			   .append(" ")
			   .append(obj.getAction())
			   .append(" ")
			   .append(obj.getVal1())
			   .append(" ")
		       .append(obj.getVal2())
		       .append(" ")
		       .append(obj.getVal3());
		}
		log.info("脚本调用开始："+commend.toString());
		result = PublicCloudSshUtil.executeSshCmd(ip,username, password,commend.toString());
		if (PublicCloudConstant.SUCCESS.equals(result)) {
			log.info("执行防火墙规则脚本：成功");
			ret = rulesDao.deleteByObj(obj);;// 释放公网IP和虚拟机的绑定关系
			//回收规则号
			handleRuleNumber(new RuleNumberObj(obj.getRule_id()),QuotaConstant.not_used);
			if(ret <= 0 ){
				log.error("删除防火墙规则("+obj.getId()+")失败：");
			}
		}else{
			log.error("执行防火墙规则脚本：失败");
		}
		return result;
	}

	/**
	 * 
	 * @Title: handleRuleNumber
	 * @Description: 分配或者释放rule号
	 * @param
	 * @return void
	 * @throws
	 * @version 1.0
	 * @createtime 2014-8-10 下午4:01:57
	 */
	private void handleRuleNumber(RuleNumberObj rule, String is_used) {
		rule.setIsused(is_used);
		ruleNumberDao.updataRuleNum(rule);
	}
	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getRules
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 */
	@Override
	public RulesObj getRules(RulesObj obj) {
		return rulesDao.getRulesByObj(obj);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: queryForCount
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see service.rules.RulesService#queryForCount(domain.rules.RulesObj)
	 */
	@Override
	public Integer queryForCount(RulesObj obj) {
		try {
			return rulesDao.queryForCount(obj);
		} catch (SQLException e) {
			LogHelper.error("查询防火墙规则数量失败"+e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
}
