package com.sitech.shop.domain.vlan;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * @ClassName: PhysicalVlanObj
 * @Description: TODO(物理Vlan实体类)
 * @author wanglei_bj@si-tech.com.cn
 * @date 2014-4-23 上午11:28:20
 * @version 1.0
 */
public class PhysicalVlanObj extends BaseObj {
	private String id;
	private String name;
	private String ip;
	private String gateway;
	private String subnet_mask;
	private String vlan_id;
	private String isused;
	private String area_id; // 地域ID 北京电信使用
	private String user_id;
	private String behavior;// 用于标记带宽的服务状态。包括已过期、即将过期等。
	private String qos_num;// qos号
	private String flow_class;// 流量对应class
	private Integer flow_size = 0;// 流量大小
	private String create_time;// 创建时间
	private String oper;// 对vlan的操作（增加vlan带宽；修改vlan带宽；删除vlan带宽）
	private String router_name;// 路由器名称
	private String router_id;// 路由器标示
	private String vlan_type;// vlan 类型（1.公网Vlan；0.内网Vlan）
	private String service_type;// 1.vlan是给预附费用户使用 2.vlan是给后付费用户使用
	private String payment_type;// 计费方式1.按量计费2.包年包月
	private String end_time;// 到期时间
	private String obtain_way;// 获取资源方式0.赠送 1.购买
	private String rule_id;// 规则号标示

	public String getRule_id() {
		return rule_id;
	}

	public void setRule_id(String rule_id) {
		this.rule_id = rule_id;
	}

	public String getObtain_way() {
		return obtain_way;
	}

	public void setObtain_way(String obtain_way) {
		this.obtain_way = obtain_way;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getService_type() {
		return service_type;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}

	public String getVlan_type() {
		return vlan_type;
	}

	public void setVlan_type(String vlan_type) {
		this.vlan_type = vlan_type;
	}

	public String getRouter_name() {
		return router_name;
	}

	public void setRouter_name(String router_name) {
		this.router_name = router_name;
	}

	public String getRouter_id() {
		return router_id;
	}

	public void setRouter_id(String router_id) {
		this.router_id = router_id;
	}

	public String getBehavior() {
		return behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getQos_num() {
		return qos_num;
	}

	public void setQos_num(String qos_num) {
		this.qos_num = qos_num;
	}

	public String getFlow_class() {
		return flow_class;
	}

	public void setFlow_class(String flow_class) {
		this.flow_class = flow_class;
	}

	public Integer getFlow_size() {
		return flow_size;
	}

	public void setFlow_size(Integer flow_size) {
		this.flow_size = flow_size;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getSubnet_mask() {
		return subnet_mask;
	}

	public void setSubnet_mask(String subnet_mask) {
		this.subnet_mask = subnet_mask;
	}

	public String getVlan_id() {
		return vlan_id;
	}

	public void setVlan_id(String vlan_id) {
		this.vlan_id = vlan_id;
	}

	public String getIsused() {
		return isused;
	}

	public void setIsused(String isused) {
		this.isused = isused;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
}
