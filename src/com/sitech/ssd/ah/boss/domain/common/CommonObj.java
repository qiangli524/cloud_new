package com.sitech.ssd.ah.boss.domain.common;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class CommonObj extends BaseObj {
	private String uid;
	private String type;
	private String nodeId;
	private String nodeName;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

}
