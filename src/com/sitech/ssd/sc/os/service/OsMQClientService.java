package com.sitech.ssd.sc.os.service;

import com.sitech.ssd.sc.os.domain.HostModel;

public interface OsMQClientService {
	
	public String sendOsInstallMsg(HostModel host);

}
