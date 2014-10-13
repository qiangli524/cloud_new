package com.sitech.basd.sxcloud.cloud.web.virtual.form;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.virtual.Tbcloud2VirtualStorageInfoObj;

@SuppressWarnings("serial")
public class VirtualForm {
	private String VH_ID;// 虚拟机编号
	private String VH_NAME;// 虚拟机名称
	private String VH_MEM;// 内存
	private String VH_CPU;// CPU
	private String VH_STORAGE;// 存储
	private String VH_SYSTEM;// 操作系统
	private String VH_IP;// IP地址
	private String VH_NETWORK;// 网络
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
	private String VH_STORAGE_NAME;// 存储器名称
	private String VH_STORAGE_VALUE;// 存储器大小
	private String start_stop_flag; // '0：停止 1：启动',
	private String start_stop_flag_name;
	private int flag = 0;
	private String SETTINGS; // 处理器设置
	private String MODEL; // 处理单元模式
	private String NET_ID; // 网络ID
	private List netList; // 网络下拉列表
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
	private int ID;//自增序列-虚拟机信息表唯一标示
	private String VH_UUID;//Xen虚拟机唯一标识UUId
	
	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getVH_UUID() {
		return VH_UUID;
	}

	public void setVH_UUID(String vh_uuid) {
		VH_UUID = vh_uuid;
	}

	public String getVH_PROCESS_UNIT_Priority() {
		return VH_PROCESS_UNIT_Priority;
	}

	public void setVH_PROCESS_UNIT_Priority(String vHPROCESSUNITPriority) {
		VH_PROCESS_UNIT_Priority = vHPROCESSUNITPriority;
	}

	private List<Tbcloud2VirtualStorageInfoObj> list;

	public List<Tbcloud2VirtualStorageInfoObj> getList() {
		return list;
	}

	public void setList(List<Tbcloud2VirtualStorageInfoObj> list) {
		this.list = list;
	}

	@SuppressWarnings("unchecked")
	private List projectList = null;
	@SuppressWarnings("unchecked")
	private List resultList = null;

	public String getVH_ID() {
		return VH_ID;
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

	public String getStart_stop_flag_name() {
		return start_stop_flag_name;
	}

	public void setStart_stop_flag_name(String start_stop_flag_name) {
		this.start_stop_flag_name = start_stop_flag_name;
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

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@SuppressWarnings("unchecked")
	public List getResultList() {
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	@SuppressWarnings("unchecked")
	public List getProjectList() {
		return projectList;
	}

	@SuppressWarnings("unchecked")
	public void setProjectList(List projectList) {
		this.projectList = projectList;
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

	public String getStart_stop_flag() {
		return start_stop_flag;
	}

	public void setStart_stop_flag(String start_stop_flag) {
		this.start_stop_flag = start_stop_flag;
	}

	public String getSETTINGS() {
		return SETTINGS;
	}

	public void setSETTINGS(String settings) {
		SETTINGS = settings;
	}

	public String getMODEL() {
		return MODEL;
	}

	public void setMODEL(String model) {
		MODEL = model;
	}

	public String getNET_ID() {
		return NET_ID;
	}

	public void setNET_ID(String net_id) {
		NET_ID = net_id;
	}

	public List getNetList() {
		return netList;
	}

	public void setNetList(List netList) {
		this.netList = netList;
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

}
