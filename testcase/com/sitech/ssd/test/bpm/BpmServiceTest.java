/**   
 * Copyright: Copyright (c) 2014
 * Company: SI-TECH
 *
* @Title: BpmServiceTest.java 
* @Package service.bpm 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wanglei_bj@si-tech.com.cn 
* @date 2014-7-22 上午10:53:07 
* @version V1.0   
*/
package com.sitech.ssd.test.bpm;

import java.util.HashMap;
import org.junit.Test;

import com.sitech.shop.webservice.service.BpmService;
import com.sitech.ssd.bpm.domain.CloudWorkorder;
import com.sitech.ssd.bpm.domain.WorkFlow;
import com.sitech.ssd.test.base.spring.AppContext;

/** 
 * @ClassName: BpmServiceTest 
 * @Description: bpmService 测试用例 
 * @author wanglei_bj@si-tech.com.cn 
 * @date 2014-7-22 上午10:53:07 
 * @version 1.0 
 */
public class BpmServiceTest {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void test() {
		BpmService bpmService = AppContext.getBean("bpmService", BpmService.class);
		String user_id = "1";
		String resourceName = "maintenance_workorder";
		String orderTitle = "111111";//订单编号；
		HashMap formMap = new HashMap();
		formMap.put("email", "wanglei_bj@si-tech.com.cn");
		formMap.put("phone", "18600613050");
		
		/*
		 * 运维工单下面三项必填 formMap 中的Key 我们可以事先定义好，然后我来做页面展示。
		 */
		
		formMap.put("type", "云主机开通故障"); //故障类型
		formMap.put("question", "云主机开始失败：没有可以用的资源");//问题描述
		formMap.put("content", "1.添加新的虚拟机到资源池 2.那啥3.那啥呀？4.完成哈哈 5.逗你玩");//建议解决办法
		
		bpmService.startBPM(user_id,resourceName, orderTitle,formMap);
	}
	/**
	 * 
	*
	* @Title: testNew 
	* @Description: 启动流程新的测试用例，建议使用这个
	* @param     设定文件 
	* @return void    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	* @createtime 2014-8-5
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testNew() {
		BpmService bpmService = AppContext.getBean("bpmService", BpmService.class);
		String userId = "1";//工单归属人id,默认系统管理员，可以不填
		String resourceName = "maintenance_workorder";//运维工单
		String orderID = "111111";//订单编号；

		WorkFlow workFlow = new WorkFlow();
		workFlow.setUserId(userId);
		
		/*
		 * 下面字段为必填字段！！！
		 */
		workFlow.setResourceName(resourceName);
		workFlow.setFormId(orderID);
		
		CloudWorkorder workorder = new CloudWorkorder();
		workorder.setQuestionContent("new云主机开始失败：没有可以用的资源");							//问题描述
		workorder.setQuestionType("new云主机开通故障");												//故障类型
		workorder.setEvaluateText("new1.添加新的虚拟机到资源池 2.那啥3.那啥呀？4.完成哈哈 5.逗你玩");  //建议解决办法
		
		bpmService.startBPM(workFlow,workorder);
	}

}
