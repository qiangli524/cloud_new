package com.sitech.shop.webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.bpm.domain.CloudWorkorder;
import com.sitech.ssd.bpm.domain.WorkFlow;

@Service("publicCloudOrderUtil")
public class PublicCloudOrderUtil {
	@Autowired
	private BpmService bpmService;

	public void startOrder(String user_id, String order_id, String content, String type,
			String advice) {
		WorkFlow workFlow = new WorkFlow();
		workFlow.setUserId(user_id);
		workFlow.setResourceName("maintenance_workorder");
		workFlow.setFormId(order_id);
		CloudWorkorder workorder = new CloudWorkorder();
		workorder.setQuestionContent(content); // 问题描述
		workorder.setQuestionType(type); // 故障类型
		workorder.setEvaluateText(advice); // 建议解决办法
		bpmService.startBPM(workFlow, workorder);
	}

}
