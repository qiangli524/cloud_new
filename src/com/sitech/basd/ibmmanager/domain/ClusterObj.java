package com.sitech.basd.ibmmanager.domain;

import java.util.List;

import com.sitech.vo.united.ResultSet;

/**
 * 
 * <p>
 * Title: ClusterObj
 * </p>
 * <p>
 * Description: 集群标识和名字
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
 * @createtime 2013-11-14 下午3:17:11
 * 
 */
public class ClusterObj extends ResultSet {
	private String clusterCode;// 对应TB_CLOUD_CLUSTER_INFO中的ID
	private String clusterName;// 对应TB_CLOUD_CLUSTER_INFO中的name
	private List<ClusterObj> list;

	public String getClusterCode() {
		return clusterCode;
	}

	public void setClusterCode(String clusterCode) {
		this.clusterCode = clusterCode;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public List<ClusterObj> getList() {
		return list;
	}

	public void setList(List<ClusterObj> list) {
		this.list = list;
	}

}
