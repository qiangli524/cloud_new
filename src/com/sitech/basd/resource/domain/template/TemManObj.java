package com.sitech.basd.resource.domain.template;

import java.io.Serializable;
import java.util.List;

import com.sitech.basd.common.domain.BasePrivilegeObj;

/**
 * 
 * <p>
 * Title: TemManObj
 * </p>
 * <p>
 * Description: tb_tem_manage-
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-7-27 上午11:28:00
 * 
 */
public class TemManObj extends BasePrivilegeObj implements Serializable, Cloneable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	// 模板ID-UUID
	private String id;
	// 模板名称
	private String name;
	// 模板类型-VMware/Xen/OVF1.vmware虚拟机转化的模板2.xen虚拟机转化的模板3.标准ovf模板
	
	private String dataCenterName;
	//模板所属数据中心的名字
	
	private String type;
	// 是否共有0.共有1.私有
	private String isPublic;
	// 模板存储位置
	private String position;
	// 标记
	private String remark;
	// 模板所属用户-云平台用户
	private String account;
	// 操作系统
	private String system;
	private int cpu;
	// 单位MB
	private int mem;
	// 单位MB
	private double store;
	// 连接标识 vCenter(IP/用户名/密码)标示、XenCenter标示(共有)
	private String connectId;
	// 主机唯一标示
	private String hostCode;
	// 模板CODE(VMware-vm-1,XEN-UUID)
	private String templateCode;
	private String isPhysical;// 是否为物理模板 1是 0否
	private String username;// 模板用户名
	private String password;// 模板密码
	private Integer usable;

	private String user_id;// 用户，北京电信使用
	private String alias;// 别名，北京电信使用
	// 模板网卡数量
	private Integer nic_count;
	private String domain;
	//预装软件名称
	private String soft_name;

	public String getSoft_name() {
		return soft_name;
	}

	public void setSoft_name(String soft_name) {
		this.soft_name = soft_name;
	}

	private List<String> connectCodeList;// vCenter标示列表，北京电信使用

	public Integer getNic_count() {
		return nic_count;
	}

	public void setNic_count(Integer nic_count) {
		this.nic_count = nic_count;
	}

	/**
	 * <p>
	 * Title: 构造器
	 * </p>
	 * <p>
	 * Description: 根据ID创建对象
	 * </p>
	 * 
	 * @param string
	 */
	public TemManObj(String id) {
		this.id = id;
	}

	public TemManObj() {
		super();
	}

	public List<String> getConnectCodeList() {
		return connectCodeList;
	}

	public void setConnectCodeList(List<String> connectCodeList) {
		this.connectCodeList = connectCodeList;
	}

	public String getUsername() {
		return username;
	}

	public String getDataCenterName() {
		return dataCenterName;
	}

	public void setDataCenterName(String dataCenterName) {
		this.dataCenterName = dataCenterName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsPhysical() {
		return isPhysical;
	}

	public void setIsPhysical(String isPhysical) {
		this.isPhysical = isPhysical;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getConnectId() {
		return connectId;
	}

	public void setConnectId(String connectId) {
		this.connectId = connectId;
	}

	public String getHostCode() {
		return hostCode;
	}

	public void setHostCode(String hostCode) {
		this.hostCode = hostCode;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public int getCpu() {
		return cpu;
	}

	public void setCpu(int cpu) {
		this.cpu = cpu;
	}

	public int getMem() {
		return mem;
	}

	public void setMem(int mem) {
		this.mem = mem;
	}

	public double getStore() {
		return store;
	}

	public void setStore(double store) {
		this.store = store;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUsable() {
		return usable;
	}

	public void setUsable(Integer usable) {
		this.usable = usable;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 *
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 *
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
}
