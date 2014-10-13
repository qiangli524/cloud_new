package com.sitech.shop.webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.billing.vo.resourceInfo.VmInfo;
import com.sitech.utils.jackson.JacksonUtil;

@Service("resourcePowerStateChangeListener")
public class ResourcePowerStateChangeListener {
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
		VmInfo info = JacksonUtil.fromJson(message, VmInfo.class);
		try {
			resourceService.putResourceState(info);
		} catch (Exception e) {
		}
	}
}
