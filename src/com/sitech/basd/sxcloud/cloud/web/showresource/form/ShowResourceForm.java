package com.sitech.basd.sxcloud.cloud.web.showresource.form;

import java.util.List;

public class ShowResourceForm {
	// 主机所需参数
	private String x_cpu;
	private String x_cpu_use;
	private String x_cpu_free;
	private String x_mem;
	private String x_mem_use;
	private String x_mem_free;
	private String x_store;
	private String x_store_use;
	private String x_store_free;
	private String x_cpu_per;
	private String x_mem_per;
	private String x_store_per;
	private String v_cpu;
	private String v_cpu_use;
	private String v_cpu_free;
	private String v_mem;
	private String v_mem_use;
	private String v_mem_free;
	private String v_store;
	private String v_store_use;
	private String v_store_free;
	private String v_cpu_per;
	private String v_mem_per;
	private String v_store_per;
	// 所有资源所需参数
	private String cpu_all;
	private String mem_all;
	private String store_all;
	private String cpu_all_use;
	private String mem_all_use;
	private String store_all_use;
	private String cpu_all_free;
	private String mem_all_free;
	private String store_all_free;
	// 主机及虚拟机所需要参数
	private String host_all_num;
	private String host_x_num;
	private String host_v_num;
	private String vm_all_num;
	private String vm_x_num;
	private String vm_v_num;
	private String host_other;
	private String vm_other;

	// 网络IP所需要的参数
	private String net_name;
	private int ip_all;
	private int ip_use;
	private List netList;
	private int alarm_all;
	private List alarmList;

	/** 统计主机等信息 */
	private XenHostStatistics xenStatistics;
	private VmwareHostStatistics vmwareStatistics;
	private PowerHostStatistics powerStatistics;
	private OtherHostStatistics otherStatistics;
	private AlarmHostStatistics alarmStatistice;
	private List alarmResultList;

	public XenHostStatistics getXenStatistics() {
		return xenStatistics;
	}

	public void setXenStatistics(XenHostStatistics xenStatistics) {
		this.xenStatistics = xenStatistics;
	}

	public VmwareHostStatistics getVmwareStatistics() {
		return vmwareStatistics;
	}

	public void setVmwareStatistics(VmwareHostStatistics vmwareStatistics) {
		this.vmwareStatistics = vmwareStatistics;
	}

	public PowerHostStatistics getPowerStatistics() {
		return powerStatistics;
	}

	public void setPowerStatistics(PowerHostStatistics powerStatistics) {
		this.powerStatistics = powerStatistics;
	}

	public OtherHostStatistics getOtherStatistics() {
		return otherStatistics;
	}

	public void setOtherStatistics(OtherHostStatistics otherStatistics) {
		this.otherStatistics = otherStatistics;
	}

	public String getHost_other() {
		return host_other;
	}

	public void setHost_other(String host_other) {
		this.host_other = host_other;
	}

	public String getVm_other() {
		return vm_other;
	}

	public void setVm_other(String vm_other) {
		this.vm_other = vm_other;
	}

	public String getHost_all_num() {
		return host_all_num;
	}

	public void setHost_all_num(String host_all_num) {
		this.host_all_num = host_all_num;
	}

	public String getHost_x_num() {
		return host_x_num;
	}

	public void setHost_x_num(String host_x_num) {
		this.host_x_num = host_x_num;
	}

	public String getHost_v_num() {
		return host_v_num;
	}

	public void setHost_v_num(String host_v_num) {
		this.host_v_num = host_v_num;
	}

	public String getVm_all_num() {
		return vm_all_num;
	}

	public void setVm_all_num(String vm_all_num) {
		this.vm_all_num = vm_all_num;
	}

	public String getVm_x_num() {
		return vm_x_num;
	}

	public void setVm_x_num(String vm_x_num) {
		this.vm_x_num = vm_x_num;
	}

	public String getVm_v_num() {
		return vm_v_num;
	}

	public void setVm_v_num(String vm_v_num) {
		this.vm_v_num = vm_v_num;
	}

	public String getCpu_all_free() {
		return cpu_all_free;
	}

	public void setCpu_all_free(String cpu_all_free) {
		this.cpu_all_free = cpu_all_free;
	}

	public String getMem_all_free() {
		return mem_all_free;
	}

	public void setMem_all_free(String mem_all_free) {
		this.mem_all_free = mem_all_free;
	}

	public String getStore_all_free() {
		return store_all_free;
	}

	public void setStore_all_free(String store_all_free) {
		this.store_all_free = store_all_free;
	}

	public String getX_cpu_free() {
		return x_cpu_free;
	}

	public void setX_cpu_free(String x_cpu_free) {
		this.x_cpu_free = x_cpu_free;
	}

	public String getX_mem_free() {
		return x_mem_free;
	}

	public void setX_mem_free(String x_mem_free) {
		this.x_mem_free = x_mem_free;
	}

	public String getX_store_free() {
		return x_store_free;
	}

	public void setX_store_free(String x_store_free) {
		this.x_store_free = x_store_free;
	}

	public String getV_cpu_free() {
		return v_cpu_free;
	}

	public void setV_cpu_free(String v_cpu_free) {
		this.v_cpu_free = v_cpu_free;
	}

	public String getV_mem_free() {
		return v_mem_free;
	}

