package com.sitech.shop.webservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.billing.vo.resourceInfo.VmInfo;
import com.sitech.utils.jackson.JacksonUtil;

@Service("applyBalanceListener")
public class ApplyBalanceListener {
	Logger logger = LoggerFactory.getLogger(ApplyBalanceListener.class);
	@Autowired
	private ResourceService resourceService;

	/**
	 * 
	 * @Title: handleMessage
	 * @Description: 处理消息
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-28 上午11:36:22
	 */
	public void handleMessage(String message) {
		logger.info("执行接收申请虚拟机消息方法：开始");
		VmInfo info = JacksonUtil.fromJson(message, VmInfo.class);
		logger.info("执行接收申请虚拟机消息方法：结束");
		try {
			resourceService.applyBalance(info);
		} catch (Exception e) {
			logger.error("执行申请虚拟机方法：异常");
		}
	}

}
