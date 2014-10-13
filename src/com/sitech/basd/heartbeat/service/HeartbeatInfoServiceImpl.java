package com.sitech.basd.heartbeat.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sitech.basd.filter.HeartbeatAction;

@Service("heartbeatInfoService")
public class HeartbeatInfoServiceImpl implements HeartbeatInfoService {
	@Resource
	private HeartbeatAction heartbeatAction;

	@Override
	public Map<String, String> getHeartbeatInfo() {
		Map<String, String> beatMap = heartbeatAction.getClientConnIdentMap();
		return beatMap;
	}
}
