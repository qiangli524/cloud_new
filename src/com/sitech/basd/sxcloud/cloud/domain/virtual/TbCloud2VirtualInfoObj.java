package com.sitech.basd.sxcloud.cloud.domain.virtual;

import java.io.Serializable;
import java.util.List;

import com.sitech.basd.common.domain.BasePrivilegeObj;

@SuppressWarnings("serial")
/**
 * 
 */
public class TbCloud2VirtualInfoObj extends BasePrivilegeObj implements Serializable, Cloneable {
	private String ID;// 虚拟机编号
	private String NEED_NUMBERS;// 需求编号
	private String VH_ID;// 虚拟机编号
	private String VH_NAME;// 虚拟机名称
	private String VH_MEM;// 内存
	private String VH_CPU;// CPU
	private String VH_STORAGE;// 存储
	private String VH_SYSTEM;// 操作系统
	private String VH_IP;// IP地址
	private String VH_NETWORK;// 网络
	private String VH_NETWORK_DOUBLE;// 网络
	private String VH_RUNTIME;// 运行时间
	private String EQ_ID;// 所在物理主机编号
	private String VH_STAT;// 虚拟机状态
	private String VH_DESC;// 虚拟机描述
	private String PROJECT_ID;// 所属项目编号
	private String VH_MIN_CPU;// CPU最小值
	private String VH_MAX_CPU;// CPU最大值
	private String VH_MIN_MEM;// 内存最小值
	private String VH_MAX_MEM;// 内存最大值
	private String VH_STARTED;// 开始时间
	private String VH_COMPLETED;// 完成时间
	private String VH_DEPLOYETIME;// 部署时间
	private String VH_UPTIME;// 更新时间
	private String VH_LOG;// 日志
	private String VH_USER;// 虚拟机用户
	private String VH_PASS;// 虚拟机密码
	private String START_STOP_FLAG_NAME;// 控制虚拟机启停时用到的
	private String VH_STORAGE_NAME;// 存储器名称
	private String VH_STORAGE_VALUE;// 存储器大小
	/** CPU处理单元 */
	private String VH_PROCESS_UNIT;
	/** CPU处理单元最大值 */
	private String VH_MAX_PROCESS_UNIT;
	/** CPU处理单元最小值 */
	private String VH_MIN_PROCESS_UNIT;
	/** CPU独占或共享模式 */
	private String VH_CPU_MODE;
	/** CPU处理单元独占或共享模式 */
	private String VH_PROCESS_UNIT_MODE;
	/** 虚拟机ID-IBM云-SKC返回值 */
	private String VH_ID_IBM;
	/** CPU处理单元在共享处理单元处理池权重 */
	private String VH_PROCESS_UNIT_Priority;

	private String VH_NULL;
	private List<TbCloud2VirtualInfoObj> tbVirtualList;// 获取虚拟机列表
	private String TASK_ID;// 任务编号
	private String PROCESS_NODE;// 流程节点
	private String VH_TYPE = "-1";// 虚拟机类型，kvm,vmware,ibm等

	public String getVH_TYPE() {
		return VH_TYPE;
	}

	public void setVH_TYPE(String vh_type) {
		VH_TYPE = vh_type;
	}

	public String getTASK_ID() {
		return TASK_ID;
	}

	public void setTASK_ID(String task_id) {
		TASK_ID = task_id;
	}

	public String getPROCESS_NODE() {
		return PROCESS_NODE;
	}

	public void setPROCESS_NODE(String process_node) {
		PROCESS_NODE = process_node;
	}

	public List<TbCloud2VirtualInfoObj> getTbVirtualList() {
		return tbVirtualList;
	}

	public void setTbVirtualList(List<TbCloud2VirtualInfoObj> tbVirtualList) {
		this.tbVirtualList = tbVirtualList;
	}

	public String getVH_ID() {
		return VH_ID;
	}

	public String getVH_PROCESS_UNIT_Priority() {
		return VH_PROCESS_UNIT_Priority;
	}

	public void setVH_PROCESS_UNIT_Priority(String priority) {
		VH_PROCESS_UNIT_Priority = priority;
	}

	public void setVH_ID(String vh_id) {
		VH_ID = vh_id;
	}

	public String getVH_NAME() {
		return VH_NAME;
	}

	public void setVH_NAME(String vh_name) {
		VH_NAME = vh_name;
	}

	public String getVH_MEM() {
		return VH_MEM;
	}

	public void setVH_MEM(String vh_mem) {
		VH_MEM = vh_mem;
	}

	public String getVH_CPU() {
		return VH_CPU;
	}

	public void setVH_CPU(String vh_cpu) {
		VH_CPU = vh_cpu;
	}

	public String getVH_STORAGE() {
		return VH_STORAGE;
	}

	public void setVH_STORAGE(String vh_storage) {
		VH_STORAGE = vh_storage;
	}

	public String getVH_SYSTEM() {
		return VH_SYSTEM;
	}

	public void setVH_SYSTEM(String vh_system) {
		VH_SYSTEM = vh_system;
	}

	public String getVH_IP() {
		return VH_IP;
	}

	public void setVH_IP(String vh_ip) {
		VH_IP = vh_ip;
	}

	public String getVH_NETWORK() {
		return VH_NETWORK;
	}

	public void setVH_NETWORK(String vh_network) {
		VH_NETWORK = vh_network;
	}

	public String getVH_RUNTIME() {
		return VH_RUNTIME;
	}

	public void setVH_RUNTIME(String vh_runtime) {
		VH_RUNTIME = vh_runtime;
	}

	public String getEQ_ID() {
		return EQ_ID;
	}

