package com.sitech.basd.resource.domain.template;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class TemVmRelationObj extends BaseObj {
	private String vmCode;
	private String temCode;
	private String connectCode;

	public String getVmCode() {
		return vmCode;
	}

	public void setVmCode(String vmCode) {
		this.vmCode = vmCode;
	}

	public String getTemCode() {
		return temCode;
	}

	public void setTemCode(String temCode) {
		this.temCode = temCode;
	}

	public String getConnectCode() {
		return connectCode;
	}

	public void setConnectCode(String connectCode) {
		this.connectCode = connectCode;
	}

}
