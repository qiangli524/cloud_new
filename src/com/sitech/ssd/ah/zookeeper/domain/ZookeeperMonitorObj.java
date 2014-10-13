package com.sitech.ssd.ah.zookeeper.domain;

/**
 * <p>
 * Title: ZookeeperMonitorObj
 * </p>
 * <p>
 * Description: zookeeper监控对象
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-7-23 下午3:46:22
 * 
 */
public class ZookeeperMonitorObj {
	private String siPath;// 四级节点完整路径
	private String nodeName;// 五级节点名称
	private boolean flag;// 节点是否正常
	private String dataValue;// 节点对应的属性

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getSiPath() {
		return siPath;
	}

	public void setSiPath(String siPath) {
		this.siPath = siPath;
	}
}
