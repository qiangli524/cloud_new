package com.sitech.basd.ibmmanager.domain;

import java.util.List;

import com.sitech.vo.united.ResultSet;

/**
 * 
 * <p>
 * Title: VMObj
 * </p>
 * <p>
 * Description: 虚拟机标识和名字
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2013-11-14 下午3:19:26
 * 
 */
public class VMObj extends ResultSet {
	private String hostCode;// 对应TB_CLOUD2_VMHOST_INFO中的h_uuid
	private String vmCode;// 对应TB_CLOUD2_VMHOST_INFO中的vh_uuiid
	private String vmName;// 对应TB_CLOUD2_VMHOST_INFO中的vh_name
	private List<VMObj> list;

	public String getHostCode() {
		return hostCode;
	}

	public void setHostCode(String hostCode) {
		this.hostCode = hostCode;
	}

	public String getVmCode() {
		return vmCode;
	}

	public void setVmCode(String vmCode) {
		this.vmCode = vmCode;
	}

	public String getVmName() {
		return vmName;
	}

	public void setVmName(String vmName) {
		this.vmName = vmName;
	}

	public List<VMObj> getList() {
		return list;
	}

	public void setList(List<VMObj> list) {
		this.list = list;
	}

}
