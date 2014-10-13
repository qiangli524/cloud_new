package com.sitech.basd.resource.domain.switchBoard;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;
/**
 * 
 * <p>Title: StaticRouterObj</p>
 * <p>Description: 交换机 静态路由相关属性</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author yanggl
 * @version 1.0
 * @createtime 2014-6-8 下午4:25:40
 *
 */
public class StaticRouterObj extends BaseObj {
	private String id;
	private String source_ip;//目的地址
	private String subnet_mask;//子网掩码
	private String ip;//网关地址
	private String switch_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSource_ip() {
		return source_ip;
	}
	public void setSource_ip(String source_ip) {
		this.source_ip = source_ip;
	}
	public String getSubnet_mask() {
		return subnet_mask;
	}
	public void setSubnet_mask(String subnet_mask) {
		this.subnet_mask = subnet_mask;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSwitch_id() {
		return switch_id;
	}
	public void setSwitch_id(String switch_id) {
		this.switch_id = switch_id;
	}
	
	
}
