package com.sitech.basd.report.domain;

import java.util.List;

import com.sitech.basd.common.domain.BasePrivilegeObj;

/**
 * <p>Title:VmHostDetailInfoVO</p>
 * <p>Description:虚拟机详细信息VO</p>
 * <p>Copyright:Copyright (c) 2014</p>
 * <p>Company:SI-TECH </p>
 * @author wangjl_cmi_jl
 * @version 1.0
 * @createtime Aug 6, 2014 4:22:51 PM
 */
public class VmHostDetailInfoVO extends BasePrivilegeObj {
	//虚拟机所属数据中心名称
	private String blgCenterName;
	//虚拟机所属主机名称
	private String blgHostName;
	//虚拟机名称
	private String vmHostName;
	//虚拟机IP
	private String vmHostIp;
	//虚拟机所承载的业务系统list
	private List<VmHostBusiSysVO> busiSysList;
	public String getBlgCenterName() {
		return blgCenterName;
	}
	public void setBlgCenterName(String blgCenterName) {
		this.blgCenterName = blgCenterName;
	}
	public String getBlgHostName() {
		return blgHostName;
	}
	public void setBlgHostName(String blgHostName) {
		this.blgHostName = blgHostName;
	}
	public String getVmHostName() {
		return vmHostName;
	}
	public void setVmHostName(String vmHostName) {
		this.vmHostName = vmHostName;
	}
	public String getVmHostIp() {
		return vmHostIp;
	}
	public void setVmHostIp(String vmHostIp) {
		this.vmHostIp = vmHostIp;
	}
	public List<VmHostBusiSysVO> getBusiSysList() {
		return busiSysList;
	}
	public void setBusiSysList(List<VmHostBusiSysVO> busiSysList) {
		this.busiSysList = busiSysList;
	}
}
