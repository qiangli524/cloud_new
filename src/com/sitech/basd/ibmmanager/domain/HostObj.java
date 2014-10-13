package com.sitech.basd.ibmmanager.domain;

import java.util.List;

import com.sitech.vo.united.ResultSet;

/**
 * 
 * <p>
 * Title: HostObj
 * </p>
 * <p>
 * Description: 主机标识和名字
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
 * @createtime 2013-11-14 下午3:17:53
 * 
 */
public class HostObj extends ResultSet {
	private String clusterCode;
	private String hostCode;// 对应TB_CLOUD2_HOST_INFO中的h_uuid
	private String hostName;// 对应TB_CLOUD2_HOST_INFO中的eq_name
	private List<HostObj> list;

	public String getClusterCode() {
		return clusterCode;
	}

	public void setClusterCode(String clusterCode) {
		this.clusterCode = clusterCode;
	}

	public String getHostCode() {
		return hostCode;
	}

	public void setHostCode(String hostCode) {
		this.hostCode = hostCode;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public List<HostObj> getList() {
		return list;
	}

	public void setList(List<HostObj> list) {
		this.list = list;
	}

}