	public void setV_mem_free(String v_mem_free) {
		this.v_mem_free = v_mem_free;
	}

	public String getV_store_free() {
		return v_store_free;
	}

	public void setV_store_free(String v_store_free) {
		this.v_store_free = v_store_free;
	}

	public List getAlarmList() {
		return alarmList;
	}

	public void setAlarmList(List alarmList) {
		this.alarmList = alarmList;
	}

	public int getAlarm_all() {
		return alarm_all;
	}

	public void setAlarm_all(int alarm_all) {
		this.alarm_all = alarm_all;
	}

	public String getX_cpu() {
		return x_cpu;
	}

	public void setX_cpu(String x_cpu) {
		this.x_cpu = x_cpu;
	}

	public String getX_cpu_use() {
		return x_cpu_use;
	}

	public void setX_cpu_use(String x_cpu_use) {
		this.x_cpu_use = x_cpu_use;
	}

	public String getX_mem() {
		return x_mem;
	}

	public void setX_mem(String x_mem) {
		this.x_mem = x_mem;
	}

	public String getX_mem_use() {
		return x_mem_use;
	}

	public void setX_mem_use(String x_mem_use) {
		this.x_mem_use = x_mem_use;
	}

	public String getX_store() {
		return x_store;
	}

	public void setX_store(String x_store) {
		this.x_store = x_store;
	}

	public String getX_store_use() {
		return x_store_use;
	}

	public void setX_store_use(String x_store_use) {
		this.x_store_use = x_store_use;
	}

	public String getV_cpu() {
		return v_cpu;
	}

	public void setV_cpu(String v_cpu) {
		this.v_cpu = v_cpu;
	}

	public String getV_cpu_use() {
		return v_cpu_use;
	}

	public void setV_cpu_use(String v_cpu_use) {
		this.v_cpu_use = v_cpu_use;
	}

	public String getV_mem() {
		return v_mem;
	}

	public void setV_mem(String v_mem) {
		this.v_mem = v_mem;
	}

	public String getV_mem_use() {
		return v_mem_use;
	}

	public void setV_mem_use(String v_mem_use) {
		this.v_mem_use = v_mem_use;
	}

	public String getV_store() {
		return v_store;
	}

	public void setV_store(String v_store) {
		this.v_store = v_store;
	}

	public String getV_store_use() {
		return v_store_use;
	}

	public void setV_store_use(String v_store_use) {
		this.v_store_use = v_store_use;
	}

	public String getCpu_all() {
		return cpu_all;
	}

	public void setCpu_all(String cpu_all) {
		this.cpu_all = cpu_all;
	}

	public String getMem_all() {
		return mem_all;
	}

	public void setMem_all(String mem_all) {
		this.mem_all = mem_all;
	}

	public String getStore_all() {
		return store_all;
	}

	public void setStore_all(String store_all) {
		this.store_all = store_all;
	}

	public String getCpu_all_use() {
		return cpu_all_use;
	}

	public void setCpu_all_use(String cpu_all_use) {
		this.cpu_all_use = cpu_all_use;
	}

	public String getMem_all_use() {
		return mem_all_use;
	}

	public void setMem_all_use(String mem_all_use) {
		this.mem_all_use = mem_all_use;
	}

	public String getStore_all_use() {
		return store_all_use;
	}

	public void setStore_all_use(String store_all_use) {
		this.store_all_use = store_all_use;
	}

	public String getNet_name() {
		return net_name;
	}

	public void setNet_name(String net_name) {
		this.net_name = net_name;
	}

	public int getIp_all() {
		return ip_all;
	}

	public void setIp_all(int ip_all) {
		this.ip_all = ip_all;
	}

	public int getIp_use() {
		return ip_use;
	}

	public void setIp_use(int ip_use) {
		this.ip_use = ip_use;
	}

	public List getNetList() {
		return netList;
	}

	public void setNetList(List netList) {
		this.netList = netList;
	}

	public String getX_cpu_per() {
		return x_cpu_per;
	}

	public void setX_cpu_per(String x_cpu_per) {
		this.x_cpu_per = x_cpu_per;
	}

	public String getX_mem_per() {
		return x_mem_per;
	}

	public void setX_mem_per(String x_mem_per) {
		this.x_mem_per = x_mem_per;
	}

	public String getX_store_per() {
		return x_store_per;
	}

	public void setX_store_per(String x_store_per) {
		this.x_store_per = x_store_per;
	}

	public String getV_cpu_per() {
		return v_cpu_per;
	}

	public void setV_cpu_per(String v_cpu_per) {
		this.v_cpu_per = v_cpu_per;
	}

	public String getV_mem_per() {
		return v_mem_per;
	}

	public void setV_mem_per(String v_mem_per) {
		this.v_mem_per = v_mem_per;
	}

	public String getV_store_per() {
		return v_store_per;
	}

	public void setV_store_per(String v_store_per) {
		this.v_store_per = v_store_per;
	}

	public AlarmHostStatistics getAlarmStatistice() {
		return alarmStatistice;
	}

	public void setAlarmStatistice(AlarmHostStatistics alarmStatistice) {
		this.alarmStatistice = alarmStatistice;
	}

	public List getAlarmResultList() {
		return alarmResultList;
	}

	public void setAlarmResultList(List alarmResultList) {
		this.alarmResultList = alarmResultList;
	}
	
	
}
