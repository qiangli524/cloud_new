package com.sitech.ssd.ah.paas.domain.host;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class GreenPlumHostInfoObj extends BaseObj {

	private String id;// 主机--id主键
	private String h_uuid;// xen uuid
	private String eq_id;
	private String eq_name;
	private String eq_type;
	private String eq_ip;
	private String eq_hostname;
	private String eq_temperature;
	private String hasvertual;
	private String cq_id;
	private String protocol; // 远程连接主机协议(telnet、ssh等)
	private Integer control = 0; // 云平台能否管控 0 不管控、1 管控
	private Integer device_id;// 设备id
	private String ins_date;
	private String cpu_fq;// cpu主频
	private String cpu_cl;// cpu芯数
	private String memory;// 内存
	private String store;// 存储
	private String model;// 主机型号
	private Integer nic_num;// 主机网卡数量
	private String cpu_desc;// cpu描述
	private String host_proc;// 主机制造商
	private String status;// 主机状态：0，未采集到 1，正常启动 2，停止 3，异常
	private String connect_id;// 集群标识 vmware-集群id xen-资源池pool-uuid(共有)
	private String max_vcpu;
	private String used_vcpu;
	private Integer allocated;// 是否分配
	private String connect_status;
	private Integer storage_num;
	private String tableName;
	private String sn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getH_uuid() {
		return h_uuid;
	}

	public void setH_uuid(String h_uuid) {
		this.h_uuid = h_uuid;
	}

	public String getEq_id() {
		return eq_id;
	}

	public void setEq_id(String eq_id) {
		this.eq_id = eq_id;
	}

	public String getEq_name() {
		return eq_name;
	}

	public void setEq_name(String eq_name) {
		this.eq_name = eq_name;
	}

	public String getEq_type() {
		return eq_type;
	}

	public void setEq_type(String eq_type) {
		this.eq_type = eq_type;
	}

	public String getEq_ip() {
		return eq_ip;
	}

	public void setEq_ip(String eq_ip) {
		this.eq_ip = eq_ip;
	}

	public String getEq_hostname() {
		return eq_hostname;
	}

	public void setEq_hostname(String eq_hostname) {
		this.eq_hostname = eq_hostname;
	}

	public String getEq_temperature() {
		return eq_temperature;
	}

	public void setEq_temperature(String eq_temperature) {
		this.eq_temperature = eq_temperature;
	}

	public String getHasvertual() {
		return hasvertual;
	}

	public void setHasvertual(String hasvertual) {
		this.hasvertual = hasvertual;
	}

	public String getCq_id() {
		return cq_id;
	}

	public void setCq_id(String cq_id) {
		this.cq_id = cq_id;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Integer getControl() {
		return control;
	}

	public void setControl(Integer control) {
		this.control = control;
	}

	public Integer getDevice_id() {
		return device_id;
	}

	public void setDevice_id(Integer device_id) {
		this.device_id = device_id;
	}

	public String getIns_date() {
		return ins_date;
	}

	public void setIns_date(String ins_date) {
		this.ins_date = ins_date;
	}

	public String getCpu_fq() {
		return cpu_fq;
	}

	public void setCpu_fq(String cpu_fq) {
		this.cpu_fq = cpu_fq;
	}

	public String getCpu_cl() {
		return cpu_cl;
	}

	public void setCpu_cl(String cpu_cl) {
		this.cpu_cl = cpu_cl;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getNic_num() {
		return nic_num;
	}

	public void setNic_num(Integer nic_num) {
		this.nic_num = nic_num;
	}

	public String getCpu_desc() {
		return cpu_desc;
	}

	public void setCpu_desc(String cpu_desc) {
		this.cpu_desc = cpu_desc;
	}

	public String getHost_proc() {
		return host_proc;
	}

	public void setHost_proc(String host_proc) {
		this.host_proc = host_proc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConnect_id() {
		return connect_id;
	}

	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}

	public String getMax_vcpu() {
		return max_vcpu;
	}

	public void setMax_vcpu(String max_vcpu) {
		this.max_vcpu = max_vcpu;
	}

	public String getUsed_vcpu() {
		return used_vcpu;
	}

	public void setUsed_vcpu(String used_vcpu) {
		this.used_vcpu = used_vcpu;
	}

	public Integer getAllocated() {
		return allocated;
	}

	public void setAllocated(Integer allocated) {
		this.allocated = allocated;
	}

	public String getConnect_status() {
		return connect_status;
	}

	public void setConnect_status(String connect_status) {
		this.connect_status = connect_status;
	}

	public Integer getStorage_num() {
		return storage_num;
	}

	public void setStorage_num(Integer storage_num) {
		this.storage_num = storage_num;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

}
