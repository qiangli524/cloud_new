/**   
 * Copyright: Copyright (c) 2014
 * Company: SI-TECH
 *
 * @Title: CloudBpmService.java 
 * @Package service.bpm 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author wanglei_bj@si-tech.com.cn 
 * @date 2014-7-22 上午10:16:43 
 * @version V1.0   
 */
package com.sitech.shop.webservice.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.bpm.domain.CloudStep;
import com.sitech.ssd.bpm.domain.CloudWorkorder;
import com.sitech.ssd.bpm.domain.WorkFlow;
import com.sitech.ssd.bpm.service.WorkFlowService;
import com.sitech.ssd.bpm.service.WorkFlowUserService;
import com.sitech.utils.common.CommonUtil;
import com.sitech.utils.publicShop.MapUtil;

/**
 * @ClassName: CloudBpmService
 * @Description: TODO()
 * @author wanglei_bj@si-tech.com.cn
 * @date 2014-7-22 上午10:16:43
 * @version 1.0
 */
@Service("bpmService")
public class BpmService {
	@Autowired
	WorkFlowService bpmWorkFlowService;
	@Autowired
	WorkFlowUserService workFlowUserService;

	private static final Logger log = LoggerFactory.getLogger(BpmService.class);

	/**
	 * 
	 * @Title: startBPM
	 * @Description: 工作流程启动服务
	 * @param user_id
	 *            发起人Id 可以为空,默认为"1"
	 * @param callType
	 *            派发方式 WorkFlowConstant.GROUP_EXE_AI; //智能分配
	 *            WorkFlowConstant.GROUP_EXE_SEIZE;//抢占式
	 * @param resourceName
	 *            流程实例名称
	 * @param orderTitle
	 *            流程实例标题 传入订单编号 回调是返回此编号
	 * @param formData
	 *            流程表单数据 {"content"：["内容"]}
	 * @return boolean 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public boolean startBPM(String user_id, String resourceName, String orderTitle, Map formData) {
		log.info("启动流程实例:" + resourceName);
		if (CommonUtil.isEmpty(resourceName) || CommonUtil.isEmpty(formData)) {
			log.error("流程实例启动失败：必填参数为空");
			return false;
		}
		WorkFlow workFlow = new WorkFlow();
		CloudStep step = new CloudStep();

		workFlow.setResourceName(resourceName);
		workFlow.setOrderTitle(orderTitle);

		// 设置用户名
		if (CommonUtil.isNotEmpty(user_id)) {
			workFlow.setAccount(user_id);
		} else {
			workFlow.setAccount("1");
		}

		// 设置用户组
		workFlow.setRoleList(workFlowUserService.getGroupIdListByUserId(workFlow.getAccount()));

		formData = MapUtil.StringCastToLString(formData);
		step.setObjData(formData);

		workFlow = bpmWorkFlowService.addWorkFlow(workFlow, step, null);
		log.info("启动流程实例:" + resourceName + workFlow == null ? "失败" : "成功");
		return workFlow == null ? false : true;
	}

	/**
	 * 
	 * @Title: startBPM
	 * @Description: 工作流程启动服务
	 * @param @param workFlow 必填：resourceName(流程实例名称)\ formId(订单ID) 选填：
	 *        userId（流程归属人）
	 * @param @param workorder
	 *        必填：QuestionType（故障类型：30字符）、QuestionContent(问题描述：255
	 *        字符)、EvaluateText(建议解决办法： 255字符)
	 * @return boolean 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @createtime 2014-8-5
	 */
	public boolean startBPM(WorkFlow workFlow, CloudWorkorder workorder) {

		if (CommonUtil.isEmpty(workFlow) || CommonUtil.isEmpty(workFlow.getResourceName())
				|| CommonUtil.isEmpty(workorder) || CommonUtil.isEmpty(workorder.getQuestionType())
				|| CommonUtil.isEmpty(workorder.getQuestionContent())
				|| CommonUtil.isEmpty(workorder.getEvaluateText())) {
			log.error("流程实例启动失败：必填参数为空");
			return false;
		}
		log.info("启动流程实例:" + workFlow.getResourceName());

		// 设置工单归属人 如果不传默认是 超级管理员id : 1
		if (CommonUtil.isEmpty(workFlow.getUserId())) {
			workFlow.setAccount("1");
		} else {
			workFlow.setAccount(workFlow.getUserId());
		}

		// 设置用户组
		workFlow.setRoleList(workFlowUserService.getGroupIdListByUserId(workFlow.getAccount()));

		CloudStep step = new CloudStep();
		Map formMap = new HashMap();
		formMap.put("type", workorder.getQuestionType()); // 故障类型
		formMap.put("question", workorder.getQuestionContent());// 问题描述
		formMap.put("content", workorder.getEvaluateText());// 建议解决办法
		formMap = MapUtil.StringCastToLString(formMap);
		step.setObjData(formMap);

		workFlow = bpmWorkFlowService.addWorkFlow(workFlow, step, null);
		log.info("启动流程实例:" + workFlow.getResourceName() + workFlow == null ? "失败" : "成功");
		return workFlow == null ? false : true;
	}
}
