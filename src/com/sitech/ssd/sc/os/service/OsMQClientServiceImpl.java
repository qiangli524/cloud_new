package com.sitech.ssd.sc.os.service;

import org.springframework.stereotype.Service;

import com.sitech.ssd.sc.os.domain.HostModel;

@Service("osMQClientService")
public class OsMQClientServiceImpl implements OsMQClientService {

	@Override
	public String sendOsInstallMsg(HostModel host) {
		
		return null;
	}

}