	public void setEQ_ID(String eq_id) {
		EQ_ID = eq_id;
	}

	public String getVH_STAT() {
		return VH_STAT;
	}

	public void setVH_STAT(String vh_stat) {
		VH_STAT = vh_stat;
	}

	public String getVH_DESC() {
		return VH_DESC;
	}

	public void setVH_DESC(String vh_desc) {
		VH_DESC = vh_desc;
	}

	public String getPROJECT_ID() {
		return PROJECT_ID;
	}

	public void setPROJECT_ID(String project_id) {
		PROJECT_ID = project_id;
	}

	public String getVH_MIN_CPU() {
		return VH_MIN_CPU;
	}

	public void setVH_MIN_CPU(String vh_min_cpu) {
		VH_MIN_CPU = vh_min_cpu;
	}

	public String getVH_MAX_CPU() {
		return VH_MAX_CPU;
	}

	public void setVH_MAX_CPU(String vh_max_cpu) {
		VH_MAX_CPU = vh_max_cpu;
	}

	public String getVH_MIN_MEM() {
		return VH_MIN_MEM;
	}

	public void setVH_MIN_MEM(String vh_min_mem) {
		VH_MIN_MEM = vh_min_mem;
	}

	public String getVH_MAX_MEM() {
		return VH_MAX_MEM;
	}

	public void setVH_MAX_MEM(String vh_max_mem) {
		VH_MAX_MEM = vh_max_mem;
	}

	public String getVH_STARTED() {
		return VH_STARTED;
	}

	public void setVH_STARTED(String vh_started) {
		VH_STARTED = vh_started;
	}

	public String getVH_COMPLETED() {
		return VH_COMPLETED;
	}

	public void setVH_COMPLETED(String vh_completed) {
		VH_COMPLETED = vh_completed;
	}

	public String getVH_DEPLOYETIME() {
		return VH_DEPLOYETIME;
	}

	public void setVH_DEPLOYETIME(String vh_deployetime) {
		VH_DEPLOYETIME = vh_deployetime;
	}

	public String getVH_UPTIME() {
		return VH_UPTIME;
	}

	public void setVH_UPTIME(String vh_uptime) {
		VH_UPTIME = vh_uptime;
	}

	public String getVH_LOG() {
		return VH_LOG;
	}

	public void setVH_LOG(String vh_log) {
		VH_LOG = vh_log;
	}

	public String getVH_USER() {
		return VH_USER;
	}

	public void setVH_USER(String vh_user) {
		VH_USER = vh_user;
	}

	public String getVH_PASS() {
		return VH_PASS;
	}

	public void setVH_PASS(String vh_pass) {
		VH_PASS = vh_pass;
	}

	public String getVH_PROCESS_UNIT() {
		return VH_PROCESS_UNIT;
	}

	public void setVH_PROCESS_UNIT(String vh_process_unit) {
		VH_PROCESS_UNIT = vh_process_unit;
	}

	public String getVH_MAX_PROCESS_UNIT() {
		return VH_MAX_PROCESS_UNIT;
	}

	public void setVH_MAX_PROCESS_UNIT(String vh_max_process_unit) {
		VH_MAX_PROCESS_UNIT = vh_max_process_unit;
	}

	public String getVH_MIN_PROCESS_UNIT() {
		return VH_MIN_PROCESS_UNIT;
	}

	public void setVH_MIN_PROCESS_UNIT(String vh_min_process_unit) {
		VH_MIN_PROCESS_UNIT = vh_min_process_unit;
	}

	public String getSTART_STOP_FLAG_NAME() {
		return START_STOP_FLAG_NAME;
	}

	public void setSTART_STOP_FLAG_NAME(String start_stop_flag_name) {
		START_STOP_FLAG_NAME = start_stop_flag_name;
	}

	public String getVH_STORAGE_NAME() {
		return VH_STORAGE_NAME;
	}

	public void setVH_STORAGE_NAME(String vh_storage_name) {
		VH_STORAGE_NAME = vh_storage_name;
	}

	public String getVH_STORAGE_VALUE() {
		return VH_STORAGE_VALUE;
	}

	public void setVH_STORAGE_VALUE(String vh_storage_value) {
		VH_STORAGE_VALUE = vh_storage_value;
	}

	public String getVH_CPU_MODE() {
		return VH_CPU_MODE;
	}

	public void setVH_CPU_MODE(String vh_cpu_mode) {
		VH_CPU_MODE = vh_cpu_mode;
	}

	public String getVH_PROCESS_UNIT_MODE() {
		return VH_PROCESS_UNIT_MODE;
	}

	public void setVH_PROCESS_UNIT_MODE(String vh_process_unit_mode) {
		VH_PROCESS_UNIT_MODE = vh_process_unit_mode;
	}

	public String getVH_ID_IBM() {
		return VH_ID_IBM;
	}

	public void setVH_ID_IBM(String vh_id_ibm) {
		VH_ID_IBM = vh_id_ibm;
	}

	public String getVH_NULL() {
		return VH_NULL;
	}

	public void setVH_NULL(String vh_null) {
		VH_NULL = vh_null;
	}

	public String getVH_NETWORK_DOUBLE() {
		return VH_NETWORK_DOUBLE;
	}

	public void setVH_NETWORK_DOUBLE(String vh_network_double) {
		VH_NETWORK_DOUBLE = vh_network_double;
	}

	public String getNEED_NUMBERS() {
		return NEED_NUMBERS;
	}

	public void setNEED_NUMBERS(String need_numbers) {
		NEED_NUMBERS = need_numbers;
	}
}
