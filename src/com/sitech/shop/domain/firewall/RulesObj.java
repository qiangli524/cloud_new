/**   
 * Copyright: Copyright (c) 2014
 * Company: SI-TECH
 *
* @Title: RulesObj.java 
* @Package domain.firewall 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wanglei_bj@si-tech.com.cn 
* @date 2014-6-6 下午4:01:59 
* @version V1.0   
*/
package com.sitech.shop.domain.firewall;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/** 
 * @ClassName: RulesObj 
 * @Description: TODO(防火墙规则实体类) 
 * @author wanglei_bj@si-tech.com.cn 
 * @date 2014-6-6 下午4:01:59 
 * @version 1.0 
 */
public class RulesObj extends BaseObj{
	private String id;
	private String priority; 	//优先级，由高到低为 0 - 100
	private String protocol; 	//协议
	private String name;	 	//规则名称
	private String action;	 	//操作，1 表示 accept 接受 和0 表示  drop 拒绝
	private String direction;	//方向，0 表示下行，1 表示上行。
	private String val1;	 	//如果协议为 tcp 或 udp，此值表示起始端口。 如果协议为 icmp，此值表示 ICMP 类型。 具体类型可参见 ICMP 类型及代码
	private String val2;	 	//如果协议为 tcp 或 udp，此值表示结束端口。 如果协议为 icmp，此值表示 ICMP 代码。 具体代码可参见 ICMP 类型及代码
	private String val3;	 	//源IP
	private String firewall_id;	//搜索防火墙
	private String rule_id;        //规则号_id
	private Integer acl;
	private Integer rule;
	
	
	public Integer getAcl() {
		return acl;
	}

	public void setAcl(Integer acl) {
		this.acl = acl;
	}

	public Integer getRule() {
		return rule;
	}

	public void setRule(Integer rule) {
		this.rule = rule;
	}

	/** 特别注意：
	 * 1.由于业务变更，字段含义发生变化：
	 * protocol 表示 操作，1 表示 accept 接受 和0 表示  drop 拒绝
	 * action 表示出公网的内网IP地址
	 * val1 表示 目的端口
	 * val2 表示 源IP
	 * val3 表示 源掩码
	 * 2.如与上面注释发生冲突以此处为准。
	 */
	
	public RulesObj() {
		super();
	}
	
	public RulesObj(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getVal1() {
		return val1;
	}
	public void setVal1(String val1) {
		this.val1 = val1;
	}
	public String getVal2() {
		return val2;
	}
	public void setVal2(String val2) {
		this.val2 = val2;
	}
	public String getVal3() {
		return val3;
	}
	public void setVal3(String val3) {
		this.val3 = val3;
	}
	public String getFirewall_id() {
		return firewall_id;
	}
	public void setFirewall_id(String firewall_id) {
		this.firewall_id = firewall_id;
	}

	public String getRule_id() {
		return rule_id;
	}

	public void setRule_id(String rule_id) {
		this.rule_id = rule_id;
	}
}
