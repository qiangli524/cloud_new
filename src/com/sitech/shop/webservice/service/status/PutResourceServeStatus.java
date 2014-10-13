package com.sitech.shop.webservice.service.status;

import com.sitech.ssd.billing.vo.resourceInfo.VmInfo;

public interface PutResourceServeStatus {
	public VmInfo updateServeStatusProcess(VmInfo info) throws Exception;
}
