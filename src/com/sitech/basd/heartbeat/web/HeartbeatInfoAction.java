package com.sitech.basd.heartbeat.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.heartbeat.service.HeartbeatInfoService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;

@SuppressWarnings("serial")
@Controller("heartbeatInfoAction")
@Scope("prototype")
public class HeartbeatInfoAction extends CRUDBaseAction {
	private Map<String, String> beatMap = new HashMap<String, String>();

	@Resource
	private HeartbeatInfoService heartbeatInfoService;

	public Map<String, String> getBeatMap() {
		return beatMap;
	}

	public void setBeatMap(Map<String, String> beatMap) {
		this.beatMap = beatMap;
	}

	public String heartbeatInfo() {
		Map<String, String> map = heartbeatInfoService.getHeartbeatInfo();
		this.beatMap.putAll(map);
		return SUCCESS;
	}

}
