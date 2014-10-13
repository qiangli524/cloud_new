package com.sitech.basd.sxcloud.cloud.domain.virtual;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: TbCloud2VirtualConfigObj
 * </p>
 * <p>
 * Description: 虚拟机资源池信息-属性类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author duangh
 * @version 1.0
 * @createtime Mar 27, 2012 11:32:03 AM
 * 
 */
public class TbCloud2VirtualPoolObj extends BaseObj implements Serializable,
		Cloneable {
	private String VH_ID; // 虚拟机编号
	private String VH_NAME; // 虚拟机名称
	private String VH_TYPE; // 虚拟机类型
	private String VH_IP; // 虚拟机IP地址
	private String PROTOCOL;// 监控虚拟机方式
	private String PORT; // 端口
	private String eq_id; // 所属物理主机编号

	public String getEq_id() {
		return eq_id;
	}

	public void setEq_id(String eq_id) {
		this.eq_id = eq_id;
	}

	public String getVH_NAME() {
		return VH_NAME;
	}

	public void setVH_NAME(String vh_name) {
		VH_NAME = vh_name;
	}

	public String getVH_ID() {
		return VH_ID;
	}

	public void setVH_ID(String vh_id) {
		VH_ID = vh_id;
	}

	public String getVH_TYPE() {
		return VH_TYPE;
	}

	public void setVH_TYPE(String vh_type) {
		VH_TYPE = vh_type;
	}

	public String getVH_IP() {
		return VH_IP;
	}

	public void setVH_IP(String vh_ip) {
		VH_IP = vh_ip;
	}

	public String getPROTOCOL() {
		return PROTOCOL;
	}

	public void setPROTOCOL(String protocol) {
		PROTOCOL = protocol;
	}

	public String getPORT() {
		return PORT;
	}

	public void setPORT(String port) {
		PORT = port;
	}

}
